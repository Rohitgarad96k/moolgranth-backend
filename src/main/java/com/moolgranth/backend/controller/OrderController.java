package com.moolgranth.backend.controller;

import com.moolgranth.backend.dto.OrderRequest;
import com.moolgranth.backend.entity.Order;
import com.moolgranth.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"https://moolgranth.com", "https://www.moolgranth.com", "http://localhost:5173"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // 1. PLACE A NEW ORDER
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        Order newOrder = new Order();
        newOrder.setCustomerName(request.getCustomerName());
        newOrder.setCustomerPhone(request.getCustomerPhone());
        newOrder.setAddress(request.getAddress());
        newOrder.setTotalAmount(request.getTotalAmount());
        newOrder.setCartItems(request.getCartItems());

        orderRepository.save(newOrder);
        return ResponseEntity.ok("Order placed successfully!");
    }

    // 2. GET ORDERS FOR A SPECIFIC CUSTOMER
    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String phone) {
        List<Order> orders = orderRepository.findByCustomerPhoneOrderByOrderDateDesc(phone);
        return ResponseEntity.ok(orders);
    }

    // 3. GET ALL ORDERS (For the Admin Dashboard)
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
    
 // 4. UPDATE ORDER STATUS (For Admin)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> payload) {
        String newStatus = payload.get("status");
        
        // Find the order, update it, and save it back
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        orderRepository.save(order);
        
        return ResponseEntity.ok("Order status updated successfully!");
    }
}