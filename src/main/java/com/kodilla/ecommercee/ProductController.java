package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
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
    @DeleteMapping(value = {"productId"})
    public void deleteProduct(@PathVariable Long productId) {
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return new ProductDto(1,"Produkt1.1",14,2);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(ProductDto productDto){

    }

}


