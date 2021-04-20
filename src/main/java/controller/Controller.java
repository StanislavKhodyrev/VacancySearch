package controller;

import model.strategy.City;
import model.Model;

public class Controller {
    private final Model model;


    public Controller(Model model) {
        this.model = model;
        if (model == null) {
            throw new IllegalArgumentException();
        }
    }

    public void onParametersSelect(String position, City cityName, int count) {
        model.selectParameters(position, cityName, count);
    }

}
