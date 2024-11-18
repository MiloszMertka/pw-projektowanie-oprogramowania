package pl.edu.pw.ee.catering;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;

@SpringBootApplication
public class CateringApplication {

	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(CateringApplication.class, args);
	}

	@PostConstruct
	public void init() {
		AppOrder order1 = new AppOrder(1L, "zamowienie1", "01-01-2024", OrderStatus.IN_PREPARATION, 1L, 1L);
		AppOrder order2 = new AppOrder(2L, "zamowienie2", "01-01-2023", OrderStatus.FINISHED, 1L, 1L);
		orderRepository.save(order1);
		orderRepository.save(order2);
	}

}
