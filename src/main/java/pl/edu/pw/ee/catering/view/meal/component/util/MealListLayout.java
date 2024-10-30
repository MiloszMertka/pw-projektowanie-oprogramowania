package pl.edu.pw.ee.catering.view.meal.component.util;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

@CssImport("./styles/meal-list/meal-list-layout.css")
public class MealListLayout extends VerticalLayout {

    public MealListLayout(MealList mealList) {
        addClassName("meal-list-layout.");

        mealList.getMeals().forEach(this::addMealCard);
    }

    private void addMealCard(Meal meal) {
        MealCard mealCard = new MealCard(meal);
        add(mealCard);
    }
}
