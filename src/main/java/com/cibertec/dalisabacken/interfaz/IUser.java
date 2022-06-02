package com.cibertec.dalisabacken.interfaz;


import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CUser;
import org.apache.catalina.User;

public interface IUser {

     CUser getUseByMail(String email) throws CBusinessException;
     CUser getUse(Integer id)throws CBusinessException;
     CUser updateUser(CUser use)throws CBusinessException;
     Integer getNewCode() throws CBusinessException;
     CUser loginUser(String user,String pass) throws CBusinessException;
}
