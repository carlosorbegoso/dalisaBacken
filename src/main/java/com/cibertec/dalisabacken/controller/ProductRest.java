package com.cibertec.dalisabacken.controller;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.config.exception.CErrorResponse;
import com.cibertec.dalisabacken.models.CProduct;
import com.cibertec.dalisabacken.models.request.CRequestProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.cibertec.dalisabacken.service.ServiceProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/Product")
public class ProductRest {

    @Autowired
    private ServiceProduct prodService;

    @Operation(summary = "Listado de Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CProduct.class)))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto not found",
                    content = @Content)})
    @RequestMapping(path = "/listProduct", method = RequestMethod.GET)
    public ResponseEntity<?> getListProduct() throws CBusinessException {
        try {
            return ResponseEntity.ok(prodService.getList());
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Guardar  Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto not found",
                    content = @Content)})
    @RequestMapping(path = "/saveProduct", method = RequestMethod.POST)
    ResponseEntity<?> saveProduct(@RequestBody CRequestProduct product) throws CBusinessException {
        try {
            //seteo el codigo de producto con un nuevo code y valido si se registro o no
            //se envio opc=n significando que se le pasara un objecto nuevo
            product.setIdProduct(prodService.getNewCode());
            if (prodService.saveObject(new CProduct(product,"n")) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }
    @Operation(summary = "Modificar  Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto not found",
                    content = @Content)})
    @RequestMapping(path = "/updateProduct", method = RequestMethod.PUT)
    ResponseEntity<?> updateProduct(@RequestBody CRequestProduct product) throws CBusinessException {
        try {
            //se envio opc=m significando que se le pasara un objecto nuevo
            if (prodService.updateObject(new CProduct(product,"m")) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Guardo Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.CONFLICT, "Error al Guardar");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Buscar  Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CProduct.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto not found",
                    content = @Content)})
    @RequestMapping(path = "/getProduct", method = RequestMethod.GET)
    ResponseEntity<?> getProduct(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            var product = prodService.getObject(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "No existe el Producto ");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

    @Operation(summary = "Remover  Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto not found",
                    content = @Content)})
    @RequestMapping(path = "/removerProduct", method = RequestMethod.DELETE)
    ResponseEntity<?> removedProduct(@RequestParam(name = "id") Integer id) throws CBusinessException {
        try {
            if (prodService.removedObject(id) != null) {
                throw new CBusinessException(HttpStatus.OK, "Se Removio Correctamente");
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Remover");
            }
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }
}
