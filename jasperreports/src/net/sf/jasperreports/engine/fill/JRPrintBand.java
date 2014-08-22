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
package net.sf.jasperreports.engine.fill;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintElementContainer;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintBand.java 3940 2010-08-20 10:35:15Z teodord $
 */
public class JRPrintBand implements JRPrintElementContainer
{
	

	/**
	 *
	 */
	private int height;
	private List elements = new ArrayList();

	
	/**
	 *
	 */
	public int getHeight()
	{
		return this.height;
	}
		
	/**
	 *
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 *
	 */
	public List getElements()
	{
		return this.elements;
	}
	
	/**
	 *
	 */
	public void addElement(JRPrintElement element)
	{
		this.elements.add(element);
	}
		

}
