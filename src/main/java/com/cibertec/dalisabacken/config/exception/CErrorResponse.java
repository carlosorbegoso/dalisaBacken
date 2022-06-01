package com.cibertec.dalisabacken.config.exception;


import lombok.*;



@Data
@Builder
public class CErrorResponse {

    private Integer code;
    private String status;
    private String message;

}
