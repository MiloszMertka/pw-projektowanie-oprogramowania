package pl.edu.pw.ee.catering.model.meal.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;

public interface IMeal {
    void createMeal(MealDetails meal);
    void deleteMeal(Long id);
    MealList getMealList(Long id);
}
