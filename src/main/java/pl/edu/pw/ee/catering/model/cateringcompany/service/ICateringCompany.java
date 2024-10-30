package pl.edu.pw.ee.catering.model.cateringcompany.service;

import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface ICateringCompany {

    Meal getMeal(long id);

    void createMeal(MealDetails mealDetails);

    void editMeal(MealDetails mealDetails);

    void deleteMeal(Long id);

    OrderList showHistoricalOrderList(Long id);

    MealList showMealList(Long id);
    
    OrderWithDetails getOrder(Long id);
    
    void changeOrderStatus(Long id, OrderStatus status);
}
