package com.kodilla.ecommercee.domain;



import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;



import java.util.List;
import java.util.Optional;


@SpringBootTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;



    @Test
    void saveOrderTest(){

        //Given
        Cart cart = new Cart();
        Order order = new Order();
        User user = new User();

        user.setEmail("email@email.com");
        user.setUserName("user");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);
        cartRepository.save(cart);
        order.setCart(cart);
        order.setOrderStatus(OrderStatus.WYSLANY);

        orderRepository.save(order);
        //Then
        assertEquals(1,orderRepository.findAll().size());
        //CleanUp
        try {
            orderRepository.deleteById(order.getOrderId());
            cartRepository.deleteById(cart.getCartId());
            userRepository.deleteById(user.getUserId());
        }catch (Exception e){

        }

    }

    @Test
    void findByIdTest() {


        //Given
        Cart cart = new Cart();
        Order order = new Order();
        User user = new User();

        user.setEmail("email@email.com");
        user.setUserName("user");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);
        cartRepository.save(cart);
        order.setCart(cart);
        order.setOrderStatus(OrderStatus.WYSLANY);

        orderRepository.save(order);
        //When
        Optional<Order> order1 = orderRepository.findById(order.getOrderId());
        //Then
        assertNotNull(order1);
        //CleanUp
        orderRepository.deleteById(order.getOrderId());
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void updateOrderTest() {
        //Given
        Cart cart = new Cart();
        Order order = new Order();
        User user = new User();

        user.setEmail("email@email.com");
        user.setUserName("user");
        user.setActive(true);
        userRepository.save(user);
        cart.setUser(user);
        cart.setTotalValue(100L);
        cartRepository.save(cart);
        order.setCart(cart);
        order.setOrderStatus(OrderStatus.WYSLANY);
        orderRepository.save(order);
        //When
        Optional<Order> orderToUpdate = orderRepository.findById(order.getOrderId());
        orderToUpdate.get().setOrderStatus(OrderStatus.ANULOWANY);
        orderRepository.save(orderToUpdate.get());
        Optional<Order> resultOrder = orderRepository.findById(order.getOrderId());
        //Then
        assertEquals(OrderStatus.ANULOWANY, resultOrder.get().getOrderStatus());
        //CleanUp
        orderRepository.deleteById(order.getOrderId());
        cartRepository.deleteById(cart.getCartId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void deleteOrderTest() {
        //Given
        Cart cart1 = new Cart();
        Order order1 = new Order();
        User user1 = new User();

        user1.setEmail("email222@email.com");
        user1.setUserName("user1");
        user1.setActive(true);
        userRepository.save(user1);
        cart1.setUser(user1);
        cart1.setTotalValue(100L);
        cartRepository.save(cart1);
        order1.setCart(cart1);
        order1.setOrderStatus(OrderStatus.WYSLANY);

        Cart cart2 = new Cart();
        Order order2 = new Order();
        User user2 = new User();

        user2.setEmail("email111@email.com");
        user2.setUserName("user2");
        user2.setActive(true);
        userRepository.save(user2);
        cart2.setUser(user2);
        cart2.setTotalValue(100L);
        cartRepository.save(cart2);
        order2.setCart(cart2);
        order2.setOrderStatus(OrderStatus.WYSLANY);





        orderRepository.save(order1);
        orderRepository.save(order2);


        List<Order> beforeDel = orderRepository.findAll();


        orderRepository.deleteById(order1.getOrderId());
        //When

        List<Order> afterDel = orderRepository.findAll();
        //Then
        assertEquals(2, beforeDel.size());
        assertEquals(1, afterDel.size());
        //CleanUp
        orderRepository.deleteById(order2.getOrderId());
        cartRepository.deleteById(cart1.getCartId());
        cartRepository.deleteById(cart2.getCartId());
        userRepository.deleteById(user1.getUserId());
        userRepository.deleteById(user2.getUserId());
    }

}
