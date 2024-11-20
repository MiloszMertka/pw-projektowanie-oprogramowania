package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.view.order.ui.IClientOrderList;
import pl.edu.pw.ee.catering.view.order.ui.IClientOrderStatus;

import java.util.Collections;

@UIScope
@SpringComponent
@Route("client-orders-list")
@CssImport("./styles/styles.css")
@CssImport("./styles/order-styles.css")
public class ClientOrderListComponent extends VerticalLayout implements IClientOrderList {
    private final IClientOrderStatus status;
    private Grid<AppOrder> orderGrid;
    private final ICateringCompanyRouter router;

    public ClientOrderListComponent(ICateringCompanyRouter router, IClientOrderStatus status) {
        this.router = router;
        this.status = status;
        initLayout();
    }

    private void initLayout() {
        H1 header = new H1("Lista zamówień klienta:");

        orderGrid = new Grid<>(AppOrder.class, false);
        orderGrid.addClassName("custom-grid");

        orderGrid.addColumn(AppOrder::getName).setHeader("Nazwa zamówienia")
                .addClassName("primary-color");
        orderGrid.addColumn(AppOrder::getDate).setHeader("Data")
                .addClassName("primary-color");
        orderGrid.addColumn((orderWithDetails) -> orderWithDetails.getStatus().getDisplayName()).setHeader("Status")
                .addClassName("primary-color");

        orderGrid.addColumn(new ComponentRenderer<>(order -> {
            VerticalLayout optionsLayout = new VerticalLayout();
            optionsLayout.addClassName("primary-color");
            optionsLayout.getStyle().set("padding", "0");

            Button showOrderStatus = new Button("Zobacz status zamówienia", event -> {
                OrderStatus orderStatus = order.getStatus();
                if(orderStatus == null) {
                    this.status.showErrorMessage("Brak informacji o statusie zamówienia");
                } else {
                    this.status.showOrderStatusWindow(orderStatus);
                }
            });

            optionsLayout.add(showOrderStatus);
            return optionsLayout;
        })).setHeader("Opcje");

        orderGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        Button closeButton = new Button("Zamknij", event -> UI.getCurrent().navigate("client"));
        closeButton.addClassName("secondary-button");

        add(header, orderGrid, closeButton);
    }

    @Override
    public void showClientOrderList(OrderList orderList) {
        if (orderList != null && orderList.getOrders() != null) {
            orderGrid.setItems(orderList.getOrders());
        } else {
            orderGrid.setItems(Collections.emptyList());
        }
    }
}
