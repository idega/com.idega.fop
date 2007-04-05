/*
 * $Id: PropertyInputSource.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import org.xml.sax.InputSource;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class PropertyInputSource extends InputSource {
	
	private Property property = null;
	
	public PropertyInputSource(Property property) {
		this.property = property;
	}

	
	public Property getProperty() {
		return property;
	}

	
	public void setProperty(Property property) {
		this.property = property;
	}
	
	
}
