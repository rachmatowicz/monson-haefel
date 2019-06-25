package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.travelagent.TravelAgentHomeRemote;
import com.titan.travelagent.TravelAgentRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

public class Client_51 {

    public static void main(String[] args) {

        try {
            Context jndiContext = getInitialContext();

            // Obtain a list of all the cabins for ship 1 with bed count of 3.
            Object ref = jndiContext.lookup("TravelAgentHomeRemote");
            TravelAgentHomeRemote agentHome = (TravelAgentHomeRemote) PortableRemoteObject.narrow(ref, TravelAgentHomeRemote.class);

            TravelAgentRemote agent = agentHome.create();
            String list[] = agent.listCabins(1, 3);

            System.out.println("1st List: Before deleting cabin number 30");
            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i]);
            }

            // Obtain the home and remove cabin 30. Rerun the same cabin list.
            ref = jndiContext.lookup("CabinHomeRemote");
            CabinHomeRemote c_home = (CabinHomeRemote) PortableRemoteObject.narrow(ref, CabinHomeRemote.class);

            Integer pk = new Integer(30);
            c_home.remove(pk);

            list = agent.listCabins(1, 3);
            System.out.println("2nd List: After deleting cabin number 30");
            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i]);
            }

        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
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
