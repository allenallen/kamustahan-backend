package com.tamaraw.kamustahan.repository;

import com.tamaraw.kamustahan.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
