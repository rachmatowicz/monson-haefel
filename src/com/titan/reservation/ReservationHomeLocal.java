package com.titan.reservation;

import com.titan.cruise.CruiseLocal;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.Collection;

public interface ReservationHomeLocal extends javax.ejb.EJBLocalHome {

    // methods for creating and finding Reservation EJBs
    ReservationLocal create(CruiseLocal cruise, Collection customers) throws CreateException;

    ReservationLocal findByPrimaryKey(Integer key) throws FinderException;
}
