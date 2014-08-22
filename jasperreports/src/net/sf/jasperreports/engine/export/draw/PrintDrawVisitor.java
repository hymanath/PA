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
package net.sf.jasperreports.engine.export.draw;

import java.awt.Graphics2D;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.PrintElementVisitor;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.TextRenderer;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRStyledText;

/**
 * Print element draw visitor.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: PrintDrawVisitor.java 3991 2010-10-12 20:10:55Z lucianc $
 */
public class PrintDrawVisitor implements PrintElementVisitor<Offset>
{
	
	private Graphics2D grx;
	private LineDrawer lineDrawer = new LineDrawer();
	private RectangleDrawer rectangleDrawer = new RectangleDrawer();
	private EllipseDrawer ellipseDrawer = new EllipseDrawer();
	private ImageDrawer imageDrawer = new ImageDrawer();
	private TextDrawer textDrawer;
	private FrameDrawer frameDrawer;

	public PrintDrawVisitor()
	{
	}
	
	public void setTextRenderer(JRReport report)
	{
		TextRenderer textRenderer = 
			new TextRenderer(
				JRProperties.getBooleanProperty(report, JRGraphics2DExporter.MINIMIZE_PRINTER_JOB_SIZE, true),
				JRProperties.getBooleanProperty(report, JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, false)
				);
		
		textDrawer = new TextDrawer(textRenderer);
		frameDrawer = new FrameDrawer(null, textRenderer);
	}

	public void setTextDrawer(TextDrawer textDrawer)
	{
		this.textDrawer = textDrawer;
	}

	public void setFrameDrawer(FrameDrawer frameDrawer)
	{
		this.frameDrawer = frameDrawer;
	}

	public void setClip(boolean isClip)
	{
		frameDrawer.setClip(isClip);
	}
	
	public void setGraphics2D(Graphics2D grx)
	{
		this.grx = grx;
	}

	public void visit(JRPrintText textElement, Offset offset)
	{
		textDrawer.draw(
				grx,
				textElement, 
				offset.getX(), 
				offset.getY()
				);
	}

	public void visit(JRPrintImage image, Offset offset)
	{
		try
		{
			imageDrawer.draw(
					grx,
					image, 
					offset.getX(), 
					offset.getY()
					);
		} 
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	public void visit(JRPrintRectangle rectangle, Offset offset)
	{
		rectangleDrawer.draw(
				grx,
				rectangle, 
				offset.getX(), 
				offset.getY()
				);
	}

	public void visit(JRPrintLine line, Offset offset)
	{
		lineDrawer.draw(
				grx,
				line, 
				offset.getX(), 
				offset.getY()
				);
	}

	public void visit(JRPrintEllipse ellipse, Offset offset)
	{
		ellipseDrawer.draw(
				grx,
				ellipse, 
				offset.getX(), 
				offset.getY()
				);
	}

	public void visit(JRPrintFrame frame, Offset offset)
	{
		try
		{
			frameDrawer.draw(
				grx,
				frame, 
				offset.getX(), 
				offset.getY()
				);
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	public void visit(JRGenericPrintElement printElement, Offset offset)
	{
		// not drawing anything because there are no Graphics2D generic element handlers
	}

}
