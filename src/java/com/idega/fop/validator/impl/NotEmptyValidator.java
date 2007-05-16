/*
 * $Id: NotEmptyValidator.java,v 1.1 2007/05/16 15:57:02 thomas Exp $
 * Created on May 14, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.validator.impl;

import java.util.Iterator;
import java.util.List;
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
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class NotEmptyValidator implements PropertyValidator, PropertyVisitor {
	
	public static Object iterateChildren(PropertyVisitor validator, PropertyTree propertyTree) {
		List items = propertyTree.getValue();
		// are there some children?
		if (items == null || items.isEmpty()) {
			return Boolean.FALSE;
		}
		// check children
		Iterator iterator = propertyTree.getValue().iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			Boolean result = (Boolean) property.accept(validator);
			// stop if someone returns false
			if (! result.booleanValue()) {
				return result;
			}
		}
		// fine, all children contain something return true
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.bean.validation.PropertyValidator#isValid(java.lang.String)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}

	public Object visit(PropertyTree propertyTree)  {
		return NotEmptyValidator.iterateChildren(this, propertyTree);
	}

	public Object visit(PropertyImpl propertyImpl)  {
		String value = propertyImpl.getValue();
		return StringHandler.isNotEmpty(value)? Boolean.TRUE : Boolean.FALSE;
		
	}

	public Object visit(PropertyWithUnit propertyWithUnit)  {
		return visit((PropertyImpl) propertyWithUnit);
		
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription)  {
		return visit((PropertyImpl) propertyWithValueDescription);
		
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		String value = threeValuePropertyWithUnit.getValue();
		if (StringHandler.isNotEmpty(value)) {
			String value2 = threeValuePropertyWithUnit.getValue2();
			if (StringHandler.isNotEmpty(value2)) {
				String value3 = threeValuePropertyWithUnit.getValue3();
				if (StringHandler.isNotEmpty(value3)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
