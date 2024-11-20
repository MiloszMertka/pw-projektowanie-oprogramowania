package pl.edu.pw.ee.catering.view.order.ui;

import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;

public interface IClientOrderStatus extends IClientOrderErrorMessage{
    void showOrderStatusWindow(OrderStatus status);
}
