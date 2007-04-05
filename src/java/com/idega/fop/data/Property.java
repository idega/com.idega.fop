/*
 * $Id: Property.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
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


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public interface Property {
	
	void generateFor(EasyGenerationContentHandlerProxy contentHandler) throws SAXException;
}
