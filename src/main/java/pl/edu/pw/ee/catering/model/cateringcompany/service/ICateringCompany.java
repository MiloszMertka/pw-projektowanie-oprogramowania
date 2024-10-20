package pl.edu.pw.ee.catering.model.cateringcompany.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;

public interface ICateringCompany {

    Meal getMeal(long id);

    void createMeal(MealDetails mealDetails);

    void editMeal(MealDetails mealDetails);

    void deleteMeal(Long id);
    MealList showMealList(Long id);
}
