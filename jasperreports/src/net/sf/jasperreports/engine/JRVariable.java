/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;


/**
 * An interface for implementing classes that deal with report variables. This interface defines constants for names of
 * built-in variables and for reset, increment and calculation types.
 * <p>
 * When declaring a report group, the engine will automatically create a count variable that will calculate
 * the number of records that make up the current group (number of records processed between group ruptures).
 * The name for this variable comes from the name of the group it corresponds to, suffixed with the
 * "_COUNT" sequence. It can be used like any other report variable, in any report expression (even in the
 * current group expression like you can see done in the "BreakGroup" of the <i>jasper</i> sample).
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRVariable.java 3583 2010-03-12 11:35:40Z shertage $
 */
public interface JRVariable extends JRCloneable
{
	/**
	 * Built-in variable that contains the total number of records read from the datasource. After finishing iterating throught the
	 * datasource, it will contain the total number of records that were processed.
	 */
	public static final String REPORT_COUNT = "REPORT_COUNT";


	/**
	 * Built-in variable containing the number of records that were processed when generating the current page.
	 */
	public static final String PAGE_COUNT = "PAGE_COUNT";


	/**
	 * This variable contains the number of records that were processed when generating the current column.
	 */
	public static final String COLUMN_COUNT = "COLUMN_COUNT";


	/**
	 * Built-in variable containing the current page number. At the end of the report filling process, it will contain the total
	 * number of pages for the resulting document.
	 */
	public static final String PAGE_NUMBER = "PAGE_NUMBER";


	/**
	 * Built-in variable containing the current column number.
	 */
	public static final String COLUMN_NUMBER = "COLUMN_NUMBER";


	/**
	 * @deprecated Replaced by {@link ResetTypeEnum#REPORT}.
	 */
	public static final byte RESET_TYPE_REPORT = 1;


	/**
	 * @deprecated Replaced by {@link ResetTypeEnum#PAGE}.
	 */
	public static final byte RESET_TYPE_PAGE = 2;


	/**
	 * @deprecated Replaced by {@link ResetTypeEnum#COLUMN}.
	 */
	public static final byte RESET_TYPE_COLUMN = 3;


	/**
	 * @deprecated Replaced by {@link ResetTypeEnum#GROUP}.
	 */
	public static final byte RESET_TYPE_GROUP = 4;


	/**
	 * @deprecated Replaced by {@link ResetTypeEnum#NONE}.
	 */
	public static final byte RESET_TYPE_NONE = 5;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#NOTHING}.
	 */
	public static final byte CALCULATION_NOTHING = 0;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#COUNT}.
	 */
	public static final byte CALCULATION_COUNT = 1;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#SUM}.
	 */
	public static final byte CALCULATION_SUM = 2;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#AVERAGE}.
	 */
	public static final byte CALCULATION_AVERAGE = 3;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#LOWEST}.
	 */
	public static final byte CALCULATION_LOWEST = 4;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#HIGHEST}.
	 */
	public static final byte CALCULATION_HIGHEST = 5;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#STANDARD_DEVIATION}.
	 */
	public static final byte CALCULATION_STANDARD_DEVIATION = 6;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#VARIANCE}.
	 */
	public static final byte CALCULATION_VARIANCE = 7;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#SYSTEM}.
	 */
	public static final byte CALCULATION_SYSTEM = 8;
	
	
	/**
	 * @deprecated Replaced by {@link CalculationEnum#FIRST}.
	 */
	public static final byte CALCULATION_FIRST = 9;


	/**
	 * @deprecated Replaced by {@link CalculationEnum#DISTINCT_COUNT}.
	 */
	public static final byte CALCULATION_DISTINCT_COUNT = 10;


	/**
	 * Returns the name of the variable. Since all variables are stored in a map, the variable names are the keys in the map.
	 * @return a string containing the variable name
	 */
	public String getName();


	/**
	 * Returns the class of the variable value. Any class is allowed as long as it is in the classpath at compile and run time.
	 * @return a <tt>Class</tt> instance representing the variable value class
	 */
	public Class getValueClass();
		
	/**
	 * Returns the string name of the variable value class.
	 */
	public String getValueClassName();
		
	/**
	 * Returns the class of the incrementer factory used for choosing the right incrementer for the variable value.
	 * @return the <tt>Class</tt> instance of the incrementer factory
	 * @see net.sf.jasperreports.engine.fill.JRIncrementer
	 * @see net.sf.jasperreports.engine.fill.JRIncrementerFactory
	 */
	public Class getIncrementerFactoryClass();
		
	/**
	 * Returns the string name of the variable value class.
	 */
	public String getIncrementerFactoryClassName();
		
	/**
	 * @deprecated Replaced by {@link #getResetTypeValue()}.
	 */
	public byte getResetType();
		
	/**
	 * @deprecated Replaced by {@link #getIncrementTypeValue()}.
	 */
	public byte getIncrementType();
	
	/**
	 * Gets the variable reset type.
	 * @return a value representing one of the reset type constants in {@link ResetTypeEnum}
	 */
	public ResetTypeEnum getResetTypeValue();
	
	/**
	 * Gets the variable increment type.
	 * @return a value representing one of the reset type constants in {@link IncrementTypeEnum}
	 */
	public IncrementTypeEnum getIncrementTypeValue();
	
	/**
	 * @deprecated Replaced by {@link #getCalculationValue()}.
	 */
	public byte getCalculation();
	
	/**
	 * Gets the variable calculation type.
	 * @return a value representing one of the calculation type constants in {@link CalculationEnum}
	 */
	public CalculationEnum getCalculationValue();

	/**
	 * Returns <code>true</code> if the variable calculation type is system defined.
	 * @see JRVariable#CALCULATION_SYSTEM
	 */
	public boolean isSystemDefined();

	/**
	 * Returns the main expression for this variable. The expression must be numeric for certain calculation types.
	 * @return a {@link JRExpression} instance containing the expression.
	 */
	public JRExpression getExpression();
		
	/**
	 * Returns the initial value expression for this variable. The expression must be numeric for certain calculation types.
	 * @return a {@link JRExpression} instance containing the initial expression.
	 */
	public JRExpression getInitialValueExpression();
		
	/**
	 * Returns the group whose break triggers the variable reset. Only used when {@link JRVariable#getResetType()} returns
	 * {@link JRVariable#RESET_TYPE_GROUP}.
	 */
	public JRGroup getResetGroup();
		
	/**
	 * Returns the group whose break triggers the variable increment. Only used when {@link JRVariable#getIncrementType()} returns
	 * {@link JRVariable#RESET_TYPE_GROUP}.
	 */
	public JRGroup getIncrementGroup();
		
}
