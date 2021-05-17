package com.example.tradingcompany.order;

import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>, JpaSpecificationExecutor<OrderDetails> {

    List<OrderDetails> findOrderDetailsByProductAndInventory(Product product, Inventory inventory);

    List<OrderDetails> findOrderDetailsByProductAndType(Product product, OrderType type);

}
