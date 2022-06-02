package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CPartnerPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryPackage extends JpaRepository<CPartnerPackage,Integer> {

    @Query(value = "SELECT (COUNT(p)+1) FROM CPartnerPackage p")
    Integer getNewCode();
}
