package com.cibertec.dalisabacken.config.exception;

import lombok.Getter;

@Getter
public class CRequestException extends RuntimeException{

    private Integer code;


    public CRequestException(Integer code, String message){
       super(message);
        this.code = code;
    }

}
