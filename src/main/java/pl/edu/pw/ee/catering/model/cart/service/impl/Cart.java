package pl.edu.pw.ee.catering.model.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cart.service.ICart;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

@Service
@RequiredArgsConstructor
public class Cart implements ICart {

    @Override
    public void addMealToCart(Meal meal) {
        // TODO
    }

}
