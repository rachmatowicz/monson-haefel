package com.titan.clients;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface Test72HomeRemote extends EJBHome {
    Test72Remote create() throws RemoteException, CreateException;
}
