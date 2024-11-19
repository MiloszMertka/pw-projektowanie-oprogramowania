package pl.edu.pw.ee.catering.model.cart.service;

import pl.edu.pw.ee.catering.model.meal.entity.Meal;

public interface ICart {
    void addMealToCart(Meal meal);
}
