package com.cibertec.dalisabacken.controller;

import com.cibertec.dalisabacken.models.CMark;
import com.cibertec.dalisabacken.servicio.ServiceMark;
import com.cibertec.dalisabacken.servicio.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("v1/Mark")
@CrossOrigin("*")
public class MarkRest {

    @Qualifier("MarkService")
    private  ServiceMark serviceMark;

    @Autowired
    public MarkRest(ServiceMark serviceMark) {
        this.serviceMark = serviceMark;
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertMark(@RequestBody CMark cMark){

        CMark flag = serviceMark.saveObject(cMark);
        if (flag.equals(cMark)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/list")
    public List<CMark> listMark(){
        return serviceMark.getList();
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateMark(@RequestBody CMark cMark) {

        CMark flag = serviceMark.updateObject(cMark);
        if (flag.equals(cMark)) {

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMark(@PathVariable("id") int id){

        CMark flag = serviceMark.removedObject(id);
        if (flag.equals(id)) {

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getMark/{id}")
    public CMark getMark(@PathVariable("id") int id){
        return serviceMark.getObject(id);
    }

}
