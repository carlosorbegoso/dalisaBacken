package com.cibertec.dalisabacken.config.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CBusinessException  extends  RuntimeException{

    private HttpStatus status;

    public  CBusinessException(HttpStatus satus,String message){
        super(message);
        this.status=satus;
    }
}
