package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventSurveyUser;

public interface IEventSurveyUserDAO extends GenericDao<EventSurveyUser, Long>{
	public List<Object[]> getUserDetailsByUnamePwd(String uname,String pwd);	
	
}
