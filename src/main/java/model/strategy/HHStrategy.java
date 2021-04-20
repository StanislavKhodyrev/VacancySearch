package model.strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Stanislav Khodyrev
 */

/* Парсинг HTML для ресурса hh.ru */
public class HHStrategy implements Strategy {
    private static final List<Vacancy> vacancies = new ArrayList<>();
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=%s&search_field=name&page=%d&area=%s";


    @Override
    public List<Vacancy> getVacancies(String findPosition, City findCity, int count) {
        Document doc;
        int page = 0;

        try {
            while (count > 0) {
                doc = getDocument(findPosition, page, findCity);
                Elements elements = doc.getElementsByClass("vacancy-serp-item");
                for (Element element : elements) {
                    if (count == 0) break;
                    String city = findCity.getName();
                    String position = element.getElementsByAttributeValueContaining("data-qa", "vacancy-title").text();
                    String companyName = element.getElementsByAttributeValueContaining("data-qa", "vacancy-employer").text();
                    String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                    String siteName = "https://hh.ru/";
                    String url = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
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
        position = position.replaceAll(" ", "+");
        String myURL = String.format(URL_FORMAT, position, page, city.getHHId());
        return Jsoup.connect(myURL).get();
    }


}
