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

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.PrintElementVisitor;
import net.sf.jasperreports.engine.type.ModeEnum;


/**
 * Base implementation of {@link net.sf.jasperreports.engine.JRPrintElement} that uses
 * a {@link net.sf.jasperreports.engine.fill.JRTemplateElement} instance to
 * store common attributes. 
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplatePrintElement.java 3991 2010-10-12 20:10:55Z lucianc $
 */
public class JRTemplatePrintElement implements JRPrintElement, Serializable
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected JRTemplateElement template;

	private int x;
	private int y;
	private int height;
	private int width;

	private JRPropertiesMap properties;
	
	/**
	 *
	 */
	protected JRTemplatePrintElement(JRTemplateElement element)
	{
		template = element;
	}

	/**
	 * Updates the template used by this element.
	 * 
	 * @param elementTemplate the new element template
	 */
	protected void updateElementTemplate(JRTemplateElement elementTemplate)
	{
		this.template = elementTemplate;
	}
	
	/**
	 *
	 */
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return template.getDefaultStyleProvider();
	}
	
	/**
	 *
	 */
	public JROrigin getOrigin()
	{
		return template.getOrigin();
	}
	
	/**
	 *
	 */
	public JRStyle getStyle()
	{
		return template.getStyle();
	}
	
	/**
	 *
	 */
	public void setStyle(JRStyle style)
	{
	}
	
	/**
	 * @deprecated Replaced by {@link #getModeValue()}.
	 */
	public byte getMode()
	{
		return getModeValue().getValue();
	}

	/**
	 * @deprecated Replaced by {@link #getOwnModeValue()}.
	 */
	public Byte getOwnMode()
	{
		return getOwnModeValue() == null ? null : getOwnModeValue().getValueByte();
	}

	/**
	 *
	 */
	public ModeEnum getModeValue()
	{
		return this.template.getModeValue();
	}
	
	/**
	 *
	 */
	public ModeEnum getOwnModeValue()
	{
		return this.template.getOwnModeValue();
	}
	
	/**
	 * @deprecated Replaced by {@link #setMode(ModeEnum)}.
	 */
	public void setMode(byte mode)
	{
	}
	
	/**
	 * @deprecated Replaced by {@link #setMode(ModeEnum)}.
	 */
	public void setMode(Byte mode)
	{
	}
	
	/**
	 *
	 */
	public void setMode(ModeEnum modeValue)
	{
	}
	
	/**
	 *
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 *
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 *
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 *
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 *
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	/**
	 *
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	
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
	public Color getForecolor()
	{
		return this.template.getForecolor();
	}
	
	/**
	 *
	 */
	public Color getOwnForecolor()
	{
		return this.template.getOwnForecolor();
	}
	
	/**
	 *
	 */
	public void setForecolor(Color color)
	{
	}
	
	/**
	 *
	 */
	public Color getBackcolor()
	{
		return this.template.getBackcolor();
	}

	/**
	 *
	 */
	public Color getOwnBackcolor()
	{
		return this.template.getOwnBackcolor();
	}

	/**
	 *
	 */
	public void setBackcolor(Color color)
	{
	}

	
	public JRTemplateElement getTemplate()
	{
		return template;
	}

	public void setTemplate(JRTemplateElement template)
	{
		this.template = template;
		
		if (properties != null)
		{
			if (this.template != null && this.template.hasProperties())
			{
				properties.setBaseProperties(this.template.getPropertiesMap());
			}
			else
			{
				properties.setBaseProperties(null);
			}
		}
	}

	public String getKey()
	{
		return template.getKey();
	}

	/**
	 * Returns null as external style references are not allowed for print objects.
	 */
	public String getStyleNameReference()
	{
		return null;
	}

	/**
	 * 
	 */
	public Color getDefaultLineColor() 
	{
		return getForecolor();
	}

	public synchronized boolean hasProperties()
	{
		return properties != null && properties.hasProperties()
				|| template.hasProperties();
	}

	public synchronized JRPropertiesMap getPropertiesMap()
	{
		if (properties == null)
		{
			//FIXME avoid this on read only calls
			properties = new JRPropertiesMap();
			
			if (template.hasProperties())
			{
				properties.setBaseProperties(template.getPropertiesMap());
			}
		}
		
		return properties;
	}

	public JRPropertiesHolder getParentProperties()
	{
		return null;
	}

	private synchronized void writeObject(ObjectOutputStream out) throws IOException
	{
		if (properties != null && !properties.hasOwnProperties())
		{
			properties = null;
		}
		
		out.defaultWriteObject();
	}

	// we need to implement this method because the class is not abstract
	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		throw new UnsupportedOperationException();
	}
}
