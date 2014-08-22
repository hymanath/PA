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

import net.sf.jasperreports.engine.design.JRDesignReportFont;

import org.xml.sax.Attributes;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRReportFontFactory.java 3616 2010-03-24 14:20:41Z teodord $
 */
public class JRReportFontFactory extends JRBaseFactory
{

	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRDesignReportFont reportFont = new JRDesignReportFont();

		reportFont.setName(atts.getValue(JRXmlConstants.ATTRIBUTE_name));
		
		String isDefault = atts.getValue(JRXmlConstants.ATTRIBUTE_isDefault);
		if (isDefault != null && isDefault.length() > 0)
		{
			reportFont.setDefault(Boolean.valueOf(isDefault).booleanValue());
		}

		reportFont.setFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_fontName));

		String isBold = atts.getValue(JRXmlConstants.ATTRIBUTE_isBold);
		if (isBold != null && isBold.length() > 0)
		{
			reportFont.setBold(Boolean.valueOf(isBold));
		}

		String isItalic = atts.getValue(JRXmlConstants.ATTRIBUTE_isItalic);
		if (isItalic != null && isItalic.length() > 0)
		{
			reportFont.setItalic(Boolean.valueOf(isItalic));
		}

		String isUnderline = atts.getValue(JRXmlConstants.ATTRIBUTE_isUnderline);
		if (isUnderline != null && isUnderline.length() > 0)
		{
			reportFont.setUnderline(Boolean.valueOf(isUnderline));
		}

		String isStrikeThrough = atts.getValue(JRXmlConstants.ATTRIBUTE_isStrikeThrough);
		if (isStrikeThrough != null && isStrikeThrough.length() > 0)
		{
			reportFont.setStrikeThrough(Boolean.valueOf(isStrikeThrough));
		}

		String size = atts.getValue(JRXmlConstants.ATTRIBUTE_size);
		if (size != null && size.length() > 0)
		{
			reportFont.setFontSize(Integer.parseInt(size));
		}

		reportFont.setPdfFontName(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfFontName));
		reportFont.setPdfEncoding(atts.getValue(JRXmlConstants.ATTRIBUTE_pdfEncoding));

		String isPdfEmbedded = atts.getValue(JRXmlConstants.ATTRIBUTE_isPdfEmbedded);
		if (isPdfEmbedded != null && isPdfEmbedded.length() > 0)
		{
			reportFont.setPdfEmbedded(Boolean.valueOf(isPdfEmbedded));
		}

		return reportFont;
	}
	

}
