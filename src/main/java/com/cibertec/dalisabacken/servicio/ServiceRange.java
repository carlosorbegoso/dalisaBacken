package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.ICrudEntity;
import com.cibertec.dalisabacken.models.CRange;
import com.cibertec.dalisabacken.repository.IRepositoryRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRange implements ICrudEntity<CRange> {

    @Autowired
    private IRepositoryRange repoRange;

    @Override
    public List<CRange> getList() throws CBusinessException {
        try {
            return repoRange.findAll().stream().filter(x -> x.getRemoved() == '0').toList();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CRange getObject(Integer id) throws CBusinessException {
        try {
            return repoRange.findById(id).filter(x -> x.getRemoved() == '0').orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CRange removedObject(Integer id) throws CBusinessException {
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
    public CRange saveObject(CRange object) throws CBusinessException {
        try {
            return repoRange.save(object);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CRange updateObject(CRange object) throws CBusinessException {
        try {
            var range = getObject(object.getIdRange());
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
            return repoRange.getNewCode();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
