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

import net.sf.jasperreports.engine.type.LineDirectionEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintLine.java 3583 2010-03-12 11:35:40Z shertage $
 */
public interface JRPrintLine extends JRPrintGraphicElement
{


	/**
	 * @deprecated Replaced by {@link #getDirectionValue()}.
	 */
	public byte getDirection();

	/**
	 * @deprecated Replaced by {@link #setDirection(LineDirectionEnum)}.
	 */
	public void setDirection(byte direction);

	/**
	 * Gets the line direction.
	 * @return a value representing one of the line direction constants in {@link LineDirectionEnum}
	 */
	public LineDirectionEnum getDirectionValue();
	
	/**
	 * Sets the line direction.
	 * @param lineDirectionEnum a value representing one of the line direction constants in {@link LineDirectionEnum}
	 */
	public void setDirection(LineDirectionEnum lineDirectionEnum);

}
