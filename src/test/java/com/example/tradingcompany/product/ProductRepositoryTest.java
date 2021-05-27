package com.example.tradingcompany.product;

import com.example.tradingcompany.category.Category;
import com.example.tradingcompany.category.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAllTest() {
        Assertions.assertEquals(productRepository.findAll().size(), 50);
    }

    @Test
    public void findByIdTest() {
        Product productById = productRepository.findAll().stream()
                .filter(product -> product.getId() == 1)
                .findFirst()
                .orElse(null);
        Product product = productRepository.findById(1L).orElse(null);
        Assertions.assertEquals(productById, product);
    }

    @Test
    @Rollback(false)
    public void saveTest() {
        Product product = new Product("Product", "Model", "Brand", null, "Desc", null, 0.0, 0.0);
        Product savedProduct = productRepository.save(product);
        Assertions.assertEquals(product, savedProduct);
    }
}
