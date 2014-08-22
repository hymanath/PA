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
package net.sf.jasperreports.charts.fill;

import net.sf.jasperreports.charts.JRDataRange;
import net.sf.jasperreports.charts.JRMeterPlot;
import net.sf.jasperreports.charts.JRValueDisplay;
import net.sf.jasperreports.charts.type.MeterShapeEnum;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.fill.JRFillChartPlot;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

import java.awt.Color;
import java.util.List;

/**
 * @author Barry Klawans (bklawans@users.sourceforge.net)
 * @version $Id: JRFillMeterPlot.java 3715 2010-04-08 18:08:49Z teodord $
 */
public class JRFillMeterPlot extends JRFillChartPlot implements JRMeterPlot
{

	/**
	 *
	 */
	public JRFillMeterPlot(JRMeterPlot meterPlot, JRFillObjectFactory factory)
	{
		super(meterPlot, factory);
	}

	/**
	 *
	 */
	public JRDataRange getDataRange()
	{
		return ((JRMeterPlot)parent).getDataRange();
	}

	/**
	 *
	 */
	public JRValueDisplay getValueDisplay()
	{
		return ((JRMeterPlot)parent).getValueDisplay();
	}

	/**
	 * @deprecated Replaced by {@link #getShapeValue()}
	 */
	public byte getShape()
	{
		return getShapeValue() == null ? MeterShapeEnum.PIE.getValue() : getShapeValue().getValue();
	}

	/**
	 * @deprecated Replaced by {@link #getShapeValue()}
	 */
	public Byte getShapeByte()
	{
		return getShapeValue() == null ? null : getShapeValue().getValueByte();
	}

	/**
	 * 
	 */
	public MeterShapeEnum getShapeValue()
	{
		return ((JRMeterPlot)parent).getShapeValue();
	}

	/**
	 *
	 */
	public List getIntervals(){
		return ((JRMeterPlot)parent).getIntervals();
	}

	/**
	 * @deprecated Replaced by {@link #getMeterAngleInteger()}
	 */
	public int getMeterAngle()
	{
		return ((JRMeterPlot)parent).getMeterAngle();
	}

	/**
	 * 
	 */
	public Integer getMeterAngleInteger()
	{
		return ((JRMeterPlot)parent).getMeterAngleInteger();
	}

	/**
	 *
	 */
	public String getUnits()
	{
		return ((JRMeterPlot)parent).getUnits();
	}

	/**
	 * @deprecated Replaced by {@link #getTickIntervalDouble()}
	 */
	public double getTickInterval()
	{
		return ((JRMeterPlot)parent).getTickInterval();
	}

	/**
	 *
	 */
	public Double getTickIntervalDouble()
	{
		return ((JRMeterPlot)parent).getTickIntervalDouble();
	}

	/**
	 *
	 */
	public Color getMeterBackgroundColor()
	{
		return ((JRMeterPlot)parent).getMeterBackgroundColor();
	}

	/**
	 *
	 */
	public Color getNeedleColor()
	{
		return ((JRMeterPlot)parent).getNeedleColor();
	}

	/**
	 *
	 */
	public Color getTickColor()
	{
		return ((JRMeterPlot)parent).getTickColor();
	}

	public JRFont getTickLabelFont()
	{
		return ((JRMeterPlot)parent).getTickLabelFont();
	}
}
