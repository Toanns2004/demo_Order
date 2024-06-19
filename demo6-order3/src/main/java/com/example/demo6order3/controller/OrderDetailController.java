package com.example.demo6order3.controller;

import com.example.demo6order3.model.OrderDetail;
import com.example.demo6order3.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-details")
@CrossOrigin("http://localhost:3000")

public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable int orderId) {
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail savedOrderDetail = orderDetailService.save(orderDetail);
        return ResponseEntity.ok(savedOrderDetail);
    }
}
