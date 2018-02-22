package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.exceptionalReport.service.IKaizalaExceptionReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class KaizalaExceptionReportService implements IKaizalaExceptionReportService {
	private final static Logger LOG = Logger.getLogger(KaizalaExceptionReportService.class);
	
	private DateUtilService dateUtilService;
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	
	
}
