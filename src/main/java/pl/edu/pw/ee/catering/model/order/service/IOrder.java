package pl.edu.pw.ee.catering.model.order.service;

import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;

public interface IOrder {
    
    OrderWithDetails getOrderWithDetails(Long id);

    void changeOrderStatus(Long id, OrderStatus status);
    
    /// Since there will be only 1 company (due to no authentication) it should be set to always one number
    OrderList getOrderList(Long id);
}