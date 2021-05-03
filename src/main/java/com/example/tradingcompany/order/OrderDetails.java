package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime dateTime = LocalDateTime.now();
  @ManyToOne
  @JoinTable(
      name = "order_staff",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Staff staff;
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orderDetails")
  private Set<Product> products = new HashSet<>();
  @ManyToOne
  @JoinTable(
      name = "order_customer",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Customer customer;
  private Integer quantity;
  private OrderType type;

  public OrderDetails(LocalDateTime dateTime, Staff staff, Set<Product> products, Customer customer, Integer quantity, OrderType type) {
    this.dateTime = dateTime;
    this.staff = staff;
    this.products = products;
    this.customer = customer;
    this.quantity = quantity;
    this.type = type;
  }
}
