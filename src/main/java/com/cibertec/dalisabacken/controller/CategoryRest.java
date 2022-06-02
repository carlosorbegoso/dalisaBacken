package com.cibertec.dalisabacken.controller;


import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CCategory;
import com.cibertec.dalisabacken.servicio.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("v1/Category")
@CrossOrigin("*")
public class CategoryRest {


    private ServiceCategory serviceCategory;

    @Autowired
    public CategoryRest(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @RequestMapping(path = "/saveCategory", method = RequestMethod.POST)
    ResponseEntity<?> saveProduct(@RequestBody CCategory category) throws CBusinessException {
        try {
            category.setIdCategory(serviceCategory.getNewCode());
            if (serviceCategory.saveObject(category) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }


    @RequestMapping(path = "/listCategory", method = RequestMethod.POST)
    public ResponseEntity<?> getListCategory() throws CBusinessException {
        try {
            return ResponseEntity.ok(serviceCategory.getList());
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @RequestMapping(path = "/updateCategory", method = RequestMethod.PUT)
    ResponseEntity<?> updateCategory(@RequestBody CCategory category) throws CBusinessException {
        try {
            if (serviceCategory.updateObject(category) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }


    @RequestMapping(path = "/removerCategory/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removedCategory(@PathVariable("id") Integer id) throws CBusinessException {
        try {
            if (serviceCategory.removedObject(id) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Removio Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Remover");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @RequestMapping(path = "/getCategory", method = RequestMethod.GET)
    ResponseEntity<?> getCategory(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            var product = serviceCategory.getObject(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "No existe la categoria ");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }
}
