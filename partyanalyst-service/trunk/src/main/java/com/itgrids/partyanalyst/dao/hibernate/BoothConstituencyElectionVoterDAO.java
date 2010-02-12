package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;

public class BoothConstituencyElectionVoterDAO extends GenericDaoHibernate<BoothConstituencyElectionVoter, Long> implements IBoothConstituencyElectionVoterDAO{
	public BoothConstituencyElectionVoterDAO(){
		super(BoothConstituencyElectionVoter.class);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElection(Long boothConstituencyElectionId) {
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ?", boothConstituencyElectionId);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElectionVoter> findByBoothConstituencyElectionAndVoter(Long boothConstituencyElectionId, Long voterId) {
		Object[] params = {boothConstituencyElectionId, voterId};
		return getHibernateTemplate().find("from BoothConstituencyElectionVoter model where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = ? and " +
				"model.voter.voterId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Voter> findVotersByHamletAndElectionYear(Long hamletId, String year){
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model where model.voter.hamlet.hamletId = ? and" +
				" model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}
	
	public List findVotersCastInfoByHamletAndElectionYear(Long hamletId, String year){
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select count(model.voter.voterId), model.voter.gender, model.voter.cast from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?" +
				"group by model.voter.cast, model.voter.gender order by model.voter.cast", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Voter> findVotersGroupByHouseNoForHamlet(Long hamletId, String year){
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> findVoterHouseNosInHamlet(Long hamletId, String year) {
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select distinct(model.voter.houseNo) from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Voter> findVotersByHouseNoAndHamlet(String houseNo, Long hamletId,String year) {
		Object[] params = {houseNo, hamletId, "m", year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model " +
				"where model.voter.houseNo = ? and model.voter.hamlet.hamletId = ? and model.voter.gender = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? order by age", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Voter> findVotersGroupByHouseNoAndAgeForHamletAndYear(Long hamletId, String year){
		Object[] params = {hamletId, year};
		return getHibernateTemplate().find("select model.voter from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?" +
				" group by model.voter.houseNo, model.voter.age", params);
	}
	
	public List findTotalVotersForHamlet(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year,electionType};
		return getHibernateTemplate().find("select model.voter.hamlet.hamletId, model.voter.hamlet.hamletName, count(model.voter) from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.hamlet.hamletName order by model.voter.hamlet.hamletName", params);
		
	}
	
	public List findHamletBoothsForRevenueVillage(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year, electionType};
		return getHibernateTemplate().find("select model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo order by model.voter.hamlet.hamletName", params);
	}
}
