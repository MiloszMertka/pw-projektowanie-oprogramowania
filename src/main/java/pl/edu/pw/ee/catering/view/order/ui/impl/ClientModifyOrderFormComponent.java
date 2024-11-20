package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.presenter.order.usecase.IModifyOrderUC;
import pl.edu.pw.ee.catering.view.order.ui.IModifyOrderForm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringComponent
@Route("edit-order")
public class ClientModifyOrderFormComponent extends VerticalLayout implements IModifyOrderForm, HasUrlParameter<Long> {
    private final IModifyOrderUC modifyOrderUC;

    private Long orderId;

    private final TextField nameField = new TextField("Nazwa zamówienia");
    private final DatePicker dateField = new DatePicker("Data zamówienia");
    private final ComboBox<String> statusField = new ComboBox<>("Status zamówienia");
    private final Button saveButton = new Button("Zapisz");
    private final Button cancelButton = new Button("Anuluj");
    private final VerticalLayout mealsLayout = new VerticalLayout();
    private List<String> meals = new ArrayList<>();


    public ClientModifyOrderFormComponent(IModifyOrderUC modifyOrderUC) {
        this.add(new H1("Zmodyfikuj zamówienie"));
        this.modifyOrderUC = modifyOrderUC;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.orderId = parameter;
    }

    @Override
    public void showOrderModifyForm(OrderWithDetails order) {
        this.configureForm(order);

        updateMealsList();

        nameField.setValue(order.getName());
        nameField.setReadOnly(true);
        dateField.setValue(LocalDate.parse(order.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        dateField.setReadOnly(true);
        statusField.setValue(order.getStatus().getDisplayName());
        statusField.setReadOnly(true);    }

    @Override
    public void showSuccessModifyWindow() {
        Dialog successDialog = new Dialog();

        Text successMessage = new Text("Pomyślnie zmodyfikowano zamówienie!");

        Button closeButton = new Button("Powrót", event -> {
            successDialog.close();
            getUI().ifPresent(ui -> ui.navigate("/client"));
        });
        closeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout dialogLayout = new VerticalLayout(successMessage, closeButton);
        dialogLayout.setSpacing(true);
        dialogLayout.setAlignItems(Alignment.CENTER);
        dialogLayout.setPadding(true);

        successDialog.add(dialogLayout);

        successDialog.open();
    }

    private void configureForm(OrderWithDetails order) {
        this.meals = new ArrayList<>(Arrays.asList("Burger", "Pizza", "Pasta"));

        statusField.setItems(getOrderStatusDisplayNames());

        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);


        mealsLayout.setPadding(false);
        mealsLayout.setSpacing(true);
        mealsLayout.getStyle().set("border", "1px solid var(--lumo-contrast-10pct)")
            .set("padding", "10px")
            .set("border-radius", "8px");

        add(nameField, dateField, statusField, mealsLayout, new HorizontalLayout(saveButton, cancelButton));

        saveButton.addClickListener(event -> this.modifyOrderUC.updateOrder(order));
        cancelButton.addClickListener(event -> cancelAction());    }

    public void cancelAction() {
        getUI().ifPresent(ui -> ui.navigate("/client"));
    }

    private void updateMealsList() {
        mealsLayout.removeAll();

        meals.forEach(meal -> {
            HorizontalLayout mealLayout = new HorizontalLayout();
            Text mealName = new Text(meal);
            Button deleteButton = new Button("Usuń", event -> deleteMeal(meal));

            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

            mealLayout.add(mealName, deleteButton);
            mealLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

            mealsLayout.add(mealLayout);
        });
    }

    private void deleteMeal(String meal) {
        if (meals.size() <= 1) {
            Notification.show(
                "Nie można usunąć wszystkich posiłków",
                3000,
                Notification.Position.TOP_CENTER
            );
            return;
        }
        meals.remove(meal);
        updateMealsList();
    }

    private Collection<String> getOrderStatusDisplayNames() {
        return Arrays.stream(OrderStatus.values())
            .map(OrderStatus::getDisplayName)
            .toList();
    }

}
