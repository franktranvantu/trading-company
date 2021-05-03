package com.example.tradingcompany.product;

import com.example.tradingcompany.category.CategoryService;
import com.example.tradingcompany.provider.ProviderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(4)
public class ProductBootStrap implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final ProviderService providerService;
  private final CategoryService categoryService;

  public ProductBootStrap(ProductRepository productRepository, ProviderService providerService, CategoryService categoryService) {
    this.productRepository = productRepository;
    this.providerService = providerService;
    this.categoryService = categoryService;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    double revenue = 0.2;
    List<Product> products = Stream.iterate(1L, i -> i + 1)
        .map(i -> {
          int randomId = random.ints(1, 16).findFirst().getAsInt();
          double randomPrice = random.doubles(10.0, 100).findFirst().getAsDouble();
          return new Product(
              String.format("Product %d", i),
              String.format("Model %d", i),
              String.format("Brand %d", i),
              providerService.getProviderById(randomId),
              String.format("Description %d", i),
              categoryService.getCategoryById(randomId),
              randomPrice,
              randomPrice + (randomPrice * revenue)
          );
        })
        .limit(50)
        .collect(Collectors.toList());

    productRepository.saveAll(products);
  }
}
