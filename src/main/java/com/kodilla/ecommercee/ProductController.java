package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = {"productId"})
    public ProductDto getProduct(@PathVariable long productId) {
        return new ProductDto(1,"Produkt1",12,4);
    }
    @DeleteMapping
    public void deleteProduct(Long productId) {
    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto){
        return new ProductDto(1,"Produkt1.1",14,2);
    }

    @PostMapping
    public void createProduct(ProductDto productDto){

    }

}


