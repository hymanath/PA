/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.jasper.dataSource;

import java.util.*;

import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Data source provider implementation that
 * provides a bean collection data source
 * containing instances of PartyPerformanceReportVO class.
 * 
 * @author Sujatha Boddu
 */

public class BeanDataSourceProvider extends JRAbstractBeanDataSourceProvider {

	private List<PartyPerformanceReportVO> reportData;
	
	public BeanDataSourceProvider() {
	   super(PartyPerformanceReportVO.class);
	}
	
	public JRDataSource create(JasperReport report)
	        throws JRException {
	    return new JRBeanCollectionDataSource(reportData);
	}
	
	public JRDataSource createReport(JasperReport report, PartyPerformanceReportVO stateLevelReport) throws JRException {
		reportData = new ArrayList<PartyPerformanceReportVO>();
		reportData.add(stateLevelReport);
		return create(report);
	}
	public void dispose(JRDataSource dataSource) 
	        throws JRException {
	    // nothing to dispose
	}

}

