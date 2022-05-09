package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {

    private int productId;
    private String name;
    private BigDecimal price;
    private int stock;
}
