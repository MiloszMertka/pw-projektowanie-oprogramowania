package pl.edu.pw.ee.catering.view.order.ui;

import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface IModifyOrderForm {
    void showOrderModifyForm(OrderWithDetails order);
    void showSuccessModifyWindow();
}
