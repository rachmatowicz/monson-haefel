package com.titan.clients;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Test73Remote extends EJBObject {
    public String test_Customer() throws RemoteException;
}
