package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.ICrudEntity;
import com.cibertec.dalisabacken.models.CCategory;
import com.cibertec.dalisabacken.repository.IRepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCategory implements ICrudEntity<CCategory> {

    @Autowired
    private IRepositoryCategory repoCategory;

    @Override
    public List<CCategory> getList() throws CBusinessException {
        try{
            return  repoCategory.findAll().stream().filter(x->x.getRemoved() == '0').collect(Collectors.toList());
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CCategory getObject(Integer id) throws CBusinessException {
        try{
            return  repoCategory.findById(id).filter(x->x.getRemoved() == '0').orElse(null);
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CCategory removedObject(Integer id) throws CBusinessException {
        try{
            var category  = getObject(id);
            if(category != null){
                category.setRemoved('1');
                return saveObject(category);
            }else{
                return null;
            }
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CCategory saveObject(CCategory Object) throws CBusinessException {
        try{

            return  repoCategory.save(Object);
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CCategory updateObject(CCategory object) throws CBusinessException {
        try {
            var category = getObject(object.getIdCategory());
            if(category != null){
                return saveObject(object);
            }else{
                throw new CBusinessException(HttpStatus.BAD_REQUEST,"Error al Modificar");
            }
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Integer getNewCode() throws CBusinessException {
        try{
            return  repoCategory.getNewCode();
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

}
