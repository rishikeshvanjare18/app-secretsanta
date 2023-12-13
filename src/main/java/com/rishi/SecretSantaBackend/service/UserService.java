package com.rishi.SecretSantaBackend.service;

import com.rishi.SecretSantaBackend.model.User;
import com.rishi.SecretSantaBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public void registerUser(User user) {
        // Add validation and error handling as needed
        userRepository.save(user);
    }

    public String loginUser(User loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername());

        if (user != null && loginUser.getPassword().equals(user.getPassword())) {
            // Return a token or session information
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    public User assignChild(Long santaId) {
        User santa = userRepository.findById(santaId).orElse(null);

        if (santa != null && !santa.isAssigned()) {
            List<User> potentialChildren = userRepository.findAllByAssignedChildIsNullAndAssignedFalse();
            if (!potentialChildren.isEmpty()) {
                // Randomly select a child
                Random random = new Random();
                User child = potentialChildren.get(random.nextInt(potentialChildren.size()));

                // Assign the child to the Santa
                santa.setAssignedChild(child);
                santa.setAssigned(true);
                userRepository.save(santa);

                // Mark the child as assigned
                child.setSanta(santa);
                child.setAssigned(true);
                userRepository.save(child);

                return child;
            }
        }

        return null; // No child assigned
    }
}
