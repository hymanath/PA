package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;

public interface IVotingTrendzPartiesResultDAO extends GenericDao<VotingTrendzPartiesResult,Long>{
	
	public List<Long> getVotingTrendzIds(Long constituenyId);
	
	public Integer deletePartyResultByConstituencyId(List<Long> votingTrendzIds);
	
	public List<VotingTrendzPartiesResult> getVotingTrendzPartiesResultList(Long constituencyId);
	
	public List<Object[]> getpartiesListForVotingTrendz(Long constituencyId);
}
