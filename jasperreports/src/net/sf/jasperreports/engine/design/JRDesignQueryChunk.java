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
package net.sf.jasperreports.engine.design;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.base.JRBaseQueryChunk;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignQueryChunk.java 3033 2009-08-27 11:46:22Z teodord $
 */
public class JRDesignQueryChunk extends JRBaseQueryChunk implements JRChangeEventsSupport
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_TEXT = "text";
	
	public static final String PROPERTY_TOKENS = "tokens";
	
	public static final String PROPERTY_TYPE = "type";

	/**
	 *
	 */
	public void setType(byte type)
	{
		byte old = this.type;
		this.type = type;
		getEventSupport().firePropertyChange(PROPERTY_TYPE, old, this.type);
	}
		
	/**
	 *
	 */
	public void setText(String text)
	{
		Object old = this.text;
		this.text = text;
		getEventSupport().firePropertyChange(PROPERTY_TEXT, old, this.text);
	}
	
	
	/**
	 * Sets the clause tokens for chunks of type {@link JRQueryChunk#TYPE_CLAUSE_TOKENS TYPE_CLAUSE_TOKENS}.
	 * 
	 * @param tokens the clause tokens
	 * @see #getTokens()
	 */
	public void setTokens(String[] tokens)
	{
		Object old = this.tokens;
		this.tokens = tokens;
		getEventSupport().firePropertyChange(PROPERTY_TOKENS, old, this.tokens);
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

}
