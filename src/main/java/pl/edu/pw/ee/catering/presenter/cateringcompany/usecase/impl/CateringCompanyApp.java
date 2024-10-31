package pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.impl;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.view.meal.ui.impl.DeleteMealComponent;
import pl.edu.pw.ee.catering.view.order.ui.IOrderDetails;
import pl.edu.pw.ee.catering.view.meal.ui.impl.CateringCompanyMealListUI;
import pl.edu.pw.ee.catering.view.meal.ui.impl.CreateCompanyMealUI;
import pl.edu.pw.ee.catering.view.order.ui.impl.HistoricalOrderListComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.OrderDetailsComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.OrderListComponent;

@Component
@RequiredArgsConstructor
public class CateringCompanyApp implements ICateringCompanyRouter {

    private final ICateringCompany cateringCompany;
    private final ObjectProvider<HistoricalOrderListComponent> historicalOrderListProvider;
    private final ObjectProvider<IOrderDetails> orderDetailsProvider;
    private final ObjectProvider<CateringCompanyMealListUI> cateringCompanyMealUIProvider;
    private final ObjectProvider<OrderListComponent> orderListProvider;
    private final ObjectProvider<CreateCompanyMealUI> createMealFormComponents;
    private final ObjectProvider<DeleteMealComponent> deleteMealComponents;

    @Override
    public void navigateToHistoricalOrderList() {
        UI.getCurrent().navigate(HistoricalOrderListComponent.class);

        HistoricalOrderListComponent historicalOrderList = historicalOrderListProvider.getIfAvailable();
        if (historicalOrderList == null) {
            throw new IllegalStateException("HistoricalOrderListComponent not found");
        }

        OrderList orders = cateringCompany.showHistoricalOrderList(1L);
        historicalOrderList.showHistoricalOrderList(orders);
    }

    @Override
    public void navigateToOrderDetails(Long id) {
        UI.getCurrent().navigate(OrderDetailsComponent.class, id);
        final var orderDetails = orderDetailsProvider.getIfAvailable();
        if (orderDetails == null) {
            throw new IllegalStateException("OrderDetailsComponent not found");
        }

        final var orderWithDetails = cateringCompany.getOrderDetails(id);
        orderDetails.showOrderDetails(orderWithDetails);
    }

    @Override
    public void navigateToMealList() {
        UI.getCurrent().navigate(CateringCompanyMealListUI.class);

        CateringCompanyMealListUI cateringCompanyMealListUI = cateringCompanyMealUIProvider.getIfAvailable();
        if (cateringCompanyMealListUI == null) {
            throw new IllegalStateException("CateringCompanyMealUI not found");
        }

        MealList mealList = cateringCompany.showMealList(1L);
        cateringCompanyMealListUI.showMealList(mealList);
    }

    @Override
    public void navigateToDeleteMeal(Long id) {
        UI.getCurrent().navigate(DeleteMealComponent.class, id);
        DeleteMealComponent deleteMealComponent = deleteMealComponents.getIfAvailable();
        if(deleteMealComponent == null) {
            throw new IllegalStateException("deleteMealCompoennt not found");
        }

        deleteMealComponent.deleteMeal(id);
    }

    @Override
    public void navigateToOrderList() {
        UI.getCurrent().navigate(OrderListComponent.class);

        OrderListComponent orderList = orderListProvider.getIfAvailable();
        if (orderList == null) {
            throw new IllegalStateException("OrderListComponent not found");
        }

        OrderList orders = cateringCompany.showOrderList(1L);
        orderList.showOrderList(orders);
    }

    @Override
    public void navigateToCreateMealForm() {
        UI.getCurrent().navigate(CreateCompanyMealUI.class);

        CreateCompanyMealUI createMealForm = createMealFormComponents.getIfAvailable();
        if(createMealForm == null){
            throw new IllegalStateException("CreateMealFormComponent not found");
        }

        createMealForm.showCreateMealForm();
    }

}
