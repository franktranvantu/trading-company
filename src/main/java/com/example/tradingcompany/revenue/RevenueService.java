package com.example.tradingcompany.revenue;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.dto.DateRange;
import com.example.tradingcompany.dto.DateTimeRange;
import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.order.OrderDetailsService;
import com.example.tradingcompany.order.OrderType;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.staff.Staff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class RevenueService {

  private final OrderDetailsService orderDetailsService;

  public RevenueService(OrderDetailsService orderDetailsService) {
    this.orderDetailsService = orderDetailsService;
  }

  public List<RevenueItem> getAllRevenueItems(Customer customer, Staff staff, DateTimeRange dateRange) {
    List<RevenueItem> items = new ArrayList<>();
    List<Map.Entry<Product, List<OrderDetails>>> orders = orderDetailsService.getAllOrderDetails(customer, staff, dateRange).stream()
            .collect(Collectors.groupingBy(OrderDetails::getProduct))
            .entrySet().stream()
            .sorted(Comparator.comparing(o -> o.getKey().getId()))
            .collect(Collectors.toList());

    for (Map.Entry<Product, List<OrderDetails>> order : orders) {
      RevenueItem item = new RevenueItem();
      Product product = order.getKey();
      item.setProduct(product);
      int saleQuantity = order.getValue().stream()
              .filter(orderDetails -> orderDetails.getType() == OrderType.SALE)
              .mapToInt(OrderDetails::getQuantity)
              .reduce(0, Integer::sum);
      if (saleQuantity > 0) {
        double balance = saleQuantity * product.getSellingPrice() - saleQuantity * product.getBuyingPrice();
        int buyQuantity = orderDetailsService.getOrderDetailsByProductAndType(product, OrderType.BUY).stream()
                .mapToInt(OrderDetails::getQuantity)
                .reduce(0, Integer::sum);
        item.setBuyQuantity(buyQuantity);
        item.setSaleQuantity(saleQuantity);
        item.setBalance(balance);
        items.add(item);
      }
    }

    return items;
  }
}
