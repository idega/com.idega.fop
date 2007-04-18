/*
 * $Id: PropertyTree.java,v 1.2 2007/04/18 17:53:47 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.SAXException;
import com.idega.fop.tools.EasyGenerationContentHandlerProxy;
import com.idega.io.serialization.ObjectReader;
import com.idega.io.serialization.ObjectWriter;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:53:47 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class PropertyTree implements Property {
	
	String key = null;
	
	String description = null;
	
	List value = null;
	
	public PropertyTree(String key, String description) {
		this(key,description, new ArrayList());
	}
	
	public PropertyTree(String key, String description, List value) {
		this.key = key;
		this.description = description;
		this.value = value;
	}
	
	public PropertyTree add(Property property) {
		value.add(property);
		return this;
	}
	
	public PropertyTree add(String key, String description, String value) {
		Property property = new PropertyImpl(key, description, value);
		this.value.add(property);
		return this;
	}
	
	public PropertyTree addWithUnit(String key, String description, String value, String unit) {
		Property property = new PropertyWithUnit(key, description,value, unit);
		this.value.add(property);
		return this;
	}
	
	public PropertyTree addWithValueDescription(String key, String description, String value, String valueDescription) {
		Property property = new PropertyWithValueDescription(key, description,value, valueDescription);
		this.value.add(property);
		return this;
	}
	
	public PropertyTree add(String key, 
			String description, 
			String value,
			String description2,
			String value2,
			String description3,
			String value3,
			String unit) {
		Property property = new ThreeValuePropertyWithUnit(
				key, 
				description,
				value,
				description2,
				value2,
				description3,
				value3,
				unit);
		this.value.add(property);
		return this;
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
		// TODO Auto-generated method stub
		return key;
	}

}
