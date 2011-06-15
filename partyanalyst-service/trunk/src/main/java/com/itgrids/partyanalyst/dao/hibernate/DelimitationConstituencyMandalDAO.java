package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;

public class DelimitationConstituencyMandalDAO extends GenericDaoHibernate<DelimitationConstituencyMandal, Long> implements
IDelimitationConstituencyMandalDAO {

	public DelimitationConstituencyMandalDAO() {
		super(DelimitationConstituencyMandal.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =?", 
				delimitationConstituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID,Long tehsilId) {
		
		Object[] params = {delimitationConstituencyID,tehsilId};
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =? and model.tehsil.tehsilId = ?", 
				params);
	}

	@SuppressWarnings("unchecked")
	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID) {
		return getHibernateTemplate().find("Select model.tehsil from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =? order by model.tehsil.tehsilName", 
				delimitationConstituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List getStateDistConstituencyMandalByMandalID(Long mandalID){
		return getHibernateTemplate().find("Select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName," +
				" model.tehsil.district.districtId, model.tehsil.district.districtName," +
				" model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name," +
				" model.tehsil.tehsilName, model.delimitationConstituency.year from DelimitationConstituencyMandal model where model.tehsil.tehsilId=?" +
				" group by model.delimitationConstituency.year, model.delimitationConstituency.constituency.name" +
				" order by model.delimitationConstituency.year, model.delimitationConstituency.constituency.name ",mandalID);
	}
	
	
	public List getMandalsOfConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName, model.delimitationConstituency.year, " +
				"model.isPartial from DelimitationConstituencyMandal model where model.delimitationConstituency.constituency.constituencyId = ? " +
				"order by model.delimitationConstituency.year desc,model.tehsil.tehsilName ", constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestMandalDetailsForAConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID = " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId = ?" +
				" group by model1.constituency.constituencyId order by model1.year desc) ", constituencyId);
	}
	
	public List getMandalDetailsForAConstituency(Long constituencyId,Long electionYear){
		Object[] parms = {constituencyId,electionYear};
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.constituency.constituencyId = ? " +
				" and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <= ?)" +
				" ", parms);
	}
	

	@SuppressWarnings("unchecked")
	public List<Tehsil> getLatestMandalDetailsForAConstituencies(String constituencyIds){
		return getHibernateTemplate().find("select model.tehsil" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in("+constituencyIds+")"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getLatestMandalIdsByConstituenciesIds(List<Long> constituencyIds){
		StringBuilder query = new StringBuilder();
		query.append("select model.tehsil.tehsilId from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in( :constituencyIds)"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}	
	
	public List getLatestAssemblyConstitueciesOfTehsil(Long tehsilId){
		return getHibernateTemplate().find("Select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName," +
				" model.tehsil.district.districtId, model.tehsil.district.districtName," +
				" model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name" +
				" from DelimitationConstituencyMandal model where model.tehsil.tehsilId = ? and model.delimitationConstituency.year = " +
				" (select max(model1.year) from DelimitationConstituency model1)",tehsilId);
	}

	public List getLatestMandalsInConstituencies(String constituencyIds) {
		
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in("+constituencyIds+")"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestMandalsForAConstituency(Long delimconstituencyId) {
		
		return getHibernateTemplate().find("select model.dcm_id,model.tehsil.tehsilId, model.tehsil.tehsilName, model.isPartial " +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID = ? order by model.tehsil.tehsilName",delimconstituencyId);
	}
}
