package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.view.meal.component.MealListComponent;
import pl.edu.pw.ee.catering.view.meal.ui.IMealList;

@UIScope
@SpringComponent
@CssImport("./styles/styles.css")
@Route("meals")
public class CateringCompanyMealListUI extends VerticalLayout implements IMealList {

    @Override
    public void showMealList(MealList mealList) {
        removeAll();
        MealListComponent mealListComponent = new MealListComponent(mealList);
        add(mealListComponent);
    }
}
