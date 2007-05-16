/*
 * $Id: IgnoreValidator.java,v 1.1 2007/05/16 15:57:02 thomas Exp $
 * Created on May 14, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.validator.impl;

import com.idega.fop.data.Property;
import com.idega.fop.validator.PropertyValidator;

/**
 * 
 * A dummy validator, returns always true. Very useful to prevent annoying null checks.
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class IgnoreValidator implements PropertyValidator {

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.bean.validation.validators.PropertyValidator#isValid(java.lang.String)
	 */
	public boolean isValid(Property property) {
		return true;
	}
}
