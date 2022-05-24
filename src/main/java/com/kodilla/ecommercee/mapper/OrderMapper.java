package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private CartRepository cartRepository;

    public Order mapToOrder(final OrderDto orderDto) throws CartNotFoundException{
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new);
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCart(orderDto.getCart());
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
