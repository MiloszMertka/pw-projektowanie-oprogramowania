package pl.edu.pw.ee.catering.view.meal.component.util;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.presenter.client.usecase.IAddMealToCartUC;

@CssImport("./styles/meal-list/meal-list-layout.css")
public class MealListLayout extends VerticalLayout {

    public MealListLayout(MealList mealList, boolean isMutable, IAddMealToCartUC addMealToCartUC) {
        addClassName("meal-list-layout.");

        mealList.getMeals().forEach((meal) -> addMealCard(meal, isMutable, addMealToCartUC));
    }

    private void addMealCard(Meal meal, boolean isMutable, IAddMealToCartUC addMealToCartUC) {
        MealCard mealCard = new MealCard(meal, isMutable, addMealToCartUC);
        add(mealCard);
    }
}
