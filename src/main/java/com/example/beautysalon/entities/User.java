package com.example.beautysalon.entities;

import com.example.beautysalon.common.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private Set<Role> roles;
    private String phone;



}
