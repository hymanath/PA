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

import net.sf.jasperreports.charts.type.ScaleTypeEnum;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRExpression;

/**
 * @author Flavius Sana (flavius_sana@users.sourceforge.net)
 * @version $Id: JRBubblePlot.java 3559 2010-03-10 10:31:16Z shertage $
 */
public interface JRBubblePlot extends JRChartPlot, JRXAxisFormat, JRYAxisFormat
{
	
	/**
	 * 
	 */
	public JRExpression getXAxisLabelExpression();

	/**
	 * 
	 */
	public JRExpression getYAxisLabelExpression();

	/**
	 * @deprecated Replaced by {@link #getScaleTypeValue()}
	 */
	public int getScaleType();

	/**
	 * @deprecated Replaced by {@link #getScaleTypeValue()}
	 */
	public Integer getScaleTypeInteger();

	/**
	 * 
	 */
	public ScaleTypeEnum getScaleTypeValue();

	/**
	 * @deprecated Replaced by {@link #setScaleType(ScaleTypeEnum)}
	 */
	public void setScaleType(int scaleType);
	
	/**
	 * @deprecated Replaced by {@link #setScaleType(ScaleTypeEnum)}
	 */
	public void setScaleType(Integer scaleType);

	/**
	 * 
	 */
	public void setScaleType(ScaleTypeEnum scaleType);

	/**
	 * 
	 */
	public JRExpression getDomainAxisMinValueExpression();

	/**
	 * 
	 */
	public JRExpression getDomainAxisMaxValueExpression();

	/**
	 * 
	 */
	public JRExpression getRangeAxisMinValueExpression();

	/**
	 * 
	 */
	public JRExpression getRangeAxisMaxValueExpression();

}
