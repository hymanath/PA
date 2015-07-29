package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateContestedLocationDAO;
import com.itgrids.partyanalyst.model.CandidateContestedLocation;

public class CandidateContestedLocationDAO extends GenericDaoHibernate<CandidateContestedLocation, Long> implements ICandidateContestedLocationDAO{

	public CandidateContestedLocationDAO() {
		super(CandidateContestedLocation.class);
		
	}

	public CandidateContestedLocation getCandidateContestedLocationDetailsByCadre(Long cadreId){
		
		Query query=getSession().createQuery("select model from CandidateContestedLocation model " +
				" where model.tdpCadre.tdpCadreId =:cadreId ");
		
		query.setParameter("cadreId", cadreId);
		return 	(CandidateContestedLocation) query.uniqueResult();
	}
}
