package pl.edu.pw.ee.catering.presenter.order.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.service.IOrder;
import pl.edu.pw.ee.catering.presenter.order.usecase.IChangeOrderStatusUc;
import pl.edu.pw.ee.catering.view.order.ui.IChangeOrderStatus;

@Service
@RequiredArgsConstructor
public class ChangeOrderStatusUCImpl implements IChangeOrderStatusUc {
    private final IOrder order;
    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        order.changeOrderStatus(id, status);
    }
}
