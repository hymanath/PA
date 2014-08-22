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

import net.sf.jasperreports.charts.JRCategoryDataset;
import net.sf.jasperreports.charts.JRCategorySeries;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseChartDataset;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.JRVerifier;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseCategoryDataset.java 3938 2010-08-19 14:59:36Z teodord $
 */
public class JRBaseCategoryDataset extends JRBaseChartDataset implements JRCategoryDataset
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRCategorySeries[] categorySeries;

	
	/**
	 *
	 */
	public JRBaseCategoryDataset(JRChartDataset dataset)
	{
		super(dataset);
	}
	
	
	/**
	 *
	 */
	public JRBaseCategoryDataset(JRCategoryDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);

		/*   */
		JRCategorySeries[] srcCategorySeries = dataset.getSeries();
		if (srcCategorySeries != null && srcCategorySeries.length > 0)
		{
			categorySeries = new JRCategorySeries[srcCategorySeries.length];
			for(int i = 0; i < categorySeries.length; i++)
			{
				categorySeries[i] = factory.getCategorySeries(srcCategorySeries[i]);
			}
		}

	}

	
	/**
	 *
	 */
	public JRCategorySeries[] getSeries()
	{
		return categorySeries;
	}


	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRChartDataset#getDatasetType()
	 */
	public byte getDatasetType() {
		return JRChartDataset.CATEGORY_DATASET;
	}

	
	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
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
		JRBaseCategoryDataset clone = (JRBaseCategoryDataset)super.clone();
		
		if (categorySeries != null)
		{
			clone.categorySeries = new JRCategorySeries[categorySeries.length];
			for(int i = 0; i < categorySeries.length; i++)
			{
				clone.categorySeries[i] = (JRCategorySeries)categorySeries[i].clone();
			}
		}
		
		return clone;
	}

}
