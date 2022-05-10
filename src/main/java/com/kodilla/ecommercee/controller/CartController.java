package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/carts")
public class CartController {

    @PostMapping(value = "/userId/{userId}")
    public CartDto createEmptyCart(@PathVariable Long userId) {
        return new CartDto(1L, userId, new ArrayList<>(), new BigDecimal(0.00));
    }

    @GetMapping(value = "{cartId}")
    public List<CartDto> getCartItems(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PutMapping(value = "{cartId}/{productId}")
    public boolean addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return true;
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public boolean deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return true;
    }

    @PostMapping(value = "/cartId/{cartId}")
    public String createOrder(@PathVariable Long cartId) {
        return "Order has been created";
    }
}
