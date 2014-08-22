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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Default {@link JRTemplate} implementation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRSimpleTemplate.java 3033 2009-08-27 11:46:22Z teodord $
 */
public class JRSimpleTemplate implements JRTemplate, Serializable
{
	
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private final List includedTemplates = new ArrayList();
	private final List styles = new ArrayList();
	private JRStyle defaultStyle;
	
	/**
	 * Adds a style to the template.
	 * 
	 * @param style the style to add
	 * @throws JRException when a style with the same name already exists
	 */
	public void addStyle(JRStyle style) throws JRException
	{
		checkExistingName(style.getName());
		
		if (style.isDefault())
		{
			defaultStyle = style;
		}
		
		styles.add(style);
	}
	
	protected void checkExistingName(String name) throws JRException
	{
		if (getStyle(name) != null)
		{
			throw new JRException("Duplicate declaration of template style : " + name);
		}
	}
	
	protected boolean nameMatches(JRStyle style, String name)
	{
		String styleName = style.getName();
		return name == null ? styleName == null : name.equals(styleName);
	}

	/**
	 * Returns an included style by name.
	 * 
	 * @param name the name of the style to be returned
	 * @return the style having the specified name, or <code>null</code> if not found
	 */
	public JRStyle getStyle(String name)
	{
		JRStyle style = null;
		for (Iterator it = styles.iterator(); it.hasNext();)
		{
			JRStyle itStyle = (JRStyle) it.next();
			if (nameMatches(itStyle, name))
			{
				style = itStyle;
				break;
			}
		}
		return style;
	}
	
	/**
	 * Removes an included style.
	 * 
	 * @param style the style to remove
	 * @return <code>true</code> if and only if the style has been found and removed
	 */
	public boolean removeStyle(JRStyle style)
	{
		boolean removed = styles.remove(style);
		if (removed)
		{
			if (style.isDefault())
			{
				defaultStyle = null;
			}
		}
		return removed;
	}

	/**
	 * Removes an included style.
	 * 
	 * @param name the name of the style to be removed
	 * @return the removed style, or <code>null</code> if not found
	 */
	public JRStyle removeStyle(String name)
	{
		JRStyle removed = null;
		for (ListIterator it = styles.listIterator(); it.hasNext();)
		{
			JRStyle style = (JRStyle) it.next();
			if (nameMatches(style, name))
			{
				if (style.isDefault())
				{
					defaultStyle = null;
				}
				
				removed = style;
				it.remove();
				break;
			}
		}
		return removed;
	}
	
	public JRStyle[] getStyles()
	{
		return (JRStyle[]) styles.toArray(new JRStyle[styles.size()]);
	}

	public JRStyle getDefaultStyle()
	{
		return defaultStyle;
	}

	public JRReportFont getDefaultFont()
	{
		return null;
	}

	/**
	 * Adds an included template.
	 * 
	 * @param reference the template reference
	 * @see #getIncludedTemplates()
	 */
	public void addIncludedTemplate(JRTemplateReference reference)
	{
		includedTemplates.add(reference);
	}

	/**
	 * Adds an included template.
	 * 
	 * @param templateLocation the template location
	 * @see #getIncludedTemplates()
	 */
	public void addIncludedTemplate(String templateLocation)
	{
		includedTemplates.add(new JRTemplateReference(templateLocation));
	}
	
	/**
	 * Removes an included template.
	 * 
	 * @param reference the template reference to remove
	 * @return <code>true</code> if and only if the included template has been found and removed
	 */
	public boolean removeIncludedTemplate(JRTemplateReference reference)
	{
		return includedTemplates.remove(reference);
	}
	
	/**
	 * Removes an included template.
	 * <p/>
	 * The first template reference that matches the location is removed.
	 * 
	 * @param location the location of the template to remove
	 * @return the removed template reference, or <code>null</code> if not found
	 */
	public JRTemplateReference removeIncludedTemplate(String location)
	{
		JRTemplateReference removed = null;
		for (ListIterator it = includedTemplates.listIterator(); it.hasNext();)
		{
			JRTemplateReference ref = (JRTemplateReference) it.next();
			if (ref.getLocation().equals(location))
			{
				removed = ref;
				it.remove();
			}
		}
		return removed;
	}
	
	public JRTemplateReference[] getIncludedTemplates()
	{
		return (JRTemplateReference[]) includedTemplates.toArray(new JRTemplateReference[includedTemplates.size()]);
	}

}
