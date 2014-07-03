package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;

public interface ISurveyUserBoothAssignDAO extends GenericDao<SurveyUserBoothAssign, Long>{
	
	public List<Object[]> getAllTheAssignedBoothsByConstituencyIdAndUserId(Long constituencyId,Long userId);


}
