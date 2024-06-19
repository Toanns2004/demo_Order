package com.example.demo6order3.service;

import com.example.demo6order3.model.Orders;
import com.example.demo6order3.rep.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }


    public Orders getOrderById(int order_id){
        return orderRepository.findById(order_id).orElse(null);
    }

    public Orders createOrder(Orders order){
        return orderRepository.save(order);
    }

    public Orders updateOrder(Orders order){
        return orderRepository.save(order);
    }

    public void deleteOrder(int order_id){
        orderRepository.deleteById(order_id);
    }

    public Timestamp getDate() {
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
        return currentTimestamp;
    }

}
