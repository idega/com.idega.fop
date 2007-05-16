/*
 * $Id: PropertyTree.java,v 1.4 2007/05/16 15:57:02 thomas Exp $
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
import java.util.List;
import org.xml.sax.SAXException;
import com.idega.fop.visitor.PropertyVisitor;
import com.idega.io.serialization.ObjectReader;
import com.idega.io.serialization.ObjectWriter;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
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
	
	public Object accept(PropertyVisitor propertyVisitor)  {
		return propertyVisitor.visit(this);
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
	
	public List getValue() {
		return value;
	}

}
