package com.rishi.SecretSantaBackend.controller;

import com.rishi.SecretSantaBackend.model.User;
import com.rishi.SecretSantaBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userService.saveUser(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
