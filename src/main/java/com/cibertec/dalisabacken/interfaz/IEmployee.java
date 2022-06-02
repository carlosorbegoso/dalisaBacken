package com.cibertec.dalisabacken.interfaz;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CEmployee;

public interface IEmployee {

    CEmployee getEmployee(Integer id) throws CBusinessException;

}
