package com.titan.clients;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Properties;

public class Client_1 {

    public static void main(String[] args) {
        try {
            Context jndiContext = getInitialContext() ;
            Object ref = jndiContext.lookup("CabinHomeRemote");
            CabinHomeRemote home = (CabinHomeRemote) PortableRemoteObject.narrow(ref, CabinHomeRemote.class);

            CabinRemote cabin_1 = home.create(new Integer(1));
            cabin_1.setName("Master Suite");
            cabin_1.setDeckLevel(1);
            cabin_1.setShipId(1);
            cabin_1.setBedCount(3);

            Integer pk = new Integer(1);

            CabinRemote cabin_2 = home.findByPrimaryKey(pk);
            System.out.println(cabin_2.getName());
            System.out.println(cabin_2.getDeckLevel());
            System.out.println(cabin_2.getShipId());
            System.out.println(cabin_2.getBedCount());

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

        // 1. old JNP lookup stuff (JBoss AS 4.2)
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
}
