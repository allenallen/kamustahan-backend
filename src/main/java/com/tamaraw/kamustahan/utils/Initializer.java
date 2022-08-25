//package com.tamaraw.kamustahan.utils;
//
//import com.tamaraw.kamustahan.model.Category;
//import com.tamaraw.kamustahan.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Stream;
//
//@Component
//public class Initializer implements CommandLineRunner {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Stream.of("General", "Government", "Anything")
//                .forEach(category -> {
//                    categoryRepository.save(new Category(category));
//                });
//    }
//}
