package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.IEmployee;
import com.cibertec.dalisabacken.interfaz.IPartner;
import com.cibertec.dalisabacken.interfaz.IUser;
import com.cibertec.dalisabacken.models.CEmployee;
import com.cibertec.dalisabacken.models.CPartner;
import com.cibertec.dalisabacken.models.CUser;
import com.cibertec.dalisabacken.repository.IRepositoryEmployee;
import com.cibertec.dalisabacken.repository.IRepositoryPartner;
import com.cibertec.dalisabacken.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServiceUser implements IUser, IPartner, IEmployee {

    private final IRepositoryUser repoUser;
    private final IRepositoryPartner repoPartner;
    private final IRepositoryEmployee repoEmployee;

    @Autowired
    public ServiceUser(IRepositoryUser repoUser, IRepositoryPartner repoPartner, IRepositoryEmployee repoEmployee) {
        this.repoUser = repoUser;
        this.repoPartner = repoPartner;
        this.repoEmployee = repoEmployee;
    }

    @Override
    public CUser getUseByMail(String email) throws CBusinessException {
        try {
            return repoUser.findByMail(email);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CUser getUse(Integer id) throws CBusinessException {
        try {
            return repoUser.findById(id).orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CUser updateUser(CUser use) throws CBusinessException {
        try {
            return repoUser.save(use);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public Integer getNewCode() throws CBusinessException {
        try {
            return repoUser.getNewCode();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CUser loginUser(String user, String pass) throws CBusinessException {
       try {
           return repoUser.findByUserNameAndPassword(user, pass);
       }catch (Exception e){
           throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
       }
    }

    @Transactional
    @Override
    public CPartner affiliatePartner(CPartner partner) throws CBusinessException {
        try {
            var use = partner.getUser();
            repoUser.save(use);
            repoPartner.save(partner);
            return repoPartner.spNewCodUser(use.getIdUser());
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public CPartner getPartner(Integer id) throws CBusinessException {
        try {
            return repoPartner.findById(id).filter(x->x.getUser().getRemoved() == '0').orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CEmployee getEmployee(Integer id) throws CBusinessException {
        try {
            return repoEmployee.findById(id).filter(x->x.getUser().getRemoved() == '0').orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
}
