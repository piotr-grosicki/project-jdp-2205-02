package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrder(long id) throws OrderNotFoundException{
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public void createOrder(final Order order){
        orderRepository.save(order);
    }

    public void updateOrder(final Order order) throws OrderNotFoundException{
        orderRepository.save(order);
    }

    public void deleteOrder(long id) throws OrderNotFoundException{
        orderRepository.deleteById(id);
    }

}
