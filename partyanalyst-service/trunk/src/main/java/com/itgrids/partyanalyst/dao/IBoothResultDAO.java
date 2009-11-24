package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothResult;

public interface IBoothResultDAO extends GenericDao<BoothResult, Long>{

	public List<BoothResult> findByBoothConstituencyElection(Long boothConstituencyElectionId);

	public List<BoothResult> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScopeId);
}
