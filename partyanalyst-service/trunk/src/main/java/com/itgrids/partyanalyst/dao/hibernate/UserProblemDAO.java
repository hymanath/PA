package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dao.ProblemSearchFilterOptionsVO;
import com.itgrids.partyanalyst.dto.ProblemSearchVO;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.Problem;
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
				" and model.problem.isApproved = ?  and model.problem.isDelete = ? and model.problem.regionScopes.regionScopesId <= 5 order by model.problem.problemId desc ");
		 
		query.setParameter(0,IConstants.PUBLIC);
		query.setParameter(1,IConstants.TRUE);
		query.setParameter(2,IConstants.TRUE);
		query.setParameter(3,IConstants.FALSE);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAllValidProblemIdsCount()
	{
	return getHibernateTemplate().find("select count(model.userProblemId) from UserProblem model where model.visibility.type ='"+IConstants.PUBLIC+"' and model.isOwner ='"+IConstants.TRUE+"' and model.problem.isApproved ='"+IConstants.TRUE+"' " +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.problem.regionScopes.regionScopesId < 5");
	
	
}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllProblemsOfCurrentDateByFreeUser(Date firstDate,Date lastDate,String isApproved, String problemStatus)
	{
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append("select model.problem.problemId,model.problem.description,model.problem.isApproved,model.problem.identifiedOn,model.user.firstName,model.user.lastName,model.problem.title ");
		conditionQuery.append(" from UserProblem model where  model.visibility.type =:status and model.problem.isDelete =:isDelete ");
		if(firstDate != null)
			conditionQuery.append(" and date(model.problem.insertedTime) >=:initialDate ");
		
		 if(lastDate != null)
			conditionQuery.append(" and date(model.problem.insertedTime) <=:endDate ");
		
		 if(isApproved != null)
			conditionQuery.append(" and model.problem.isApproved =:isApproved ");
		
		 if(problemStatus != null)
			 conditionQuery.append(" and model.problem.problemStatus.status =:problemStatus "); 
			 
		Query queryObject = getSession().createQuery(conditionQuery.toString());
		
		if(firstDate != null)
		    queryObject.setParameter("initialDate", firstDate);
		
	    if(lastDate != null)
		    queryObject.setParameter("endDate", lastDate);
		
		 if(isApproved != null)
		    queryObject.setParameter("isApproved", isApproved); 
		 
		 if(problemStatus != null)
			 queryObject.setParameter("problemStatus", problemStatus);
			 
		queryObject.setParameter("status", "Public");
		queryObject.setParameter("isDelete", "false");
		return queryObject.list();	
	}
	

	
	@SuppressWarnings("unchecked")
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
			String order, String columnName, String reasonType, List<Long> connectedUserIds)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.problem.problemId, ");
		query.append(" model.problem.title,model.problem.description,model.problem.identifiedOn,model.problem.existingFrom, ");
		query.append(" model.problem.impactLevelValue,model.problem.regionScopes.regionScopesId,model.problem.regionScopes.scope, ");
		query.append(" model.problem.isApproved,model.userProblemId,model.user.userId,model.user.profileImg,model.user.firstName,model.user.lastName from UserProblem model where (model.problem.isDelete = 'false' or model.problem.isDelete is null) and model.visibility.type = '"+IConstants.PUBLIC+"'");
		
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append(" and model.user.userId = ? and model.problem.isApproved = 'true'");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
			query.append(" and  model.user.userId != ? and model.problem.isApproved = '"+IConstants.TRUE+"' and model.user.userId not in(:connectedUserIds) ");
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.TRUE+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append(" and model.user.userId = ? and model.problem.isApproved = '"+IConstants.FALSE+"'");
		else if(reasonType.equalsIgnoreCase("ConnectedUserProblems"))
			query.append(" and model.user.userId in (:connectedUserIds) and model.problem.isApproved = '"+IConstants.TRUE+"'");
		
		query.append(" order by "+columnName+" "+order);

		Query queryObject = getSession().createQuery(query.toString());

		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType) && !reasonType.equalsIgnoreCase("ConnectedUserProblems"))
			queryObject.setParameter(0, userId);
		
		if(reasonType.equalsIgnoreCase("ConnectedUserProblems") || reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
			queryObject.setParameterList("connectedUserIds", connectedUserIds);
		
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(results);
		
		

		return queryObject.list();
		
	}
	@SuppressWarnings("unchecked")
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long userId, String reasonType, List<Long> conectedUserIds){

		StringBuilder query = new StringBuilder();
		query.append(" select count(*) from UserProblem model ");
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append("where model.user.userId is not null and model.problem.isApproved = '"+IConstants.TRUE+"' ");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append("where model.user.userId = ? ");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
		{
			query.append("where model.user.userId != ? and model.problem.isApproved = '"+IConstants.TRUE+"' ");
			if(conectedUserIds != null && conectedUserIds.size() > 0)
				query.append(" and model.user.userId not in (:conectedUserIds) ");
		}
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.TRUE+"'");			
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append("where model.user.userId = ? and model.problem.isApproved = '"+IConstants.FALSE+"'");
		else if(reasonType.equalsIgnoreCase("ConnectedUserProblems") && (conectedUserIds != null && conectedUserIds.size() > 0))
			query.append("where model.user.userId in (:conectedUserIds) and model.problem.isApproved = '"+IConstants.TRUE+"'");
		
		query.append(" and (model.problem.isDelete = 'false' or model.problem.isDelete is null) and model.visibility.type ='"+IConstants.PUBLIC+"'");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType) && !reasonType.equalsIgnoreCase("ConnectedUserProblems"))
			queryObject.setParameter(0, userId);
		if((conectedUserIds != null && conectedUserIds.size() > 0) && (reasonType.equalsIgnoreCase("ConnectedUserProblems") || reasonType.equalsIgnoreCase(IConstants.OTHERUSERS)))
			queryObject.setParameterList("conectedUserIds", conectedUserIds);
		return (Long)queryObject.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCount(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId),model.problem.isApproved from UserProblem model where model.user.userId = ? and model.visibility.type = '"+IConstants.PUBLIC+"' and (model.problem.isDelete ='false'or model.problem.isDelete is null) group by model.problem.isApproved",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId) from UserProblem model where model.user.userId != ? and model.visibility.type = '"+IConstants.PUBLIC+"' and (model.problem.isDelete is null or model.problem.isDelete = 'false') and model.problem.isApproved = 'true' ",userId);
	}
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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
	public Long getFreeUserIdOfAProblem(Long problemHistoryId)
	{
		Query query = getSession().createQuery("select model.user.userId from UserProblem model where model.userProblemId = ? and " +
					" model.visibility.type = '"+IConstants.PUBLIC+"' and (model.problem.isDelete is null or model.problem.isDelete = 'false')" +
					" and model.problem.isApproved = 'true' and model.isOwner = 'true'");
		query.setParameter(0,problemHistoryId);
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> checkUserFileUploadRight(Long userId,Long problemHistoryId){
		Object[] params = {userId,problemHistoryId};
		return getHibernateTemplate().find("select model.problem.problemId from UserProblem model where model.user.userId=? and model.problem.problemId=?",params);
		
	}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getStates()
		{
			return getHibernateTemplate().find("select distinct model.problem.problemCompleteLocation.state.stateId , model.problem.problemCompleteLocation.state.stateName from " +
					" UserProblem model where model.visibility.type = 'Public' and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
							" and model.problem.isApproved = 'true' and model.isOwner = 'true' order by model.problem.problemCompleteLocation.state.stateName");
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getUserProblemIdByUserIdAndProblemId(Long userId, Long problemId){
			Query query = getSession().createQuery("select model.userProblemId from UserProblem model where model.problem.problemId = ? and model.user.userId = ?");
			 
			query.setParameter(0,problemId);
			query.setParameter(1,userId);
			return query.list();
		}
		
		public List getProblemsCountPostedByUserInDifferentLifeCycleStages(
				Long userId) {
			
			return getHibernateTemplate().find("select count(model.userProblemId),model.problem.problemStatus.problemStatusId,"+
					"model.problem.problemStatus.status,model.problem.informationSource.informationSourceId,"+
					"model.problem.informationSource.informationSource,model.userProblemId from UserProblem model where "+
					"model.user.userId = "+userId+" and model.problem.problemStatus.status "+
					"in ("+IConstants.PROBLEMS_LIFE_CYCLE+") and (model.problem.isDelete is null or model.problem.isDelete = 'false') and (model.problem.isApproved = 'true') group by "+
					"model.problem.problemStatus.status,model.problem.informationSource.informationSource "+
					"order by model.problem.problemStatus.problemStatusId");
		}
		public List<UserProblem> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(Long userId,Integer startIndex, Integer maxResults) {
	
			Query queryObject = getSession().createQuery("select model from UserProblem model where " +
					"model.user.userId = :userId and  model.problem.isDelete = 'false' and (model.problem.isApproved = 'true') order by  model.problem.updatedTime desc");
			queryObject.setParameter("userId", userId);
			queryObject.setFirstResult(startIndex);
			queryObject.setMaxResults(maxResults);
			return queryObject.list();
		}
		public List<Long> getProblemsPostedByUserInDifferentLifeCycleStagesByDateCount(Long userId) {
			
			Query queryObject = getSession().createQuery("select count(model.problem.problemId) from UserProblem model where " +
					"model.user.userId = :userId and  model.problem.isDelete = 'false' and (model.problem.isApproved = 'true') ");
			queryObject.setParameter("userId", userId);
			return queryObject.list();
		}
		public List<Problem> getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(
				Long userId,Long statusId, Date startDate, Date endDate, Integer startIndex,
				Integer maxResults) {
			
			StringBuilder query = new StringBuilder();
			
			query.append("select model.problem from UserProblem model where model.user.userId = :userId ");
			//If problems by problem status 
			if(statusId != null && !statusId.equals(0L))
				query.append("and model.problem.problemStatus.problemStatusId = "+statusId+ " ");
			
			if(startDate != null && endDate != null){
				query.append("and date(model.problem.identifiedOn) >= :startDate and date(model.problem.identifiedOn) <= :endDate ");
				
			}
			else{
				if(startDate != null)
					query.append("and date(model.problem.identifiedOn) >= :startDate ");
							
				if(endDate != null)
					query.append("and date(model.problem.identifiedOn) <= :endDate ");
					
			}
			
			query.append("and  model.problem.isDelete = 'false' and model.problem.isApproved = 'true' ");
			query.append("order by date(model.problem.identifiedOn) desc");
			
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter("userId", userId);
			
			if(startDate != null && endDate != null){
				queryObject.setParameter("startDate", startDate);
				queryObject.setParameter("endDate", endDate);
			}else{
				if(startDate != null)
					queryObject.setParameter("startDate", startDate);
				if(endDate != null)
					queryObject.setParameter("endDate", endDate);				
			}
			queryObject.setFirstResult(startIndex);		
			queryObject.setMaxResults(maxResults);
					
	     return queryObject.list();
		}
		public List<Long> getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(Long userId,Long statusId,Date startDate,Date endDate){
			
			StringBuilder query = new StringBuilder();
					
			query.append("select count(model.problem.problemId) from UserProblem model where model.user.userId = :userId ");
			//If problems by problem status 
			if(statusId != null && !statusId.equals(0L))
				query.append("and model.problem.problemStatus.problemStatusId = "+statusId+ " ");
			
			if(startDate != null && endDate != null){
				query.append("and date(model.problem.identifiedOn) >= :startDate and date(model.problem.identifiedOn) <= :endDate ");
				
			}
			else{
				if(startDate != null)
					query.append("and date(model.problem.identifiedOn) >= :startDate ");
							
				if(endDate != null)
					query.append("and date(model.problem.identifiedOn) <= :endDate ");
					
			}
			
			query.append("and  model.problem.isDelete = 'false' and model.problem.isApproved = 'true' ");
			
			Query queryObject = getSession().createQuery(query.toString());
					
             queryObject.setParameter("userId", userId);
			
			if(startDate != null && endDate != null){
				queryObject.setParameter("startDate", startDate);
				queryObject.setParameter("endDate", endDate);
			}else{
				if(startDate != null)
					queryObject.setParameter("startDate", startDate);
				if(endDate != null)
					queryObject.setParameter("endDate", endDate);				
			}
			
							
	     return queryObject.list();
		}
		public List<Problem> getProblemHistoryBasedOnId(Long problemId,Long userId){
			
			Query queryObject = getSession().createQuery("select model.problem from UserProblem model where " +
					"model.user.userId = :userId and model.problem.problemId = :problemId and model.problem.isDelete = 'false' and model.problem.isApproved = 'true' ");
			queryObject.setParameter("userId", userId);
			queryObject.setParameter("problemId", problemId);
			return queryObject.list();
			
		}
		@SuppressWarnings("unchecked")
		
		public List<Object[]> getProblemPostedUserDetails()
		{
			return getHibernateTemplate().find("select distinct model.user.userId,model.user.firstName,model.user.lastName from UserProblem model where model.user.userId is not null and model.visibility.type = '"+IConstants.PUBLIC+"' " +
					"and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
					" and model.problem.isApproved = 'true' and model.isOwner = 'true' order by model.user.firstName");
		}
		
	
		@SuppressWarnings("unchecked")
		public List<Object[]> getFreeUserProblemsInSearch(ProblemSearchVO problemSearchVO,int startIndex,int maxIndex,boolean countReq)
		{
			StringBuilder query = new StringBuilder();
			query.append("select model.userProblemId,model.problem.title,model.problem.description,model.problem.existingFrom,model.problem.identifiedOn," +
					" model.problem.regionScopes.regionScopesId,model.problem.impactLevelValue,model.user.firstName,model.user.lastName,model.user.userId " +
					" from UserProblem model where model.user.userId is not null and model.visibility.type = '"+IConstants.PUBLIC+"' and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
					" and model.problem.isApproved = 'true' and model.isOwner = 'true'");
			if(!problemSearchVO.getScopeAll())
			{
				query.append(" and model.problem.problemCompleteLocation."); 
				if(problemSearchVO.getScopeId().equals(2l))
					query.append("state.stateId ");
				else if(problemSearchVO.getScopeId().equals(3l))
					query.append("district.districtId ");
				else if(problemSearchVO.getScopeId().equals(4l))
					query.append("constituency.constituencyId ");
				else if(problemSearchVO.getScopeId().equals(5L))
					query.append("tehsil.tehsilId ");
				else if(problemSearchVO.getScopeId().equals(6L))
					query.append("hamlet.hamletId ");
				else if(problemSearchVO.getScopeId().equals(7L))
					query.append("localElectionBody.localElectionBodyId ");
				else if(problemSearchVO.getScopeId().equals(8L))
					query.append("ward.constituencyId ");
				else if(problemSearchVO.getScopeId().equals(9L))
					query.append("booth.boothId ");
				
				query.append("  = :localtionValue ");
					
			}
			if(!problemSearchVO.getStatusAll())
				query.append(" and model.problem.problemStatus.problemStatusId = :problemStatusId");
			if(!problemSearchVO.getUsersAll())
				query.append(" and model.user.userId = :userId");
			if(!problemSearchVO.getTypeAll())
				query.append(" and model.problem.problemType.problemTypeId = :problemTypeId");
			Query queryObj = getSession().createQuery(query.toString());
			if(!problemSearchVO.getScopeAll())
				queryObj.setParameter("localtionValue", problemSearchVO.getLocationValue());
			if(!problemSearchVO.getStatusAll())
				queryObj.setParameter("problemStatusId", problemSearchVO.getStatusId());
			if(!problemSearchVO.getUsersAll())
				queryObj.setParameter("userId", problemSearchVO.getUserId());
			if(!problemSearchVO.getTypeAll())
				queryObj.setParameter("problemTypeId", problemSearchVO.getTypeId());
			 if(!countReq)
			   {
				queryObj.setFirstResult(startIndex);
				queryObj.setMaxResults(maxIndex);
			   }
			return queryObj.list();
		
		
						
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Object> getTotalProblemsCountForAnUserInARegion(Long userId,String locationStr)
		{
			Object[] params = {userId};
			return getHibernateTemplate().find("select model.problem.problemStatus.status from UserProblem model where " +
			"model.user.userId = ? "+locationStr+" and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) and model.problem.isApproved ='"+IConstants.TRUE+"'",params);
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Object> getTotalProblemsStatusForAnUser(Long userId)
		{
			Object[] params = {userId};
			return getHibernateTemplate().find("select model.problem.problemStatus.status from UserProblem model where "+
					"model.user.userId = ? and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) and model.problem.isApproved ='"+IConstants.TRUE+"'",params);
		}
		
	
		@SuppressWarnings("unchecked")
		public List<UserProblem> getStatusWiseProblemsForAnUserInARegion(Long userId,String locationStr,String statusStr)
		{
			Object[] params = {userId};
			return getHibernateTemplate().find(" from UserProblem model where " +
			" model.user.userId = ? "+locationStr+" "+statusStr+" and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) and model.problem.isApproved ='"+IConstants.TRUE+"'",params);
		}
		

		
		@SuppressWarnings("unchecked")
		public List<UserProblem> getStatusWiseProblemsForAnUser(Long userId,String statusStr)
		{
		Object[] params = {userId};
		return getHibernateTemplate().find("from UserProblem model where " +
		" model.user.userId = ? "+statusStr+" and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) and model.problem.isApproved ='"+IConstants.TRUE+"'",params);
		}
		
		
		
		public List getProblemsCountInAllStatusByLocation(Long userId) {
			return getHibernateTemplate().find("select model.problem.problemStatus.problemStatusId, model.problem.problemStatus.status," +
		"count(model.userProblemId) from UserProblem model where "+
					"model.user.userId = ? and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null)" +
		"group by model.problem.problemStatus.problemStatusId order by model.problem.problemStatus.problemStatusId",userId);
		}
		
		
		@SuppressWarnings("unchecked")
		public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(String tehsilIds, String statusIds)
		{
			return getHibernateTemplate().find("select count(model.userProblemId),model.updatedTime,model.problem.problemStatus.problemStatusId, " +
					"model.problem.problemStatus.status from UserProblem model where model.problem.problemCompleteLocation.hamlet.township.tehsil.tehsilId in " +
					"(" + tehsilIds +") and model.problem.problemStatus.problemStatusId in("+statusIds+") " +
					" group by date(model.updatedTime),model.problem.problemStatus.problemStatusId order by model.updatedTime desc");
					
		}
		
		
		
		public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(Long userId, String statusIds){
			
			return getHibernateTemplate().find("select  count(model.userProblemId),model.updatedTime,model.problem.problemStatus.problemStatusId, " +
					"model.problem.problemStatus.status from UserProblem model where model.user.userId = ? " +
					"and model.problem.problemStatus.problemStatusId in("+statusIds+") " +
					" group by date(model.updatedTime),model.problem.problemStatus.problemStatusId order by model.updatedTime desc",userId);
			
					
		
		}
		
		@SuppressWarnings("unchecked")
		public List findLatestProblemsByMandals(String tehsilIds, Long statusId){		
			return getHibernateTemplate().find("select model.problem.problemId, " +
					"model.problem.title,"+
					"model.problem.description,"+
					"model.problem.problemCompleteLocation.hamlet.hamletName," +
					" model.problem.informationSource.informationSource," +
					
					"model.problem.problemStatus.status," +
					"model.problem.identifiedOn"+
					" from UserProblem model where model.problem.problemCompleteLocation.hamlet.township.tehsil.tehsilId in " +
					"(" + tehsilIds +") and model.problem.problemStatus.problemStatusId = ?" +
							" and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) order by model.updatedTime desc", statusId);
		}
		@SuppressWarnings("unchecked")
		public String buildCommonQueryForProblems(){		
			StringBuilder query = new StringBuilder();
			query.append(" select model.problem.problemStatus.status," );
			query.append(" model.problem.regionScopes.regionScopesId,");
			query.append(" model.problem.identifiedOn,");
			query.append(" model.problem.title,");
			query.append(" model.problem.existingFrom,");
			query.append(" model.userProblemId,model.problem.externalSource.problemExternalSourceId,"); 
			query.append(" model.problem.description,model.updatedTime,model.problem.problemId,");
			query.append(" model.user.userId,model.problem.impactLevelValue");
			
			query.append(" from UserProblem model ");
			return query.toString();		
		}
		
		@SuppressWarnings("unchecked")
		public List findProblemsByStatusForALocationsByConstituencyId(Long userId, Long status) {
			Object[] params = {userId,status};
			String query = buildCommonQueryForProblems();
			StringBuilder conditionQuery = new StringBuilder();		
			conditionQuery.append(query);
			conditionQuery.append(" where model.user.userId = ? and model.problem.problemStatus.problemStatusId = ? and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null)");
			return getHibernateTemplate().find(conditionQuery.toString(),params);
		}
		
		@SuppressWarnings("unchecked")
		public List findProblemsForALocationsByConstituencyId(Long userId) {
			Object[] params = {userId};
			String query = buildCommonQueryForProblems();
			
			StringBuilder conditionQuery = new StringBuilder();		
			conditionQuery.append(query);
			conditionQuery.append(" where model.user.userId = ? and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null)");
			
			return getHibernateTemplate().find(conditionQuery.toString());
		}
		
		@SuppressWarnings("unchecked")
		public List<UserProblem> getUserProblem(Long problemId, Long userId)
		{
			Object[] params = {problemId,userId};
			
			StringBuilder conditionQuery = new StringBuilder();		
			
			conditionQuery.append(" from UserProblem model where  model.problem.problemId = ? and model.user.userId =?");
			return getHibernateTemplate().find(conditionQuery.toString(),params);
			
		}
		
		@SuppressWarnings("unchecked")
		public List findProblemsByDateAndLocation(Long userId, Date fromDate, Date toDate){
			Object[] params = {fromDate, toDate,userId};
			return getHibernateTemplate().find("select model.problem.impactLevelValue," +
					" model.problem.title,model.problem.description,model.problem.regionScopes.regionScopesId," +
					" model.problem.informationSource.informationSource,model.problem.problemStatus.status," +
					" model.problem.identifiedOn,model.problem.problemId from UserProblem model where date(model.updatedTime) >= ? and date(model.updatedTime) <= ? and " +
					" model.user.userId = ? and (model.problem.isDelete is null or model.problem.isDelete = '"+IConstants.FALSE+"')",params);
		}
		
		
		@SuppressWarnings("unchecked")
		public List findProblemsByStatusDateAndLocation(Long userId, Long statusId, Date fromDate, Date toDate){
			Object[] params = {fromDate,toDate,userId,statusId};
			return getHibernateTemplate().find("select model.problem.impactLevelValue," +
					" model.problem.title,model.problem.description,model.problem.regionScopes.regionScopesId," +
					" model.problem.informationSource.informationSource,model.problem.problemStatus.status," +
					" model.problem.existingFrom,model.problem.problemId from UserProblem model where date(model.updatedTime) >= ? and date(model.updatedTime) <= ? and " +
					" model.user.userId = ? and model.problem.problemStatus.problemStatusId = ? and (model.problem.isDelete is null or model.problem.isDelete = '"+IConstants.FALSE+"')",params);
					
		}
		
		@SuppressWarnings("unchecked")
		public List<UserProblem> findProblemDetailsByProblemId(Long problemId) {
			Object [] params = {problemId};
			return getHibernateTemplate().find("from UserProblem model where model.problem.problemId = ?" +
					" and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"')",params );
		}
		//to get cadreproblems
		@SuppressWarnings("unchecked")
			public List<UserProblem> getCadreProblemsInARegionByUserProblemId(Long userId,Long problemId)
			{
			Object [] params = {userId,problemId};
			return getHibernateTemplate().find("from UserProblem model where model.user.userId =? and model.problem.problemId =?  and (model.problem.isDelete is null or model.problem.isDelete='"+IConstants.FALSE+"') and model.problem.isApproved = 'true'",params);
			
			
			}
		
		/*@SuppressWarnings("unchecked")
		public List<Object> getTotalProblemsCountForAnUserInARegion(Long userId, String locationStr)
		{
			Object[] params = {userId};
			return getHibernateTemplate().find(" select model.problem.problemStatus.status from UserProblem model where " +
					" model.user.userId = ? "+locationStr+" and (model.problem.isDelete is null or model.problem.isDelete is 'false') and model.problem.isApproved = 'true' ",params);
		}*/
		
		@SuppressWarnings("unchecked")
		public List<UserProblem> getUserProblemIdForCadreProblems(Long problemId,Long userId)
		{
			StringBuffer query=new StringBuffer("select model from UserProblem model where model.problem.problemId =:problemId and model.user.userId =:userId");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter("problemId",problemId);
			queryObject.setParameter("userId",userId);
			
			return queryObject.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<UserProblem> getProblemDetailsOfUserToSendEmailAfterProblemApproval(Long problemId)
		{
		Query query = getSession().createQuery("from UserProblem model where model.problem.problemId = ?");
			query.setParameter(0, problemId);
		
		return query.list();
		}
 		
	       public List<String> checkIsProblemOwner(Long problemId,Long userId){
	    	   Object [] params = {problemId,userId};
	    	   return getHibernateTemplate().find("select model.isOwner from UserProblem model where model.problem.problemId = ?" +
						" and model.user.userId = ?",params );
	       }
	       public List<Object[]> getProblemOwnerName(Long problemId){
	    	   Object [] params = {problemId,IConstants.TRUE};
	    	   return getHibernateTemplate().find("select model.user.firstName,model.user.lastName from UserProblem model where model.problem.problemId = ?" +
						" and model.isOwner = ?",params );
	       }
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getProblemDetailsByProblemReferenceNo(String problemReferenceNo)
		{
			Query query = getSession().createQuery("select model.problem.problemId, model.problem.title, model.user.userId," +
					"model.problem.isApproved from UserProblem model where model.problem.referenceNo= ? and " +
					"(model.problem.isDelete is null or model.problem.isDelete = 'false') " +
					"and model.user.userId is not null and model.visibility.type='Public'");
			query.setParameter(0, problemReferenceNo);
			return query.list();
		}
		
		public int makeProblemPublic(Long problemId,Long visibility)
		{
			Query queryObject=getSession().createQuery("update UserProblem model set model.visibility.visibilityId=? where model.problem.problemId=?");
			queryObject.setParameter(0,visibility);
			queryObject.setParameter(1,problemId);
			return queryObject.executeUpdate();
		}
       public List<Long> checkIsPublicProblem(Long problemId){
    	   Object [] params = {problemId,new Long(1),IConstants.FALSE,IConstants.TRUE,IConstants.TRUE};
    	   return getHibernateTemplate().find("select count(model.problem.problemId) from UserProblem model where model.problem.problemId = ?" +
					" and model.visibility.visibilityId = ? and model.problem.isDelete =? and model.problem.isApproved =? and model.isOwner = ?",params);
       }
       public List<UserProblem> getProblemAndOwnerDetails(Long problemId){
    	   Object [] params = {problemId,IConstants.TRUE};
    	   return getHibernateTemplate().find(" from UserProblem model where model.problem.problemId = ? and model.isOwner = ? ",params);
       }
       public List<Long> checkIsTakenUpProblem(Long problemId,Long userId){
    	   Object [] params = {problemId,IConstants.FALSE,userId};
    	   return getHibernateTemplate().find(" select count(model.problem.problemId) from UserProblem model where model.problem.problemId = ? and model.isOwner = ? " +
    	   		" and model.user.userId = ?",params);
       }
       public List<Long> checkIsTakenUpProblemIsInPublicVisiblty(Long problemId){
    	   Object [] params = {problemId,IConstants.TRUE,IConstants.PUBLIC};
    	   return getHibernateTemplate().find(" select count(model.problem.problemId) from UserProblem model where model.problem.problemId = ? and model.isOwner = ? " +
    	   		" and model.visibility.type = ?",params);
       }
       
       public List<Long> getUserIds(Long problemId){
    	   Object [] params = {problemId,2l};
   		return getHibernateTemplate().find("select model.user.userId from UserProblem model where model.problem.problemId = ? and  model.visibility.visibilityId = ?",params);
   	  }
     
       public List<Problem> getUserProblemsByProblemTyp(Long problemId ,String problemTyp){
			
			
			Query query = getSession().createQuery("select model.problem from UserProblem model"+
					" where model.problem.problemType.problemType = ? and model.problem.problemId != ? and model.problem.isDelete = ? and model.visibility.visibilityId = ?");
			
			
			query.setParameter(0, problemTyp);
			query.setParameter(1, problemId);
			query.setParameter(2, IConstants.FALSE);
			query.setParameter(3, 1L);
			
			query.setFirstResult(0);
			query.setMaxResults(IConstants.MAX_PROBLES);
			
			return query.list();
			
		}
       public List<Problem> geUserProblemsByConstituency(List<Long> problemIds,Long cnstncyId,Long userId){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select model.problem from UserProblem model" +
    				" where model.problem.problemCompleteLocation.constituency.constituencyId = :cnstncyId " +
    				" and model.problem.problemId not in (:problemIds) and model.problem.isDelete = :isDelete  ");		
    		if(userId != null)
    			queryString.append(" and ( model.user.userId = :userId or  model.visibility.visibilityId = :visibilityId )  order by model.problem.insertedTime desc ");
    		else
    			queryString.append(" and model.visibility.visibilityId = :visibilityId order by model.problem.insertedTime desc");
    		Query query = getSession().createQuery(queryString.toString());
    		query.setParameter("cnstncyId", cnstncyId);
    		query.setParameterList("problemIds", problemIds);
    		query.setParameter("isDelete", IConstants.FALSE);
    		if(userId != null)
    			query.setParameter("userId", userId);
    		query.setParameter("visibilityId", 1L);
    		query.setFirstResult(0);
    		query.setMaxResults(IConstants.MAX_PROBLES);
    		
    		return query.list();
    		
    		
    	}
       public List<Problem> getProblemsByDistrictId(List<Long> problemIds,Long districtId,Long userId){
   		
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select model.problem from  UserProblem model where model.problem.problemCompleteLocation.district.districtId = :districtId " +
   				" and model.problem.problemId not in (:problemIds) and model.problem.isDelete = :isDelete   ");
   		
   		if(userId != null)
			queryString.append(" and ( model.user.userId = :userId or  model.visibility.visibilityId = :visibilityId )  order by model.problem.insertedTime desc ");
		else
			queryString.append(" and model.visibility.visibilityId = :visibilityId order by model.problem.insertedTime desc");
   		Query query = getSession().createQuery(queryString.toString());
   		query.setParameter("districtId",districtId);
   		query.setParameterList("problemIds", problemIds);
   		query.setParameter("isDelete", IConstants.FALSE);
   		if(userId != null)
			query.setParameter("userId", userId);
		query.setParameter("visibilityId", 1L);
   		query.setFirstResult(0);
   		query.setMaxResults(IConstants.MAX_PROBLES);
   		
   		return query.list();  		
      }
       public List<Problem> getProblemsByUserId(List<Long> problemIds,Long userId){
      		StringBuilder queryString = new StringBuilder();
      		queryString.append("select model.problem from  UserProblem model where model.problem.problemId not in (:problemIds) and model.problem.isDelete = :isDelete ");
      		if(userId != null)
      			queryString.append(" and  model.user.userId = :userId ");
      		else
      			queryString.append(" and  model.visibility.visibilityId = :visibilityId ");
      		queryString.append(" order by model.problem.insertedTime desc ");
      		Query query = getSession().createQuery(queryString.toString());
      		
      		if(userId != null)
      		  query.setParameter("userId",userId);
      		else
      		  query.setParameter("visibilityId", 1L);
      		query.setParameterList("problemIds", problemIds);
      		query.setParameter("isDelete", IConstants.FALSE);

      		query.setFirstResult(0);
      		query.setMaxResults(IConstants.MAX_PROBLES);
      		
      		return query.list();  		
      	}
       public List<UserProblem> getUserProblemByUserAndProblemId(Long problemId,Long userId){
      	 Object [] params = {problemId,userId,IConstants.TRUE,IConstants.FALSE};
      	return getHibernateTemplate().find("from UserProblem model where model.problem.problemId = ? and model.user.userId = ? and model.problem.isApproved =? and model.problem.isDelete =? ",params);
      }
       
       /*@SuppressWarnings("unchecked")
   	public List<Object[]> getProblemDeatilsByProblemId(Long problemId)
       {
       	return getHibernateTemplate().find("select model.problem.title,model.problem.description,model.problem.existingFrom,model.problem.problemType.problemTypeId,model.problem.problemType.problemType from UserProblem model where model.problem.problemId = ? ",problemId);
       }*/
   	@SuppressWarnings("unchecked")
   	public List<UserProblem> getProblemDeatilsByProblemId(Long problemId)
       {
       	return getHibernateTemplate().find("from UserProblem model where model.problem.problemId = ? and model.problem.isApproved ='true' and model.problem.isDelete ='false'",problemId);
       }
   	
   	public List<UserProblem> getUserProblemId(Long userId,Long problemId)
   	{
   		 Object [] params = {problemId,userId};
   		 
   		return getHibernateTemplate().find("from UserProblem model where model.problem.problemId = ? and model.user.userId =? and model.problem.isDelete ='false'",params);
   	}
   	
       public List<Object[]> getAllProblemPostedUserDetails(Long userId)
		{
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select distinct model.user.userId,model.user.firstName,model.user.lastName from UserProblem model where model.user.userId is not null and (model.visibility.type = '"+IConstants.PUBLIC+"' ");
    	   if(userId != null)
    	   queryString.append(" or model.user.userId =:userId ");	
    	   queryString.append(") and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
					" and model.problem.isApproved = 'true' and model.isOwner = 'true' order by model.user.firstName");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   
    	   if(userId != null)
       		  query.setParameter("userId",userId);
    	   
    	   return query.list(); 
		}
       
       public List<Object[]> getAllProblemContainStates(List<Long> problemIds){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.state.stateId,model.problem.problemCompleteLocation.state.stateName,count(model.problem.problemCompleteLocation.state.stateId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' group by model.problem.problemCompleteLocation.state.stateId order by model.problem.problemCompleteLocation.state.stateName ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainDistricts(Long stateId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.district.districtId,model.problem.problemCompleteLocation.district.districtName,count(model.problem.problemCompleteLocation.district.districtId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.state.stateId = "+stateId+" and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.district.districtId order by model.problem.problemCompleteLocation.district.districtName ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainConstituencies(Long districtId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.constituency.constituencyId,model.problem.problemCompleteLocation.constituency.name,count(model.problem.problemCompleteLocation.constituency.constituencyId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.district.districtId = "+districtId+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.constituency.constituencyId order by model.problem.problemCompleteLocation.constituency.name ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainMandals(Long constituencyId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.tehsil.tehsilId,model.problem.problemCompleteLocation.tehsil.tehsilName,count(model.problem.problemCompleteLocation.tehsil.tehsilId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.constituency.constituencyId = "+constituencyId+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.tehsil.tehsilId order by model.problem.problemCompleteLocation.tehsil.tehsilName ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainVillages(Long mandalId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.hamlet.hamletId,model.problem.problemCompleteLocation.hamlet.hamletName,count(model.problem.problemCompleteLocation.hamlet.hamletId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.tehsil.tehsilId = "+mandalId+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.hamlet.hamletId order by model.problem.problemCompleteLocation.hamlet.hamletName ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainBooths(Long mandalId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.booth.boothId,model.problem.problemCompleteLocation.booth.partNo,count(model.problem.problemCompleteLocation.booth.boothId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.tehsil.tehsilId = "+mandalId+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.booth.boothId order by model.problem.problemCompleteLocation.booth.partNo ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       public List<Object[]> getAllProblemContainMuncpCorpGmc(Long constituencyId,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId,model.problem.problemCompleteLocation.localElectionBody.name,count(model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId),model.problem.problemCompleteLocation.localElectionBody.electionType.electionType from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.constituency.constituencyId = "+constituencyId+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId order by model.problem.problemCompleteLocation.localElectionBody.name ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       
       public List<Object[]> getAllProblemContainWards(Long localElection,List<Long> problemIds,String locationscop){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  model.problem.problemCompleteLocation.ward.constituencyId,model.problem.problemCompleteLocation.ward.name,count(model.problem.problemCompleteLocation.ward.constituencyId) from UserProblem model where  ");
    	   
    	   queryString.append(" model.problem.problemId in (:problemIds) and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId = "+localElection+"  and  model.problem.isDelete = 'false' " +
					" and model.problem.isApproved = 'true' "+locationscop+" group by model.problem.problemCompleteLocation.ward.constituencyId order by model.problem.problemCompleteLocation.ward.name ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   query.setParameterList("problemIds", problemIds);
    	   
    	   return query.list(); 
       }
       public Long getRegionWideProbCount(Long stateId,String locationString,List<Long> problemIds){
    	   Query query = getSession().createQuery("select count(distinct model.problem.problemId) from  UserProblem model where model.problem.problemId in (:problemIds) "+locationString+" and model.problem.problemCompleteLocation.state.stateId = "+stateId+"  ");
    	   query.setParameterList("problemIds", problemIds);
    	   return (Long)query.uniqueResult();
       }
       public List<Long> getAllProblemCount(Long userId,Long stateId,String location){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select  count(*) from UserProblem model where model.user.userId is not null and (model.visibility.type = '"+IConstants.PUBLIC+"' ");
    	   if(userId != null)
    	   queryString.append(" or model.user.userId =:userId ");	
    	   queryString.append(") and (model.problem.isDelete is null or model.problem.isDelete = 'false') " +
					" and model.problem.isApproved = 'true' and model.isOwner = 'true' "+location+" and model.problem.problemCompleteLocation.state.stateId = "+stateId+"");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   
    	   if(userId != null)
       		  query.setParameter("userId",userId);
    	   
    	   return query.list(); 
       }
       
       public List<Long> getCadrePostedProblems(Long userId,boolean isOnlyUserProb){
    	   StringBuilder queryString = new StringBuilder();
    	   queryString.append("select distinct model.problem.problemId from UserProblem model where model.problem.isDelete = :isDelete and model.problem.isApproved =:isApproved and model.problem.informationSource.informationSourceId = 4 ");
    	   
    	   if(isOnlyUserProb && userId != null){
    		   queryString.append(" and (model.user.userId =:userId ");
    		   
    	   }else{
    		   queryString.append("and (model.visibility.type = '"+IConstants.PUBLIC+"' ");
    	   if(userId != null)
        	   queryString.append(" or model.user.userId =:userId ");
    	   }
    	   queryString.append(" )");
            Query query = getSession().createQuery(queryString.toString());
    	   
            query.setParameter("isDelete",IConstants.FALSE);
            query.setParameter("isApproved",IConstants.TRUE);
    	   if(userId != null)
       		  query.setParameter("userId",userId);
    	   
    	   return query.list(); 
       }
       
       public List<Problem> getAllProblemsByFilterOptions(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
    	   return getQueryForProblemsByFilterOptions(problemSearchFilterOptionsVO,"records").list();
       }
       
       public List<Long> getAllProblemsByFilterOptionsCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
    	   return getQueryForProblemsByFilterOptions(problemSearchFilterOptionsVO,"count").list();
       }
       
       public List<Object[]> getAllProblemsByFilterOptionsStatusCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
    	   return getQueryForProblemsByFilterOptions(problemSearchFilterOptionsVO,"statuscount").list();
       }
       
       public Query getQueryForProblemsByFilterOptions(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO,String queryType){
    	   boolean isMyPrivateProb   = problemSearchFilterOptionsVO.isMyPrivateProb();
    	   boolean isMyPublicProb    = problemSearchFilterOptionsVO.isMyPublicProb();
    	   boolean isTakenUpProb     = problemSearchFilterOptionsVO.isTakenUpProb();
    	   boolean isCommentByMeProb = problemSearchFilterOptionsVO.isCommentByMeProb();
    	   boolean isAllPublicProb   = problemSearchFilterOptionsVO.isAllPublicProb();
    	   boolean isPostedByMeProb  = problemSearchFilterOptionsVO.isPostedByMeProb();
    	   StringBuilder queryString = new StringBuilder();
    	   if(queryType.equalsIgnoreCase("statuscount"))
    		   queryString.append("select model.problem.problemStatus.status,count(distinct model.problem.problemId) from UserProblem model where model.problem.isDelete = :isDelete and model.problem.isApproved =:isApproved  ");
    	   else if(queryType.equalsIgnoreCase("count"))
    	     queryString.append("select count(distinct model.problem.problemId) from UserProblem model where model.problem.isDelete = :isDelete and model.problem.isApproved =:isApproved  ");
    	   else
    		   queryString.append("select distinct model.problem from UserProblem model where model.problem.isDelete = :isDelete and model.problem.isApproved =:isApproved  ");  
    	   if(problemSearchFilterOptionsVO.getLocationId() != null && problemSearchFilterOptionsVO.getLocationValue() != null && problemSearchFilterOptionsVO.getLocationString() != null)
    		   queryString.append(problemSearchFilterOptionsVO.getLocationString());
    	   if(problemSearchFilterOptionsVO.getStatusId() != null)
    		   queryString.append(" and model.problem.problemStatus.problemStatusId =:statusId ");
    	   if(problemSearchFilterOptionsVO.getProblemTypeId() != null)
    		   queryString.append(" and model.problem.problemType.problemTypeId =:problemTypeId ");    	   
    	   if(problemSearchFilterOptionsVO.isCadreReq())
    		   queryString.append(" and model.problem.problemId in( :cadreProblemIds) ");
    	   if(problemSearchFilterOptionsVO.getDepartmentId() != null)
    		   queryString.append(" and model.problem.problemId in( :departmntProblemIds ) ");
    	   if(isMyPrivateProb  || isMyPublicProb || isTakenUpProb || isCommentByMeProb || isAllPublicProb || isPostedByMeProb)
    		   queryString.append(" and model.problem.problemId in( :initialConditionsIds ) "); 
    	   if(problemSearchFilterOptionsVO.getSelectedUserid() != null && problemSearchFilterOptionsVO.isOnlyUserProb() != true)
    		   queryString.append(" and model.user.userId = :selectedUserid and model.isOwner = :isOwner ");
    	   if(problemSearchFilterOptionsVO.getFromDate() != null)
    		   queryString.append(" and date(model.problem.identifiedOn) >= :fromDate ");
    	   if(problemSearchFilterOptionsVO.getToDate() != null)
    		   queryString.append(" and date(model.problem.identifiedOn) <= :toDate ");
    	   if(problemSearchFilterOptionsVO.isOnlyUserProb() && problemSearchFilterOptionsVO.getUserId() != null){
    		   queryString.append(" and (model.user.userId =:userId ");
    		   
    	   }else{
    		   queryString.append(" and (model.visibility.type = '"+IConstants.PUBLIC+"' ");
    	   if(problemSearchFilterOptionsVO.getUserId() != null)
        	   queryString.append(" or model.user.userId =:userId ");
    	   }
    	   queryString.append(" ) ");
    	   if(queryType.equalsIgnoreCase("statuscount")){
    		   
    		   queryString.append(" group by model.problem.problemStatus.problemStatusId");
    	   }
    	   if(queryType.equalsIgnoreCase("records"))
    	   queryString.append("  order by model.problem.updatedTime desc ");
    	   
    	   Query query = getSession().createQuery(queryString.toString());
    	   
    	   query.setParameter("isDelete",IConstants.FALSE);
           query.setParameter("isApproved",IConstants.TRUE);
           if(problemSearchFilterOptionsVO.getStatusId() != null)
    		   query.setLong("statusId",problemSearchFilterOptionsVO.getStatusId());
    	   if(problemSearchFilterOptionsVO.getProblemTypeId() != null)
    		   query.setLong("problemTypeId",problemSearchFilterOptionsVO.getProblemTypeId());    	   
    	   if(problemSearchFilterOptionsVO.isCadreReq())
    		   query.setParameterList("cadreProblemIds",problemSearchFilterOptionsVO.getCadreProblemIds());
    	   if(problemSearchFilterOptionsVO.getDepartmentId() != null)
    		   query.setParameterList("departmntProblemIds",problemSearchFilterOptionsVO.getDepartmntProblemIds());
    	   if(isMyPrivateProb  || isMyPublicProb || isTakenUpProb || isCommentByMeProb || isAllPublicProb || isPostedByMeProb)
    		   query.setParameterList("initialConditionsIds",problemSearchFilterOptionsVO.getInitialConditionsIds());
    	   if(problemSearchFilterOptionsVO.getSelectedUserid() != null  && problemSearchFilterOptionsVO.isOnlyUserProb() != true){
    		   query.setLong("selectedUserid",problemSearchFilterOptionsVO.getSelectedUserid());
    		   query.setString("isOwner", IConstants.TRUE);
    	   }
    	   if(problemSearchFilterOptionsVO.getFromDate() != null)
    		   query.setDate("fromDate",problemSearchFilterOptionsVO.getFromDate());
    	   if(problemSearchFilterOptionsVO.getToDate() != null)
    		   query.setDate("toDate",problemSearchFilterOptionsVO.getToDate());
    	   if(problemSearchFilterOptionsVO.getUserId() != null)
        		  query.setParameter("userId",problemSearchFilterOptionsVO.getUserId());
    	   if(queryType.equalsIgnoreCase("records")){
    	    query.setFirstResult(problemSearchFilterOptionsVO.getFirstResult());
    	    query.setMaxResults(problemSearchFilterOptionsVO.getMaxResult());
    	   }
    	   
    	   return query; 
       }
       
       public List<Long> getMyProblemsCount(String query){
  
    	   return getHibernateTemplate().find("select count(distinct model.problem.problemId) from UserProblem model "+query+" and model.problem.isDelete = '"+IConstants.FALSE+"' and model.problem.isApproved = '"+IConstants.TRUE+"' ");
    	    
       }
       
       public List<Long> getMyProblems(String query){
    	   
    	   return getHibernateTemplate().find("select distinct model.problem.problemId from UserProblem model "+query+" and model.problem.isDelete = '"+IConstants.FALSE+"' and model.problem.isApproved = '"+IConstants.TRUE+"' ");
    	    
       }
       
       public Long getCommentedByMeProbCount(Long userId,List<Long> problemIds){
    	   Query query = getSession().createQuery("select count(distinct model.problem.problemId) from UserProblem model where  model.problem.problemId in (:problemIds ) and ( (model.user.userId = :userId  and model.isOwner = :isOwner) or (model.isOwner = :isOwner and  model.visibility.visibilityId = :visibilityId )) " +
    	   		" and model.problem.isDelete = '"+IConstants.FALSE+"' and model.problem.isApproved = '"+IConstants.TRUE+"' ");
    	   query.setParameterList("problemIds", problemIds);
    	   query.setParameter("userId", userId);
    	   query.setParameter("isOwner", IConstants.TRUE);
    	   query.setParameter("visibilityId", 1l);
    	   return (Long)query.uniqueResult();
       }
       
       public List<Long> getCommentedByMeProblemIds(Long userId,List<Long> problemIds){
    	   Query query = getSession().createQuery("select distinct model.problem.problemId from UserProblem model where  model.problem.problemId in (:problemIds ) and ( (model.user.userId = :userId  and model.isOwner = :isOwner) or (model.isOwner = :isOwner and  model.visibility.visibilityId = :visibilityId )) " +
    	   		" and model.problem.isDelete = '"+IConstants.FALSE+"' and model.problem.isApproved = '"+IConstants.TRUE+"' ");
    	   query.setParameterList("problemIds", problemIds);
    	   query.setParameter("userId", userId);
    	   query.setParameter("isOwner", IConstants.TRUE);
    	   query.setParameter("visibilityId", 1l);
    	   return query.list();
       }
       
    @SuppressWarnings("rawtypes")
	public List getConnectedUsersProblemCount(List<Long> connectedUserIds)
   	{
   		StringBuilder queryString = new StringBuilder();
   		queryString.append("select count(distinct model.problem.problemId),model.problem.isApproved from UserProblem model where model.user.userId in (:connectedUserIds) ");
   		queryString.append(" and model.visibility.type = '"+IConstants.PUBLIC+"' and (model.problem.isDelete ='false'or model.problem.isDelete is null) group by model.problem.isApproved");
   		Query queryObj = getSession().createQuery(queryString.toString());
   		queryObj.setParameterList("connectedUserIds", connectedUserIds);
   		return queryObj.list();
   		
   	}
    
    @SuppressWarnings("unchecked")
	public List<Object[]> getProblemDetailsForPublicProfile(Long userId,int startIndex, int maxIndex)
    {
		StringBuilder query = new StringBuilder();
		query.append(" select model.problem.problemId, ");
		query.append(" model.problem.title,model.problem.description,model.problem.identifiedOn,model.problem.existingFrom, ");
		query.append(" model.problem.impactLevelValue,model.problem.regionScopes.regionScopesId,model.problem.regionScopes.scope, ");
		query.append(" model.problem.isApproved,model.userProblemId,model.user.userId,model.user.profileImg,model.user.firstName,model.user.lastName  from UserProblem model where (model.problem.isDelete = 'false' or model.problem.isDelete is null) and model.visibility.type = '"+IConstants.PUBLIC+"'");
		query.append(" and model.user.userId = ? and model.problem.isApproved = 'true' and (model.problem.isDelete = 'false' or model.problem.isDelete is null) and model.visibility.type = '"+IConstants.PUBLIC+"' ORDER BY model.updatedTime");	
		
    	Query queryObj = getSession().createQuery(query.toString());
    	queryObj.setParameter(0, userId);
    	queryObj.setFirstResult(startIndex);
    	queryObj.setMaxResults(maxIndex);
    	return queryObj.list();
    }
	@SuppressWarnings("unchecked")
	public List<Long> getAllProblemsByLocation(Long userId,Long locationId,Long locationValue,String status)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.userProblemId) from UserProblem model where (model.user.userId = :userId or model.visibility.type = :visibilityType) and model.problem.isApproved ='"+IConstants.TRUE+"'" +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.problem.problemStatus.status = :status ");
		if(locationId ==4)
			str.append(" and model.problem.problemCompleteLocation.constituency.constituencyId = :locationValue ");	
		else if(locationId == 5)
		str.append(" and model.problem.problemCompleteLocation.tehsil.tehsilId = :locationValue ");
		else if(locationId == 8)
			str.append(" and model.problem.problemCompleteLocation.ward.constituencyId =:locationValue ");
		
		else if(locationId == 7)
			str.append(" and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId =:locationValue ");
		
		// I Had changed For getting Hamlet Problems
		
		else if(locationId == 10)
			str.append(" and model.problem.problemCompleteLocation.hamlet.hamletId =:locationValue ");
		//End
		
			str.append(" order by model.problem.problemId desc ");	
		
			
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationValue", locationValue);
		//query.setParameter("locationId", locationId);
		query.setParameter("status", status);
		query.setParameter("userId",userId);
		query.setParameter("visibilityType",IConstants.PUBLIC);
		
		return query.list();
		
	}
	
	/*@SuppressWarnings("unchecked")
	public Long getAllPrivateProblemsByLocation(Long locationValue,Long userId,Long locationId,String status)
	{
		
		Query query = getSession().createQuery("select distinct count(model.userProblemId) from UserProblem model where model.visibility.type ='"+IConstants.PRIVATE+"' and model.isOwner ='"+IConstants.TRUE+"' and model.problem.isApproved ='"+IConstants.TRUE+"' " +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.problem.impactLevelValue =:locationValue and model.user.userId =:userId  and model.problem.regionScopes.regionScopesId=:locationId and model.problem.problemStatus.status = :status order by model.problem.problemId desc");
		query.setParameter("locationValue", locationValue);
		query.setParameter("userId", userId);
		query.setParameter("locationId", locationId);
		query.setParameter("status", status);
		
		return (Long) query.uniqueResult();
		
	}*/
	
	@SuppressWarnings("unchecked")
	
	public List<Long> getAllUserProblemsBySource(Long locationValue,Long userId,Long locationId,Long sourceId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.userProblemId) from UserProblem model where model.problem.isApproved ='"+IConstants.TRUE+"' " +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.user.userId =:userId and model.problem.informationSource.informationSourceId = :sourceId and model.problem.problemStatus.status = '"+IConstants.NEW+"' ");
		if(locationId ==4)
			str.append(" and model.problem.problemCompleteLocation.constituency.constituencyId = :locationValue ");	
		else if(locationId == 5)
			str.append(" and model.problem.problemCompleteLocation.tehsil.tehsilId = :locationValue ");
		
		else if(locationId == 8)
			str.append(" and model.problem.problemCompleteLocation.ward.constituencyId =:locationValue ");
		else if(locationId == 7)
			str.append(" and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId =:locationValue ");
		//I Had changed here for getting Hamlets Problems
		else if(locationId == 10)
		str.append(" and model.problem.problemCompleteLocation.hamlet.hamletId =:locationValue ");
				//end

			str.append(" order by model.problem.problemId desc ");	
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationValue", locationValue);
		query.setParameter("userId", userId);
		//query.setParameter("locationId", locationId);
		query.setParameter("sourceId", sourceId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllProblemsByLocation(Long userId,Long locationId,Long locationValue,String status,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.userProblemId) from UserProblem model where (model.user.userId = :userId or model.visibility.type = :visibilityType) and model.problem.isApproved ='"+IConstants.TRUE+"'" +
				" and model.problem.isDelete ='"+IConstants.FALSE+"' and model.problem.problemStatus.status = :status ");
		if(locationId ==4)
			str.append(" and model.problem.problemCompleteLocation.constituency.constituencyId = :locationValue ");	
		else if(locationId == 5)
			str.append(" and model.problem.problemCompleteLocation.tehsil.tehsilId = :locationValue ");
		else if(locationId == 8)
			str.append(" and model.problem.problemCompleteLocation.ward.constituencyId =:locationValue ");
		else if(locationId == 7)
			str.append(" and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId =:locationValue ");
		//I Had changed here for getting Hamlets Problems
		else if(locationId == 10)
			str.append(" and model.problem.problemCompleteLocation.hamlet.hamletId = :locationValue ");	
		//end
		
		str.append(" order by model.problem.problemId desc ");	
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationValue", locationValue);
		//query.setParameter("locationId", locationId);
		query.setParameter("status", status);
		query.setParameter("userId",userId);
		query.setParameter("visibilityType",IConstants.PUBLIC);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
		
	}
	
@SuppressWarnings("unchecked")
	
	public List<Long> getAllProblemsBySource(Long locationValue,Long userId,Long locationId,Long sourceId,Integer startIndex,Integer maxIndex)
	{
	
		StringBuilder str = new StringBuilder();
		str.append("select distinct(model.userProblemId) from UserProblem model where model.problem.isApproved ='"+IConstants.TRUE+"' " +
				" and model.problem.isDelete ='"+IConstants.FALSE+"'  and model.user.userId =:userId and model.problem.informationSource.informationSourceId = :sourceId and model.problem.problemStatus.status = '"+IConstants.NEW+"' ");
		if(locationId ==4)
			str.append(" and model.problem.problemCompleteLocation.constituency.constituencyId = :locationValue ");	
		else if(locationId == 5)
		str.append(" and model.problem.problemCompleteLocation.tehsil.tehsilId = :locationValue ");
		
		else if(locationId == 8)
			str.append(" and model.problem.problemCompleteLocation.ward.constituencyId =:locationValue ");
		else if(locationId == 7)
			str.append(" and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId =:locationValue ");
		//Changed by gayathri to get Hamlet Level Problems
		else if(locationId == 10)
			str.append(" and model.problem.problemCompleteLocation.hamlet.hamletId =:locationValue ");	
		//end
		str.append(" order by model.problem.problemId desc ");	
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationValue", locationValue);
		query.setParameter("userId", userId);
		//query.setParameter("locationId", locationId);
		query.setParameter("sourceId", sourceId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		
		return query.list();
	}

	public Long getCountOfNewlyPostedProblemsByPublicUser(Date currentDate)
	{
		Query query = getSession().createQuery("select count(model.problem.problemId) from UserProblem model where Date(model.problem.updatedTime) = ? and (model.problem.isDelete is null or model.problem.isDelete = 'false') and model.visibility.type ='"+IConstants.PUBLIC+"' ");
		query.setParameter(0, currentDate);
		return (Long)query.uniqueResult();
		
	}

	public List getProblemDetailsByLocationValuesList(List<Long> locationValuesList, String problemScope, String visibilityType)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getCommonDataForAllProblems());
		
		stringBuilder.append(" where model.problem.regionScopes.scope =:problemScope and model.problem.impactLevelValue in (:locationValuesList) " +
				" and model.visibility.type = :type and model.problem.isDelete =:isDelete order by date(model.updatedTime) ");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameterList("locationValuesList", locationValuesList);
		queryObj.setParameter("problemScope", problemScope);
		queryObj.setParameter("type", visibilityType);
		queryObj.setParameter("isDelete", IConstants.FALSE);
		queryObj.setMaxResults(IConstants.MAX_PROBLEMS_DISPLAY.intValue());
		return queryObj.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getLatestProblemsOfCurrentDateByFreeUser(Date firstDate,Date lastDate,String isApproved, String problemStatus)
	{
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append("select model.problem.problemId,model.problem.description,model.problem.isApproved,model.problem.identifiedOn,model.user.firstName,model.user.lastName,model.problem.title ");
		conditionQuery.append(" from UserProblem model where  model.visibility.type =:status and model.problem.isDelete =:isDelete ");
		if(firstDate != null)
			conditionQuery.append(" and date(model.problem.insertedTime) >=:initialDate ");
		
		 if(lastDate != null)
			conditionQuery.append(" and date(model.problem.insertedTime) <=:endDate ");
		
		 if(isApproved != null)
			conditionQuery.append(" and model.problem.isApproved =:isApproved ");
		
		 if(problemStatus != null)
			 conditionQuery.append(" and model.problem.problemStatus.status = :problemStatus");
		 
		Query queryObject = getSession().createQuery(conditionQuery.toString());
		
		if(firstDate != null)
		    queryObject.setParameter("initialDate", firstDate);
		
	    if(lastDate != null)
		    queryObject.setParameter("endDate", lastDate);
		
		 if(isApproved != null)
		    queryObject.setParameter("isApproved", isApproved);   
		
		 if(problemStatus != null)
			 queryObject.setParameter("problemStatus", problemStatus);
		 
		queryObject.setParameter("status", "Public");
		queryObject.setParameter("isDelete", "false");
		return queryObject.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProblem> getLatestProblemsForUser(Long userId,Long statusId,Integer startIndex,Integer maxIndex)
	{
		Query query = getSession().createQuery("from UserProblem model where model.user.userId=:userId and model.problem.problemStatus.problemStatusId =:statusId and (model.problem.isDelete = '"+IConstants.FALSE+"' or model.problem.isDelete is null) order by model.updatedTime desc");
		query.setParameter("statusId", statusId);
		query.setParameter("userId", userId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getProblemsByStatusAndBetweenDates(Long userId,Long statusId, Date fromDate, Date toDate)
	{
		Query query = getSession().createQuery("select model.userProblemId from UserProblem model where date(model.updatedTime) >=:fromDate and date(model.updatedTime) <=:toDate and " +
					" model.user.userId =:userId and model.problem.problemStatus.problemStatusId =:statusId and (model.problem.isDelete is null or model.problem.isDelete = '"+IConstants.FALSE+"') order by model.updatedTime desc");
		query.setParameter("userId", userId);
		query.setParameter("statusId", statusId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getProblemsByBetweenDates(Long userId,Date fromDate, Date toDate)
	{
		Query query = getSession().createQuery("select model.userProblemId from UserProblem model where date(model.updatedTime) >=:fromDate and date(model.updatedTime) <=:toDate and " +
					" model.user.userId =:userId and (model.problem.isDelete is null or model.problem.isDelete = '"+IConstants.FALSE+"') order by model.updatedTime desc");
		query.setParameter("userId", userId);
		
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
		
	}
	
	
}
