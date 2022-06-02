package com.cibertec.dalisabacken.interfaz;

import com.cibertec.dalisabacken.models.CAccountType;
import com.cibertec.dalisabacken.models.CBank;
import com.cibertec.dalisabacken.models.CBankAccountType;

import java.util.List;

public interface IBank {

    List<CBank> getListBank();
    List<CAccountType> getListAccountType();
    List<CBankAccountType> getListBankAccountType();
    CBank registrar(CBank obj);
    CBank actualizar(CBank obj);
    void eliminar(Integer id);
    List<CBank> findAllBanks();
}
