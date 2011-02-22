package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.model.CadreProblemDetails;

public class CadreProblemDetailsDAO extends GenericDaoHibernate<CadreProblemDetails,Long> implements ICadreProblemDetailsDAO 
{
	public CadreProblemDetailsDAO()
	{
		super(CadreProblemDetails.class);
	}
	
	public Integer deleteProblemDetailsByCadre(Long cadreId) {
		Query query = getSession().createQuery("delete from CadreProblemDetails model where model.cadre.cadreId = ?");
		query.setParameter(0, cadreId);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List getCadreDetailsByProblemHistoryId(Long problemId) {
		
		return getHibernateTemplate().find("select model.cadre.firstName,model.cadre.lastName from CadreProblemDetails "+
				"model where model.problemHistory.problemLocation.problemAndProblemSource.problem.problemId = ?",problemId);
	}
	
}
