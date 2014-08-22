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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.base.JRBaseQuery;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.util.JRQueryChunkHandler;
import net.sf.jasperreports.engine.util.JRQueryParser;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignQuery.java 3033 2009-08-27 11:46:22Z teodord $
 */
public class JRDesignQuery extends JRBaseQuery implements JRChangeEventsSupport
{
	/** Property change support mechanism. */
	private transient JRPropertyChangeSupport propSupport;


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;


	public static final String PROPERTY_LANGUAGE = "language";
	
	public static final String PROPERTY_TEXT = "text";

	/**
	 *
	 */
	protected List chunks = new ArrayList();

	
	private transient final JRQueryChunkHandler chunkAdder = new JRQueryChunkHandler()
	{
		public void handleParameterChunk(String text)
		{
			addParameterChunk(text);
		}

		public void handleParameterClauseChunk(String text)
		{
			addParameterClauseChunk(text);
		}

		public void handleTextChunk(String text)
		{
			addTextChunk(text);
		}

		public void handleClauseChunk(String[] tokens)
		{
			addClauseChunk(tokens);
		}
	};

	/**
	 *
	 */
	public JRQueryChunk[] getChunks()
	{
		JRQueryChunk[] chunkArray = null;
		
		if (chunks != null && chunks.size() > 0)
		{
			chunkArray = new JRQueryChunk[chunks.size()];
			chunks.toArray(chunkArray);
		}
		
		return chunkArray;
	}
		
	/**
	 *
	 */
	public void setChunks(List chunks)
	{
		this.chunks = chunks;
	}
		
	/**
	 *
	 */
	public void addChunk(JRDesignQueryChunk chunk)
	{
		this.chunks.add(chunk);
	}
		
	/**
	 *
	 */
	public void addTextChunk(String text)
	{
		JRDesignQueryChunk chunk = new JRDesignQueryChunk();
		chunk.setType(JRQueryChunk.TYPE_TEXT);
		chunk.setText(text);

		this.chunks.add(chunk);
	}
		
	/**
	 *
	 */
	public void addParameterChunk(String text)
	{
		JRDesignQueryChunk chunk = new JRDesignQueryChunk();
		chunk.setType(JRQueryChunk.TYPE_PARAMETER);
		chunk.setText(text);

		this.chunks.add(chunk);
	}
		
	/**
	 *
	 */
	public void addParameterClauseChunk(String text)
	{
		JRDesignQueryChunk chunk = new JRDesignQueryChunk();
		chunk.setType(JRQueryChunk.TYPE_PARAMETER_CLAUSE);
		chunk.setText(text);

		this.chunks.add(chunk);
	}
	

	/**
	 * Adds a {@link JRQueryChunk#TYPE_CLAUSE_TOKENS clause chunk} to the query.
	 * 
	 * @param tokens the clause tokens
	 * @see JRDesignQueryChunk#setTokens(String[])
	 */
	public void addClauseChunk(String[] tokens)
	{
		JRDesignQueryChunk chunk = new JRDesignQueryChunk();
		chunk.setType(JRQueryChunk.TYPE_CLAUSE_TOKENS);
		chunk.setTokens(tokens);

		this.chunks.add(chunk);
	}

	/**
	 *
	 */
	public void setText(String text)
	{
		Object old = getText();
		chunks = new ArrayList();
		JRQueryParser.instance().parse(text, chunkAdder);
		getEventSupport().firePropertyChange(PROPERTY_TEXT, old, getText());
	}
			

	/**
	 * Sets the query language.
	 * 
	 * @param language the query language
	 * @see net.sf.jasperreports.engine.JRQuery#getLanguage()
	 */
	public void setLanguage(String language)
	{
		String oldValue = this.language;
		this.language = language;
		getPropertyChangeSupport().firePropertyChange(PROPERTY_LANGUAGE, oldValue, this.language);
	}

	public JRPropertyChangeSupport getEventSupport()
	{
		synchronized (this)
		{
			if (propSupport == null)
			{
				propSupport = new JRPropertyChangeSupport(this);
			}
		}
		
		return propSupport;
	}
	
	/**
	 * Get the property change support object for this class.  Because the
	 * property change support object has to be transient, it may need to be
	 * created.
	 * 
	 * @return the property change support object.
	 */
	protected PropertyChangeSupport getPropertyChangeSupport()
	{
		return getEventSupport();
	}

	/**
	 * Add a property listener to listen to all properties of this class.
	 * @param l The property listener to add.
	 * @see #removePropertyChangeListener(PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener l)
	{
		getPropertyChangeSupport().addPropertyChangeListener(l);
	}

	/**
	 * Add a property listener to receive property change events for only one specific
	 * property.
	 * @param propName The property to listen to.
	 * @param l The property listener to add.
	 * @see #removePropertyChangeListener(String, PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propName, PropertyChangeListener l)
	{
		getPropertyChangeSupport().addPropertyChangeListener(propName, l);
	}

	/**
	 * Remove a property change listener registered for all properties.
	 * 
	 * This will only remove listeners that were added through the 
	 * {@link #addPropertyChangeListener(PropertyChangeListener) addPropertyChangeListener(PropertyChangeListener)}
	 * method.
	 * 
	 * @param l The listener to remove.
	 */
	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		getPropertyChangeSupport().removePropertyChangeListener(l);
	}

	/**
	 * Remove a property change listener registered for a specific property.
	 * 
	 * @param propName The property to listen to.
	 * @param l The listener to remove.
	 */
	public void removePropertyChangeListener(String propName, PropertyChangeListener l)
	{
		getPropertyChangeSupport().removePropertyChangeListener(propName, l);
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRDesignQuery clone = (JRDesignQuery)super.clone();

		if (chunks != null)
		{
			clone.chunks = new ArrayList(chunks.size());
			for(int i = 0; i < chunks.size(); i++)
			{
				clone.chunks.add(((JRQueryChunk)chunks.get(i)).clone());
			}
		}

		return clone;
	}
}
