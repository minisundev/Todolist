package com.minisun.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minisun.todolist.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
