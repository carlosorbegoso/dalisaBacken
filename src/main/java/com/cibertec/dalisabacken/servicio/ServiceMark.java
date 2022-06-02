package com.cibertec.dalisabacken.servicio;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.ICrudEntity;
import com.cibertec.dalisabacken.models.CMark;

import com.cibertec.dalisabacken.repository.IRepostiroyMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("MarkService")
public class ServiceMark implements ICrudEntity<CMark> {

    @Autowired
    private IRepostiroyMark repoMark;

    @Override
    public List<CMark> getList() throws CBusinessException {
     try {
            return repoMark.findAll().stream().filter(x->x.getRemoved() == '0').collect(Collectors.toList());
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CMark getObject(Integer id) throws CBusinessException {
        try {
            return repoMark.findById(id).filter(x->x.getRemoved() == '0').orElse(null);
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CMark removedObject(Integer id) throws CBusinessException {
        try{
            var mark = getObject(id);
            if(mark != null){
                mark.setRemoved('1');
                return saveObject(mark);
            }else{
                return null;
            }
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CMark saveObject(CMark object) throws CBusinessException {
        try {
            return repoMark.save(object);
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public CMark updateObject(CMark object) throws CBusinessException {
        try {
            var mark = getObject(object.getIdMark());
            if(mark != null){
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
            return  repoMark.getNewCode();
        }catch (Exception e){
            throw  new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }


}
