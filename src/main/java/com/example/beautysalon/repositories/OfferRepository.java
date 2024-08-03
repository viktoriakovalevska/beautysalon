package com.example.beautysalon.repositories;

import com.example.beautysalon.entities.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {

    List<Offer> findByNameContainingIgnoreCase(String name);
}
