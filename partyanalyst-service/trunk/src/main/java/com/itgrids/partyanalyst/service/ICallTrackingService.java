package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;

public interface ICallTrackingService {
	
	public List<CallTrackingVO> saveCallTrackingProblem(final CallTrackingVO callTrackingVO);
	
	public List<CallTrackingVO> getCurrentDayCallTrackingProblem();
	
	public List<CallTrackingVO> getCurrentDayProblemCount();
	
	public ProblemBeanVO getCallTrackingProblemByProblemId(Long problemId);
	
	public List<CallTrackingVO> searchCallTrackingProblem(final CallTrackingVO callTrackingVO);
	
	public List<CallTrackingVO> updateCallTrackingProblem(final CallTrackingVO callTrackingVO);

}
