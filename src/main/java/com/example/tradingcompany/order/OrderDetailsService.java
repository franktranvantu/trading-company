package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.dto.DateRange;
import com.example.tradingcompany.dto.DateTimeRange;
import com.example.tradingcompany.dto.SearchCriteria;
import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.provider.Provider;
import com.example.tradingcompany.staff.Staff;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

  private final OrderDetailsRepository orderDetailsRepository;

  public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
    this.orderDetailsRepository = orderDetailsRepository;
  }

  public List<OrderDetails> getAllOrderDetails() {
    return orderDetailsRepository.findAll();
  }

  public List<OrderDetails> getAllOrderDetails(Staff staff,
                                               Product product,
                                               Provider provider,
                                               Customer customer,
                                               Inventory inventory,
                                               DateTimeRange dateTimeRange) {
    RevenueOrderDetailsSpecification staffSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("staff", staff));
    RevenueOrderDetailsSpecification productSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("product", product));
    RevenueOrderDetailsSpecification providerSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("provider", provider));
    RevenueOrderDetailsSpecification customerSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("customer", customer));
    RevenueOrderDetailsSpecification inventorySpec = new RevenueOrderDetailsSpecification(new SearchCriteria("inventory", inventory));
    RevenueOrderDetailsSpecification dateRangeSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("dateTime", dateTimeRange));
    return orderDetailsRepository.findAll(Specification.where(staffSpec).and(productSpec).and(providerSpec).and(customerSpec).and(inventorySpec).and(dateRangeSpec));
  }

  public List<OrderDetails> getAllOrderDetails(Customer customer, Staff staff, DateTimeRange dateRange) {
    RevenueOrderDetailsSpecification customerSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("customer", customer));
    RevenueOrderDetailsSpecification staffSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("staff", staff));
    RevenueOrderDetailsSpecification dateRangeSpec = new RevenueOrderDetailsSpecification(new SearchCriteria("dateTime", dateRange));
    return orderDetailsRepository.findAll(Specification.where(customerSpec).and(staffSpec).and(dateRangeSpec));
  }

  public List<OrderDetails> getOrderDetailsByProductAndType(Product product, OrderType type) {
    return orderDetailsRepository.findOrderDetailsByProductAndType(product, type);
  }

  public OrderDetails getOrderDetailsById(long id) {
    return orderDetailsRepository.findById(id).orElse(null);
  }

  public OrderDetails createOrderDetails(OrderDetails orderDetails) {
    int quantity = 0;
    List<OrderDetails> orders = orderDetailsRepository.findOrderDetailsByProductAndInventory(orderDetails.getProduct(), orderDetails.getInventory());
    for (OrderDetails order : orders) {
      if (order.getType() == OrderType.BUY) {
        quantity += order.getQuantity();
      } else {
        quantity -= order.getQuantity();
      }
    }
    if (orderDetails.getType() == OrderType.SALE && orderDetails.getQuantity() > quantity) {
      throw new IllegalArgumentException(String.format("Quantity must be maximum %d", quantity));
    }

    return orderDetailsRepository.save(orderDetails);
  }

  public void deleteOrderDetails(long id) {
    Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
    if (!orderDetails.isPresent()) {
      throw new IllegalArgumentException(String.format("OrderDetails with id %s not exists", id));
    }
    try {
      orderDetailsRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("OrderDetails %d is being used by others!", orderDetails.get().getId()));
    }
  }
}
