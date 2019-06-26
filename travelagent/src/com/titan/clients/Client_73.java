package com.titan.clients;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Example showing use of accessing Customer PhoneNumber EJBs
 */
public class Client_73 {

    public static void main(String [] args) throws Exception
    {
        // obtain CustomerHomeRemote
        Context jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("Test73HomeRemote");
        Test73HomeRemote home = (Test73HomeRemote) PortableRemoteObject.narrow(obj, Test73HomeRemote.class);

        // create a Test71 session bean
        Test73Remote testBean = home.create();

        String output = testBean.test_Customer();
        System.out.println(output);
   }

    /*
     * THis depends on jndi.properties being on the classpath at runtime
     * This in turn depends on IntelliJ marking the jndi directory as a resources root
     */
    static public Context getInitialContext() throws Exception {
        return new InitialContext();
    }
}
