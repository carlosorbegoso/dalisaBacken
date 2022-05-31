package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblTicketHead")
@Getter
@Setter
@ToString
public class CTicketHead implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer tickerNumber;
    @ManyToOne
    @JoinColumn(name = "idPartner",nullable = false)
    private CPartner partner;
    private Integer pointTotal;
    private Integer ProductPriceTotal;
    @Column(nullable = false,length = 45)
    private String orderDate;
    @Column(length = 45)
    private String deliveryDate;
    private Double  amountPriceTotal;
    @Column(length = 50)
    private String status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="idTicketDetail")
    private List<CTicketDetail> ticketDetails;

    public Double getTotal() {
        Double total = 0.0;
        for (CTicketDetail det:ticketDetails) {
            total += det.calculateImport();
        }
        return total;
    }
}
