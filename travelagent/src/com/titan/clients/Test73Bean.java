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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class Test73Bean implements SessionBean {

    private SessionContext context;

    public String test_Customer() throws RemoteException
    {
        String output = null;
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        try
        {
            // obtain Home interfaces
            Context jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("CustomerHomeLocal");
            CustomerHomeLocal customerhome = (CustomerHomeLocal)obj;

            obj = jndiContext.lookup("AddressHomeLocal");
            AddressHomeLocal addresshome = (AddressHomeLocal)obj;

            obj = jndiContext.lookup("CreditCardHomeLocal");
            CreditCardHomeLocal cardhome = (CreditCardHomeLocal)obj;

            out.println("Creating Customer 10078, Addresses, Credit Card, Phones");

            CustomerLocal customer = customerhome.create(new Integer(10078));
            customer.setName( new Name("Star","Ringo") );

            out.println("Creating CreditCard");

            // set Credit Card info
            Calendar now = Calendar.getInstance();
            CreditCardLocal card = cardhome.create(now.getTime(), "370000000000001", "Ringo Star", "Beatles");

            customer.setCreditCard(card);

            out.println("customer.getCreditCard().getName()="+customer.getCreditCard().getNameOnCard());

            out.println("Creating Address");

            AddressLocal addr = addresshome.createAddress("780 Main Street","Beverly Hills","CA","90210");

            customer.setHomeAddress(addr);

            out.println("Address Info: "+addr.getStreet()+" "+addr.getCity()+", "+addr.getState()+" "+addr.getZip());

            out.println("Creating Phones");


            out.println("Adding a new type 1 phone number..");
            customer.addPhoneNumber("612-555-1212",(byte)1);
            out.println("Adding a new type 2 phone number.");
            customer.addPhoneNumber("888-555-1212",(byte)2);

            out.println("New contents of phone list:");
            List vv = customer.getPhoneList();
            for (int jj=0; jj<vv.size(); jj++)
            {
                String ss = (String)(vv.get(jj));
                out.println(ss);
            }

            out.println("Removing Customer EJB only");
            customer.remove();

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
        context = sessionContext;
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
