package com.titan.clients;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface Test71HomeRemote extends EJBHome {
    Test71Remote create() throws RemoteException, CreateException;
}
