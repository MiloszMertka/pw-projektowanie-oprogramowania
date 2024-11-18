package pl.edu.pw.ee.catering.view.meal.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.view.meal.component.util.MealListLayout;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.AddMealButton;

@CssImport("./styles/meal-list/meal-list-component.css")
public class MealListComponent extends VerticalLayout {

    public MealListComponent(MealList mealList, boolean isMutable) {
        initLayout(mealList, isMutable);
    }

    private void initLayout(MealList mealList, boolean isMutable) {
        H1 title = new H1(isMutable ? "Aktualna oferta:" : "Lista posiłków:");
        add(title);

        MealListLayout mealListLayout = new MealListLayout(mealList, isMutable);
        mealListLayout.addClassName("meal-list-layout");
        add(mealListLayout);

        AddMealButton addMealButton = new AddMealButton();
        Button closeButton = new Button("Zamknij", event -> {
            getUI().ifPresent(ui -> ui.navigate(isMutable ? "" : "client"));
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(/*addMealButton,*/ closeButton);
        buttonLayout.addClassName("meal-button-layout");

        add(buttonLayout);
    }
}
