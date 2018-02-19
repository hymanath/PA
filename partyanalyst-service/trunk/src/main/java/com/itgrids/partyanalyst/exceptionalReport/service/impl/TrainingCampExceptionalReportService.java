package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.exceptionalReport.service.ITrainingCampExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class TrainingCampExceptionalReportService implements ITrainingCampExceptionalReportService{
	private final static Logger LOG = Logger.getLogger(TrainingCampExceptionalReportService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private DateUtilService dateUtilService;
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	
}
