package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;

public class BoothConstituencyElectionDAO extends GenericDaoHibernate<BoothConstituencyElection, Long> implements IBoothConstituencyElectionDAO{

	public BoothConstituencyElectionDAO(){
		super(BoothConstituencyElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findBoothsByConstituencyElection(Long constiElecId) {
		return getHibernateTemplate().find("select model.booth from BoothConstituencyElection model where model.constituencyElection.constiElecId = ?", constiElecId);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByElectionConstituencyAndTehsil(String electionYear, Long tehsilId, Long constituencyId){
		Object[] params = {tehsilId, electionYear, constituencyId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.tehsil.tehsilId = ? " +
				"and model.constituencyElection.election.electionYear = ? " +
				"and model.constituencyElection.constituency.constituencyId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByElectionConstituencyAndBooth(Long boothId, String electionYear, Long constituencyId) {
		Object[] params = {boothId, electionYear, constituencyId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.boothId = ? and " +
				"model.constituencyElection.election.electionYear = ? and " +
				"model.constituencyElection.constituency.constituencyId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyElectionTehsilAndPartNo(Long constituencyElectionId, Long tehsilId, String partNo) {
		Object[] params = {partNo, tehsilId, constituencyElectionId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.partNo=? and model.booth.tehsil.tehsilId = ? and model.constituencyElection.constiElecId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByBoothAndConstiuencyElection(String partNo, Long constiElecId) {
		Object[] params = {partNo, constiElecId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.partNo=? and model.constituencyElection.constiElecId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScopeId) {
		Object[] params = {constituencyName, electionScopeId, electionYear};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.constituencyElection.constituency.name = ? and model.constituencyElection.constituency.electionScope.electionScopeId = ? and model.constituencyElection.election.electionYear = ?", params);
	}
	
	public List getAllElectionBoothVotersForMandal(Long tehsilID){
		StringBuilder query = new StringBuilder();
		query.append("Select constituencyElection.election, ");
		query.append(" sum(model.booth.totalVoters) , sum(model.boothResult.validVotes), ");
		query.append(" sum(model.boothResult.rejectedVotes), sum(model.boothResult.tenderedVotes)");
		query.append(" from BoothConstituencyElection model ");
		query.append(" where model.booth.tehsil.tehsilId = ").append(tehsilID);
		query.append(" group by model.constituencyElection.election");
		query.append(" order by model.constituencyElection.election");
		return getHibernateTemplate().find(query.toString() );
	}
	
	public List getPartyVotesByMandal(Long tehsilID, String partyIDs, Long electionID){
		StringBuilder query = new StringBuilder();
		query.append("Select model.nomination.candidate.firstname,");
		query.append(" model.nomination.candidate.middlename,"); 
		query.append(" model.nomination.candidate.lastname,");		 
		query.append(" model.boothConstituencyElection.constituencyElection.election,");
		query.append(" sum(model.votesEarned), model.nomination.party.partyId, model.nomination.party.shortName"); 
		query.append(" from CandidateBoothResult model"); 
		query.append(" where model.boothConstituencyElection.booth.tehsil.tehsilId =").append(tehsilID);    
		query.append(" and model.boothConstituencyElection.constituencyElection.election.electionId=").append(electionID);
		query.append(" and model.nomination.party.partyId in (").append(partyIDs);
		query.append(") group by model.nomination.party.partyId");
		return getHibernateTemplate().find(query.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstituencyByElectionYearAndElectionScope(String electionYear, Long electionScopeId){
		Object[] params = {electionScopeId, electionYear};
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.electionScope.electionScopeId = ? " +
				"and model.constituencyElection.election.electionYear = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<String> findElectionYearsForBoothData(){
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionYear from " +
				"BoothConstituencyElection model");
	}
	


	public List getStatesByCountryFromBooth(Long countryID) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency.state.stateId, " +
				"model.constituencyElection.constituency.state.stateName from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.state.country.countryId=? " +
				"order by model.constituencyElection.constituency.state.stateName",countryID);
	}

	public List getDistrictsByStateIDFromBooth(Long stateID) {
		return getHibernateTemplate().find("select distinct model.booth.tehsil.district.districtId, " +
				"model.booth.tehsil.district.districtName from BoothConstituencyElection model " +
				"where model.booth.tehsil.district.state.stateId=? order by model.booth.tehsil.district.districtName",stateID);
	}

	public List getConstituenciesByDistrictIDFromBooth(Long districtID) {
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency.constituencyId, " +
				"model.constituencyElection.constituency.name from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.district.districtId=? " +
				"order by model.constituencyElection.constituency.name",districtID);
	}

	public List getMandalsByConstituencyIDFromBooth(Long constituencyID) {
		return getHibernateTemplate().find("select distinct model.booth.tehsil.tehsilId, model.booth.tehsil.tehsilName from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.constituencyId=? order by model.booth.tehsil.tehsilName",constituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyIdAndElectionYear(Long constituencyId, String electionYear){
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.constituencyId= ? and model.constituencyElection.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByElectionElectionTypeConstituencyAndPartNo(
			Long stateId, Long districtId, String constituencyName,
			Long electionTypeId, String electionYear, String partNo) {
		Object[] params ={constituencyName, stateId, districtId, electionTypeId, electionYear, partNo}; 
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.constituencyElection.constituency.name = ? " +
				"and model.constituencyElection.constituency.state.stateId = ?" +
				"and model.constituencyElection.constituency.district.districtId = ?" +
				"and model.constituencyElection.constituency.electionScope.electionType.electionTypeId = ?" +
				"and model.constituencyElection.election.electionYear = ?" +
				"and model.booth.partNo = ?",params);
	}

	public List findPartNoConstituencyNameForTehsil(Long tehsilId, String electionType, String electionYear) {
		Object[] params = {tehsilId, electionYear, electionType};
		return getHibernateTemplate().find("select model.boothConstituencyElectionId, model.constituencyElection.constituency.name," +
				" model.booth.villagesCovered, model.booth.partNo from BoothConstituencyElection model where model.booth.tehsil.tehsilId = ? and" +
				" model.constituencyElection.election.electionYear = ? and model.constituencyElection.election.electionScope.electionType.electionType = ? " +
				" group by model.booth.partNo",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findVoterInformationByMandalIdsAndDelimitationYear (String mandalsIds,String year, Long constituencyId)
	{
		Object[] params = {year, constituencyId};
		return getHibernateTemplate().find("select model.booth.tehsil.tehsilId," +
				"model.booth.tehsil.tehsilName,sum(model.booth.maleVoters),sum(model.booth.femaleVoters),sum(model.booth.totalVoters) from BoothConstituencyElection model " +
				"where model.booth.tehsil.tehsilId in (" +mandalsIds+") and model.constituencyElection.election.electionYear = ? and model.constituencyElection.constituency.constituencyId =? " +
						"group by model.booth.tehsil.tehsilId order by model.booth.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseMaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue){
		Object[] params = {electionId,tehsilId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters,")
		        .append("model.constituencyElection.constituency.constituencyId,")
		        .append("model.constituencyElection.constituency.name")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.booth.tehsil.tehsilId = ? and model.booth.femaleVoters <= ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue){
		Object[] params = {electionId,tehsilId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters,")
		        .append("model.constituencyElection.constituency.constituencyId,")
		        .append("model.constituencyElection.constituency.name")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.booth.tehsil.tehsilId = ? and model.booth.maleVoters <= ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long maleTrend,Long femaleTrend){
		Object[] params = {electionId,tehsilId,maleTrend,femaleTrend};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters,")
		        .append("model.constituencyElection.constituency.constituencyId,")
		        .append("model.constituencyElection.constituency.name")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.booth.tehsil.tehsilId = ? and model.booth.maleVoters > ? and model.booth.femaleVoters > ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseAllBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId){
		Object[] params = {electionId,tehsilId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters,")
		        .append("model.constituencyElection.constituency.constituencyId,")
		        .append("model.constituencyElection.constituency.name")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.booth.tehsil.tehsilId = ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseAllBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId){
		System.out.println("Inside 1 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.constituencyElection.constituency.constituencyId = ?")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long trendValue){
		System.out.println("Inside 2 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.constituencyElection.constituency.constituencyId = ? and model.booth.femaleVoters <= ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseFemaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long trendValue){
		System.out.println("Inside 3 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,trendValue};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.constituencyElection.constituency.constituencyId = ? and model.booth.maleVoters <= ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
		
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long maleTrend,Long femaleTrend){
		System.out.println("Inside 4 (ElecId) :" + electionId + "ConstitID :" + constituencyId);
		Object[] params = {electionId,constituencyId,maleTrend,femaleTrend};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.boothId,model.booth.partNo,")
		        .append("model.booth.location,model.booth.villagesCovered,")
		        .append("model.booth.maleVoters,model.booth.femaleVoters,model.booth.totalVoters")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.election.electionId = ?")
		        .append(" and model.constituencyElection.constituency.constituencyId = ? and model.booth.maleVoters > ? and model.booth.femaleVoters > ?")
		        .append(" group by model.booth.boothId,model.constituencyElection.constituency.constituencyId")
		        .append(" order by model.constituencyElection.constituency.constituencyId");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findMandalDetailsForAParticularElectionYearForAParticularConstituency(Long constituencyId,Long electionId){
		Object[] params = {constituencyId,electionId};
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.booth.tehsil.tehsilId,model.booth.tehsil.tehsilName")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId = ?")
		        .append(" and model.constituencyElection.election.electionId = ?");
		return getHibernateTemplate().find(hqlQuery.toString(), params);
	}
	
	@SuppressWarnings("unchecked")
	public List findAllElectionsForAConstituency(Long constituencyId){
		StringBuilder hqlQuery =new StringBuilder();
		hqlQuery.append("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear")
		        .append(" from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId = ?");
		return getHibernateTemplate().find(hqlQuery.toString(), constituencyId);
		
	}
	@SuppressWarnings("unchecked")
	public List findBoothwiseResultsConstituency(Long constituencyId)
	{
		return getHibernateTemplate().find("select max(model.constituencyElection.election.electionYear),model.constituencyElection.election.electionId,model.constituencyElection.election.electionScope.electionType.electionTypeId,model.constituencyElection.election.electionScope.electionType.electionType from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId= ?" , constituencyId);
		
	}
	
	@SuppressWarnings("unchecked")
    public List getPreviousYearElectionDetails(Long electionId, Long constituencyId)
	{   Object[] params = {electionId,constituencyId};
		return  getHibernateTemplate().find("select max(Model.constituencyElection.election.electionYear),Model.constituencyElection.election.electionId from BoothConstituencyElection Model where Model.constituencyElection.election.electionId != ? and Model.constituencyElection.constituency.constituencyId= ?", params);
	}
}
