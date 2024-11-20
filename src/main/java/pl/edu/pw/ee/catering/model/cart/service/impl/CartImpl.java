package pl.edu.pw.ee.catering.model.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cart.entity.Cart;
import pl.edu.pw.ee.catering.model.cart.repository.CartRepository;
import pl.edu.pw.ee.catering.model.cart.service.ICart;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartImpl implements ICart {
    private final CartRepository cartRepository;

    @Override
    public void addMealToCart(Meal meal) {
        if (cartRepository.findByClientId(1L).isEmpty()) {
            cartRepository.save(Cart.builder()
                    .clientId(1L)
                    .meals(List.of(meal))
                    .build()
            );
            return;
        }

        Cart cart = cartRepository.findByClientId(1L).get();
        List<Meal> meals = cart.getMeals();
        meals.add(meal);
        cart.setMeals(meals);
        cartRepository.save(cart);
    }

}
