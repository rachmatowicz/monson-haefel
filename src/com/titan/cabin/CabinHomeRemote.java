package com.titan.cabin;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface CabinHomeRemote extends javax.ejb.EJBHome {

    public CabinRemote create(Integer id) throws RemoteException, CreateException;
    public CabinRemote findByPrimaryKey(Integer pk) throws FinderException, RemoteException;

}
