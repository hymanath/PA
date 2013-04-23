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
				"(select max(model1.year) from DelimitationConstituency model1)",params);
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
				"(select max(model1.year) from DelimitationConstituency model1) and model.constituency.constituencyId in(:assemblyIds)");
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
}
