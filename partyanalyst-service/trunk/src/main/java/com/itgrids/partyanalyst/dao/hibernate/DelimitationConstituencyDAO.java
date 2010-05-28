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

	public List getLatestDelimitationYear() {		
		return getHibernateTemplate().find("Select max(model.year) from DelimitationConstituency model ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getLatestConstituenciesForDistrict(Long districtId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituency model where " +
				"model.constituency.district.districtId =? and model.year =(Select max(model.year) from DelimitationConstituency model)",districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getLatestAssemblyConstituenciesInState(Long stateId){
		return getHibernateTemplate().find("select model.constituency from DelimitationConstituency model where " +
				"model.constituency.district.state.stateId =? and model.year =(Select max(model.year) from DelimitationConstituency model)",stateId);
	}
	
}
