package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CBankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryBankAccoutType extends JpaRepository<CBankAccountType, Integer> {
}
