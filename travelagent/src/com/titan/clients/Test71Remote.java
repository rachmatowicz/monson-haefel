package com.titan.clients;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Test71Remote extends EJBObject {
    public String test_Address() throws RemoteException;
    public String test_CreditCard() throws RemoteException;
    public String test_PhoneNumber() throws RemoteException;
}
