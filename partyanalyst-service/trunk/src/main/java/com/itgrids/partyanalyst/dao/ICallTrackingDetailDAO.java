package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CallTrackingDetail;

public interface ICallTrackingDetailDAO extends GenericDao<CallTrackingDetail,Long>{
	
	public List<CallTrackingDetail> getCallTrackingDetailByCallTrackingProblemId(Long problemId);

}
