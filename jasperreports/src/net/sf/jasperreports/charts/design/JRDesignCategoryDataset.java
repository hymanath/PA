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
package net.sf.jasperreports.charts.design;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRCategoryDataset;
import net.sf.jasperreports.charts.JRCategorySeries;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRVerifier;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRDesignCategoryDataset.java 4136 2011-01-11 15:47:51Z chicuslavic $
 */
public class JRDesignCategoryDataset extends JRDesignChartDataset implements JRCategoryDataset
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_CATEGORY_SERIES = "categorySeries";

	private List categorySeriesList = new ArrayList();


	/**
	 *
	 */
	public JRDesignCategoryDataset(JRChartDataset dataset)
	{
		super(dataset);
	}


	/**
	 *
	 */
	public JRCategorySeries[] getSeries()
	{
		JRCategorySeries[] categorySeriesArray = new JRCategorySeries[categorySeriesList.size()];
		
		categorySeriesList.toArray(categorySeriesArray);

		return categorySeriesArray;
	}
	

	/**
	 * 
	 */
	public List getSeriesList()
	{
		return categorySeriesList;
	}

	
	/**
	 *
	 */
	public void addCategorySeries(JRCategorySeries categorySeries)
	{
		categorySeriesList.add(categorySeries);
		getEventSupport().fireCollectionElementAddedEvent(PROPERTY_CATEGORY_SERIES, 
				categorySeries, categorySeriesList.size() - 1);
	}
	
	/**
	 *
	 */
	public void addCategorySeries(int index, JRCategorySeries categorySeries)
	{
		categorySeriesList.add(index, categorySeries);
		getEventSupport().fireCollectionElementAddedEvent(PROPERTY_CATEGORY_SERIES, 
				categorySeries, index);
	}

	/**
	 *
	 */
	public JRCategorySeries removeCategorySeries(JRCategorySeries categorySeries)
	{
		if (categorySeries != null)
		{
			int idx = categorySeriesList.indexOf(categorySeries);
			if (idx >= 0)
			{
				categorySeriesList.remove(idx);
				getEventSupport().fireCollectionElementRemovedEvent(PROPERTY_CATEGORY_SERIES, 
						categorySeries, idx);
			}
		}
		
		return categorySeries;
	}


	/** 
	 * 
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
		JRDesignCategoryDataset clone = (JRDesignCategoryDataset)super.clone();
		
		if (categorySeriesList != null)
		{
			clone.categorySeriesList = new ArrayList(categorySeriesList.size());
			for(int i = 0; i < categorySeriesList.size(); i++)
			{
				clone.categorySeriesList.add(((JRCategorySeries)categorySeriesList.get(i)).clone());
			}
		}

		return clone;
	}
	
}
