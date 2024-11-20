package pl.edu.pw.ee.catering.presenter.order.usecase;

import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;

public interface IChangeOrderStatusUc {
    void changeOrderStatus(Long id, OrderStatus status);
    void makeComplain(Long id);
}
