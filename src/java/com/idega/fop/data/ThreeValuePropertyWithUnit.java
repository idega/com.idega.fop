/*
 * $Id: ThreeValuePropertyWithUnit.java,v 1.4 2007/12/03 15:07:22 laddi Exp $
 * Created on Apr 11, 2007
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
 * Last modified: $Date: 2007/12/03 15:07:22 $ by $Author: laddi $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class ThreeValuePropertyWithUnit extends PropertyImpl {

	String description2 = null;

	String value2 = null;

	String description3 = null;

	String value3 = null;

	String unit = null;

	public ThreeValuePropertyWithUnit(String key, String description, String value, String description2, String value2, String description3, String value3, String unit) {
		super(key, description, value);
		this.description2 = description2;
		this.value2 = value2;
		this.description3 = description3;
		this.value3 = value3;
		this.unit = unit;
	}

	public Object accept(PropertyVisitor propertyVisitor) {
		return propertyVisitor.visit(this);
	}

	public String getType() {
		return PropertyConstants.PROPERTY_THREE_VALUES_WITH_UNIT;
	}

	/**
	 * @return the description2
	 */
	public String getDescription2() {
		return description2;
	}

	/**
	 * @param description2
	 *          the description2 to set
	 */
	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	/**
	 * @return the description3
	 */
	public String getDescription3() {
		return description3;
	}

	/**
	 * @param description3
	 *          the description3 to set
	 */
	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *          the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the value2
	 */
	public String getValue2() {
		return value2;
	}

	/**
	 * @param value2
	 *          the value2 to set
	 */
	public void setValue2(String value2) {
		this.value2 = value2;
	}

	/**
	 * @return the value3
	 */
	public String getValue3() {
		return value3;
	}

	/**
	 * @param value3
	 *          the value3 to set
	 */
	public void setValue3(String value3) {
		this.value3 = value3;
	}

}
