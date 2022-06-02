package com.cibertec.dalisabacken.controller;


import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.config.exception.CErrorResponse;
import com.cibertec.dalisabacken.models.CRange;
import com.cibertec.dalisabacken.servicio.ServiceRange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/Range")
public class RangeRest {

    @Autowired
    private ServiceRange rangeService;


    @Operation(summary = "Listado de Rango")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CRange.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rango not found",
                    content = @Content)})
    @RequestMapping(path = "/listRange", method = RequestMethod.GET)
    public ResponseEntity<?> getListRange() throws CBusinessException {
        try {
            return ResponseEntity.ok(rangeService.getList());
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Guardar  Rango")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rango not found",
                    content = @Content)})
    @RequestMapping(path = "/saveRange", method = RequestMethod.POST)
    public ResponseEntity<?> saveRange(@RequestBody CRange range) throws CBusinessException {
        try {
            //seteo el codigo de Rango con un nuevo code y valido si se registro o no
            range.setIdRange(rangeService.getNewCode());
            range.setRemoved('0');
            if (rangeService.saveObject(range) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.NO_CONTENT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }
    @Operation(summary = "Modificar  Rango")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rango not found",
                    content = @Content)})
    @RequestMapping(path = "/updateRange", method = RequestMethod.PUT)
    ResponseEntity<?> updateRange(@RequestBody CRange range) throws CBusinessException {
        try {
            range.setRemoved('0');
            if (rangeService.updateObject(range) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Buscar  Rango")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CRange.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rango not found",
                    content = @Content)})
    @RequestMapping(path = "/getRange", method = RequestMethod.GET)
    ResponseEntity<?> getRange(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            var range = rangeService.getObject(id);
            if (range != null) {
                return ResponseEntity.ok(range);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "No existe el Paquete ");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Remover  Rango")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rango not found",
                    content = @Content)})
    @RequestMapping(path = "/removerRange", method = RequestMethod.DELETE)
    ResponseEntity<?> removedRange(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            if (rangeService.removedObject(id) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Removio Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Remover");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

}
