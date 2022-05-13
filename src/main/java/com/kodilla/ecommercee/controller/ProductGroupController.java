package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductGroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/groups")
public class ProductGroupController {

    @GetMapping()
    public List<ProductGroupDto> getGroupList() {
        return new ArrayList<>();
    }

    @PostMapping()
    public ProductGroupDto addGroup(@RequestBody ProductGroupDto productGroupDto) {
        return new ProductGroupDto(
                productGroupDto.getProductGroupId(),
                productGroupDto.getName(),
                productGroupDto.getProductList());
    }

    @GetMapping(value = "{groupId}")
    public ProductGroupDto getGroup(@PathVariable Long groupId) {
        return new ProductGroupDto(1L, "test group", new ArrayList<>());
    }

    @PutMapping
    public ProductGroupDto updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        return productGroupDto;
    }
}
