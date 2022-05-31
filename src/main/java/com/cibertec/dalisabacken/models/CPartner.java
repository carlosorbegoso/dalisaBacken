package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tblPartner")
public class CPartner implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idSonPartner;
    @Column(length = 1)
    private String activated;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRange")
    private CRange range;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInscribedPackage")
    private CPartnerPackage inscribedPackage;
    private Integer idParentPartner;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private CUser user;
    private Integer totalPoint;

}
