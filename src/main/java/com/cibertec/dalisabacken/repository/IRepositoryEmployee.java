package com.cibertec.dalisabacken.repository;


import com.cibertec.dalisabacken.models.CEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryEmployee extends JpaRepository<CEmployee,Integer> {
}
