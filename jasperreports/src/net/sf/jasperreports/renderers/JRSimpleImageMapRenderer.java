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
package net.sf.jasperreports.renderers;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImageMapRenderer;
import net.sf.jasperreports.engine.JRImageRenderer;

/**
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 * @version $Id: JRSimpleImageMapRenderer.java 3035 2009-08-27 12:05:03Z teodord $
 */
public class JRSimpleImageMapRenderer extends JRImageRenderer implements JRImageMapRenderer
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private List areaHyperlinks;
	
	/**
	 * 
	 */
	public JRSimpleImageMapRenderer(byte[] imageData, List areaHyperlinks) 
	{
		super(imageData);
		this.areaHyperlinks = areaHyperlinks;
	}

	public List renderWithHyperlinks(Graphics2D grx, Rectangle2D rectangle) throws JRException
	{
		render(grx, rectangle);
		
		return areaHyperlinks;
	}
	
	/**
	 * @deprecated Replaced by {@link #renderWithHyperlinks(Graphics2D, Rectangle2D)}
	 */
	public List getImageAreaHyperlinks(Rectangle2D renderingArea) throws JRException 
	{
		return areaHyperlinks;
	}

	public boolean hasImageAreaHyperlinks()
	{
		return areaHyperlinks != null && !areaHyperlinks.isEmpty();
	}

}
