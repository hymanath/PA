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
package net.sf.jasperreports.engine;


/**
 * An abstract representation of a report level font. Report fonts are different from normal fonts because they can
 * be defined once at report level and then simply referenced by many text elements (they can be reused).
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRReportFont.java 3033 2009-08-27 11:46:22Z teodord $
 */
public interface JRReportFont extends JRFont
{


	/**
	 * Gets the font unique name.
	 */
	public String getName();
	
	/**
	 * Gets a flag that specifies if this is the default report font.
	 */
	public boolean isDefault();
	

}
