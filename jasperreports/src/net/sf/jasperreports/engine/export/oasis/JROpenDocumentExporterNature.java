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

package net.sf.jasperreports.engine.export.oasis;

import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.export.ExporterFilter;
import net.sf.jasperreports.engine.export.ExporterNature;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JROpenDocumentExporterNature.java 3939 2010-08-20 09:52:00Z teodord $
 */
public abstract class JROpenDocumentExporterNature implements ExporterNature
{
	
	public static final byte ODT_NATURE = 1;
	public static final byte ODS_NATURE = 2;
	
	protected ExporterFilter filter;

	/**
	 * 
	 */
	public JROpenDocumentExporterNature(ExporterFilter filter)
	{
		this.filter = filter;
	}
	
	/**
	 * 
	 */
	public boolean isToExport(JRPrintElement element)
	{
		return (filter == null || filter.isToExport(element));
	}
	
	/**
	 * 
	 */
	public boolean isDeep(JRPrintFrame frame)
	{
		return false;
	}
	
	/**
	 * 
	 */
	public boolean isSplitSharedRowSpan()
	{
		return true;
	}

	/**
	 * 
	 */
	public boolean isSpanCells()
	{
		return true;
	}
	
	/**
	 * 
	 */
	public boolean isIgnoreLastRow()
	{
		return true;
	}

	public boolean isHorizontallyMergeEmptyCells()
	{
		return false;
	}

	/**
	 * Specifies whether empty page margins should be ignored
	 */
	public boolean isIgnorePageMargins()
	{
		return false;
	}
	
	/**
	 *
	 */
	public boolean isBreakBeforeRow(JRPrintElement element)
	{
		return false;
	}
	
	/**
	 *
	 */
	public boolean isBreakAfterRow(JRPrintElement element)
	{
		return false;
	}
	
	protected abstract byte getOpenDocumentNature();
}
