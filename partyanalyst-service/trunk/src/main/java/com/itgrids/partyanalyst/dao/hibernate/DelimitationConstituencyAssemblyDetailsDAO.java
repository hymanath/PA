package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
				"(select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",params);
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
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId," +
				" model.delimitationConstituency.constituency.name from DelimitationConstituencyAssemblyDetails model where model.constituency.constituencyId = ? and" +
				" model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <=?)",params);
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

}
