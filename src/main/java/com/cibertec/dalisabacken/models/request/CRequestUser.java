package com.cibertec.dalisabacken.models.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CRequestUser implements Serializable {

    private Integer idUser;
    private String  country;
    private String  name;
    private String  surName;
    private String  birthDate;
    private String  role;
    private String  civilStatus;
    private String  sex;
    private String  dni;
    private String  direction;
    private String  district;
    private String  mobile;
    private String  salary;
    private String  mail;
    private String  userName;
    private String  password;
    private String  accountDeposit;
    private Integer idBank;
    private String  userPhoto;
    private String  registrationDate;
    private Character removed;
}
