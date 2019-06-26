package com.titan.reservation;

import com.titan.cruise.CruiseLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public abstract class ReservationBean implements EntityBean {

    private static final int IDGEN_START = (int)System.currentTimeMillis();
    private static int idgen = IDGEN_START;

    // persistence fields

    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract float getAmountPaid();
    public abstract void setAmountPaid(float amt);

    public abstract Date getDate();
    public abstract void setDate(Date date);


    // persistence relationships

    public abstract void setCruise(CruiseLocal cruise);
    public abstract CruiseLocal getCruise();

    public abstract void setCustomers(Set customers);
    public abstract Set getCustomers();

    public abstract void setCabins(Set cabins);
    public abstract Set getCabins();

    // standard callback methods

    public Integer ejbCreate(CruiseLocal cruise, Collection customers) throws CreateException {
        System.out.println("ReservationBean: ejbCreate");
        setId(new Integer(idgen++));
        return null;
    }

    public void ejbPostCreate(CruiseLocal cruise, Collection customers) {
        System.out.println("ReservationBean: ejbCreate");
        // update the relation to point to the cruise accociated with this Reservation
        setCruise(cruise);
        // update the existing relation with the new customers we are adding
        Collection myCustomers = this.getCustomers();
        myCustomers.addAll(customers);
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
    }

    public void unsetEntityContext() throws EJBException {
    }

    public void ejbRemove() throws RemoveException, EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public void ejbLoad() throws EJBException {
    }

    public void ejbStore() throws EJBException {
    }
}
