package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;
import com.titan.travelagent.TravelAgentHomeRemote;
import com.titan.travelagent.TravelAgentRemote;

import javax.ejb.EJBMetaData;
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
 * Example showing use of primary keys
 */
public class Client_52b {

    public static void main(String [] args) {
        try {
            Context jndiContext = getInitialContext();

            // get the Remote Client API for the CabinEJB
            Object ref = jndiContext.lookup("CabinHomeRemote");
            CabinHomeRemote home = (CabinHomeRemote) PortableRemoteObject.narrow(ref,CabinHomeRemote.class);

            testReferences(home);
            testSerialization(home);

            System.out.println("Removing Cabin from database to clean up..");
            // Make this client class re-entrant by cleaning up the bean we created
            Integer pk = new Integer(101);
            home.remove(pk);
        }
        catch (java.rmi.RemoteException re) {
            re.printStackTrace();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Test the equivalence of two remote references to the same bean
     */
    public static void testReferences(CabinHomeRemote home) throws Exception
    {

        System.out.println("Creating Cabin 101 and retrieving additional reference by pk");
        CabinRemote cabin_1 = home.create(new Integer(101));
        Integer pk = (Integer)cabin_1.getPrimaryKey();
        CabinRemote cabin_2 = home.findByPrimaryKey(pk);

        System.out.println("Testing reference equivalence");
        // We now have two remote references to the same bean -- Prove it!
        cabin_1.setName("Keel Korner");
        if (cabin_2.getName().equals("Keel Korner")) {
            System.out.println("Names match!");
        }

        // Test the isIdentical() function
        if (cabin_1.isIdentical(cabin_2)) {
            System.out.println("cabin_1.isIdentical(cabin_2) returns true - This is correct");
        } else {
            System.out.println("cabin_1.isIdentical(cabin_2) returns false - This is wrong!");
        }
    }

    /**
     * Test the serialization/deserialization of a primary key.
     * This is a pointless example: we should be serializing the Handle for the object
     */
    public static void testSerialization(CabinHomeRemote home) throws Exception  {

        System.out.println("Testing serialization of primary key");
        Integer pk_1 = new Integer(101);
        CabinRemote cabin_1 = home.findByPrimaryKey(pk_1);
        System.out.println("Setting cabin name to Presidential Suite");
        cabin_1.setName("Presidential Suite");

        // Serialize the primary key for cabin 101 to a file.
        FileOutputStream fos = new FileOutputStream("pk101.ser");
        ObjectOutputStream outStream = new ObjectOutputStream(fos);
        System.out.println("Writing primary key object to file...");
        outStream.writeObject(pk_1);
        outStream.flush();
        outStream.close();
        pk_1 = null;

        // Deserialize the primary key for cabin 101.
        FileInputStream fis = new FileInputStream("pk101.ser");
        ObjectInputStream inStream = new ObjectInputStream(fis);
        System.out.println("Reading primary key object from file...");
        Integer pk_2 = (Integer)inStream.readObject();
        inStream.close();

        // Re-obtain a remote reference to cabin 101 and read its name.
        System.out.println("Acquiring reference using deserialized primary key...");
        CabinRemote cabin_2 = home.findByPrimaryKey(pk_2);
        System.out.println("Retrieving name of Cabin using new remote reference...");
        System.out.println(cabin_2.getName());

    }

    static public Context getInitialContext() throws Exception {
        Properties p = new Properties();
        // Specify the JNDI properties specific to the vendor (and the version!)

        // 1. old JNP lookup stuff (JBoss AS 4.2)
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        return new InitialContext(p);
    }
}
