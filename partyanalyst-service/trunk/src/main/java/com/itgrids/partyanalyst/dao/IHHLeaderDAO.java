
package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHLeader;
import com.itgrids.partyanalyst.model.HHOptions;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;

public interface IHHLeaderDAO extends GenericDao<HHLeader, Long>{
	
	public List<String> getAllExistingVoterIds();
	public List<Object[]> getAllLeadersOfConstituency(Long constituencyId);
}