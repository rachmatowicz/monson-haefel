package com.titan.travelagent;

import com.titan.cabin.CabinHomeRemote;
import com.titan.cabin.CabinRemote;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Vector;

public class TravelAgentBean implements SessionBean {
    public TravelAgentBean() {
    }

    public void ejbCreate() throws CreateException {
        // do nothing
    }

    public String[] listCabins(int shipId, int bedCount) {
        try {
            javax.naming.Context jndiContext = new InitialContext();
            Object ref = jndiContext.lookup("java:comp/env/ejb/CabinHomeRemote");

            CabinHomeRemote home = (CabinHomeRemote) PortableRemoteObject.narrow(ref, CabinHomeRemote.class);

            Vector vect = new Vector();
            for (int i = 1; ; i++) {
                Integer pk = new Integer(i);
                CabinRemote cabin ;
                try {
                    cabin = home.findByPrimaryKey(pk);
                } catch(javax.ejb.FinderException fe) {
                    break ;
                }
                // Check to see if the bed count and ship id match
                if (cabin.getShipId() == shipId && cabin.getBedCount() == bedCount) {
                    String details = i + "," + cabin.getName() + "," + cabin.getDeckLevel() ;
                    vect.addElement(details);
                }
            }

            String [] list = new String[vect.size()];
            vect.copyInto(list);
            return list;

        } catch(Exception e) {
            throw new EJBException(e);
        }
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
        // do nothing
    }

    public void ejbRemove() throws EJBException {
        // do nothing
    }

    public void ejbActivate() throws EJBException {
        // do nothing
    }

    public void ejbPassivate() throws EJBException {
        // do nothing
    }
}
