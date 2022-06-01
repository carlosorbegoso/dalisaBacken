package com.cibertec.dalisabacken.models;

import com.cibertec.dalisabacken.models.request.CRequestProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblProduct")
@Getter
@Setter
@ToString
public class CProduct implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idProduct;
    private String name;
    @Column(length = 400)
    private String description ;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idCategory")
    private CCategory category;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idMark")
    private CMark mark;
    private Double price;
    private Integer quantity;
    private Integer point;
    @Column(length = 100)
    private String photo;
    @Column(length = 45,nullable = false)
    private String  registrationDate;
    @Column(length = 1)
    private Character removed;

    public CProduct(CRequestProduct product, String opc) {
        this.idProduct = product.getIdProduct();
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = new CCategory(product.getIdCategory());
        this.mark = new CMark(product.getIdMark());
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.point = product.getPoint();
        this.photo = product.getPhoto();
        this.registrationDate = (opc.equalsIgnoreCase("m"))?product.getRegistrationDate():Utils.getDateCurrent().concat(Utils.getTimeCurrent());
        this.removed = '0';
    }

}
