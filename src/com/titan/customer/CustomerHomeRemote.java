package com.titan.customer;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface CustomerHomeRemote extends javax.ejb.EJBHome {

    public CustomerRemote create(Integer id) throws CreateException, RemoteException;

    public CustomerRemote findByPrimaryKey(Integer id) throws RemoteException, FinderException;
}
