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
package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRPrintHyperlink;

/**
 * An adapter of {@link JRHtmlExporterContext} as {@link JRHyperlinkProducer}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: HtmlExporterHyperlinkProducerAdapter.java 3627 2010-03-25 17:51:07Z lucianc $
 */
public class HtmlExporterHyperlinkProducerAdapter implements JRHyperlinkProducer
{

	private final JRHtmlExporterContext exporterContext;
	
	/**
	 * Creates an adapter.
	 * 
	 * @param exporterContext the exporter context
	 */
	public HtmlExporterHyperlinkProducerAdapter(
			JRHtmlExporterContext exporterContext)
	{
		this.exporterContext = exporterContext;
	}

	public String getHyperlink(JRPrintHyperlink hyperlink)
	{
		return exporterContext.getHyperlinkURL(hyperlink);
	}

}
