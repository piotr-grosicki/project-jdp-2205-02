package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    UserRepository userRepository;
    OrderRepository orderRepository;

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        User user = userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Optional<Order> order = orderRepository.findById(cartDto.getOrderId());
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setTotalValue(cartDto.getTotalValue());
        cart.setUser(user);
        cart.setItems(cartDto.getProducts());
        cart.setOrder(order.get());
        return cart;
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getCartId(), cart.getUser().getUserId(), cart.getItems(), cart.getTotalValue(), cart.getOrder().getOrderId());
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        List<CartDto> list = cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
        return list;
    }

}
