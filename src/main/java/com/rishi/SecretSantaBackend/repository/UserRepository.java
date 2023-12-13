package com.rishi.SecretSantaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.SecretSantaBackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByAssignedChildIsNullAndAssignedFalse();
    // Add custom queries if needed

	User findByUsername(Integer username);
}
