package com.titan.ship;

import javax.ejb.EJBLocalObject;

public interface ShipLocal extends EJBLocalObject {
    // business methods

    // getter/setter access methods to persistence fields

    public Integer getId();
    public void setId(Integer id);
    public String getName();
    public void setName(String name);
    public double getTonnage();
    public void setTonnage(double tonnage);
}
