package com.idega.fop.business;


import java.io.IOException;
import java.rmi.RemoteException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import com.idega.business.IBOService;

public interface DataToXMLToPDFBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.output.DataToXMLToPDFBusinessBean#convertToXML
	 */
	public void convertToXML() throws RemoteException, TransformerConfigurationException, TransformerException ;

	/**
	 * @see is.idega.nest.rafverk.output.DataToXMLToPDFBusinessBean#dataToXML
	 */
	public void dataToXML() throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.output.DataToXMLToPDFBusinessBean#xmlToPDF
	 */
	public void xmlToPDF() throws TransformerException, IOException, RemoteException;
}