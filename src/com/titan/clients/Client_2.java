package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Properties;

public class Client_2 {

    public static void main(String[] args) {
        try {
            Context jndiContext = getInitialContext() ;
            Object ref = jndiContext.lookup("CabinHomeRemote");
            CabinHomeRemote home = (CabinHomeRemote) PortableRemoteObject.narrow(ref, CabinHomeRemote.class);

            // Add 9 cabins to deck 1 of ship 1.
            makeCabins(home, 2, 10, 1, 1);
            // Add 10 cabins to deck 2 of ship 1.
            makeCabins(home, 11, 20, 2, 1);
            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 21, 30, 3, 1);

            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 31, 40, 1, 2);
            // Add 10 cabins to deck 2 of ship 1.
            makeCabins(home, 41, 50, 2, 2);
            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 51, 60, 3, 2);

            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 61, 70, 1, 3);
            // Add 10 cabins to deck 2 of ship 1.
            makeCabins(home, 71, 80, 2, 3);
            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 81, 90, 3, 3);
            // Add 10 cabins to deck 1 of ship 1.
            makeCabins(home, 91, 100, 4, 3);

            for (int i = 1; i < 100; i++) {
                Integer pk = new Integer(i);
                CabinRemote cabin = home.findByPrimaryKey(pk);
                System.out.println("PK = " + i + ", Ship = " +  cabin.getShipId() +
                        ", Deck = " + cabin.getDeckLevel() +
                        ", Bed Count = " + cabin.getBedCount() +
                        ", Name = " + cabin.getName());
            }

        } catch(RemoteException re) {
            re.printStackTrace();
        } catch(NamingException ne) {
            ne.printStackTrace();
        } catch(CreateException ce) {
            ce.printStackTrace();
        } catch(FinderException fe) {
            fe.printStackTrace();
        }
    }

    public static Context getInitialContext() throws NamingException {
        Properties p = new Properties();
        // Specify the JNDI properties specific to the vendor (and the version!)

        // 1. old JNP lookup stuff
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");

        // 2. remote naming access to JNDI
        // need to bring in client jars specifically
        // p.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
        // p.put(Context.PROVIDER_URL, "remote://localhost:4447");
        // p.put(Context.PROVIDER_URL, "remote://localhost:4447");
        // p.put(Context.SECURITY_PRINCIPAL, "fred");
        // p.put(Context.SECURITY_CREDENTIALS, "barney");

        // 3. HTTP override access to JNDI


        return new InitialContext(p);
    }

    public static void makeCabins(CabinHomeRemote home, int fromId, int toId, int deckLevel, int shipNumber) throws RemoteException, CreateException {
        int bc = 3;

        for (int i = fromId; i <= toId; i++) {
            CabinRemote cabin = home.create(new Integer(i));
            int suiteNumber = deckLevel * 100 + (i - fromId);
            cabin.setName("Suite " + suiteNumber);
            cabin.setDeckLevel(deckLevel);
            bc = (bc == 3) ? 2 : 3;
            cabin.setBedCount(bc);
            cabin.setShipId(shipNumber);
        }
    }
}
