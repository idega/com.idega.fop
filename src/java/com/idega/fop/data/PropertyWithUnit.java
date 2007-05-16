/*
 * $Id: PropertyWithUnit.java,v 1.3 2007/05/16 15:57:02 thomas Exp $
 * Created on Apr 11, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import org.xml.sax.SAXException;
import com.idega.fop.tools.EasyGenerationContentHandlerProxy;
import com.idega.fop.visitor.PropertyVisitor;


/**
 * 
 * Represents things like: "Staerd 300 A"
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class PropertyWithUnit extends PropertyImpl {
	
	private String unit = null; 
	
	public PropertyWithUnit(String key, String description, String value, String unit) {
		super(key, description, value);
		this.unit = unit;
	}
	
	public Object accept(PropertyVisitor propertyVisitor)  {
		return propertyVisitor.visit(this);
	}

	public String getType() {
		return PropertyConstants.PROPERTY_WITH_UNIT;
	}
	
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
