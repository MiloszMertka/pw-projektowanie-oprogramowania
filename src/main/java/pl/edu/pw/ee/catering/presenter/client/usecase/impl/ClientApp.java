package pl.edu.pw.ee.catering.presenter.client.usecase.impl;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.service.IOrder;
import pl.edu.pw.ee.catering.presenter.client.usecase.IClientRouter;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientComplaintUI;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientHistoricalOrderListComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientOrderUI;

@Component
@RequiredArgsConstructor
public class ClientApp implements IClientRouter, IPlaceOrderUC {

    private final IOrder order;
    //    private final ISavingsAccount savingsAccount;
    private final ObjectProvider<ClientOrderUI> clientOrderUIObjectProvider;
    private final ObjectProvider<ClientComplaintUI> clientComplaintUIObjectProvider;

    @Override
    public void navigateToPlaceOrderForm() {
        ClientOrderUI clientOrderUI = getClientOrderUI();
        clientOrderUI.showOrderForm();
    }

    @Override
    public void navigateToPlaceComplaintForm(Long Id) {
        ClientComplaintUI clientComplaintUI = getClientComplaintUI();
        clientComplaintUI.showPlaceComplaintForm(Id);
    }


    @Override
    public void placeOrder() {
        long orderId = 1L; // MOCK

        //int orderPrice = order.getOrderPrice();
        int orderPrice = 100; // MOCK

        //boolean isAmountEnough = savingsAccount.checkIsAmountEnough(orderPrice);
        boolean isAmountEnough = false; // MOCK

        ClientOrderUI clientOrderUI = getClientOrderUI();
        if (isAmountEnough) {
            //savingsAccount.updateSavingsAccount(orderPrice)
            clientOrderUI.showSuccessForm();
        } else {
            clientOrderUI.showRedirectionForm(orderId);
        }
    }

    @Override
    public void payWithPaySystem(Long orderId) {
        boolean result = true; // mock request to PaySystem

        if (result) {
            order.changeOrderStatus(orderId, OrderStatus.PAID);
            ClientOrderUI clientOrderUI = getClientOrderUI();
            clientOrderUI.showSuccessForm();
        }
    }

    private ClientOrderUI getClientOrderUI() {
        UI.getCurrent().navigate(ClientOrderUI.class);
        ClientOrderUI clientOrderUI = clientOrderUIObjectProvider.getIfAvailable();
        if (clientOrderUI == null) {
            throw new IllegalStateException("ClientOrderUI not found");
        }

        return clientOrderUI;
    }

    private ClientComplaintUI getClientComplaintUI() {
        UI.getCurrent().navigate(ClientComplaintUI.class);
        ClientComplaintUI clientComplaintUI = clientComplaintUIObjectProvider.getIfAvailable(); //clientOrderUIObjectProvider.getIfAvailable();
        if (clientComplaintUI == null) {
            throw new IllegalStateException("ClientOrderUI not found");
        }

        return clientComplaintUI;
    }
}
