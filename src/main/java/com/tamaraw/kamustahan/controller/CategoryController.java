package com.tamaraw.kamustahan.controller;

import com.tamaraw.kamustahan.model.Category;
import com.tamaraw.kamustahan.repository.CategoryRepository;
import com.tamaraw.kamustahan.utils.TrackExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private static final String API_URL = "api/v1/category";

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = {"/", ""})
    @TrackExecutionTime
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    @TrackExecutionTime
    public Category getOne(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    @TrackExecutionTime
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) throws URISyntaxException {
        Category created = categoryRepository.save(category);
        return ResponseEntity.created(new URI(API_URL + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @TrackExecutionTime
    public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
        Category updated = categoryRepository.save(category);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @TrackExecutionTime
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
