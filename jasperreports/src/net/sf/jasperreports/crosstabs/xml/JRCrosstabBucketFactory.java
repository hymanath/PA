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
package net.sf.jasperreports.crosstabs.xml;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.engine.type.SortOrderEnum;
import net.sf.jasperreports.engine.xml.JRBaseFactory;

import org.xml.sax.Attributes;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRCrosstabBucketFactory.java 3500 2010-03-03 15:56:41Z teodord $
 */
public class JRCrosstabBucketFactory extends JRBaseFactory
{
	public static final String ELEMENT_bucket = "bucket";
	public static final String ELEMENT_bucketExpression = "bucketExpression";
	public static final String ELEMENT_orderByExpression = "orderByExpression";
	public static final String ELEMENT_comparatorExpression = "comparatorExpression";
	
	public static final String ATTRIBUTE_order = "order";
	
	public Object createObject(Attributes attributes)
	{
		JRDesignCrosstabBucket bucket = new JRDesignCrosstabBucket();
		
		SortOrderEnum order = SortOrderEnum.getByName(attributes.getValue(ATTRIBUTE_order));
		if (order != null)
		{
			bucket.setOrder(order);
		}
		
		return bucket;
	}

}
