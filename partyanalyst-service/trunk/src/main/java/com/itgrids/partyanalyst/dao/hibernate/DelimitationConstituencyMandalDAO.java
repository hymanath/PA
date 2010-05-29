package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID) {
		return getHibernateTemplate().find("Select model.tehsil from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =? order by model.tehsil.tehsilName", 
				delimitationConstituencyID);
	}
	
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
				"order by model.delimitationConstituency.year desc,model.tehsil.tehsilId ", constituencyId);
	}
	
	public List getLatestMandalDetailsForAConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID = " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId = ?" +
				" group by model1.constituency.constituencyId order by model1.year desc) ", constituencyId);
	}
}
