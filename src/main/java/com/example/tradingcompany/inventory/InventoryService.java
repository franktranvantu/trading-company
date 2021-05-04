package com.example.tradingcompany.inventory;

import com.example.tradingcompany.dto.SearchCriteria;
import com.example.tradingcompany.product.ProductService;
import com.google.common.collect.Sets;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {

  private final InventoryRepository inventoryRepository;
  private final ProductService productService;

  public InventoryService(InventoryRepository inventoryRepository, ProductService productService) {
    this.inventoryRepository = inventoryRepository;
    this.productService = productService;
  }

  public List<Inventory> getAllInventories() {
    return inventoryRepository.findAll();
  }

  public List<Inventory> getAllInventories(String name, String address, String product) {
    InventorySpecification nameSpec = new InventorySpecification(new SearchCriteria("name", ":", name));
    InventorySpecification addressSpec = new InventorySpecification(new SearchCriteria("address", ":", address));
    InventorySpecification productSpec = new InventorySpecification(new SearchCriteria("products", ":", product));
    return inventoryRepository.findAll(Specification.where(nameSpec).and(addressSpec).and(productSpec));
  }

  public Inventory getInventoryById(long id) {
    return inventoryRepository.findById(id).orElse(null);
  }

  public Inventory createInventory(Inventory inventory) {
    return inventoryRepository.save(inventory);
  }

  public Inventory updateInventory(long id, Inventory inventory) {
    Inventory existInventory = inventoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Inventory with id does not exist")));
    if (Objects.nonNull(inventory.getName()) && !Objects.equals(existInventory.getName(), inventory.getName())) {
      Optional<Inventory> inventoryByName = inventoryRepository.findInventoryByName(inventory.getName());
      if (inventoryByName.isPresent()) {
        throw new IllegalArgumentException("Inventory already exists");
      }
      existInventory.setName(inventory.getName());
    }
    return inventoryRepository.save(existInventory);
  }

  public void deleteInventory(long id) {
    Optional<Inventory> inventory = inventoryRepository.findById(id);
    if (!inventory.isPresent()) {
      throw new IllegalArgumentException(String.format("Inventory with id %s not exists", id));
    }
    try {
      inventoryRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Inventory %s is being used by others!", inventory.get().getName()));
    }
  }
}
