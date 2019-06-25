package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.travelagent.TravelAgentHomeRemote;
import com.titan.travelagent.TravelAgentRemote;

import javax.ejb.EJBMetaData;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Demonstrates how to recover out of scope home interface and use it to get information aboyut the bean via EJBMetaData
 */
public class Client_52a {

    public static void main(String [] args)
    {
        try {
            Context jndiContext = getInitialContext();
            // lookup the Remote Client API for the TravelAgentEJB
            Object ref = jndiContext.lookup("TravelAgentHomeRemote");
            TravelAgentHomeRemote home = (TravelAgentHomeRemote) PortableRemoteObject.narrow(ref,TravelAgentHomeRemote.class);

            // Get a remote reference to the bean (EJB object).
            TravelAgentRemote agent = home.create();
            // Pass the remote reference to some method and so lose the original lookup context
            getTheEJBHome(agent);

        } catch (java.rmi.RemoteException re) {
            re.printStackTrace();
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static void getTheEJBHome(TravelAgentRemote agent) throws RemoteException {

        // The home interface is out of scope in this method, so it must be obtained from the EJB object.
        // The return value for the method is EJBHome, so we still need to cast/narrow the reference we receive
        Object ref = agent.getEJBHome();
        TravelAgentHomeRemote home = (TravelAgentHomeRemote) PortableRemoteObject.narrow(ref,TravelAgentHomeRemote.class);

        // Do something useful with the home interface
        EJBMetaData meta = home.getEJBMetaData();
        System.out.println("EJBMetadata: remote home interface = " + meta.getHomeInterfaceClass().getName());
        System.out.println("EJBMetadata: remote interface = " + meta.getRemoteInterfaceClass().getName());
        System.out.println("EJBMetadata: is session bean? = " + meta.isSession());
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
