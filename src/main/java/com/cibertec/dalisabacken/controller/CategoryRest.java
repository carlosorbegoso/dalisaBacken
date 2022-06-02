package com.cibertec.dalisabacken.controller;


import com.cibertec.dalisabacken.models.CCategory;
import com.cibertec.dalisabacken.models.CMark;
import com.cibertec.dalisabacken.servicio.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/Mark")
@CrossOrigin("*")
public class CategoryRest {

    //@Qualifier("CategoryService")
    private ServiceCategory serviceCategory;

    @Autowired
    public CategoryRest(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertCategory(@RequestBody CCategory cCat){

        CCategory flag = serviceCategory.saveObject(cCat);
        if (flag.equals(cCat)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/list")
    public List<CCategory> listCategory(){
        return serviceCategory.getList();
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateCategory(@RequestBody CCategory cCat) {

        CCategory flag = serviceCategory.updateObject(cCat);
        if (flag.equals(cCat)) {

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){

        CCategory flag = serviceCategory.removedObject(id);
        if (flag.equals(id)) {

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getCategory/{id}")
    public CCategory getCategory(@PathVariable("id") int id){
        return serviceCategory.getObject(id);
    }




}
