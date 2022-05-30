package com.cibertec.dalisabacken.models.request;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CRequestPartner implements Serializable {

    private Integer idSonPartner;
    private String activated;
    private Integer idRange;
    private Integer idInscribedPackage;
    private Integer idParentPartner;
    private CRequestUser user;
    private Integer totalPoint;

}
