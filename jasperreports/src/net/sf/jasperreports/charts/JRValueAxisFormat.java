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
package net.sf.jasperreports.charts;

import java.awt.Color;

import net.sf.jasperreports.engine.JRFont;

/**
 * @author Flavus Sana (flavius_sana@users.sourceforge.net) 
 * @version $Id: JRValueAxisFormat.java 3179 2009-10-30 10:03:01Z teodord $
 */
public interface JRValueAxisFormat 
{

	/**
	 * 
	 */
	public JRFont getValueAxisLabelFont();
	
	/**
	 * 
	 */
	public Color getValueAxisLabelColor();

	/**
	 * 
	 */
	public Color getOwnValueAxisLabelColor();

	/**
	 * 
	 */
	public JRFont getValueAxisTickLabelFont();
	
	/**
	 * 
	 */
	public Color getValueAxisTickLabelColor();
	
	/**
	 * 
	 */
	public Color getOwnValueAxisTickLabelColor();
	
	/**
	 * 
	 */
	public String getValueAxisTickLabelMask();
	
	/**
	 * 
	 */
	public Boolean getValueAxisVerticalTickLabels();
	
	/**
	 * 
	 */
	public Color getValueAxisLineColor();
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLineColor();
}
