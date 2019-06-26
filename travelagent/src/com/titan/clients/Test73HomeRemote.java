package com.titan.clients;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface Test73HomeRemote extends EJBHome {
    Test73Remote create() throws RemoteException, CreateException;
}
