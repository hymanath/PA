package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IProblemManagementReportService {
	
	public List<ProblemBeanVO> getHamletProblemsInfo(Long hamletId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getTehsilProblemsInfo(Long tehsilId,Long registrationId,String taskType);
	
	public List<ProblemBeanVO> getConstituencyProblemsInfo(Long constituencyId,Long registrationId,String taskType, String constituencyType);
	
	public List<ProblemHistoryVO> getCompleteDetailsForAProblem(Long problemLocationId);
	
	public LocationwiseProblemStatusInfoVO getProblemsStatusCount(String accessType, Long accessValue, int limit);
	
	public List<SelectOptionVO> getAllProblemStatusInfo();
	
	public List<ProblemBeanVO> getProblemsPostedByStatusAndDates(String fromDate, String toDate, Long statusId, String accessType, Long accessValue);
	
	public List<InfluencingPeopleVO> findInfluencingPeopleInfoInLocation(String accessType, Long accessValue, Long hamletId, String flag);
	
	public List<ProblemBeanVO> getProblemsInfoByStatusInALocation(Long accessValue,String accessType,Long registrationId,String taskType);
	
	public LocationwiseProblemStatusInfoVO getRecentProblemsWithInTheRegion(String accessType, Long accessValue, Long statusId, int limit);
	
	public ProblemBeanVO getProblemHistoryInfo(Long problemLocationId);
}
