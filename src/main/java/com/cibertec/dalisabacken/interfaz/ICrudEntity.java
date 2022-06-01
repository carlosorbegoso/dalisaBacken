package com.cibertec.dalisabacken.interfaz;

import com.cibertec.dalisabacken.config.exception.CBusinessException;

import java.util.List;

public interface ICrudEntity<T>{

    List<T> getList() throws CBusinessException;

    T getObject(Integer id) throws CBusinessException;

    T removedObject(Integer id) throws CBusinessException;

    T saveObject(T object) throws CBusinessException;

    T updateObject(T object) throws CBusinessException;

    Integer getNewCode() throws CBusinessException;
}
