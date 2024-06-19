package com.example.demo6order3.controller;


import com.example.demo6order3.model.Product;
import com.example.demo6order3.rep.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Integer id) {
        Optional<Product> optionalProducts = productRepository.findById(id);
        if (!optionalProducts.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        product.setId(optionalProducts.get().getId());
        productRepository.save(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        Optional<Product> optionalProducts = productRepository.findById(id);
        if (!optionalProducts.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        productRepository.deleteById(optionalProducts.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> optionalProducts = productRepository.findById(id);
        if (!optionalProducts.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalProducts.get());
    }

}
