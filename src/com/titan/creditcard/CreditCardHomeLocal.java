package com.titan.creditcard;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Date;

public interface CreditCardHomeLocal extends EJBLocalHome {
    public CreditCardLocal create(Date exp, String numb, String name, String org) throws CreateException ;
    public CreditCardLocal findByPrimaryKey(Integer key) throws FinderException;
}
