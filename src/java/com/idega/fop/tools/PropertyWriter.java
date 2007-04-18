/*
 * $Id: PropertyWriter.java,v 1.1 2007/04/18 17:53:47 thomas Exp $
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
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.Driver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.idega.core.idgenerator.business.UUIDGenerator;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyInputSource;
import com.idega.idegaweb.IWBundle;
import com.idega.io.serialization.Storable;
import com.idega.io.serialization.WriterToFile;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:53:47 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class PropertyWriter extends WriterToFile {
	
	public static final String PDF_RENDERER = "pdf";
	
	public static final String TXT_RENDERER = "txt";
	
	public static final String XML_DATA_RENDERER = "xml";
	
	private static final String BUNDLE_IDENTIFIER = "com.idega.fop";
	
	private File xslFile = null; 
	
	private String renderer = null;	
	
	public PropertyWriter( IWContext iwc) {
		super(iwc);
		initialize();
	}
	
	public PropertyWriter(Storable storable, IWContext iwc) {
		super(storable, iwc);
		initialize();
	}
	
	private void initialize() {
		IWBundle bundle = iwc.getApplicationContext().getIWMainApplication().getBundle(BUNDLE_IDENTIFIER);
		File resourceFile = new File(bundle.getResourcesRealPath());
		xslFile = new File(resourceFile, "default.xsl");
		renderer = PDF_RENDERER;
	}

	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#createContainer()
	 */
	public String createContainer() throws IOException {
		String folderIdentifier = UUIDGenerator.getInstance().generateUUID();
		Property property = (Property) storable;
		// name of the outputfile
		String name = property.getKey();
		String outputFilePath = getRealPathToFile(name, getRenderer(), folderIdentifier);
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
		return getURLToFile(name, getRenderer(), folderIdentifier);
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

//	public void convertToXML() throws TransformerFactoryConfigurationError, TransformerException {
//		Property property = Test.getTestInstanceOfAProperty();
//	    //Setup XSLT
//	    TransformerFactory factory = TransformerFactory.newInstance();
//	    Transformer transformer = factory.newTransformer();
//	    
//	    /* Note:
//	       We use the identity transformer, no XSL transformation is done.
//	       The transformer is basically just used to serialize the 
//	       generated document to XML. */
//	
//	    //Setup input
//	    XMLReader xmlReader = new PropertyXMLReader();
//	    InputSource inputSource = new PropertyInputSource(property); 
//	    Source src = new SAXSource( xmlReader, inputSource);
//	
//	    File outputFile = new File(getIWMainApplication().getApplicationSpecialRealPath(), "nestOut4.xml");
//	    //Setup output
//	    Result res = new StreamResult(outputFile);
//	
//	    //Start XSLT transformation
//	    transformer.transform(src, res);
//	}
	
	
	/* (non-Javadoc)
	 * @see com.idega.io.serialization.WriterToFile#writeData(java.io.OutputStream)
	 */
	public OutputStream writeData(OutputStream destination) throws IOException {

		
		
	    //Setup input
        Property property = (Property) storable;
	    XMLReader xmlReader = new PropertyXMLReader();
	    InputSource inputSource = new PropertyInputSource(property); 
	    
	    Source src = new SAXSource( xmlReader, inputSource);
        
        //Setup input for XSLT transformation
        //Source src = new StreamSource(inputFile);
    
        //Resulting SAX events (the generated FO) must be piped through to FOP
  
	    Result res = null;
	    Transformer transformer = null;
	    TransformerFactory factory = TransformerFactory.newInstance();
	    if (XML_DATA_RENDERER.equals(getRenderer())) {
	    	res = new StreamResult(destination);
	    	transformer = getIdentityTransformer(factory);
	    }
	    else {
	    	res = getResult(destination);
	    	transformer = getTransformer(factory);
	    }
        
        //Start XSLT transformation and FOP processing
        try {
			transformer.transform(src, res);
		}
		catch (TransformerException e) {
			e.printStackTrace();
			throw new IOException("[PropertyWriter] Error during transformation. " + e.getMessage());
		}
        return destination;

	}
	
	private Result getResult(OutputStream destination) {
	    Driver driver = new Driver();
	    
	    //Setup logger
	    //Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);
	    //driver.setLogger(logger);
	    //MessageHandler.setScreenLogger(logger);
	
	    //Setup Renderer (output format)
	    int driverType = Driver.RENDER_TXT;
	    if (PDF_RENDERER.equals(getRenderer())) {
	    	driverType = Driver.RENDER_PDF;
	    }
	    driver.setRenderer(driverType);    
	    //Setup output
	    driver.setOutputStream(destination);
	    //Resulting SAX events (the generated FO) must be piped through to FOP

	    return new SAXResult(driver.getContentHandler());
	}
	
	private Transformer getIdentityTransformer(TransformerFactory factory) throws IOException {
	    Transformer transformer;
		try {
			transformer = factory.newTransformer();
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new IOException("[PropertyWriter] Error during configuration of identity transformer. " + e.getMessage());
		}
		return transformer;
	}
	
	private Transformer getTransformer(TransformerFactory factory) throws IOException {
	    //Setup XSLT

	    Transformer transformer;
		try {
			transformer = factory.newTransformer(new StreamSource(xslFile));
		}
		catch (TransformerConfigurationException e) {
			String filePath = (xslFile == null) ? "xslFile is NULL" : ("xslFile '" +xslFile.getCanonicalPath()); 
			e.printStackTrace();
			throw new IOException("[PropertyWriter] Error during configuration of transformer. " + filePath + e.getMessage());
		}
		return transformer;
	}
		
	public File getXslFile() {
		return xslFile;
	}

	
	public void setXslFile(File xslFile) {
		this.xslFile = xslFile;
	}

	
	/**
	 * @return the renderer
	 */
	public String getRenderer() {
		return renderer;
	}

	
	/**
	 * @param renderer the renderer to set
	 */
	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}
}
