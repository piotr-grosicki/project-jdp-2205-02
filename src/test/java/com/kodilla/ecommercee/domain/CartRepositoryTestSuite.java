package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartRepositoryTestSuite {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void saveCartTest() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        user.setUserName("user");
        user.setEmail("email@email.com");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);

        //When
        cartRepository.save(cart);

        //Then
        assertTrue(cartRepository.findById(cart.getCartId()).isPresent());

        //Clean Up
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void updateCartTest() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        user.setUserName("user");
        user.setEmail("email@email.com");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);

        //When
        cart.setTotalValue(150L);
        cartRepository.save(cart);

        //Then
        assertEquals(150L, cartRepository.findById(cart.getCartId()).get().getTotalValue());

        //Clean Up
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void getByCartIdTest() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        user.setUserName("user");
        user.setEmail("email@email.com");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);

        //When
        cartRepository.save(cart);

        //Then
        assertEquals(cart.getCartId(), cartRepository.findById(cart.getCartId()).get().getCartId());

        //Clean Up
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }
}
