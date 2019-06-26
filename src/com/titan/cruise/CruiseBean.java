package com.titan.cruise;

import com.titan.ship.ShipLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import java.util.Collection;

public abstract class CruiseBean implements EntityBean {

    // keep a running primary key generator
    private static final int IDGEN_START = (int)System.currentTimeMillis();
    private static int idgen = IDGEN_START;

    // persistence fields
    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract String getName();
    public abstract void setName(String name);

    // relationship fields

    public abstract ShipLocal getShip();
    public abstract void setShip(ShipLocal ship);

    public abstract Collection getReservations();
    public abstract void setReservations(Collection res);

    // standard callback methods

    public Integer ejbCreate(String name, ShipLocal ship) throws CreateException {
        System.out.println("CruiseBean: ejbCreate");

        setId(new Integer(idgen++));
        setName(name);
        return null;
    }

    public void ejbPostCreate(String name, ShipLocal ship) {
        System.out.println("CruiseBean: ejbPostCreate");
        setShip(ship);
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
