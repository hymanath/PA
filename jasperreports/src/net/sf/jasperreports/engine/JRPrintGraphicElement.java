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
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.type.FillEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintGraphicElement.java 3504 2010-03-04 12:41:19Z shertage $
 */
public interface JRPrintGraphicElement extends JRPrintElement, JRCommonGraphicElement
{

	/**
	 * Indicates the pen type used for this element.
	 * @return one of the pen constants in this class
	 * @deprecated Replaced by {@link #getLinePen()}
	 */
	public byte getPen();

	/**
	 * @deprecated Replaced by {@link #getLinePen()}
	 */
	public Byte getOwnPen();

	/**
	 * Sets the pen type that will used for this element.
	 * @param pen one of the pen constants in this class
	 * @deprecated Replaced by {@link #getLinePen()}
	 */
	public void setPen(byte pen);

	/**
	 * @deprecated Replaced by {@link #getLinePen()}
	 */
	public void setPen(Byte pen);
		
	/**
	 * @deprecated Replaced by {@link #setFill(FillEnum)}
	 */
	public void setFill(byte fill);
	
	/**
	 * @deprecated Replaced by {@link #setFill(FillEnum)}
	 */
	public void setFill(Byte fill);
		
	/**
	 * Sets the fill type used for this element.
	 * @param fill one of the pen constants in {@link FillEnum}.
	 */
	public void setFill(FillEnum fill);
	
	
}
