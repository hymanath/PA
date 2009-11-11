package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;

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

	public List<DelimitationConstituencyMandal> findDelConstMandalByConstID(Long constituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.constituency.constituencyId =?", constituencyID);
	}

}
