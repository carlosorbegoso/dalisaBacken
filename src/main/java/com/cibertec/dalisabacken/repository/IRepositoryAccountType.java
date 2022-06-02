package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryAccountType extends JpaRepository<CAccountType, Integer> {
}
