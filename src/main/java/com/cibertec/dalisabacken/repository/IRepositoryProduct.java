package com.web.dalisaback_end.repository;

import com.cibertec.dalisabacken.models.CProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryProduct extends JpaRepository<CProduct,Integer> {

    @Query(value = "SELECT (COUNT(p)+1) FROM CProduct p")
    Integer getNewCode();
}
