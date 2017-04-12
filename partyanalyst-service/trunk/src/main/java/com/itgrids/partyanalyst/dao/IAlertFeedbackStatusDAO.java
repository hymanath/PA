package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertFeedbackStatus;

public interface IAlertFeedbackStatusDAO extends GenericDao<AlertFeedbackStatus, Long>{
	
	public List<Object[]> getFeedBackStatus();

}
