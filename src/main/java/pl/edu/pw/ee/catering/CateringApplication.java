package pl.edu.pw.ee.catering;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pw.ee.catering.model.meal.entity.Image;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.entity.Price;
import pl.edu.pw.ee.catering.model.meal.repository.MealRepository;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Currency;

@SpringBootApplication
public class CateringApplication {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MealRepository mealRepository;

    public static void main(String[] args) {
        SpringApplication.run(CateringApplication.class, args);
    }
	
  @PostConstruct
  public void init() {
	AppOrder order1 = new AppOrder(1L, "zamowienie1", "01-01-2024", OrderStatus.IN_PREPARATION, 1L, 1L);
	AppOrder order2 = new AppOrder(2L, "zamowienie2", "01-01-2023", OrderStatus.FINISHED, 1L, 1L);
	orderRepository.save(order1);
	orderRepository.save(order2);
	
        Meal meal = new Meal(1L, "meal1", 111,
            Price.builder().amount(19.99).currency(Currency.getInstance("PLN")).build(),
            new Image("", ""), new ArrayList<>(), true, "Nothing", 1L);
        mealRepository.save(meal);
  }

}
