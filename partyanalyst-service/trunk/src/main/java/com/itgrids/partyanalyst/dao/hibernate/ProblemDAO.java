package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemStatus;

public class ProblemDAO extends GenericDaoHibernate<Problem,Long> implements IProblemDAO{

	public ProblemDAO()
	{
		super(Problem.class);
	}	
	
	public Integer deleteProblemDetails(Long problemId,Date currentDate)
	{
		Query queryObj = getSession().createQuery("update Problem model set model.isDelete = 'true', model.updatedTime = :updatedTime where model.problemId = :problemId");
		queryObj.setParameter("problemId", problemId);
		queryObj.setParameter("updatedTime", currentDate);
		return queryObj.executeUpdate();
	}	
	
}
