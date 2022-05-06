package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Entity(name = "product")
    public class Product {

        @Id
        @GeneratedValue
        private int productId;

        @Column(name = "Name")
        private String name;

        @Column(name = "Price")
        private double price;

        @Column(name = "Stock")
        private int stock;
    }


