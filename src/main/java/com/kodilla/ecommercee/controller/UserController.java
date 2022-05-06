package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "v1/users")
public class UserController {

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "JohnSmith", "123456", "smith@domain.com",
                "Street 1", true, "123456",
                LocalDateTime.of(2022, 5, 5, 10, 00, 00), 1L);
    }

    @PutMapping(value = "{userId}/block")
    public boolean blockUser(@PathVariable Long userId) {
        return true;
    }

    @PutMapping(value = "{userId}/generateToken")
    public String generateToken(@PathVariable Long userId) {
        return "token123";
    }
}