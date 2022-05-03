package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/carts")
public class CartController {

    @PostMapping
    public CartDto createEmptyCart() {
        return new CartDto(1L, 1L, new ArrayList<>(), new BigDecimal(0.00));
    }

    @GetMapping(value = "{cartId}")
    public List<CartDto> getCartItems(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping(value = "{productID}")
    public boolean addProductToCart(@PathVariable Long productId) {
        return true;
    }

    @DeleteMapping(value = "{productId}")
    public boolean deleteProductFromCart(Long productId) {
        return true;
    }

    @PostMapping(value = "{cartId}")
    public String createOrder(Long cartId) {
        return "Order has been created";
    }
}
