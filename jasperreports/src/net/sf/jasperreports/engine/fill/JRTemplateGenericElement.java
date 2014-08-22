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

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRGenericElement;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.JROrigin;

/**
 * Generic print element information shared by multiple elements.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRTemplateGenericElement.java 3206 2009-11-20 16:11:29Z lucianc $
 * @see JRTemplateGenericPrintElement
 */
public class JRTemplateGenericElement extends JRTemplateElement
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private JRGenericElementType genericType;

	protected JRTemplateGenericElement(JROrigin origin, 
			JRDefaultStyleProvider defaultStyleProvider, JRGenericElement element)
	{
		super(origin, defaultStyleProvider);
		
		setElement(element);
		setGenericType(element.getGenericType());
	}

	/**
	 * Creates a generic print element template.
	 * 
	 * @param origin the origin of the elements that will use the template
	 * @param defaultStyleProvider the style provider to be used for the elements
	 * @param genericType the type of the generic elements
	 */
	public JRTemplateGenericElement(JROrigin origin, 
			JRDefaultStyleProvider defaultStyleProvider,
			JRGenericElementType genericType)
	{
		super(origin, defaultStyleProvider);
		
		setGenericType(genericType);
	}

	/**
	 * Creates a generic print element template.
	 * 
	 * @param origin the origin of the elements that will use the template
	 * @param defaultStyleProvider the style provider to be used for the elements
	 * @param element an element to copy basic elements from
	 * @param genericType the type of the generic elements
	 */
	public JRTemplateGenericElement(JROrigin origin, 
			JRDefaultStyleProvider defaultStyleProvider,
			JRElement element,
			JRGenericElementType genericType)
	{
		super(origin, defaultStyleProvider);
		
		setElement(element);
		setGenericType(genericType);
	}
	
	/**
	 * Returns the type of the generic elements that use this template.
	 * 
	 * @return the type of the generic elements
	 */
	public JRGenericElementType getGenericType()
	{
		return genericType;
	}
	
	/**
	 * Sets the type of the generic elements that use this template.
	 * 
	 * @param genericType the generic type
	 */
	public void setGenericType(JRGenericElementType genericType)
	{
		this.genericType = genericType;
	}

}
