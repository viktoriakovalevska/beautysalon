package com.example.beautysalon.dto;

import com.example.beautysalon.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data

public class UserRequestDTO {
    private String username;
    private String password;
    private String phone;
    private Date birthDay;
    private Set<Role> roles;
}
