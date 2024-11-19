package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.component.UI;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.meal.usecase.IDeleteMealUC;
import pl.edu.pw.ee.catering.view.meal.ui.IDeleteMeal;

@UIScope
@SpringComponent
@Route("delete-meal")
public class DeleteMealComponent extends VerticalLayout implements IDeleteMeal, HasUrlParameter<Long> {

    private Long mealId;
    private final IDeleteMealUC deleteMealUC;
    private final ICateringCompanyRouter router;

    public DeleteMealComponent(IDeleteMealUC deleteMeal, ICateringCompanyRouter router) {
        this.router = router;
        this.deleteMealUC = deleteMeal;
        setSpacing(true);
        setPadding(true);
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.mealId = parameter;
        deleteMeal(mealId);
    }

    @Override
    public void deleteMeal(Long id) {
        Dialog confirmDialog = new Dialog();
        confirmDialog.add("Czy na pewno chcesz usunąć ten posiłek?");

        Button confirmButton = new Button("Tak, usuń", event -> {
            deleteMealUC.deleteMeal(mealId);
            Notification.show("Posiłek został usunięty.");
            router.navigateToMealList();
            confirmDialog.close();
        });

        Button cancelButton = new Button("Anuluj", event -> {
            router.navigateToMealList();
            confirmDialog.close();
        });

        confirmDialog.add(confirmButton, cancelButton);
        confirmDialog.open();
    }
}
