package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
				" and model.boothConstituencyElection.constituencyElection.election.electionYear = ?",params);
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
				" model.nomination.nominationId",params);
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
				" model.nomination.nominationId",params);
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
				" model.nomination.nominationId",params);
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
	
}
