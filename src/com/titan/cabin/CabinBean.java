package com.titan.cabin;

import com.titan.ship.ShipLocal;

import javax.ejb.CreateException;
import javax.ejb.EntityContext;

public abstract class CabinBean implements javax.ejb.EntityBean {

    // persistence fields

    public abstract void setId(Integer id);
    public abstract Integer getId();
    public abstract void setShipId(int ship);
    public abstract int getShipId();
    public abstract void setName(String name);
    public abstract String getName();
    public abstract void setBedCount(int bedCount);
    public abstract int getBedCount();
    public abstract void setDeckLevel(int level);
    public abstract int getDeckLevel();

    // relationship fields
    public abstract void setShip(ShipLocal ship);
    public abstract ShipLocal getShip();

    // standard callback methods

    public Integer ejbCreate(Integer id) throws javax.ejb.CreateException {
        this.setId(id);
        return null;
    }

    public void ejbPostCreate(Integer id) {
        // no implementation
    }

    public Integer ejbCreate(ShipLocal ship, String name) throws CreateException {
        this.setName(name);
        return null;
    }

    public void ejbPostCreate(ShipLocal ship, String name) {
        this.setShip(ship);
    }

    public void setEntityContext(EntityContext ctx) {
        // empty implementation
    }
    public void unsetEntityContext() {
        // empty implementation
    }
    public void ejbActivate() {
        // empty implementation
    }
    public void ejbPassivate() {
        // empty implementation
    }
    public void ejbLoad() {
        // empty implementation
    }
    public void ejbStore() {
        // empty implementation
    }
    public void ejbRemove() {
        // empty implementation
    }
}
