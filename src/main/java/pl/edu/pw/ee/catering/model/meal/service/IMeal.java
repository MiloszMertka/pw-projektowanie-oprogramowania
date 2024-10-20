package pl.edu.pw.ee.catering.model.meal.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

public interface IMeal {

    Meal getMeal(long id);

    void createMeal(MealDetails meal);

    void editMeal(MealDetails mealDetails);

    void deleteMeal(Long id);
    MealList getMealList(Long id);
}
