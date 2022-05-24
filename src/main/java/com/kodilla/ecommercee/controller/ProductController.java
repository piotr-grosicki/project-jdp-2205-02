package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

private final ProductMapper productMapper;
private final ProductDbService productDbService;

    @Autowired

    public ProductController(ProductMapper productMapper, ProductDbService productDbService) {
        this.productMapper = productMapper;
        this.productDbService = productDbService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product>products = productDbService.getProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(productDbService.getProduct(productId)));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long productId) {
        productDbService.deleteProduct(productId);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    public ResponseEntity<ProductDto>updateProduct(@RequestBody ProductDto productDto) throws ProductGroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product saveProduct = productDbService.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(saveProduct)) ;
    }

    @PostMapping
    public ResponseEntity<Void>createProduct(@RequestBody ProductDto productDto) throws ProductGroupNotFoundException{
        Product product = productMapper.mapToProduct(productDto);
        productDbService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

}
