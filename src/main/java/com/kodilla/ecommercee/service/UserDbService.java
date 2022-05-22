package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public void createUser(final User user) {
        userRepository.save(user);
    }

    public String blockUser(final Long userId) throws UserNotFoundException {
        User currentUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        currentUser.setActive(false);
        return "The user with the given id: " + userId + " has been blocked.";
    }

    public String unblockUser(final Long userId) throws UserNotFoundException {
        User currentUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(!currentUser.isActive()) {
            currentUser.setActive(true);
            return "The user with the given id: " + userId + " has been activated.";
        } else {
            return "User with id: " + userId + " is already active.";
        }
    }

    public String generateToken(final Long userId, final String userEmail) throws UserNotFoundException {
        User currentUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (currentUser.getEmail().equals(userEmail)) {
            if (currentUser.isActive()) {
                String token = UUID.randomUUID().toString();
                currentUser.setSessionToken(token);
                currentUser.setSessionStartTime(LocalDateTime.now());
                userRepository.save(currentUser);
                return token;
            } else {
                return "The user is blocked";
            }
        } else {
            return "The user data provided is incorrect";
        }
    }

    public boolean checkTokenValidity(final Long userId) throws UserNotFoundException {
        User currentUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (currentUser.getSessionToken() != null) {
            LocalDateTime checkedTime = LocalDateTime.now().minusHours(1);
            LocalDateTime sessionStartTime = currentUser.getSessionStartTime();
            if (checkedTime.isBefore(sessionStartTime)) {
                return true;
            } else {
                currentUser.setSessionToken(null);
                currentUser.setSessionStartTime(null);
                userRepository.save(currentUser);
                return false;
            }
        } else {
            return false;
        }
    }
}