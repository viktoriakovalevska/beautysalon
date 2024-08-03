package com.example.beautysalon.repositories;

import com.example.beautysalon.entities.Order;
import com.example.beautysalon.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByClient(User client);
    List<Order> findByMaster(User master);
}
