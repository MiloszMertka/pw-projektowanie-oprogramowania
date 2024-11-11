package pl.edu.pw.ee.catering.view.order.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SuccessfulOrderComponent extends VerticalLayout {

    public SuccessfulOrderComponent() {
        add("Udana płatność! Dziękujemy za zamówienie.");

        Button backToHomeButton = new Button("Powrót do strony głównej",
            e -> getUI().ifPresent(ui -> ui.navigate("/client")));

        add(backToHomeButton);
    }
}
