package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_Bank_AccountType")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CBankAccountType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBankAccountType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBank")
    private CBank bank;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAccountType")
    private CAccountType accountType;

    public CBankAccountType(Integer idBankAccountType) {
        this.idBankAccountType = idBankAccountType;
    }
}
