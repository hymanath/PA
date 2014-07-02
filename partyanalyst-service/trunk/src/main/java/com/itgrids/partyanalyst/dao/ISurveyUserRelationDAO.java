package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserRelation;

public interface ISurveyUserRelationDAO  extends GenericDao<SurveyUserRelation, Long>{

	public List<Object[]> getLeadersByConstituency();
	public List<Object[]> getUsersByConstituencyAndLeader(Long leaderId,Long constituencyId);
}
