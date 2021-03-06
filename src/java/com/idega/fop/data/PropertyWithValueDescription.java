/*
 * $Id: PropertyWithValueDescription.java,v 1.4 2007/12/03 15:07:21 laddi Exp $
 * Created on Apr 14, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import com.idega.fop.visitor.PropertyVisitor;

/**
 * 
 * Last modified: $Date: 2007/12/03 15:07:21 $ by $Author: laddi $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class PropertyWithValueDescription extends PropertyImpl {

	String valueDescription = null;

	public PropertyWithValueDescription(String key, String description, String value, String valueDescription) {
		super(key, description, value);
		this.valueDescription = valueDescription;
	}

	public Object accept(PropertyVisitor propertyVisitor) {
		return propertyVisitor.visit(this);
	}

	public String getType() {
		return PropertyConstants.PROPERTY_WITH_VALUE_DESCRIPTION;
	}

	/**
	 * @return the valueDescription
	 */
	public String getValueDescription() {
		return valueDescription;
	}

	/**
	 * @param valueDescription
	 *          the valueDescription to set
	 */
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}

}
