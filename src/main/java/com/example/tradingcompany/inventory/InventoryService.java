package com.example.tradingcompany.inventory;

import com.example.tradingcompany.dto.SearchCriteria;
import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.order.OrderType;
import com.example.tradingcompany.product.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  public List<Inventory> getAllInventories() {
    return inventoryRepository.findAll();
  }

  public List<Inventory> getAllInventories(String name, String address, String product) {
    InventorySpecification nameSpec = new InventorySpecification(new SearchCriteria("name", name));
    InventorySpecification addressSpec = new InventorySpecification(new SearchCriteria("address", address));
    InventorySpecification productSpec = new InventorySpecification(new SearchCriteria("orderDetails", product));
    List<Inventory> inventories = inventoryRepository.findAll(Specification.where(nameSpec).and(addressSpec).and(productSpec));
    inventories.stream().forEach(inventory -> {
      Map<Product, List<OrderDetails>> orderPerProduct = inventory.getOrderDetails().stream()
          .collect(Collectors.groupingBy(OrderDetails::getProduct));
      Set<Map.Entry<Product, List<OrderDetails>>> entries = orderPerProduct.entrySet();
      Set<OrderDetails> orders = new HashSet<>();
      for (Map.Entry<Product, List<OrderDetails>> entry : entries) {
        List<OrderDetails> orderDetails = entry.getValue();
        int quantity = 0;
        for (OrderDetails order : orderDetails) {
          if (order.getType() == OrderType.BUY) {
            quantity += order.getQuantity();
          } else {
            quantity -= order.getQuantity();
          }
        }
        OrderDetails order = new OrderDetails();
        order.setProduct(entry.getKey());
        order.setQuantity(quantity);
        orders.add(order);
      }
      inventory.setOrderDetails(orders);
    });
    return inventories;
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
