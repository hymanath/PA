/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Aug 4, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;
/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.utils.IConstants;


public class ConstituencyElectionDAO extends GenericDaoHibernate<ConstituencyElection, Long> implements
		IConstituencyElectionDAO {

	public ConstituencyElectionDAO() {
		super(ConstituencyElection.class);
	}	 
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByProperty(String propertyName, Object value) {
		return getHibernateTemplate().find("from ConstituencyElection where " + propertyName + "=?", value);		
		
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElection(Long electionID){
		return getHibernateTemplate().find("select model.constiElecId, model.constituencyElectionResult.validVotes from ConstituencyElection model where model.election.electionId =?", electionID);	
	}
	

	@SuppressWarnings("unchecked")
	public List findByElectionAndDistrict(Long electionID, Long districtID){
		Long[] params = {electionID,districtID};
		return getHibernateTemplate().find("select model.constiElecId, model.constituencyElectionResult.validVotes from ConstituencyElection model where model.election.electionId =? and model.constituency.district.districtId=?", params);	
	}

	@SuppressWarnings("unchecked")
	public List findByElectionAndConstituency(Long electionID, Long constituencyID){
		Long[] params = {electionID,constituencyID};
		return getHibernateTemplate().find("select model.constiElecId, model.constituencyElectionResult.validVotes from ConstituencyElection model where model.election.electionId =? and model.constituency.constituencyId=?", params);	
	}
	
	
	@SuppressWarnings("unchecked")
	public List findAllElectionsByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("select model.constiElecId, model.election.electionYear from " +
				"ConstituencyElection model where  model.constituency.constituencyId=? order by model.election.electionYear DESC", constituencyId);	
	}
	
	@SuppressWarnings("unchecked")
	public List findAllAssetsAndLiabilitiesElectionsByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("select model.constiElecId, model.election.electionYear from " +
				"ConstituencyElection model where  model.constituency.constituencyId=? and " +
				"model.election.hasAssets = 'true' order by model.election.electionYear DESC", constituencyId);	
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByConstituencyElectionAndDistrict(String electionYear, String constituencyName, Long electionScopeId, Long districtId){
		Object[] params = {electionYear,constituencyName, electionScopeId, districtId};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionYear =? and model.constituency.name=? and model.election.electionScope.electionScopeId = ? and model.constituency.district.districtId = ?", params);	
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByConstituencyElectionAndState(
			String electionYear, String parliamentConstituencyName,
			Long electionScopeId, Long stateId) {
		Object[] params = {electionYear, parliamentConstituencyName, electionScopeId, stateId};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionYear =? and model.constituency.name=? and model.election.electionScope.electionScopeId = ? and model.constituency.state.stateId = ?", params);	
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionScopeAndYear(Long electionScopeId,String year){
		Object params[] = {electionScopeId,year};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionScope.electionScopeId = ? and model.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndState(Long electionID,Long stateId){
		Long[] params = {electionID,stateId};
		return getHibernateTemplate().find("select model.constiElecId, model.constituencyElectionResult.validVotes from ConstituencyElection model where model.election.electionId =? and model.constituency.state.stateId =?", params);	

	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndStateAndDistrict(Long electionID,Long stateId,Long districtID){
		Long[] params = {electionID,stateId,districtID};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.state.stateId =? and model.constituency.district.districtId=?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstituencyByElectionAndDistrict(Long electionId,Long districtId){
		Long[] params = {electionId,districtId};
		return getHibernateTemplate().find("Select distinct model.constituency from ConstituencyElection model where model.election.electionId = ? and model.constituency.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAState(Long electionId){
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes) from ConstituencyElection model where model.election.electionId = ?",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAStateAndDistrict(Long electionId,Long districtId){
		Object[] params = {electionId,districtId};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes) from ConstituencyElection model where model.election.electionId = ? and model.constituency.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAStateAndDistrictForLocalElectionBody(Long electionId,Long districtId){
		Object[] params = {electionId,districtId};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes) from ConstituencyElection model where model.election.electionId = ? and model.constituency.localElectionBody.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalConstituenciesCountInADistrictForAnElection(Long electionId,Long districtId){
		Object[] params = {electionId,districtId};
		return getHibernateTemplate().find("select count(model.constituency) from ConstituencyElection model where model.election.electionId = ? and model.constituency.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalAssemblyConstituencies(Long electionId,Long stateId){
		Object[] params = {electionId , stateId};
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name from ConstituencyElection model " +
				"where model.election.electionId = ? and model.constituency.state.stateId = ? order by model.constituency.name", params);
	}
	 
	@SuppressWarnings("unchecked")
	public List getTotalValidVotesForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear){
		Object[] params = {electionType,electionYear};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes)" +
				" from ConstituencyElection model where model.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituency.tehsil.tehsilId  in (  " + tehsilIds +
				" ) and model.election.electionYear = ?  ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalVotersForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear){
		Object[] params = {electionType,electionYear};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.totalVotes)" +
				" from ConstituencyElection model where model.constituency.electionScope.electionType.electionType = ?" +
				" and model.constituency.tehsil.tehsilId  in (  " + tehsilIds +
				" ) and model.election.electionYear = ?  ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalValidVotesParticularElectionYear(String electionType,String electionYear,Long districtId){
		Object[] params = {electionType,electionYear,districtId};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes)" +
				" from ConstituencyElection model where model.constituency.electionScope.electionType.electionType = ?" +
				" and model.election.electionYear = ? and model.constituency.district.districtId = ? ",params);
	}
	@SuppressWarnings("unchecked")
	public List findByDistrictElectionConstituency(Long electionId,
			Long districtId, String constituencyName) {
		Object[] params = {electionId, districtId, constituencyName};
		return getHibernateTemplate().find("select model.constiElecId, model.constituency.name from ConstituencyElection model " +
				"where model.election.electionId = ? and model.constituency.district.districtId = ? and model.constituency.name = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionYear) {
		Object[] params = {districtId, stateId, electionYear};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from" +
				" ConstituencyElection model where " +
				" model.constituency.district.districtId = ? and model.constituency.state.stateId = ? and " +
				" model.election.electionYear=? and " +
				" model.constituency.constituencyId in (select consti.constituencyId from Constituency consti )and " +
				" model.election.electionId in (select elec.electionId from Election elec)",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionYear,String electionType) {
		Object[] params = {districtId, stateId, electionYear,electionType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from" +
				" ConstituencyElection model where " +
				" model.constituency.district.districtId = ? and model.constituency.state.stateId = ? and " +
				" model.election.electionYear=? and model.election.electionScope.electionType.electionType = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIdsForLocalBodys(Long districtId,Long stateId,String electionYear,String electionType) {
		Object[] params = {districtId, stateId, electionYear,electionType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from" +
				" ConstituencyElection model where " +
				" model.constituency.localElectionBody.district.districtId = ? and model.constituency.localElectionBody.district.state.stateId = ? and " +
				" model.election.electionYear=? and model.election.electionScope.electionType.electionType = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionIdForAParticularElectionTypeAndYearAndConstituency(String electionType,String electionYear,Long constituencyId){
		Object[] params = {electionType, electionYear, constituencyId};
		return getHibernateTemplate().find("select model.election from ConstituencyElection model where model.election.electionScope.electionType.electionType = ?"+
				" and model.election.electionYear = ? and model.constituency.constituencyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAnState(Long electionId,Long stateId){
		Object[] params = {electionId,stateId};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes) from ConstituencyElection model where model.election.electionId = ? and model.constituency.state.stateId = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List findConstituenciesCountInAnElection(Long electionId) {
		return getHibernateTemplate().find("select count(distinct model.constituency.constituencyId) from ConstituencyElection model"+
				" where model.election.electionId = ?",electionId);
	}

	public List findTotalValidVotesInConstituencyElection(Long constituencyId,
			String electionYear) {
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes) from ConstituencyElection model " +
				"where model.constituency.constituencyId = ? and model.election.electionYear = ?",params);
	}
	
	public List findConstituenciesByElectionGroupByDistrict(Long electionId) {
		return getHibernateTemplate().find("select model.constituency.district.districtId, count(model.constituency.constituencyId)," +
				"sum(model.constituencyElectionResult.validVotes),model.constituency.district.districtName from " +
				"ConstituencyElection model where model.election.electionId = ? group by model.constituency.district.districtId " +
				"order by model.constituency.district.districtId",electionId);
	}
	
	public List findConstituenciesByElectionGroupByState(Long electionId) {
		return getHibernateTemplate().find("select model.constituency.state.stateId, count(model.constituency.constituencyId)," +
				"sum(model.constituencyElectionResult.validVotes),model.constituency.state.stateName from " +
				"ConstituencyElection model where model.election.electionId = ? group by model.constituency.state.stateId " +
				"order by model.constituency.state.stateId",electionId);
	}
	
	public List getVotesDataForAConstituency(Long constituencyId,Long electionId){  
		Object[] params = {constituencyId,electionId};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.totalVotes)," +
				" sum(model.constituencyElectionResult.totalVotesPolled) from ConstituencyElection model "+
				" where model.constituency.constituencyId = ? and model.election.electionId = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List getValidVotesForAConstituency(Long constId, String elecYear) {
		Object[] params = {constId,elecYear};
		return getHibernateTemplate().find("select model.constituencyElectionResult.validVotes from "+
				"ConstituencyElection model where model.constituency.constituencyId = ? "+
				"and model.election.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getValidVotesForMptcZptcElectionsInMandals(String mandalIds){
		return getHibernateTemplate().find("select model.election.electionId," +
				"model.election.electionScope.electionType.electionType, " +
				"model.election.electionYear," +
				"sum(model.constituencyElectionResult.validVotes) from " +
				"ConstituencyElection model where model.constituency.tehsil.tehsilId in("+mandalIds+") " +
				"group by model.election.electionId order by " +
				"model.election.electionId");
	}
	
	@SuppressWarnings("unchecked")
	public List getValidVotesForMunicipalitiesAndCorporationsInMandals(String mandalIds){
		return getHibernateTemplate().find("select model.election.electionId," +
				"model.election.electionScope.electionType.electionType, " +
				"model.election.electionYear," +
				"sum(model.constituencyElectionResult.validVotes) from " +
				"ConstituencyElection model where model.constituency.localElectionBody.tehsil.tehsilId in("+mandalIds+") " +
				"group by model.election.electionId order by " +
				"model.election.electionId");
	}
	@SuppressWarnings("unchecked")
	public List getParticipatedStateDetailsForAnElectionType(Long electionType) {
		return getHibernateTemplate().find("select distinct model.constituency.state.stateId,model.constituency.state.stateName "+
				"from ConstituencyElection model where model.election.electionScope.electionType.electionTypeId = ? " +
				"order by model.constituency.state.stateName",electionType);
	}
	@SuppressWarnings("unchecked")
	public List findPartyvalidVotesInfoByElectionAndPartyGroupByDistrictId(Long electionId, String partyIds) {
		Object[] params = {electionId,electionId};
		return getHibernateTemplate().find("select model.constituency.district.districtId, " +
				"sum(model.constituencyElectionResult.validVotes) " +
				"from ConstituencyElection model where model.election.electionId = ? and " +
				"model.constituency.constituencyId in " +
				"(select distinct model2.constituencyElection.constituency.constituencyId from Nomination model2 where" +
				" model2.constituencyElection.election.electionId = ? and model2.party.partyId in ("+partyIds+")) "+
				"group by model.constituency.district.districtId " +
				"order by model.constituency.district.districtId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findPartyvalidVotesInfoByElectionAndPartyGroupByStateId(Long electionId, String partyIds) {
		Object[] params = {electionId,electionId};
		return getHibernateTemplate().find("select model.constituency.state.stateId, " +
				"sum(model.constituencyElectionResult.validVotes) " +
				"from ConstituencyElection model where model.election.electionId = ? and " +
				"model.constituency.constituencyId in " +
				"(select distinct model2.constituencyElection.constituency.constituencyId from Nomination model2 where" +
				" model2.constituencyElection.election.electionId = ? and model2.party.partyId in ("+partyIds+")) "+
				"group by model.constituency.state.stateId " +
				"order by model.constituency.state.stateId",params);
	}
	
	public List getConstituenciesCountByDistrictForStateAndElection(Long stateId, Long electionId){
		Object[] params = {stateId, electionId};
		return getHibernateTemplate().find("select model.constituency.district.districtId, count(model.constiElecId) " +
				"from ConstituencyElection model where model.constituency.district.state.stateId = ? and " +
				"model.election.electionId = ? group by model.constituency.district.districtId", params);
	}
	
	public List getConstituenciesCountByStateForCountryAndElection(Long countryId, Long electionId){
		Object[] params = {countryId, electionId};
		return getHibernateTemplate().find("select model.constituency.state.stateId, count(model.constiElecId) " +
				"from ConstituencyElection model  where model.constituency.state.country.countryId = ? and " +
				"model.election.electionId = ? group by model.constituency.state.stateId", params);
	}
	@SuppressWarnings("unchecked")
	public List getConstituencyValidVotesForLocalBodyElection(Long localBodyId,
			String electionYear) {
		Object[] params = {localBodyId,electionYear};
		return getHibernateTemplate().find("select sum(model.constituencyElectionResult.validVotes),sum(model.constituencyElectionResult.totalVotesPolled),"+
				"sum(model.constituencyElectionResult.totalVotes) from ConstituencyElection model "+
				"where model.constituency.localElectionBody.localElectionBodyId = ? and model.election.electionYear = ? "+
				"group by model.constituency.localElectionBody.localElectionBodyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getLocalBodyElectionsInAState(Long localBodyId,Long stateId){
		Object[] params = {localBodyId,stateId};
		return getHibernateTemplate().find("select distinct model.election.electionId,model.election.electionYear from ConstituencyElection model"+
				" where model.constituency.localElectionBody.localElectionBodyId = ? and"+
				" model.election.electionScope.state.stateId = ? order by model.election.electionYear desc",params);
	}
			
	@SuppressWarnings("unchecked")
	public List getConstituencyVotesInfoForLocalBodyElection(Long localBodyId,Long electionId){
		Object[] params = {localBodyId,electionId};
		return getHibernateTemplate().find("select model.constituency.constituencyId,"+
				"model.constituency.name,sum(model.constituencyElectionResult.validVotes),"+
				"sum(model.constituencyElectionResult.totalVotesPolled),"+
				"sum(model.constituencyElectionResult.totalVotes) from ConstituencyElection model "+
				"where model.constituency.localElectionBody.localElectionBodyId = ? and model.election.electionId = ? "+
				"group by model.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getAllWardsDetailsParticipatedInALocalBodyElection(Long localBodyId,Long electionId){
		Object[] params = {localBodyId,electionId};
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId,"+
				"model.constituency.name from ConstituencyElection model where "+
				"model.constituency.localElectionBody.localElectionBodyId = ? and model.election.electionId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getConstituencyVotesInfoForLocalBodyElection(Long localBodyId,Long electionId,Long wardId){
		Object[] params = {localBodyId,electionId,wardId};
		return getHibernateTemplate().find("select model.constituency.constituencyId,"+
				"model.constituency.name,sum(model.constituencyElectionResult.validVotes),"+
				"sum(model.constituencyElectionResult.totalVotesPolled),"+
				"sum(model.constituencyElectionResult.totalVotes) from ConstituencyElection model "+
				"where model.constituency.localElectionBody.localElectionBodyId = ? and model.election.electionId = ? "+
				"and model.constituency.constituencyId = ? group by model.constituency.constituencyId",params);
	}
	
	public List getLatestReservationZone(List<Long> constituencyIds,Long electionYear){
		StringBuilder query = new StringBuilder();
		query.append(" select model.reservationZone,model.election.electionYear,model.constituency.constituencyId from ConstituencyElection model");			
		query.append(" where model.election.electionYear = ? ");		
		query.append(" and model.constituency.constituencyId in (:constituencyIds)");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0,electionYear);		
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getElectionYearAndElectionTypeByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find(" select model.election.electionId, model.election.electionYear, model.constituency.electionScope.electionType.electionType from ConstituencyElection model "+
				"where constituency.constituencyId = ?",constituencyId);
		
	}
	
	public Object getPCCountInAElection(Long electionId)
	{
		Query query = getSession().createQuery("select count(model.constiElecId) from ConstituencyElection model where model.election.electionId = ?");
		query.setParameter(0,electionId);
		return query.uniqueResult();
	}
	
	
	public List getConstituenciesHavingMaxSpan(String electionSubType,String electionType,Long stateId,List<Long> elecIds,String type){
		StringBuilder query = new StringBuilder();
		query.append(" select count(model),model.constituency.constituencyId,model.constituency.name from ConstituencyElection model");			
		query.append(" where model.constituency.state.stateId = ? and model.election.electionId in (:elecIds)");	
		if(!type.equalsIgnoreCase(IConstants.ALL))
			query.append(" and model.constituency.startDate is null ");
		query.append(" and model.constituency.deformDate is null group by model.constituency.constituencyId  order by count(model) desc");	
		
		Query queryObject = getSession().createQuery(query.toString());		
		
		queryObject.setLong(0,stateId);			
		queryObject.setParameterList("elecIds", elecIds);
		return queryObject.list();		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getLatestConstituencies(List<Long> constIds,Long latestElecId){
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituency.constituencyId from ConstituencyElection model");	
		query.append(" where  model.election.electionId = ? and ");
		query.append(" model.constituency.constituencyId in (:constIds)");
		
		Query queryObject = getSession().createQuery(query.toString());		
		
		queryObject.setLong(0,latestElecId);			
		queryObject.setParameterList("constIds", constIds);
		return queryObject.list();	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findConstituencyElectionsByElection(
			Long electionId) {
		
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId = ?",electionId);
	}
	@SuppressWarnings("unchecked")
	public List getLatestResultsElectionYearInAConstituency(Long constituencyId) {
		
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select max(nModel.election.electionYear) from ConstituencyElection nModel where "+
			"nModel.hasResults is null and nModel.constituency.constituencyId = ? order by nModel.election.electionYear desc",params);
	}
	
	/*@SuppressWarnings("unchecked")
	public List getLatestResultsElectionYearInAConstituency(Long constituencyId){
		
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("SELECT model1.election.electionId, model1.election.electionYear "+
										   "FROM ConstituencyElection model1,Election model2 "+
										   "where model1.election.electionId = model2.electionId "+
										   "and model1.hasResults is null "+ 
										   "and model1.constituency.constituencyId = ? "+ 
										   "ORDER BY model1.election.electionYear desc",params);
	}*/
	
	
	public List getLeadingConstituenciesCount(Long electionId){
		
		 return getHibernateTemplate().find("select count(model.constituency.constituencyId) from ConstituencyElection model where " +
		 		" model.constiElecId in (select model1.constiElecId from ConstituencyLeadCandidate model1 " +
		 		" where model.election.electionId = ?",electionId);
	}
	
	@SuppressWarnings("unchecked")
	public List getCountOfOldConstituencies(Long electionId){
		
		Object[] params ={electionId};
		return getHibernateTemplate().find("select count(model.constituency.constituencyId) from ConstituencyElection model " +
				" where model.election.electionId =? and model.constituency.startDate is null and model.constituency.deformDate is null",params );
	}
	
	
	@SuppressWarnings("unchecked")
	public List getCountOfDelimitedConstituencies(Long electionId){
		
		Object[] params ={electionId};
		return getHibernateTemplate().find("select count(model.constituency.constituencyId) from ConstituencyElection model " +
				" where model.election.electionId =? and model.constituency.startDate is not null and model.constituency.deformDate is null",params );
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyWinningConstituenciesCount(Long electionId) {
	
	return getHibernateTemplate().find("Select nModel.constituencyElection.constituency.startDate,count(nModel.constituencyElection.constituency.constituencyId), nModel.party.shortName " +
			" from Nomination nModel ,CandidateResult crModel where " +
			" nModel.nominationId = crModel.nomination.nominationId and" +
			" nModel.constituencyElection.election.electionId = ? and crModel.rank = 1 group by nModel.party.shortName,nModel.constituencyElection.constituency.startDate",electionId);
    }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getOldAndNewConstituenciesInAElection(Long electionId)
	{
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.startDate from ConstituencyElection model where model.election.electionId = ? ",electionId);
	}
	
	public List<Object[]> getConstituenciesForAnElec(Long electionId)
	{
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from ConstituencyElection model where model.election.electionId = ? order by model.constituency.name ",electionId);
	   
	}
}


