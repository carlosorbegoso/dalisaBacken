package com.cibertec.dalisabacken.repository;

import com.cibertec.dalisabacken.models.CUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryUser extends JpaRepository<CUser,Integer> {

    @Query(value = "SELECT (COUNT(u)+1) FROM CUser u")
    Integer getNewCode();
    CUser findByMail(String mail);
    CUser findByUserNameAndPassword(String userName,String password);
}
