package pl.edu.pw.ee.catering.model.cateringcompany.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.service.IMeal;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.service.IOrder;

@Service
@RequiredArgsConstructor
public class CateringCompanyImpl implements ICateringCompany {
    private final IMeal meal;
    private final IOrder order;

    @Override
    public Meal getMeal(long id) {
        return meal.getMeal(id);
    }

    @Override
    public void createMeal(MealDetails mealDetails) {
        meal.createMeal(mealDetails);
    }

    @Override
    public void editMeal(MealDetails mealDetails) {
        meal.editMeal(mealDetails);
    }

    @Override
    public void deleteMeal(Long id) {
        meal.deleteMeal(id);
    }

    @Override
    public OrderList showHistoricalOrderList(Long id) {
        return new OrderList(new ArrayList<>());
    }

    @Override
    public OrderList showOrderList(Long id) {
        return new OrderList(new ArrayList<>());
    }

    @Override
    public MealList showMealList(Long id) {
        return meal.getMealList(id);
    }
    
    @Override
    public OrderWithDetails getOrder(Long id) {
        return order.getOrder(id);
    }
    
    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        order.changeOrderStatus(id, status);
    }
}
