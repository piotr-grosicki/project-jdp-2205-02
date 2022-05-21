package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    Cart cart1 = new Cart();

    public User createUser() {
        User user = new User("TestUser1", "12345", "user1@mail.com",
                "user1 address", true, "token1",
                LocalDateTime.of(2022, 1, 1, 12, 00, 00),
                cart1.getCartId());
        return user;
    }

    @Test
    void saveUserTest() {
        //Given
        User user = createUser();
        //When
        userRepository.save(user);
        Optional<User> resultUser = userRepository.findById(user.getUserId());
        //Then
        assertTrue(resultUser.isPresent());
        //Cleanup
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void findByIdTest() {
        //Given
        User user = createUser();
        userRepository.save(user);
        //When
        Optional<User> resultUser = userRepository.findById(user.getUserId());
        //Then
        assertNotNull(resultUser);
        //CleanUp
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void blockUserTest() {
        //Given
        User user = createUser();
        user.setActive(false);
        //When
        userRepository.save(user);
        Optional<User> resultUser = userRepository.findById(user.getUserId());
        //Then
        assertFalse(resultUser.get().isActive());
        //CleanUp
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void findByIsActiveTest() {
        //Given
        User user1 = createUser();
        User user2 = createUser();
        user2.setEmail("user2@mail.com");
        User user3 = createUser();
        user3.setEmail("user3@mail.com");
        user3.setActive(false);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //When
        List<User> wholeUsersList = userRepository.findAll();
        List<User> activeUsersList = userRepository.findByActive(true);
        //Then
        assertEquals(3, wholeUsersList.size());
        assertEquals(2, activeUsersList.size());
        //CleanUp
        userRepository.deleteById(user1.getUserId());
        userRepository.deleteById(user2.getUserId());
        userRepository.deleteById(user3.getUserId());
    }

    @Test
    void updateUserTest() {
        //Given
        User user = createUser();
        userRepository.save(user);
        //When
        Optional<User> toUpdateUser = userRepository.findById(user.getUserId());
        toUpdateUser.get().setPhoneNumber("11111");
        toUpdateUser.get().setShippingAddress("New Address");
        userRepository.save(toUpdateUser.get());
        Optional<User> result = userRepository.findById(user.getUserId());
        //Then
        assertEquals("11111", result.get().getPhoneNumber());
        assertEquals("New Address", result.get().getShippingAddress());
        //CleanUp
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void deleteUserTest() {
        //Given
        User user = createUser();
        User user2 = createUser();
        user2.setEmail("user2@mail.com");
        userRepository.save(user);
        userRepository.save(user2);
        List<User> wholeUsersList = userRepository.findAll();
        //When
        userRepository.deleteById(user2.getUserId());
        List<User> listAfterDelete = userRepository.findAll();
        //Then
        assertEquals(2, wholeUsersList.size());
        assertEquals(1, listAfterDelete.size());
        //CleanUp
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void saveWithCartsTest() {
        //Given
        User user = createUser();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        user.getCarts().add(cart1);
        user.getCarts().add(cart2);
        user.getCarts().add(cart3);
        cart1.setUser(user);
        cart2.setUser(user);
        cart3.setUser(user);
        //When
        userRepository.save(user);
        List<Cart> cartsList = cartRepository.findAll();
        //Then
        assertNotEquals(Optional.empty(), Optional.ofNullable(user.getUserId()));
        assertEquals(3, cartsList.size());
        //CleanUp
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void deleteCartsTest() {
        //Given
        User user = createUser();
        user.getCarts().add(cart1);
        cart1.setUser(user);
        userRepository.save(user);
        Long cartId = cart1.getCartId();
        //When
        userRepository.deleteById(user.getUserId());
        //Then
        assertFalse(cartRepository.findById(cartId).isPresent());
    }
}
