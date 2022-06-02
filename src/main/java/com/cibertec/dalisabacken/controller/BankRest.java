package com.cibertec.dalisabacken.controller;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CBank;
import com.cibertec.dalisabacken.servicio.ServiceBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("v1/Bank")
@CrossOrigin("*")
public class BankRest {

    private final ServiceBank bankService;

    @Autowired
    public BankRest(ServiceBank bankService) {
        this.bankService = bankService;
    }

    @RequestMapping(path = "/bankList", method = RequestMethod.GET)
    public List<CBank> bankList(){
        try {
            List<CBank> listar = bankService.findAllBanks();
            System.out.println("Bank List:" + listar);
            return listar;
        } catch (CBusinessException e){
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @RequestMapping(path = "/registerBank", method = RequestMethod.POST)
    public ResponseEntity<?> bankRegister(@RequestBody CBank cBank){
        try {
            CBank banco = bankService.registrar(cBank);
            System.out.println(banco);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CBusinessException e){
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @RequestMapping(path = "/updateBank", method = RequestMethod.PUT)
    public ResponseEntity<?> bankUpdate(@RequestBody CBank cBank){
        try {
            CBank banco = bankService.actualizar(cBank);
            System.out.println(banco);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CBusinessException e){
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }
    @RequestMapping(path = "/deleteBank/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> bankDelete(@PathVariable("id") Integer id){
        try {
            bankService.eliminar(id);
            System.out.println("Banco eliminado:" + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CBusinessException e){
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }
}
