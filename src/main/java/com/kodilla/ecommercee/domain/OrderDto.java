package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {

    private long order_id;
    private Cart cart;
    private OrderStatus status;
    private LocalDateTime order_date;

}
