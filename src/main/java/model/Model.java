package model;

import model.strategy.City;
import model.strategy.Strategy;
import view.View;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final View view;
    private final Strategy[]strategies;

    public Model(View view, Strategy... strategies) {
        this.view = view;
        this.strategies = strategies;
        if (view == null || strategies == null || strategies.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public void selectParameters(String position, City city, int count) {
        List<Vacancy> allVacancyList = new ArrayList<>();
        for (Strategy strategy : strategies) {
            allVacancyList.addAll(strategy.getVacancies(position, city, count));
        }
        view.update(allVacancyList);
    }

}
