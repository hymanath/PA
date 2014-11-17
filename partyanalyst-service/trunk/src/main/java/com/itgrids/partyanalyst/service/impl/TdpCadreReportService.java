package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;

public class TdpCadreReportService implements ITdpCadreReportService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	
	private ITdpCadreReportService tdpCadreReportService;
	
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}
	
	
	public void generateExcelReportForTdpCadre()
	{
		try {
			List<TdpCadreLocationWiseReportVO> constituncyReportList = new ArrayList<TdpCadreLocationWiseReportVO>();
			
			if(constituncyReportList != null && constituncyReportList.size() >0)
			{
				for (TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO : constituncyReportList) 
				{
					
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in generateExcelReportForTdpCadre () at TdpCadreReportService ",e);
		}
	}
}
