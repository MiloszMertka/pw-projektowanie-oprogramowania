package pl.edu.pw.ee.catering.presenter.meal.usecase;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;

public interface ICreateMealUC {
    void createMeal(MealDetails mealDetails);
}
