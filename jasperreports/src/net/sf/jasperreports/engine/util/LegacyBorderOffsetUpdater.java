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
package net.sf.jasperreports.engine.util;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.legacy.BorderOffset;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRAntCompileTask.java 1606 2007-02-28 08:21:12Z lucianc $
 */
public class LegacyBorderOffsetUpdater implements ReportUpdater
{

	/**
	 * 
	 */
	public JasperDesign update(JasperDesign jasperDesign)
	{
		jasperDesign.setProperty(BorderOffset.PROPERTY_LEGACY_BORDER_OFFSET, "true");
		return jasperDesign;
	}

}
