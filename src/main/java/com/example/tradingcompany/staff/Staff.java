package com.example.tradingcompany.staff;

import com.example.tradingcompany.inventory.InventoryDeliveryNote;
import com.example.tradingcompany.inventory.InventoryReceivingNote;
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
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;
  private String phone;
  private String email;
  @OneToMany(mappedBy = "staff")
  private Set<OrderDetails> orderDetails = new HashSet<>();
  @OneToOne
  @JoinTable(
      name = "staff_delivery_note",
      joinColumns = {@JoinColumn(name = "staff_id")},
      inverseJoinColumns = {@JoinColumn}
  )
  private InventoryDeliveryNote deliveryNote;
  @OneToOne
  @JoinTable(
      name = "staff_receiving_note",
      joinColumns = {@JoinColumn(name = "staff_id")},
      inverseJoinColumns = {@JoinColumn}
  )
  private InventoryReceivingNote receivingNote;

  public Staff(String name, String address, String phone, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }
}
