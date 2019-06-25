package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;

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
import java.util.Properties;

/**
 * Example showing use of handles
 */
public class Client_52c {

    public static void main(String [] args)
    {
        try {
            Context jndiContext = getInitialContext();

            // Get the Remote Client API for the CabinEJB
            Object ref = jndiContext.lookup("CabinHomeRemote");
            CabinHomeRemote home = (CabinHomeRemote) PortableRemoteObject.narrow(ref,CabinHomeRemote.class);

            System.out.println("Testing serialization of EJBObject handle");

            Integer pk_1 = new Integer(100);
            CabinRemote cabin_1 = home.findByPrimaryKey(pk_1);

            // Serialize the Handle for cabin 100 to a file.
            Handle handle = cabin_1.getHandle();
            FileOutputStream fos = new FileOutputStream("handle100.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fos);
            System.out.println("Writing handle to file...");
            outStream.writeObject(handle);
            outStream.flush();
            fos.close();
            handle = null;

            // Deserialize the Handle for cabin 100.
            FileInputStream fis = new FileInputStream("handle100.ser");
            ObjectInputStream inStream = new ObjectInputStream(fis);
            System.out.println("Reading handle from file...");
            handle = (Handle)inStream.readObject();
            fis.close();

            // Re-obtain a remote reference to cabin 100 and read its name.
            System.out.println("Acquiring reference using deserialized handle...");
            ref = handle.getEJBObject();
            CabinRemote cabin_2 = (CabinRemote) PortableRemoteObject.narrow(ref, CabinRemote.class);

            // Check to see that the handle before serialization and handle after serialization point to the same object
            if(cabin_1.isIdentical(cabin_2)) {
                System.out.println("cabin_1.isIdentical(cabin_2) returns true - This is correct");
            } else {
                System.out.println("cabin_1.isIdentical(cabin_2) returns false - This is wrong!");
            }

            System.out.println("Testing serialization of Home handle");

            // Serialize the HomeHandle for the cabin bean.
            HomeHandle homeHandle = home.getHomeHandle();
            fos = new FileOutputStream("handle.ser");
            outStream = new ObjectOutputStream(fos);
            System.out.println("Writing Home handle to file...");
            outStream.writeObject(homeHandle);
            outStream.flush();
            fos.close();
            homeHandle = null;

            // Deserialize the HomeHandle for the cabin bean.
            fis = new FileInputStream("handle.ser");
            inStream = new ObjectInputStream(fis);
            System.out.println("Reading Home handle from file...");
            homeHandle = (HomeHandle)inStream.readObject();
            fis.close();

            System.out.println("Acquiring reference using de-serialized Home handle...");
            Object hometemp = homeHandle.getEJBHome();
            CabinHomeRemote home2 = (CabinHomeRemote) PortableRemoteObject.narrow(hometemp,CabinHomeRemote.class);

            System.out.println("Acquiring reference to bean using new Home interface...");
            CabinRemote cabin_3 = home2.findByPrimaryKey(pk_1);

            // Test that we end up with the same bean after finding it through this home
            if(cabin_1.isIdentical(cabin_3)) {
                System.out.println("cabin_1.isIdentical(cabin_3) returns true - This is correct");
            } else {
                System.out.println("cabin_1.isIdentical(cabin_3) returns false - This is wrong!");
            }
        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
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
