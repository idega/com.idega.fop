/*
 * $Id: PropertyTree.java,v 1.1 2007/04/05 22:21:14 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.SAXException;
import com.idega.fop.tools.EasyGenerationContentHandlerProxy;
import com.idega.io.serialization.ObjectReader;
import com.idega.io.serialization.ObjectWriter;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/04/05 22:21:14 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class PropertyTree implements Property {
	
	String key = null;
	
	String description = null;
	
	List value = null;
	
	public PropertyTree(String key, String description, List value) {
		this.key = key;
		this.description = description;
		this.value = value;
	}

	public void generateFor(EasyGenerationContentHandlerProxy contentHandler) throws SAXException {
		contentHandler.startElementLineBreak(PropertyConstants.PROPERTY_TREE);

		contentHandler.elementLineBreak(PropertyConstants.KEY, key);
		contentHandler.elementLineBreak(PropertyConstants.DESCRIPTION, description);
		contentHandler.startElementLineBreak(PropertyConstants.VALUE);
		Iterator iterator = value.iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			property.generateFor(contentHandler);
		}
		contentHandler.endELementLineBreak(PropertyConstants.VALUE);
		contentHandler.endELementLineBreak(PropertyConstants.PROPERTY_TREE);
	}
	
	public Object write(ObjectWriter writer, IWContext iwc) throws RemoteException {
		return writer.write(this, iwc);
	}

	public Object read(ObjectReader reader, IWContext iwc) throws RemoteException {
		return reader.read(this, iwc);
	}
}
