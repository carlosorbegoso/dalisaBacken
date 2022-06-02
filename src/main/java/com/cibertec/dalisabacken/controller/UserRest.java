package com.cibertec.dalisabacken.controller;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.*;
import com.cibertec.dalisabacken.models.request.CRequestPartner;
import com.cibertec.dalisabacken.servicio.ServiceUser;
import com.cibertec.dalisabacken.utils.Utils;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/User")
@CrossOrigin("*")
public class UserRest {

    private final ServiceUser useService;
    @Autowired
    public UserRest(ServiceUser useService) {
        this.useService = useService;
    }

    @Operation(summary = "Afiliar Socio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CPartner.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "CSocio not found",
                    content = @Content)})
    @RequestMapping(path = "/newPartner", method = RequestMethod.POST)
    public ResponseEntity<?> affiliatePartner(@RequestBody CRequestPartner requestPartner) {
        try {
            var code=useService.getNewCode();
            var partner = new CPartner();
            partner.setIdSonPartner(code);
            partner.setActivated(requestPartner.getActivated());
            partner.setInscribedPackage(new CPartnerPackage(requestPartner.getIdInscribedPackage()));
            partner.setTotalPoint(requestPartner.getTotalPoint());
            partner.setRange(new CRange(requestPartner.getIdRange()));
            partner.setIdParentPartner(requestPartner.getIdParentPartner());
            var user =new CUser(requestPartner.getUser());
            user.setRegistrationDate(Utils.getDateCurrent());
            partner.setUser(user);
            System.out.println(new Gson().toJson(partner));
            return ResponseEntity.ok(useService.affiliatePartner(partner));
         //throw new CBusinessException(HttpStatus.OK,"success");
        } catch (CBusinessException e) {
            throw new CBusinessException(e.getStatus(), e.getMessage());
        }

    }

}
