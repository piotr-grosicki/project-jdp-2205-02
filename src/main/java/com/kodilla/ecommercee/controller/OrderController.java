package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable long orderId) {
        return new OrderDto(1,1,"order status", LocalDate.of(2000, 11, 11));
    }
    @DeleteMapping(value = "{orderId}")
    public String deleteOrder(@PathVariable long orderId) {
        return "order xyz was deleted";
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(1,1,"order status ", LocalDate.of(2010, 10, 20));
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto){

        return new OrderDto(2, 2,"create order" , LocalDate.of(2111, 9, 11));
    }



}
