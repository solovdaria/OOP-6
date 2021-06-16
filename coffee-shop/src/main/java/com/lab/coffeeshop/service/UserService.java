package com.lab.coffeeshop.service;

import com.lab.coffeeshop.exception.ValidationException;
import com.lab.coffeeshop.model.User;
import com.lab.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Set<String> ADMIN_USERNAMES = Set.of("Admin", "admin");

    private final UserRepository userRepository;

    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User loginOrRegister(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                throw new ValidationException("User password is not valid");
            }
        } else {
            user = create(username, password);
        }
        return user;
    }

    public boolean isAdmin(String username) {
        return ADMIN_USERNAMES.contains(username);
    }
}
