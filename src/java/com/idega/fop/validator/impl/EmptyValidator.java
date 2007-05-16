/*
 * $Id: EmptyValidator.java,v 1.1 2007/05/16 15:57:02 thomas Exp $
 * Created on May 15, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.validator.impl;

import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.visitor.PropertyVisitor;
import com.idega.util.StringHandler;


/**
 * A validator with a nested validator. The nested validator is only involved if the
 * value is not empty. 
 * 
 * In other words:
 * Use this validator if a field might be empty but if it is filled it should be valid.
 * (e.g., opional birthdate) 
 * 
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class EmptyValidator implements PropertyValidator, PropertyVisitor {
	
	private PropertyValidator propertyValidator = null;
	
	public EmptyValidator(PropertyValidator validator) {
		propertyValidator = validator;
	}
	

	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}

	public Object visit(PropertyTree propertyTree) {
		return NotEmptyValidator.iterateChildren(this, propertyTree);
	}

	public Object visit(PropertyImpl propertyImpl) {
		String value = propertyImpl.getValue();
		if (StringHandler.isEmpty(value)) {
			return Boolean.TRUE;
		}
		return (propertyValidator.isValid(propertyImpl)) ? Boolean.TRUE : Boolean.FALSE;
	}

	public Object visit(PropertyWithUnit propertyWithUnit) {
		return visit((PropertyImpl) propertyWithUnit);
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription) {
		return visit((PropertyImpl) propertyWithValueDescription);
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		String value = threeValuePropertyWithUnit.getValue();
		if (StringHandler.isNotEmpty(value)) {
			String value2 = threeValuePropertyWithUnit.getValue2();
			if (StringHandler.isNotEmpty(value2)) {
				String value3 = threeValuePropertyWithUnit.getValue3();
				if (StringHandler.isNotEmpty(value3)) {
					// all three values are not empty
					return (propertyValidator.isValid(threeValuePropertyWithUnit)) ? Boolean.TRUE : Boolean.FALSE;
				}
			}
		}
		// first value is empty
		String value2 = threeValuePropertyWithUnit.getValue2();
		if (StringHandler.isEmpty(value2)) {
			String value3 = threeValuePropertyWithUnit.getValue3();
			if (StringHandler.isEmpty(value3)) {
				// all three values are empty
				return Boolean.TRUE;
			}
		}
		// some value are empty others are not
		return Boolean.FALSE;
	}
}
