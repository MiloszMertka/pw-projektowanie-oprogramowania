package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.view.order.ui.IOrderDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UIScope
@SpringComponent
@Route("order-details")
public class OrderDetailsComponent extends VerticalLayout implements IOrderDetails, HasUrlParameter<Long> {
    private Long orderId;

    public OrderDetailsComponent() {
        setSpacing(true);
        setPadding(true);
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.orderId = parameter;
    }

    @Override
    public void showOrderDetails(OrderWithDetails orderWithDetails) {
        removeAll();

        if (orderWithDetails != null) {
            final var verticalLayout = new VerticalLayout();
            final var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            final var formattedDate = formatter.format(
                LocalDateTime.parse(orderWithDetails.getDate())
            );

            verticalLayout.add(new Div(new Text("ID zamówienia: " + orderWithDetails.getId())));
            verticalLayout.add(new Div(new Text("Nazwa zamówienia: " + orderWithDetails.getName())));
            verticalLayout.add(new Div(new Text("Data zamówienia: " + formattedDate)));
            verticalLayout.add(new Div(new Text("Status zamówienia: " + orderWithDetails.getStatus().getDisplayName())));

            add(verticalLayout);
        }  else {
            add(new Text("Nie znaleziono zamówienia"));
        }


        Button backButton = new Button("Powróć do zamówień", e -> getUI().ifPresent(ui -> ui.navigate("orders")));
        add(backButton);
    }

}
