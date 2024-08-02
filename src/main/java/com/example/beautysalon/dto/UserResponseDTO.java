package com.example.beautysalon.dto;

import com.example.beautysalon.common.Role;
import com.example.beautysalon.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private long id;
    private String username;
    private String phone;
    private Date birthDay;
    private Set<Role> roles;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.birthDay = user.getBirthDay();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
    }

    public UserResponseDTO(UserRequestDTO user){
        this.username = user.getUsername();
        this.birthDay = user.getBirthDay();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
    }

}
