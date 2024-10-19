package pl.edu.pw.ee.catering.model.cateringcompany.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;

public interface ICateringCompany {
    void createMeal(MealDetails mealDetails);
    void deleteMeal(Long id);
}
