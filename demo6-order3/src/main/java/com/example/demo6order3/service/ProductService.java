package com.example.demo6order3.service;


import com.example.demo6order3.model.Product;
import com.example.demo6order3.rep.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


}
