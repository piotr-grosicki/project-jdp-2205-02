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
@RequestMapping("/v1/orders")
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrder() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable long orderId) {
        return new OrderDto(1,1,"order status",new Date(2000,11, 11));
    }
    @DeleteMapping(value = "{orderId}")
    public String deleteOrder(@PathVariable long orderId) {
        return "order xyz was deleted";
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(1,1,"order status ",new Date(2000,11, 13));
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto){

        return new OrderDto(2, 2,"create order" , new Date( 2111,11,10));
    }



}
