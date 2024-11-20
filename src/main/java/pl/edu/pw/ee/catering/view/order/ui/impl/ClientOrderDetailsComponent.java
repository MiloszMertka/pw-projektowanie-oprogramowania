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
import pl.edu.pw.ee.catering.view.order.ui.IClientOrderDetails;

@UIScope
@SpringComponent
@Route("client-order-details")
public class ClientOrderDetailsComponent extends VerticalLayout implements IClientOrderDetails, HasUrlParameter<Long> {
    private Long orderId;

    public ClientOrderDetailsComponent() {
        setSpacing(true);
        setPadding(true);
    }

    @Override
    public void showOrderDetailsFrom(OrderWithDetails order) {
        removeAll();

        if (order != null) {
            final var verticalLayout = new VerticalLayout();
            verticalLayout.add(new Div(new Text("ID zamówienia: " + order.getId())));
            verticalLayout.add(new Div(new Text("Nazwa zamówienia: " + order.getName())));
            verticalLayout.add(new Div(new Text("Data zamówienia: " + order.getDate())));
            verticalLayout.add(new Div(new Text("Status zamówienia: " + order.getStatus().getDisplayName())));

            add(verticalLayout);
        }  else {
            add(new Text("Nie znaleziono zamówienia"));
        }


        Button backButton = new Button(
            "Zamknij",
            e -> close()
        );
        add(backButton);
    }

    @Override
    public void close() {
        getUI().ifPresent(ui -> ui.navigate("/client"));
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.orderId = parameter;
    }
}
