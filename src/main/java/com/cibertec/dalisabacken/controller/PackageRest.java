package com.cibertec.dalisabacken.controller;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.config.exception.CErrorResponse;
import com.cibertec.dalisabacken.models.CPartnerPackage;
import com.cibertec.dalisabacken.servicio.ServicePackage;
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
@RequestMapping("v1/Package")
public class PackageRest {
    @Autowired
    private ServicePackage packageRange;


    @Operation(summary = "Listado de Paquete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CPartnerPackage.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paquete not found",
                    content = @Content)})
    @RequestMapping(path = "/listPackage", method = RequestMethod.GET)
    public ResponseEntity<?> getListPackage() throws CBusinessException {
        try {
            return ResponseEntity.ok(packageRange.getList());
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Guardar  Paquete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paquete not found",
                    content = @Content)})
    @RequestMapping(path = "/savePackage", method = RequestMethod.POST)
    public ResponseEntity<?> savePackage(@RequestBody CPartnerPackage pack) throws CBusinessException {
        try {
            //seteo el codigo de Rango con un nuevo code y valido si se registro o no
            pack.setIdPackage(packageRange.getNewCode());
            pack.setRemoved('0');
            if (packageRange.saveObject(pack) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.NO_CONTENT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }
    @Operation(summary = "Modificar  Paquete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paquete not found",
                    content = @Content)})
    @RequestMapping(path = "/updatePackage", method = RequestMethod.PUT)
    ResponseEntity<?> updatePackage(@RequestBody CPartnerPackage pack) throws CBusinessException {
        try {
            pack.setRemoved('0');
            if (packageRange.updateObject(pack) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Buscar  Paquete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CPartnerPackage.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paquete not found",
                    content = @Content)})
    @RequestMapping(path = "/getPackage", method = RequestMethod.GET)
    ResponseEntity<?> getPackage(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            var pack = packageRange.getObject(id);
            if (pack != null) {
                return ResponseEntity.ok(pack);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "No existe el Paquete ");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Remover  Paquete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Paquete not found",
                    content = @Content)})
    @RequestMapping(path = "/removerPackage", method = RequestMethod.DELETE)
    ResponseEntity<?> removedPackage(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            if (packageRange.removedObject(id) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Removio Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Remover");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }
}
