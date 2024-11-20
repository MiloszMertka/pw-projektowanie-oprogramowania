package pl.edu.pw.ee.catering.presenter.order.usecase;

import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface IModifyOrderUC {
    void updateOrder(OrderWithDetails order);
}
