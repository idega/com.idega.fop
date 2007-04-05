/*
 * $Id: XSLWriter.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 4, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.tools;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.Driver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.idega.core.idgenerator.business.UUIDGenerator;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyInputSource;
import com.idega.fop.data.Test;
import com.idega.io.serialization.Storable;
import com.idega.io.serialization.WriterToFile;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class XSLWriter extends WriterToFile {
	
	private File xslFile = null; 
	
	public XSLWriter( IWContext iwc) {
		super(iwc);
	}
	
	public XSLWriter(Storable storable, IWContext iwc) {
		super(storable, iwc);
	}

	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#createContainer()
	 */
	public String createContainer() throws IOException {
		String folderIdentifier = UUIDGenerator.getInstance().generateUUID();
		String outputFilePath = getRealPathToFile("nest","pdf", folderIdentifier);
		File outputFile = new File(outputFilePath);
        OutputStream destination = new java.io.FileOutputStream(outputFile);
		try {
			writeData(destination);
		}
		finally {
			try {
				destination.close();
			}
			catch (IOException ex) {
				// do not hide existing exceptions
			}
		}
		return getURLToFile("nest", "pdf", folderIdentifier);
	}

	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#getMimeType()
	 */
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#writeData(java.io.OutputStream)
	 */
	public OutputStream writeData(OutputStream destination) throws IOException {
        Driver driver = new Driver();
        
        //Setup logger
        //Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);
        //driver.setLogger(logger);
        //MessageHandler.setScreenLogger(logger);

        //Setup Renderer (output format)        
        driver.setRenderer(Driver.RENDER_TXT);
        
        //Setup output


            driver.setOutputStream(destination);

        //Setup XSLT
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer;
		try {
			transformer = factory.newTransformer(new StreamSource(xslFile));
		}
		catch (TransformerConfigurationException e) {
			String filePath = (xslFile == null) ? "xslFile is NULL" : ("xslFile '" +xslFile.getCanonicalPath()); 
			e.printStackTrace();
			throw new IOException("[XSLWriter] Error during configuration of transformer. " + filePath + e.getMessage());
		}
    
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
        try {
			transformer.transform(src, res);
		}
		catch (TransformerException e) {
			e.printStackTrace();
			throw new IOException("[XSLWriter] Error during transformation. " + e.getMessage());
		}
        return destination;

	}
}
