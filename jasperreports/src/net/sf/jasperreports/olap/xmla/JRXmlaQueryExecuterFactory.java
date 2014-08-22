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
package net.sf.jasperreports.olap.xmla;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRQueryExecuter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;


/**
 * @author Michael G�nther (m.guenther at users.sourceforge.net)
 * @version $Id: JRXmlaQueryExecuterFactory.java 3778 2010-05-03 16:17:18Z lucianc $
 */
public class JRXmlaQueryExecuterFactory implements JRQueryExecuterFactory
{

	public final static String PARAMETER_XMLA_URL = "XMLA_URL";

	public final static String PARAMETER_XMLA_DATASOURCE = "XMLA_DATASOURCE";

	public final static String PARAMETER_XMLA_CATALOG = "XMLA_CATALOG";
	
	public final static String PARAMETER_XMLA_USER = "XMLA_USER";
	
	public final static String PARAMETER_XMLA_PASSWORD = "XMLA_PASSWORD";


	private final static Object[] XMLA_BUILTIN_PARAMETERS = { 
		PARAMETER_XMLA_URL, "java.lang.String", 
		PARAMETER_XMLA_DATASOURCE, "java.lang.String", 
		PARAMETER_XMLA_CATALOG, "java.lang.String",
		PARAMETER_XMLA_USER, "java.lang.String",
		PARAMETER_XMLA_PASSWORD, "java.lang.String",
	};

	public Object[] getBuiltinParameters()
	{
		return XMLA_BUILTIN_PARAMETERS;
	}

	public JRQueryExecuter createQueryExecuter(JRDataset dataset, Map parameters) throws JRException
	{
		return new JRXmlaQueryExecuter(dataset, parameters);
	}

	public boolean supportsQueryParameterType(String className)
	{
		return true;
	}

}
