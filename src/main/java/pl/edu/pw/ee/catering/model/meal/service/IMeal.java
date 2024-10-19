package pl.edu.pw.ee.catering.model.meal.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;

public interface IMeal {
    void createMeal(MealDetails meal);
    void deleteMeal(Long id);
}
