package pl.edu.pw.ee.catering.model.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
