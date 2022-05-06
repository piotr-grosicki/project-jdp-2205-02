package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {

    private int productId;
    private String name;
    private double price;
    private int stock;
}
gid