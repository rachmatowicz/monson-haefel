package com.titan.clients;

import com.titan.customer.CustomerHomeRemote;
import com.titan.customer.CustomerRemote;
import com.titan.customer.Name;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Example showing use of dependency value classes
 */
public class Client_62 {

    public static void main(String [] args) throws Exception
    {
        // obtain CustomerHome
        Context jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("CustomerHomeRemote");
        CustomerHomeRemote home = (CustomerHomeRemote) javax.rmi.PortableRemoteObject.narrow(obj, CustomerHomeRemote.class);

        // create example customer
        Integer primaryKey = new Integer(1);
        Name name = new Name("Monson", "Richard");
        CustomerRemote customer = home.create(primaryKey);
        customer.setName(name);

        // find Customer by key
        customer = home.findByPrimaryKey(primaryKey);
        name = customer.getName();
        System.out.print(primaryKey+" = ");
        System.out.println(name.getFirstName( )+" "+ name.getLastName());

        // change customer's name
        name = new Name("Monson-Haefel", "Richard");
        customer.setName(name);
        name = customer.getName();
        System.out.print(primaryKey+" = ");
        System.out.println(name.getFirstName( )+" "+name.getLastName( ));

        // remove Customer to clean up
        customer.remove();
    }

    /*
     * THis depends on jndi.properties being on the classpath at runtime
     * This in turn depends on IntelliJ marking the jndi directory as a resources root
     */
    static public Context getInitialContext() throws Exception {
        return new InitialContext();
    }
}
