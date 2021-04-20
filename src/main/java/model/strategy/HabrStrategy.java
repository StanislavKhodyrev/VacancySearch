package model.strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import java.io.IOException;
import java.util.*;


/**
 * @author Stanislav Khodyrev
 */

/* Парсинг HTML для ресурса career.habr.com */
public class HabrStrategy implements Strategy {
    private static final List<Vacancy> vacancies = new ArrayList<>();
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=%s&page=%d&city_id=%s";

    public static void main(String[] args) {
        vacancies.forEach(x -> System.out.println(vacancies.indexOf(x) + 1 + " " + x));
    }

    @Override
    public List<Vacancy> getVacancies(String findPosition, City findCity, int count) {
        Document doc;
        int page = 0;

        try {
            while (count > 0) {
                doc = getDocument(findPosition, page, findCity);
                Elements elements = doc.getElementsByClass("vacancy-card__info");
                for (Element element : elements) {
                    if (count == 0) break;
                    String companyName = element.getElementsByClass("vacancy-card__company-title").text();
                    String position = element.getElementsByClass("vacancy-card__title").text();
                    String city = findCity.getName();
                    String salary = element.getElementsByClass("vacancy-card__salary").text();
                    String siteName = "https://career.habr.com";
                    String url = siteName +
                            element.child(1).getElementsByTag("a").get(0).attr("href");
                    Vacancy vacancy = new Vacancy(position, companyName, city, salary, siteName, url);
                    vacancies.add(vacancy);
                    count--;
                }
                page++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    /* Форматируем тело запроса url и получаем документ Jsoup */
    static Document getDocument(String position, int page, City city) throws IOException {
        position = position.replaceAll(" ", "%20");
        String myURL = String.format(URL_FORMAT, position, page, city.getHabrId());
        return Jsoup.connect(myURL).get();
    }


}
