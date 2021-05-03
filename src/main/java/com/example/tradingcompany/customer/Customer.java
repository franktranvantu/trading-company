package com.example.tradingcompany.customer;

import com.example.tradingcompany.order.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;
  private String phone;
  private String fax;
  private String email;
  @OneToMany(mappedBy = "customer")
  private Set<OrderDetails> orderDetails = new HashSet<>();

  public Customer(String name, String address, String phone, String fax, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
  }
}
