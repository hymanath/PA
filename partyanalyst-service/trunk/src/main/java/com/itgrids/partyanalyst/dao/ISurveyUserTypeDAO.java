package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserType;

public interface ISurveyUserTypeDAO  extends GenericDao<SurveyUserType, Long>{

	public Long checkForUsertype(String description);
	
	public List<Object[]> getAllUserTypes();
}
