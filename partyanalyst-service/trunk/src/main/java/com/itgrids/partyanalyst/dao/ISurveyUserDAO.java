package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUser;

public interface ISurveyUserDAO extends GenericDao<SurveyUser, Long>
{
	public Long getUserDetails(String userName,String password);
}
