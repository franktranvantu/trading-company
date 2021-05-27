package com.example.tradingcompany.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void getAllTest() {
        List<Category> categories = Stream.iterate(1L, i -> i + 1)
                .map(i -> new Category(String.format("Category %d", i)))
                .limit(15)
                .collect(Collectors.toList());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Assertions.assertEquals(categoryService.getAllCategories().size(), 15);
    }
}
