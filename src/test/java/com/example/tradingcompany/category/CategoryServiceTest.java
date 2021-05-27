package com.example.tradingcompany.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
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
        Assertions.assertEquals(categoryService.getAllCategories(), categories);
    }

    @Test
    public void getByIdTest() {
        Category category = new Category(1L, "Category", null);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Assertions.assertEquals(categoryService.getCategoryById(1L), category);
    }

    @Test
    public void createTest() {
        Category category = new Category(1L, "Category", null);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Assertions.assertEquals(categoryService.createCategory(category), category);
    }

    @Test
    public void updateTest() {
        Category category = new Category(1L, "Category", null);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Assertions.assertEquals(categoryService.updateCategory(1L, category), category);
    }
}
