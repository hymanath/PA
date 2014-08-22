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

import net.sf.jasperreports.charts.JRHighLowDataset;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.base.JRBaseChartDataset;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.JRVerifier;


/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 * @version $Id: JRBaseHighLowDataset.java 3032 2009-08-27 11:32:28Z teodord $
 */
public class JRBaseHighLowDataset extends JRBaseChartDataset implements JRHighLowDataset
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRExpression seriesExpression;
	protected JRExpression dateExpression;
	protected JRExpression highExpression;
	protected JRExpression lowExpression;
	protected JRExpression openExpression;
	protected JRExpression closeExpression;
	protected JRExpression volumeExpression;
	private JRHyperlink itemHyperlink;


	/**
	 *
	 */
	public JRBaseHighLowDataset(JRChartDataset dataset)
	{
		super(dataset);
	}


	/**
	 *
	 */
	public JRBaseHighLowDataset(JRHighLowDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);

		seriesExpression = factory.getExpression(dataset.getSeriesExpression());
		dateExpression = factory.getExpression(dataset.getDateExpression());
		highExpression = factory.getExpression(dataset.getHighExpression());
		lowExpression = factory.getExpression(dataset.getLowExpression());
		openExpression = factory.getExpression(dataset.getOpenExpression());
		closeExpression = factory.getExpression(dataset.getCloseExpression());
		volumeExpression = factory.getExpression(dataset.getVolumeExpression());
		itemHyperlink = factory.getHyperlink(dataset.getItemHyperlink());
	}



	public JRExpression getSeriesExpression()
	{
		return seriesExpression;
	}


	public JRExpression getDateExpression()
	{
		return dateExpression;
	}


	public JRExpression getHighExpression()
	{
		return highExpression;
	}


	public JRExpression getLowExpression()
	{
		return lowExpression;
	}


	public JRExpression getOpenExpression()
	{
		return openExpression;
	}


	public JRExpression getCloseExpression()
	{
		return closeExpression;
	}

	public JRExpression getVolumeExpression()
	{
		return volumeExpression;
	}


	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRChartDataset#getDatasetType()
	 */
	public byte getDatasetType() {
		return JRChartDataset.HIGHLOW_DATASET;
	}


	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	
	public JRHyperlink getItemHyperlink()
	{
		return itemHyperlink;
	}


	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}

	
	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseHighLowDataset clone = (JRBaseHighLowDataset)super.clone();
		
		if (seriesExpression != null)
		{
			clone.seriesExpression = (JRExpression)seriesExpression.clone();
		}
		if (dateExpression != null)
		{
			clone.dateExpression = (JRExpression)dateExpression.clone();
		}
		if (highExpression != null)
		{
			clone.highExpression = (JRExpression)highExpression.clone();
		}
		if (lowExpression != null)
		{
			clone.lowExpression = (JRExpression)lowExpression.clone();
		}
		if (openExpression != null)
		{
			clone.openExpression = (JRExpression)openExpression.clone();
		}
		if (closeExpression != null)
		{
			clone.closeExpression = (JRExpression)closeExpression.clone();
		}
		if (volumeExpression != null)
		{
			clone.volumeExpression = (JRExpression)volumeExpression.clone();
		}
		if (itemHyperlink != null)
		{
			clone.itemHyperlink = (JRHyperlink)itemHyperlink.clone();
		}
		
		return clone;
	}
}
