package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothConstituencyElectionDAO extends GenericDaoHibernate<BoothConstituencyElection, Long> implements IBoothConstituencyElectionDAO{

	public BoothConstituencyElectionDAO(){
		super(BoothConstituencyElection.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findBoothsByConstituencyElection(Long constiElecId) {
		return getHibernateTemplate().find("select model.booth from BoothConstituencyElection model where model.constituencyElection.constiElecId = ?", constiElecId);
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
	public List<BoothConstituencyElection> findByBoothIdAndConstiuencyElection(Long boothId, Long constiElecId) {
		Object[] params = {boothId, constiElecId};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.boothId=? and model.constituencyElection.constiElecId = ?", params);
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
	
	public List getPartyVotesByMandalWithRankDetails(Long tehsilID, String partyIDs, Long electionID){
		StringBuilder query = new StringBuilder();
		query.append("Select model.nomination.candidate.firstname,");
		query.append(" model.nomination.candidate.middlename,"); 
		query.append(" model.nomination.candidate.lastname,");		 
		query.append(" model.boothConstituencyElection.constituencyElection.election,");
		query.append(" sum(model.votesEarned), model.nomination.party.partyId, model.nomination.party.shortName,model.nomination.candidateResult.rank "); 
		query.append(" from CandidateBoothResult model"); 
		query.append(" where model.boothConstituencyElection.booth.tehsil.tehsilId =").append(tehsilID);    
		query.append(" and model.boothConstituencyElection.constituencyElection.election.electionId=").append(electionID);
		query.append(" and model.nomination.party.partyId in (").append(partyIDs);
		query.append(") group by model.nomination.party.partyId,model.nomination.nominationId");
		return getHibernateTemplate().find(query.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstituencyByElectionYearAndElectionScope(String electionYear, Long electionScopeId){
		Object[] params = {electionScopeId, electionYear};
		return getHibernateTemplate().find("select distinct model.constituencyElection.constituency from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.electionScope.electionScopeId = ? " +
				"and model.constituencyElection.election.electionYear = ? order by model.constituencyElection.constituency.name ", params);
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
	public List findByConstituencyIdAndElectionYear(Long constituencyId, Long parliamentId, String electionYear){
		Object[] params = {constituencyId, parliamentId, electionYear};
		return getHibernateTemplate().find("select count(model.boothResult.boothResultId) from BoothConstituencyElection model " +
				"where model.booth.boothId in(select distinct model1.booth.boothId from BoothConstituencyElection model1 where " +
				"model1.constituencyElection.constituency.constituencyId= ? ) and model.constituencyElection.constituency.constituencyId= ? " +
				"and model.constituencyElection.election.electionYear = ?",params);
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
	public List<BoothConstituencyElection> findByConstituencyDistrictAndPartNo(String constituencyName, Long districtId, String partNo, String year){
		Object[] params = {constituencyName, districtId, partNo, year};
		return getHibernateTemplate().find("from BoothConstituencyElection model where model.booth.boothId = (" +
				"select distinct model.booth.boothId from BoothConstituencyElection model1 where model1.constituencyElection.constituency.name = ?" +
				" and model.constituencyElection.constituency.district.districtId = ? and model.booth.partNo = ? and " +
				"model1.constituencyElection.election.electionYear = ?)", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByBoothIds(String boothIds){
		return getHibernateTemplate().find("select distinct model from BoothConstituencyElection model where model.booth.boothId in("+boothIds+")");
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
		return getHibernateTemplate().find("select max(model.constituencyElection.election.electionYear),model.constituencyElection.election.electionId,model.constituencyElection.election.electionScope.electionType.electionTypeId,model.constituencyElection.election.electionScope.electionType.electionType from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId= ?" +
				" group by model.constituencyElection.election.electionId" , constituencyId);
		
	}
	
	@SuppressWarnings("unchecked")
	public List getPreviousElectionYearsDetails(String presentYear,Long constituencyId){
		Object[] params = {presentYear,constituencyId};
		return  getHibernateTemplate().find("select distinct Model.constituencyElection.election.electionYear,Model.constituencyElection.election.electionId,Model.constituencyElection.election.electionScope.electionType.electionTypeId from BoothConstituencyElection Model where Model.constituencyElection.election.electionYear != ? and Model.constituencyElection.constituency.constituencyId= ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getElectionIdForAElectionTypeAndYear(Long elecTypeId,String elecYear){
		Object[] params = {elecTypeId,elecYear};
		return  getHibernateTemplate().find("select distinct Model.constituencyElection.election.electionId from BoothConstituencyElection Model where Model.constituencyElection.election.electionScope.electionType.electionTypeId = ? and Model.constituencyElection.election.electionYear = ?", params);
	}

	
	@SuppressWarnings("unchecked")
	public List getBoothsInRevenueVillageForElection(Long townshipId, Long electionId) {
		Object[] params = {townshipId, electionId};
		return getHibernateTemplate().find("select model.booth.boothId, model.booth.partNo from BoothConstituencyElection model " +
				"where model.villageBoothElection.township.townshipId = ? and model.constituencyElection.election.electionId = ?", params) ;
	}
	
	public List findElectionsHappendInConstituency(Long constituencyId){
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId, model.constituencyElection.election.electionYear,model.constituencyElection.election.electionScope.electionType.electionType from " +
				"BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId = ? order by model.constituencyElection.election.electionYear desc",constituencyId);
	}
	
	public List findTotalBoothWiseValidVotesForConstituencyElection(
			Long constituencyId, String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select sum(model.boothResult.validVotes) from BoothConstituencyElection model " +
				"where model.constituencyElection.constituency.constituencyId = ? and model.constituencyElection.election.electionYear = ?",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List getTotalVotesInAMandal(Long tehsilId,String electionIds){
		return getHibernateTemplate().find(" select sum(model.boothResult.validVotes),model.constituencyElection.election.electionId,model.booth.tehsil.tehsilName from" +
				" BoothConstituencyElection model where model.booth.tehsil.tehsilId = ? " +
				" and model.constituencyElection.election.electionId in("+electionIds+			
				" ) group by model.constituencyElection.election.electionId,model.booth.tehsil.tehsilId",tehsilId);
	}

	@SuppressWarnings("unchecked")
	public List getValidVotesInAnElectionInMandal(Long mandalId,
			String electionType, String elecYear) {
		Object[] params = {mandalId,electionType,elecYear};
		return getHibernateTemplate().find("select sum(model.boothResult.validVotes) from BoothConstituencyElection "+
				"model where model.booth.tehsil.tehsilId = ? and "+
				"model.constituencyElection.election.electionScope.electionType.electionType = ? and "+
				"model.constituencyElection.election.electionYear = ? group by model.booth.tehsil.tehsilId",params);
	}

	@SuppressWarnings("unchecked")
	public List getTotalVotersInAnElectionInMandal(Long mandalId,
			String electionType, String elecYear) {
		Object[] params = {mandalId,electionType,elecYear};
		return getHibernateTemplate().find("select sum(model.booth.totalVoters) from BoothConstituencyElection "+
				"model where model.booth.tehsil.tehsilId = ? and "+
				"model.constituencyElection.election.electionScope.electionType.electionType = ? and "+
				"model.constituencyElection.election.electionYear = ? group by model.booth.tehsil.tehsilId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoothConstituencyElection> findByConstituencyIdPartNosAndYear(Long constituencyId, Long year, String partNos){
		Object[] params = {constituencyId, year};
		return getHibernateTemplate().find("select distinct model from BoothConstituencyElection model where model.booth.constituency.constituencyId = ? " +
				"and model.booth.year = ? and model.booth.partNo in ("+partNos+")",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getElectionYears()
	{
		return getHibernateTemplate().find(" select distinct model.constituencyElection.election.electionYear from BoothConstituencyElection model order by model.constituencyElection.election.electionYear desc");
	}
	
	public Object getElectionIdInMandal(Long tehsilId, String electionType, String year)
	{
		Query query = getSession().createQuery("select distinct model.constituencyElection.election.electionId from BoothConstituencyElection model where model.booth.tehsil.tehsilId = ? and " +
				" model.constituencyElection.election.electionScope.electionType.electionType = ? and model.constituencyElection.election.electionYear = ? ");
		query.setParameter(0,tehsilId);
		query.setParameter(1,electionType);
		query.setParameter(2,year);
		return query.uniqueResult();
	}

	public List<Object[]> getBoothResultsContainStates(){
		
		Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.state.stateId,model.constituencyElection.constituency.state.stateName from BoothConstituencyElection model " +
				" where  model.constituencyElection.election.electionScope.electionType.electionTypeId = 2 order by model.constituencyElection.constituency.state.stateName");
		return query.list();
	}
	
    public List<Object[]> getElectionYears(Long stateId,Long electionType){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from BoothConstituencyElection model " +
				" where  model.constituencyElection.election.electionScope.electionType.electionTypeId = :electionType ");
		if(electionType.longValue() == 2l)
			query.append(" and model.constituencyElection.constituency.state.stateId = :stateId ");
		query.append(" order by model.constituencyElection.election.electionYear desc");
    	Query queryObj  = getSession().createQuery(query.toString());
    	queryObj.setParameter("electionType", electionType);
    	
    	if(electionType.longValue() == 2l)
    	queryObj.setParameter("stateId", stateId);
    	
    	return queryObj.list();	
	}
    
    public List<Object[]> getConstituencies(Long electionId){
    	Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name from BoothConstituencyElection model " +
				" where  model.constituencyElection.election.electionId = :electionId order by model.constituencyElection.constituency.name");
    	query.setParameter("electionId", electionId);
    	return query.list();
   	}
    
    public List<Object[]> getVotersCountInAConstituency(Long electionId,Long constituencyId){
    	Query query = getSession().createQuery("select sum(model.booth.maleVoters),sum(model.booth.femaleVoters),sum(model.booth.totalVoters),count(*) from BoothConstituencyElection model " +
				" where model.constituencyElection.election.electionId = :electionId and model.constituencyElection.constituency.constituencyId = :constituencyId");
    	query.setParameter("electionId", electionId);
    	query.setParameter("constituencyId", constituencyId);
    	return query.list();
   	}
    
    public List<Object[]> getVotersCountInAMandalBooth(Long electionId,Long id,String type,String partNo,Long constituencyId,List<Long> constituencyIds){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append("select sum(model.booth.maleVoters),sum(model.booth.femaleVoters),sum(model.booth.totalVoters),count(*) from BoothConstituencyElection model " +
				" where model.constituencyElection.election.electionId = :electionId ");
    	if(type.equalsIgnoreCase("mandal")){
    		queryStr.append(" and model.booth.tehsil.tehsilId = :id and model.booth.localBody is null and model.booth.constituency.constituencyId in(:constituencyIds)");
    	}else if(type.equalsIgnoreCase("localElec")){
    	    queryStr.append(" and model.booth.localBody.localElectionBodyId = :id ");
    		if(constituencyId != null && constituencyId > 0l)
    		  queryStr.append(" and model.booth.constituency.constituencyId = :constituencyId ");
    	}else if(type.equalsIgnoreCase("booth")){
    		queryStr.append(" and model.booth.tehsil.tehsilId = :id and model.booth.partNo = :partNo ");
    	}else if(type.equalsIgnoreCase("ward")){
    		queryStr.append(" and model.booth.boothId in(select model1.booth.boothId from BoothLocalBodyWard model1 where model1.localBodyWard.constituencyId = :id )");
    	}
    	Query query = getSession().createQuery(queryStr.toString());
    	query.setParameter("electionId", electionId);
    	query.setParameter("id", id);
    	if(type.equalsIgnoreCase("booth"))
    		query.setParameter("partNo", partNo);
    	if(type.equalsIgnoreCase("localElec") && constituencyId != null && constituencyId > 0l)
    	    query.setParameter("constituencyId", constituencyId);
    	if(type.equalsIgnoreCase("mandal")){
    		 query.setParameterList("constituencyIds", constituencyIds);
    	}
    	return query.list();
   	}
    
    @SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByLocalEleBodyId(Long localEleBodyId, Long electionId,Long constituencyId)
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("select distinct model.booth.boothId from BoothConstituencyElection model where model.booth.localBody.localElectionBodyId =:localElectionBodyId  " +
    			" and model.constituencyElection.election.electionId = :electionId and model.booth.constituency.constituencyId = :constituencyId");
    	Query query = getSession().createQuery(stringBuilder.toString());
    	query.setParameter("electionId", electionId);
    	query.setParameter("localElectionBodyId", localEleBodyId);
    	query.setParameter("constituencyId", constituencyId);
    	return query.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByConstituencyId(Long constituencyId, Long electionId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct(model.booth.boothId) from BoothConstituencyElection model ");
		stringBuilder.append(" where model.constituencyElection.election.electionId = :electionId ");
		stringBuilder.append("and model.booth.constituency.constituencyId = :constituencyId ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		
		return query.list();
	}
    
    public List getElectionYearsByMandalId(String type, Long id)
    {
    	StringBuilder str = new StringBuilder();
    	str.append("select distinct model.constituencyElection.election.electionYear from BoothConstituencyElection model where ");
    	if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
    		str.append(" model.constituencyElection.constituency.constituencyId =:id  ");
    	else if(type.equalsIgnoreCase(IConstants.MANDAL))
    		str.append(" model.booth.tehsil.tehsilId =:id and model.booth.localBody is null ");
    	else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
    		str.append(" model.booth.localBody.localElectionBodyId =:id and model.booth.panchayat is null ");
    	str.append(" order by model.constituencyElection.election.electionYear ");
    	Query query = getSession().createQuery(str.toString());
    	
    	query.setParameter("id", id);
    	return query.list();
    	
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyIdAndElectionYearByElectionType(String type,Long id, String year, String electionType)
    {
    	StringBuilder str = new StringBuilder();
    	str.append("select distinct(model.constituencyElection.constituency.constituencyId),model.constituencyElection.election.electionId from  BoothConstituencyElection model ");
    	str.append(" where model.constituencyElection.election.electionYear = :electionYear and model.constituencyElection.election.electionScope.electionType.electionType = :electionType ");
    	
    	if(type.equalsIgnoreCase(IConstants.MANDAL))
       	 str.append(" and model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
       	else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
       		str.append(" and model.booth.localBody.localElectionBodyId = :id and model.booth.panchayat is null "); 
       	Query query = getSession().createQuery(str.toString());
       	query.setParameter("id", id);
       	query.setParameter("electionYear", year);
       	query.setParameter("electionType", electionType);
       	
    	return query.list();
    }
	
	public List<Object[]> findAllElectionsHappenedInABooth(Long boothId){
		
		
		Query query = getSession().createQuery("select distinct(model.constituencyElection.election.electionId)," +
				" model.constituencyElection.election.elecSubtype from BoothConstituencyElection model " +
				" where model.booth.boothId =  ?");
		
		query.setParameter(0, boothId);
		
		return query.list();
		
	}
	
	
	public List<Object[]> findAllElectionsHappenedInABooth1(Long boothId , List<Long> constElectionIds){
		
		Query query = getSession().createQuery("select distinct(model.constituencyElection.election.electionId)," +
				" model.constituencyElection.election.elecSubtype from BoothConstituencyElection model " +
				"where model.constituencyElection.constiElecId in(:constElectionIds) and model.booth.partNo=" +
				"(select model1.partNo from Booth model1 where model1.boothId = :boothId)");
		
		query.setParameterList("constElectionIds", constElectionIds);
		query.setParameter("boothId", boothId);
		
		
		return query.list();
		
	}
	
	public List<Long> getBoothIdsByConstituencyIdPartNo(Long constituencyId, Long electionId,String partNo)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select distinct(model.booth.boothId) from BoothConstituencyElection model ");
		stringBuilder.append(" where model.constituencyElection.election.electionId = :electionId ");
		stringBuilder.append(" and model.booth.constituency.constituencyId = :constituencyId and model.booth.partNo =:partNo ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("electionId", electionId);
		query.setParameter("partNo", partNo);
		
		return query.list();
	}
	
	public List<Long> getBoothIdsByElectionIdWardIds(Long electionId,List<Long> wardIds)
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("select distinct model.booth.boothId from BoothConstituencyElection model where   " +
    			"  model.constituencyElection.election.electionId = :electionId and model.constituencyElection.constituency.constituencyId in(:wardIds)");
    	Query query = getSession().createQuery(stringBuilder.toString());
    	query.setParameter("electionId", electionId);
    	query.setParameterList("wardIds", wardIds);
    	return query.list();
    }
	
	public List<Long> getBoothIdsByWardId(Long wardId, Long electionId,Long constituencyId)
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("select distinct model.booth.boothId from BoothConstituencyElection model where model.booth.boothId in(select model1.booth.boothId from  " +
    			" BoothLocalBodyWard model1 where model1.localBodyWard.constituencyId = :wardId) and model.constituencyElection.election.electionId = :electionId " +
    			" and model.booth.constituency.constituencyId = :constituencyId");
    	Query query = getSession().createQuery(stringBuilder.toString());
    	query.setParameter("electionId", electionId);
    	query.setParameter("wardId", wardId);
    	query.setParameter("constituencyId", constituencyId);
    	return query.list();
    }
	
	public List<Object[]> getVotersCountInAConstituencyByParliamentConsId(Long electionId,Long assemblyConstituencyId, Long parliamentConstituencyId){
    	Query query = getSession().createQuery("select sum(model.booth.maleVoters),sum(model.booth.femaleVoters),sum(model.booth.totalVoters),count(*) from BoothConstituencyElection model " +
				" where model.constituencyElection.election.electionId = :electionId and model.booth.constituency.constituencyId = :assemblyConstituencyId and model.constituencyElection.constituency.constituencyId = :parliamentConstituencyId");
    	query.setParameter("electionId", electionId);
    	query.setParameter("assemblyConstituencyId", assemblyConstituencyId);
    	query.setParameter("parliamentConstituencyId", parliamentConstituencyId);
    	return query.list();
   	}
	
	public List<Object[]> getBoothIdsByWardIds(List<Long> wardIds, List<Long> electionIds,Long constituencyId)
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("select distinct model.constituencyElection.election.electionId,model1.localBodyWard.constituencyId,model.booth.boothId,model1.localBodyWard.name,model.booth.totalVoters from BoothConstituencyElection model,BoothLocalBodyWard model1 where model.booth.boothId = model1.booth.boothId and  " +
    			"   model1.localBodyWard.constituencyId in(:wardIds) and model.constituencyElection.election.electionId in(:electionIds) " +
    			" and model.booth.constituency.constituencyId = :constituencyId");
    	Query query = getSession().createQuery(stringBuilder.toString());
    	query.setParameterList("electionIds", electionIds);
    	query.setParameterList("wardIds", wardIds);
    	query.setParameter("constituencyId", constituencyId);
    	return query.list();
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothResultsBasedOnTotVotesIsNullByElectionId(Long electionId)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.name, model.booth.partNo,model.booth.boothId from BoothConstituencyElection model " +
				" where model.constituencyElection.election.electionId = :electionId and (model.booth.totalVoters is null or model.booth.totalVoters = 0) " +
				" order by model.constituencyElection.constituency.name, model.booth.boothId ");
		query.setParameter("electionId",electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothResultsBasedOnValidVotesIsNullByElectionId(Long electionId)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.name, model.booth.partNo,model.booth.boothId from BoothConstituencyElection model " +
				" where model.constituencyElection.election.electionId = :electionId and (model.boothResult.validVotes is null or model.boothResult.validVotes = 0) " +
				" order by model.constituencyElection.constituency.name, model.booth.boothId ");
		query.setParameter("electionId",electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothResultsBasedOnTotVotesGreaterValidVotesByElectionId(Long electionId)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.name, model.booth.partNo,model.booth.totalVoters,model.boothResult.validVotes,model.booth.boothId " +
				" from BoothConstituencyElection model where model.constituencyElection.election.electionId = :electionId " +
				"  and model.boothResult.validVotes > model.booth.totalVoters order by model.constituencyElection.constituency.name, model.booth.boothId " );
		
		query.setParameter("electionId",electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothResultsBasedOnMaleAndFemaleVotesByElectionId(Long electionId)
	{
		Query query = getSession().createQuery("select model.constituencyElection.constituency.name, model.booth.partNo,model.booth.totalVoters,model.booth.maleVoters,model.booth.femaleVoters ,model.boothResult.validVotes " +
				" from BoothConstituencyElection model where model.constituencyElection.election.electionId = :electionId " +
				"  and (model.booth.maleVoters + model.booth.femaleVoters) != model.booth.totalVoters order by model.constituencyElection.constituency.name, model.booth.boothId " );
		
		query.setParameter("electionId",electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getEleYears()
	{
		return getHibernateTemplate().find(" select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear,model.constituencyElection.election.elecSubtype,model.constituencyElection.election.electionScope.electionType.electionType from BoothConstituencyElection model order by model.constituencyElection.election.electionYear desc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituenciesByEleId(Long electionId)
	{
		Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.constituencyId, model.constituencyElection.constituency.name from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId order by model.constituencyElection.constituency.name ");
		query.setParameter("electionId", electionId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByElectionId(Long electionId)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId ");
		query.setParameter("electionId", electionId);
		return query.list();
	}
	
	public List<String> getAllElectionYearsByConstiIds(List<Long> constiIds){
		
		Query query = getSession().createQuery("select distinct model.constituencyElection.election.electionYear from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId in(:constiIds) order by model.constituencyElection.election.electionYear desc");
		query.setParameterList("constiIds", constiIds);
		
		return query.list();
		
	}
	
  public List<Object[]> getParliamentConstisByElectionYear(List<Long> constiIds,String year){
		
		Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId in(:constiIds) " +
				" and model.constituencyElection.election.electionYear = :year order by model.constituencyElection.constituency.name ");
		query.setParameterList("constiIds", constiIds);
		query.setParameter("year", year);
		
		return query.list();
		
	}
  
  public List<Long> getAllAssemblyConstisByYearsAndConstiIds(String electionYear,List<Long> constiIds){
		
		Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.constituencyId from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId in(:constiIds) and model.constituencyElection.election.electionYear = :electionYear");
		query.setParameterList("constiIds", constiIds);
		query.setParameter("electionYear", electionYear);
		return query.list();
		
	}
  
  public List<Object[]> getAllConstisByYearsAndConstiIds(String electionYear,List<Long> constiIds){
		
		Query query = getSession().createQuery("select distinct model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name from BoothConstituencyElection model where model.constituencyElection.constituency.constituencyId in(:constiIds) and model.constituencyElection.election.electionYear = :electionYear");
		query.setParameterList("constiIds", constiIds);
		query.setParameter("electionYear", electionYear);
		return query.list();
		
	}
  
  @SuppressWarnings("unchecked")
public List<Object[]> getBoothsByConstituency(Long constituencyId,Long electionId)
  {
	 Query query = getSession().createQuery("select model.booth.boothId,model.booth.partNo from BoothConstituencyElection model " +
	 		" where model.constituencyElection.election.electionId = :electionId " +
    			" and model.booth.constituency.constituencyId = :constituencyId");
	 query.setParameter("constituencyId", constituencyId);
	 query.setParameter("electionId", electionId);
	 return query.list();
  }

public List<Long> getElectionsByMandals(List<Long> mandalIds,Long partyId,Long electionTypeId)
{
	Query query = getSession().createQuery("select distinct model.constituencyElection.election.electionId from BoothConstituencyElection model " +
			" where model.booth.tehsil.tehsilId in (:mandalIds) and " +
			" model.constituencyElection.election.electionScope.electionType.electionTypeId in (1,2) " );
	//query.setParameter("partyId", partyId);
	//query.setParameter("electionTypeId", electionTypeId);
	query.setParameterList("mandalIds", mandalIds);
	return query.list();
}

public List<Long> getElectionsByUrbanConsti(Long constiId,Long electionTypeId)
{
	Query query = getSession().createQuery("select distinct model.constituencyElection.election.electionId from BoothConstituencyElection model " +
			" where model.constituencyElection.constituency.constituencyId=:constituencyId and " +
			" model.constituencyElection.election.electionScope.electionType.electionTypeId = :electionTypeId " );
	//query.setParameter("partyId", partyId);
	query.setParameter("electionTypeId", electionTypeId);
	query.setParameter("constituencyId", constiId);
	return query.list();
}


public List<Object[]> getTotalValidVotesAndTotalVotersByBoothIdsList(List<Long> boothslist,Long electionId,Long constituencyId)
{
	   Query query = getSession().createQuery(" select sum(model.boothResult.validVotes),model.booth.totalVoters,model.booth.panchayat.panchayatName,model.booth.partNo from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId  " +
		   		" and model.booth.boothId in(:boothslist) and model.booth.constituency.constituencyId =:constituencyId group by model.booth.boothId");
		    query.setParameter("electionId",electionId);
			query.setParameterList("boothslist",boothslist);
			query.setParameter("constituencyId", constituencyId);
		   return query.list(); 
}
public List<Object[]> getTotalValidVotesAndTotalVotersByConstituency(Long electionId,Long constituencyId)
{
	   Query query = getSession().createQuery(" select sum(model.boothResult.validVotes),sum(model.booth.totalVoters) from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId  " +
		   		" and model.booth.boothId in(:boothslist) and model.booth.constituency.constituencyId =:constituencyId");
		    query.setParameter("electionId",electionId);
			query.setParameter("constituencyId", constituencyId);
		   return query.list(); 
}

@SuppressWarnings("unchecked")
public List<Object[]> getBoothIdsByConstituencyIdAndEleId(Long electionId,Long constituencyId)
{
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("select distinct model.booth.boothId,model.booth.constituency.name from BoothConstituencyElection model where  " +
			" model.constituencyElection.election.electionId = :electionId and model.booth.constituency.constituencyId = :constituencyId");
	Query query = getSession().createQuery(stringBuilder.toString());
	query.setParameter("electionId", electionId);
	query.setParameter("constituencyId", constituencyId);
	return query.list();
}


 @SuppressWarnings("unchecked")
public List<Object[]> getElectionYearsByConstituencyIdsList(Long electionTypeId,Long stateId,List<Long> constituencyIdsList)
 {
	StringBuilder str = new StringBuilder();
	str.append(" select distinct model.constituencyElection.election.electionId,model.constituencyElection.election.electionYear from " +
			" BoothConstituencyElection model where model.constituencyElection.election.electionScope.electionType.electionTypeId =:electionTypeId ");
	
	str.append(" and model.constituencyElection.constituency.constituencyId in (:constituencyIdsList)");
	if(electionTypeId.longValue() == 2l)
	 str.append(" and model.constituencyElection.constituency.state.stateId =:stateId ");
	str.append(" order by model.constituencyElection.election.electionYear desc ");
	
	Query query = getSession().createQuery(str.toString());
	
	query.setParameter("electionTypeId", electionTypeId);
	if(electionTypeId.longValue() == 2l)
	 query.setParameter("stateId", stateId);
	query.setParameterList("constituencyIdsList", constituencyIdsList);
	
	return query.list();
 }

@SuppressWarnings("unchecked")
public List<Object[]> getConstituencyListByElectionId(Long electionId,List<Long> constituencyIdsList)
{
  Query query = getSession().createQuery(" select distinct model.constituencyElection.constituency.constituencyId,model.constituencyElection.constituency.name " +
  		" from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId and model.constituencyElection.constituency.constituencyId in (:constituencyIdsList) " +
  		" order by model.constituencyElection.constituency.name ");
  
  query.setParameter("electionId", electionId);
  query.setParameterList("constituencyIdsList", constituencyIdsList);
  return query.list();
}

public List<Long> getBoothIdsByLocalEleBody(Long localEleBodyId, Long electionId)
{
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("select distinct model.booth.boothId from BoothConstituencyElection model where model.booth.localBody.localElectionBodyId =:localElectionBodyId  " +
			" and model.constituencyElection.election.electionId = :electionId ");
	Query query = getSession().createQuery(stringBuilder.toString());
	query.setParameter("electionId", electionId);
	query.setParameter("localElectionBodyId", localEleBodyId);
	return query.list();
}


public Long getTotalVotesByLocationId(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList)
{
	StringBuilder str = new StringBuilder();
	str.append(" select sum(model.boothResult.validVotes) from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId and ");
	
	if(locationType.equalsIgnoreCase("constituency"))
	  str.append(" model.booth.constituency.constituencyId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("mandal") && electionId.longValue() == 258l)//2014 Election
		str.append(" model.booth.panchayat.tehsil.tehsilId =:locationId and model.booth.localBody is null ");
	
	else if(locationType.equalsIgnoreCase("mandal") && electionId.longValue() == 38l)//2009 Election
		str.append(" model.booth.tehsil.tehsilId =:locationId and model.booth.localBody is null ");
	
	else if(locationType.equalsIgnoreCase("panchayat"))
		str.append(" model.booth.panchayat.panchayatId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("booth"))
	 str.append(" model.booth.boothId = :locationId ");
	
	else if(locationType.equalsIgnoreCase("muncipality"))
	  str.append(" model.booth.localBody.localElectionBodyId =:locationId and model.booth.localBody is not null ");
	
	else if(locationType.equalsIgnoreCase("District"))
	 str.append(" model.booth.constituency.district.districtId =:locationId ");
	
	else if(locationType.equalsIgnoreCase("Parliament"))
	 str.append(" model.booth.constituency.constituencyId in (:constituencyIdsList) ");
	
	 if(!locationType.equalsIgnoreCase("District") && !locationType.equalsIgnoreCase("Parliament"))
		str.append(" and model.constituencyElection.constituency.constituencyId =:constituencyId "); 
	 
	 
	
	Query query = getSession().createQuery(str.toString());
	
	if(!locationType.equalsIgnoreCase("Parliament"))
	  query.setParameter("locationId", locationId);
	
	if(!locationType.equalsIgnoreCase("District") && !locationType.equalsIgnoreCase("Parliament"))
	  query.setParameter("constituencyId", constituencyId);
	
	query.setParameter("electionId", electionId);
	
	if(locationType.equalsIgnoreCase("Parliament"))
	  query.setParameterList("constituencyIdsList", constituencyIdsList);
	
	return (Long) query.uniqueResult();
	
}

public List<Object[]> getTotalVotersByLocationId(List<Long> locationIdsList,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList,Long publicationDateId)
{
	StringBuilder str = new StringBuilder();
	//if(locationType.equalsIgnoreCase("Parliament"))
		//str.append(" select sum(model.totalVoters) ");
	//else
		str.append(" select model.reportLevelValue , model.totalVoters ");
		str.append(" from VoterInfo model where model.publicationDate.publicationDateId =:publicationDateId ");
		
		if(locationIdsList != null && locationIdsList.size()>0){
			if(locationType.equalsIgnoreCase("constituency"))
				  str.append("  and  model.reportLevelValue in (:locationIdsList)  and model.voterReportLevel.voterReportLevelId = 1  ");
				
				else if(locationType.equalsIgnoreCase("mandal"))//2014 Election
					str.append("  and  model.reportLevelValue in (:locationIdsList) and model.voterReportLevel.voterReportLevelId = 2  ");
				
				else if(locationType.equalsIgnoreCase("panchayat"))
					str.append("  and  model.reportLevelValue in (:locationIdsList) and model.voterReportLevel.voterReportLevelId = 3  ");
				
				else if(locationType.equalsIgnoreCase("booth"))
					str.append("  and  model.reportLevelValue in (:locationIdsList) and model.voterReportLevel.voterReportLevelId = 4  ");
				
				else if(locationType.equalsIgnoreCase("muncipality"))
					str.append("  and  model.reportLevelValue in (:locationIdsList) and model.voterReportLevel.voterReportLevelId = 5  ");
				
				else if(locationType.equalsIgnoreCase("District"))
					str.append("  and  model.reportLevelValue in (:locationIdsList) and model.voterReportLevel.voterReportLevelId = 11  ");
				
				//else if(locationType.equalsIgnoreCase("Parliament"))
				// str.append("  and model.voterReportLevel.voterReportLevelId = 1 and   model.reportLevelValue in (:constituencyIdsList) ");
		}
		str.append(" group by model.reportLevelValue ");
	Query query = getSession().createQuery(str.toString());
	
	if(!locationType.equalsIgnoreCase("Parliament"))
	  query.setParameterList("locationIdsList", locationIdsList);
	
	query.setParameter("publicationDateId", publicationDateId);
	
	//if(locationType.equalsIgnoreCase("Parliament"))
	//  query.setParameterList("constituencyIdsList", constituencyIdsList);
	
		return query.list();
	
}

public Long getTotalVotersByBoothIdsList(List<Long> boothIdsList,Long electionId)
{
	Query query = getSession().createQuery(" select sum(model.booth.totalVoters) from BoothConstituencyElection model where model.constituencyElection.election.electionId =:electionId and" +
			"  model.booth.boothId in (:boothIdsList) ");
	
	query.setParameter("electionId", electionId);
	query.setParameterList("boothIdsList", boothIdsList);
	return (Long) query.uniqueResult();
}


	public Long getValidVotesforBoothByElectionId(Long boothId,Long electionId)
	{
		Query query = getSession().createQuery(" select sum(model.validVotes) from BoothResult model where " +
				" model.boothConstituencyElection.constituencyElection.election.electionId =:electionId and " +
				"  model.boothConstituencyElection.booth.boothId =:boothId ");
		query.setParameter("electionId", electionId);
		query.setParameter("boothId", boothId);
		return (Long) query.uniqueResult();
	}


	public List<Object[]> getDistrictLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" SELECT c.district_id,sum(votes_earned),sum(BR.valid_votes),0,0,0  FROM ");
		queryStr.append(" candidate_booth_result CBR, ");
		queryStr.append(" nomination N, ");
		queryStr.append(" booth_constituency_election BCE, ");
		queryStr.append(" constituency_election CE, ");
		queryStr.append(" booth B, ");
		queryStr.append(" booth_result BR, ");
		queryStr.append(" constituency c ");
		queryStr.append(" WHERE ");
		queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
		queryStr.append(" N.party_id = 872 AND ");
		queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		queryStr.append(" CE.election_id = 258 AND ");
		queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_id = B.booth_id AND ");
		queryStr.append(" B.local_election_body_id IS null  and B.constituency_id = c.constituency_id   ");
		
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()==1)
			queryStr.append(" and  (c.district_id BETWEEN 11 and 23)");
		else if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()==1)
			queryStr.append(" and (c.district_id BETWEEN 1 and 10)");
		queryStr.append(" group by c.district_id");
		queryStr.append(" order by c.district_id ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getAssemblyLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" SELECT B.constituency_id,sum(CBR.votes_earned),sum(BR.valid_votes),CR.margin_votes,CR.margin_votes_percentage,CR.rank,N.party_id,P.short_name,P.party_flag  FROM ");
	    queryStr.append(" candidate_booth_result CBR, ");
	    queryStr.append(" nomination N, ");
	    queryStr.append(" booth_constituency_election BCE, ");
	    queryStr.append(" constituency_election CE, ");
	    queryStr.append(" booth B, ");
	    queryStr.append(" booth_result BR, ");
	    queryStr.append(" constituency c, candidate_result CR , party P ");
	    queryStr.append(" WHERE ");
	    queryStr.append(" CR.nomination_id = N.nomination_id AND ");
	    queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
	    //queryStr.append(" N.party_id = 872 AND ");
	    queryStr.append(" N.party_id = P.party_id AND ");
	    queryStr.append(" CR.rank in (1,2,3) AND ");
	    queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
	    queryStr.append(" CE.election_id = 258 AND ");
	    queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
	    queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
	    queryStr.append(" BCE.booth_id = B.booth_id AND ");
	    queryStr.append(" B.local_election_body_id IS null  and B.constituency_id = c.constituency_id  ");
	    //if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
	      //queryStr.append(" and   c.district_id = "+inputVO.getParentLocationTypeId().longValue()+"");
	    if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
	      queryStr.append(" and (   c.district_id between 11 and 23) ");
	    else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
	      queryStr.append(" and (   c.district_id between 1 and 10) ");
	    queryStr.append(" group by  B.constituency_id,CR.rank order by B.constituency_id,CR.rank ");
	    
	    Query query = getSession().createSQLQuery(queryStr.toString());
	    return query.list();
	}
	
	public List<Object[]> getMandalLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT B.tehsil_id,SUM(votes_earned),SUM(BR.valid_votes),0,0,0  FROM ");
		queryStr.append(" candidate_booth_result CBR, ");
		queryStr.append(" nomination N, ");
		queryStr.append(" booth_constituency_election BCE, ");
		queryStr.append(" constituency_election CE, ");
		queryStr.append(" booth B, ");
		queryStr.append(" booth_result BR ");
		queryStr.append(" WHERE ");
		queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
		queryStr.append(" N.party_id = 872 AND ");
		queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		queryStr.append(" CE.election_id = 258 AND ");
		queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_id = B.booth_id AND ");
		queryStr.append(" B.local_election_body_id IS null   ");
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L){
			//queryStr.append(" and B.constituency_id = "+inputVO.getParentLocationTypeId().toString()+"  ");
			queryStr.append(" and B.district_id = "+inputVO.getDistrictId().toString()+" ");
		}
		queryStr.append(" GROUP BY B.tehsil_id ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getMunciORUrbanLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT B.local_election_body_id,SUM(votes_earned),SUM(BR.valid_votes),0,0,0  FROM ");
		queryStr.append(" candidate_booth_result CBR, ");
		queryStr.append(" nomination N, ");
		queryStr.append(" booth_constituency_election BCE, ");
		queryStr.append(" constituency_election CE, ");
		queryStr.append(" booth B, ");
		queryStr.append(" booth_result BR ");
		queryStr.append(" WHERE ");
		queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
		queryStr.append(" N.party_id = 872 AND ");
		queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		queryStr.append(" CE.election_id = 258 AND ");
		queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_id = B.booth_id AND ");
		queryStr.append(" B.local_election_body_id IS NOT null   ");
		//queryStr.append(" B.constituency_id = 232 ");		
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			queryStr.append(" and B.constituency_id =  "+inputVO.getParentLocationTypeId().toString()+"  ");
		queryStr.append(" GROUP BY B.local_election_body_id ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getPanchayatLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder quertStr = new StringBuilder();
		quertStr.append(" SELECT B.panchayat_id,SUM(votes_earned),SUM(BR.valid_votes),0,0,0  FROM ");
		quertStr.append(" candidate_booth_result CBR, ");
		quertStr.append(" nomination N, ");
		quertStr.append(" booth_constituency_election BCE, ");
		quertStr.append(" constituency_election CE, ");
		quertStr.append(" booth B, ");
		quertStr.append(" booth_result BR ");
		quertStr.append(" WHERE ");
		quertStr.append(" CBR.nomination_id = N.nomination_id AND ");
		quertStr.append(" N.party_id = 872 AND ");
		quertStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		quertStr.append(" CE.election_id = 258 AND ");
		quertStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		quertStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		quertStr.append(" BCE.booth_id = B.booth_id AND ");
		quertStr.append(" B.local_election_body_id IS null   ");
		
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			quertStr.append(" and B.tehsil_id  =  "+inputVO.getParentLocationTypeId().longValue()+"  ");
		quertStr.append(" GROUP BY B.panchayat_id ");		
		Query query = getSession().createSQLQuery(quertStr.toString());
		return query.list();
	}
	
	public List<Object[]> getPanchayatBoothLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT B.booth_id,votes_earned,BR.valid_votes ,0,0,0 FROM ");
		queryStr.append(" candidate_booth_result CBR, ");
		queryStr.append(" nomination N, ");
		queryStr.append(" booth_constituency_election BCE, ");
		queryStr.append(" constituency_election CE, ");
		queryStr.append(" booth B, ");
		queryStr.append(" booth_result BR ");
		queryStr.append(" WHERE ");
		queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
		queryStr.append(" N.party_id = 872 AND ");
		queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		queryStr.append(" CE.election_id = 258 AND ");
		queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_id = B.booth_id AND ");
		queryStr.append(" B.local_election_body_id IS null   ");
		
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			queryStr.append(" and B.panchayat_id  =  "+inputVO.getParentLocationTypeId().longValue()+"  ");
				
		Query query = getSession().createSQLQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getLocalBodyBoothLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT B.booth_id,votes_earned,BR.valid_votes ,0,0,0 FROM ");
		queryStr.append(" candidate_booth_result CBR, ");
		queryStr.append(" nomination N, ");
		queryStr.append(" booth_constituency_election BCE, ");
		queryStr.append(" constituency_election CE, ");
		queryStr.append(" booth B, ");
		queryStr.append(" booth_result BR ");
		queryStr.append(" WHERE ");
		queryStr.append(" CBR.nomination_id = N.nomination_id AND ");
		queryStr.append(" N.party_id = 872 AND ");
		queryStr.append(" N.consti_elec_id = CE.consti_elec_id AND ");
		queryStr.append(" CE.election_id = 258 AND ");
		queryStr.append(" CBR.booth_constituency_election_id = BCE.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_constituency_election_id = BR.booth_constituency_election_id AND ");
		queryStr.append(" BCE.booth_id = B.booth_id AND ");
		queryStr.append(" B.local_election_body_id IS not null   ");
		
		if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			queryStr.append(" and B.local_election_body_id  =  "+inputVO.getParentLocationTypeId().longValue()+"  ");
				
		Query query = getSession().createSQLQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getLocationWiseAssemblyElectionPolledVotes(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,Long locationTypeId,List<Long> locationValues,List<String> subtypes,Long electionScopeId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT" +
				" e.election_year,sum(br.valid_votes) as polled_votes" +
				" from booth_result  br, booth b , booth_constituency_election bce,constituency_election ce, election e ," +
				" constituency c where br.booth_constituency_election_id = bce.booth_constituency_election_id and " +
				" bce.booth_id = b.booth_id and bce.consti_elec_id = ce.consti_elec_id and ce.election_id = e.election_id   " );
		
		if(electionYrs != null && electionYrs.size() > 0){
			 sb.append(" and  e.election_year in (:electionYrs) ");
		}
		if(parliamentIds != null && parliamentIds.size() > 0 && electionScopeId.longValue() == 1l){
			 sb.append(" and  c.constituency_id in (:parliamentIds) ");
		}else if( electionScopeId.longValue() == 1l){
			sb.append(" and c.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+") ");
		}
		if(assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() != 1l){
			 sb.append(" and  c.constituency_id in (:assemlyIds)  ");
		}else if(assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() == 1l){
			sb.append(" and  b.constituency_id  in (:assemlyIds) ");
		}
		if(electionScopeId != null && electionScopeId.longValue() != 1l){
			sb.append(" and b.constituency_id = c.constituency_id and (c.district_id BETWEEN 11 and 23) ");
		}
		if(subtypes != null && subtypes.size() > 0){
			 sb.append(" and  e.sub_type  in (:subtypes) ");
		}
		if(electionScopeId != null && electionScopeId.longValue() >0){
			sb.append(" and e.election_scope_id = :electionScopeId  ");
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId.longValue() == 2l && electionScopeId.longValue() != 1l){
		          sb.append(" and c.state_id in (:locationValues) ");
		        }else if(locationTypeId.longValue() == 3l && electionScopeId.longValue() != 1l){
		          sb.append(" and c.district_id in (:locationValues)");
		        }else if(locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l){
	              sb.append(" and c.constituency_id in (:locationValues) ");
	            }else if(locationTypeId.longValue() == 5l){
	              sb.append(" and b.tehsil_id in (:locationValues)"); 
	            }else if(locationTypeId.longValue() == 6l){
	              sb.append(" and b.panchayat_id in (:locationValues)"); 
	            }else if(locationTypeId == 7l){
	              sb.append(" and b.local_election_body_id in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append(" and b.ward_id in (:locationValues)"); 
	            }
	        }
				sb.append(" GROUP BY e.election_year order by e.election_year desc ");
				
				Query query = getSession().createSQLQuery(sb.toString());
				
				if(electionYrs != null && electionYrs.size() > 0){
					query.setParameterList("electionYrs", electionYrs);
				}
				if(parliamentIds != null && parliamentIds.size() > 0 && electionScopeId.longValue() == 1l){
					query.setParameterList("parliamentIds", parliamentIds);
				}
				if(assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() != 1l){
					query.setParameterList("assemlyIds", assemlyIds);
				}else if(electionScopeId.longValue() == 1l && assemlyIds != null && assemlyIds.size() > 0 ){
					query.setParameterList("assemlyIds", assemlyIds);
				}
				if(subtypes != null && subtypes.size() > 0){
					query.setParameterList("subtypes", subtypes);
				}
				if(electionScopeId != null && electionScopeId.longValue() >0){
					query.setParameter("electionScopeId", electionScopeId);
				}
				if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 ){
					if((electionScopeId.longValue() == 1l && (locationTypeId.longValue() == 2l || locationTypeId.longValue() == 3l))){}
					else{query.setParameterList("locationValues", locationValues);}
				}
				return query.list();
	}
	
	public List<Object[]> getLocationwiseAssemblyEarnedVotes(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,List<Long> partyids,Long locationTypeId,List<Long> locationValues,List<String> subtypes,Long electionScopeId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT c.state_id,e.election_year,n.party_id,sum(cbr.votes_earned)  from election e ,election_scope es,election_type  et ," +
				" nomination n, constituency_election ce,party p,booth b ,constituency c ,booth_constituency_election bce,candidate_booth_result cbr" +
				" where n.consti_elec_id = ce.consti_elec_id and ce.election_id = e.election_id and e.election_scope_id = es.election_scope_id and " +
				" es.election_type_id = et.election_type_id and n.party_id = p.party_id and bce.consti_elec_id  =ce.consti_elec_id and " +
				" bce.booth_constituency_election_id = cbr.booth_constituency_election_id and cbr.nomination_id = n.nomination_id and " +
				"     ce.constituency_id = c.constituency_id  " +
				"    and bce.booth_id = b.booth_id " );
				
		if(electionScopeId != null && electionScopeId.longValue() != 1l){
			sb.append(" and b.constituency_id = ce.constituency_id and (c.district_id BETWEEN 11 and 23) " );
		}
		if(electionYrs != null && electionYrs.size() > 0){
			 sb.append(" and  e.election_year in (:electionYrs) ");
		}
		if(parliamentIds != null && parliamentIds.size() > 0 && electionScopeId.longValue() == 1l){
			 sb.append(" and  c.constituency_id in (:parliamentIds) ");
		}else if( electionScopeId.longValue() == 1l && parliamentIds.size() == 0){
			sb.append(" and c.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+") ");
		}
		if(assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() != 1l){
			 sb.append(" and  c.constituency_id in (:assemlyIds) ");
		}else if (assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() == 1l){
			sb.append(" and  b.constituency_id in (:assemlyIds) ");
		}
		if(subtypes != null && subtypes.size() > 0){
			 sb.append(" and  e.sub_type  in (:subtypes) ");
		}
		if(electionScopeId != null && electionScopeId.longValue() >0){
			sb.append(" and e.election_scope_id = :electionScopeId  ");
		}
		if(partyids != null && partyids.size() > 0){
			 sb.append(" and  n.party_id  in (:partyids) ");
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId.longValue() == 2l && electionScopeId.longValue() != 1l){
		          sb.append(" and c.state_id in (:locationValues) ");
		        }else if(locationTypeId.longValue() == 3l && electionScopeId.longValue() != 1l){
		          sb.append(" and c.district_id in (:locationValues)");
		        }else if(locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l){
	              sb.append(" and c.constituency_id in (:locationValues) ");
	            }else if(locationTypeId.longValue() == 5l){
	              sb.append(" and b.tehsil_id in (:locationValues)"); 
	            }else if(locationTypeId.longValue() == 6l){
	              sb.append(" and b.panchayat_id in (:locationValues)"); 
	            }else if(locationTypeId == 7l){
	              sb.append(" and b.local_election_body_id in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append(" and b.ward_id in (:locationValues)"); 
	            }
	        }
		sb.append("GROUP BY c.state_id,e.election_year,n.party_id order by e.election_year desc ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(electionYrs != null && electionYrs.size() > 0){
			query.setParameterList("electionYrs", electionYrs);
		}
		if(parliamentIds != null && parliamentIds.size() > 0 && electionScopeId.longValue() == 1l){
			query.setParameterList("parliamentIds", parliamentIds);
		}
		if(assemlyIds != null && assemlyIds.size() > 0 && electionScopeId.longValue() != 1l){
			query.setParameterList("assemlyIds", assemlyIds);
		}else if (electionScopeId.longValue() == 1l && assemlyIds.size() > 0 && electionScopeId.longValue() == 1l){
			query.setParameterList("assemlyIds", assemlyIds);
		}
		if(subtypes != null && subtypes.size() > 0){
			query.setParameterList("subtypes", subtypes);
		}
		if(electionScopeId != null && electionScopeId.longValue() >0){
			query.setParameter("electionScopeId", electionScopeId);
		}
		if(partyids != null && partyids.size() > 0){
			query.setParameterList("partyids", partyids);
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 ){
			if((electionScopeId.longValue() == 1l && (locationTypeId.longValue() == 2l || locationTypeId.longValue() == 3l))){}
			else{query.setParameterList("locationValues", locationValues);}
		}
		return query.list();
	}
	
	public List<Object[]> getLocationWisePolledVotesForVotingDetails(List<Long> electionYrs,Long locationTypeId,List<Long> locationValues,List<String> subtypes,String searchLevel,String clickType,List<Long> partyIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT " );
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if( locationTypeId.longValue() == 3l || locationTypeId.longValue() == 10l ){
		          sb.append(" distinct c.constituency_id ,c.name ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append(" distinct  d.district_id,d.district_name   ");
		        }else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append(" distinct pan.panchayat_id,pan.panchayat_name  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append(" distinct t.tehsil_id ,t.tehsil_name ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		 sb.append(" distinct leb.local_election_body_id ,leb.name ");
		        	}
	            }else if(locationTypeId.longValue() == 5l ){
	            	 sb.append(" distinct  pan.panchayat_id,pan.panchayat_name  ");
	            }else if(locationTypeId.longValue() == 6l || locationTypeId == 7l){
	 	            	sb.append(" distinct b.booth_id,b.location  "); 
	 	        }/*else if(locationTypeId == 7l ){
	              sb.append(" distinct leb.local_election_body_id ,leb.name ");
	            }*/else if(locationTypeId == 8l ){
	              sb.append(" distinct b.ward_id ,ward.name "); 
	            }
	        }
		
		if(clickType != null && clickType.equalsIgnoreCase("clickFunction")){
			sb.append(" ,sum(b.total_voters) as totalVoters " );
		}else {
			sb.append(" ,sum(br.valid_votes) as polled_votes " );
		}
		
		sb.append(" ,e.election_year,n.party_id ");
		 if(locationTypeId.longValue() == 6l || locationTypeId == 7l){
          	sb.append(" ,b.part_no"); 
		 }
		sb.append("  from booth_result  br, booth b , booth_constituency_election bce,constituency_election ce, election e ," +
				" constituency c,nomination n  " );
		
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if( locationTypeId.longValue() == 3l || locationTypeId.longValue() == 10l || locationTypeId.longValue() == 6l || locationTypeId.longValue() == 7l){
		          sb.append(" where ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append(",district d where d.district_id=c.district_id and   ");
		        }
	    	   else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append(",panchayat pan where pan.panchayat_id=b.panchayat_id and   ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append(" ,tehsil t where t.tehsil_id=b.tehsil_id and  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		sb.append(" ,local_election_body leb  where leb.local_election_body_id=b.local_election_body_id and ");
		        	}
	            }else if(locationTypeId.longValue() == 5l ){
	            	 sb.append(" ,panchayat pan where pan.panchayat_id=b.panchayat_id and  "); 
	            }/*else if(locationTypeId == 7l ){
	              sb.append(" ,local_election_body leb  where leb.local_election_body_id=b.local_election_body_id and ");
	            }*/else if(locationTypeId == 8l ){
	              sb.append(" , constituency ward where ward.constituency_id=b.ward_id and "); 
	            }
	        }
		//if(electionScopeId != null && electionScopeId.longValue() != 1l){
			sb.append(" n.consti_elec_id  = ce.consti_elec_id and  b.constituency_id = ce.constituency_id and (c.district_id BETWEEN 11 and 23) " );
		//}
		sb.append(" and br.booth_constituency_election_id = bce.booth_constituency_election_id and " +
				" bce.booth_id = b.booth_id and bce.consti_elec_id = ce.consti_elec_id and ce.election_id = e.election_id " );
		
		if(electionYrs != null && electionYrs.size() > 0){
			 sb.append(" and  e.election_year in (:electionYrs) ");
		}
		if(partyIds != null && partyIds.size() > 0){
			 sb.append(" and  n.party_id  in (:partyIds) ");
		}
		if(subtypes != null && subtypes.size() > 0){
			 sb.append(" and  e.sub_type  in (:subtypes) ");
		}
		sb.append(" and c.election_scope_id = 2  and c.deform_date is null and  b.constituency_id = c.constituency_id ");
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId.longValue() == 2l ){
		          sb.append(" and c.state_id in (:locationValues) ");
		        }else if(locationTypeId.longValue() == 3l ){
		          sb.append(" and c.district_id in (:locationValues)");
		        }else if(locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l){
	              sb.append(" and c.constituency_id in (:locationValues) ");
	            }else if(locationTypeId.longValue() == 5l){
	              sb.append(" and b.tehsil_id in (:locationValues)"); 
	            }else if(locationTypeId.longValue() == 6l){
	              sb.append(" and b.panchayat_id in (:locationValues)"); 
	            }else if(locationTypeId == 7l){
	              sb.append(" and b.local_election_body_id in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append(" and b.ward_id in (:locationValues)"); 
	            }
	        }
		
		sb.append("  group by " );
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if( locationTypeId.longValue() == 3l || locationTypeId.longValue() == 10l ){
		          sb.append("   c.constituency_id , ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append("  d.district_id ,  ");
		        }
		        else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append("  pan.panchayat_id ,  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append("  t.tehsil_id , ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		sb.append("  leb.local_election_body_id , ");
		        	}
	            }else if(locationTypeId.longValue() == 5l ){
	            	 sb.append("  pan.panchayat_id ,  "); 
	            }else if( locationTypeId.longValue() == 6l || locationTypeId == 7l){
	            	sb.append(" b.booth_id ,  "); 
	            }/*else if(locationTypeId == 7l ){
	              sb.append(" group by leb.local_election_body_id order by  leb.name ");
	            }*/else if(locationTypeId == 8l ){
	              sb.append(" b.ward_id in ,)"); 
	            }
	        }
		sb.append(" e.election_year ,n.party_id ");
				Query query = getSession().createSQLQuery(sb.toString());
				
				if(electionYrs != null && electionYrs.size() > 0){
					query.setParameterList("electionYrs", electionYrs);
				}
				if(subtypes != null && subtypes.size() > 0){
					query.setParameterList("subtypes", subtypes);
				}
				
				if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 ){
					query.setParameterList("locationValues", locationValues);
				}
				
				if(partyIds != null && partyIds.size() > 0){
					query.setParameterList("partyIds", partyIds);
				}
				return query.list();
	}
	
	public List<Object[]> getLocationWiseErnedVotesForVotingDetails(List<Long> electionYrs,Long locationTypeId,List<Long> locationValues,List<String> subtypes,String searchLevel,Long electionScopeId,List<Long> partyIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT " );
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if( locationTypeId.longValue() == 10l || locationTypeId.longValue() == 3l){
		          sb.append(" distinct c.constituency_id ,c.name ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append(" distinct  d.district_id,d.district_name   ");
		        }
		        else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append(" distinct pan.panchayat_id,pan.panchayat_name  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append(" distinct t.tehsil_id ,t.tehsil_name ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		 sb.append(" distinct leb.local_election_body_id ,leb.name ");
		        	}
	            }else if(locationTypeId.longValue() == 5l){
	            	 sb.append(" distinct pan.panchayat_id,pan.panchayat_name  "); 
	            }else if(locationTypeId.longValue() == 6l || locationTypeId == 7l){
	            	sb.append(" distinct b.booth_id,b.location  "); 
	            } /*else if(locationTypeId == 7l ){
	              sb.append(" distinct leb.local_election_body_id ,leb.name ");
	            }*/else if(locationTypeId == 8l ){
	              sb.append(" distinct b.ward_id, ward.name "); 
	            }
	        }
		sb.append(" ,sum(cbr.votes_earned),e.election_year,n.party_id  from election e ,election_scope es,election_type  et ," +
				" nomination n, constituency_election ce,party p,booth b ,constituency c ,booth_constituency_election bce,candidate_booth_result cbr" );
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if( locationTypeId.longValue() == 3l || locationTypeId.longValue() == 10l  || locationTypeId.longValue() == 6l || locationTypeId.longValue() == 7l){
		          sb.append(" where ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append(",district d where d.district_id=c.district_id and   ");
		        }else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append(",panchayat pan where pan.panchayat_id=b.panchayat_id and  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append(" ,tehsil t where t.tehsil_id=b.tehsil_id and  ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		sb.append(" ,local_election_body leb  where leb.local_election_body_id=b.local_election_body_id and ");
		        	}
	            }else if(locationTypeId.longValue() == 5l ){
	            	 sb.append(" ,panchayat pan where pan.panchayat_id=b.panchayat_id and "); 
	            }/*else if(locationTypeId == 7l ){
	              sb.append(" ,local_election_body leb  where leb.local_election_body_id=b.local_election_body_id and ");
	            }*/else if(locationTypeId == 8l ){
	              sb.append(" , constituency ward where ward.constituency_id=b.ward_id and "); 
	            }
	        }
		sb.append(" n.consti_elec_id  = ce.consti_elec_id and  n.consti_elec_id = ce.consti_elec_id and ce.election_id = e.election_id and e.election_scope_id = es.election_scope_id and " +
				" es.election_type_id = et.election_type_id and n.party_id = p.party_id and bce.consti_elec_id  =ce.consti_elec_id and " +
				" bce.booth_constituency_election_id = cbr.booth_constituency_election_id and cbr.nomination_id = n.nomination_id and " +
				"     bce.booth_id = b.booth_id  " );
			
		sb.append(" and c.election_scope_id = 2  and c.deform_date is null ");
		//if(electionScopeId != null && electionScopeId.longValue() != 1l){
			sb.append(" and b.constituency_id = c.constituency_id and (c.district_id BETWEEN 11 and 23) " );
		//}
		if(electionYrs != null && electionYrs.size() > 0){
			 sb.append(" and  e.election_year in (:electionYrs) ");
		}
		
		if(subtypes != null && subtypes.size() > 0){
			 sb.append(" and  e.sub_type  in (:subtypes) ");
		}
		if(electionScopeId != null && electionScopeId.longValue() >0){
			sb.append(" and e.election_scope_id = :electionScopeId  ");
		}
		if(partyIds != null && partyIds.size() >0){
			sb.append(" and n.party_id in (:partyIds)  ");
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId.longValue() == 2l ){
		          sb.append(" and c.state_id in (:locationValues) ");
		        }else if(locationTypeId.longValue() == 3l ){
		          sb.append(" and c.district_id in (:locationValues)");
		        }else if(locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l ){
	              sb.append(" and c.constituency_id in (:locationValues) ");
	            }else if(locationTypeId.longValue() == 5l){
	              sb.append(" and b.tehsil_id in (:locationValues)"); 
	            }else if(locationTypeId.longValue() == 6l){
	              sb.append(" and b.panchayat_id in (:locationValues)"); 
	            }else if(locationTypeId == 7l){
	              sb.append(" and b.local_election_body_id in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append(" and b.ward_id in (:locationValues)"); 
	            }
	        }
		
		sb.append("  group by " );
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId.longValue() == 10l || locationTypeId.longValue() == 3l ){
		          sb.append(" c.constituency_id , ");
		        }else if(locationTypeId.longValue() == 2l){
		        	sb.append("  d.district_id ,  ");
		        }else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		 sb.append("  pan.panchayat_id, ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		 sb.append(" t.tehsil_id, ");
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		sb.append("  leb.local_election_body_id, ");
		        	}
	            }else if(locationTypeId.longValue() == 5l ){
	            	 sb.append("  pan.panchayat_id ,  "); 
	            }else if(locationTypeId.longValue() == 6l || locationTypeId == 7l){
		              sb.append("  b.booth_id , "); 
		        }/*else if(locationTypeId == 7l ){
	              sb.append(" group by leb.local_election_body_id order by  leb.name ");
	            }*/else if(locationTypeId == 8l){
	              sb.append("  b.ward_id , "); 
	            }
	        } 
		sb.append("  e.election_year,n.party_id ");
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(electionYrs != null && electionYrs.size() > 0){
			query.setParameterList("electionYrs", electionYrs);
		}
		if(subtypes != null && subtypes.size() > 0){
			query.setParameterList("subtypes", subtypes);
		}
		if(electionScopeId != null && electionScopeId.longValue() >0){
			query.setParameter("electionScopeId", electionScopeId);
		}
		if(partyIds != null && partyIds.size() >0){
			query.setParameterList("partyIds", partyIds);
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 ){
			 query.setParameterList("locationValues", locationValues);
		}
		return query.list();
	}
	
	
}
	