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

import javax.xml.parsers.ParserConfigurationException;

import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.util.JRProperties;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.commons.digester.RuleSetBase;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * Factory for template XML digesters.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRXmlTemplateDigesterFactory.java 3616 2010-03-24 14:20:41Z teodord $
 * @see JRTemplate
 */
public class JRXmlTemplateDigesterFactory implements ErrorHandler
{

	protected static final String PATTERN_ROOT = JRXmlConstants.TEMPLATE_ELEMENT_ROOT;
	protected static final String PATTERN_INCLUDED_TEMPLATE = PATTERN_ROOT + "/" + JRXmlConstants.TEMPLATE_ELEMENT_INCLUDED_TEMPLATE;
	protected static final String PATTERN_STYLE = PATTERN_ROOT + "/" + JRXmlConstants.ELEMENT_style;
	protected static final String PATTERN_STYLE_PEN = PATTERN_STYLE + "/" + JRXmlConstants.ELEMENT_pen;
	protected static final String PATTERN_BOX = PATTERN_STYLE + "/" + JRXmlConstants.ELEMENT_box;
	protected static final String PATTERN_BOX_PEN = PATTERN_BOX + "/" + JRXmlConstants.ELEMENT_pen;
	protected static final String PATTERN_BOX_TOP_PEN = PATTERN_BOX + "/" + JRXmlConstants.ELEMENT_topPen;
	protected static final String PATTERN_BOX_LEFT_PEN = PATTERN_BOX + "/" + JRXmlConstants.ELEMENT_leftPen;
	protected static final String PATTERN_BOX_BOTTOM_PEN = PATTERN_BOX + "/" + JRXmlConstants.ELEMENT_bottomPen;
	protected static final String PATTERN_BOX_RIGHT_PEN = PATTERN_BOX + "/" + JRXmlConstants.ELEMENT_rightPen;
	
	private static final JRXmlTemplateDigesterFactory instance = new JRXmlTemplateDigesterFactory();
	
	private final RuleSet rules;

	protected JRXmlTemplateDigesterFactory() 
	{
		rules = readRuleSet();
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static JRXmlTemplateDigesterFactory instance()
	{
		return instance;
	}

	protected RuleSet readRuleSet()
	{
		return new RuleSetBase()
		{
			public void addRuleInstances(Digester digester)
			{
				digester.addObjectCreate(PATTERN_ROOT, JRSimpleTemplate.class);
				
				digester.addCallMethod(PATTERN_INCLUDED_TEMPLATE, "addIncludedTemplate", 0);
				
				digester.addFactoryCreate(PATTERN_STYLE, JRTemplateStyleFactory.class);
				digester.addSetNext(PATTERN_STYLE, "addStyle", JRStyle.class.getName());
				
				digester.addFactoryCreate(PATTERN_STYLE_PEN, JRPenFactory.Style.class.getName());
				
				digester.addFactoryCreate(PATTERN_BOX, JRBoxFactory.class.getName());
				digester.addFactoryCreate(PATTERN_BOX_PEN, JRPenFactory.Box.class.getName());
				digester.addFactoryCreate(PATTERN_BOX_TOP_PEN, JRPenFactory.Top.class.getName());
				digester.addFactoryCreate(PATTERN_BOX_LEFT_PEN, JRPenFactory.Left.class.getName());
				digester.addFactoryCreate(PATTERN_BOX_BOTTOM_PEN, JRPenFactory.Bottom.class.getName());
				digester.addFactoryCreate(PATTERN_BOX_RIGHT_PEN, JRPenFactory.Right.class.getName());
			}
		};
	}
	
	/**
	 * Creates and configures a digester for template XML.
	 * 
	 * @return a template XML digester
	 */
	public JRXmlDigester createDigester()
	{
		JRXmlDigester digester = new JRXmlDigester();
		try
		{
			configureDigester(digester);
		}
		catch (SAXException e)
		{
			throw new JRRuntimeException(e);
		}
		catch (ParserConfigurationException e)
		{
			throw new JRRuntimeException(e);
		}
		return digester;
	}

	protected void configureDigester(Digester digester) throws SAXException, ParserConfigurationException 
	{
		boolean validating = JRProperties.getBooleanProperty(JRProperties.COMPILER_XML_VALIDATION);
		
		digester.setErrorHandler(this);
		digester.setValidating(validating);
		digester.setFeature("http://xml.org/sax/features/validation", validating);

		digester.addRuleSet(rules);
	}
	
	public void error(SAXParseException exception) throws SAXException 
	{
		throw exception;
	}

	public void fatalError(SAXParseException exception) throws SAXException 
	{
		throw exception;
	}

	public void warning(SAXParseException exception) throws SAXException 
	{
		throw exception;
	}
	
}
