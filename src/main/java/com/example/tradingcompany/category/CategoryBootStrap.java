package com.example.tradingcompany.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(3)
public class CategoryBootStrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;

  public CategoryBootStrap(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Category> categories = Stream.iterate(1L, i -> i + 1)
        .map(i -> new Category(String.format("Category %d", i)))
        .limit(15)
        .collect(Collectors.toList());

    categoryRepository.saveAll(categories);
  }
}
