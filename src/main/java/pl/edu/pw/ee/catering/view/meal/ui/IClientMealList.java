package pl.edu.pw.ee.catering.view.meal.ui;

import pl.edu.pw.ee.catering.model.meal.dto.MealList;

public interface IClientMealList {
    void showClientMealList(MealList mealList);
    void showMessage(String message);
}
