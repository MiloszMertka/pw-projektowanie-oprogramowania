package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.view.meal.component.ClientMealListMessageComponent;
import pl.edu.pw.ee.catering.view.meal.component.MealListComponent;
import pl.edu.pw.ee.catering.view.meal.ui.IClientMealList;

@UIScope
@SpringComponent
@Route("/client-meal")
@RequiredArgsConstructor
public class ClientMealUI extends VerticalLayout implements IClientMealList {

    @Override
    public void showClientMealList(MealList mealList) {
        removeAll();
        MealListComponent clientMealListComponent = new MealListComponent(mealList, false);
        add(clientMealListComponent);
    }

    @Override
    public void showMessage(String message) {
        removeAll();
        ClientMealListMessageComponent clientMealListMessageComponent = new ClientMealListMessageComponent(message);
        add(clientMealListMessageComponent);
    }
}
