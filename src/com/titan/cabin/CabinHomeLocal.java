package com.titan.cabin;

import com.titan.ship.ShipLocal;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface CabinHomeLocal extends javax.ejb.EJBLocalHome {

    public CabinLocal create(ShipLocal ship, String name) throws CreateException;
    public CabinLocal findByPrimaryKey(Integer pk) throws FinderException;

}
