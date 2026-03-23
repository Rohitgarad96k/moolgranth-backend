package com.moolgranth.backend.repository;

import com.moolgranth.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom method to find all orders for a specific user!
    List<Order> findByCustomerPhoneOrderByOrderDateDesc(String customerPhone);
}