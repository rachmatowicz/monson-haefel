package com.titan.address;

import javax.ejb.CreateException;
import javax.ejb.EntityContext;

public abstract class AddressBean implements javax.ejb.EntityBean {

    private static final int IDGEN_START = (int)System.currentTimeMillis();
    private static int idgen = IDGEN_START;

    public Integer ejbCreateAddress(String street, String city, String state, String zip) throws CreateException {
        // generate the pk by hand
        setId(new Integer(idgen++));
        setStreet(street);
        setCity(city);
        setState(state);
        setZip(zip);
        return null;
    }

    public void ejbPostCreateAddress(String street, String city, String state, String zip) {
        // do nothing
    }

    // persistence fields

    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract String getStreet();
    public abstract void setStreet(String street);

    public abstract String getCity();
    public abstract void setCity(String city);

    public abstract String getState();
    public abstract void setState(String state);

    public abstract String getZip();
    public abstract void setZip(String zip);

    // standard callback methods

    public void setEntityContext(EntityContext entityContext) {
        // do nothing
    }
    public void unsetEntityContext()  {
        // do nothing
    }
    public void ejbRemove()  {
        // do nothing
    }
    public void ejbActivate() {
        // do nothing
    }
    public void ejbPassivate() {
        // do nothing
    }
    public void ejbLoad()  {
        // do nothing
    }
    public void ejbStore()  {
        // do nothing
    }
}
