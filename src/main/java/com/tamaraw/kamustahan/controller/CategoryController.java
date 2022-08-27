package com.tamaraw.kamustahan.controller;

import com.tamaraw.kamustahan.model.Category;
import com.tamaraw.kamustahan.model.WebUser;
import com.tamaraw.kamustahan.repository.CategoryRepository;
import com.tamaraw.kamustahan.repository.WebUserRepository;
import com.tamaraw.kamustahan.utils.TrackExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private static final String API_URL = "api/v1/category";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WebUserRepository webUserRepository;

    @GetMapping(value = {"/", ""})
    @TrackExecutionTime
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/user")
    @TrackExecutionTime
    public List<Category> getByUser(Principal principal) {
        Optional<WebUser> webUser = webUserRepository.findById(principal.getName());
        if (webUser.isPresent()) {
            return categoryRepository.findAllByCreatedBy(webUser.get());
        }

        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @TrackExecutionTime
    public Category getOne(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    @TrackExecutionTime
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, @AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
        Map<String, Object> userDetails = principal.getAttributes();
        String userId = userDetails.get("sub").toString();
        Optional<WebUser> webUser = webUserRepository.findById(userId);

        category.setCreatedBy(webUser.orElse(
                new WebUser(userId, userDetails.get("name").toString(), userDetails.get("email").toString())
        ));

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
