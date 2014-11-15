package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyAssemblyDetails;

public class DelimitationConstituencyAssemblyDetailsDAO extends GenericDaoHibernate<DelimitationConstituencyAssemblyDetails, Long> implements IDelimitationConstituencyAssemblyDetailsDAO{

	public DelimitationConstituencyAssemblyDetailsDAO(){
		super(DelimitationConstituencyAssemblyDetails.class);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findAssemblyConstituencies(
			Long parliamentConstituencyId, Long electionYear) {
		Object[] params = {parliamentConstituencyId, electionYear};
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1 where model1.year <=?) order by model.constituency.name",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findAssemblyConstituenciesByDelimitationConstituencyId(Long delimitationConstituencyId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.delimitationConstituencyID = ?",delimitationConstituencyId);
	}
			
	@SuppressWarnings("unchecked")
	public List findAssembliesConstituencies(Long parliamentConstituencyId) 
	{
		Object[] params = {parliamentConstituencyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name ",params);
	}
	
	public List findLatestParliamentForAssembly(Long assemblyId){
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId," +
				"model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId = ?",assemblyId);
	} 
	
	@SuppressWarnings("unchecked")
	public List findParliamentConstituenciesForAAssemblyConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId,model.delimitationConstituency.constituency.electionScope.electionType.electionTypeId," +
				"model.delimitationConstituency.constituency.name,model.delimitationConstituency.year from DelimitationConstituencyAssemblyDetails model where " +
				"model.constituency.constituencyId = ?)",constituencyId);
	}
	
	public List findParliamentForAssemblyForTheGivenYear(Long assemblyId,Long electionYear){
		Object[] params = {assemblyId,electionYear};
		return getHibernateTemplate().find("select distinct model.delimitationConstituency.constituency.constituencyId," +
				" model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.constituency.constituencyId = ? and" +
				" model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",params);
	}
	public List findParliamentForAssemblyForTheMaxOfGivenYear(Long assemblyId){
		Object[] params = {assemblyId};
		return getHibernateTemplate().find("select distinct model.delimitationConstituency.constituency.constituencyId," +
				" model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ? and" +
				" model.delimitationConstituency.year =(select max(model1.year) from DelimitationConstituency model1)",params);
	}
	
	public List findParliamentConstituencyForListOfAssemblyConstituency(String assemblyId,Long electionYear){
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId," +
				" model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.constituency.constituencyId  in (  " + assemblyId +
				" ) and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",electionYear);
	}

	public List getAllAssembliesOfParliament(Long parliamentId) {		
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.delimitationConstituency.year from " +
				" DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ?",parliamentId);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findAssemblyConstituenciesIdsAndNames(
			Long parliamentConstituencyId, Long electionYear) {
		Object[] params = {parliamentConstituencyId, electionYear};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",params);
	}
	
	public List findDistrictsOfParliamentConstituency(Long parliamentId){
		return getHibernateTemplate().find("select model.constituency.district.districtId, model.constituency.district.districtName " +
				"from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ? " +
				"and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1) group by " +
				"model.constituency.district.districtId", parliamentId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findAssembliesConstituenciesForAListOfParliamentConstituency(List<Long> parliamentConstituencyIds){	
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model where ");
		query.append(" model.delimitationConstituency.constituency.constituencyId in (:parliamentConstituencyIds) and ");
		query.append(" model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1)");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findParliamentConstituenciesByDistrictId(Long districtId,Long year)
	{
		Object[] params = {districtId, year};
		return getHibernateTemplate().find("select distinct model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model " +
				"where model.constituency.district.districtId = ? and model.delimitationConstituency.year = ?",params);
	}
	public List<Object[]> findDistrictsOfParliamentConstituencies(Long parliamentId){
		return getHibernateTemplate().find("select distinct model.constituency.district.districtId, model.constituency.district.districtName " +
				"from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ? " +
				"and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1) group by " +
				"model.constituency.district.districtId", parliamentId);
	}
	
	
	public List findParliamentByAssemblyIdAndElectionYear(Long assemblyId, Long electionYear){
		Query query = getSession().createQuery("select model.delimitationConstituency.constituency.constituencyId," +
				"model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year =:year " +
				" and model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", assemblyId);
		query.setParameter("year", electionYear);
		
		return query.list();
	} 
	
	public List<Object[]> findLatestParliamentForAssembly(List<Long> assemblyIds){
		Query query = getSession().createQuery("select distinct model.delimitationConstituency.constituency.constituencyId,model.constituency.constituencyId,model.delimitationConstituency.constituency.name" +
				" ,model.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in(:assemblyIds) order by model.constituency.name asc ");
		query.setParameterList("assemblyIds", assemblyIds);
		return query.list();
	} 
	
	public List<Object[]> findLatestAssemblyForParliament(List<Long> assemblyIds,Long parliamentId){
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name " +
				"  from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in(:assemblyIds) " +
				" and model.delimitationConstituency.constituency.constituencyId = :parliamentId ");
		query.setParameterList("assemblyIds", assemblyIds);
		query.setParameter("parliamentId", parliamentId);
		return query.list();
	} 
	
	public List<Long> findLatestParliamentByAssembly(Long assemblyId){
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId" +
				" from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId = ?",assemblyId);
	} 
	
	public List<Long> findAllParliamentForAssembly(List<Long> assemblyIds){
		Query query = getSession().createQuery("select distinct model.delimitationConstituency.constituency.constituencyId " +
				"  from DelimitationConstituencyAssemblyDetails model where  " +
				" model.constituency.constituencyId in (:assemblyIds)");
		query.setParameterList("assemblyIds", assemblyIds);
		return query.list();
	} 
	
	public List<Long> findAllParliamentForAssembliesForTheGivenYear(List<Long> assemblyIds,Long electionYear){
		Query query = getSession().createQuery("select distinct model.delimitationConstituency.constituency.constituencyId " +
				"  from DelimitationConstituencyAssemblyDetails model where model.constituency.constituencyId in (:assemblyIds) and" +
				" model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <= :electionYear )");
		
		query.setParameterList("assemblyIds", assemblyIds);
		query.setParameter("electionYear", electionYear);
		return query.list();
	} 
	

	public List<Object[]> findAllParliamentDetailsAssembliesForTheGivenYear(List<Long> assemblyIds,Long electionYear){
		Query query = getSession().createQuery(" select distinct model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name " +
				" from DelimitationConstituencyAssemblyDetails model where model.constituency.constituencyId in (:assemblyIds) and " +
				" model.delimitationConstituency.year = "+electionYear+" order by  model.delimitationConstituency.constituency.name ");
		
		query.setParameterList("assemblyIds", assemblyIds);
		//query.setParameter("electionYear", electionYear);
		return query.list();
	} 
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAssemblyInfoByDelimitationConstituencyId(Long delimitationConstituencyId){
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.delimitationConstituencyID = ?",delimitationConstituencyId);
	}
	
	
	public List<Object[]> getAllAssemblyDetailsOfParliament(Long parliamentId,Long electionYear)
	{		
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.delimitationConstituency.year from " +
				" DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year ="+electionYear+" order by model.constituency.name ",parliamentId);
	}

	
	
	public List<Long> findAllAssembliesForParliamentForTheGivenYear(List<Long>  assemblyIds,Long parliamentId,Long electionYear){
		Query query = getSession().createQuery("select model.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = :parliamentId and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1 where model1.year <= :electionYear) and model.constituency.constituencyId in(:assemblyIds) ");
		
		query.setParameterList("assemblyIds", assemblyIds);
		query.setParameter("parliamentId", parliamentId);
		query.setParameter("electionYear", electionYear);
		return query.list();
	
	}
	
	public List<Object[]> findLatestParliamentsByAssemblyIds(List<Long> assemblyIds,List<Long> notIds){
		StringBuilder q = new StringBuilder();
		 q.append("select distinct model.delimitationConstituency.constituency.constituencyId,model.delimitationConstituency.constituency.name " +
				" from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in (:assemblyIds) ");
		if(notIds != null && notIds.size() > 0){
			q.append(" and model.constituency.constituencyId not in (:notIds) ");
		}
			
       q.append(" order by model.delimitationConstituency.constituency.name ");
       Query query = getSession().createQuery(q.toString());
		query.setParameterList("assemblyIds", assemblyIds);
		if(notIds != null && notIds.size() > 0){
		query.setParameterList("notIds", notIds);
		}
		return query.list();
	} 
	
	public List<Object[]> findAssParlIdsForAListOfParlConstis(List<Long> parliamentConstituencyIds,List<Long> notIds){	
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituency.constituencyId,model.delimitationConstituency.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model where ");
		query.append(" model.delimitationConstituency.constituency.constituencyId in (:parliamentConstituencyIds) and model.constituency.areaType != 'URBAN' ");
		query.append(" and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1)  ");
		
		if(notIds != null && notIds.size() > 0){
			query.append(" and model.constituency.constituencyId not in (:notIds) ");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		if(notIds != null && notIds.size() > 0){
			queryObject.setParameterList("notIds", notIds);
			}
		queryObject.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestParConstituenciesByStateIdForregion(String electionType , Long stateID,String region)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.delimitationConstituency.constituency.constituencyId , model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model" +
				" where model.delimitationConstituency.constituency.electionScope.electionType.electionType = :electionType" +
				" and model.delimitationConstituency.constituency.state.stateId = :stateID and model.delimitationConstituency.constituency.deformDate is null and model.delimitationConstituency.year = 2009");
		if(region.equalsIgnoreCase("Telangana"))
		{
			str.append(" and model.constituency.district.districtId between 1 and 10 order by model.delimitationConstituency.constituency.name");
			
		}
		if(region.equalsIgnoreCase("All"))
		{
			str.append(" and model.constituency.district.districtId between 1 and 23 order by model.delimitationConstituency.constituency.name");
		}
		else
		{
			str.append(" and model.constituency.district.districtId between 11 and 23 order by model.delimitationConstituency.constituency.name");	
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateID", stateID);
		query.setParameter("electionType", electionType);
		return query.list();
		
	}
	public List<Object[]> getPcListByRegion(Long regionId)
	{

	StringBuilder str = new StringBuilder();
	str.append("select distinct c.constituency_id,c.name from constituency c, delimitation_constituency_assembly_details dcad, delimitation_constituency dc, constituency ac where");
	str.append(" dc.constituency_id = c.constituency_id and");
	str.append(" dc.delimitation_constituency_id=dcad.delimitation_constituency_id and dc.year = '2009' and ");
	str.append(" dcad.constituency_id=ac.constituency_id and c.election_scope_id=1 and c.state_id=1 and");
	if(regionId == 2)
	str.append(" ac.district_id between 1 and 10 and c.deform_date is null ");
	if(regionId == 1)
	str.append(" ac.district_id between 11 and 23 and c.deform_date is null ");
	if(regionId == 0)
	str.append(" ac.district_id between 1 and 23 and c.deform_date is null ");	
	
	str.append(" order by c.name ");
	Query query = getSession().createSQLQuery(str.toString());
	return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> findAssembliesConstituenciesByParliament(Long parliamentConstituencyId) 
	{
		Object[] params = {parliamentConstituencyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId = ? and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAssembliesConstituenciesListForAListOfParliamentConstituency(List<Long> parliamentConstituencyIds)
	{	
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituency.constituencyId,model.constituency.name from DelimitationConstituencyAssemblyDetails model where ");
		query.append(" model.delimitationConstituency.constituency.constituencyId in (:parliamentConstituencyIds) and ");
		query.append(" model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1)");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		return queryObject.list();
	}
	
	public List<Object[]> findLatestParliamentsForAssembly(List<Long> assemblyIds){
		Query query = getSession().createQuery("select distinct model.delimitationConstituency.constituency.constituencyId,model.delimitationConstituency.constituency.name" +
				" ,model.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in(:assemblyIds) order by model.delimitationConstituency.constituency.name asc ");
		query.setParameterList("assemblyIds", assemblyIds);
		return query.list();
	} 
	
	public List<Long> findDistrictsOfParliamentConstituencies(List<Long> parliamentIds){
		Query query = getSession().createQuery("select model.constituency.district.districtId  " +
				"from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId in(:parliamentIds) " +
				"and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1) group by " +
				"model.constituency.district.districtId");
		
		query.setParameterList("parliamentIds", parliamentIds);
		return query.list();
	}
	public List<Long> findDistrictsBYParliament(Long parliamentId){
		return getHibernateTemplate().find("select model.constituency.district.districtId " +
				"from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.constituency.constituencyId = ? " +
				"and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1) group by " +
				"model.constituency.district.districtId", parliamentId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findAssembliesConstituenciesByParliamentList(List<Long> parliamentConstituencyIds) 
	{
		Query query = getSession().createQuery(" select model.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId in (:parliamentConstituencyIds) and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name ");
		
		query.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		return query.list();
		
	}
	public List<Object[]> findLatestParliamentsAndDistrictForAssembly(List<Long> assemblyIds){
	Query query = getSession().createQuery("select distinct model.constituency.constituencyId, model.delimitationConstituency.constituency.constituencyId,model.delimitationConstituency.constituency.name" +
			"  ,model.constituency.district.districtId,model.constituency.district.districtName,model.constituency.name from DelimitationConstituencyAssemblyDetails model where model.delimitationConstituency.year = " +
			"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in(:assemblyIds) order by model.delimitationConstituency.constituency.name asc ");
	query.setParameterList("assemblyIds", assemblyIds);
	return query.list();
	}
	
	public Long getConstituencyByNo(Long constituencyNo){
		Query query = getSession().createQuery(" select model.constituency.constituencyId from DelimitationConstituencyAssemblyDetails model" +
				" where model.delimitationConstituency.constituencyNO = :constituencyNo" +
				" and model.delimitationConstituency.year = 2009" +
				" and model.constituency.electionScope.electionScopeId = 2");
		
		query.setParameter("constituencyNo", constituencyNo);
		
		return (Long) query.uniqueResult();
	}
	
}
