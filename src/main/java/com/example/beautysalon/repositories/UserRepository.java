package com.example.beautysalon.repositories;


import com.example.beautysalon.entities.Counter;
import com.example.beautysalon.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByBirthDay(Date birthDay);
}
