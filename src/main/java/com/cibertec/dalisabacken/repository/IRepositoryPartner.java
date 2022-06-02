package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryPartner extends JpaRepository<CPartner,Integer> {

      @Query(value = "EXECUTE SP_NEW_COD_USE :id",nativeQuery = true)
      CPartner spNewCodUser(@Param("id") Integer Id);
}
