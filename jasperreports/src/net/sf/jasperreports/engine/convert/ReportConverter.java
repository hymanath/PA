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

/*
 * Contributors:
 * Ryan Johnson - delscovich@users.sourceforge.net
 * Carlton Moore - cmoore79@users.sourceforge.net
 */
package net.sf.jasperreports.engine.convert;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRStyleContainer;
import net.sf.jasperreports.engine.JRStyleSetter;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.base.JRBasePrintFrame;
import net.sf.jasperreports.engine.base.JRBasePrintPage;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.util.JRExpressionUtil;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 * @version $Id: ReportConverter.java 4128 2011-01-05 10:35:36Z teodord $
 */
public class ReportConverter 
{

	private static final Log log = LogFactory.getLog(ReportConverter.class);
	public static final Color GRID_LINE_COLOR = new Color(170, 170, 255);
	
	private final JRReport report;
	private JasperPrint jasperPrint;
	private JRPrintPage page;
	int pageWidth;
	private int offsetY;
	private int upColumnHeader;
	private int downColumnHeader;
	private int upDetails;
	private int downDetails;
	private int upColumnFooter;
	private int downColumnFooter;
	
	/**
	 * List containing page elements in a given order 
	 */
	private List pageElements = new ArrayList();
	
	private StyleFactory styleFactory;
	protected Map stylesMap;
	protected final boolean cacheStyles;

	
	/**
	 *
	 */
	public ReportConverter(JRReport report, boolean ignoreContent, boolean cacheStyles)
	{
		this.report = report;
		this.cacheStyles = cacheStyles;
		
		if (report instanceof JasperDesign)
		{
			((JasperDesign)report).preprocess();
		}
		
		convert(ignoreContent);
	}
	
	/**
	 *
	 */
	protected void convert(boolean ignoreContent)
	{
		jasperPrint = new JasperPrint();
		
		jasperPrint.setDefaultFont(report.getDefaultFont());
		jasperPrint.setFormatFactoryClass(report.getFormatFactoryClass());
		//FIXME locale and timezone settings jasperprint object
		//jasperPrint.setLocaleCode(JRDataUtils.getLocaleCode(Locale.getDefault()));
		//jasperPrint.setTimeZoneId(JRDataUtils.getTimeZoneId(TimeZone.getDefault()));
		//FIXMEFONT the locale is important for font
		//jasperPrint.setLocaleCode(report.getProperty(JRProperties.PROPERTY_PREFIX + "locale"));
		//JRStyledTextAttributeSelector.setLocale(locale);
		jasperPrint.setName(report.getName());
		jasperPrint.setOrientation(report.getOrientationValue());
		jasperPrint.setPageWidth(report.getPageWidth());
		jasperPrint.setPageHeight(report.getPageHeight());
		jasperPrint.setTopMargin(report.getTopMargin());
		jasperPrint.setLeftMargin(report.getLeftMargin());
		jasperPrint.setBottomMargin(report.getBottomMargin());
		jasperPrint.setRightMargin(report.getRightMargin());
		
		JRProperties.transferProperties(report, jasperPrint, JasperPrint.PROPERTIES_PRINT_TRANSFER_PREFIX);

		setStyles(report);

		if (!ignoreContent)
		{
			pageWidth = report.getPageWidth();
			page = new JRBasePrintPage();
			
			offsetY = report.getTopMargin();

			addBand(report.getBackground());
			addBand(report.getTitle());
			addBand(report.getPageHeader());
			upColumnHeader = offsetY;
			addBand(report.getColumnHeader(), true);
			downColumnHeader = offsetY;

			boolean isColumnGroupBands = report.getPrintOrderValue() == PrintOrderEnum.VERTICAL;
			
			JRGroup[] groups = report.getGroups();
			if (groups != null)
			{
				for (int i = 0; i < groups.length ; i++)
				{
					addSection(groups[i].getGroupHeaderSection(), isColumnGroupBands);
				}
			}
			
			upDetails = offsetY;
			addSection(report.getDetailSection(), true);
			downDetails = offsetY;

			if (groups != null)
			{
				for (int i = 0; i < groups.length ; i++)
				{
					addSection(groups[i].getGroupFooterSection(), isColumnGroupBands);
				}
			}
			
			upColumnFooter = offsetY;
			addBand(report.getColumnFooter(), true);
			downColumnFooter = offsetY;
			addBand(report.getPageFooter());
			addBand(report.getLastPageFooter());
			addBand(report.getSummary());
			addBand(report.getNoData());
			
			jasperPrint.setPageHeight(offsetY + report.getBottomMargin());
			
			// column dotted delimitation 
			int colX = report.getLeftMargin();
			for(int i = 0; i < report.getColumnCount(); i++)
			{
				addColumnSeparator(colX);
				colX += report.getColumnWidth();
				addColumnSeparator(colX);
				colX += report.getColumnSpacing();
			}

			// page dotted contour line
			addHorizontalGridLine(0, report.getTopMargin(), pageWidth);
			addHorizontalGridLine(0, offsetY, pageWidth);
			addVerticalGridLine(report.getLeftMargin(), 0, jasperPrint.getPageHeight());
			addVerticalGridLine(pageWidth - report.getRightMargin(), 0, jasperPrint.getPageHeight());

			page.setElements(pageElements);
			jasperPrint.addPage(page);
		}
	}

