package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {

    private Long productId;
    private String name;
    private double price;
    private int stock;
}
