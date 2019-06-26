package com.titan.phonenumber;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class PhoneBean implements javax.ejb.EntityBean {

    private static final int IDGEN_START = (int)System.currentTimeMillis();
    private static int idgen = IDGEN_START;

    public Integer ejbCreate(String number, byte type) throws CreateException {
        System.out.println("ejbCreate");
        setId(new Integer(idgen++));
        setNumber(number);
        setType(type);
        return null;
    }

    public void ejbPostCreate(String number, byte type) {
        System.out.println("ejbPostCreate");
        // do nothing
    }

    // persistence fields

    public abstract Integer getId();
    public abstract void setId(Integer pk);

    public abstract String getNumber();
    public abstract void setNumber(String number);

    public abstract byte getType();
    public abstract void setType(byte type);

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
