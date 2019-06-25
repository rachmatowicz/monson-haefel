package com.titan.address;

import javax.ejb.FinderException;

public interface AddressHomeLocal extends javax.ejb.EJBLocalHome {

    public AddressLocal createAddress(String street, String city, String state, String zip) throws javax.ejb.CreateException;
    public AddressLocal findByPrimaryKey(Integer primaryKey) throws FinderException;
}
