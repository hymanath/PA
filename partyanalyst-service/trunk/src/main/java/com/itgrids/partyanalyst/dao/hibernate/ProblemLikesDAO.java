package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	public List<ProblemLikes> checkIfUserAlreadyLikedOrDisliked(Long problemId, Long userId){
		Query queryObj=getSession().createQuery("select model from ProblemLikes model where model.problem.problemId=? and model.user.userId=?");
		queryObj.setParameter(0, problemId);
		queryObj.setParameter(1, userId);
		return queryObj.list();
	}	
	public Integer updateUserLikeOrDislike(Long userId, Long problemId, String isLiked){
		Query queryObj=getSession().createQuery("update ProblemLikes model set model.isLiked=? where model.problem.problemId=? and model.user.userId=?");
		queryObj.setParameter(0, isLiked);
		queryObj.setParameter(1, problemId);
		queryObj.setParameter(2, userId);
		return queryObj.executeUpdate();
	}
}
