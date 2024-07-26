package com.example.beautysalon.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RsponseStatuse {
    SUCCESSFUL(0),
    GENERAL_ERROR(1);

    private final Integer code;
}
