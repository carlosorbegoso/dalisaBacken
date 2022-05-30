package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@Table(name="tblPartnerPackage")
public class CPartnerPackage implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idPackage;
    private String packageName;
    private Integer productQuantity;
    private Double price;
    private Integer promotionQuantity;
    @Column(length = 1)
    private Character removed;


    public CPartnerPackage(int idPackage) {
        this.idPackage = idPackage;
    }
}
