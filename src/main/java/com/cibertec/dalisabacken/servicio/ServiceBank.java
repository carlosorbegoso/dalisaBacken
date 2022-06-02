package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.IBank;
import com.cibertec.dalisabacken.models.CAccountType;
import com.cibertec.dalisabacken.models.CBank;
import com.cibertec.dalisabacken.models.CBankAccountType;
import com.cibertec.dalisabacken.repository.IRepositoryAccountType;
import com.cibertec.dalisabacken.repository.IRepositoryBank;
import com.cibertec.dalisabacken.repository.IRepositoryBankAccoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBank implements IBank {

    private final IRepositoryBank iRepositoryBank;
    private final IRepositoryAccountType iRepositoryAccountType;
    private final IRepositoryBankAccoutType iRepositoryBankAccoutType;

    @Autowired
    public ServiceBank(IRepositoryBank iRepositoryBank, IRepositoryAccountType iRepositoryAccountType,
                       IRepositoryBankAccoutType iRepositoryBankAccoutType) {
        this.iRepositoryBank = iRepositoryBank;
        this.iRepositoryAccountType = iRepositoryAccountType;
        this.iRepositoryBankAccoutType=iRepositoryBankAccoutType;
    }

    public List<CBank> getListBank() throws CBusinessException {
        try {
            return  iRepositoryBank.findAll().stream().filter(x->x.getRemoved().equals('0')).toList();
        }catch (Exception e){
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    public List<CAccountType> getListAccountType() throws CBusinessException {
        try {
            return iRepositoryAccountType.findAll().stream().filter(x->x.getRemoved().equals('0')).toList();
        }catch (Exception e){
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
    public List<CBankAccountType> getListBankAccountType() throws CBusinessException {
        try {
            return iRepositoryBankAccoutType.findAll();
        }catch (Exception e){
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    public CBank registrar(CBank obj){
        try {
            return  iRepositoryBank.save(obj);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    public CBank actualizar(CBank obj){
        try {
            return  iRepositoryBank.save(obj);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    public void eliminar(Integer id){
        try {
            iRepositoryBank.deleteById(id);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    public List<CBank> findAllBanks(){
        try {
            return iRepositoryBank.findAll();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
}
