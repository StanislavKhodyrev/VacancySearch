import controller.Controller;
import model.Model;
import model.strategy.City;
import model.strategy.HHStrategy;
import model.strategy.HabrStrategy;
import view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        Aggregator.find("Java junior", City.Moscow, 10);
    }

    public static void find(String position, City city, int count) {
        HtmlView view = new HtmlView();
        Model model = new Model(view, new HHStrategy(), new HabrStrategy());
        Controller controller = new Controller(model);
        view.setController(controller);
        view.setParameters(position, city, count);
        System.out.println("Файл vacancies.html обновлен");
    }
}
