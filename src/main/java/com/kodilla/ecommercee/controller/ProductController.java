package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(@PathVariable long productId) {
        return new ProductDto(1L,"Produkt1",new BigDecimal(12),4);
    }

    @DeleteMapping(value = "{productId}")
    public void deleteProduct(@PathVariable Long productId) {
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return new ProductDto(1L,"Produkt1.1",new BigDecimal(14),2);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto){

    }

}


