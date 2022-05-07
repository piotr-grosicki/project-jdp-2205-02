package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class OrderDto {
    private int order_id;
    private int cart_id;
    private String status;
    private Date order_date;

}
