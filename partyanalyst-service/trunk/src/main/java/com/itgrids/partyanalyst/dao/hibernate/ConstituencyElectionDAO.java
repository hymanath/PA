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
import org.appfuse.dao.jpa.GenericDaoJpa;
import javax.persistence.Query;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;


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
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =?", electionID);	
	}
	

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndDistrict(Long electionID, Long districtID){
		Long[] params = {electionID,districtID};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.district.districtId=?", params);	
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndConstituency(Long electionID, Long constituencyID){
		Long[] params = {electionID,constituencyID};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.constituencyId=?", params);	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByConstituency(Constituency constituency){
		return getHibernateTemplate().find("from ConstituencyElection model where and model.constituency.constituencyId=?", constituency.getConstituencyId());	
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
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.state.stateId =?", params);	

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
	public List findTotalConstituenciesCountInADistrictForAnElection(Long electionId,Long districtId){
		Object[] params = {electionId,districtId};
		return getHibernateTemplate().find("select count(model.constituency) from ConstituencyElection model where model.election.electionId = ? and model.constituency.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTotalAssemblyConstituencies(Long electionId,Long stateId){
		Object[] params = {electionId , stateId};
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name from ConstituencyElection model where model.election.electionId = ? and model.constituency.state.stateId = ? order by model.constituency.name", params);
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
	public List findElectionIdForAParticularElectionTypeAndYearAndConstituency(String electionType,String electionYear,Long constituencyId){
		Object[] params = {electionType, electionYear, constituencyId};
		return getHibernateTemplate().find("select model.election from ConstituencyElection model where model.election.electionScope.electionType.electionType = ?"+
				" and model.election.electionYear = ? and model.constituency.constituencyId = ?",params);
	}
}
