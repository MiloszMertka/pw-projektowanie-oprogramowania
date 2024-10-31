package pl.edu.pw.ee.catering.view.meal.component;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.view.meal.component.util.MealListLayout;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.AddMealButton;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.DeleteMealButton;

@CssImport("./styles/meal-list/meal-list-component.css")
public class MealListComponent extends VerticalLayout {

    public MealListComponent(MealList mealList) {
        initLayout(mealList);
    }

    private void initLayout(MealList mealList) {
        H1 title = new H1("Aktualna oferta:");
        add(title);

        MealListLayout mealListLayout = new MealListLayout(mealList);
        mealListLayout.addClassName("meal-list-layout");
        add(mealListLayout);

        AddMealButton addMealButton = new AddMealButton();

        HorizontalLayout buttonLayout = new HorizontalLayout(addMealButton);
        buttonLayout.addClassName("meal-button-layout");

        add(buttonLayout);
    }
}
