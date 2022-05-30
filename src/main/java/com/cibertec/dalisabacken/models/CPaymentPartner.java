package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblPaymentPartner")
@Getter
@Setter
@ToString
public class CPaymentPartner implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idPayment;
    @ManyToOne
    @JoinColumn(name = "idPartner")
    private CPartner partner;
    private Integer totalCollected;
    @Column(length = 20,nullable = false)
    private String generatedDate;
    @Column(length = 100)
    private String description;

}
