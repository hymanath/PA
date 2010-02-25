package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IProblemManagementReportService {
	
	public List<ProblemBeanVO> getHamletProblemsInfo(Long hamletId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getTehsilProblemsInfo(Long tehsilId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getConstituencyProblemsInfo(Long constituencyId,Long registrationId,String taskType);
}
