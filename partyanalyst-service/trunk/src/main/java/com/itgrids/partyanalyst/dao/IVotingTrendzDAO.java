package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VotingTrendz;

public interface IVotingTrendzDAO extends GenericDao<VotingTrendz,Long>{

	public Integer deleteVotingTrendzByConstituencyId(Long constituenyId);
	
	public List<VotingTrendz> getVotingTrendzList(Long constituencyId);

}
