package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String userName;
    private String phoneNumber;
    private String email;
    private String shippingAddress;
    private boolean isActive;
    private String sessionToken;
    private LocalDateTime sessionStartTime;
    private Long activeCart;

}
