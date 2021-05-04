package com.example.tradingcompany.inventory;

import com.example.tradingcompany.product.ProductService;
import com.google.common.collect.Sets;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(6)
public class InventoryBootStrap implements CommandLineRunner {

  private final InventoryRepository inventoryRepository;
  private final ProductService productService;

  public InventoryBootStrap(InventoryRepository inventoryRepository, ProductService productService) {
    this.inventoryRepository = inventoryRepository;
    this.productService = productService;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    List<Inventory> inventories = Stream.iterate(1L, i -> i + 1)
        .map(i -> {
            int productIdRandom = random.ints(1, 51).findFirst().getAsInt();
            return new Inventory(
                  String.format("Inventory %d", i),
                  String.format("Address %d", i),
                  Sets.newHashSet(
                          productService.getProductById(productIdRandom),
                          productService.getProductById(productIdRandom + 1),
                          productService.getProductById(productIdRandom + 2),
                          productService.getProductById(productIdRandom + 3),
                          productService.getProductById(productIdRandom + 4)
            ));
        })
        .limit(15)
        .collect(Collectors.toList());

    inventoryRepository.saveAll(inventories);
  }
}
