package com.cibertec.dalisabacken.controller;
import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CMark;
import com.cibertec.dalisabacken.servicio.ServiceMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/Mark")
@CrossOrigin("*")
public class MarkRest {

    @Qualifier("MarkService")
    private ServiceMark serviceMark;

    @Autowired
    public MarkRest(ServiceMark serviceMark) {
        this.serviceMark = serviceMark;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveMark(@RequestBody CMark cMark) throws CBusinessException {
        try {
            cMark.setIdMark(serviceMark.getNewCode());
            if (serviceMark.saveObject(cMark) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se guardo correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error de Guardado");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/list")
    public ResponseEntity<?> listMark() throws CBusinessException {
        try {
            return ResponseEntity.ok(serviceMark.getList());
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMark(@RequestBody CMark cMark) throws CBusinessException {

        try {
            if (serviceMark.updateObject(cMark) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se guardo correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error de Guardado");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMark(@PathVariable("id") int id) {

        try {
            if (serviceMark.removedObject(id) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Removio Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Remover");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/getMark/{id}")
    public ResponseEntity<?> getMark(@PathVariable("id") Integer id) throws CBusinessException {
        try {
            var mark = serviceMark.getObject(id);
            if (mark != null) {
                return ResponseEntity.ok(mark);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "No existe el Producto ");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

}
