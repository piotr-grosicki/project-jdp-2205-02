package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.service.CartDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private CartDbService cartDbService;

    public Order mapToOrder(final OrderDto orderDto) throws CartNotFoundException {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCart(cartDbService.getCart(orderDto.getCartId()));
        order.setOrderStatus(orderDto.getStatus());
        order.setDate(orderDto.getOrderDate());
        return order;
    }

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(order.getOrderId(), order.getCart().getCartId(), order.getOrderStatus(), order.getDate());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList){

        List<OrderDto> list = orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
        return list;

    }


}
