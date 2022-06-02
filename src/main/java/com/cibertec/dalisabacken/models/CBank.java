package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblBank")
@Getter
@Setter
@ToString
public class CBank implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idBank;
    private String nameBank;
    @Column(length =1)
    private Character removed;

    public CBank(Integer idBank) {
        this.idBank = idBank;
    }
}
