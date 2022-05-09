package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
    @AllArgsConstructor
    @Getter

    public class Product {


        private int productId;


        private String name;


        private BigDecimal price;


        private int stock;
    }


