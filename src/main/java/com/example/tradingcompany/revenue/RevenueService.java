package com.example.tradingcompany.revenue;

import com.example.tradingcompany.inventory.InventoryService;
import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.order.OrderType;
import com.example.tradingcompany.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RevenueService {

  private final InventoryService inventoryService;

  public RevenueService(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  public List<RevenueItem> getAllRevenueItems() {
    List<RevenueItem> items = new ArrayList<>();
    List<Map<OrderType, List<OrderDetails>>> orders = inventoryService.getAllInventories().stream().map(inventory -> {
      Map<Product, List<OrderDetails>> ordersByProduct = inventory.getOrderDetails().stream()
          .collect(Collectors.groupingBy(OrderDetails::getProduct));

      Set<Map.Entry<Product, List<OrderDetails>>> entries = ordersByProduct.entrySet();
      Map<OrderType, List<OrderDetails>> ordersByType = entries.stream()
          .flatMap(entry -> entry.getValue().stream())
          .collect(Collectors.groupingBy(OrderDetails::getType));
      return ordersByType;
    }).collect(Collectors.toList());

    for (Map<OrderType, List<OrderDetails>> order : orders ) {
      RevenueItem item = new RevenueItem();
      Product product = null;
      int buyQuantity = 0;
      int saleQuantity = 0;
      Set<Map.Entry<OrderType, List<OrderDetails>>> entries = order.entrySet();
      for (Map.Entry<OrderType, List<OrderDetails>> entry : entries) {
        product = entry.getValue().get(0).getProduct();
        item.setProduct(product);
        for (OrderDetails o : entry.getValue()) {
          if (entry.getKey() == OrderType.BUY) {
            buyQuantity += o.getQuantity();
          } else {
            saleQuantity += o.getQuantity();
          }
        }
      }
      if (buyQuantity != 0 && saleQuantity != 0) {
        double balance = saleQuantity * product.getSellingPrice() - saleQuantity * product.getBuyingPrice();
        item.setBuyQuantity(buyQuantity);
        item.setSaleQuantity(saleQuantity);
        item.setBalance(balance);
        items.add(item);
      }
    }

    return items;
  }
}
