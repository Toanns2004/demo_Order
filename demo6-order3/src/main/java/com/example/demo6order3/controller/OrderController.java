package com.example.demo6order3.controller;

import com.example.demo6order3.model.Customer;
import com.example.demo6order3.model.Orders;
import com.example.demo6order3.model.Product;
import com.example.demo6order3.rep.CustomerRepository;
import com.example.demo6order3.rep.ProductRepository;
import com.example.demo6order3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Optional<Customer> customerOptional = customerRepository.findById(order.getCustomer().getId());
        if (!customerOptional.isPresent() ) {
            return ResponseEntity.badRequest().build();
        }
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (!productOptional.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        Orders orders = new Orders();
        orders.setCustomer(customerOptional.get());
        orders.setOrder_date(orderService.getDate());
        orders.setTotal_price(order.getTotal_price());
        orders.setStatus(1);

        Orders savedOrder = orderService.createOrder(orders);

        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        Optional<Orders> orderOptional = Optional.ofNullable(orderService.getOrderById(id));

        if (!orderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orderService.deleteOrder(orderOptional.get().getId());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id) {
        Optional<Orders> orderOptional = Optional.ofNullable(orderService.getOrderById(id));

        if (!orderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (!productOptional.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        Orders orders = orderOptional.get();

        orderService.createOrder(orders);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Integer id) {
        Optional<Orders> orderOptional = Optional.ofNullable(orderService.getOrderById(id));

        if (!orderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orderOptional.get());
    }


}
