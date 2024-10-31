package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.order.usecase.IChangeOrderStatusUc;
import pl.edu.pw.ee.catering.view.order.ui.IChangeOrderStatus;
@UIScope
@SpringComponent
@Route("change-order-status")
public class ChangeOrderStatusComponent extends VerticalLayout implements IChangeOrderStatus {
    private final IChangeOrderStatusUc changeOrderStatusUc;
    private final ICateringCompanyRouter router;
    public ChangeOrderStatusComponent(IChangeOrderStatusUc changeOrderStatusUc, ICateringCompanyRouter router) {
        this.changeOrderStatusUc = changeOrderStatusUc;
        this.router = router;
        setSpacing(true);
        setPadding(true);
    }
    @Override
    public void changeOrderStatus(OrderWithDetails orderWithDetails) {
        removeAll();
        Label orderNameLabel = new Label("Nazwa zamówienia: " + orderWithDetails.getName());
        Label statusLabel = new Label("Status zamówienia: " + orderWithDetails.getStatus().getDisplayName());

        ComboBox<OrderStatus> statusComboBox = new ComboBox<>("Zmień status");
        statusComboBox.setItems(OrderStatus.values());
        statusComboBox.setItemLabelGenerator(OrderStatus::getDisplayName);

        Button changeStatusButton = new Button("Zmień status", event -> {
            OrderStatus selectedStatus = statusComboBox.getValue();
            if (selectedStatus != null) {
                confirmChangeStatus(orderWithDetails, selectedStatus);
            }
        });

        add(orderNameLabel, statusLabel, statusComboBox,changeStatusButton);
    }

    private void confirmChangeStatus(OrderWithDetails orderWithDetails, OrderStatus selectedStatus) {
        changeOrderStatusUc.changeOrderStatus(orderWithDetails.getId(), selectedStatus);
        router.navigateToOrderList();
    }
}
