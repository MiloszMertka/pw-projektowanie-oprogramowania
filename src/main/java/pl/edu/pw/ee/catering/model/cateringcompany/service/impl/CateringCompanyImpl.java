package pl.edu.pw.ee.catering.model.cateringcompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.service.IMeal;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CateringCompanyImpl implements ICateringCompany {
    private final IMeal meal;

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
    public OrderWithDetails getOrderDetails(Long id) {
        return new OrderWithDetails(
            id,
            "Typical Order",
            LocalDateTime.now().toString(),
            OrderStatus.IN_PREPARATION
        );
    }

    public OrderList showOrderList(Long id) {
        return new OrderList(new ArrayList<>());
    }

    @Override
    public MealList showMealList(Long id) {
        return meal.getMealList(id);
    }
}
