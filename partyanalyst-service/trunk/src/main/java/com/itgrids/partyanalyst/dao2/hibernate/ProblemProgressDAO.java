package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.model.ProblemAssignedDepartment;
import com.itgrids.partyanalyst.model.ProblemProgress;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemProgressDAO extends GenericDaoHibernate<ProblemProgress,Long> implements IProblemProgressDAO{

	public ProblemProgressDAO()
	{
		super(ProblemProgress.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProblemProgress>  getProblemPrograss(Long userProblemId)
	{
		return getHibernateTemplate().find("from ProblemProgress model where model.userProblem.userProblemId = ? ",userProblemId);
	}
	public List<ProblemProgress>  getProblemPrograssDetails(Long userProblemId)
	{
		Object[] parameters = {userProblemId,"false"};
		return getHibernateTemplate().find("from ProblemProgress model where model.userProblem.userProblemId = ? and model.isDelete =? order by model.insertedTime desc",parameters);
	}
	
	public List<ProblemProgress> getAllProblemProgressDetails(Long problemId,String visibility){
		StringBuilder query = new StringBuilder();
		query.append("from ProblemProgress model where model.userProblem.problem.problemId = :problemId  and model.isDelete = :isDelete ");
		if(visibility != null)
			query.append(" and model.visibility.type = :visibility and model.userProblem.visibility.type = :visibility ");	
		query.append(" order by model.insertedTime desc");
		
		Query queryObj = getSession().createQuery(query.toString());
		
		queryObj.setParameter("problemId", problemId);
		queryObj.setParameter("isDelete", IConstants.FALSE);
		if(visibility != null)
			queryObj.setParameter("visibility", visibility);
		return queryObj.list();
	}
	
	public int updateActivityVisibility(Long prblmPrgrssId,Long visibility)
	{
		Query queryObject=getSession().createQuery("update ProblemProgress model set model.visibility.visibilityId=? where model.problemProgressId=?");
		queryObject.setParameter(0,visibility);
		queryObject.setParameter(1,prblmPrgrssId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemProgress> getAllActivitesByProblemId(Long userProblemId)
	{
		return getHibernateTemplate().find("from ProblemProgress model where model.userProblem.userProblemId = ?  order by model.updatedTime desc",userProblemId);
				
	}

}