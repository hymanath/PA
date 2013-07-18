package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VotingTrendz;

public interface IVotingTrendzDAO extends GenericDao<VotingTrendz,Long>{

	public Integer deleteVotingTrendzByConstituencyId(Long constituenyId);

}
