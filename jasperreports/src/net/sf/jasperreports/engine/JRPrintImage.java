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
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 */
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintImage.java 3583 2010-03-12 11:35:40Z shertage $
 */
public interface JRPrintImage extends JRPrintGraphicElement, JRPrintAnchor, JRPrintHyperlink, JRAlignment, JRBox, JRCommonImage
{


	/**
	 *
	 */
	public JRRenderable getRenderer();
		
	/**
	 *
	 */
	public void setRenderer(JRRenderable renderer);
		
	/**
	 * @deprecated Replaced by {@link #setScaleImage(ScaleImageEnum)}.
	 */
	public void setScaleImage(byte scaleImage);
	
	/**
	 * @deprecated Replaced by {@link #setScaleImage(ScaleImageEnum)}.
	 */
	public void setScaleImage(Byte scaleImage);
	
	/**
	 * 
	 */
	public void setScaleImage(ScaleImageEnum scaleImage);

	/**
	 *
	 */
	public boolean isUsingCache();

	/**
	 *
	 */
	public void setUsingCache(boolean isUsingCache);
	
	/**
	 *
	 */
	public boolean isLazy();

	/**
	 *
	 */
	public void setLazy(boolean isLazy);

	/**
	 * @deprecated Replaced by {@link #getOnErrorTypeValue()}
	 */
	public byte getOnErrorType();

	/**
	 * @deprecated Replaced by {@link #setOnErrorType(OnErrorTypeEnum)}
	 */
	public void setOnErrorType(byte onErrorType);

	/**
	 * 
	 */
	public OnErrorTypeEnum getOnErrorTypeValue();

	/**
	 *
	 */
	public void setOnErrorType(OnErrorTypeEnum onErrorType);

	/**
	 * @deprecated Replaced by {@link JRBoxContainer#getLineBox()}
	 */
	public JRBox getBox();

	/**
	 * @deprecated Replaced by {@link JRBoxContainer#getLineBox()}
	 */
	public void setBox(JRBox box);


}
