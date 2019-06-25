package com.titan.customer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerRemote extends javax.ejb.EJBObject {

    public Name getName() throws RemoteException;
    public void setName(Name name) throws RemoteException ;

    public void setAddress(AddressDO address) throws RemoteException ;
    public AddressDO getAddress() throws RemoteException ;

    public void setAddress(String street, String city, String state, String zip) throws RemoteException;

    public String getLastName() throws RemoteException;
    public void setLastName(String lname) throws RemoteException;

    public String getFirstName() throws RemoteException;
    public void setFirstName(String fname) throws RemoteException;

}
