/*
 * $Id: PropertyImpl.java,v 1.2 2007/04/20 18:12:55 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import java.rmi.RemoteException;
import org.xml.sax.SAXException;
import com.idega.fop.visitor.PropertyVisitor;
import com.idega.io.serialization.ObjectReader;
import com.idega.io.serialization.ObjectWriter;
import com.idega.presentation.IWContext;


/**
 * Represents things like "Heimasimi 123456" 
 * 
 *  Last modified: $Date: 2007/04/20 18:12:55 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class PropertyImpl implements Property {
	
	String key = null;
	
	String description = null;
	
	String value = null;
	
	
	public PropertyImpl(String key, String description, String value) {
		this.key = key;
		this.description = description;
		this.value = value;
	}
	
	public void accept(PropertyVisitor propertyVisitor) throws SAXException {
		propertyVisitor.visit(this);
	}

	public String getType() {
		return PropertyConstants.PROPERTY_SIMPLE;
	}
	
	public Object write(ObjectWriter writer, IWContext iwc) throws RemoteException {
		throw new UnsupportedOperationException();
		//return writer.write(this, iwc);
	}

	public Object read(ObjectReader reader, IWContext iwc) throws RemoteException {
		throw new UnsupportedOperationException();
		//return reader.read(this, iwc);
	}

	public String getDescription() {
		return description;
	}

	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
