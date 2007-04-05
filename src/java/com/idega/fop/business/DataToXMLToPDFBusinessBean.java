/*
 * $Id: DataToXMLToPDFBusinessBean.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 2, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.business;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.Driver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.idega.business.IBOServiceBean;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyInputSource;
import com.idega.fop.data.Test;
import com.idega.fop.tools.PropertyXMLReader;
import com.idega.util.xml.XMLData;
import com.idega.xml.XMLDocument;
import com.idega.xml.XMLElement;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class DataToXMLToPDFBusinessBean extends IBOServiceBean implements DataToXMLToPDFBusiness {
	
	public void convertToXML() throws TransformerFactoryConfigurationError, TransformerException {
		Property property = Test.getTestInstanceOfAProperty();
	    //Setup XSLT
	    TransformerFactory factory = TransformerFactory.newInstance();
	    Transformer transformer = factory.newTransformer();
	    
	    /* Note:
	       We use the identity transformer, no XSL transformation is done.
	       The transformer is basically just used to serialize the 
	       generated document to XML. */
	
	    //Setup input
	    XMLReader xmlReader = new PropertyXMLReader();
	    InputSource inputSource = new PropertyInputSource(property); 
	    Source src = new SAXSource( xmlReader, inputSource);
	
	    File outputFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nestOut4.xml");
	    //Setup output
	    Result res = new StreamResult(outputFile);
	
	    //Start XSLT transformation
	    transformer.transform(src, res);
	}
	
	
	public void dataToXML() throws IOException {
		XMLData xmlData = XMLData.getInstanceWithoutExistingFileSetNameSetRootName("hello", "weser");
		XMLDocument xmlDocument = xmlData.getDocument();
		XMLElement root =  xmlDocument.getRootElement();
		root.addContent("name", "Thomas");
		File outputFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nest.xml");
		xmlData.writeToFile(outputFile);
		try {
			xmlToPDF();
		}
		catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void xmlToPDF() throws TransformerException, IOException {
		File inputFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nest1.xml");
		File outputFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nest42.txt");
		File nestXSLTFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nest.xsl");
		
        Driver driver = new Driver();
        
        //Setup logger
        //Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);
        //driver.setLogger(logger);
        //MessageHandler.setScreenLogger(logger);

        //Setup Renderer (output format)        
        driver.setRenderer(Driver.RENDER_TXT);
        
        //Setup output
        OutputStream out = new java.io.FileOutputStream(outputFile);
        try {
            driver.setOutputStream(out);

            //Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(nestXSLTFile));
        
    	    //Setup input
            Property property = Test.getTestInstanceOfAProperty();
    	    XMLReader xmlReader = new PropertyXMLReader();
    	    InputSource inputSource = new PropertyInputSource(property); 
    	    Source src = new SAXSource( xmlReader, inputSource);

            
            //Setup input for XSLT transformation
            //Source src = new StreamSource(inputFile);
        
            //Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(driver.getContentHandler());

            //Start XSLT transformation and FOP processing
            transformer.transform(src, res);
        } finally {
            out.close();
        }
	}
	
}
