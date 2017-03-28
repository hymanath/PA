package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.model.CallTrackingProblem;

public interface ICallTrackingProblemDAO extends GenericDao<CallTrackingProblem,Long>{
	
	public List<Object[]> getProblemDetailbyMobileNo(String mobile);
	
	public List<Object[]> getProblemDetailbyTodayDate(Date today);
	
	public List<Object[]> getProblemDetailbyProblemId(Long problemId);
	
	public List<Object[]> searchCallTrackingProblem(CallTrackingVO callTrackingVO,Date addedDate);
	
	 public List<Long>  getProblemCount(Date fromDate,Date toDate);
	 
	 public List<String> getProblemTypes();
	 
	 public List<Object[]> getProblemByProblemPurpose(String problemPurpose,Date fromDate,Date toDate);
	 
	 public List<Object[]> getProblemCountDateByProblem(String problemPurpose,Date fromDate,Date toDate);
}
