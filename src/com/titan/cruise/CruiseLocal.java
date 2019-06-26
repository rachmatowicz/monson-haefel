package com.titan.cruise;

import com.titan.ship.ShipLocal;

import javax.ejb.EJBLocalObject;
import java.util.Collection;

public interface CruiseLocal extends EJBLocalObject {
    // business methods

    // getter/setter access methods to persistence fields

    public Integer getId();
    public void setId(Integer id);
    public String getName();
    public void setName(String name);

    // getter/setter access to relationship fields

    public ShipLocal getShip();
    public void setShip(ShipLocal ship);

    public Collection getReservations();
    public void setReservations(Collection res);

}
