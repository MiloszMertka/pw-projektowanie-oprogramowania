package pl.edu.pw.ee.catering.model.meal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MealList {
    private List<Meal> meals;
}
