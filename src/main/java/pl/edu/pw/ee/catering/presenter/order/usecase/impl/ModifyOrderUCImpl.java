package pl.edu.pw.ee.catering.presenter.order.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.service.IOrder;
import pl.edu.pw.ee.catering.presenter.order.usecase.IModifyOrderUC;

@Service
@RequiredArgsConstructor
public class ModifyOrderUCImpl implements IModifyOrderUC {
    private final IOrder order;

    @Override
    public void updateOrder(OrderWithDetails order) {
        this.order.updateOrder(order);
    }
}
