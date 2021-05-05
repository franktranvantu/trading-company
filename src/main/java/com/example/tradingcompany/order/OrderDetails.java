package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.provider.Provider;
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
  private Provider provider;
  @ManyToOne
  private Customer customer;
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
  private Integer quantity;
  private OrderType type;

  public OrderDetails(LocalDateTime dateTime,
                      Staff staff,
                      Provider provider,
                      Product product,
                      Inventory inventory,
                      Integer quantity,
                      OrderType type) {
    this.dateTime = dateTime;
    this.staff = staff;
    this.product = product;
    this.inventory = inventory;
    this.provider = provider;
    this.quantity = quantity;
    this.type = type;
  }

  public OrderDetails(LocalDateTime dateTime,
                      Staff staff,
                      Customer customer,
                      Product product,
                      Inventory inventory,
                      Integer quantity,
                      OrderType type) {
    this.dateTime = dateTime;
    this.staff = staff;
    this.customer = customer;
    this.product = product;
    this.inventory = inventory;
    this.quantity = quantity;
    this.type = type;
  }
}