	protected void setStyles(JRReport report)
	{
		styleFactory = new StyleFactory();
		stylesMap = new SequencedHashMap();
		
		loadReportStyles(report);
		
		try
		{
			for (Iterator it = stylesMap.values().iterator(); it.hasNext();)
			{
				JRStyle style = (JRStyle) it.next();
				jasperPrint.addStyle(style);
			}
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}

		JRStyle reportDefault = report.getDefaultStyle();
		JRStyle printDefault = null;
		if (reportDefault == null)
		{
			//search for the last default style
			for (Iterator it = stylesMap.values().iterator(); it.hasNext();)
			{
				JRStyle style = (JRStyle) it.next();
				if (style.isDefault())
				{
					printDefault = style;
				}
			}
		}
		else
		{
			printDefault = reportDefault;
		}
		
		if (printDefault != null)
		{
			jasperPrint.setDefaultStyle(printDefault);
		}		
	}

	protected void loadReportStyles(JRReport report)
	{
		JRReportTemplate[] templates = report.getTemplates();
		if (templates != null)
		{
			Set loadedLocations = new HashSet();
			for (int i = 0; i < templates.length; i++)
			{
				loadReportTemplateStyles(templates[i], loadedLocations);
			}
		}
		
		collectStyles(report.getStyles());
	}

	protected void loadReportTemplateStyles(JRReportTemplate template, Set loadedLocations)
	{
		JRExpression sourceExpression = template.getSourceExpression();
		if (sourceExpression != null)
		{
			String location = JRExpressionUtil.getSimpleExpressionText(sourceExpression);
			if (location == null)
			{
				log.warn("Template source expression " + sourceExpression.getText() 
						+ "cannot be evaluated; some styles might remain unresolved.");
			}
			else
			{
				HashSet parentLocations = new HashSet();
				loadTemplateStyles(location, loadedLocations, parentLocations);
			}
		}
	}

	protected void loadTemplateStyles(String location, Set loadedLocations, Set parentLocations)
	{
		if (!parentLocations.add(location))
		{
			throw new JRRuntimeException("Circular dependency found for template at location " 
					+ location);
		}
		
		if (!loadedLocations.add(location))
		{
			//already loaded
			return;
		}
		
		JRTemplate template;
		try
		{
			template = JRXmlTemplateLoader.load(location);
		}
		catch (Exception e)
		{
			log.warn("Could not load template from location " + location 
					+ "; some styles might remain unresolved.");
			return;
		}
		
		JRTemplateReference[] includedTemplates = template.getIncludedTemplates();
		if (includedTemplates != null)
		{
			for (int i = 0; i < includedTemplates.length; i++)
			{
				JRTemplateReference reference = includedTemplates[i];
				loadTemplateStyles(reference.getLocation(), loadedLocations, parentLocations);
			}
		}
		
		collectStyles(template.getStyles());
	}

	protected void collectStyles(JRStyle[] styles)
	{
		if (styles != null)
		{
			for (int i = 0; i < styles.length; i++)
			{
				JRStyle style = styles[i];
				JRStyle copy = styleFactory.getStyle(style);
				stylesMap.put(copy.getName(), copy);
			}
		}
	}

	/**
	 *
	 */
	private void addSection(JRSection section, boolean isColumnSection)
	{
		if (section != null)
		{
			JRBand[] bands = section.getBands();
			if (bands != null && bands.length > 0)
			{
				for(int i = 0; i< bands.length; i++)
				{
					addBand(bands[i], isColumnSection);
				}
			}
		}
	}

	/**
	 *
	 */
	private void addBand(JRBand band)
	{
		addBand(band, false);
	}

	/**
	 *
	 */
	private void addBand(JRBand band, boolean isColumnBand)
	{
		if (band != null)
		{
			JRBasePrintFrame frame = new JRBasePrintFrame(null);
			frame.setX(
				isColumnBand && report.getColumnDirection() == RunDirectionEnum.RTL 
					? report.getPageWidth() - report.getRightMargin() - report.getColumnWidth() 
					: report.getLeftMargin()
				);
			frame.setY(offsetY);
			frame.setWidth(
				isColumnBand
				? report.getColumnWidth()
				: report.getPageWidth() - report.getLeftMargin() - report.getRightMargin()
				);
			frame.setHeight(band.getHeight());
			
			band.visit(new ConvertVisitor(this, frame));
			
			pageElements.add(frame);
			
			offsetY += band.getHeight();
			addBandSeparator(offsetY);
		}
	}
	
	/**
	 *
	 */
	private void addBandSeparator(int bandY)
	{
		addHorizontalGridLine(0, bandY, pageWidth);
	}
	
