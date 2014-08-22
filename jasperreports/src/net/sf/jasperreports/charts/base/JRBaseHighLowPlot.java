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
package net.sf.jasperreports.charts.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.charts.JRHighLowPlot;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRStyleResolver;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRBaseHighLowPlot.java 3941 2010-08-20 10:55:10Z teodord $
 */
public class JRBaseHighLowPlot extends JRBaseChartPlot implements JRHighLowPlot
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_SHOW_CLOSE_TICKS = "showCloseTicks";
	
	public static final String PROPERTY_SHOW_OPEN_TICKS = "showOpenTicks";

	protected JRExpression timeAxisLabelExpression;
	protected JRFont timeAxisLabelFont;
	protected Color timeAxisLabelColor;
	protected JRFont timeAxisTickLabelFont;
	protected Color timeAxisTickLabelColor;
	protected String timeAxisTickLabelMask;
	protected Boolean timeAxisVerticalTickLabels;
	protected Color timeAxisLineColor;

	protected JRExpression valueAxisLabelExpression;
	protected JRExpression rangeAxisMinValueExpression;
	protected JRExpression rangeAxisMaxValueExpression;
	protected JRExpression domainAxisMinValueExpression;
	protected JRExpression domainAxisMaxValueExpression;
	protected JRFont valueAxisLabelFont;
	protected Color valueAxisLabelColor;
	protected JRFont valueAxisTickLabelFont;
	protected Color valueAxisTickLabelColor;
	protected String valueAxisTickLabelMask;
	protected Boolean valueAxisVerticalTickLabels;
	protected Color valueAxisLineColor;

	protected Boolean showOpenTicks;
	protected Boolean showCloseTicks;


	/**
	 *
	 */
	public JRBaseHighLowPlot(JRChartPlot plot, JRChart chart)
	{
		super(plot, chart);
		
		JRHighLowPlot highLowPlot = plot instanceof JRHighLowPlot ? (JRHighLowPlot)plot : null;
		if (highLowPlot == null)
		{
			timeAxisLabelFont = new JRBaseFont(chart, null);
			timeAxisTickLabelFont = new JRBaseFont(chart, null);
			valueAxisLabelFont = new JRBaseFont(chart, null);
			valueAxisTickLabelFont = new JRBaseFont(chart, null);
		}
		else
		{
			timeAxisLabelFont = new JRBaseFont(chart, highLowPlot.getTimeAxisLabelFont());
			timeAxisTickLabelFont = new JRBaseFont(chart, highLowPlot.getTimeAxisTickLabelFont());
			valueAxisLabelFont = new JRBaseFont(chart, highLowPlot.getValueAxisLabelFont());
			valueAxisTickLabelFont = new JRBaseFont(chart, highLowPlot.getValueAxisTickLabelFont());
		}
	}


	/**
	 *
	 */
	public JRBaseHighLowPlot(JRHighLowPlot highLowPlot, JRBaseObjectFactory factory)
	{
		super(highLowPlot, factory);

		showOpenTicks = highLowPlot.getShowOpenTicks();
		showCloseTicks = highLowPlot.getShowCloseTicks();

		timeAxisLabelExpression = factory.getExpression( highLowPlot.getTimeAxisLabelExpression() );
		timeAxisLabelFont = new JRBaseFont(highLowPlot.getChart(), highLowPlot.getTimeAxisLabelFont());
		timeAxisLabelColor = highLowPlot.getOwnTimeAxisLabelColor();
		timeAxisTickLabelFont = new JRBaseFont(highLowPlot.getChart(), highLowPlot.getTimeAxisTickLabelFont());
		timeAxisTickLabelColor = highLowPlot.getOwnTimeAxisTickLabelColor();
		timeAxisTickLabelMask = highLowPlot.getTimeAxisTickLabelMask();
		timeAxisVerticalTickLabels = highLowPlot.getTimeAxisVerticalTickLabels();
		timeAxisLineColor = highLowPlot.getOwnTimeAxisLineColor();
		
		valueAxisLabelExpression = factory.getExpression( highLowPlot.getValueAxisLabelExpression() );
		domainAxisMinValueExpression = factory.getExpression( highLowPlot.getDomainAxisMinValueExpression() );
		domainAxisMaxValueExpression = factory.getExpression( highLowPlot.getDomainAxisMaxValueExpression() );
		rangeAxisMinValueExpression = factory.getExpression( highLowPlot.getRangeAxisMinValueExpression() );
		rangeAxisMaxValueExpression = factory.getExpression( highLowPlot.getRangeAxisMaxValueExpression() );
		valueAxisLabelFont = new JRBaseFont(highLowPlot.getChart(), highLowPlot.getValueAxisLabelFont());
		valueAxisLabelColor = highLowPlot.getOwnValueAxisLabelColor();
		valueAxisTickLabelFont = new JRBaseFont(highLowPlot.getChart(), highLowPlot.getValueAxisTickLabelFont());
		valueAxisTickLabelColor = highLowPlot.getOwnValueAxisTickLabelColor();
		valueAxisTickLabelMask = highLowPlot.getValueAxisTickLabelMask();
		valueAxisVerticalTickLabels = highLowPlot.getValueAxisVerticalTickLabels();
		valueAxisLineColor = highLowPlot.getOwnValueAxisTickLabelColor();
	}


	/**
	 * 
	 */
	public JRExpression getTimeAxisLabelExpression(){
		return timeAxisLabelExpression;
	}
	
	/**
	 * 
	 */
	public JRFont getTimeAxisLabelFont()
	{
		return timeAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getTimeAxisLabelColor()
	{
		return JRStyleResolver.getTimeAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnTimeAxisLabelColor()
	{
		return timeAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getTimeAxisTickLabelFont()
	{
		return timeAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getTimeAxisTickLabelColor()
	{
		return JRStyleResolver.getTimeAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnTimeAxisTickLabelColor()
	{
		return timeAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getTimeAxisTickLabelMask()
	{
		return timeAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Boolean getTimeAxisVerticalTickLabels()
	{
		return timeAxisVerticalTickLabels;
	}

	/**
	 * 
	 */
	public Color getTimeAxisLineColor()
	{
		return JRStyleResolver.getTimeAxisLineColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnTimeAxisLineColor()
	{
		return timeAxisLineColor;
	}

	/**
	 * 
	 */
	public JRExpression getValueAxisLabelExpression(){
		return valueAxisLabelExpression;
	}

	/**
	 * 
	 */
	public JRExpression getDomainAxisMinValueExpression(){
		return domainAxisMinValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getDomainAxisMaxValueExpression(){
		return domainAxisMaxValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMinValueExpression(){
		return rangeAxisMinValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMaxValueExpression(){
		return rangeAxisMaxValueExpression;
	}
	/**
	 * 
	 */
	public JRFont getValueAxisLabelFont()
	{
		return valueAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisLabelColor()
	{
		return JRStyleResolver.getValueAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLabelColor()
	{
		return valueAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getValueAxisTickLabelFont()
	{
		return valueAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisTickLabelColor()
	{
		return JRStyleResolver.getValueAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnValueAxisTickLabelColor()
	{
		return valueAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getValueAxisTickLabelMask()
	{
		return valueAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Boolean getValueAxisVerticalTickLabels()
	{
		return valueAxisVerticalTickLabels;
	}

	/**
	 * 
	 */
	public Color getValueAxisLineColor()
	{
		return JRStyleResolver.getValueAxisLineColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLineColor()
	{
		return valueAxisLineColor;
	}
	
	/**
	 * @deprecated Replaced by {@link #getShowOpenTicks()}
	 */
	public boolean isShowOpenTicks()
	{
		return showOpenTicks == null ? false : showOpenTicks.booleanValue();
	}

	/**
	 * 
	 */
	public Boolean getShowOpenTicks()
	{
		return showOpenTicks;
	}


	/**
	 * @deprecated Replaced by {@link #setShowOpenTicks(Boolean)}
	 */
	public void setShowOpenTicks(boolean showOpenTicks)
	{
		setShowOpenTicks(Boolean.valueOf(showOpenTicks));
	}

	/**
	 * 
	 */
	public void setShowOpenTicks(Boolean showOpenTicks)
	{
		Boolean old = this.showOpenTicks;
		this.showOpenTicks = showOpenTicks;
		getEventSupport().firePropertyChange(PROPERTY_SHOW_OPEN_TICKS, old, this.showOpenTicks);
	}

	/**
	 * @deprecated Replaced by {@link #getShowCloseTicks()}
	 */
	public boolean isShowCloseTicks()
	{
		return showCloseTicks == null ? false : showCloseTicks.booleanValue();
	}

	/**
	 * 
	 */
	public Boolean getShowCloseTicks()
	{
		return showCloseTicks;
	}

	/**
	 * @deprecated Replaced by {@link #setShowCloseTicks(Boolean)}
	 */
	public void setShowCloseTicks(boolean showCloseTicks)
	{
		setShowCloseTicks(Boolean.valueOf(showCloseTicks));
	}

	/**
	 * 
	 */
	public void setShowCloseTicks(Boolean showCloseTicks)
	{
		Boolean old = this.showCloseTicks;
		this.showCloseTicks = showCloseTicks;
		getEventSupport().firePropertyChange(PROPERTY_SHOW_CLOSE_TICKS, old, this.showCloseTicks);
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public Object clone(JRChart parentChart) 
	{
		JRBaseHighLowPlot clone = (JRBaseHighLowPlot)super.clone(parentChart);
		if (timeAxisLabelExpression != null)
		{
			clone.timeAxisLabelExpression = (JRExpression)timeAxisLabelExpression.clone();
		}
		if (valueAxisLabelExpression != null)
		{
			clone.valueAxisLabelExpression = (JRExpression)valueAxisLabelExpression.clone();
		}
		if (domainAxisMinValueExpression != null)
		{
			clone.domainAxisMinValueExpression = (JRExpression)domainAxisMinValueExpression.clone();
		}
		if (domainAxisMaxValueExpression != null)
		{
			clone.domainAxisMaxValueExpression = (JRExpression)domainAxisMaxValueExpression.clone();
		}
		if (rangeAxisMinValueExpression != null)
		{
			clone.rangeAxisMinValueExpression = (JRExpression)rangeAxisMinValueExpression.clone();
		}
		if (rangeAxisMaxValueExpression != null)
		{
			clone.rangeAxisMaxValueExpression = (JRExpression)rangeAxisMaxValueExpression.clone();
		}
		return clone;
	}
	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private boolean isShowOpenTicks;
	/**
	 * @deprecated
	 */
	private boolean isShowCloseTicks;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_1_3)
		{
			showOpenTicks = Boolean.valueOf(isShowOpenTicks);
			showCloseTicks = Boolean.valueOf(isShowCloseTicks);
		}
	}
	
}
