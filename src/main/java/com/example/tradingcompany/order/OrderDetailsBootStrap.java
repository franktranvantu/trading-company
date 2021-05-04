package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.CustomerService;
import com.example.tradingcompany.product.ProductService;
import com.example.tradingcompany.staff.StaffService;
import com.google.common.collect.Sets;
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
  private final ProductService productService;
  private final CustomerService customerService;

  public OrderDetailsBootStrap(OrderDetailsRepository orderDetailsRepository, StaffService staffService, ProductService productService, CustomerService customerService) {
    this.orderDetailsRepository = orderDetailsRepository;
    this.staffService = staffService;
    this.productService = productService;
    this.customerService = customerService;
  }

  @Override
  public void run(String... args) throws Exception {
    Random random = new Random();
    List<OrderDetails> orderDetails = Stream.iterate(1L, i -> i + 1)
        .map(i -> {
          int randomId = random.ints(1, 16).findFirst().getAsInt();
          return new OrderDetails(
              LocalDateTime.now(),
              staffService.getStaffById(randomId),
              Sets.newHashSet(productService.getProductById(randomId), productService.getProductById(randomId+1), productService.getProductById(randomId+2)),
              customerService.getCustomerById(randomId),
              10,
              (randomId % 2 == 0) ? OrderType.BUY : OrderType.SALE
          );
        })
        .limit(50)
        .collect(Collectors.toList());

    orderDetailsRepository.saveAll(orderDetails);
  }
}
