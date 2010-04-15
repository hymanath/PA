package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.model.HamletBoothElection;

public class HamletBoothElectionDAO extends GenericDaoHibernate<HamletBoothElection, Long> implements IHamletBoothElectionDAO{

	public HamletBoothElectionDAO(){
		super(HamletBoothElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) {
		Object[] params = {hamletId, boothConstituencyElectionId};	
		return getHibernateTemplate().find("select model.hamletBoothElectionId from HamletBoothElection model where " +
				"model.hamlet.hamletId = ? and model.boothConstituencyElection.boothConstituencyElectionId = ?",params);
	}
	
	public List findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.hamlet.hamletName, " +
				"model.boothConstituencyElection.boothResult.validVotes, model.boothConstituencyElection.booth.totalVoters " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				"order by model.hamlet.panchayatName",params); 
	}
	
	
	public List findPachayathBoothIdsInTehsilForElection(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.booth.boothId " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? group by " +
				"model.boothConstituencyElection.booth.boothId",params);
	}
	
	
}
