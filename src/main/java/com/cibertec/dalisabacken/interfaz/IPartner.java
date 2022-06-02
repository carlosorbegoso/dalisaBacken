package com.cibertec.dalisabacken.interfaz;

import com.cibertec.dalisabacken.config.exception.CBusinessException;
import com.cibertec.dalisabacken.models.CPartner;


public interface IPartner {

    CPartner affiliatePartner(CPartner partner) throws CBusinessException;
    CPartner getPartner(Integer id)throws CBusinessException;

}
