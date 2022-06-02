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
@Table(name = "tblRange")
public class CRange implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idRange;
    private String name;
    private Integer pointRange;
    private Double bonus;
    @Column(length = 1)
    private Character removed;

    public CRange(Integer idRange) {
        this.idRange = idRange;
    }
}

