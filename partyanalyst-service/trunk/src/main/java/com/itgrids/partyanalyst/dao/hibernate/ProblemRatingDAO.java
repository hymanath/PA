package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemRatingDAO;
import com.itgrids.partyanalyst.model.ProblemRating;

public class ProblemRatingDAO extends GenericDaoHibernate<ProblemRating, Long> implements IProblemRatingDAO{

	public ProblemRatingDAO() {
		super(ProblemRating.class);
	}
	
	public Double getAverageRatingOfAProblem(Long problemId)
	{
		Query query = getSession().createQuery("select avg(model.rating) from ProblemRating model where model.problem.problemId = ?");
		query.setParameter(0, problemId);
		return (Double)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRatingWiseCountOfAProblem(Long problemId)
	{
		Query query = getSession().createQuery("select model.rating,count(model.problemRatingId) from ProblemRating model where model.problem.problemId = ? GROUP BY model.rating ");
		query.setParameter(0, problemId);
		return query.list();
	}

}
