package com.titan.clients;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Example showing use of accessing Customer CreditCard EJBs
 */
public class Client_71b {

    public static void main(String [] args) throws Exception
    {
        // obtain CustomerHomeRemote
        Context jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("Test71HomeRemote");
        Test71HomeRemote home = (Test71HomeRemote) PortableRemoteObject.narrow(obj, Test71HomeRemote.class);

        // create a Test71 session bean
        Test71Remote testBean = home.create();

        String output = testBean.test_CreditCard();
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
