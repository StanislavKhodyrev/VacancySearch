package model.strategy;

import vo.Vacancy;

import java.util.List;

public interface Strategy {
    /* Получаем список вакансий (findPosition) в городе (findCity) в количетсве (count) */
    List<Vacancy> getVacancies(String findPosition, City findCity, int count);
}
