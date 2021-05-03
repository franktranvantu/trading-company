package com.example.tradingcompany.category;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category updateCategory(long id, Category category) {
    Category existCategory = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Category with id does not exist")));
    if (Objects.nonNull(category.getName()) && !Objects.equals(existCategory.getName(), category.getName())) {
      Optional<Category> categoryByName = categoryRepository.findCategoryByName(category.getName());
      if (categoryByName.isPresent()) {
        throw new IllegalArgumentException("Category already exists");
      }
      existCategory.setName(category.getName());
    }
    return categoryRepository.save(existCategory);
  }

  public void deleteCategory(long id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (!category.isPresent()) {
      throw new IllegalArgumentException(String.format("Category with id %s not exists", id));
    }
    try {
      categoryRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Category %s is being used by others!", category.get().getName()));
    }
  }
}
