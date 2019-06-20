package com.titan.cabin;

import javax.ejb.EntityContext;

public abstract class CabinBean implements javax.ejb.EntityBean {

    // added by RA
    private static final long serialVersionUID = 1113799434508676095L;

    public Integer ejbCreate(Integer id) throws javax.ejb.CreateException {
        this.setId(id);
        return null;
    }

    public void ejbPostCreate(Integer id) {
        // no implementation
    }

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
