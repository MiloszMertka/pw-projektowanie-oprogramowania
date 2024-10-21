package pl.edu.pw.ee.catering.model.cateringcompany.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;

public interface ICateringCompany {
    void createMeal(MealDetails mealDetails);
    void deleteMeal(Long id);
    OrderList showHistoricalOrderList(Long id);
}
