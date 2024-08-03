package com.example.beautysalon.repositories;

import com.example.beautysalon.entities.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProducerRepository extends MongoRepository<Producer, String> {


    List<Producer> findByNameContainingIgnoreCase(String name);
}
