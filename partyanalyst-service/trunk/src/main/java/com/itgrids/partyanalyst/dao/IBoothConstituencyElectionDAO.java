package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;

public interface IBoothConstituencyElectionDAO extends GenericDao<BoothConstituencyElection, Long>{

	public BoothConstituencyElection findByBoothAndConstiuencyElection(String partNo, Long constituencyElectionId);

	public List<Booth> findBoothsByConstituencyElection(Long constiElecId);
	
	public List<BoothConstituencyElection> findByBoothElectionAndScope(Long boothId, String electionYear, Long electionScopeId);

	public List<BoothConstituencyElection> findByTehsilElectionAndScope(String electionYear, Long electionScopeId, Long tehsilId);
}
