package com.cibertec.dalisabacken.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblUser")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CUser implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idUser;
    @Column(length = 25)
    private String  country;
    @Column(length = 35)
    private String  name;
    @Column(length = 35)
    private String  surName;
    @Column(length = 12)
    private String  birthDate;
    private String  role;
    private String  civilStatus;
    @Column(length = 25)
    private String  sex;
    @Column(unique = true,length = 8)
    private String  dni;
    private String  direction;
    private String  district;
    @Column(length = 20)
    private String  mobile;
    @Column(length = 10)
    private String  salary;
    @Column(unique = true)
    private String  mail;
    @Column(length = 15)
    private String  userName;
    @Column(length = 100)
    private String  password;
    @Column(length = 25)
    private String  accountDeposit;
    @ManyToOne
    @JoinColumn(name = "idBank")
    private CBank bank;
    private String  userPhoto;
    @Column(length = 45,nullable = false)
    private String  registrationDate;
    @Column(length = 1)
    private Character removed;
}


