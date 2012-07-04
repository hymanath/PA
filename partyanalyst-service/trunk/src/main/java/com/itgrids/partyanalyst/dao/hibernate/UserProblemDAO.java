package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserProblemDAO extends GenericDaoHibernate<UserProblem,Long> implements IUserProblemDAO{

	public UserProblemDAO()
	{
		super(UserProblem.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllValidProblemIds(Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery("select model.userProblemId from UserProblem model where model.visibility.type = ? and model.isOwner = ? " +
				" and model.problem.isApproved = ?  and model.problem.isDelete = ? and model.problem.regionScopes.regionScopesId <= 5 ");
		 
		query.setParameter(0,IConstants.PUBLIC);
		query.setParameter(1,IConstants.TRUE);
		query.setParameter(2,IConstants.TRUE);
		query.setParameter(3,IConstants.FALSE);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Long> getAllValidProblemIdsCount()
	{
	return getHibernateTemplate().find("select count(model.userProblemId) from UserProblem model where model.visibility.type ='"+IConstants.PUBLIC+"' and model.isOwner ='"+IConstants.TRUE+"' and model.problem.isApproved ='"+IConstants.TRUE+"' " +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.problem.regionScopes.regionScopesId < 5");
	
	
}
	
	
	public List<Object[]> getProblemCompleteInfo(Long problemId)
	{
		return getHibernateTemplate().find("select model.problem.title,model.problem.description,model.problem.existingFrom,model.problem.identifiedOn,model.user.firstName,model.user.lastName,model.problem.regionScopes.regionScopesId,model.problem.impactLevelValue from UserProblem model where model.problem.problemId = ?",problemId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getAllProblemDetails(Long userProblemId)
	{
		return getHibernateTemplate().find("from UserProblem model where  model.userProblemId = ?",userProblemId);
	}
	
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemsByAnanymousUserId(Long userId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.problem.problemId, ");
		query.append(" model.problem.title,model.problem.description,model.problem.identifiedOn,model.problem.existingFrom, ");
		query.append(" model.problem.impactLevelValue,model.problem.regionScopes.regionScopesId,model.problem.regionScopes.scope, ");
		query.append(" model.problem.isApproved,model.userProblemId from UserProblem model where (model.problem.isDelete = 'false' or model.problem.isDelete is null) ");
		
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append(" and model.user.userId = ? and model.problem.isApproved = 'true'");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERS))
			query.append(" and  model.user.userId != ? and model.problem.isApproved = '"+IConstants.TRUE+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.TRUE+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.FALSE+"'");
		
		query.append(" order by "+columnName+" "+order);

		Query queryObject = getSession().createQuery(query.toString());

		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType))
			queryObject.setParameter(0, userId);
		
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(results);
		
		

		return queryObject.list();
		
	}
	
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long userId, String reasonType){

		StringBuilder query = new StringBuilder();
		query.append(" select count(*) from UserProblem model ");
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append("where model.user.userId is not null and model.problem.isApproved = '"+IConstants.TRUE+"' ");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append("where model.user.userId .userId = ? ");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
			query.append("where model.user.userId != ? and model.problem.isApproved = '"+IConstants.TRUE+"' ");
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.TRUE+"'");			
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.FALSE+"'");	
		
		query.append(" and (model.problem.isDelete = 'false' or model.problem.isDelete is null) ");
		Query queryObject = getSession().createQuery(query.toString());
		
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType))
			queryObject.setParameter(0, userId);
		
		return (Long)queryObject.uniqueResult();
		
	}
	
	
	public List getAllPostedProblemCount(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId),model.problem.isApproved from UserProblem model where model.user.userId = ? and (model.problem.isDelete ='false'or model.problem.isDelete is null) group by model.problem.isApproved",userId);
	}
	
	
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId) from UserProblem model where model.user.userId != ? and (model.problem.isDelete is null or model.problem.isDelete = 'false') and model.problem.isApproved = 'true' ",userId);
	}
	
	public String getCommonDataForAllProblems(){
		
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append("select model.problem.title,");
		conditionQuery.append(" model.problem.description,");
		conditionQuery.append(" model.problem.regionScopes.scope,");
		conditionQuery.append(" model.problem.impactLevelValue,");
		conditionQuery.append(" model.problem.identifiedOn,");
		conditionQuery.append(" model.user.firstName,");
		conditionQuery.append(" model.problem.problemId,");
		conditionQuery.append(" model.userProblemId,");
		conditionQuery.append(" model.problem.existingFrom,");
		conditionQuery.append(" model.problem.problemStatus.status,");
		conditionQuery.append(" model.problem.regionScopes.regionScopesId,");
		conditionQuery.append(" model.user.lastName,");
		conditionQuery.append(" model.problem.isApproved ");
		conditionQuery.append(" from UserProblem model");
		return conditionQuery.toString();
		
		}
	
		public List getAllProblemHistoryIdsForGivenLocationByTheirIds(List<Long> locationIds,String impactLevel,String isApproved){
		StringBuilder locationQuery = new StringBuilder();
		locationQuery.append(getCommonDataForAllProblems());
		locationQuery.append(" where model.problem.regionScopes.scope = ?");
		locationQuery.append(" and model.problem.isApproved = ? ");
		locationQuery.append(" and model.visibility.type = ? ");
		locationQuery.append(" and model.problem.impactLevelValue in(:locationIds)");
		locationQuery.append(" and model.user is not null ");
		
		locationQuery.append(" order by date(model.updatedTime)");
		Query queryObject = getSession().createQuery(locationQuery.toString());
		queryObject.setString(0,impactLevel);
		queryObject.setString(1, isApproved);
		queryObject.setString(2,IConstants.PUBLIC);
		queryObject.setParameterList("locationIds",locationIds);
		
		
		queryObject.setMaxResults(IConstants.MAX_PROBLEMS_DISPLAY.intValue());
		return queryObject.list();
		
		
		
	}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getStates()
		{
			return getHibernateTemplate().find("select distinct model.problem.problemCompleteLocation.state.stateId , model.problem.problemCompleteLocation.state.stateName from " +
					" UserProblem model where model.visibility.type = 'Public' and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
							" and model.problem.isApproved = 'true' and model.isOwner = 'true' order by model.problem.problemCompleteLocation.state.stateName");
		}
	
}