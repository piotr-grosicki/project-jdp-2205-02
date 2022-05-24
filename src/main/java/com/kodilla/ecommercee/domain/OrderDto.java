package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {


    private Long orderId;
    private Long cartId;

    private OrderStatus status;
    private LocalDateTime orderDate;

}
