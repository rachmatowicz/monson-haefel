package com.titan.creditcard;

import com.titan.customer.CustomerLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import java.util.Date;

public abstract class CreditCardBean implements EntityBean {

    private static final int IDGEN_START = (int)System.currentTimeMillis();
    private static int idgen = IDGEN_START;

    public Integer ejbCreate(Date exp, String numb, String name, String org) throws CreateException {
        // generate the pk by hand
        setId(new Integer(idgen++));
        // other fields are passed in
        setExpirationDate(exp);
        setNumber(numb);
        setNameOnCard(name);
        setCreditOrganization(org);
        return null;
    }

    public void ejbPostCreate(Date exp, String numb, String name, String org) throws CreateException {

    }

    // relationship fields
    public abstract CustomerLocal getCustomer();
    public abstract void setCustomer(CustomerLocal local);

    // persistence fields
    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract Date getExpirationDate();
    public abstract void setExpirationDate(Date date);

    public abstract String getNumber();
    public abstract void setNumber(String number);

    public abstract String getNameOnCard();
    public abstract void setNameOnCard(String name);

    public abstract String getCreditOrganization();
    public abstract void setCreditOrganization(String org);


    // standard callback methods
    public void setEntityContext(EntityContext entityContext) throws EJBException {
        // do nothing
    }

    public void unsetEntityContext() throws EJBException {
        // do nothing
    }

    public void ejbRemove() throws RemoveException, EJBException {
        // do nothing
    }

    public void ejbActivate() throws EJBException {
        // do nothing
    }

    public void ejbPassivate() throws EJBException {
        // do nothing
    }

    public void ejbLoad() throws EJBException {
        // do nothing
    }

    public void ejbStore() throws EJBException {
        // do nothing
    }
}
