package com.titan.phonenumber;

import javax.ejb.EJBLocalObject;

public interface PhoneLocal extends EJBLocalObject {
    final public static byte HOME_PHONE = (byte)1;
    final public static byte WORK_PHONE = (byte)2;
    final public static byte CELL_PHONE = (byte)3;

    public String getNumber();
    public void setNumber(String number);
    public byte getType();
    public void setType(byte type);

}
