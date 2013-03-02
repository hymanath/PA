package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
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
		return getHibernateTemplate().find("select distinct model.constituencyElection.election.electionId, model.constituencyElection.election.electionYear from " +
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
	
	
}
