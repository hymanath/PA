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

import net.sf.jasperreports.engine.JRChartPlot;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPiePlot.java 3948 2010-09-01 12:07:07Z shertage $
 */
public interface JRPiePlot extends JRChartPlot
{
	/**
	 * @deprecated Replaced by {@link #getCircular()}
	 */
	public boolean isCircular();
	
	/**
	 * 
	 */
	public Boolean getCircular();
	
	/**
	 * 
	 */
	public String getLabelFormat();
	
	/**
	 * 
	 */
	public String getLegendLabelFormat();

	/**
	 * 
	 */
	public JRItemLabel getItemLabel();
	
	/**
	 * 
	 */
	public Boolean getShowLabels();
	
}
