/*
 * $Id: PropertyVisitor.java,v 1.1 2007/04/20 18:12:55 thomas Exp $
 * Created on Apr 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.visitor;

import org.xml.sax.SAXException;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;


/**
 * 
 *  Last modified: $Date: 2007/04/20 18:12:55 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public interface PropertyVisitor {
	
	void visit(PropertyTree propertyTree) throws SAXException;
	
	void visit(PropertyImpl propertyImpl) throws SAXException;
	
	void visit(PropertyWithUnit propertyWithUnit) throws SAXException;
	
	void visit(PropertyWithValueDescription propertyWithValueDescription) throws SAXException;
	
	void visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) throws SAXException;
}
