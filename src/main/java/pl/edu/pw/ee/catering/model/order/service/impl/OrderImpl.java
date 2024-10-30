package pl.edu.pw.ee.catering.model.order.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;
import pl.edu.pw.ee.catering.model.order.service.IOrder;

@Service
@RequiredArgsConstructor
class OrderImpl implements IOrder {
    private final OrderRepository orderRepository;
    
    @Override
    public OrderWithDetails getOrder(Long id) {
        return getOrderById(id);
    }
    
    private OrderWithDetails getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        OrderWithDetails order = getOrderById(id);
        changeOrderStatus(order, status);
        orderRepository.save(order);
    }
    
    private void changeOrderStatus(OrderWithDetails order, OrderStatus status) {
        order.setStatus(status);
    }
}
