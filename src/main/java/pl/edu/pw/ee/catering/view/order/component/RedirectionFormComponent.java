package pl.edu.pw.ee.catering.view.order.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;

public class RedirectionFormComponent extends VerticalLayout {

    public RedirectionFormComponent(IPlaceOrderUC placeOrderUC, Long orderId) {
        add("Zapłać z PaySystem");

        Button payWithPaySystemButton = new Button("Zapłać z PaySystem",
            e -> placeOrderUC.payWithPaySystem(orderId));

        Button backToHomeButton = new Button("Back to Home",
            e -> getUI().ifPresent(ui -> ui.navigate("/client")));

        HorizontalLayout buttonLayout = new HorizontalLayout(payWithPaySystemButton,
            backToHomeButton);

        add(buttonLayout);
    }
}
