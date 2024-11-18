package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;
import pl.edu.pw.ee.catering.view.order.component.AddressFormComponent;
import pl.edu.pw.ee.catering.view.order.component.RedirectionFormComponent;
import pl.edu.pw.ee.catering.view.order.component.SuccessfulOrderComponent;
import pl.edu.pw.ee.catering.view.order.ui.IPlaceOrderForm;

@UIScope
@SpringComponent
@Route("/client-order")
@RequiredArgsConstructor
public class ClientOrderUI extends VerticalLayout implements IPlaceOrderForm {

    private final IPlaceOrderUC placeOrderUC;

    @Override
    public void showOrderForm() {
        removeAll();
        AddressFormComponent addressFormComponent = new AddressFormComponent(placeOrderUC);
        add(addressFormComponent);
    }

    @Override
    public void showSuccessForm() {
        removeAll();
        SuccessfulOrderComponent successfulOrderComponent = new SuccessfulOrderComponent();
        add(successfulOrderComponent);
    }

    @Override
    public void showRedirectionForm(Long orderId) {
        removeAll();
        RedirectionFormComponent redirectionFormComponent = new RedirectionFormComponent(
            placeOrderUC, orderId);
        add(redirectionFormComponent);
    }
}
