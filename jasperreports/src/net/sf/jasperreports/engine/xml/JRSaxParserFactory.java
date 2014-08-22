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
package net.sf.jasperreports.engine.xml;

import javax.xml.parsers.SAXParser;

import net.sf.jasperreports.engine.util.JRProperties;

/**
 * A factory of {@link SAXParser} objects used by JasperReports
 * parsers/digesters.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRSaxParserFactory.java 3034 2009-08-27 11:58:04Z teodord $
 */
public interface JRSaxParserFactory
{

	/**
	 * A property that gives a parser factory class which should be used
	 * for parsing JRXMLs.
	 * 
	 * <p>
	 * By default, this property is set to use {@link JRReportSaxParserFactory}
	 * as report parser factory.
	 */
	String PROPERTY_REPORT_PARSER_FACTORY = JRProperties.PROPERTY_PREFIX + "compiler.xml.parser.factory";
	
	/**
	 * Creates a SAX parser.
	 * 
	 * @return the created parser
	 */
	SAXParser createParser();
	
}
