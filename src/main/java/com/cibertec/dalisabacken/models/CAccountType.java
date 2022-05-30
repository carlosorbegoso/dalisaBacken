package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblAccountType")
@Getter
@Setter
@ToString
public class CAccountType implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idAccountType;
    private String accountType;
    @Column(length = 1)
    private Character removed;
}
