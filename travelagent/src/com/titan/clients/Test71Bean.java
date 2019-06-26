package com.titan.clients;

import com.titan.address.AddressHomeLocal;
import com.titan.address.AddressLocal;
import com.titan.creditcard.CreditCardHomeLocal;
import com.titan.creditcard.CreditCardLocal;
import com.titan.customer.CustomerHomeLocal;
import com.titan.customer.CustomerLocal;
import com.titan.customer.Name;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class Test71Bean implements SessionBean {

    public String test_Address() {
        String output = null;
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        try
        {
            // obtain CustomerHome
            Context jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("CustomerHomeLocal");
            CustomerHomeLocal customerhome = (CustomerHomeLocal)obj;

            obj = jndiContext.lookup("AddressHomeLocal");
            AddressHomeLocal addresshome = (AddressHomeLocal)obj;

            out.println("Creating Customer 71");

            Integer primaryKey = new Integer(71);
            CustomerLocal customer = customerhome.create(primaryKey);
            customer.setName( new Name("Smith","John") );

            AddressLocal addr = customer.getHomeAddress();

            if (addr==null) {
                out.println("Address reference is NULL, Creating one and setting in Customer..");
                addr = addresshome.createAddress("333 North Washington","Minneapolis","MN","55401");
                customer.setHomeAddress(addr);
            }

            out.println("Address Info: "+addr.getStreet()+" "+addr.getCity()+", "+addr.getState()+" "+addr.getZip());

            out.println("Modifying Address through address reference");

            addr.setStreet("445 East Lake Street");
            addr.setCity("Wayzata");
            addr.setState("MN");
            addr.setZip("55432");

            out.println("Address Info: "+addr.getStreet()+" "+addr.getCity()+", "+addr.getState()+" "+addr.getZip());

            out.println("Creating New Address and calling setHomeAddress");

            addr = addresshome.createAddress("700 Main Street","St. Paul","MN","55302");
            out.println("Address Info: "+addr.getStreet()+" "+addr.getCity()+", "+addr.getState()+" "+addr.getZip());
            customer.setHomeAddress(addr);

            // Note: Original Address remains in database, orphaned by setHomeAddress call..

            out.println("Retrieving Address reference from Customer via getHomeAddress");

            addr = customer.getHomeAddress();
            out.println("Address Info: "+addr.getStreet()+" "+addr.getCity()+", "+addr.getState()+" "+addr.getZip());

            out.println("Setting Billing address to be the same as Home address.");
            customer.setBillingAddress(addr);

            out.println("Testing that Billing and Home Address are the same Entity.");

            AddressLocal billAddr = customer.getBillingAddress();
            AddressLocal homeAddr = customer.getHomeAddress();
            if (billAddr.isIdentical(homeAddr)) {
                out.println("Billing and Home are the same!");
            } else {
                out.println("Billing and Home are NOT the same! BUG IN JBOSS!");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(out);
        }

        out.close();
        output = writer.toString();

        return output;
    }

    public String test_CreditCard() {
        String output = null;
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        try
        {
            // obtain CustomerHome
            Context jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("CustomerHomeLocal");
            CustomerHomeLocal customerhome = (CustomerHomeLocal)obj;

            obj = jndiContext.lookup("CreditCardHomeLocal");
            CreditCardHomeLocal cardhome = (CreditCardHomeLocal)obj;

            out.println("Finding Customer 71");
            // Find Customer 71
            Integer primaryKey = new Integer(71);
            CustomerLocal customer = customerhome.findByPrimaryKey(primaryKey);

            out.println("Creating CreditCard");
            // set Credit Card info
            Calendar now = Calendar.getInstance();
            CreditCardLocal card = cardhome.create(now.getTime(), "370000000000001", "John Smith", "O'Reilly");

            out.println("Linking CreditCard and Customer");

            customer.setCreditCard(card);

            out.println("Testing both directions on relationship");

            String cardname = customer.getCreditCard().getNameOnCard();
            out.println("customer.getCreditCard().getNameOnCard()="+cardname);

            Name name = card.getCustomer().getName();
            String custfullname = name.getFirstName()+" "+name.getLastName();
            out.println("card.getCustomer().getName()="+custfullname);

            out.println("Unlink the beans using CreditCard, test Customer side");

            card.setCustomer(null);

            CreditCardLocal newcardref = customer.getCreditCard();
            if (newcardref == null)
            {
                out.println("Card is properly unlinked from customer bean");
            }
            else
            {
                out.println("Whoops, customer still thinks it has a card!");
            }
            out.close();
            output = writer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(out);
        }
        out.close();
        output = writer.toString();

        return output;
    }

    public String test_PhoneNumber() {
        String output = null;
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        try
        {
            // obtain CustomerHome
            Context jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("CustomerHomeLocal");
            CustomerHomeLocal home = (CustomerHomeLocal)obj;

            // Find Customer 71
            Integer primaryKey = new Integer(71);
            CustomerLocal customer = home.findByPrimaryKey(primaryKey);

            // Display current phone numbers and types
            out.println("Starting contents of phone list:");
            ArrayList vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }

            // add a new phone number
            out.println("Adding a new type 1 phone number..");
            customer.addPhoneNumber("612-555-1212",(byte)1);

            out.println("New contents of phone list:");
            vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }

            // add a new phone number
            out.println("Adding a new type 2 phone number..");
            customer.addPhoneNumber("800-333-3333",(byte)2);

            out.println("New contents of phone list:");
            vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }

            // update a phone number
            out.println("Updating type 1 phone numbers..");
            customer.updatePhoneNumber("763-555-1212",(byte)1);

            out.println("New contents of phone list:");
            vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }

            // delete a phone number
            out.println("Removing type 1 phone numbers from this Customer..");
            customer.removePhoneNumber((byte)1);

            out.println("Final contents of phone list:");
            vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }
            // Note that the phone is still in the database, but it is no longer related to this customer bean
            out.close();
            output = writer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(out);
        }

        out.close();
        output = writer.toString();

        return output;
   }

    public void ejbCreate() throws CreateException {
        // do nothing
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
        // do nothing
    }

    public void ejbRemove() throws EJBException {
        // do nothing
    }

    public void ejbActivate() throws EJBException {
        // do nothing
    }

    public void ejbPassivate() throws EJBException {
        // do nothing
    }

    /*
     * THis depends on jndi.properties being on the classpath at runtime
     * This in turn depends on IntelliJ marking the jndi directory as a resources root
     */
    public Context getInitialContext() throws Exception {
        return new InitialContext();
    }
}
