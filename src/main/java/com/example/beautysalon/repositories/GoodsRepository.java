package com.example.beautysalon.repositories;

import com.example.beautysalon.entities.Goods;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GoodsRepository extends MongoRepository<Goods, Long> {
    List<Goods> findByNameContainingIgnoreCase(String name);
    Goods findByName(String name);

}
