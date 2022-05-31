package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblTicketDetail")
@Getter
@Setter
@ToString
public class CTicketDetail implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer id;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idProduct")
    private CProduct product;
    private Double price;
    private Double discount;
    private Integer quantity;
    private Integer point;
    private Double igv;
    private Double importToPay;




    public Double calculateImport() {
        return quantity.doubleValue() * product.getPrice();
    }
}
