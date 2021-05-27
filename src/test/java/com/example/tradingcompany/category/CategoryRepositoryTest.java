package com.example.tradingcompany.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findAllTest() {
        Assertions.assertEquals(categoryRepository.findAll().size(), 15);
    }

    @Test
    public void findByIdTest() {
        Category categoryById = categoryRepository.findAll().stream()
                .filter(category -> category.getId() == 1)
                .findFirst()
                .orElse(null);
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertEquals(categoryById, category);
    }

    @Test
    @Rollback(false)
    public void saveTest() {
        Category category = new Category("Category Test");
        Category savedCategory = categoryRepository.save(category);
        Assertions.assertEquals(category, savedCategory);
    }
}
