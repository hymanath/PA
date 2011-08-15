package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;

public class DelimitationConstituencyDAO extends GenericDaoHibernate<DelimitationConstituency, Long> implements
IDelimitationConstituencyDAO {

	public DelimitationConstituencyDAO() {
		super(DelimitationConstituency.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(
			Long constituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituency model where " +
				"model.constituency.constituencyId =? order by model.year desc", 
				constituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(
			Long constituencyID,Long year) {
		
		Object[] params = {constituencyID,year};
		return getHibernateTemplate().find("from DelimitationConstituency model where " +
				"model.constituency.constituencyId = ? and model.year = ?", 
				params);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituency> findByElectionScopeIdStateIdAndElectionYear(Long electionScopeId, Long stateId, Long electionYear){
		Object[] params = {electionScopeId, stateId, electionYear};
		return getHibernateTemplate().find("from DelimitationConstituency model where " +
				"model.constituency.electionScope.electionScopeId =? and "+
				"model.constituency.state.stateId = ? and model.year = ?",params);
	}
	

	@SuppressWarnings("unchecked")
	public List getConstituenciesByDistrictID(Long districtID) {
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name,model.year from DelimitationConstituency model " +
				"where model.constituency.district.districtId = "+districtID+
				" group by model.constituency having max(model.year)=model.year order by model.constituency.name");
	}

	
	public List getDelimitationConstituenciesByDistrictID(Long districtID,Long electionYear) {
		Object[] params = {districtID, electionYear};
		return getHibernateTemplate().find("Select model.constituency.constituencyId ,model.constituency.name, " +
				" YEAR(model.constituency.startDate), YEAR(model.constituency.deformDate) from " +
				"DelimitationConstituency model where model.constituency.district.districtId =? " +
				"and model.year =?",params);
	}

	@SuppressWarnings("unchecked")
	public List getLatestDelimitationYear() {		
		return getHibernateTemplate().find("Select max(model.year) from DelimitationConstituency model ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getLatestConstituenciesForDistrict(Long districtId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituency model where " +
				"model.constituency.district.districtId =? and model.year =(Select max(model.year) from DelimitationConstituency model) order by model.constituency.name",districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getLatestAssemblyConstituenciesInState(Long stateId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituency model where " +
				"model.constituency.district.state.stateId =? and model.year =(Select max(model.year) from DelimitationConstituency model)",stateId);
	}

	public List getConstituenciesByAreaTypeInDist(Long districtId,
			String areaType) {
		Object[] params = {districtId, areaType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.areaType from DelimitationConstituency model where " +
				"model.constituency.district.districtId =? and model.year =(Select max(model.year) from DelimitationConstituency model) and model.constituency.areaType != ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestConstituenciesForADistrict(Long districtId){
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				"model.constituency.district.districtId =? and model.year =(Select max(model.year) from DelimitationConstituency model) order by model.constituency.name",districtId);
	}

	public List getLatestConstituenciesByDistrictIds(String districtIds) {
		
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				"model.constituency.district.districtId in(" + districtIds + ") and model.year =(Select max(model.year) from DelimitationConstituency model) order by model.constituency.name"); 
				
	}

	public List getLatestConstituenciesByElectionTypeInState(
			Long electionTypeId, Long stateId) {
		Object[] params = {electionTypeId, stateId};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				"model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.state.stateId = ? and model.year =(Select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name", params); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestConstituenciesByElectionTypeInDistrict(Long electionTypeId, Long districtId) {
		Object[] params = {electionTypeId, districtId};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				"model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.district.districtId = ? and model.year =(Select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name", params); 
	}
	
	/*@SuppressWarnings("unchecked")
	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyIDForCensus(
			Long constituencyID,Long delimitationYear,Long censusYear) {
		Object[] params = {constituencyID, delimitationYear,censusYear};
		return getHibernateTemplate().find("from DelimitationConstituency model where " +
				"model.constituency.constituencyId =? and model.delimitationYear.delimitationYearId = ? and model.censusYear.censusYearId = ? ", params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object> findDelimitationConstituencyByConstituencyIDForCensus(
			Long constituencyID,Long delimitationYear) {
		Object[] params = {constituencyID,delimitationYear};
		return getHibernateTemplate().find("select model.delimitationConstituencyID from DelimitationConstituency model "+
				"where model.constituency.constituencyId =? and model.year = ? ", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getLatestConstituenciesForDistrictBasedOnYear(Long districtId,Long year){
	    Object[] params = {districtId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId from DelimitationConstituency model where " +
				"model.constituency.district.districtId =? and model.year =? order by model.constituency.name",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInCountry(Long electionTypeId, Long countryId,Long electionYear)
	{
		Object[] params = {electionTypeId, countryId, electionYear};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				" model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.state.country.countryId = ? and model.year = ? " +
				" order by model.constituency.name", params); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInState(Long electionTypeId, Long stateId,Long electionYear)
	{
		Object[] params = {electionTypeId, stateId, electionYear};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				" model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.state.stateId = ? and model.year = ? " +
				" order by model.constituency.name", params); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYearInADistrict(Long electionTypeId, Long districtId,Long electionYear)
	{
		Object[] params = {electionTypeId, districtId, electionYear};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				" model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.district.districtId = ? and model.year = ? " +
				" order by model.constituency.name", params); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestConstituenciesByElectionTypeAndYear(Long electionTypeId, Long constituencyId,Long electionYear)
	{
		Object[] params = {electionTypeId, constituencyId, electionYear};
		return getHibernateTemplate().find("Select model.constituency.constituencyId,model.constituency.name from DelimitationConstituency model where " +
				" model.constituency.electionScope.electionType.electionTypeId = ? and model.constituency.constituencyId = ? and model.year = ? " +
				" order by model.constituency.name", params); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getLatestConstituencyByConstituencyNameAndDistrictIdAndElectionType(
			String constituencyName, Long districtId, String electionType) {
		
		Object[] params = {constituencyName,districtId,electionType,constituencyName,electionType};
		return getHibernateTemplate().find("select model.constituency.constituencyId from DelimitationConstituency model where "+
				"model.constituency.name = ? and model.constituency.district.districtId = ? and model.constituency.electionScope.electionType.electionType = ? "+
				"and model.year =(select max(model1.year) from DelimitationConstituency model1 where model1.constituency.name = ? and "+
				" model1.constituency.electionScope.electionType.electionType = ?)",params);
	}
}
