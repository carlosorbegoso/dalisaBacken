package com.cibertec.dalisabacken.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(force = true)
@Table(name="tblCategory")
@Getter
@Setter
@ToString
public class CCategory implements Serializable {

    @Id
    @Column(nullable = false)
    private Integer idCategory;
    private String categoryName;
    @Column(length = 1)
    private Character removed;

    public CCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}
