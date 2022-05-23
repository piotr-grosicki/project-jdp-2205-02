package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class ProductDto {

    private Long productId;
    private String name;
    private BigDecimal price;
    private int stock;
    private List<Long> cardsId;
    private Long id;

}
