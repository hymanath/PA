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

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPieSeries.java 3032 2009-08-27 11:32:28Z teodord $
 */
public interface JRPieSeries extends JRCloneable
{
	
	/**
	 * 
	 */
	public JRExpression getKeyExpression();

	/**
	 * 
	 */
	public JRExpression getValueExpression();

	/**
	 * 
	 */
	public JRExpression getLabelExpression();

	/**
	 * Returns the hyperlink specification for chart sections.
	 * <p>
	 * The hyperlink will be evaluated for every chart section and an image map will be created for the chart.
	 * </p>
	 * 
	 * @return hyperlink specification for chart sections
	 */
	public JRHyperlink getSectionHyperlink();

}
