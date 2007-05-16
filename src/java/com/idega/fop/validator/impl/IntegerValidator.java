/*
 * $Id: IntegerValidator.java,v 1.1 2007/05/16 15:57:02 thomas Exp $
 * Created on May 15, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.fop.validator.impl;

import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.visitor.PropertyVisitor;


/**
 * An integer validator. Uses validator implementation of org.apache.commons.validator.routines
 * 
 *  Last modified: $Date: 2007/05/16 15:57:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class IntegerValidator implements PropertyValidator, PropertyVisitor {
	
	/**
	 * If equal is set to false:  Value must be less than max.
	 * If equal is set to true: Value must be less than max or equal.
	 *
	 * @param min
	 * @param notEqual
	 * @return
	 */
	public static IntegerValidator getInRangeOrEqualValidator(int min, boolean minEqual, int max, boolean maxEqual) {
		IntegerValidator validator = new IntegerValidator();
		validator.setCheckMinNotEqual(! minEqual);
		validator.setMin(min);
		validator.setCheckMaxNotEqual(! maxEqual);
		validator.setMax(max);
		return validator;
	}	
	
	/**
	 * If equal is set to false:  Value must be less than max.
	 * If equal is set to true: Value must be less than max or equal.
	 *
	 * @param min
	 * @param notEqual
	 * @return
	 */
	public static IntegerValidator getLessThanOrEqualValidator(int max, boolean equal) {
		IntegerValidator validator = new IntegerValidator();
		validator.setCheckMaxNotEqual(! equal);
		validator.setMax(max);
		return validator;
	}	
	
	/**
	 * If equal is set to false:  Value must be greater than min.
	 * If equal is set to true: Value must be greater than min or equal.
	 *
	 * @param min
	 * @param notEqual
	 * @return
	 */
	public static IntegerValidator getGreaterThanOrEqualValidator(int min, boolean equal) {
		IntegerValidator validator = new IntegerValidator();
		validator.setCheckMinNotEqual(! equal);
		validator.setMin(min);
		return validator;
	}
	
	private org.apache.commons.validator.routines.IntegerValidator apacheIntegerValidator = null;
	
	private boolean checkMax = false;
	private boolean checkMaxNotEqual = false;
	
	private boolean checkMin = false;
	private boolean checkMinNotEqual = false;
	
	private int max = -1;
	private int min = -1;
	
	public IntegerValidator() {
		apacheIntegerValidator = new org.apache.commons.validator.routines.IntegerValidator();
	}
	
	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}

	
	/**
	 * @return the checkMax
	 */
	public boolean isCheckMax() {
		return checkMax;
	}

	
	/**
	 * @param checkMax the checkMax to set
	 */
	public void setCheckMax(boolean checkMax) {
		this.checkMax = checkMax;
	}

	
	/**
	 * @return the checkMaxEqual
	 */
	public boolean isCheckMaxNotEqual() {
		return checkMaxNotEqual;
	}

	
	/**
	 * @param checkMaxEqual the checkMaxEqual to set
	 */
	public void setCheckMaxNotEqual(boolean checkMaxNotEqual) {
		this.checkMaxNotEqual = checkMaxNotEqual;
	}

	
	/**
	 * @return the checkMin
	 */
	public boolean isCheckMin() {
		return checkMin;
	}

	
	/**
	 * @param checkMin the checkMin to set
	 */
	public void setCheckMin(boolean checkMin) {
		this.checkMin = checkMin;
	}

	
	/**
	 * @return the checkMinEqual
	 */
	public boolean isCheckMinEqual() {
		return checkMinNotEqual;
	}

	
	/**
	 * @param checkMinEqual the checkMinEqual to set
	 */
	public void setCheckMinNotEqual(boolean checkMinNotEqual) {
		this.checkMinNotEqual = checkMinNotEqual;
	}

	
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}


	public Object visit(PropertyTree propertyTree) {
		return NotEmptyValidator.iterateChildren(this, propertyTree);
	}


	public Object visit(PropertyImpl propertyImpl) {
		String value = propertyImpl.getValue();
		return (isValueValid(value)) ? Boolean.TRUE : Boolean.FALSE;
	}


	public Object visit(PropertyWithUnit propertyWithUnit) {
		return visit((PropertyImpl) propertyWithUnit);
	}


	public Object visit(PropertyWithValueDescription propertyWithValueDescription) {
		return visit((PropertyImpl) propertyWithValueDescription);
	}


	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		String value = threeValuePropertyWithUnit.getValue();
		if (isValueValid(value)) {
			String value2 = threeValuePropertyWithUnit.getValue2();
			if (isValueValid(value2)) {
				String value3 = threeValuePropertyWithUnit.getValue3();
				if (isValueValid(value3)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	private boolean isValueValid(String value) {
		Integer result = apacheIntegerValidator.validate(value);
		if (result == null) {
			return false;
		}
		// check if equal 
		if (! isEqualCheckValid(result)) {
			return false;
		}
		// check range
		if ((checkMin || checkMinNotEqual) && (checkMax || checkMaxNotEqual)) {
			return apacheIntegerValidator.isInRange(result, min,max);
		}
		// check min
		if (checkMin || checkMinNotEqual) {
			return apacheIntegerValidator.minValue(result, min);
		}
		// check max
		if (checkMax || checkMaxNotEqual) {
			return apacheIntegerValidator.maxValue(result,max);
		}
		// nothing to check (?)
		return true;
	}
	
	private boolean isEqualCheckValid(Integer value) {
		int valueAsInt = value.intValue();
		if (checkMinNotEqual) {
			if (valueAsInt == min) {
				return false;
			}
		}
		if (checkMinNotEqual) {
			if (valueAsInt == max) {
				return false;
			}
		}
		return true;
	}




}
