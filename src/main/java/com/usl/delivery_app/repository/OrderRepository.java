package com.usl.delivery_app.repository;

import com.usl.delivery_app.data.OrderData.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{


}
