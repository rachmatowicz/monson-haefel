package com.titan.cruise;

import com.titan.ship.ShipLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface CruiseHomeLocal extends EJBLocalHome {
    CruiseLocal create(String name, ShipLocal ship) throws CreateException;
    CruiseLocal findByPrimaryKey(Integer key) throws FinderException;
}
