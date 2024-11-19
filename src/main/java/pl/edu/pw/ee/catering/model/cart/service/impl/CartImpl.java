package pl.edu.pw.ee.catering.model.cart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cart.entity.Cart;
import pl.edu.pw.ee.catering.model.cart.repository.CartRepository;
import pl.edu.pw.ee.catering.model.cart.service.ICart;

@Service
@RequiredArgsConstructor
public class CartImpl implements ICart {
    private final CartRepository cartRepository;

    @Override
    public void addMealToCart(Long mealId) {
        cartRepository.save(Cart.builder()
                .clientId(1L)
                .mealId(mealId)
                .build()
        );
    }

}
