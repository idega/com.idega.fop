/*
 * $Id: SingleProperty.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import com.idega.fop.tools.EasyGenerationContentHandlerProxy;
import org.xml.sax.SAXException;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class SingleProperty implements Property {
	
	String key = null;
	
	String description = null;
	
	String value = null;
	
	
	public SingleProperty(String key, String description, String value) {
		this.key = key;
		this.description = description;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.output.model.Property#generateFor(org.xml.sax.ContentHandler)
	 */
	public void generateFor(EasyGenerationContentHandlerProxy contentHandler) throws SAXException {
		contentHandler.startElementLineBreak(PropertyConstants.PROPERTY);
		contentHandler.elementLineBreak(PropertyConstants.KEY, key);
		contentHandler.elementLineBreak(PropertyConstants.DESCRIPTION, description);
		contentHandler.elementLineBreak(PropertyConstants.VALUE, value);
		contentHandler.endELementLineBreak(PropertyConstants.PROPERTY);
	}
}
