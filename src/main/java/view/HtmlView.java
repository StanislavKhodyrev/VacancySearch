package view;

import model.strategy.City;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import vo.Vacancy;
import controller.Controller;
import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "src/main/java/"
            + this.getClass().getPackage().getName().replace('.', '/')
            + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();
            Elements templateHidden = doc.getElementsByClass("template");
            Element template = templateHidden.clone().removeAttr("style").removeClass("template").get(0);

            //удаляем станые вакансии
            Elements prevVacancies = doc.getElementsByClass("vacancy");

            for (Element redundant : prevVacancies) {
                if (!redundant.hasClass("template")) {
                    redundant.remove();
                }
            }

            //добавляем новые
            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = template.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getPosition());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Element salary = vacancyElement.getElementsByClass("salary").get(0);
                salary.appendText(vacancy.getSalary());
                Element siteName = vacancyElement.getElementsByClass("siteName").get(0);
                siteName.appendText(vacancy.getSiteName());

                templateHidden.before(vacancyElement.outerHtml());
            }


            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String dataFile) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            out.write(dataFile);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setParameters(String position, City city, int count) {
        controller.onParametersSelect(position, city, count);
    }
}
