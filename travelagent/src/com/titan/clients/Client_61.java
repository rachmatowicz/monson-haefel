package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;
import com.titan.customer.CustomerHomeRemote;
import com.titan.customer.CustomerRemote;

import javax.ejb.Handle;
import javax.ejb.HomeHandle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

/**
 * Example showing use of accessing entitiy beans
 */
public class Client_61 {

    public static void main(String [] args) throws Exception
    {
        if (args.length<3 || args.length%3!=0)
        {
            System.out.println("Usage: java com.titan.clients.Client_61 <pk1> <fname1> <lname1> ...");
            System.exit(-1);
        }

        // obtain CustomerHome
        Context jndiContext = getInitialContext();

        Object obj = jndiContext.lookup("CustomerHomeRemote");
        CustomerHomeRemote home = (CustomerHomeRemote) javax.rmi.PortableRemoteObject.narrow(obj, CustomerHomeRemote.class);

        // create customers
        for (int i = 0; i < args.length; i++) {
            Integer pk = new Integer(i);
            String firstName = args[++i];
            String lastName = args[++i];
            CustomerRemote customer = home.create(pk);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
        }

        // find and remove customers
        for (int i = 0; i < args.length; i+=3) {
            Integer pk = new Integer(i);
            CustomerRemote customer = home.findByPrimaryKey(pk);
            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            System.out.println("Primary Key = " + pk);
            System.out.println(firstName + " " + lastName);
            customer.remove();
        }
    }

    /*
     * THis depends on jndi.properties being on the classpath at runtime
     * This in turn depends on IntelliJ marking the jndi directory as a resources root
     */
    static public Context getInitialContext() throws Exception {
        return new InitialContext();
    }
}
