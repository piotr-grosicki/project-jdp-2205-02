package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {

    private Long cartId;
    private Long userId;
    private List<ProductDto> products;
    private BigDecimal totalValue;
    private Long orderId;
}
