package pl.edu.pw.ee.catering.presenter.client.usecase.impl;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.cart.service.ICart;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.service.IMeal;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.service.IOrder;
import pl.edu.pw.ee.catering.model.savingsaccount.service.ISavingsAccount;
import pl.edu.pw.ee.catering.presenter.client.usecase.IAddMealToCartUC;
import pl.edu.pw.ee.catering.presenter.client.usecase.IClientRouter;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;
import pl.edu.pw.ee.catering.view.meal.ui.impl.ClientMealUI;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientComplaintUI;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientOrderListComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.ClientOrderUI;
import pl.edu.pw.ee.catering.view.order.ui.impl.ReviewFormUI;

@Component
@RequiredArgsConstructor
public class ClientApp implements IClientRouter, IPlaceOrderUC, IAddMealToCartUC {

    private final IOrder order;
    private final IMeal meal;
    private final ICart cart;
    private final ISavingsAccount savingsAccount;
    private final ObjectProvider<ClientOrderUI> clientOrderUIObjectProvider;
    private final ObjectProvider<ClientComplaintUI> clientComplaintUIObjectProvider;
    private final ObjectProvider<ReviewFormUI> reviewFormUIObjectProvider;
    private final ObjectProvider<ClientMealUI> clientMealUIObjectProvider;
    private final ObjectProvider<ClientOrderListComponent> clientOrderListComponents;

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
    public void navigateToReviewForm(Long Id) {
        ReviewFormUI reviewFormUI = getReviewFormUI();
        reviewFormUI.showReviewForm(Id);
    }

    @Override
    public void navigateToClientMealList() {
        ClientMealUI clientMealUI = getClientMealUI();
        MealList mealList = meal.getClientMealList();

        if (mealList == null || mealList.getMeals().isEmpty()) {
            clientMealUI.showMessage("Brak dostępnych dań do wyświetlenia!");
        } else {
            clientMealUI.showClientMealList(mealList);
        }
    }

    @Override
    public void navigateToClientOrderList() {
        UI.getCurrent().navigate(ClientOrderListComponent.class);

        ClientOrderListComponent clientOrderListComponent = clientOrderListComponents.getIfAvailable();
        if(clientOrderListComponent == null) {
            throw new IllegalStateException("clientOrderListComponentNotFound");
        }
        long clientId = 1L; // MOCK
        OrderList orderList = order.getClientOrderList(clientId);
        clientOrderListComponent.showClientOrderList(orderList);
    }

    @Override
    public void placeOrder() {
        long orderId = 1L; // MOCK

        int orderPrice = order.getOrderPrice(orderId);
        boolean isAmountEnough = savingsAccount.checkIsAmountEnough(orderId, orderPrice);

        ClientOrderUI clientOrderUI = getClientOrderUI();
        if (isAmountEnough) {
            savingsAccount.updateSavingsAccount(orderId, orderPrice);
            clientOrderUI.showSuccessForm();
        } else {
            clientOrderUI.showRedirectionForm(orderId);
        }
    }

    @Override
    public void payWithPaySystem(Long orderId) {
        boolean result = true; // MOCK

        if (result) {
            order.changeOrderStatus(orderId, OrderStatus.PAID);
            ClientOrderUI clientOrderUI = getClientOrderUI();
            clientOrderUI.showSuccessForm();
        }
    }

    @Override
    public void addMealToCart(Long mealId) {
        Meal mealToAdd = meal.getMeal(mealId);
        cart.addMealToCart(mealToAdd);

        ClientMealUI clientMealUI = getClientMealUI();
        clientMealUI.showUpdateCartMessage();
    }

    private ClientOrderUI getClientOrderUI() {
        UI.getCurrent().navigate(ClientOrderUI.class);
        ClientOrderUI clientOrderUI = clientOrderUIObjectProvider.getIfAvailable();
        if (clientOrderUI == null) {
            throw new IllegalStateException("ClientOrderUI not found");
        }

        return clientOrderUI;
    }

    private ReviewFormUI getReviewFormUI() {
        UI.getCurrent().navigate(ReviewFormUI.class);
        ReviewFormUI reviewFormUI = reviewFormUIObjectProvider.getIfAvailable();
        if (reviewFormUI == null) {
            throw new IllegalStateException("ReviewFormUI not found");
        }

        return reviewFormUI;
    }

    private ClientComplaintUI getClientComplaintUI() {
        UI.getCurrent().navigate(ClientComplaintUI.class);
        ClientComplaintUI clientComplaintUI = clientComplaintUIObjectProvider.getIfAvailable();
        if (clientComplaintUI == null) {
            throw new IllegalStateException("ClientComplaintUI not found");
        }

        return clientComplaintUI;
    }

    private ClientMealUI getClientMealUI() {
        UI.getCurrent().navigate(ClientMealUI.class);
        ClientMealUI clientMealUI = clientMealUIObjectProvider.getIfAvailable();
        if (clientMealUI == null) {
            throw new IllegalStateException("ClientMealUI not found");
        }

        return clientMealUI;
    }
}
