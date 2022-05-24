package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    public void createCart(final Cart cart) throws UserNotFoundException {
        cartRepository.save(cart);
    }

    public void updateCart(final Cart cart) throws CartNotFoundException {
        cartRepository.save(cart);
    }

    public void deleteCart(long id) throws CartNotFoundException {
        cartRepository.deleteById(id);
    }

}
