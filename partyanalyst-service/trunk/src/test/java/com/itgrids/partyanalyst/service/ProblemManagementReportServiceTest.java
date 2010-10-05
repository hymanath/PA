package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemManagementReportServiceTest extends BaseDaoTestCase {

	private IProblemManagementReportService problemManagementReportService;

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
	
	public void test(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		NavigationVO result = problemManagementReportService.getAllProblemsForGivenLocation(list,IConstants.STATE_LEVEL);
		System.out.println(result.getApprovalProblems());
	}
}
