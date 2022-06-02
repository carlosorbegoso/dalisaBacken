package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.ICrudEntity;
import com.cibertec.dalisabacken.models.CPartnerPackage;
import com.cibertec.dalisabacken.repository.IRepositoryPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicePackage implements ICrudEntity<CPartnerPackage> {
    @Autowired
    private IRepositoryPackage repoPackage;

    @Override
    public List<CPartnerPackage> getList() throws CBusinessException {
        try {
            return repoPackage.findAll().stream().filter(x -> x.getRemoved() == '0').toList();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CPartnerPackage getObject(Integer id) throws CBusinessException {
        try {
            return repoPackage.findById(id).filter(x -> x.getRemoved() == '0').orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CPartnerPackage removedObject(Integer id) throws CBusinessException {
        try {
            var range = getObject(id);
            if (range != null) {
                range.setRemoved('1');
                return saveObject(range);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CPartnerPackage saveObject(CPartnerPackage object) throws CBusinessException {
        try {
            return repoPackage.save(object);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CPartnerPackage updateObject(CPartnerPackage object) throws CBusinessException {
        try {
            var range = getObject(object.getIdPackage());
            if (range != null) {
                return saveObject(object);
            } else {
                throw new CBusinessException(HttpStatus.BAD_REQUEST, "Error al Modificar");
            }
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Integer getNewCode() throws CBusinessException {
        try {
            return repoPackage.getNewCode();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
