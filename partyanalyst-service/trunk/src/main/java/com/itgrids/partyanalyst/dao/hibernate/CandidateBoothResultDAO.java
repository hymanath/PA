package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateBoothResultDAO extends GenericDaoHibernate<CandidateBoothResult, Long> implements ICandidateBoothResultDAO{

	public List getBoothwisePartyResultsOfNominationInMandalWithInConstituency(Long nominationId, 
			Long assemblyId, Long tehsilId){
		Object[] params = {nominationId, assemblyId, tehsilId};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, " +
				"model.boothConstituencyElection.booth.totalVoters, model.boothConstituencyElection.boothResult.validVotes, model.votesEarned " +
				"from CandidateBoothResult model where model.nomination.nominationId = ? and " +
				"model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model.boothConstituencyElection.booth.localBody is null", params);
	}
	
	public List getBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(Long nominationId, 
			Long assemblyId, String electionYear){
		Object[] params = {nominationId, assemblyId, assemblyId, new Long(electionYear)};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, " +
				"model.boothConstituencyElection.booth.totalVoters, model.boothConstituencyElection.boothResult.validVotes, model.votesEarned " +
				"from CandidateBoothResult model where model.nomination.nominationId = ? and " +
				"model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.booth.boothId not in(select model1.booth.boothId from BoothLocalBodyWard model1 where " +
				"model1.booth.constituency.constituencyId = ? and model1.booth.year = ?) and model.boothConstituencyElection.booth.tehsil is null", params);
	}
	
	public List getBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(Long nominationId, 
			Long assemblyId, Long localBodyId){
		Object[] params = {nominationId, assemblyId, localBodyId};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, " +
				"model.boothConstituencyElection.booth.totalVoters, model.boothConstituencyElection.boothResult.validVotes, model.votesEarned " +
				"from CandidateBoothResult model where model.nomination.nominationId = ? and " +
				"model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.booth.localBody.localElectionBodyId = ?", params);
	}
	
	public List getBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(Long nominationId, 
			Long assemblyId, Long localBodyWardId){
		Object[] params = {nominationId, assemblyId, localBodyWardId};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothId, " +
				"model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, " +
				"model.boothConstituencyElection.booth.totalVoters, model.boothConstituencyElection.boothResult.validVotes, model.votesEarned " +
				"from CandidateBoothResult model where model.nomination.nominationId = ? and " +
				"model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId = ?", params);
	}
	
	public CandidateBoothResultDAO(){
		super(CandidateBoothResult.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByProperty(Object value) {
		return getHibernateTemplate().find("from CandidateBoothResult model where model.nomination.nominationId = ?", value);		
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByBoothElectionScopeAndParty(Long boothId, String electionYear, Long electionScopeId, Long partyId) {
		Object[] params = {boothId, electionYear, electionScopeId, partyId};
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.booth.boothId = ? and model.nomination.constituencyElection.election.electionYear = ? and model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.party.partyId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<CandidateBoothResult> findByConstituencyElection(Long constituencyElectionId) {
		return getHibernateTemplate().find("from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.constiElecId = ?", constituencyElectionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findPartiesByConstituencyAndElectionYear(Long constituencyId, String electionYear){
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select distinct model.nomination.party from CandidateBoothResult model where " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ?" +
				" and model.boothConstituencyElection.constituencyElection.election.electionYear = ? order by model.nomination.party.shortName ",params);
	}
	
	public List findPartyElectionResultForMandal(Long tehsilID,String electionTypeIDs, String electionYears){
		return getHibernateTemplate().find("select model.nomination.party.shortName, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"sum(model.votesEarned) from CandidateBoothResult model " +
				"where model.boothConstituencyElection.booth.tehsil.tehsilId=? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear in (" + electionYears +
				") and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId " +
				"in (" + electionTypeIDs + ") group by model.nomination.party.shortName," +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
						"model.boothConstituencyElection.constituencyElection.election.electionYear " +
						"order by model.nomination.party.shortName, " +
						"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc" , tehsilID);
	}
	
	public List findPartyElectionResultForRevenueVillage(Long revenueVillageID,String electionTypeIDs, String electionYears){
		return getHibernateTemplate().find("select model.nomination.party.shortName, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"sum(model.votesEarned) from CandidateBoothResult model " +
				"where model.boothConstituencyElection.boothConstituencyElectionId in (select distinct model.boothConstituencyElection.boothConstituencyElectionId " +
				"from VillageBoothElection model where model.township.townshipId = ? ) and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear in (" + electionYears +
				") and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionTypeId " +
				"in (" + electionTypeIDs + ") group by model.nomination.party.shortName," +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
						"model.boothConstituencyElection.constituencyElection.election.electionYear " +
						"order by model.nomination.party.shortName, " +
						"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
						"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc" , revenueVillageID);
	}
	
	public List findBoothWisePartiesAllElectionResultsByBooth(Long boothId){
		return getHibernateTemplate().find("select model.nomination.party.shortName, model.nomination.candidate.candidateId, model.nomination.candidate.firstname, " +
				"model.nomination.candidate.middlename, model.nomination.candidate.lastname, model.nomination.candidateResult.rank, model.boothConstituencyElection.booth, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.nomination.constituencyElection.constituency.name, model.nomination.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, model.votesEarned " +
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.boothId = ? " +
				"group by model.candidateBoothResultId," +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				"model.boothConstituencyElection.constituencyElection.election.electionYear " +
				"order by model.candidateBoothResultId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType desc",boothId);		
	}
	

	public List getPartyGenderWiseBoothVotesForMandal(Long tehsilID){
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId, model.nomination.party.shortName, ")
			.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname, model.nomination.candidateResult.rank, ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionYear, ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, ")
			.append("model.boothConstituencyElection.boothResult.validVotes, model.boothConstituencyElection.booth.boothId, ")
			.append("model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, ")
			.append("model.boothConstituencyElection.booth.maleVoters,model.boothConstituencyElection.booth.femaleVoters, ")
			.append("model.boothConstituencyElection.booth.totalVoters, model.votesEarned, ")
			.append("model.nomination.constituencyElection.constituency.constituencyId, ")
			.append(" model.nomination.constituencyElection.constituency.name, model.nomination.nominationId ");
		hqlQuery.append("from CandidateBoothResult model ");
		hqlQuery.append("where model.boothConstituencyElection.booth.tehsil.tehsilId=? ");
		hqlQuery.append("group by model.nomination.nominationId, ")
		.append("model.boothConstituencyElection.booth.boothId ");
		hqlQuery.append("order by model.boothConstituencyElection.constituencyElection.election.electionYear desc,")
		.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")
		.append("model.nomination.constituencyElection.constituency.constituencyId, ")
		.append("model.nomination.party.shortName, ")
		.append("model.boothConstituencyElection.booth.boothId ");
		
		return getHibernateTemplate().find(hqlQuery.toString(), tehsilID);
	}
	
	public List getElectionPartyResultsForTownship(Long electionID, Long townshipID){
		Object[] params = {townshipID, electionID};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination, sum(model.votesEarned) ");
		hqlQuery.append("from CandidateBoothResult model ")
				.append("where model.boothConstituencyElection.boothConstituencyElectionId in ")
				.append("( select distinct bcev.boothConstituencyElection.boothConstituencyElectionId ")
				.append("from BoothConstituencyElectionVoter bcev ")
				.append("where bcev.voter.hamlet.township.townshipId=? ) and ")
				.append("model.boothConstituencyElection.constituencyElection.election.electionId=?");
		hqlQuery.append("group by model.nomination");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}

	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultsForElection(Long tehsilId, Long electionId) {
		Object[] params = {electionId, tehsilId};
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("select  model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		.append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname,")
		.append("model.nomination.party.partyId, model.nomination.party.shortName,")
		.append("model.boothConstituencyElection.villageBoothElection.township.townshipId,")
		.append("model.boothConstituencyElection.villageBoothElection.township.townshipName,")
		.append("sum(model.votesEarned), sum(model.boothConstituencyElection.boothResult.validVotes), ")
		.append("model.nomination.candidateResult.rank from CandidateBoothResult model")
		.append(" where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		.append(" and model.boothConstituencyElection.villageBoothElection.township.tehsil.tehsilId = ?")
		.append(" group by model.nomination.nominationId,")
		.append("model.boothConstituencyElection.villageBoothElection.township.townshipId ")
		.append("order by model.boothConstituencyElection.villageBoothElection.township.townshipName, ")
		.append("model.nomination.nominationId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findPachayatWisePartiesResultsForElectionInATehsil(Long tehsilId, Long electionId) {
		Object[] params = {tehsilId,electionId};
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("select  model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		.append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname,")
		.append("model.nomination.party.partyId, model.nomination.party.shortName,")
		.append("model3.panchayat.panchayatId,model3.panchayat.panchayatName,")
		.append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes), ")
		.append("model.nomination.candidateResult.rank from CandidateBoothResult model,HamletBoothElection model2,PanchayatHamlet model3 ")
		.append("where model.boothConstituencyElection.boothConstituencyElectionId = model2.boothConstituencyElection.boothConstituencyElectionId and ")
		.append("model3.panchayat.tehsil.tehsilId = ? and model3.hamlet.hamletId = model2.hamlet.hamletId and ")
		.append("model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		.append("group by model.nomination.nominationId,model3.panchayat.panchayatId ")
		.append("order by model3.panchayat.panchayatName,model.nomination.nominationId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForMaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue){
		Object[] params = {electionId,tehsilId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		        .append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		        .append("model.nomination.party.partyId,model.nomination.party.shortName,")
		        .append("model.nomination.party.partyLogo,model.nomination.party.partyFlag,")
		        .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
		        .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
		        .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId)")
		        .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		        .append(" and model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model.boothConstituencyElection.booth.femaleVoters <= ?")
		        .append(" group by model.nomination.nominationId")
		        .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
		
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue){
		Object[] params = {electionId,tehsilId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		        .append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		        .append("model.nomination.party.partyId,model.nomination.party.shortName,")
		        .append("model.nomination.party.partyLogo,model.nomination.party.partyFlag,")
		        .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
		        .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
		        .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId)")
		        .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		        .append(" and model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model.boothConstituencyElection.booth.maleVoters <= ?")
		        .append(" group by model.nomination.nominationId")
		        .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
		
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long maleTrend,Long femaleTrend){
		Object[] params = {electionId,tehsilId,maleTrend,femaleTrend};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		        .append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		        .append("model.nomination.party.partyId,model.nomination.party.shortName,")
		        .append("model.nomination.party.partyLogo,model.nomination.party.partyFlag,")
		        .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
		        .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
		        .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId)")
		        .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		        .append(" and model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model.boothConstituencyElection.booth.maleVoters > ? and model.boothConstituencyElection.booth.femaleVoters > ?")
		        .append(" group by model.nomination.nominationId")
		        .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForAllBoothsInAnElection(Long electionId,Long tehsilId){
		Object[] params = {electionId,tehsilId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		        .append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		        .append("model.nomination.party.partyId,model.nomination.party.shortName,")
		        .append("model.nomination.party.partyLogo,model.nomination.party.partyFlag,")
		        .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
		        .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
		        .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId)")
		        .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		        .append(" and model.boothConstituencyElection.booth.tehsil.tehsilId = ?")
		        .append(" group by model.nomination.nominationId")
		        .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseVotingTrendz(Long electionId,Long constituencyId){
		Object[] params = {electionId,constituencyId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId,model.nomination.party.shortName,")
                .append("model.nomination.party.partyFlag,")
                .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
                .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
                .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId),")
                .append("model.nomination.candidateResult.rank")
                .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
                .append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ?")
                .append(" group by model.nomination.nominationId")
                .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleVotingTrendz(Long electionId,Long constituencyId,Long trendzValue){
		System.out.println("Inside 4 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,trendzValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId,model.nomination.party.shortName,")
                .append("model.nomination.party.partyFlag,")
                .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
                .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
                .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId),")
                .append("model.nomination.candidateResult.rank")
                .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
                .append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and model.boothConstituencyElection.booth.femaleVoters <= ?")
                .append(" group by model.nomination.nominationId")
                .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseFemaleVotingTrendz(Long electionId,Long constituencyId,Long trendzValue){
		System.out.println("Inside 4 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,trendzValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId,model.nomination.party.shortName,")
                .append("model.nomination.party.partyFlag,")
                .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
                .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
                .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId),")
                .append("model.nomination.candidateResult.rank")
                .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
                .append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and model.boothConstituencyElection.booth.maleVoters <= ?")
                .append(" group by model.nomination.nominationId")
                .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleAndFemaleVotingTrendz(Long electionId,Long constituencyId,Long maleTrendz,Long femaleTrendz){
		System.out.println("Inside 4 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,maleTrendz,femaleTrendz};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId,model.nomination.party.shortName,")
                .append("model.nomination.party.partyFlag,")
                .append("model.nomination.candidate.candidateId,model.nomination.candidate.lastname,")
                .append("sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),")
                .append("sum(model.boothConstituencyElection.booth.maleVoters),sum(model.boothConstituencyElection.booth.femaleVoters),sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId),")
                .append("model.nomination.candidateResult.rank")
                .append(" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
                .append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and model.boothConstituencyElection.booth.maleVoters > ? and model.boothConstituencyElection.booth.femaleVoters > ?")
                .append(" group by model.nomination.nominationId")
                .append(" order by model.nomination.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}

	
	public List findTownshipElectionResult(Long townshipId, Long electionId){
		Object[] params = {townshipId, electionId};
		return getHibernateTemplate().find("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.boothConstituencyElectionId in (" +
				"select distinct model1.boothConstituencyElection.boothConstituencyElectionId from BoothConstituencyElectionVoter model1 " +
				"where model1.voter.hamlet.township.townshipId = ? and model1.boothConstituencyElection.constituencyElection.election.electionId = ?)" +
				" group by model.nomination.party.partyId order by sum(model.votesEarned) desc", params);
	}
	
	public List findPartyResultsForBooths(String boothConstituencyElectionIds){
		return getHibernateTemplate().find("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.boothConstituencyElectionId in ("+boothConstituencyElectionIds+")" +
						" group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
	}
	
	public List findAllElectionsInfoInRevenueVillage(Long townshipId){
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId, model.nomination.party.shortName, ")
			.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname, model.nomination.candidateResult.rank, ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionYear, ")
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, ")
			.append("model.boothConstituencyElection.boothResult.validVotes, model.boothConstituencyElection.booth.boothId, ")
			.append("model.boothConstituencyElection.booth.partNo, model.boothConstituencyElection.booth.villagesCovered, ")
			.append("model.boothConstituencyElection.booth.maleVoters,model.boothConstituencyElection.booth.femaleVoters, ")
			.append("model.boothConstituencyElection.booth.totalVoters, model.votesEarned, ")
			.append("model.nomination.constituencyElection.constituency.constituencyId, ")
			.append(" model.nomination.constituencyElection.constituency.name, model.nomination.nominationId ");
		hqlQuery.append("from CandidateBoothResult model ");
		hqlQuery.append("where model.boothConstituencyElection.villageBoothElection.township.townshipId=? ");
		hqlQuery.append("group by model.nomination.nominationId, ")
		.append("model.boothConstituencyElection.booth.boothId ");
		hqlQuery.append("order by model.boothConstituencyElection.constituencyElection.election.electionYear desc,")
		.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")
		.append("model.nomination.constituencyElection.constituency.constituencyId, ")
		.append("model.nomination.party.shortName, ")
		.append("model.boothConstituencyElection.booth.boothId ");
		
		return getHibernateTemplate().find(hqlQuery.toString(), townshipId);
	}
	
	public List findAllElectionsInfoOfRevenueVillagesInTehsil(Long tehsilId, Long electionId){
		Object[] params = {tehsilId, electionId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.nomination.party.partyId, model.nomination.party.shortName, ")
			.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname, model.nomination.candidateResult.rank, ")
			.append("sum(model.boothConstituencyElection.boothResult.validVotes), ")
			.append("sum(model.boothConstituencyElection.booth.totalVoters), sum(model.votesEarned), ")
			.append("model.nomination.constituencyElection.constituency.constituencyId, ")
			.append(" model.nomination.constituencyElection.constituency.name, ")
			.append("model.boothConstituencyElection.villageBoothElection.township.townshipId, ")
			.append("model.boothConstituencyElection.villageBoothElection.township.townshipName ");
		hqlQuery.append("from CandidateBoothResult model ");
		hqlQuery.append("where model.boothConstituencyElection.villageBoothElection.township.tehsil.tehsilId=? ")
				.append("and model.boothConstituencyElection.constituencyElection.election.electionId = ?");
		hqlQuery.append("group by model.boothConstituencyElection.constituencyElection.election.electionYear,")
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")
			.append("model.boothConstituencyElection.villageBoothElection.township.townshipId,")
			.append("model.nomination.nominationId, ")
			.append("model.nomination.constituencyElection.constituency.constituencyId ");
		hqlQuery.append("order by model.boothConstituencyElection.constituencyElection.election.electionYear desc,")
		.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")
		.append("model.nomination.constituencyElection.constituency.constituencyId, ")
		.append("model.boothConstituencyElection.villageBoothElection.township.townshipId,")
		.append("sum(model.votesEarned) desc ");
		
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	public List findBoothResultsForTownshipAndElection(Long townshipId, Long electionId){
		Object[] params = {townshipId, electionId};
		return getHibernateTemplate().find("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.villageBoothElection.township.townshipId = ? and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId = ?" +
						" group by model.nomination.party.partyId order by sum(model.votesEarned) desc", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findBoothResultsForBoothsAndElection(List<Long> boothslist, Long electionId){
		Query query = getSession().createQuery("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist) group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
		
		query.setParameter(0,electionId);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findBoothResultsForBoothsAndElectionWithParty(List<Long> boothslist, Long electionId){
		Query query = getSession().createQuery("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				" sum(model.votesEarned),model.nomination.candidate.candidateId, model.nomination.candidate.lastname,model.nomination.candidateResult.rank " +
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist) group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
		
		query.setParameter(0,electionId);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	public List findRevenueVillagesWisePartyResultsForElectionInMandal(Long tehsilId, Long electionId, Long partyId) {
		Object[] params = {electionId, tehsilId, partyId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.villageBoothElection.township.townshipId, " +
				"model.boothConstituencyElection.villageBoothElection.township.townshipName, sum(m)")
		.append("sum(model.votesEarned) from CandidateBoothResult model")
		.append(" where model.boothConstituencyElection.constituencyElection.election.electionId = ?")
		.append(" and model.boothConstituencyElection.villageBoothElection.township.tehsil.tehsilId = ? group by model.nomination.party.partyId," +
				"model.boothConstituencyElection.villageBoothElection.township.townshipId " +
				"order by model.boothConstituencyElection.villageBoothElection.township.townshipId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}

	public List getAllACPCPartiesInMandal(Long tehsilId) {
		return getHibernateTemplate().find("select distinct model.nomination.party.partyId, model.nomination.party.shortName from " +
				" CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? order by model.nomination.party.partyId", tehsilId);
	}

	public List getAllACPCPartiesInRevenueVillage(Long townshipId) {
		return getHibernateTemplate().find("select distinct model.nomination.party.partyId, model.nomination.party.shortName from " +
				" CandidateBoothResult model where model.boothConstituencyElection.villageBoothElection.township.townshipId = ?", townshipId);
	}
	
	// These Following three Methods are Used to calculate the Election Results for all Parties that are Participated in
	// An Assembly Region By Mandal or By Local Election Body or By Greater Wards
	
	@SuppressWarnings("unchecked")
	public List getCandidatesResultsForElectionAndConstituencyByMandal(Long constituencyId, String electionYear, String electionType){
		Object[] params = {new Long(electionYear), constituencyId, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.tehsil.tehsilName," +
				" model.boothConstituencyElection.booth.tehsil.tehsilId, model.nomination.candidateResult.rank, model.nomination.candidate.lastname, " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.nomination.candidate.candidateId, " +
				" model.nomination.party.partyId from CandidateBoothResult model where model.boothConstituencyElection.booth.year = ? " +
				" and model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				" model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? and " +
				" model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				" model.nomination.nominationId order by model.boothConstituencyElection.booth.tehsil.tehsilName,model.nomination.candidateResult.rank",params);
	}
	@SuppressWarnings("unchecked")
	public List getCandidatesResultsForElectionAndConstituencyByMandalWise(Long constituencyId, String electionYear, String electionType){
		Object[] params = {new Long(electionYear), constituencyId, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.tehsil.tehsilName," +
				" model.boothConstituencyElection.booth.tehsil.tehsilId, model.nomination.candidateResult.rank, model.nomination.candidate.lastname, " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.nomination.candidate.candidateId, " +
				" model.nomination.party.partyId from CandidateBoothResult model where model.boothConstituencyElection.booth.year = ? " +
				" and model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				" model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				" model.nomination.nominationId",params);
	}
	@SuppressWarnings("unchecked")
	public List getCandidatesResultsForElectionAndConstituencyByLocalElectionBody(Long constituencyId, String electionYear, String electionType, 
			String localBodyTypes){
		Object[] params = {new Long(electionYear), constituencyId, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.localBody.electionType.electionType," +
				" model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.candidateResult.rank, model.nomination.candidate.lastname, model.nomination.party.shortName," +
				" sum(model.votesEarned),model.nomination.candidate.candidateId, model.nomination.party.partyId, " +
				" model.boothConstituencyElection.booth.localBody.name from CandidateBoothResult model where " +
				" model.boothConstituencyElection.booth.year = ? and model.boothConstituencyElection.booth.constituency.constituencyId = ?" +
				" and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? and " +
				" model.boothConstituencyElection.booth.localBody.electionType.electionType in ("+localBodyTypes+") " +
				" group by model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.nominationId order by model.boothConstituencyElection.booth.localBody.name,model.nomination.candidateResult.rank",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getCandidatesResultsForElectionAndConstituencyByLocalElectionBodyWard(Long constituencyId, String electionYear, String electionType,
			String localBodyType){
		Object[] params = {new Long(electionYear), constituencyId, electionType, localBodyType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId, " +
				" model.nomination.candidateResult.rank, model.nomination.candidate.lastname, model.nomination.party.shortName," +
				" sum(model.votesEarned),model.nomination.candidate.candidateId, model.nomination.party.partyId, " +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.localElectionBody.name " +
				" from CandidateBoothResult model where model.boothConstituencyElection.booth.year = ? " +
				" and model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				" model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? and " +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.localElectionBody.electionType.electionType = ? " +
				" group by model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId, " +
				" model.nomination.nominationId order by model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name,model.nomination.candidateResult.rank",params);
	}

	public List findAssemblyRegionResultsForPartiesForAConstituency(Long acId, Long pcId, String electionYear){
		Object[] params = {acId, new Long(electionYear.trim()), pcId};
		return getHibernateTemplate().find("select model.nomination.party.partyId, model.nomination.party.shortName, " +
				" model.nomination.candidateResult.rank, sum(model.votesEarned), sum(model.boothConstituencyElection.boothResult.validVotes) from " +
				" CandidateBoothResult model where model.boothConstituencyElection.booth.constituency.constituencyId = ? and " +
				" model.boothConstituencyElection.booth.year = ? and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ?" +
				" group by model.nomination.nominationId order by model.nomination.nominationId", params);
		
	}
	
	public List findAllPartiesElectionResultsInDistrictForElectionType(Long districtId, String electionType){
		Object[] params = {districtId, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.nomination.party.partyId, model.nomination.party.shortName, sum(model.votesEarned) " +
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.district.districtId = ? " +
				"and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.nomination.party.partyId ", params);
	}

	public List getcandidatesResultsByBoothConstiIds(String boothConstiElecIds) {
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append("select  model.boothConstituencyElection.constituencyElection.constituency.constituencyId,")
		.append("model.boothConstituencyElection.constituencyElection.constituency.name,")
		.append("model.nomination.candidate.candidateId, model.nomination.candidate.lastname,")
		.append("model.nomination.party.partyId, model.nomination.party.shortName,")
		.append("sum(model.votesEarned), sum(model.boothConstituencyElection.boothResult.validVotes), ")
		.append("model.nomination.candidateResult.rank from CandidateBoothResult model")
		.append(" where model.boothConstituencyElection.boothConstituencyElectionId in ("+boothConstiElecIds+")")
		.append("group by model.boothConstituencyElection.constituencyElection.constituency.constituencyId," +
				"model.nomination.nominationId");
		return getHibernateTemplate().find(hqlQuery.toString());
	}

	@SuppressWarnings("unchecked")
	public List getElectionResultsInAMandalForAllParties(Long mandalId,String electionType,String electionYear) {
		
		Object[] params = {mandalId,electionType,electionYear};
		return getHibernateTemplate().find("select model.nomination.party.partyId,model.nomination.party.shortName,"+
				"sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),model.nomination.candidateResult.rank,"+
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId,"+
				"model.boothConstituencyElection.constituencyElection.constituency.name from CandidateBoothResult model "+
				"where model.boothConstituencyElection.booth.tehsil.tehsilId = ? "+
				"and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? "+
				"and model.boothConstituencyElection.constituencyElection.election.electionYear = ? "+
				"group by model.nomination.nominationId",params);
				
	}
		
	@SuppressWarnings("unchecked")
	public List getResultsForElectionAndConstituencyByMandal(String mandalIds, String electionYear,String electionType){
		Object[] parms = {electionYear,electionType};
		return getHibernateTemplate().find("select model.nomination.party.shortName," +
				" sum(model.votesEarned), model.nomination.party.partyId,sum(model.boothConstituencyElection.boothResult.validVotes)"+
				" from CandidateBoothResult model " +
				" where model.boothConstituencyElection.booth.tehsil.tehsilId in (" + mandalIds +
				" ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? group by " +
				" model.nomination.party.partyId order by sum(model.votesEarned) desc ",parms);
				
	}
	
	public List getResultsForElectionForAllMandalsAndParties(String mandalIds, String electionYear,String electionType){
		Object[] params = {electionYear,electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, model.nomination.party.shortName, " +
				"sum(model.votesEarned), model.nomination.party.partyId from CandidateBoothResult model " +
				" where model.boothConstituencyElection.booth.tehsil.tehsilId in (" + mandalIds +")" +
				" and model.boothConstituencyElection.constituencyElection.election.electionYear = ? " +
				" and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.booth.tehsil.tehsilId, model.nomination.party.partyId " +
				"order by sum(model.votesEarned) desc ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getResultsForElectionAndConstituencyByMandalByPaliamentWise(Long constituencyId,String mandalIds,String electionYear){
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select model.nomination.candidateResult.rank, model.nomination.candidate.lastname, model.nomination.party.shortName," +
				" sum(model.votesEarned),model.nomination.candidate.candidateId, model.nomination.party.partyId from CandidateBoothResult model " +
				" where model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and " +
				" model.boothConstituencyElection.booth.tehsil.tehsilId in (" + mandalIds +
				" ) and model.boothConstituencyElection.constituencyElection.election.electionYear = ? group by " +
				" model.nomination.nominationId,model.boothConstituencyElection.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getAllPartiesPariticipatedInAConstituencyElection(
			Long constituencyId) {
		
		return getHibernateTemplate().find("select distinct model.nomination.party"+
				" from CandidateBoothResult model where model.nomination.constituencyElection.constituency.constituencyId = ? and"+
				" model.nomination.constituencyElection.election.electionYear=2009 or model.nomination.constituencyElection.election.electionYear=2004"+
				" and model.nomination.constituencyElection.election.electionScope.electionType.electionType='Assembly' or model.nomination.constituencyElection.election.electionScope.electionType.electionType='Parliament'"+
				" group by model.nomination.nominationId",constituencyId);
	}

	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandal(Long tehsilId, Long partyId,
			String electionYear) {
		Object[] params = {tehsilId,partyId,electionYear};
	 return getHibernateTemplate().find("select model.boothConstituencyElection.booth,model.votesEarned,"+
			 "model.boothConstituencyElection.boothResult.validVotes,"+
			 "model.nomination.constituencyElection.constituency.constituencyId,"+
			 "model.nomination.constituencyElection.constituency.name,"+
			 "model.nomination.party.partyId,model.nomination.party.shortName,"+
			 "model.nomination.constituencyElection.election.electionScope."+
			 "electionType.electionType,model.percentage from CandidateBoothResult model where "+
			 "model.boothConstituencyElection.booth.tehsil.tehsilId = ? and "+
			 "model.nomination.party.partyId = ? and model.nomination."+
			 "constituencyElection.election.electionYear = ? order by model.nomination."+
			 "constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandalByPartyRank(Long tehsilId,Long constituencyId,
			String electionYear,String electionType, Long rank) {
		Object[] params = {tehsilId,constituencyId,electionYear,electionType,rank};	
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth,model.votesEarned,"+
				 "model.boothConstituencyElection.boothResult.validVotes,"+
			     "model.nomination.constituencyElection.constituency.constituencyId,"+
			     "model.nomination.constituencyElection.constituency.name,"+
			     "model.nomination.party.partyId,model.nomination.party.shortName,"+
			     "model.nomination.constituencyElection.election.electionScope."+
			     "electionType.electionType,model.percentage from CandidateBoothResult model "+
			     "where model.boothConstituencyElection.booth.tehsil.tehsilId = ? "+
			     "and model.nomination.constituencyElection.constituency.constituencyId = ? "+
				 "and model.boothConstituencyElection.constituencyElection.election.electionYear = ? "+
				 "and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? "+
				 "and model.nomination.candidateResult.rank = ? "+
			     "order by model.boothConstituencyElection.booth.boothId",params);
	}
	
	public List findPartyResultsInAllElectionsByRevenueVillagesInMandal(Long tehsilId, Long partyId){
		Object[] params = {tehsilId, partyId};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.boothConstituencyElection.villageBoothElection.township.townshipId, " +
				"model.boothConstituencyElection.villageBoothElection.township.townshipName, " +
				"sum(model.votesEarned), sum(model.boothConstituencyElection.boothResult.validVotes), " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.constituencyElection.constituency.name " +
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? " +
				"and model.nomination.party.partyId = ? group by model.boothConstituencyElection.constituencyElection.election.electionId," +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId," +
				"model.boothConstituencyElection.villageBoothElection.township.townshipId order by " +
				"model.boothConstituencyElection.villageBoothElection.township.townshipName, " +
				"model.boothConstituencyElection.constituencyElection.election.electionId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getPartyResultsInAMandalForAnElection(Long tehsilId,
			Long constituencyId, Long partyId, String electionYear) {
		Object[] params = {tehsilId,constituencyId,partyId,electionYear};
		return getHibernateTemplate().find("select sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),"+
				"sum(model.boothConstituencyElection.booth.totalVoters),count(model.boothConstituencyElection.booth.boothId) "+
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? "+
				"and model.nomination.constituencyElection.constituency.constituencyId = ? and "+
				"model.nomination.party.partyId = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getPartyResultsInAMandalForAnElection(Long tehsilId,
			Long constituencyId, String electionYear, Long rank) {
		Object[] params = {tehsilId,constituencyId,electionYear,rank};
		return getHibernateTemplate().find("select sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),"+
				"model.nomination.party.shortName "+
				"from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? "+
				"and model.nomination.constituencyElection.constituency.constituencyId = ? and "+
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? and "+
				"model.nomination.candidateResult.rank = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandalByConstituencyId(
			Long tehsilId, Long partyId, Long constituencyId,
			String electionYear, String electionType) {
		Object[] params = {tehsilId,partyId,constituencyId,electionYear,electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth, model.votesEarned, "+
				 "model.boothConstituencyElection.boothResult.validVotes from CandidateBoothResult model where "+
				 "model.boothConstituencyElection.booth.tehsil.tehsilId = ? and model.nomination.party.partyId = ? "+
				 "and model.nomination.constituencyElection.constituency.constituencyId = ? " +
				 "and model.nomination.constituencyElection.election.electionYear = ? and "+
				 "model.nomination.constituencyElection.election.electionScope.electionType.electionType = ? "+
				 "order by model.boothConstituencyElection.booth.boothId", params);
				 
	}

	public List getAllPartiesResultsInAllElectionsByRevenueVillgesInMandal(String condition, Long tehsilId){

		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select model.boothConstituencyElection.constituencyElection.election.electionId, ")//0
			.append("model.boothConstituencyElection.constituencyElection.election.electionYear, ")//1
			.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")//2
			.append("model.boothConstituencyElection.villageBoothElection.township.townshipId,")	//3
			.append("model.boothConstituencyElection.villageBoothElection.township.townshipName,")//4
			.append("model.nomination.party.partyId, model.nomination.party.shortName, sum(model.votesEarned) ");//5 6 7 
		hqlQuery.append("from CandidateBoothResult model ");
		hqlQuery.append("where model.boothConstituencyElection.villageBoothElection.township.tehsil.tehsilId= " + tehsilId +" ");
		hqlQuery.append(condition);
		hqlQuery.append("order by model.boothConstituencyElection.constituencyElection.election.electionYear desc,")
		.append("model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,")
		.append("model.boothConstituencyElection.villageBoothElection.township.townshipName,")
		.append("model.nomination.party.shortName ");
		return getHibernateTemplate().find(hqlQuery.toString());
	}
    
		
	@SuppressWarnings("unchecked")
	public List findTownshipWiseAllPartyResultsInAMandal(String electionType,
			String electionYear, Long mandalId){
		Object[] params = {mandalId,electionType,electionYear};
		return getHibernateTemplate().find("select sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),"+
				"model.boothConstituencyElection.villageBoothElection.township.townshipId,"+
				"model.boothConstituencyElection.villageBoothElection.township.townshipName,"+
				"model.nomination.party.partyId,model.nomination.party.shortName,sum(model.boothConstituencyElection.booth.totalVoters) from "+
				"CandidateBoothResult model where model.boothConstituencyElection.villageBoothElection."+
				"township.tehsil.tehsilId = ? and model.boothConstituencyElection.constituencyElection.election."+
				"electionScope.electionType.electionType = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? "+
				"group by model.boothConstituencyElection.villageBoothElection.township.townshipId,model.nomination.nominationId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findPanchayatWiseAllPartyResultsInAMandal(String electionType,
			String electionYear, Long mandalId){
		Object[] params = {mandalId,electionType,electionYear};
		return getHibernateTemplate().find("select sum(model.votesEarned),sum(model.boothConstituencyElection.boothResult.validVotes),"+
				"model3.panchayat.panchayatId,model3.panchayat.panchayatName,"+
				"model.nomination.party.partyId,model.nomination.party.shortName,sum(model.boothConstituencyElection.booth.totalVoters) from "+
				"CandidateBoothResult model, HamletBoothElection model2, PanchayatHamlet model3 where " +
				"model.boothConstituencyElection.boothConstituencyElectionId = model2.boothConstituencyElection.boothConstituencyElectionId and " +
				"model2.hamlet.hamletId = model3.hamlet.hamletId and model3.panchayat.tehsil.tehsilId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election."+
				"electionScope.electionType.electionType = ? and model.boothConstituencyElection.constituencyElection.election.electionYear = ? "+
				"group by model3.panchayat.panchayatId,model.nomination.nominationId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getValidVotesInAMandal(Long tehsilId, String electionType,
			String electionYear) {
		Object[] params = {tehsilId,electionType,electionYear};
		return getHibernateTemplate().find("select sum(model.boothConstituencyElection.boothResult.validVotes)"+
				" from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ?"+
				" and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.boothConstituencyElection.constituencyElection.election.electionYear = ? "+
				"group by model.boothConstituencyElection.booth.boothId",params);
	}

	public List findElectionResultsForAMappedConstituencyByElectionType(
			Long constituencyId, String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.constituencyElection.constituency.name, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, model.nomination.party.partyId, " +
				"model.nomination.party.shortName, " +
				"sum(model.boothConstituencyElection.boothResult.validVotes), sum(model.votesEarned), " +
				"model.boothConstituencyElection.mappedConstituency.name from CandidateBoothResult model " +
				"where  model.boothConstituencyElection.mappedConstituency.constituencyId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? group by " +
				"model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId, model.nomination.nominationId order by " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, "+
				"model.boothConstituencyElection.constituencyElection.constituency.name", params);
	}
	
	public List findElectionResultsForAConstituencyByElectionYear(Long constituencyId, String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId, " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId, " +
				"model.boothConstituencyElection.constituencyElection.constituency.name, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, model.nomination.party.partyId, " +
				"model.nomination.party.shortName, " +
				"sum(model.boothConstituencyElection.boothResult.validVotes), sum(model.votesEarned) from CandidateBoothResult model " +
				"where  model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? group by " +
				"model.boothConstituencyElection.booth.tehsil.tehsilId, model.nomination.nominationId order by " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName, model.nomination.party.shortName", params);
	}
	
	public List findAssemblyWiseParliamentResultsForPartiesInAssembly(Long acId, String electionYear){
		Object[] params = {acId, electionYear};
		return getHibernateTemplate().find("select model.boothConstituencyElection.constituencyElection.election.electionId, " +//0
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +//1
				"model.boothConstituencyElection.constituencyElection.election.electionYear, " +//2
				"model.boothConstituencyElection.constituencyElection.constituency.constituencyId, " +//3
				"model.boothConstituencyElection.constituencyElection.constituency.name, " +//4
				"model.boothConstituencyElection.booth.tehsil.tehsilId, " +//5
				"model.boothConstituencyElection.booth.tehsil.tehsilName, model.nomination.party.partyId, " +//6,7
				"model.nomination.party.shortName, " +//8
				"sum(model.boothConstituencyElection.boothResult.validVotes), sum(model.votesEarned) from " +//9,10
				"CandidateBoothResult model where model.boothConstituencyElection.booth.boothId in(" +
				"select distinct model1.booth.boothId from BoothConstituencyElection model1 where " +
				"model1.constituencyElection.constituency.constituencyId = ? and model1.constituencyElection.election.electionYear = ?) " +
				"and model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = '" +
				IConstants.PARLIAMENT_ELECTION_TYPE+"'group by model.boothConstituencyElection.constituencyElection.constituency.constituencyId," +
						"model.boothConstituencyElection.booth.tehsil.tehsilId," +
						"model.nomination.nominationId order by model.boothConstituencyElection.constituencyElection.constituency.name, " +
						"model.boothConstituencyElection.booth.tehsil.tehsilName,model.nomination.party.shortName", params);
	}
	
	public List getAllPartiesResultsByMandalsMappedConstituency(Long constituencyId, String elecYear, String elecType){
		Object[] params = {constituencyId, elecYear, elecType};
		return getHibernateTemplate().find("select model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.boothConstituencyElection.booth.tehsil.tehsilName," +
				"model.nomination.party.partyId," +
				"model.nomination.party.shortName, " +
				"sum(model.boothConstituencyElection.boothResult.validVotes),  sum(model.votesEarned) from " +
				"CandidateBoothResult model where  model.boothConstituencyElection.mappedConstituency.constituencyId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType = ? " +
				"group by model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				"model.nomination.party.partyId order by model.boothConstituencyElection.booth.tehsil.tehsilName", params);
	}

	public List getBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(List partNos, String elecYear, Long constiId){
		Query queryObject = getSession().createQuery("select model.nomination.candidate.candidateId, model.nomination.candidate.lastname," +
				"model.nomination.party.partyId, model.nomination.party.shortName, sum(model.votesEarned), " +
				"sum(model.boothConstituencyElection.boothResult.validVotes) from " +
				"CandidateBoothResult model where  model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? and " +
				"model.boothConstituencyElection.constituencyElection.election.electionYear = ? and " +
				"model.boothConstituencyElection.booth.partNo in (:partNos) group by model.nomination.nominationId " +
				"order by sum(model.votesEarned) desc");
		queryObject.setParameter(0,constiId);
		queryObject.setParameter(1,elecYear);
		queryObject.setParameterList("partNos", partNos);
		return queryObject.list();
	}

	public List getPartNosOfAnElectionForAConstituency(Long constituencyId,
			String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select distinct model.boothConstituencyElection.booth.partNo from CandidateBoothResult "+
				"model where model.boothConstituencyElection.constituencyElection.constituency.constituencyId = ? " +
				"and model.boothConstituencyElection.constituencyElection.election.electionYear = ?",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatWisePartiesResultForAElectionInATehsil(Long tehsilId,Long electionId)
	{
		Object[] params = {electionId,tehsilId};
		return getHibernateTemplate().find("select P.panchayat.panchayatName,CBR.nomination.party.shortName,sum(CBR.votesEarned) from CandidateBoothResult CBR, " +
				" HamletBoothElection HBE,PanchayatHamlet P where CBR.boothConstituencyElection.boothConstituencyElectionId = HBE.boothConstituencyElection.boothConstituencyElectionId and " +
				" HBE.hamlet.hamletId = P.hamlet.hamletId and CBR.boothConstituencyElection.constituencyElection.election.electionId = ? and " +
				" P.panchayat.tehsil.tehsilId = ? group by P.panchayat.panchayatId,CBR.nomination.party.partyId ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getdata(Long tehsilId,Long electionId)
	{
		Object[] params = {electionId,tehsilId};
		return getHibernateTemplate().find("select P.panchayat.tehsil.tehsilName,P.panchayat.panchayatName,CBR.boothConstituencyElection.booth.partNo,CBR.boothConstituencyElection.boothResult.validVotes," +
				" CBR.nomination.party.shortName,CBR.votesEarned from CandidateBoothResult CBR, " +
				" HamletBoothElection HBE,PanchayatHamlet P where CBR.boothConstituencyElection.boothConstituencyElectionId = HBE.boothConstituencyElection.boothConstituencyElectionId and " +
				" HBE.hamlet.hamletId = P.hamlet.hamletId and CBR.boothConstituencyElection.constituencyElection.election.electionId = ? and P.panchayat.tehsil.tehsilId = ? " +
				" group by P.panchayat.tehsil.tehsilId,P.panchayat.panchayatId,CBR.nomination.party.partyId order by P.panchayat.tehsil.tehsilName,P.panchayat.panchayatName,CBR.nomination.party.shortName",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartiesParticipatedInAManadalForAnElection(Long tehsilId, Long electionId)
	{
		Object[] params = {tehsilId,electionId};
		return (List<Long>)getHibernateTemplate().find("select distinct model.nomination.party.partyId from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId = ? and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId = ? order by model.nomination.party.shortName",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartyIdsByMandalIdAndElectionYear(String type, Long id, String year)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.nomination.party.partyId from CandidateBoothResult model where ");
		str.append(" model.boothConstituencyElection.constituencyElection.election.electionYear =:electionYear ");
		if(type.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model.boothConstituencyElection.booth.tehsil.tehsilId = :id and model.boothConstituencyElection.booth.localBody is null ");
		else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			str.append(" and model.boothConstituencyElection.booth.localBody.localElectionBodyId =:id and model.boothConstituencyElection.booth.panchayat is null ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("id", id);
		query.setParameter("electionYear", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartyIdsByLocalEleBodyIdAndElectionYear(Long localEleBodyId, String year)
	{
		Query query = getSession().createQuery("select distinct model.nomination.party.partyId from CandidateBoothResult model where model.boothConstituencyElection.booth.localBody.localElectionBodyId =:localElectionBodyId " +
				" and model.boothConstituencyElection.constituencyElection.election.electionYear =:electionYear and model.boothConstituencyElection.booth.panchayat is null ");
		query.setParameter("localElectionBodyId", localEleBodyId);
		query.setParameter("electionYear", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPartiesCrossVotingReportByMandalIdAndAssemblyConstituencyId(Long mandalId, Long constituencyId, String year, List<Long> partyIdsList)
	{
		Query query = getSession().createQuery("select distinct(model.nomination.party.partyId), model.nomination.party.shortName, " +
				" model.nomination.constituencyElection.constituencyElectionResult.validVotes, model.votesEarned from CandidateBoothResult model " +
				" where model.boothConstituencyElection.booth.tehsil.tehsilId =:tehsilId and model.boothConstituencyElection.constituencyElection.election.electionYear =:year and model.nomination.party.partyId in (:partyIdsList) " +
				" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId =:constituencyId and model.boothConstituencyElection.booth.localBody is null ");
		
		query.setParameter("tehsilId", mandalId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		query.setParameterList("partyIdsList", partyIdsList);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPartiesCrossVotingReportByEleYearAndConstituencyId(String type, Long id, Long constituencyId, String year, List<Long> partyIdsList)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.nomination.party.partyId), model.nomination.party.shortName, model.nomination.constituencyElection.constituencyElectionResult.validVotes, ");
		str.append(" model.votesEarned from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionYear =:year and model.nomination.party.partyId in (:partyIdsList) ");
		str.append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId =:constituencyId ");
		if(type.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model.boothConstituencyElection.booth.tehsil.tehsilId =:id and model.boothConstituencyElection.booth.localBody is null ");
		else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
		str.append(" and model.boothConstituencyElection.booth.localBody.localElectionBodyId =:id and model.boothConstituencyElection.booth.panchayat is null  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("id", id);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		query.setParameterList("partyIdsList", partyIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getValidVotesByEleTypeAndConstituencyId(String type, Long id, Long constituencyId, String year)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.nomination.constituencyElection.constituencyElectionResult.validVotes from CandidateBoothResult model where ");
		str.append(" model.boothConstituencyElection.constituencyElection.election.electionYear =:year and model.boothConstituencyElection.constituencyElection.constituency.constituencyId =:constituencyId ");
		if(type.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model.boothConstituencyElection.booth.tehsil.tehsilId =:id and model.boothConstituencyElection.booth.localBody is null ");
		else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			str.append(" and model.boothConstituencyElection.booth.localBody.localElectionBodyId =:id and model.boothConstituencyElection.booth.panchayat is null ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("id", id);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartyIdsListByEleIdAndYearAndConstId(Long id, Long electionId, String year)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.nomination.party.partyId) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.constituency.constituencyId =:id and " +
				" model.boothConstituencyElection.constituencyElection.election.electionYear =:electionYear and model.boothConstituencyElection.constituencyElection.election.electionId =:electionId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("id", id);
		query.setParameter("electionYear", year);
		query.setParameter("electionId", electionId);
		return query.list();
	}
	
	public List<Object[]> findBoothResultsForBoothsAndElectionForParties(List<Long> boothslist, Long electionId,List<Long> partyIds){
		Query query = getSession().createQuery("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				" sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? and model.nomination.party.partyId in(:partyIds) " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist) group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
		
		query.setParameter(0,electionId);
		query.setParameterList("partyIds",partyIds);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	public Long findBoothResultsForBoothsAndElectionForPartiesWithAlliance(List<Long> boothslist, Long electionId,List<Long> partyIds){
		Query query = getSession().createQuery("select sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? and model.nomination.party.partyId in(:partyIds) " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist)");
		
		query.setParameter(0,electionId);
		query.setParameterList("partyIds",partyIds);
		query.setParameterList("boothslist",boothslist);
		return (Long)query.uniqueResult();
	}
	
	
	
	public List<Object[]> getMandalResultsForElectionAndConstituency(Long constituencyId, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId  in( :electionIds) and model.nomination.party.partyId in( :partyIds) and " +
				" model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	public List<Object[]> getMandalResultsForElectionAndConstituencyByTehsilIds(List<Long> mandalIds, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId ,model.boothConstituencyElection.constituencyElection.election.electionId  from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.tehsil.tehsilId in(:mandalIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId  in( :electionIds) and model.nomination.party.partyId in( :partyIds) and " +
				" model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.tehsil.tehsilId, " +
				" model.nomination.party.partyId");
		query.setParameterList("mandalIds", mandalIds);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	public List<Object[]> getLocalbodyResultsForElectionAndConstituency(Long constituencyId, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" model.boothConstituencyElection.booth.localBody.name,  " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId, model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	public List<Object[]> getLocalbodyResultsForElectionAndConstituency1(Long constituencyId, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" model.boothConstituencyElection.booth.localBody.name,  " +
				" model.nomination.party.shortName, sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId ,model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId, model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getlocalbodywardResults(Long constituencyId, List<Long> electionIds,List<Long> partyIds){
		
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name, " +
				" model.nomination.party.shortName," +
				" sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
}
	
public List<Object[]> getlocalbodywardResults1(Long constituencyId, List<Long> electionIds,List<Long> partyIds){
		
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name, " +
				" model.nomination.party.shortName," +
				" sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType," +
				" model.nomination.party.partyId,model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		return query.list();
}
	
	
	public List<Object[]> getParticipatedPartiesInConstituency(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.nomination.party.partyId, model.nomination.party.shortName from CandidateBoothResult model where model.boothConstituencyElection.booth.constituency.constituencyId =? order by model.nomination.party.shortName ",constituencyId);
	}
	
	public List<Object[]> getParticipatedPartiesByAssembly(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.nomination.party.partyId, model.nomination.party.shortName from CandidateBoothResult model where model.boothConstituencyElection.booth.constituency.constituencyId =? and model.nomination.constituencyElection.election.electionScope.electionType.electionTypeId = 2  order by model.nomination.party.shortName ",constituencyId);
	}
	
	public List<Object[]> getParticipatedPartiesByTehsilIds(List<Long> tehsilIds)
	{
		Query query = getSession().createQuery("select distinct model.nomination.party.partyId, model.nomination.party.shortName from CandidateBoothResult model where model.boothConstituencyElection.booth.tehsil.tehsilId in(:tehsilIds) order by model.nomination.party.shortName");
		
		query.setParameterList("tehsilIds", tehsilIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getMandalResultsForElectionAndConstituencywithAlliance(Long constituencyId,Long electionId,List<Long> partyIds)
	{
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				"  sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType" +
				"  from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId=:electionId and model.nomination.party.partyId in( :partyIds) and " +
				" model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	
	
	public List<Object[]> getMandalResultsForElectionAndTehsilIdswithAlliance(List<Long> tehsilIds,Long electionId,List<Long> partyIds)
	{
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				"  sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType" +
				"  from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.tehsil.tehsilId  in(:tehsilIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId=:electionId and model.nomination.party.partyId in( :partyIds) and " +
				" model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId");
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("electionId", electionId);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	
	public List<Object[]> getLocalbodyResultsForElectionAndConstituencywithAlliance(Long constituencyId, Long electionId,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" model.boothConstituencyElection.booth.localBody.name,  " +
				" sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType" +
				" from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		query.setParameterList("partyIds", partyIds);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getlocalbodywardResultswithAlliance(Long constituencyId, Long electionId,List<Long> partyIds){
		
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name, " +
			
				" sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType" +
				" from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		query.setParameterList("partyIds", partyIds);
		return query.list();
}
	
	
	
	public List<Object[]> getMandalValidvotes(Long constituencyId,List<Long> electionIds)
	{
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				"  sum(model.votesEarned) from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" and model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		
		return query.list();
	}
	
	public List<Object[]> getMandalValidvotesByTehsilIds(List<Long> tehsilIds,List<Long> electionIds)
	{
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName,  " +
				"  sum(model.votesEarned) from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.tehsil.tehsilId in(:tehsilIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" and model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId");
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameterList("electionIds", electionIds);
		
		return query.list();
	}
	
	public List<Object[]> getMandalValidvotesByTehsilIds1(List<Long> tehsilIds,List<Long> electionIds)
	{
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" model.boothConstituencyElection.booth.tehsil.tehsilName ," +
				"  sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionId  from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.tehsil.tehsilId in(:tehsilIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" and model.boothConstituencyElection.booth.localBody is null " +
				" group by model.boothConstituencyElection.booth.tehsil.tehsilId , model.boothConstituencyElection.constituencyElection.election.electionId");
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameterList("electionIds", electionIds);
		
		return query.list();
	}
	
	
	public List<Object[]> getLocalbodyValidvotes(Long constituencyId, List<Long> electionIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" model.boothConstituencyElection.booth.localBody.name,  " +
				" sum(model.votesEarned) from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" group by model.boothConstituencyElection.booth.localBody.localElectionBodyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		
		return query.list();
	}
	
	public List<Object[]> getLocalbodyValidvotes1(Long constituencyId, List<Long> electionIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" model.boothConstituencyElection.booth.localBody.name,  " +
				" sum(model.votesEarned) ,model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" group by model.boothConstituencyElection.booth.localBody.localElectionBodyId , " +
				"model.boothConstituencyElection.constituencyElection.election.electionId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getlocalbodywardValidvotes(Long constituencyId, List<Long> electionIds){
		
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name, " +
				" sum(model.votesEarned) from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" group by model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId");
				
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
	
		return query.list();
}	
	
  public List<Object[]> getlocalbodywardValidvotes1(Long constituencyId, List<Long> electionIds){
		
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				" model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.name, " +
				" sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where  " +
				"  model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds)" +
				" group by model.boothConstituencyElection.booth.boothLocalBodyWard.localBodyWard.constituencyId," +
				"model.boothConstituencyElection.constituencyElection.election.electionId");
				
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
	
		return query.list();
}	
	public List<Object[]> findBoothResultsForMultipleBoothsAndElectionsForParties(Set<Long> boothslist, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.boothId,model.nomination.party.partyId, model.nomination.party.shortName,"+
				" model.votesEarned ,model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType from CandidateBoothResult model where   " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in(:electionIds) and model.nomination.party.partyId in(:partyIds) and model.boothConstituencyElection.booth.boothId in(:boothslist) ");
		
		query.setParameterList("electionIds",electionIds);
		query.setParameterList("partyIds",partyIds);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	public List<Object[]> findBoothCountForMultipleBoothsAndElectionsForParties(Set<Long> boothslist, List<Long> electionIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.boothId,"+
				" model.votesEarned  from CandidateBoothResult model where   " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in(:electionIds) and model.boothConstituencyElection.booth.boothId in(:boothslist) ");
		
		query.setParameterList("electionIds",electionIds);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	public List<Object[]> findBoothResultsForMultipleBoothsInElections(Long constituencyId,List<String> partNos, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.partNo,model.nomination.party.partyId, model.nomination.party.shortName,"+
				" model.votesEarned ,model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType,model.boothConstituencyElection.booth.partNo   " +
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId in(:electionIds) and model.nomination.party.partyId in(:partyIds) and model.boothConstituencyElection.booth.partNo in(:partNos) and " +
				" model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId ");
		
		query.setParameterList("electionIds",electionIds);
		query.setParameterList("partyIds",partyIds);
		query.setParameterList("partNos",partNos);
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Object[]> findBoothCountForMultipleBoothsInElections(Long constituencyId,List<String> partNos, List<Long> electionIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.partNo,"+
				" model.votesEarned,model.boothConstituencyElection.booth.totalVoters from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId in(:electionIds)  and model.boothConstituencyElection.booth.partNo in(:partNos) and " +
				" model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId ");
		
		query.setParameterList("electionIds",electionIds);
		query.setParameterList("partNos",partNos);
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	public List<Object[]> getMandalResultsForElectionAndMandals(Long constituencyId,List<Long> tehsilIds, List<Long> electionIds,List<Long> partyIds,List<Long> boothIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.tehsil.tehsilId,model.nomination.party.partyId," +
				" model.nomination.party.shortName,sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType " +
				"  from CandidateBoothResult model where   model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId  and model.boothConstituencyElection.booth.boothId in(:boothIds)" +
				" and model.boothConstituencyElection.constituencyElection.election.electionId  in( :electionIds) and model.nomination.party.partyId in(:partyIds) and " +
				" model.boothConstituencyElection.booth.localBody is null and model.boothConstituencyElection.booth.tehsil.tehsilId in(:tehsilIds) ");
				
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		query.setParameterList("tehsilIds",tehsilIds);
		query.setParameterList("boothIds",boothIds);
		return query.list();
	}
	
	public List<Object[]> getMandalCountForElectionAndMandals(Long constituencyId,List<Long> tehsilIds, List<Long> electionIds,List<Long> boothIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.tehsil.tehsilId," +
				" sum(model.votesEarned) from CandidateBoothResult model where   model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId  and" +
				
				" model.boothConstituencyElection.constituencyElection.election.electionId  in( :electionIds) and " +
				" model.boothConstituencyElection.booth.localBody is null and model.boothConstituencyElection.booth.tehsil.tehsilId in(:tehsilIds) and model.boothConstituencyElection.booth.boothId in(:boothIds) " +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.tehsil.tehsilId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("tehsilIds",tehsilIds);
		query.setParameterList("boothIds",boothIds);
		return query.list();
	}
	
	public List<Object[]> getLocalbodyResultsForElectionAndByIds(Long constituencyId,List<Long> lclBodyIds, List<Long> electionIds,List<Long> partyIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.localBody.localElectionBodyId,model.nomination.party.partyId," +
				" model.nomination.party.shortName, sum(model.votesEarned),model.boothConstituencyElection.constituencyElection.election.electionYear,model.boothConstituencyElection.constituencyElection.election.electionScope.electionType.electionType " +
				"  from CandidateBoothResult model where   model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId  and " +
				"   model.boothConstituencyElection.booth.localBody.localElectionBodyId in(:lclBodyIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) and model.nomination.party.partyId in( :partyIds)" +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId, model.boothConstituencyElection.booth.localBody.localElectionBodyId, " +
				" model.nomination.party.partyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("partyIds", partyIds);
		query.setParameterList("lclBodyIds", lclBodyIds);
		return query.list();
	}
	
	public List<Object[]> getLocalbodyCountForElectionAndByIds(Long constituencyId,List<Long> lclBodyIds, List<Long> electionIds){
		Query query = getSession().createQuery("select model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.localBody.localElectionBodyId," +
				" sum(model.votesEarned)  from CandidateBoothResult model where   model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId  and " +
				"   model.boothConstituencyElection.booth.localBody.localElectionBodyId in(:lclBodyIds) and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) " +
				" group by model.boothConstituencyElection.constituencyElection.election.electionId, model.boothConstituencyElection.booth.localBody.localElectionBodyId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		query.setParameterList("lclBodyIds", lclBodyIds);
		return query.list();
	}
	
	public List<Object[]> getLocalbodyBoothIdsForElectionAndByIds(Long constituencyId,List<Long> lclBodyIds, List<Long> electionIds,String type,List<String> partNos){
		StringBuilder queryString = new StringBuilder();
		if("booth".equalsIgnoreCase(type)){
			queryString.append("select distinct model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.partNo,model.boothConstituencyElection.booth.boothId ");
		}else
		queryString.append("select distinct model.boothConstituencyElection.constituencyElection.election.electionId,model.boothConstituencyElection.booth.localBody.localElectionBodyId,model.boothConstituencyElection.booth.boothId" );
				queryString.append("   from CandidateBoothResult model where   model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId  and " +
				" model.boothConstituencyElection.constituencyElection.election.electionId in( :electionIds) ");
		if(!"booth".equalsIgnoreCase(type)){
			queryString.append(" and model.boothConstituencyElection.booth.localBody.localElectionBodyId in(:lclBodyIds) ");
		}else{
			queryString.append(" and model.boothConstituencyElection.booth.partNo in(:partNos) ");
		}
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("electionIds", electionIds);
		if(!"booth".equalsIgnoreCase(type))
		  query.setParameterList("lclBodyIds", lclBodyIds);
		else
		  query.setParameterList("partNos", partNos);
		return query.list();
	}
	public List<Object[]> getPartiesForElection(Long electionId,Long constituencyId)
	{
		
		Query query = getSession().createQuery("select distinct model.nomination.party.partyId, model.nomination.party.shortName,model.boothConstituencyElection.constituencyElection.election.electionId from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId order by model.nomination.party.shortName");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findBoothResultsForBoothsAndElectionAndParties(List<Long> boothslist, Long electionId,List<Long> partIds){
		Query query = getSession().createQuery("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist) and model.nomination.party.partyId in(:partIds) group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
		
		query.setParameter(0,electionId);
		query.setParameterList("boothslist",boothslist);
		query.setParameterList("partIds",partIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findBoothResultsForBoothsAndElectionAndAllParties(List<Long> boothslist, Long electionId,List<Long> partIds){
		Query query = getSession().createQuery("select model.nomination.party.partyId, model.nomination.party.shortName,"+
				"sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId = ? " +
				" and model.boothConstituencyElection.booth.boothId in(:boothslist)  group by model.nomination.party.partyId order by sum(model.votesEarned) desc");
		
		query.setParameter(0,electionId);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
	
	
	public List<Object[]> getVotesEarnedForSelectedbooth(Long constituencyId, Long electionId,Long boothId){
		Query query = getSession().createQuery("select sum(model.votesEarned) ,model.nomination.party.partyId "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.boothId =:boothId and " +
				" model.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId group by model.nomination.party.partyId order by sum(model.votesEarned) desc ");
		
		query.setParameter("electionId",electionId);
		query.setParameter("boothId",boothId);
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
 public List<Object[]> findBoothResultsForMultipleBoothsAndElectionId(Set<Long> boothslist, Long electionId,Long constituencyId){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothId,model.nomination.party.partyId, sum(model.votesEarned) "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId  " +
				"  and model.boothConstituencyElection.booth.boothId in(:boothslist) and model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId " +
				"  group by model.boothConstituencyElection.booth.boothId, model.nomination.party.partyId order by sum(model.votesEarned) desc ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
   
   public List<Object[]> getVotesEarnedByBoothIdsList( Long electionId,List<Long> boothIdsList){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothId, model.nomination.party.partyId, sum(model.votesEarned)  "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.boothId in(:boothIdsList)  " +
				"  group by model.boothConstituencyElection.booth.boothId,model.nomination.party.partyId order by sum(model.votesEarned) desc ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothIdsList",boothIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getVotesEarnedByParyInEachBooth( Long electionId,List<Long> boothIds){
		Query query = getSession().createQuery("select sum(model.votesEarned) ,model.nomination.party.partyId ,model.boothConstituencyElection.booth.boothId "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.boothId in (:boothIds)  " +
				"  group by model.nomination.party.partyId order by sum(model.votesEarned) desc ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothIds",boothIds);
		
		return query.list();
	}
	
	
   public List<Object[]> getPolledVotesByBoothIdsList(Long electionId,List<Long> boothIdsList)
   {
	   Query query = getSession().createQuery(" select model.boothConstituencyElection.booth.boothId, sum(model.boothConstituencyElection.boothResult.validVotes) from CandidateBoothResult model " +
	   		" where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and model.boothConstituencyElection.booth.boothId in (:boothIdsList)  " +
	   		"  group by model.boothConstituencyElection.booth.boothId ");
	   
	   
	   query.setParameter("electionId", electionId);
	   query.setParameterList("boothIdsList", boothIdsList);
	   return query.list();
   }
   
   public List<Object[]> getValidVotesForMultipleBooths(List<Long> boothslist, Long electionId){
		Query query = getSession().createQuery("select model.booth.boothId,model.boothResult.validVotes "+
				" from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId  " +
				"  and model.booth.boothId in(:boothslist)  " +
				"  group by model.booth.boothId ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		
		return query.list();
	}
   
   public Long getTotalValidVotes(List<Long> boothIdsList,Long electionId,Long constituencyId)
   {
	   Query query = getSession().createQuery(" select sum(model.boothConstituencyElection.boothResult.validVotes) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId  " +
	   		" and model.boothConstituencyElection.booth.boothId in(:boothslist) and model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId ");
	   query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothIdsList);
		query.setParameter("constituencyId", constituencyId);
	   return (Long) query.uniqueResult();
   }
   
   public List<Object[]> findBoothResultsForMultipleBoothsAndElectionIdForSelElection(Set<Long> boothslist, Long electionId){
		Query query = getSession().createQuery("select model.boothConstituencyElection.booth.boothId,model.nomination.party.partyId, sum(model.votesEarned) "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId  " +
				"  and model.boothConstituencyElection.booth.boothId in(:boothslist)  " +
				"  group by model.boothConstituencyElection.booth.boothId, model.nomination.party.partyId order by sum(model.votesEarned) desc ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		return query.list();
	}
   
   public Long findTotalVotesForAssembInAParliament(Set<Long> boothslist, Long electionId){
		Query query = getSession().createQuery("select sum(model.totalVoters) "+
				" from Booth model where model.boothId in(:boothslist)  ");
		
		//query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		return (Long)query.uniqueResult();
	}
   public Long findPolledVotesForAssembInAParliament(Set<Long> boothslist, Long electionId){

		Query query = getSession().createQuery("select  sum(model.votesEarned) "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId  " +
				"  and model.boothConstituencyElection.booth.boothId in(:boothslist)  ");
		
		query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		return (Long)query.uniqueResult();
	}
   public Long findTotalVotesPolledForCandidateAssembInAParliament(Set<Long> boothslist, Long electionId,Long partyId){
		Query query = getSession().createQuery("select  sum(model.votesEarned) "+
				" from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId  " +
				"  and model.boothConstituencyElection.booth.boothId in(:boothslist) and model.nomination.party.partyId =:partyId ");
		
		query.setParameter("partyId",partyId);
		query.setParameter("electionId",electionId);
		query.setParameterList("boothslist",boothslist);
		return (Long)query.uniqueResult();
	}
   public Long getConstituencyTotalVotes(Long constituencyId,Long electionId)
   {
	   Query query = getSession().createQuery(" select sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId " +
	   		" and model.boothConstituencyElection.constituencyElection.election.electionId =:electionId ");
	   
	   query.setParameter("constituencyId", constituencyId);
	   query.setParameter("electionId", electionId);
	   
	   return (Long) query.uniqueResult();
   }
   
   public List<Object[]> getPartyWiseAfterDelimationEffectBasedOnVoters(Long electionId,Long constituencyId )
   {
	   Query query = getSession().createQuery("select model.nomination.party.partyId , " +
	   		" sum(model.votesEarned) ,model.nomination.party.shortName from CandidateBoothResult model" +
	   		" where model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId " +
	   		" and model.boothConstituencyElection.constituencyElection.election.electionId =:electionId " +
	   		"  " +
	   		" group by model.nomination.party.partyId");
		query.setParameter("electionId", electionId);
		query.setParameter("constituencyId", constituencyId);
		//query.setParameterList("partyIds", partyIds);
		return query.list();
   }
   
   public List<Object[]> getPartyWiseBeforDelimationEffectBasedOnVoters(Long electionId ,List<Long> boothIds)
   {
	   Query query = getSession().createQuery("select model.nomination.party.partyId , " +
		   		" sum(model.votesEarned) , model.nomination.party.shortName from CandidateBoothResult model" +
		   		" where  " +
		   		" model.boothConstituencyElection.booth.boothId in (:boothIds) " +
		   		" and model.boothConstituencyElection.constituencyElection.election.electionId =:electionId " +
		   		" " +
		   		" group by model.nomination.party.partyId");
			query.setParameter("electionId", electionId);
			//query.setParameterList("partyIds", partyIds);
			query.setParameterList("boothIds", boothIds);
			return query.list();
   }
   
 /*  public List<Object[]> findboothWiseResultsForCandidate(Long constituencyId, Long nominationId)
   {
	   
	   Query query = getSession().createQuery("select model.boothConstituencyElection.booth, model.boothConstituencyElection.boothResult.validVotes, " +
	   		" model.votesEarned from CandidateBoothResult model" +
		   		"  where  " +
		   		"  model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId  " +
		   		"  and model.nomination.nominationId = :nominationId  ");
	   
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("nominationId", nominationId);
			
			return query.list();
			
   }*/
   
   public List<Object[]> findboothWiseResultsForCandidate(Long constituencyId, Long nominationId)
   {
	    
	   Query query = getSession().createQuery("select " +
		   		" model.boothConstituencyElection.booth.boothId, " +  //0
		   		" model.boothConstituencyElection.booth.partNo, " +//1
		   		" model.boothConstituencyElection.booth.location, " + //2
		   		" model.boothConstituencyElection.booth.totalVoters, " + //3
		   		" model.boothConstituencyElection.boothResult.validVotes, " + //4
				" model.boothConstituencyElection.booth.villagesCovered, " + //5
		   		" model.boothConstituencyElection.booth.localBody.localElectionBodyId, " + //6
		   		" model.boothConstituencyElection.booth.tehsil, " + //7
		   		" model.votesEarned " + //8
		   		//" model.boothConstituencyElection.booth.boothLocalBodyWard " + //9
		   		" from CandidateBoothResult model " + 
			   		"  where  " +
			   		"  model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId  " +
			   		"  and model.nomination.nominationId = :nominationId  order by model.boothConstituencyElection.booth.partNo ");
	   
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("nominationId", nominationId);
			
			return query.list();
			
   }
   
   public List<Object[]> findboothWiseResultsForNominators(Long constituencyId,List<Long> electionIds)
   {
	    
	   Query query = getSession().createQuery("select B.boothId,B.partNo,N.party.shortName,CBR.votesEarned  " + //8
		   		" from CandidateBoothResult CBR, Nomination N, ConstituencyElection CE, BoothConstituencyElection BCE , Booth B " + 
			   		"  where  " +
			   		" CBR.nomination.nominationId = N.nominationId and " +
			   		" N.constituencyElection.constiElecId = CE.constiElecId and " +
			   		" BCE.boothConstituencyElectionId = CBR.boothConstituencyElection.boothConstituencyElectionId and  " +
			   		" BCE.booth.boothId = B.boothId and " +
			   		" CE.election.electionId in (:electionIds) and " +
			   		" CE.constituency.constituencyId = :constituencyId order by B.boothId,CBR.votesEarned desc ");
	   
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("electionIds", electionIds);
			
			return query.list();
			
   }
   
   public List<Object[]> findboothWiseResultsForNonParties(Long constituencyId, List<Long> partyIds,List<Long> electionIds)
   {
	    
	   Query query = getSession().createQuery("select " +
		   		" model.boothConstituencyElection.booth.boothId, " +  //0
		   		" model.boothConstituencyElection.booth.partNo, " +//1
		   		" model.boothConstituencyElection.booth.totalVoters, " + //2
		   		" model.boothConstituencyElection.boothResult.validVotes, " + //3
		   		" model.votesEarned " + //4
		   		" from CandidateBoothResult model " + 
			   		"  where  " +
			   		"  model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId  " +
			   		"  and model.nomination.party.partyId not in (:partyIds) " +
			   		"  and model.boothConstituencyElection.constituencyElection.election.electionId in (:electionIds) " +
			   		"  order by model.boothConstituencyElection.booth.partNo ");
	   
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("partyIds", partyIds);
			query.setParameterList("electionIds", electionIds);
			return query.list();
			
   }
   

   public Long getTotalAndEarnedVotesForLocation(Long locationId,String locationtype,Long electionId,Long constituencyId,List<Long> partyIds,List<Long> constituencyIds)
   {
	  StringBuilder str = new StringBuilder();
	  str.append(" select sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId ");
	  str.append(" and model.nomination.party.partyId in (:partyIds)  and ");
	  
	  
	  if(locationtype.equalsIgnoreCase("Constituency"))
			 str.append("  model.boothConstituencyElection.booth.constituency.constituencyId =:locationId");
			
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 258l)//2014 Election
			 str.append("  model.boothConstituencyElection.booth.panchayat.tehsil.tehsilId =:locationId and model.boothConstituencyElection.booth.localBody is null ");
	  
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 38l)//2009 Election(panchayat Id is not mapped in Booth Table)
			 str.append("  model.boothConstituencyElection.booth.tehsil.tehsilId =:locationId and model.boothConstituencyElection.booth.localBody is null ");
			
	  else if(locationtype.equalsIgnoreCase("Panchayat"))
			 str.append("  model.boothConstituencyElection.booth.panchayat.panchayatId =:locationId");
			
	  else if(locationtype.equalsIgnoreCase("Booth"))
			 str.append(" model.boothConstituencyElection.booth.boothId =:locationId ");
		   
	  else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append("model.boothConstituencyElection.booth.localBody.localElectionBodyId =:locationId and model.boothConstituencyElection.booth.localBody is not null ");
	  
	  else if(locationtype.equalsIgnoreCase("District"))
	         str.append(" model.boothConstituencyElection.booth.constituency.district.districtId =:locationId ");
	  
	  else if(locationtype.equalsIgnoreCase("Parliament"))
		  str.append("  model.boothConstituencyElection.booth.constituency.constituencyId in (:constituencyIds) ");
	  
	  if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
		   str.append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = :constituencyId  ");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	  if(!locationtype.equalsIgnoreCase("Parliament"))
	    query.setParameter("locationId", locationId);
	  
	  query.setParameter("electionId", electionId);
	  
	  if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
	    query.setParameter("constituencyId", constituencyId);
	  
	  
	  if(locationtype.equalsIgnoreCase("Parliament"))
		 query.setParameterList("constituencyIds", constituencyIds);
	  
	  
	  query.setParameterList("partyIds", partyIds);
	  return (Long) query.uniqueResult();
   }
   
   public List<Object[]> getTotalEarnedVotesForLocation(List<Long> locationIdsList,String locationtype,Long electionId,Long constituencyId,List<Long> partyIds,List<Long> constituencyIds)
   {
	  StringBuilder str = new StringBuilder();
	  str.append(" select "); 
	  if(locationtype.equalsIgnoreCase("Constituency"))
			 str.append("   model.boothConstituencyElection.booth.constituency.constituencyId , ");
			
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 258l)//2014 Election
			 str.append("   model.boothConstituencyElection.booth.panchayat.tehsil.tehsilId , ");
	  
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 38l)//2009 Election(panchayat Id is not mapped in Booth Table)
			 str.append("    model.boothConstituencyElection.booth.tehsil.tehsilId , ");
			
	  else if(locationtype.equalsIgnoreCase("Panchayat"))
			 str.append("   model.boothConstituencyElection.booth.panchayat.panchayatId , ");
			
	  else if(locationtype.equalsIgnoreCase("Booth"))
			 str.append("   model.boothConstituencyElection.booth.boothId ,");
		   
	  else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append("  model.boothConstituencyElection.booth.localBody.localElectionBodyId ,");
	  
	  else if(locationtype.equalsIgnoreCase("District"))
	         str.append("  model.boothConstituencyElection.booth.constituency.district.districtId ,  ");
	  
	  str.append(" sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId ");
	  str.append(" and model.nomination.party.partyId in (:partyIds)  and ");
	  
	  
	  if(locationtype.equalsIgnoreCase("Constituency"))
			 str.append("  model.boothConstituencyElection.booth.constituency.constituencyId in (:locationIdsList) ");
			
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 258l)//2014 Election
			 str.append("  model.boothConstituencyElection.booth.panchayat.tehsil.tehsilId in (:locationIdsList)  and model.boothConstituencyElection.booth.localBody is null ");
	  
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 38l)//2009 Election(panchayat Id is not mapped in Booth Table)
			 str.append("  model.boothConstituencyElection.booth.tehsil.tehsilId in (:locationIdsList)  and model.boothConstituencyElection.booth.localBody is null ");
			
	  else if(locationtype.equalsIgnoreCase("Panchayat"))
			 str.append("  model.boothConstituencyElection.booth.panchayat.panchayatId in (:locationIdsList)  ");
			
	  else if(locationtype.equalsIgnoreCase("Booth"))
			 str.append(" model.boothConstituencyElection.booth.boothId in (:locationIdsList)  ");
		   
	  else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append("model.boothConstituencyElection.booth.localBody.localElectionBodyId in (:locationIdsList)  and model.boothConstituencyElection.booth.localBody is not null ");
	  
	  else if(locationtype.equalsIgnoreCase("District"))
	         str.append(" model.boothConstituencyElection.booth.constituency.district.districtId in (:locationIdsList)  ");
	  
	 // else if(locationtype.equalsIgnoreCase("Parliament"))
		//  str.append("  model.boothConstituencyElection.booth.constituency.constituencyId in (:constituencyIds) ");
	  
	 // if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
	//	   str.append(" and model.boothConstituencyElection.constituencyElection.constituency.constituencyId = :constituencyId  ");
	  
	  if(locationtype.equalsIgnoreCase("Constituency"))
			 str.append("   group by model.boothConstituencyElection.booth.constituency.constituencyId ");
			
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 258l)//2014 Election
			 str.append("   group by model.boothConstituencyElection.booth.panchayat.tehsil.tehsilId ");
	  
	  else if(locationtype.equalsIgnoreCase("Mandal") && electionId.longValue() == 38l)//2009 Election(panchayat Id is not mapped in Booth Table)
			 str.append("   group by model.boothConstituencyElection.booth.tehsil.tehsilId  ");
			
	  else if(locationtype.equalsIgnoreCase("Panchayat"))
			 str.append("  group by  model.boothConstituencyElection.booth.panchayat.panchayatId  ");
			
	  else if(locationtype.equalsIgnoreCase("Booth"))
			 str.append("  group by  model.boothConstituencyElection.booth.boothId ");
		   
	  else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append("  group by  model.boothConstituencyElection.booth.localBody.localElectionBodyId ");
	  
	  else if(locationtype.equalsIgnoreCase("District"))
	         str.append("  group by model.boothConstituencyElection.booth.constituency.district.districtId   ");
	   
	  Query query = getSession().createQuery(str.toString());
	  
	  if(!locationtype.equalsIgnoreCase("Parliament"))
	    query.setParameterList("locationIdsList", locationIdsList);
	  
	  query.setParameter("electionId", electionId);
	  
	  //if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
	 //   query.setParameter("constituencyId", constituencyId);
	  
	  
	 // if(locationtype.equalsIgnoreCase("Parliament"))
	//	 query.setParameterList("constituencyIds", constituencyIds);
	  
	  
	  query.setParameterList("partyIds", partyIds);
	  return query.list();
   }
   
   
   public Long getTotalEarnedVotesByBoothIdsList(List<Long> boothIdsList,Long electionId,List<Long> partyIds)
   {
	   Query query = getSession().createQuery(" select sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.constituencyElection.election.electionId =:electionId " +
	   		" and model.boothConstituencyElection.booth.boothId in (:boothIdsList) and model.nomination.party.partyId in (:partyIds) ");
	   
	   query.setParameter("electionId", electionId);
	   query.setParameterList("boothIdsList", boothIdsList);
	   query.setParameterList("partyIds", partyIds);
	   return (Long) query.uniqueResult();
   }

@Override
public List<Object[]> findLocationWiseBoothWisePollingPercentage(List<Long> partyIds, Long locationTypeId, Long locationValue,List<Long> electionYears,String level,
		Long electionScopeId) {

	StringBuilder sb = new StringBuilder();
	
	sb.append("select b.booth_id as boothId, b.part_no as partno, p.short_name as partyName, cn.votes_earned as votesEarned" +
			" from  candidate_booth_result cn, nomination n, party p, constituency_election ce," +
			" booth_constituency_election bc, booth b, constituency c, election e ");
	
	if(level.equalsIgnoreCase(level) && level == null){
		sb.append(" where n.party_id=p.party_id and cn.nomination_id=n.nomination_id and" +
				" n.consti_elec_id=ce.consti_elec_id and" +
				" bc.booth_constituency_election_id=cn.booth_constituency_election_id and" +
				" bc.booth_id=b.booth_id and" +
				"  c.constituency_id=ce.constituency_id and" +
				"  e.election_id=constituen2_.election_id ");
		if(electionScopeId!=null && electionScopeId.longValue()>0){
			
			sb.append(" and e.election_scope_id=:electionScopeId" );
		}
		if(locationTypeId == 5l)
		sb.append(" and b.tehsil_id =:locationValue");
		else if(locationTypeId == 6l)
			sb.append(" and b.panchayat_id =:locationValue");
		else if(locationTypeId == 7l)
			sb.append(" and b.local_election_body_id =:locationValue");

	}else{
		sb.append(" where n.party_id=p.party_id and cn.nomination_id=n.nomination_id and" +
				" n.consti_elec_id=ce.consti_elec_id and" +
				" bc.booth_constituency_election_id=cn.booth_constituency_election_id and" +
				" bc.booth_id=b.booth_id and" +
				"  c.constituency_id=ce.constituency_id and" +
				"  e.election_id=constituen2_.election_id ");
		if(locationTypeId !=null && locationValue !=null){
			sb.append("and b.constituency_id=:locationValue ");

		}if(electionScopeId!=null && electionScopeId.longValue()>0){
			
			sb.append(" and e.election_scope_id=:electionScopeId" );
		}
	}
	sb.append(" order by b.booth_id, candidateb0_.votes_earned desc");
	return null;
}
public List<Object[]> LocatioWisefindboothWiseResultsForCandidate(Long constituencyId, Long nominationId,Long locationTypeId)
{
	  
	StringBuilder sb = new StringBuilder();
	sb.append("select " +
	   		" model.boothConstituencyElection.booth.boothId, " +  //0
	   		" model.boothConstituencyElection.booth.partNo, " +//1
	   		" model.boothConstituencyElection.booth.location, " + //2
	   		" model.boothConstituencyElection.booth.totalVoters, " + //3
	   		" model.boothConstituencyElection.boothResult.validVotes, " + //4
			" model.boothConstituencyElection.booth.villagesCovered, " + //5
	   		" model.boothConstituencyElection.booth.localBody.localElectionBodyId, " + //6
	   		" model.boothConstituencyElection.booth.tehsil, " + //7
	   		" model.votesEarned " + //8
	   		//" model.boothConstituencyElection.booth.boothLocalBodyWard " + //9
	   		" from CandidateBoothResult model " + 
		   		"  where model.nomination.nominationId = :nominationId ");
	
	if(locationTypeId != null && locationTypeId.longValue()>0){
		if(locationTypeId == 2l ||locationTypeId == 3l || locationTypeId == 4l){
			
			sb.append(" and model.boothConstituencyElection.booth.constituency.constituencyId =:constituencyId");
		}else if(locationTypeId==5l){
			sb.append(" and model.boothConstituencyElection.booth.tehsil.tehsilId =:constituencyId");

		}else if(locationTypeId==6l){
			sb.append(" and model.boothConstituencyElection.booth.panchayat.panchayatId =:constituencyId");

		}else if(locationTypeId==7l){
			sb.append(" and model.boothConstituencyElection.booth.localBody.localElectionBodyId =:constituencyId");

		}
		
	}
	sb.append(" order by model.boothConstituencyElection.booth.partNo ");
	   Query query = getSession().createQuery(sb.toString());
	   
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("nominationId", nominationId);
			
			return query.list();
			
}

@Override
public List<Object[]> locationWisefindboothWiseResultsForNominators(Long constituencyId, Long electionYears,Long locationTypeId) {
	Query query = null;
	if(locationTypeId !=null && locationTypeId != 0 && locationTypeId <5l ){
		query = getSession().createQuery("select B.boothId,B.partNo,N.party.shortName,CBR.votesEarned  " + //8
	   		" from CandidateBoothResult CBR, Nomination N, ConstituencyElection CE, BoothConstituencyElection BCE , Booth B " + 
		   		"  where  " +
		   		" CBR.nomination.nominationId = N.nominationId and " +
		   		" N.constituencyElection.constiElecId = CE.constiElecId and " +
		   		" BCE.boothConstituencyElectionId = CBR.boothConstituencyElection.boothConstituencyElectionId and  " +
		   		" BCE.booth.boothId = B.boothId and " +
		   		" CE.election.electionYear =:electionYears and " +
		   		" CE.constituency.constituencyId = :constituencyId order by B.boothId,CBR.votesEarned desc ");
		query.setParameter("constituencyId", constituencyId);
	}else{
		StringBuilder sb = new StringBuilder();
		sb.append("select B.boothId,B.partNo,N.party.shortName,CBR.votesEarned  " + //8
		   		" from CandidateBoothResult CBR, Nomination N, ConstituencyElection CE, BoothConstituencyElection BCE , Booth B " + 
		   		"  where  " +
		   		" CBR.nomination.nominationId = N.nominationId and " +
		   		" N.constituencyElection.constiElecId = CE.constiElecId and " +
		   		" BCE.boothConstituencyElectionId = CBR.boothConstituencyElection.boothConstituencyElectionId and  " +
		   		" BCE.booth.boothId = B.boothId and " +
		   		" CE.election.electionYear =:electionYears and ");
		if(locationTypeId ==  5l){
			sb.append(" B.tehsil.tehsilId = :locationValue order by B.boothId,CBR.votesEarned desc ");
		}else if(locationTypeId == 6l){
			sb.append(" B.panchayat.panchayatId = :locationValue order by B.boothId,CBR.votesEarned desc ");
		}else if(locationTypeId == 7l){
			sb.append(" B.localBody.localElectionBodyId = :locationValue order by B.boothId,CBR.votesEarned desc ");
		}
		
		query = getSession().createQuery(sb.toString());
		query.setParameter("locationValue", constituencyId);
			   		
	}
		
		query.setParameter("electionYears", String.valueOf(electionYears));
		
		return query.list();
}

   
}
