package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CallCenterFeedback;



public interface ICallCenterFeedbackDAO extends GenericDao<CallCenterFeedback, Long>{ 
	
	public List<Object[]> getAllFeedbackData();
}
