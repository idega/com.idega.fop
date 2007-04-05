/*
 * $Id: PropertyXMLReader.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.tools;

import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyConstants;
import com.idega.fop.data.PropertyInputSource;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class PropertyXMLReader extends AbstractObjectReader {

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.output.tools.AbstractObjectReader#parse(org.xml.sax.InputSource)
	 */
	public void parse(InputSource input) throws IOException, SAXException {
        if (input instanceof PropertyInputSource) {
            parse(((PropertyInputSource)input).getProperty());
        } else {
            throw new SAXException("Unsupported InputSource specified. Must be a ProjectTeamInputSource");
        }
		// TODO Auto-generated method stub
	}
	
	public void parse(Property property) throws SAXException {
        if (property == null) {
            throw new NullPointerException("Parameter projectTeam must not be null");
        }
        if (handler == null) {
            throw new IllegalStateException("ContentHandler not set");
        }
        //Start the document
        handler.startDocument();
        
        handler.startElementLineBreak(PropertyConstants.ROOT);
        
        //Generate SAX events for the ProjectTeam
        property.generateFor(handler);
        
        handler.endELementLineBreak(PropertyConstants.ROOT);
        
        //End the document
        handler.endDocument(); 
	}
}
