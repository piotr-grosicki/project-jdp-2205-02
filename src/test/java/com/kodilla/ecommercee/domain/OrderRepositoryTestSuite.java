package com.kodilla.ecommercee.domain;



import com.kodilla.ecommercee.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;


import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;


@SpringBootTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;





    @Test
    void saveOrderTest(){

        //Given
        Cart testCart = new Cart();

        User user = new User();
        user.setUserId(2L);
        user.setEmail("dasdasds");
        user.setUserName("adam");
        user.setActive(true);

        testCart.setUser(user);
        testCart.setTotalValue(4);
        Order testOrder = new Order(3, testCart, OrderStatus.NOWY, LocalDateTime.of(2022, 1, 1, 12, 00, 00));
        //When
        orderRepository.save(testOrder);
        //Then
        assertEquals(1,orderRepository.findAll().size());
        //CleanUp
        orderRepository.deleteById(testOrder.getOrderId());

    }

    @Test
    void findByIdTest() {
        //Given
        Cart testCart = new Cart();

        User user = new User();
        user.setUserId(2L);
        user.setEmail("dasdasds");
        user.setUserName("adam");
        user.setActive(true);

        testCart.setUser(user);
        testCart.setTotalValue(4);
        Order testOrder = new Order(3, testCart, OrderStatus.NOWY, LocalDateTime.of(2022, 1, 1, 12, 00, 00));
        orderRepository.save(testOrder);
        //When
        Optional<Order> order1 = orderRepository.findById(testOrder.getOrderId());
        //Then
        assertNotNull(order1);
        //CleanUp
        orderRepository.deleteById(testOrder.getOrderId());
    }

    @Test
    void updateUserTest() {
        //Given
        Cart testCart = new Cart();

        User user = new User();
        user.setUserId(2L);
        user.setEmail("dasdasds");
        user.setUserName("adam");
        user.setActive(true);

        testCart.setUser(user);
        testCart.setTotalValue(4);
        Order testOrder = new Order(3, testCart, OrderStatus.NOWY, LocalDateTime.of(2022, 1, 1, 12, 00, 00));
        orderRepository.save(testOrder);
        //When
        Optional<Order> orderToUpdate = orderRepository.findById(testOrder.getOrderId());
        orderToUpdate.get().setOrderStatus(OrderStatus.ANULOWANY);
        orderRepository.save(orderToUpdate.get());
        Optional<Order> resultOrder = orderRepository.findById(testOrder.getOrderId());
        //Then
        assertEquals(OrderStatus.ANULOWANY, resultOrder.get().getOrderStatus());
        //CleanUp
        orderRepository.deleteById(testOrder.getOrderId());
    }

    @Test
    void deleteUserTest() {
        //Given
        Cart testCart = new Cart();
        User user = new User();
        user.setUserId(2L);
        user.setEmail("dasdasds");
        user.setUserName("adam");
        user.setActive(true);
        testCart.setUser(user);
        testCart.setTotalValue(4);
        Order testOrder = new Order(3, testCart, OrderStatus.NOWY, LocalDateTime.of(2022, 1, 1, 12, 00, 00));
        Order testOrder2 = new Order(2, testCart, OrderStatus.NOWY, LocalDateTime.of(2022, 1, 1, 12, 00, 00));
        orderRepository.save(testOrder);
        orderRepository.save(testOrder2);
        List<Order> beforeDel = orderRepository.findAll();


        //When
        orderRepository.deleteById(testOrder.getOrderId());
        List<Order> afterDel = orderRepository.findAll();
        //Then
        assertEquals(2, beforeDel.size());
        assertEquals(1, afterDel.size());
        //CleanUp
        orderRepository.deleteById(testOrder2.getOrderId());
    }

}
