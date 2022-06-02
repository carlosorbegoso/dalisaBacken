package com.cibertec.dalisabacken.models.request;

import com.cibertec.dalisabacken.models.CCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;


@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
public class CRequestProduct implements Serializable {


    private Integer idProduct;
    private String  name;
    private String  description ;
    private Integer idCategory;
    private Integer idMark;
    private Double  price;
    private Integer quantity;
    private Integer point;
    private String  photo;
    private String  registrationDate;
    private Character removed;
}
