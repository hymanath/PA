/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.jasper.dataSource.BeanDataSourceProvider;

/**
 * jasper producer for Party performance Report
 * 
 * @author Sujatha Boddu
 */

public class JasperProducer {

	private final static Logger log = Logger.getLogger(JasperProducer.class);
	
	public static byte[] createJasperReport(String jasperXML, Map<String, Object> params, PartyPerformanceReportVO partyReportVO) throws JRException {
		JasperDesign jasperDesign = null;
       	
		try {
			jasperDesign = JRXmlLoader.load(new LegacyJasperInputStream(new FileInputStream(jasperXML)));
		} catch (FileNotFoundException e1) {
			log.error("File Not found error");
		}
		
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	    JRBeanCollectionDataSource ds = (JRBeanCollectionDataSource) new BeanDataSourceProvider().createReport(jasperReport, partyReportVO);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,ds);
	   
	   /* JRXlsExporter exporterXLS = new JRXlsExporter(); 
	    exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
	    exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out); 
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
	    exporterXLS.exportReport(); */

	   	return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
}
