package com.itgrids.partyanalyst.exceptionalReport.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.exceptionalReport.service.ICommitteeExceptionalReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class CommitteeExceptionalReportService implements ICommitteeExceptionalReportService {

	private final static Logger LOG = Logger.getLogger(CommitteeExceptionalReportService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	
}
