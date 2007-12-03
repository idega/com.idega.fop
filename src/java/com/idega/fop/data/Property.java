/*
 * $Id: Property.java,v 1.5 2007/12/03 15:07:22 laddi Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import com.idega.fop.visitor.PropertyVisitor;
import com.idega.io.serialization.Storable;

/**
 * 
 * Last modified: $Date: 2007/12/03 15:07:22 $ by $Author: laddi $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.5 $
 */
public interface Property extends Storable {

	String getKey();

	String getDescription();

	Object accept(PropertyVisitor propertyVisitor);

}
