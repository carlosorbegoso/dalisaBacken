package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CCategory;
import com.cibertec.dalisabacken.models.CUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCategory extends JpaRepository<CCategory,Integer> {

    @Query(value = "SELECT (COUNT(c)+1) FROM CCategory c")
    Integer getNewCode();

}
