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
package net.sf.jasperreports.components.table;

import java.util.List;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.JRExpression;

/**
 * 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BaseColumn.java 3602 2010-03-19 14:53:30Z lucianc $
 */
public interface BaseColumn extends JRCloneable
{

	JRExpression getPrintWhenExpression();
	
	Cell getTableHeader();
	
	Cell getTableFooter();
	
	List<GroupCell> getGroupHeaders();
	
	Cell getGroupHeader(String groupName);
	
	List<GroupCell> getGroupFooters();
	
	Cell getGroupFooter(String groupName);
	
	Cell getColumnHeader();
	
	Cell getColumnFooter();
	
	Integer getWidth();

	<R> R visitColumn(ColumnVisitor<R> visitor);

}
