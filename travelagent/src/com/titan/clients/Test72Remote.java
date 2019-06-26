package com.titan.clients;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Test72Remote extends EJBObject {
    public String test_Cruises() throws RemoteException;
    public String test_Reservations() throws RemoteException;
    public String test_MoreReservations() throws RemoteException;
    public String test_Customers() throws RemoteException;
    public String test72e() throws RemoteException;
    public String test72f() throws RemoteException;
}
