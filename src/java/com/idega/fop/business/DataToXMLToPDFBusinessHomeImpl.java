package com.idega.fop.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class DataToXMLToPDFBusinessHomeImpl extends IBOHomeImpl implements DataToXMLToPDFBusinessHome {

	public Class getBeanInterfaceClass() {
		return DataToXMLToPDFBusiness.class;
	}

	public DataToXMLToPDFBusiness create() throws CreateException {
		return (DataToXMLToPDFBusiness) super.createIBO();
	}
}