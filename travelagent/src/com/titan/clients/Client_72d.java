package com.titan.clients;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Example showing use of accessing Customer PhoneNumber EJBs
 */
public class Client_72d {

    public static void main(String [] args) throws Exception
    {
        // obtain CustomerHomeRemote
        Context jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("Test72HomeRemote");
        Test72HomeRemote home = (Test72HomeRemote) PortableRemoteObject.narrow(obj, Test72HomeRemote.class);

        // create a Test71 session bean
        Test72Remote testBean = home.create();

        String output = testBean.test_Customers();
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
