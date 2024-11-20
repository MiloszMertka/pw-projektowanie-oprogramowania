package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.presenter.client.usecase.IClientRouter;
import pl.edu.pw.ee.catering.view.order.ui.IClientHistoricalOrderList;

import java.util.Collections;

@UIScope
@SpringComponent
@Route("client-historical-orders-list")
@CssImport("./styles/styles.css")
public class ClientHistoricalOrderListComponent extends VerticalLayout implements IClientHistoricalOrderList {

    private Grid<AppOrder> orderGrid;
    final private IClientRouter clientRouter;
    public ClientHistoricalOrderListComponent(IClientRouter clientRouter) {
        this.clientRouter = clientRouter;
        initLayout();
    }

    private void initLayout() {
        H1 header = new H1("Lista historycznych zamówień klienta:");

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

            Anchor viewDetailsLink = new Anchor("#", "Dodaj opinię");
            Paragraph changeStatusLink = new Paragraph ( "Złóż reklamację");
            Button viewDetailsButton = new Button("Wyświetl szczegóły zamówienia", event -> {
                clientRouter.navigateToOrderDetails(order.getId());
            });

            changeStatusLink.getStyle()
                    .set("text-decoration", "underline")
                    .set("cursor", "pointer");
            changeStatusLink.addClickListener(e-> clientRouter.navigateToPlaceComplaintForm(order.getId()));

            optionsLayout.add(viewDetailsLink, changeStatusLink, viewDetailsButton);
            return optionsLayout;
        })).setHeader("Opcje");

        orderGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        Button closeButton = new Button("Zamknij", event -> UI.getCurrent().navigate(""));
        closeButton.addClassName("secondary-button");

        add(header, orderGrid, closeButton);
    }

    @Override
    public void showClientHistoricalOrderList(OrderList orderlist) {
        if (orderlist != null && orderlist.getOrders() != null) {
            orderGrid.setItems(orderlist.getOrders());
        } else {
            orderGrid.setItems(Collections.emptyList());
        }
    }
}
