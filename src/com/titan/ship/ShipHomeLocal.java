package com.titan.ship;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ShipHomeLocal extends EJBLocalHome {
    ShipLocal create(Integer primaryKey, String name, double tonnage) throws CreateException;
    ShipLocal findByPrimaryKey(Integer key) throws FinderException;
}
