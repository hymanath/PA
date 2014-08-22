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

/*
 * Contributors:
 * Greg Hilton
 */

package net.sf.jasperreports.engine.export.ooxml;

import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.export.ExporterFilter;
import net.sf.jasperreports.engine.util.JRProperties;

/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: JRDocxExporterNature.java 3910 2010-07-20 11:10:57Z teodord $
 */
public class JRDocxExporterNature extends JROfficeOpenXmlExporterNature
{

	private final boolean deepGrid;

	/**
	 *
	 */
	public JRDocxExporterNature(ExporterFilter filter, boolean deepGrid)
	{
		super(filter);
		
		this.deepGrid = deepGrid;
	}

	/**
	 * 
	 */
	public boolean isDeep(JRPrintFrame frame)
	{
		if (
			frame.hasProperties()
			&& frame.getPropertiesMap().containsProperty(JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES)
			)
		{
			// we make this test to avoid reaching the global default value of the property directly
			// and thus skipping the report level one, if present
			return !JRProperties.getBooleanProperty(frame, JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES, !deepGrid);
		}
		return deepGrid;
	}

}
