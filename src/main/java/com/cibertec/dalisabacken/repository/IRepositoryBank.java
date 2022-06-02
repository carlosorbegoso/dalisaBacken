package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryBank extends JpaRepository<CBank, Integer> {
}
