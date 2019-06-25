package com.titan.clients;

import com.titan.customer.AddressDO;
import com.titan.customer.CustomerHomeRemote;
import com.titan.customer.CustomerRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Example showing use of accessing Customer Addresses
 */
public class Client_63 {

    public static void main(String [] args) throws Exception
    {
        // obtain CustomerHomeRemote
        Context jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("CustomerHomeRemote");
        CustomerHomeRemote home = (CustomerHomeRemote) PortableRemoteObject.narrow(obj, CustomerHomeRemote.class);

        // create a Customer
        Integer pk = new Integer(1);
        CustomerRemote customer = home.create(pk);

        // create an address and use the address to set the Address data on the bean
        AddressDO address = new AddressDO("1010 Colorado", "Austin", "TX", "78701");
        customer.setAddress(address);

        // print the address we juset set on the Customer
        address = customer.getAddress();
        System.out.println("Primary Key = " + pk);
        System.out.println(address.getStreet());
        System.out.println(address.getCity() + "," + address.getState() + " " + address.getZip());


        // create a new address and use the address to set the Address data on the bean
        address = new AddressDO("1600 Pennsylvania Avenue NW", "DC", "WA", "20500");
        customer.setAddress(address);

        // print the address we juset set on the Customer
        address = customer.getAddress();
        System.out.println("Primary Key = " + pk);
        System.out.println(address.getStreet());
        System.out.println(address.getCity() + "," + address.getState() + " " + address.getZip());

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
