package com.example.beautysalon.repositories;

import com.example.beautysalon.entities.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
