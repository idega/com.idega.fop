/*
 * $Id: PropertyWithValueDescription.java,v 1.1 2007/04/18 17:53:47 thomas Exp $
 * Created on Apr 14, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import org.xml.sax.SAXException;
import com.idega.fop.tools.EasyGenerationContentHandlerProxy;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:53:47 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class PropertyWithValueDescription extends PropertyImpl {
	
	String valueDescription = null;

	public PropertyWithValueDescription(String key, String description, String value, String valueDescription) {
		super(key, description, value);
		this.valueDescription = valueDescription;
	}

	protected String getType() {
		return PropertyConstants.PROPERTY_WITH_VALUE_DESCRIPTION;
	}
	
	protected void addContent(EasyGenerationContentHandlerProxy contentHandler) throws SAXException {
		super.addContent(contentHandler);
		contentHandler.elementLineBreak(PropertyConstants.VALUE_DESCRIPTION, valueDescription);
	}
	
	/**
	 * @return the valueDescription
	 */
	public String getValueDescription() {
		return valueDescription;
	}

	
	/**
	 * @param valueDescription the valueDescription to set
	 */
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}

}
