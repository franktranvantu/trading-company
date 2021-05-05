package com.example.tradingcompany.order;

import org.springframework.dao.DataIntegrityViolationException;
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

  public OrderDetails getOrderDetailsById(long id) {
    return orderDetailsRepository.findById(id).orElse(null);
  }

  public OrderDetails createOrderDetails(OrderDetails orderDetails) {
    // TODO: Validate
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
