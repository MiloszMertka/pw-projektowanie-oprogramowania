package pl.edu.pw.ee.catering.model.order.service;

import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface IOrder {
    
    OrderWithDetails getOrderWithDetails(Long id);

    void changeOrderStatus(Long id, OrderStatus status);
    
}