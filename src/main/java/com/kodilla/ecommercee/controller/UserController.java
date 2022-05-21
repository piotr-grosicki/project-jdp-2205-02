package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/users")
@RequiredArgsConstructor
public class UserController {

    private UserDbService userDbService;
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> usersList = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(usersList);
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userMapper.mapToUserDto(userDbService.getUser(userId)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new UserDto(0L, "There is no user with id: " + userId,
                    "", "", "", 0L));
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}/block")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        try{
            return ResponseEntity.ok(userDbService.blockUser(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no user with id: " + userId);
        }
    }

    @PutMapping(value = "{userId}/{userEmail}/generateToken")
    public ResponseEntity<String> generateToken(@PathVariable Long userId, @PathVariable String userEmail) {
        try {
            return ResponseEntity.ok(userDbService.generateToken(userId, userEmail));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no user with id: " + userId);
        }
    }

    @PutMapping(value = "{userId}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userDbService.unblockUser(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no user with id: " + userId);
        }
    }

    @GetMapping(value = "{userId}/{userEmail}/getToken")
    public ResponseEntity<String> getToken(@PathVariable Long userId, @PathVariable String userEmail) {
        try {
            return ResponseEntity.ok(userDbService.getToken(userId, userEmail));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no user with id: " + userId);
        }
    }
}
