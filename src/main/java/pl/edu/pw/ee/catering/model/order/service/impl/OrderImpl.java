package pl.edu.pw.ee.catering.model.order.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;
import pl.edu.pw.ee.catering.model.order.service.IOrder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class OrderImpl implements IOrder {
    private final OrderRepository orderRepository;

    @Override
    public void updateOrder(OrderWithDetails orderWithDetails) {
        var order = orderRepository.findById(orderWithDetails.getId()).orElseThrow();
        updateOrder(order, orderWithDetails);
        orderRepository.save(order);
    }

    private void updateOrder(AppOrder order, OrderWithDetails orderWithDetails) {
        order.setName(orderWithDetails.getName());
        order.setDate(orderWithDetails.getDate());
        order.setStatus(orderWithDetails.getStatus());
    }

    @Override
    public OrderWithDetails getOrderWithDetails(Long id) {
        var appOrder = orderRepository.findById(id).orElseThrow();
        return mapAppOrderToOrderWithDetails(appOrder);
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

    @Override
    public OrderList getClientHistoricalOrderList(Long clientId) {
        return new OrderList(orderRepository.findAllByClientId(clientId).stream().filter(x -> x.getStatus().equals(OrderStatus.FINISHED) || x.getStatus().equals(OrderStatus.CANCELED)).toList());
    }
  
    @Override
    public int getOrderPrice(Long id) {
        Optional<AppOrder> appOrder = orderRepository.findById(id);
        return appOrder.map(AppOrder::getPrice).orElse(0);
    }

    private OrderWithDetails mapAppOrderToOrderWithDetails(AppOrder order) {
        return OrderWithDetails.builder()
                .id(order.getId())
                .name(order.getName())
                .date(order.getDate())
                .status(order.getStatus())
                .build();
    }

    @Override
    public OrderStatus getOrderStatus(Long id){
        var order = orderRepository.findById(id).orElseThrow();
        var status = order.getStatus();
        return  status;
    }
}
