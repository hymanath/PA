package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemLikesDAO;
import com.itgrids.partyanalyst.model.ProblemLikes;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemLikesDAO extends GenericDaoHibernate<ProblemLikes,Long> implements IProblemLikesDAO{

	public ProblemLikesDAO()
	{
		super(ProblemLikes.class);
	}
	
	public List<Long> getAllLikes(Long problemId)
	{
		return getHibernateTemplate().find("select count(model.problemLikesId) from ProblemLikes model where model.problem.problemId = ? and model.isLiked = '"+IConstants.TRUE+"' ",problemId);	
	}
	public List<Long> getAllDisLikes(Long problemId)
	{
		return getHibernateTemplate().find("select count(model.problemLikesId) from ProblemLikes model where model.problem.problemId = ? and model.isLiked = '"+IConstants.FALSE+"' ",problemId);	
	}
	
}