	/**
	 *
	 */
	private void addColumnSeparator(int colX)
	{
		if (report.getPrintOrderValue() == PrintOrderEnum.HORIZONTAL)
		{
			if (downColumnHeader > upColumnHeader)
			{
				addVerticalGridLine(colX, upColumnHeader, downColumnHeader - upColumnHeader);
			}

			if (downDetails > upDetails)
			{
				addVerticalGridLine(colX, upDetails, downDetails - upDetails);
			}

			if (downColumnFooter > upColumnFooter)
			{
				addVerticalGridLine(colX, upColumnFooter, downColumnFooter - upColumnFooter);
			}
		}
		else //vertical printOrder
		{
			if (downColumnFooter > upColumnHeader)
			{
				addVerticalGridLine(colX, upColumnHeader, downColumnFooter - upColumnHeader);
			}
		}
	}
	
	/**
	 *
	 */
	private void addHorizontalGridLine(int x, int y, int width)
	{
		JRPrintFrame printFrame = new JRBasePrintFrame(getDefaultStyleProvider());
		printFrame.setX(x);
		printFrame.setY(y);
		printFrame.setWidth(width);
		printFrame.setHeight(1);
		printFrame.getLineBox().getPen().setLineWidth(0);
		printFrame.getLineBox().getPen().setLineStyle(LineStyleEnum.SOLID);
		printFrame.getLineBox().getTopPen().setLineWidth(0.1f);
		printFrame.getLineBox().getTopPen().setLineStyle(LineStyleEnum.DASHED);
		printFrame.getLineBox().getTopPen().setLineColor(GRID_LINE_COLOR);
		pageElements.add(0, printFrame);
	}
	
	/**
	 *
	 */
	private void addVerticalGridLine(int x, int y, int height)
	{
		JRPrintFrame printFrame = new JRBasePrintFrame(getDefaultStyleProvider());
		printFrame.setX(x);
		printFrame.setY(y);
		printFrame.setWidth(1);
		printFrame.setHeight(height);
		printFrame.getLineBox().getPen().setLineWidth(0);
		printFrame.getLineBox().getPen().setLineStyle(LineStyleEnum.SOLID);
		printFrame.getLineBox().getLeftPen().setLineWidth(0.1f);
		printFrame.getLineBox().getLeftPen().setLineStyle(LineStyleEnum.DASHED);
		printFrame.getLineBox().getLeftPen().setLineColor(GRID_LINE_COLOR);
		pageElements.add(0, printFrame);
	}
	
	/**
	 * 
	 */
	public JRStyle resolveStyle(JRStyleContainer originalContainer)
	{
		JRStyle originalStyle = originalContainer.getStyle();
		String nameReference = originalContainer.getStyleNameReference();
		JRStyle style;
		if (originalStyle != null)
		{
			style = styleFactory.getStyle(originalStyle);
		}
		else if (nameReference != null)
		{
			style = (JRStyle) stylesMap.get(nameReference);
			if (style == null)
			{
				log.warn("Style " + nameReference + " could not be resolved.");
			}
		}
		else
		{
			style = null;
		}
		return style;
	}	


	/**
	 * 
	 */	
	public JRReport getReport()
	{
		return report;
	}
	
	
	/**
	 * 
	 */	
	public JasperPrint getJasperPrint()
	{
		return jasperPrint;
	}
	
	
	/**
	 * 
	 */	
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		//return jasperPrint.getDefaultStyleProvider();
		return report;
	}

	
	/**
	 * 
	 */	
	protected class StyleFactory extends JRBaseObjectFactory
	{
		public StyleFactory()
		{
			super(ReportConverter.this.getDefaultStyleProvider());
		}

		public JRExpression getExpression(JRExpression expression, boolean assignNotUsedId)
		{
			return expression;
		}

		public JRStyle getStyle(JRStyle style)
		{
			JRBaseStyle baseStyle = null;

			if (style != null)
			{
				baseStyle = (JRBaseStyle)get(style);
				if (
					baseStyle == null
					|| !ReportConverter.this.cacheStyles
					)
				{
					baseStyle = new JRBaseStyle(style, this);
					put(style, baseStyle);
				}
			}

			return baseStyle;
		}

		protected void handleStyleNameReference(JRStyleSetter setter, String nameReference)
		{
			JRStyle style = (JRStyle) stylesMap.get(nameReference);
			if (style == null)
			{
				log.warn("Style " + nameReference + " could not be resolved.");
			}
			else
			{
				setter.setStyle(style);
			}
		}
	}

	public void copyBaseAttributes(JRElement source, JRPrintElement converted)
	{
		converted.setX(source.getX());
		converted.setY(source.getY());
		converted.setWidth(source.getWidth());			
		converted.setHeight(source.getHeight());
		converted.setBackcolor(source.getOwnBackcolor());
		converted.setForecolor(source.getOwnForecolor());
		//printElement.setKey(element.getKey());
		converted.setMode(source.getOwnModeValue());
		converted.setStyle(resolveStyle(source));
	}
	
}
