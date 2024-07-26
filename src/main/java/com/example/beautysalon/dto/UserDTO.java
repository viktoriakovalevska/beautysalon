package com.example.beautysalon.dto;

import com.example.beautysalon.common.Role;
import com.example.beautysalon.entities.User;
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
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private String phone;
    private Date birthDay;
    private Set<Role> roles;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthDay = user.getBirthDay();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
    }


}
