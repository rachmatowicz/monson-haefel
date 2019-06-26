package com.titan.reservation;

import com.titan.cruise.CruiseLocal;

import javax.ejb.EJBLocalObject;
import java.util.Date;
import java.util.Set;

public interface ReservationLocal extends EJBLocalObject {

    // getter/setter for persistence fields

    public float getAmountPaid();
    public void setAmountPaid(float amt);

    public Date getDate();
    public void setDate(Date date);

    // getters / setters for reltaionship fields

    public CruiseLocal getCruise();
    public void setCruise(CruiseLocal cruise);

    public Set getCabins( );
    public void setCabins(Set customers);

    public Set getCustomers( );
    public void setCustomers(Set customers);

}

