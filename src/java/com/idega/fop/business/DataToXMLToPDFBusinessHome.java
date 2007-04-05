package com.idega.fop.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface DataToXMLToPDFBusinessHome extends IBOHome {

	public DataToXMLToPDFBusiness create() throws CreateException, RemoteException;
}