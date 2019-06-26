package com.titan.ship;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class ShipBean implements EntityBean {

    // persistence fields

    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract double getTonnage();
    public abstract void setTonnage(double tonnage);


    // standard callback methods

    public Integer ejbCreate(Integer primaryKey, String name, double tonnage) throws CreateException {
        setId(primaryKey);
        setName(name);
        setTonnage(tonnage);
        return null;
    }

    public void ejbPostCreate(Integer primaryKey, String name, double tonnage) {
        // do nothing
    }

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
