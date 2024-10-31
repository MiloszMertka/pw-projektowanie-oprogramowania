package pl.edu.pw.ee.catering.view.order.ui;

import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface IChangeOrderStatus {
    void changeOrderStatus(OrderWithDetails orderWithDetails);
}
