/*
 * $Id: PropertyVisitor.java,v 1.2 2007/05/16 15:57:32 thomas Exp $
 * Created on Apr 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.visitor;

import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:57:32 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public interface PropertyVisitor {
	
	Object visit(PropertyTree propertyTree) ;
	
	Object visit(PropertyImpl propertyImpl) ;
	
	Object visit(PropertyWithUnit propertyWithUnit) ;
	
	Object visit(PropertyWithValueDescription propertyWithValueDescription) ;
	
	Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit);
}
