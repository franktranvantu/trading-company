package com.example.tradingcompany.order;

import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findOrderDetailsByProductAndInventory(Product product, Inventory inventory);

}
