package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserSurveyBooths;

public interface IUserSurveyBoothsDAO extends GenericDao<UserSurveyBooths , Long>{
	public List<Object[]> getPublicationIdByUserId(Long userId);
}
