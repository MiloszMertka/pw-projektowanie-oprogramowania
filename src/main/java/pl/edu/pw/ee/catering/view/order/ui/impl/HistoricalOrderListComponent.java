package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.view.order.ui.IHistoricalOrderList;

import java.util.Collections;

@UIScope
@SpringComponent
@Route("historical-orders-list")
@CssImport("./styles/styles.css")
@CssImport("./styles/historical-order-styles.css")
public class HistoricalOrderListComponent extends VerticalLayout implements IHistoricalOrderList {
    private Grid<OrderWithDetails> orderGrid;

    public HistoricalOrderListComponent() {
        initLayout();
    }

    private void initLayout() {
        H1 header = new H1("Lista zamówień historycznych:");

        orderGrid = new Grid<>(OrderWithDetails.class, false);
        orderGrid.addClassName("custom-grid");

        orderGrid.addColumn(OrderWithDetails::getName).setHeader("Nazwa zamówienia")
                .addClassName("primary-color");
        orderGrid.addColumn(OrderWithDetails::getDate).setHeader("Data")
                .addClassName("primary-color");
        orderGrid.addColumn((orderWithDetails) -> orderWithDetails.getStatus().getDisplayName()).setHeader("Status")
                .addClassName("primary-color");

        orderGrid.addColumn(new ComponentRenderer<>(order -> {
            VerticalLayout optionsLayout = new VerticalLayout();
            optionsLayout.addClassName("primary-color");
            optionsLayout.getStyle().set("padding", "0");

            Anchor viewDetailsLink = new Anchor("#", "Wyświetl szczegóły zamówienia");
            Anchor changeStatusLink = new Anchor("#", "Zmień status zamówienia");

            optionsLayout.add(viewDetailsLink, changeStatusLink);
            return optionsLayout;
        })).setHeader("Opcje");

        orderGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        Button closeButton = new Button("Zamknij", event -> UI.getCurrent().navigate(""));
        closeButton.addClassName("secondary-button");

        add(header, orderGrid, closeButton);
    }

    @Override
    public void showHistoricalOrderList(OrderList orderList) {
        if (orderList != null && orderList.getOrders() != null) {
            orderGrid.setItems(orderList.getOrders());
        } else {
            orderGrid.setItems(Collections.emptyList());
        }
    }
}
