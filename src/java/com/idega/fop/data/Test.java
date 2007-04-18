/*
 * $Id: Test.java,v 1.2 2007/04/18 17:53:47 thomas Exp $
 * Created on Apr 3, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.data;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:53:47 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class Test {
	
	public static Property getTestInstanceOfAProperty() {
		Property property = new PropertyImpl("name","first name","Hello world");
		Property property2 = new PropertyImpl("name","first name2","Hello world2");
		List list = new ArrayList();
		list.add(property);

		
		Property property4 = new PropertyImpl("name","first name4","Hello world4");
		List list4 = new ArrayList();
		list4.add(property4);
		Property propList = new PropertyTree("others", "hello33", list4);
		list.add(propList);
		
		list.add(property2);
		
		return new PropertyTree("members", "all members", list);
	}
}
