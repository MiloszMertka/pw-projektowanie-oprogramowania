package pl.edu.pw.ee.catering.model.order.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;
import pl.edu.pw.ee.catering.model.order.service.IOrder;

@Service
@RequiredArgsConstructor
class OrderImpl implements IOrder {
    private final OrderRepository orderRepository;
    
    @Override
    public OrderWithDetails getOrderWithDetails(Long id) {
        return getOrderWithDetailsById(id);
    }
    
    private OrderWithDetails getOrderWithDetailsById(Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        return new OrderWithDetails(order.getId(), order.getName(), order.getDate(), order.getStatus());
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        var order = orderRepository.findById(id).orElseThrow();
        changeOrderStatus(order, status);
        orderRepository.save(order);
    }
    
    private void changeOrderStatus(AppOrder order, OrderStatus status) {
        order.setStatus(status);
    }
    
    @Override
    public OrderList getOrderList(Long id) {
        return new OrderList(orderRepository.findAllByCompanyId(id).stream().filter(x -> !x.getStatus().equals(OrderStatus.FINISHED) && !x.getStatus().equals(OrderStatus.CANCELED)).toList());
    }

    @Override
    public OrderList getHistoricalOrderList(Long id) {
        return new OrderList(orderRepository.findAllByCompanyId(id).stream().filter(x -> x.getStatus().equals(OrderStatus.FINISHED) || x.getStatus().equals(OrderStatus.CANCELED)).toList());
    }
}
