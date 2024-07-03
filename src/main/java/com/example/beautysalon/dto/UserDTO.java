package com.example.beautysalon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private Date birthDay;

}
