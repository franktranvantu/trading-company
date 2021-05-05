package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.CustomerService;
import com.example.tradingcompany.inventory.InventoryService;
import com.example.tradingcompany.product.ProductService;
import com.example.tradingcompany.provider.ProviderService;
import com.example.tradingcompany.staff.StaffService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(7)
public class OrderDetailsBootStrap implements CommandLineRunner {

  private final OrderDetailsRepository orderDetailsRepository;
  private final StaffService staffService;
  private final ProviderService providerService;
  private final ProductService productService;
  private final InventoryService inventoryService;
  private final CustomerService customerService;

  public OrderDetailsBootStrap(OrderDetailsRepository orderDetailsRepository,
                               StaffService staffService,
                               ProviderService providerService,
                               ProductService productService,
                               InventoryService inventoryService,
                               CustomerService customerService) {
    this.orderDetailsRepository = orderDetailsRepository;
    this.staffService = staffService;
    this.providerService = providerService;
    this.productService = productService;
    this.inventoryService = inventoryService;
    this.customerService = customerService;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    List<OrderDetails> buyOrders = Stream.iterate(1L, i -> i + 1)
        .map(i -> {
          int randomId = random.ints(1, 16).findFirst().getAsInt();
          return new OrderDetails(
              LocalDateTime.now(),
              staffService.getStaffById(randomId),
              providerService.getProviderById(randomId),
              productService.getProductById(i),
              inventoryService.getInventoryById(i),
              100,
              OrderType.BUY
          );
        })
        .limit(inventoryService.getAllInventories().size())
        .collect(Collectors.toList());
        orderDetailsRepository.saveAll(buyOrders);

        List<OrderDetails> saleOrders = Stream.iterate(1L, i -> i + 1)
        .map(i -> {
          int randomId = random.ints(1, 16).findFirst().getAsInt();
          return new OrderDetails(
              LocalDateTime.now(),
              staffService.getStaffById(randomId),
              customerService.getCustomerById(randomId),
              productService.getProductById(i),
              inventoryService.getInventoryById(i),
              10,
              OrderType.SALE
          );
        })
        .limit(10)
        .collect(Collectors.toList());

    orderDetailsRepository.saveAll(saleOrders);
  }
}
