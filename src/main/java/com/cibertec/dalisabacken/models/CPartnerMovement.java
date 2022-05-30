package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tblMovementPartner")
public class CPartnerMovement implements Serializable {


    @Id
    @Column(nullable = false)
    private Integer idMovement;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idSendingPartner")
    private CPartner issuingPartner;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idReceiverPartner")
    private CPartner receiverPartner;
    @Column(length = 45,nullable = false)
    private String movementDate;
    private Double transferAmount;
    private String movementDescription;
    private String status;

}