package com.titan.travelagent;

import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface TravelAgentHomeRemote extends javax.ejb.EJBHome {
    TravelAgentRemote create() throws RemoteException, CreateException;
}
