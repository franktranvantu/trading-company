package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
  private Staff staff;
  @ManyToOne
  @JoinTable(
      name = "order_details_product",
      joinColumns = {@JoinColumn(name = "order_details_id")},
      inverseJoinColumns = @JoinColumn
  )
  private Product product;
  @ManyToOne
  @JoinTable(
      name = "order_details_inventory",
      joinColumns = {@JoinColumn(name = "order_details_id")},
      inverseJoinColumns = @JoinColumn
  )
  private Inventory inventory;
  @ManyToOne
  private Customer customer;
  private Integer quantity;
  private OrderType type;

  public OrderDetails(LocalDateTime dateTime,
                      Staff staff,
                      Product product,
                      Inventory inventory,
                      Customer customer,
                      Integer quantity,
                      OrderType type) {
    this.dateTime = dateTime;
    this.staff = staff;
    this.product = product;
    this.inventory = inventory;
    this.customer = customer;
    this.quantity = quantity;
    this.type = type;
  }
}
