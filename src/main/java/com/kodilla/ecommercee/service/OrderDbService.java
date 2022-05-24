package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.exception.CartNotFoundException;
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

    public void createOrder(final Order order) throws CartNotFoundException {
        orderRepository.save(order);
    }

    public String updateOrder(final Order order) throws OrderNotFoundException{

        if (orderRepository.existsById(order.getOrderId())){
            orderRepository.save(order);
            return "Order has been updated";
        }else{
            throw new OrderNotFoundException();
        }

    }

    public String deleteOrder(long id) throws OrderNotFoundException{

        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return "Order has been deleted";
        }else{
            throw new OrderNotFoundException();
        }
    }

}
