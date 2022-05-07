package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrder() {
        return new ArrayList<>();
    }

    @GetMapping(value = {"orderId"})
    public OrderDto getOrder(@PathVariable long orderId) {
        return new OrderDto(1,1,"order status",new Date(2000,11, 11));
    }
    @DeleteMapping
    public void deleteOrder(Long productId) {
    }

    @PutMapping
    public OrderDto updateOrder(OrderDto orderDto){
        return new OrderDto(1,1,"order status",new Date(2000,11, 11));
    }

    @PostMapping
    public void createOrder(OrderDto orderDto){

    }



}
