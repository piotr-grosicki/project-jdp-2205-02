package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/carts")
public class CartController {

    private CartDbService cartDbService;
    private ProductDbService productDbService;
    private CartMapper cartMapper;
    private ProductMapper productMapper;

    @PostMapping(value = "/userId/{userId}")
    public ResponseEntity<String> createEmptyCart(@RequestBody CartDto cartDto) {
        try {
            Cart cart = cartMapper.mapToCart(cartDto);
            cartDbService.createCart(cart);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> cartList = cartDbService.getAllCarts();
        return ResponseEntity.ok().body(cartMapper.mapToCartDtoList(cartList));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getCartItems(@PathVariable Long cartId) {
        try {
            return ResponseEntity.ok(productMapper.mapToProductDtoList(cartDbService.getCart(cartId).getItems()));
        } catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @PutMapping(value = "{cartId}/{productId}")
    public ResponseEntity<String> addProductToCart(@RequestBody Long cartId, @RequestBody ProductDto productDto) {
        try {
            cartDbService.getCart(cartId).getItems().add(productMapper.mapToProduct(productDto));
            return ResponseEntity.ok().body("Item has been added to your cart");
        } catch (ProductGroupNotFoundException | CartNotFoundException e) {
            return ResponseEntity.ok().body("No such product exists");
        }
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        try {
            cartDbService.getCart(cartId).getItems().remove(productDbService.getProduct(productId));
            return ResponseEntity.ok().body("Item deleted from your cart");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.ok().body("Product not found");
        } catch (CartNotFoundException e) {
            return ResponseEntity.ok().body("Cart not found");
        }
    }

}
