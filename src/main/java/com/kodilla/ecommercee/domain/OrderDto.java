package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderDto {
    private long order_id;
    private long cart_id;
    private String status;
    private LocalDate order_date;


}
