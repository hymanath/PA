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
package net.sf.jasperreports.engine.fill;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;


/**
 * Utility class to be used to evaluate parameter default value expressions for a report
 * without actually filling it.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRParameterDefaultValuesEvaluator.java 3713 2010-04-08 11:06:05Z teodord $
 */
public final class JRParameterDefaultValuesEvaluator
{

	/**
	 * Evaluates the default values for the parameters of a report.
	 * 
	 * @param report the report
	 * @param initialParameters initial parameter value map
	 * @return a map containing parameter values indexed by parameter names
	 * @throws JRException
	 */
	public static Map evaluateParameterDefaultValues(JasperReport report, Map initialParameters) throws JRException
	{
		Map valuesMap = initialParameters == null ? new HashMap() : new HashMap(initialParameters);
		
		valuesMap.put(JRParameter.JASPER_REPORT, report);
		
		ObjectFactory factory = new ObjectFactory();
		JRDataset reportDataset = report.getMainDataset();
		JRFillDataset fillDataset = factory.getDataset(reportDataset);
		fillDataset.createCalculator(report);
		fillDataset.initCalculator();

		JRResourcesFillUtil.ResourcesFillContext resourcesContext = 
			JRResourcesFillUtil.setResourcesFillContext(valuesMap);
		try
		{
			fillDataset.setParameterValues(valuesMap);
			
			Map parameterValues = new HashMap();
			JRParameter[] parameters = reportDataset.getParameters();
			for (int i = 0; i < parameters.length; i++)
			{
				JRParameter param = parameters[i];
				if (!param.isSystemDefined())
				{
					String name = param.getName();
					Object value = fillDataset.getParameterValue(name);
					parameterValues.put(name, value);
				}
			}
			
			return parameterValues;
		}
		finally
		{
			JRResourcesFillUtil.revertResourcesFillContext(resourcesContext);
		}
	}
	
	protected static class ObjectFactory extends JRFillObjectFactory
	{
		protected ObjectFactory()
		{
			super((JRBaseFiller) null, null);
		}

		protected JRFillGroup getGroup(JRGroup group)
		{
			return super.getGroup(null);
		}
	}
	

	private JRParameterDefaultValuesEvaluator()
	{
	}
}
