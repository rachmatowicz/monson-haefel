package com.titan.clients;

import com.titan.travelagent.TravelAgentHomeRemote;
import com.titan.travelagent.TravelAgentRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Properties;

public class Client_3 {
    public static int SHIP_ID = 1;
    public static int BED_COUNT = 3;

    public static void main(String[] args) {
        try {
            Context jndiContext = getInitialContext();

            Object ref = jndiContext.lookup("TravelAgentHomeRemote");
            TravelAgentHomeRemote home = (TravelAgentHomeRemote) PortableRemoteObject.narrow(ref, TravelAgentHomeRemote.class);

            TravelAgentRemote travelAgent = home.create();

            // Get a list of all cabins on ship 1 with bed count of 3
            String[] list = travelAgent.listCabins(SHIP_ID, BED_COUNT);

            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i]);
            }

        } catch(RemoteException re) {
            re.printStackTrace();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public static Context getInitialContext() throws NamingException {
        Properties p = new Properties();
        // Specify the JNDI properties specific to the vendor (and the version!)

        // 1. old JNP lookup stuff (JBoss AS 4.2)
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");

        return new InitialContext(p);
    }

}
