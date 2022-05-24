package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private OrderDbService orderDbService;
    private OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orderlist = orderDbService.getAllOrders();
        return ResponseEntity.ok().body(orderMapper.mapToOrderDtoList(orderlist));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        try{
            return ResponseEntity.ok(orderMapper.mapToOrderDto(orderDbService.getOrder(orderId)));
        }catch (OrderNotFoundException e){
            return ResponseEntity.badRequest().body(new OrderDto(0L, 0L, OrderStatus.ANULOWANY, LocalDateTime.of(0,0,0,0,0,0)));
        }
    }
    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try{
            orderDbService.deleteOrder(orderId);
            return ResponseEntity.ok().body("Order has been deleted");
        }catch (OrderNotFoundException e){
            return ResponseEntity.badRequest().body("There is no Order with such ID");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto orderDto){
        try{
            Order order = orderMapper.mapToOrder(orderDto);
            orderDbService.updateOrder(order);
            return ResponseEntity.ok().body( "Order has been updated");
        }catch (OrderNotFoundException | CartNotFoundException e){
            return ResponseEntity.badRequest().body("There is no order or cart with such ID");
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        try {
            Order order = orderMapper.mapToOrder(orderDto);
            orderDbService.createOrder(order);
            return ResponseEntity.ok().body("Order has been created");
        }catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no order or cart with such ID");
        }
    }

}
