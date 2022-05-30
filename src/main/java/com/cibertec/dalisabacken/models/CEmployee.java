package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblEmployee")
@Getter
@Setter
@ToString
public class CEmployee implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idEmployee;
    private String area;
    private String status;
    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "idUser",unique = true)
    private CUser user;
}
