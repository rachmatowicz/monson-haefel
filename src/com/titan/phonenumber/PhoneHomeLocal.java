package com.titan.phonenumber;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface PhoneHomeLocal extends EJBLocalHome {
    PhoneLocal create(String number, byte type) throws CreateException;
    PhoneLocal findByPrimaryKey(Integer key) throws FinderException;
}
