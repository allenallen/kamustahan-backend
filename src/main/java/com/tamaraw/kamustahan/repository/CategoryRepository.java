package com.tamaraw.kamustahan.repository;

import com.tamaraw.kamustahan.model.Category;
import com.tamaraw.kamustahan.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCreatedBy(WebUser createdBy);

}
