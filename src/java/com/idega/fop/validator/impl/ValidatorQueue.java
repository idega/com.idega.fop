/*
 * $Id: ValidatorQueue.java,v 1.1 2007/05/16 15:57:02 thomas Exp $
 * Created on May 15, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.validator.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.idega.fop.data.Property;
import com.idega.fop.validator.PropertyValidator;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ValidatorQueue implements PropertyValidator {
	
	private List myQueue;
	
	public ValidatorQueue() {
		myQueue = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property property) {
		Iterator iterator = myQueue.iterator();
		while (iterator.hasNext()) {
			PropertyValidator propertyValidator = (PropertyValidator) iterator.next();
			if (! propertyValidator.isValid(property)) {
				return false;
			}
		}
		return true;
	}
	
	public void add(PropertyValidator propertyValidator) {
		myQueue.add(propertyValidator);
	}
	
	
}
