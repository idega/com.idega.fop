/*
 * $Id: Property.java,v 1.4 2007/05/16 15:57:02 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import org.xml.sax.SAXException;
import com.idega.fop.visitor.PropertyVisitor;
import com.idega.io.serialization.Storable;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public interface Property extends Storable {
	
	String getKey();
	
	String getDescription();
	
	Object accept(PropertyVisitor propertyVisitor) ;
	
}
