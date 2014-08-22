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

import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;


/**
 * An interface that defines constants useful for alignment. All report elements that can be aligned in some way
 * implement this interface.
 *
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRAlignment.java 3419 2010-02-18 08:16:30Z teodord $
 */
public interface JRAlignment extends JRStyleContainer
{

	/**
	 * @deprecated Replaced by {@link HorizontalAlignEnum#LEFT}.
	 */
	public static final byte HORIZONTAL_ALIGN_LEFT = 1;
	/**
	 * @deprecated Replaced by {@link HorizontalAlignEnum#CENTER}.
	 */
	public static final byte HORIZONTAL_ALIGN_CENTER = 2;
	/**
	 * @deprecated Replaced by {@link HorizontalAlignEnum#RIGHT}.
	 */
	public static final byte HORIZONTAL_ALIGN_RIGHT = 3;
	/**
	 * @deprecated Replaced by {@link HorizontalAlignEnum#JUSTIFIED}.
	 */
	public static final byte HORIZONTAL_ALIGN_JUSTIFIED = 4;

	/**
	 * @deprecated Replaced by {@link VerticalAlignEnum#TOP}.
	 */
	public static final byte VERTICAL_ALIGN_TOP = 1;
	/**
	 * @deprecated Replaced by {@link VerticalAlignEnum#MIDDLE}.
	 */
	public static final byte VERTICAL_ALIGN_MIDDLE = 2;
	/**
	 * @deprecated Replaced by {@link VerticalAlignEnum#BOTTOM}.
	 */
	public static final byte VERTICAL_ALIGN_BOTTOM = 3;
	/**
	 * @deprecated Replaced by {@link VerticalAlignEnum#JUSTIFIED}.
	 */
	public static final byte VERTICAL_ALIGN_JUSTIFIED = 4;


	/**
	 * @deprecated Replaced by {@link #getHorizontalAlignmentValue()}.
	 */
	public byte getHorizontalAlignment();

	/**
	 * @deprecated Replaced by {@link #getOwnHorizontalAlignmentValue()}.
	 */
	public Byte getOwnHorizontalAlignment();

	/**
	 * Gets the text horizontal alignment.
	 * @return a value representing one of the horizontal alignment constants in {@link HorizontalAlignEnum}
	 */
	public HorizontalAlignEnum getHorizontalAlignmentValue();

	public HorizontalAlignEnum getOwnHorizontalAlignmentValue();

	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(HorizontalAlignEnum)}.
	 */
	public void setHorizontalAlignment(byte horizontalAlignment);

	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(HorizontalAlignEnum)}.
	 */
	public void setHorizontalAlignment(Byte horizontalAlignment);

	/**
	 * Sets the text horizontal alignment.
	 * @param horizontalAlignment a value representing one of the horizontal alignment constants in {@link HorizontalAlignEnum}
	 */
	public void setHorizontalAlignment(HorizontalAlignEnum horizontalAlignment);

	/**
	 * @deprecated Replaced by {@link #getVerticalAlignmentValue()}.
	 */
	public byte getVerticalAlignment();
		
	/**
	 * @deprecated Replaced by {@link #getOwnVerticalAlignmentValue()}.
	 */
	public Byte getOwnVerticalAlignment();

	/**
	 * Gets the text vertical alignment.
	 * @return a value representing one of the vertical alignment constants in {@link VerticalAlignEnum}
	 */
	public VerticalAlignEnum getVerticalAlignmentValue();
	
	public VerticalAlignEnum getOwnVerticalAlignmentValue();

	/**
	 * @deprecated Replaced by {@link #setVerticalAlignment(VerticalAlignEnum)}.
	 */
	public void setVerticalAlignment(byte verticalAlignment);
		
	/**
	 * @deprecated Replaced by {@link #setVerticalAlignment(VerticalAlignEnum)}.
	 */
	public void setVerticalAlignment(Byte verticalAlignment);
	
	/**
	 * Gets the text vertical alignment.
	 * @param verticalAlignment a value representing one of the vertical alignment constants in {@link VerticalAlignEnum}
	 */
	public void setVerticalAlignment(VerticalAlignEnum verticalAlignment);

}
