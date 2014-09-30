package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSurveyUser;

public interface ICadreSurveyUserDAO extends GenericDao<CadreSurveyUser, Long>{
	
	public List<Long> getUserByUserNameAndPassword(String userName , String password);

}
