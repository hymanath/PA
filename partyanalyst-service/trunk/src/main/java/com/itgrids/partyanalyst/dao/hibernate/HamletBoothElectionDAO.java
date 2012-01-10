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
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.booth.totalVoters," +
				"model.boothConstituencyElection.boothResult.validVotes,  model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.hamlet.hamletId, model.hamlet.hamletName " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				"order by model.hamlet.panchayatName, model.boothConstituencyElection.booth.partNo",params); 
	}
	
	public List findPanchayathBoothIdsInTehsilForElection(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		return getHibernateTemplate().find("select model.hamlet.panchayatName, model.boothConstituencyElection.boothConstituencyElectionId " +
				"from HamletBoothElection model where model.hamlet.township.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionId = ? group by " +
				"model.boothConstituencyElection.booth.boothId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWiseBoothDetails(Long tehsilId,Long electionId)
	{
		Object[] params = {tehsilId, electionId};
		
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model4.panchayat.panchayatId,model4.panchayat.panchayatName,model.hamlet.hamletId,model.hamlet.hamletName,model.boothConstituencyElection.booth.boothId,model.boothConstituencyElection.booth.partNo," +
				" model.boothConstituencyElection.booth.totalVoters,model.boothConstituencyElection.boothResult.validVotes from HamletBoothElection model,PanchayatHamlet model4 where model4.hamlet.hamletId = model.hamlet.hamletId " +
				" and model.hamlet.hamletId in(select model2.hamlet.hamletId from PanchayatHamlet model2 where model2.panchayat.panchayatId in(select model3.panchayatId from Panchayat model3 where model3.tehsil.tehsilId = ?)) " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId = ? group by model.boothConstituencyElection.booth.boothId order by model.boothConstituencyElection.constituencyElection.constituency.name," +
				" model4.panchayat.panchayatName,model.hamlet.hamletName, model.boothConstituencyElection.booth.boothId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findPanchayatWiseVotingTrendsForATehsil(Long tehsilId, String electionIds)
	{
		return getHibernateTemplate().find("select model2.panchayat.panchayatId, model2.panchayat.panchayatName, " +
				" sum(model.boothConstituencyElection.boothResult.validVotes), model.boothConstituencyElection.constituencyElection.election.electionId" +
				" from HamletBoothElection model,PanchayatHamlet model2 where model.boothConstituencyElection.booth.tehsil.tehsilId = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionId in ("+electionIds+
				" ) and model2.hamlet.hamletId = model.hamlet.hamletId group by model2.panchayat.panchayatId,model.boothConstituencyElection.constituencyElection.election.electionId",tehsilId);
	}
		
}
