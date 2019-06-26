package com.titan.creditcard;

import com.titan.customer.CustomerLocal;

import javax.ejb.EJBLocalObject;
import java.util.Date;

public interface CreditCardLocal extends EJBLocalObject {

    public Date getExpirationDate();
    public void setExpirationDate(Date date);
    public String getNumber();
    public void setNumber(String number);
    public String getNameOnCard();
    public void setNameOnCard(String name);
    public String getCreditOrganization();
    public void setCreditOrganization(String org);

    // persistance relationship accessor
    public CustomerLocal getCustomer();
    public void setCustomer(CustomerLocal customer);

}

