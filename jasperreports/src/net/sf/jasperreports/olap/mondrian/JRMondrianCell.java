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
package net.sf.jasperreports.olap.mondrian;

import mondrian.olap.Cell;
import net.sf.jasperreports.olap.result.JROlapCell;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianCell.java 3035 2009-08-27 12:05:03Z teodord $
 */
public class JRMondrianCell implements JROlapCell
{

	private final Cell cell;
	
	public JRMondrianCell(Cell cell)
	{
		this.cell = cell;
	}

	public String getFormattedValue()
	{
		return cell.getFormattedValue();
	}

	public Object getValue()
	{
		return cell.getValue();
	}

	public boolean isError()
	{
		return cell.isError();
	}

	public boolean isNull()
	{
		return cell.isNull();
	}

}
