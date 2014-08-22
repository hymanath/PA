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
package net.sf.jasperreports.engine.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPenContainer;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRStyleContainer;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.util.JRStyleResolver;


/**
 * This is useful for drawing borders around text elements and images. Boxes can have borders and paddings, which can
 * have different width and colour on each side of the element.
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBasePen.java 3939 2010-08-20 09:52:00Z teodord $
 */
public class JRBasePen implements JRPen, Serializable, Cloneable, JRChangeEventsSupport
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_LINE_WIDTH = "lineWidth";
	
	public static final String PROPERTY_LINE_STYLE = "lineStyle";
	
	public static final String PROPERTY_LINE_COLOR = "lineColor";
	

	protected JRPenContainer penContainer;

	/**
	 *
	 */
	protected Float lineWidth;
	protected LineStyleEnum lineStyleValue;
	protected Color lineColor;

	
	/**
	 *
	 */
	public JRBasePen(JRPenContainer penContainer)
	{
		super();
		
		this.penContainer = penContainer;
	}
	
	
	/**
	 *
	 */
	public JRStyleContainer getStyleContainer()
	{
		return penContainer;
	}

	/**
	 *
	 */
	public Float getLineWidth()
	{
		return JRStyleResolver.getLineWidth(this, penContainer.getDefaultLineWidth());
	}

	/**
	 *
	 */
	public Float getOwnLineWidth()
	{
		return lineWidth;
	}

	/**
	 *
	 */
	public void setLineWidth(float lineWidth)
	{
		setLineWidth(new Float(lineWidth));
	}

	/**
	 *
	 */
	public void setLineWidth(Float lineWidth)
	{
		Object old = this.lineWidth;
		this.lineWidth = lineWidth;
		getEventSupport().firePropertyChange(PROPERTY_LINE_WIDTH, old, this.lineWidth);
	}

	/**
	 * @deprecated Replaced by {@link #getLineStyleValue()}.
	 */
	public Byte getLineStyle()
	{
		return getLineStyleValue().getValueByte();
	}

	/**
	 * @deprecated Replaced by {@link #getOwnLineStyleValue()}.
	 */
	public Byte getOwnLineStyle()
	{
		return getOwnLineStyleValue() == null ? null : getOwnLineStyleValue().getValueByte();
	}

	/**
	 *
	 */
	public LineStyleEnum getLineStyleValue()
	{
		return JRStyleResolver.getLineStyleValue(this);
	}

	/**
	 *
	 */
	public LineStyleEnum getOwnLineStyleValue()
	{
		return lineStyleValue;
	}

	/**
	 * @deprecated Replaced by {@link #setLineStyle(LineStyleEnum)}.
	 */
	public void setLineStyle(byte lineStyle)
	{
		setLineStyle(LineStyleEnum.getByValue(lineStyle));
	}

	/**
	 * @deprecated Replaced by {@link #setLineStyle(LineStyleEnum)}.
	 */
	public void setLineStyle(Byte lineStyle)
	{
		setLineStyle(LineStyleEnum.getByValue(lineStyle));
	}

	/**
	 *
	 */
	public void setLineStyle(LineStyleEnum lineStyleValue)
	{
		Object old = this.lineStyleValue;
		this.lineStyleValue = lineStyleValue;
		getEventSupport().firePropertyChange(PROPERTY_LINE_STYLE, old, this.lineStyleValue);
	}

	/**
	 *
	 */
	public Color getLineColor()
	{
		return JRStyleResolver.getLineColor(this, penContainer.getDefaultLineColor());
	}

	/**
	 *
	 */
	public Color getOwnLineColor()
	{
		return lineColor;
	}

	/**
	 *
	 */
	public void setLineColor(Color lineColor)
	{
		Object old = this.lineColor;
		this.lineColor = lineColor;
		getEventSupport().firePropertyChange(PROPERTY_LINE_COLOR, old, this.lineColor);
	}


	public String getStyleNameReference()
	{
		return null;
	}

	/**
	 * 
	 */
	public JRPen clone(JRPenContainer penContainer)
	{
		JRBasePen clone = null;
		try
		{
			clone = (JRBasePen)super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		clone.penContainer = penContainer;
		
		return clone;
	}
	
	private transient JRPropertyChangeSupport eventSupport;
	
	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (eventSupport == null)
			{
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return eventSupport;
	}

	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private Byte lineStyle;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			lineStyleValue = LineStyleEnum.getByValue(lineStyle);
			
			lineStyle = null;
		}
	}

}
