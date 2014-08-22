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

import java.awt.Image;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;


/**
 * Fa�ade class for the JasperReports engine.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperPrintManager.java 3832 2010-05-26 14:06:54Z teodord $
 */
public final class JasperPrintManager
{

	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdfFile(String)}.
	 */
	public static String printReportToPdfFile(String sourceFileName) throws JRException
	{
		return JasperExportManager.exportReportToPdfFile(sourceFileName);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdfFile(String, String)}.
	 */
	public static void printReportToPdfFile(
		String sourceFileName,
		String destFileName
		) throws JRException
	{
		JasperExportManager.exportReportToPdfFile(sourceFileName, destFileName);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdfFile(JasperPrint, String)}.
	 */
	public static void printReportToPdfFile(
		JasperPrint jasperPrint,
		String destFileName
		) throws JRException
	{
		JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdfStream(InputStream, OutputStream)}.
	 */
	public static void printReportToPdfStream(
		InputStream inputStream,
		OutputStream outputStream
		) throws JRException
	{
		JasperExportManager.exportReportToPdfStream(inputStream, outputStream);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdfStream(JasperPrint, OutputStream)}.
	 */
	public static void printReportToPdfStream(
		JasperPrint jasperPrint,
		OutputStream outputStream
		) throws JRException
	{
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToPdf(JasperPrint)}.
	 */
	public static byte[] printReportToPdf(JasperPrint jasperPrint) throws JRException
	{
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXmlFile(String, boolean)}.
	 */
	public static String printReportToXmlFile(
		String sourceFileName,
		boolean isEmbeddingImages
		) throws JRException
	{
		return 
			JasperExportManager.exportReportToXmlFile(
				sourceFileName, 
				isEmbeddingImages
				);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXmlFile(String, String, boolean)}.
	 */
	public static void printReportToXmlFile(
		String sourceFileName,
		String destFileName,
		boolean isEmbeddingImages
		) throws JRException
	{
		JasperExportManager.exportReportToXmlFile(
			sourceFileName, 
			destFileName,
			isEmbeddingImages
			);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXmlFile(JasperPrint, String, boolean)}.
	 */
	public static void printReportToXmlFile(
		JasperPrint jasperPrint,
		String destFileName,
		boolean isEmbeddingImages
		) throws JRException
	{
		JasperExportManager.exportReportToXmlFile(
			jasperPrint,
			destFileName,
			isEmbeddingImages
			);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXmlStream(InputStream, OutputStream)}.
	 */
	public static void printReportToXmlStream(
		InputStream inputStream,
		OutputStream outputStream
		) throws JRException
	{
		JasperExportManager.exportReportToXmlStream(
			inputStream,
			outputStream
			);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXmlStream(JasperPrint, OutputStream)}.
	 */
	public static void printReportToXmlStream(
		JasperPrint jasperPrint,
		OutputStream outputStream
		) throws JRException
	{
		JasperExportManager.exportReportToXmlStream(
			jasperPrint,
			outputStream
			);
	}


	/**
	 * @deprecated Replaced by {@link JasperExportManager#exportReportToXml(JasperPrint)}.
	 */
	public static String printReportToXml(JasperPrint jasperPrint) throws JRException
	{
		return JasperExportManager.exportReportToXml(jasperPrint);
	}


	/**
	 *
	 */
	public static boolean printReport(
		String sourceFileName,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFileName);

		return printReport(jasperPrint, withPrintDialog);
	}


	/**
	 *
	 */
	public static boolean printReport(
		InputStream inputStream,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(inputStream);

		return printReport(jasperPrint, withPrintDialog);
	}


	/**
	 *
	 */
	public static boolean printReport(
		JasperPrint jasperPrint,
		boolean withPrintDialog
		) throws JRException
	{
		//artf1936
		boolean checkAvailablePrinters = JRProperties.getBooleanProperty(jasperPrint, PROPERTY_CHECK_AVAILABLE_PRINTERS, true);
		if (checkAvailablePrinters && !(unixSunJDK || JRPrintServiceExporter.checkAvailablePrinters())) 
		{
			throw new JRException("No printer available.");
		}
		//END - artf1936
		
		return printPages(
			jasperPrint,
			0,
			jasperPrint.getPages().size() - 1,
			withPrintDialog
			);
	}


	/**
	 *
	 */
	public static boolean printPage(
		String sourceFileName,
		int pageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFileName);

		return printPage(jasperPrint, pageIndex, withPrintDialog);
	}


	/**
	 *
	 */
	public static boolean printPage(
		InputStream inputStream,
		int pageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(inputStream);

		return printPage(jasperPrint, pageIndex, withPrintDialog);
	}


	/**
	 *
	 */
	public static boolean printPage(
		JasperPrint jasperPrint,
		int pageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		return printPages(
			jasperPrint,
			pageIndex,
			pageIndex,
			withPrintDialog
			);
	}


	/**
	 *
	 */
	public static boolean printPages(
		String sourceFileName,
		int firstPageIndex,
		int lastPageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFileName);

		return printPages(
			jasperPrint,
			firstPageIndex,
			lastPageIndex,
			withPrintDialog
			);
	}


	/**
	 *
	 */
	public static boolean printPages(
		InputStream inputStream,
		int firstPageIndex,
		int lastPageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(inputStream);

		return printPages(
			jasperPrint,
			firstPageIndex,
			lastPageIndex,
			withPrintDialog
			);
	}


	/**
	 *
	 */
	public static boolean printPages(
		JasperPrint jasperPrint,
		int firstPageIndex,
		int lastPageIndex,
		boolean withPrintDialog
		) throws JRException
	{
		return JRPrinterAWT.printPages(
			jasperPrint,
			firstPageIndex,
			lastPageIndex,
			withPrintDialog
			);
	}


	/**
	 *
	 */
	public static Image printPageToImage(
		String sourceFileName,
		int pageIndex,
		float zoom
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFileName);

		return printPageToImage(jasperPrint, pageIndex, zoom);
	}


	/**
	 *
	 */
	public static Image printPageToImage(
		InputStream inputStream,
		int pageIndex,
		float zoom
		) throws JRException
	{
		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(inputStream);

		return printPageToImage(jasperPrint, pageIndex, zoom);
	}


	/**
	 *
	 */
	public static Image printPageToImage(
		JasperPrint jasperPrint,
		int pageIndex,
		float zoom
		) throws JRException
	{
		return JRPrinterAWT.printPageToImage(jasperPrint, pageIndex, zoom);
	}


	/**
	 * Property whose value is used to check the availability of printers accepting jobs.
	 * <p/>
	 * This property is by default set to <code>true</code>.
	 */
	public static final String PROPERTY_CHECK_AVAILABLE_PRINTERS = JRProperties.PROPERTY_PREFIX + "awt.check.available.printers";

	/* http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6604109 (artf2423) workaround */
	protected static final boolean unixSunJDK;
	static
	{
		boolean found = false;
		try
		{
			Class.forName("sun.print.UnixPrintServiceLookup");
			found = true;
		}
		catch (ClassNotFoundException e)
		{
			found = false;
		}
		unixSunJDK = found;
	}
	
	
	private JasperPrintManager()
	{
	}
}
