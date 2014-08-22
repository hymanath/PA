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
package net.sf.jasperreports.crosstabs.base;

import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.base.JRBaseParameter;

/**
 * Base read-only implementation of crosstab parameters.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstabParameter.java 3032 2009-08-27 11:32:28Z teodord $
 */
public class JRBaseCrosstabParameter extends JRBaseParameter implements JRCrosstabParameter
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRExpression valueExpression;
	
	protected JRBaseCrosstabParameter()
	{
	}

	public JRBaseCrosstabParameter(JRCrosstabParameter parameter, JRBaseObjectFactory factory)
	{
		super(parameter, factory);
		
		valueExpression = factory.getExpression(parameter.getExpression());
	}
	
	public JRExpression getExpression()
	{
		return valueExpression;
	}
	
	public Object clone()
	{
		JRBaseCrosstabParameter clone = (JRBaseCrosstabParameter) super.clone();
		if (valueExpression != null)
		{
			clone.valueExpression = (JRExpression) valueExpression.clone();
		}
		return clone;
	}
}
