package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductGroupDto {

    private Long productGroupId;
    private String name;
//    private List<ProductDto> productList;
}