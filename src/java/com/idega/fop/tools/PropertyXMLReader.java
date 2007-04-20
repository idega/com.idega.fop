/*
 * $Id: PropertyXMLReader.java,v 1.2 2007/04/20 18:12:55 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.tools;

import java.io.IOException;
import java.util.Iterator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyConstants;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyInputSource;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.visitor.PropertyVisitor;


/**
 * 
 *  Last modified: $Date: 2007/04/20 18:12:55 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class PropertyXMLReader extends AbstractObjectReader implements PropertyVisitor{

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.output.tools.AbstractObjectReader#parse(org.xml.sax.InputSource)
	 */
	public void parse(InputSource input) throws IOException, SAXException {
        if (input instanceof PropertyInputSource) {
            parse(((PropertyInputSource)input).getProperty());
        } else {
            throw new SAXException("Unsupported InputSource specified. Must be a ProjectTeamInputSource");
        }
		// TODO Auto-generated method stub
	}
	
	public void parse(Property property) throws SAXException {
        if (property == null) {
            throw new NullPointerException("Parameter projectTeam must not be null");
        }
        if (handler == null) {
            throw new IllegalStateException("ContentHandler not set");
        }
        //Start the document
        handler.startDocument();
        
        handler.startElementLineBreak(PropertyConstants.ROOT);
        
        //Generate SAX events
        property.accept(this);
        
        handler.endELementLineBreak(PropertyConstants.ROOT);
        
        //End the document
        handler.endDocument(); 
	}

	public void visit(PropertyTree propertyTree) throws SAXException {
		handler.startElementLineBreak(PropertyConstants.PROPERTY_TREE);

		handler.elementLineBreak(PropertyConstants.KEY, propertyTree.getKey());
		handler.elementLineBreak(PropertyConstants.DESCRIPTION, propertyTree.getDescription());
		handler.startElementLineBreak(PropertyConstants.VALUE);
		Iterator iterator = propertyTree.getValue().iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			property.accept(this);
		}
		handler.endELementLineBreak(PropertyConstants.VALUE);
		handler.endELementLineBreak(PropertyConstants.PROPERTY_TREE);
		// TODO Auto-generated method stub
		
	}

	public void visit(PropertyImpl propertyImpl) throws SAXException {
		addContent(propertyImpl);
		addEnd(propertyImpl);
	}
		
	private void addContent(PropertyImpl propertyImpl) throws SAXException {
		handler.startElementLineBreak(propertyImpl.getType());
		handler.elementLineBreak(PropertyConstants.KEY, propertyImpl.getKey());
		handler.elementLineBreak(PropertyConstants.DESCRIPTION, propertyImpl.getDescription());
		handler.elementLineBreak(PropertyConstants.VALUE, propertyImpl.getValue());
	}

	public void addEnd(PropertyImpl propertyImpl) throws SAXException {
		handler.endELementLineBreak(propertyImpl.getType());
	}
	
	public void visit(PropertyWithUnit propertyWithUnit) throws SAXException {
		addContent(propertyWithUnit);
		handler.elementLineBreak(PropertyConstants.UNIT, propertyWithUnit.getUnit());
		addEnd(propertyWithUnit);
	}

	public void visit(PropertyWithValueDescription propertyWithValueDescription) throws SAXException {
		addContent(propertyWithValueDescription);
		handler.elementLineBreak(PropertyConstants.VALUE_DESCRIPTION, propertyWithValueDescription.getValueDescription());
		addEnd(propertyWithValueDescription);
	}

	public void visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) throws SAXException {
		addContent(threeValuePropertyWithUnit);
		handler.elementLineBreak(PropertyConstants.DESCRIPTION2, threeValuePropertyWithUnit.getDescription2());
		handler.elementLineBreak(PropertyConstants.VALUE2,  threeValuePropertyWithUnit.getValue2());
		handler.elementLineBreak(PropertyConstants.DESCRIPTION3, threeValuePropertyWithUnit.getDescription3());
		handler.elementLineBreak(PropertyConstants.VALUE3, threeValuePropertyWithUnit.getValue3());
		handler.elementLineBreak(PropertyConstants.UNIT, threeValuePropertyWithUnit.getUnit());
		addEnd(threeValuePropertyWithUnit);
	}
}
