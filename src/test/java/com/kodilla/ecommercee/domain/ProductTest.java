package com.kodilla.ecommercee.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductTest {

    @Autowired
    ProductRepository productRepository;

    Product product = new Product();

    @Before
    public void prepareTests(){
        product.setName("test");
        product.setPrice(new BigDecimal(50.50));
        product.setStock(10);
        product.setCarts(new ArrayList<>());
        product.setProductGroup(new ProductGroup());
    }

    @AfterEach
    void cleanUpTests(){
        productRepository.deleteById(product.getProductId());
    }

    @Test
    void saveProductTest(){
        //Given & When
        productRepository.save(product);
        //Then
        assertTrue(productRepository.findById(product.getProductId()).isPresent());
    }

    @Test
    void updateProductTest(){
        //Given
        productRepository.save(product);
        //When
        String updated = "Updated!";
        product.setName(updated);
        productRepository.save(product);
        //Then
        assertEquals(updated, productRepository.findById(product.getProductId()).get().getName());
    }

    @Test
    void getProductListTest(){
        //Given
        Product product2 = new Product(
                null,
                "test2",
                new BigDecimal(50.51),
                12,
                null,
                null
        );
        Product product3 = new Product(
                null,
                "test4",
                new BigDecimal(50.53),
                13,
                null,
                null
        );
        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        //When
        List<Product> productList = productRepository.findAll();
        //Then
        assertEquals(3, productList.size());
        //CleanUp
        productRepository.deleteById(product2.getProductId());
        productRepository.deleteById(product3.getProductId());
    }
}
