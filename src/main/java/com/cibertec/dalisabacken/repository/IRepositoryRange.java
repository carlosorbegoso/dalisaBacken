package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IRepositoryRange extends JpaRepository<CRange,Integer> {

    @Query(value = "SELECT (COUNT(r)+1) FROM CRange r")
    Integer getNewCode();
}
