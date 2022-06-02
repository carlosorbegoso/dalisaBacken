package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblMark")
@Getter
@Setter
@ToString
public class CMark implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMark;
    private String markName;
    @Column(length = 1)
    private Character removed;

    public CMark(Integer idMark) {
        this.idMark = idMark;
    }

}
