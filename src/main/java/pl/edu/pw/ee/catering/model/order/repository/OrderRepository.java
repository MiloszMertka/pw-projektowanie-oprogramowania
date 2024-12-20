package pl.edu.pw.ee.catering.model.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;

@Repository
public interface OrderRepository extends JpaRepository<AppOrder, Long> {
    List<AppOrder> findAllByCompanyId(Long companyId);
    
    List<AppOrder> findAllByCompanyIdAndStatusIsNot(Long companyId, OrderStatus status);

    List<AppOrder> findAllByClientId(Long clientId);
}

