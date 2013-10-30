/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.jasper.dataSource;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;

public class ConstituencyDataSourceProvider extends JRAbstractBeanDataSourceProvider {

		private List<ConstituencyPositionDetailVO> reportData;
		
		public ConstituencyDataSourceProvider() {
		   super(ConstituencyPositionDetailVO.class);
		}
		
		public JRDataSource create(JasperReport report)
		        throws JRException {
		    return new JRBeanCollectionDataSource(reportData);
		}
		
		public JRDataSource createReport(JasperReport report, ConstituencyPositionDetailVO stateLevelReport) throws JRException {
			reportData = new ArrayList<ConstituencyPositionDetailVO>();
			reportData.add(stateLevelReport);
			return create(report);
		}
		public void dispose(JRDataSource dataSource) 
		        throws JRException {
		    // nothing to dispose
		}
}
