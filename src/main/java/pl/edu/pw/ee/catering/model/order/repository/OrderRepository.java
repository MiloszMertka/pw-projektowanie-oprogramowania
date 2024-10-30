package pl.edu.pw.ee.catering.model.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderWithDetails, Long> {
    List<OrderWithDetails> findAllByCompanyId(Long companyId);
}

