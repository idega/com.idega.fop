/*
 * $Id: Property.java,v 1.2 2007/04/18 17:53:47 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import org.xml.sax.SAXException;
import com.idega.fop.tools.EasyGenerationContentHandlerProxy;
import com.idega.io.serialization.Storable;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:53:47 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public interface Property extends Storable {
	
	void generateFor(EasyGenerationContentHandlerProxy contentHandler) throws SAXException;
	
	String getKey();
	
	String getDescription();
	
}
