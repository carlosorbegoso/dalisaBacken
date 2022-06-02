package com.cibertec.dalisabacken.service;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.interfaz.ICrudEntity;
import com.cibertec.dalisabacken.models.CProduct;
import com.web.dalisaback_end.repository.IRepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceProduct implements ICrudEntity<CProduct> {


    @Autowired
    private IRepositoryProduct repoProduct;

    @Override
    public List<CProduct> getList() throws CBusinessException {
        try {
            return repoProduct.findAll().stream().filter(x->x.getRemoved() == '0').toList();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CProduct getObject(Integer id) throws CBusinessException {
        try {
            return repoProduct.findById(id).filter(x -> x.getRemoved() == '0').orElse(null);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CProduct removedObject(Integer id) throws CBusinessException {
        try{
            var product = getObject(id);
            if(product != null){
                product.setRemoved('1');
                return saveObject(product);
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CProduct saveObject(CProduct object) throws CBusinessException {
        try {
            return repoProduct.save(object);
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public CProduct updateObject(CProduct object) throws CBusinessException {
        try {
            var product = getObject(object.getIdProduct());
            if(product != null){
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
        try {
            return repoProduct.getNewCode();
        } catch (Exception e) {
            throw new CBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
