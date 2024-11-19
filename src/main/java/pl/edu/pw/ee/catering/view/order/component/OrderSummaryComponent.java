package pl.edu.pw.ee.catering.view.order.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;

@CssImport("./styles/order-form/summary-form.css")
public class OrderSummaryComponent extends VerticalLayout {

    private final IPlaceOrderUC placeOrderUC;

    public OrderSummaryComponent(IPlaceOrderUC placeOrderUC) {
        this.placeOrderUC = placeOrderUC;

        String firstName = (String) VaadinSession.getCurrent().getAttribute("firstName");
        String lastName = (String) VaadinSession.getCurrent().getAttribute("lastName");
        String phone = (String) VaadinSession.getCurrent().getAttribute("phone");
        String email = (String) VaadinSession.getCurrent().getAttribute("email");
        String address = (String) VaadinSession.getCurrent().getAttribute("address");
        String courierNotes = (String) VaadinSession.getCurrent().getAttribute("courierNotes");

        // order values mocks
        String orderPrice = "150.00 zł";
        String savings = "100.00 zł";
        String total = "50.00 zł";

        setAlignItems(Alignment.CENTER);
        setWidth("100%");

        H1 title = new H1("Podsumowanie");
        title.getStyle().set("color", "#9C27B0");

        VerticalLayout userInfoBox = new VerticalLayout(
            new NativeLabel(firstName + " " + lastName),
            new NativeLabel(phone),
            new NativeLabel(email),
            new NativeLabel(address),
            new NativeLabel(courierNotes)
        );
        userInfoBox.addClassName("info-box");

        VerticalLayout orderSummaryBox = new VerticalLayout(
            new HorizontalLayout(new NativeLabel("Zamówienie"), new NativeLabel(orderPrice)),
            new HorizontalLayout(new NativeLabel("Skarbonka"), new NativeLabel(savings)),
            new HorizontalLayout(new NativeLabel("Suma"), new NativeLabel(total))
        );
        orderSummaryBox.addClassName("info-box");

        Button payButton = new Button("Zapłać", buttonClickEvent -> proceedToPayment());
        payButton.addClassName("pay-button");

        Button backButton = new Button("Powrót");

        HorizontalLayout buttonLayout = new HorizontalLayout(payButton, backButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout contentLayout = new HorizontalLayout(userInfoBox, orderSummaryBox);
        contentLayout.setSpacing(true);
        contentLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(title, contentLayout, buttonLayout);
    }

    private void proceedToPayment() {
        placeOrderUC.placeOrder();
    }
}
