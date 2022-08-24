package com.tamaraw.kamustahan.repository;

import com.tamaraw.kamustahan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
