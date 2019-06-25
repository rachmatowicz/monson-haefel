package com.titan.customer;

import com.titan.address.AddressHomeLocal;
import com.titan.address.AddressLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class CustomerBean implements javax.ejb.EntityBean {

    public Integer ejbCreate(Integer id) throws CreateException {
        setId(id);
        return null;
    }

    public void ejbPostCreate(Integer id) {
    }

    // business method
    public Name getName() {
        Name name = new Name(getLastName(), getFirstName()) ;
        return name;
    }

    public void setName(Name name) {
        setLastName(name.getLastName());
        setFirstName(name.getFirstName());
    }

    // business method
    public AddressDO getAddress() {
        AddressLocal addrLocal = getHomeAddress();
        if (addrLocal == null) {
            return null;
        }
        String street = addrLocal.getStreet();
        String city = addrLocal.getCity();
        String state = addrLocal.getState();
        String zip = addrLocal.getZip();
        AddressDO addrValue = new AddressDO(street, city, state, zip);
        return addrValue;
    }

    public void setAddress(AddressDO addrValue) {
        String street = addrValue.getStreet();
        String city = addrValue.getCity();
        String state = addrValue.getState();
        String zip = addrValue.getZip();

        AddressLocal addr = getHomeAddress();

        try {
            if (addr == null) {
                // Customer doesn't have an address yet
                InitialContext cxt = new InitialContext();
                AddressHomeLocal addrHome = (AddressHomeLocal) cxt.lookup("AddressHomeLocal");
//              AddressHomeLocal addrHome = (AddressHomeLocal) cxt.lookup("java:comp/env/ejb/AddressHomeLocal");
                addr = addrHome.createAddress(street, city, state, zip);
                this.setHomeAddress(addr);
            } else {
                // Customer already has an address. Change its fields.
                addr.setStreet(street);
                addr.setCity(city);
                addr.setState(state);
                addr.setZip(zip);
            }
        } catch(NamingException ne) {
            throw new EJBException(ne);
        } catch (CreateException ce) {
            throw new EJBException(ce);
        }
    }

    // business method
    public void setAddress(String street, String city, String state, String zip) {
        try {
            AddressLocal addr = this.getHomeAddress();
            if (addr == null) {
                // Customer doesn't have an address yet
                InitialContext cxt = new InitialContext();
                AddressHomeLocal addrHome = (AddressHomeLocal) cxt.lookup("AddressHomeLocal");
//              AddressHomeLocal addrHome = (AddressHomeLocal) cxt.lookup("java:comp/env/ejb/AddressHomeLocal");
                addr = addrHome.createAddress(street, city, state, zip);
                this.setHomeAddress(addr);
            } else {
                // Customer already has an address. Change its fields.
                addr.setStreet(street);
                addr.setCity(city);
                addr.setState(state);
                addr.setZip(zip);
            }
        } catch(Exception e) {
            throw new EJBException(e);
        }
    }

    // persistence relationship between Customer and Address
    public abstract AddressLocal getHomeAddress() ;
    public abstract void setHomeAddress(AddressLocal address);

    // persistence fields
    public abstract boolean getHasGoodCredit();
    public abstract void setHasGoodCredit(boolean creditRating);

    // abstract accessor methods
    public abstract Integer getId();
    public abstract void setId(Integer id);

    public abstract String getLastName();
    public abstract void setLastName(String lname);

    public abstract String getFirstName();
    public abstract void setFirstName(String fname);

    // standard callback methods
    public void setEntityContext(EntityContext entityContext) throws EJBException { }

    public void unsetEntityContext() throws EJBException { }

    public void ejbRemove() throws RemoveException, EJBException { }

    public void ejbActivate() throws EJBException { }

    public void ejbPassivate() throws EJBException { }

    public void ejbLoad() throws EJBException { }

    public void ejbStore() throws EJBException { }
}
