package pl.edu.pw.ee.catering.model.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.ee.catering.model.cart.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
}
