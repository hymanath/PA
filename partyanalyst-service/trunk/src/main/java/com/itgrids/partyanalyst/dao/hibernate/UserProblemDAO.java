package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserProblemDAO;
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
				" and model.problem.isApproved = ?  and model.problem.isDelete = ? and model.problem.regionScopes.regionScopesId <= 5 ");
		 
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
	public List<UserProblem> getUserProblemId(Long problemId,Long userId)
	{
		StringBuffer query=new StringBuffer("select model.userProblemId from UserProblem model where model.problem.problemId =:problemId and model.user.userId =:userId and model.visibility.type =:type");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("problemId",problemId);
		queryObject.setParameter("userId",userId);
		queryObject.setParameter("type","Public");
		
		return queryObject.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllProblemsOfCurrentDateByFreeUser(Date firstDate,Date lastDate,String isApproved)
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
		
		Query queryObject = getSession().createQuery(conditionQuery.toString());
		
		if(firstDate != null)
		    queryObject.setParameter("initialDate", firstDate);
		
	    if(lastDate != null)
		    queryObject.setParameter("endDate", lastDate);
		
		 if(isApproved != null)
		    queryObject.setParameter("isApproved", isApproved);   
		
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
			String order, String columnName, String reasonType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.problem.problemId, ");
		query.append(" model.problem.title,model.problem.description,model.problem.identifiedOn,model.problem.existingFrom, ");
		query.append(" model.problem.impactLevelValue,model.problem.regionScopes.regionScopesId,model.problem.regionScopes.scope, ");
		query.append(" model.problem.isApproved,model.userProblemId from UserProblem model where (model.problem.isDelete = 'false' or model.problem.isDelete is null) ");
		
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append(" and model.user.userId = ? and model.problem.isApproved = 'true'");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
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
	@SuppressWarnings("unchecked")
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long userId, String reasonType){

		StringBuilder query = new StringBuilder();
		query.append(" select count(*) from UserProblem model ");
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append("where model.user.userId is not null and model.problem.isApproved = '"+IConstants.TRUE+"' ");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append("where model.user.userId = ? ");			
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
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCount(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId),model.problem.isApproved from UserProblem model where model.user.userId = ? and (model.problem.isDelete ='false'or model.problem.isDelete is null) group by model.problem.isApproved",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long userId)
	{
		return getHibernateTemplate().find("select count(distinct model.problem.problemId) from UserProblem model where model.user.userId != ? and (model.problem.isDelete is null or model.problem.isDelete = 'false') and model.problem.isApproved = 'true' ",userId);
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
		public List<Problem> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(Long userId,Integer startIndex, Integer maxResults) {
	
			Query queryObject = getSession().createQuery("select model.problem from UserProblem model where " +
					"model.user.userId = :userId and  model.problem.isDelete = 'false' and (model.problem.isApproved = 'true') order by  model.problem.identifiedOn desc");
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
}
