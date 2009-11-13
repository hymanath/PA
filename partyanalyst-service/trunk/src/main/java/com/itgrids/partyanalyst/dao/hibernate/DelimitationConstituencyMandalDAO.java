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

	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =?", 
				delimitationConstituencyID);
	}

	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID) {
		return getHibernateTemplate().find("Select model.tehsil from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =?", 
				delimitationConstituencyID);
	}
	
	public List getStateDistConstituencyMandalByMandalID(Long mandalID){
		return getHibernateTemplate().find("Select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName," +
				" model.tehsil.district.districtId, model.tehsil.district.districtName," +
				" model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name," +
				" model.tehsil.tehsilName from DelimitationConstituencyMandal model where model.tehsil.tehsilId=?",mandalID);
	}

}
