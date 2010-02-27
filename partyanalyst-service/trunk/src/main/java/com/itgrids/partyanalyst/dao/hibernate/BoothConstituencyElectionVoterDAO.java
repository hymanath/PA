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
	
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId){
		return getHibernateTemplate().find("select model.voter.hamlet.township.townshipName, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.totalVoters, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, model.voter.hamlet.hamletName," +
				"model.voter.hamlet.township.townshipId from BoothConstituencyElectionVoter model " +
				"where model.boothConstituencyElection.booth.tehsil.tehsilId = ? group by " +
				"model.boothConstituencyElection.booth.partNo, model.voter.hamlet.hamletName order by " +
				"model.voter.hamlet.township.townshipName, model.boothConstituencyElection.booth.partNo",tehsilId);
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
		return getHibernateTemplate().find("select model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.boothId " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.hamlet.hamletName, model.boothConstituencyElection.booth.partNo order by model.voter.hamlet.hamletName", params);
	}
	
	public List findCastWiseVoterForRevenueVillage(Long revenueVillageID, String year, String electionType){
		Object[] params = {revenueVillageID, year, electionType};
		return getHibernateTemplate().find("select model.voter.cast, count(model.voter.cast) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.cast order by model.voter.cast", 
				params);
	}
	
	public List findAgeWiseVotersForRevenueVillage(Long revenueVillageID, String year, String electionType, Long minAge, Long maxAge){
		Object[] params = {revenueVillageID, year, electionType, minAge, maxAge};
		return getHibernateTemplate().find("select model.voter.gender, count(model.voter) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.township.townshipId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and " +
				"model.voter.age>=? and model.voter.age<? " +
				"group by model.voter.gender order by model.voter.gender", 
				params);
	}
	
	/*public List findAllBoothVotersForHamlet(Long hamletID, String year, String electionType){
		Object[] params = {hamletID, year, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.boothConstituencyElectionId, model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.partName, count(model.voter) " +
				"from BoothConstituencyElectionVoter model where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.boothConstituencyElection.booth.partNo order by model.boothConstituencyElection.booth.partNo", 
				params);
	}*/

	public List findCastWiseVoterForHamlet(Long hamletID, String year, String electionType){
		Object[] params = {hamletID, year, electionType};
		return getHibernateTemplate().find("select model.voter.cast, count(model.voter.cast) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? " +
				"group by model.voter.cast order by model.voter.cast", 
				params);
	}
	
	public List findAgeWiseVotersForHamlet(Long hamletID, String year, String electionType, Long minAge, Long maxAge){
		Object[] params = {hamletID, year, electionType, minAge, maxAge};
		return getHibernateTemplate().find("select model.voter.gender, count(model.voter) " +
				"from BoothConstituencyElectionVoter model " +
				"where model.voter.hamlet.hamletId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and " +
				"model.voter.age>=? and model.voter.age<? " +
				"group by model.voter.gender order by model.voter.gender", 
				params);
	}
	
	public List getTownshipVotesByTehsil(Long electionID, Long tehsilID){
//townshipID, townshipName, totalVoters, validVoters, boothID, partNo, hamletId, hamletName	
		Object[] params = {electionID, tehsilID};
		StringBuilder query = new StringBuilder();
		query.append("select model.voter.hamlet.township.townshipId, model.voter.hamlet.township.townshipName, ")
			.append("model.boothConstituencyElection.booth.totalVoters, ")
			.append("model.boothConstituencyElection.boothResult.validVotes, ")
			.append("model.boothConstituencyElection.booth.boothId, ")
			.append("model.boothConstituencyElection.booth.partNo, ")
			.append("model.voter.hamlet.hamletId, ")
			.append("model.voter.hamlet.hamletName ");
		query.append("from BoothConstituencyElectionVoter model ");
		query.append("where model.boothConstituencyElection.constituencyElection.election.electionId=? and ")
			//.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and ")
			.append("model.boothConstituencyElection.booth.tehsil.tehsilId = ? ");
		query.append("group by model.boothConstituencyElection.booth.boothId, model.voter.hamlet.hamletName ");
		query.append("order by model.voter.hamlet.township.townshipName");
		return getHibernateTemplate().find(query.toString(),params);
	}
	
	/*public List getTownshipWiseParyVotesByTehsil(String electionYear, String electionType, Long tehsilID){
		Object[] params = {electionYear, electionType, tehsilID};
		StringBuilder query = new StringBuilder();
		query.append("select  ");
		query.append("from BoothConstituencyElectionVoter model ");
		query.append("where model.boothConstituencyElection.constituencyElection.election.electionYear=? and ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType=? and ")
			.append("model.boothConstituencyElection.booth.tehsil.tehsilId = ? ");
		return getHibernateTemplate().find(query.toString(),params);
	}*/
	
}
