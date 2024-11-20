package pl.edu.pw.ee.catering.presenter.order.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.service.IOrder;
import pl.edu.pw.ee.catering.presenter.order.usecase.IModifyOrderUC;
import pl.edu.pw.ee.catering.view.order.ui.IModifyOrderForm;

@Component
@RequiredArgsConstructor
public class ModifyOrderUCImpl implements IModifyOrderUC {
    private final IOrder orderUseCases;
    private final ObjectProvider<IModifyOrderForm> modifyOrderForm;

    @Override
    public void updateOrder(OrderWithDetails order) {
        this.orderUseCases.updateOrder(order);

        modifyOrderForm.ifAvailable(IModifyOrderForm::showSuccessModifyWindow);
    }
}
