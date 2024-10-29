package pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.impl;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.view.order.ui.IOrderDetails;
import pl.edu.pw.ee.catering.view.order.ui.impl.HistoricalOrderListComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.OrderDetailsComponent;

@Component
@RequiredArgsConstructor
public class CateringCompanyApp implements ICateringCompanyRouter {

    private final ICateringCompany cateringCompany;
    private final ObjectProvider<HistoricalOrderListComponent> historicalOrderListProvider;
    private final ObjectProvider<IOrderDetails> orderDetailsProvider;

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
}
