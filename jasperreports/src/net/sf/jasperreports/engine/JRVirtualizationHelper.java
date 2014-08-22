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

/**
 * Virtualization helper class.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRVirtualizationHelper.java 3713 2010-04-08 11:06:05Z teodord $
 */
public final class JRVirtualizationHelper
{
	private static final ThreadLocal threadVirtualizer = new ThreadLocal();

	
	/**
	 * Sets a virtualizer to be used for the current thread.
	 * <p>
	 * The current thread's virtualizer is used when a report obtained by virtualization
	 * is deserialized.
	 * 
	 * @param virtualizer
	 */
	public static void setThreadVirtualizer(JRVirtualizer virtualizer)
	{
		threadVirtualizer.set(virtualizer);
	}

	
	/**
	 * Clears the virtualizer associated to the current thread.
	 */
	public static void clearThreadVirtualizer()
	{
		threadVirtualizer.set(null);
	}

	
	/**
	 * Returns the virtualizer associated to the current thread.
	 * <p>
	 * This method is used by {@link net.sf.jasperreports.engine.base.JRVirtualPrintPage JRVirtualPrintPage}
	 * on deserialization.
	 * 
	 * @return the virtualizer associated to the current thread
	 */
	public static JRVirtualizer getThreadVirtualizer()
	{
		return (JRVirtualizer) threadVirtualizer.get();
	}
	
	
	private JRVirtualizationHelper()
	{
	}
}
