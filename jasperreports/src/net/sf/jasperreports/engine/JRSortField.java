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

import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRSortField.java 3955 2010-09-22 08:19:02Z teodord $
 */
public interface JRSortField extends JRCloneable
{

	/**
	 * @deprecated Replaced by {@link SortOrderEnum#ASCENDING}.
	 */
	public byte SORT_ORDER_ASCENDING = 0;
	/**
	 * @deprecated Replaced by {@link SortOrderEnum#DESCENDING}.
	 */
	public byte SORT_ORDER_DESCENDING = 1;


	/**
	 * Gets the sort field name.
	 */
	public String getName();
		
	/**
	 * @deprecated Replaced by {@link #getOrderValue()}.
	 */
	public byte getOrder();
	
	/**
	 * Gets the sort order for the field.
	 */
	public SortOrderEnum getOrderValue();
		
	/**
	 * Gets the type of the sort field.
	 */
	public SortFieldTypeEnum getType();

}
