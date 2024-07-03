package com.example.beautysalon.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private long id;
    private String username;
    private String password;
    private Date birthDay;
    private Set<String> roles;
    @CreatedDate
    private LocalDateTime createdAt;
    private String phone;



}
