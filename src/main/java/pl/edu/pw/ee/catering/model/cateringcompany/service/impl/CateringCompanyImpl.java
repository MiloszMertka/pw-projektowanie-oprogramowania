package pl.edu.pw.ee.catering.model.cateringcompany.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
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
        return order.getHistoricalOrderList(id);
    }

    @Override
    public OrderWithDetails getOrderDetails(Long id) {
        return order.getOrderWithDetails(id);
    }
    
    @Override
    public OrderList showOrderList(Long id) {
        return order.getOrderList(id);
    }

    @Override
    public MealList showMealList(Long id) {
        return meal.getMealList(id);
    }
    
    @Override
    public OrderWithDetails getOrderWithDetails(Long id) {
        return order.getOrderWithDetails(id);
    }
    
    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        order.changeOrderStatus(id, status);
    }

    @Override
    public OrderList getClientHistoricalOrderList(Long clientId) {
        return new OrderList(List.of(AppOrder.builder().id(1L).name("Order 1").date("2024-01-01").status(OrderStatus.FINISHED).companyId(1L).build())); // to be replaced
    }
}
