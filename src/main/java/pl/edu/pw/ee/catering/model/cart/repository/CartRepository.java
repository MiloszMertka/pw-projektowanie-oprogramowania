package pl.edu.pw.ee.catering.model.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.ee.catering.model.cart.entity.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClientId(Long clientId);
}
