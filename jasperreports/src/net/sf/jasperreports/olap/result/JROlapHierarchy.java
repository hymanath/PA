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
package net.sf.jasperreports.olap.result;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JROlapHierarchy.java 3220 2009-11-25 18:31:39Z swood $
 */
public interface JROlapHierarchy
{
	
	String getDimensionName();

    // MPenningroth 21-April-2009 deal with case when dimension is <dimension>.<hierarchy> form
    String getHierarchyUniqueName();
	
	JROlapHierarchyLevel[] getLevels();
	
}
