package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IRepostiroyMark extends JpaRepository<CMark,Integer> {

    @Query(value = "SELECT (COUNT(u)+1) FROM CMark m")
    Integer getNewCode();
}
