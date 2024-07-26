package com.example.beautysalon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private final LocalDateTime serverTime = LocalDateTime.now();
    private Integer status;
    private T body;

    public static <T> Response<T> createSuccessfulResponseEntity(T body){
        return Response.<T>builder()
                .status(RsponseStatuse.SUCCESSFUL.getCode())
                .body(body)
                .build();
    }

    public static <T> Response<T> createGeneralErrorResponseEntity(T body){
        return Response.<T>builder()
                .status(RsponseStatuse.GENERAL_ERROR.getCode())
                .body(body)
                .build();
    }
}
