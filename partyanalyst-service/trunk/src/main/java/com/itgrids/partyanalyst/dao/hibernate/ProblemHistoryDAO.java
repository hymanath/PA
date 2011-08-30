package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemHistoryDAO extends GenericDaoHibernate<ProblemHistory, Long> implements IProblemHistoryDAO{

	public ProblemHistoryDAO(){
		super(ProblemHistory.class);
	}
		
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> findProblemLocationsByUserId(Long registrationId, Long statusId){
		Object [] params = {registrationId, statusId};
		return getHibernateTemplate().find("from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? " +
				"and model.problemStatus.problemStatusId = ? order by model.problemLocation.problemAndProblemSource.problem.identifiedOn desc",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByHamletId(Long hamletId,Long userId){
		Object [] params = {hamletId, userId,IConstants.VILLAGE};
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);		
		conditionQuery.append(" where model.problemLocation.hamlet.hamletId = ? and");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.user.registrationId = ? and");
		conditionQuery.append(" model.problemLocation.problemImpactLevel in ( ");
		conditionQuery.append(" select model1.regionScopesId from RegionScopes model1 where model1.scope = ?) ");
		conditionQuery.append(" and model.isDelete is null");
		
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByTehsilId(Long tehsilId) {
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.isDelete is null)");		
		return getHibernateTemplate().find(conditionQuery.toString(),tehsilId);
	}

	/*@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(String constituencyIds,Long userId) {
		Object[] params = {userId,IConstants.CONSTITUENCY};
		
		String query = buildCommonQuery();		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.problemImpactLevelValue in (  " + constituencyIds +") and");	
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.user.registrationId = ? and");
		conditionQuery.append(" model.problemLocation.problemImpactLevel.scope = ? and");
		conditionQuery.append(" model.isDelete is null");
		
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(Long userId) {
		
		String query = buildCommonQueryForProblems();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.problemAndProblemSource.user.registrationId = ? and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString());
	}

	/*@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByHamletId(Long hamletId,String status,Long userId) {
		Object[] params = {hamletId,status,userId,IConstants.VILLAGE};
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.problemImpactLevelValue = ? and ");
		conditionQuery.append(" model.problemStatus.status = ? and ");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.user.registrationId = ? and");
		conditionQuery.append(" model.problemLocation.problemImpactLevel in ( ");
		conditionQuery.append(" select model1.regionScopesId from RegionScopes model1 where model1.scope = ?) and");
		conditionQuery.append(" model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}
*/
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByTehsilId(Long tehsilId,String status) {
		Object[] params = {tehsilId,status};
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.problemStatus.status = ? and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> findProblemsForSelectedSearchOptions(Long locationId, Long status, Long userId,String model,String idToCompare,Long deptId,Boolean groupCadre,Boolean groupDept) {
				
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(" from ProblemHistory model ");
		conditionQuery.append(" where model.problemLocation.problemAndProblemSource.user.registrationId = ? and");
		
		if(model != null && idToCompare != null)
			conditionQuery.append("  model.problemLocation.problemCompleteLocation."+ model +"."+idToCompare+" = ? and");		
				
		if(status!= null && status!= 0l && !status.equals(""))
			conditionQuery.append(" model.problemStatus.problemStatusId = ? and ");	
		
		if(deptId!=0l){
			conditionQuery.append(" model.problemHistoryId in ( select model2.problemHistory.problemHistoryId from ");			
			conditionQuery.append(" AssignedProblemProgress model2 where model2.problemSourceScopeConcernedDepartment.problemSourceScopeConcernedDepartmentId = ? ) and ");
		}
		
		conditionQuery.append(" model.isDelete is null");
		
		// To Group Problems By Cadre
		if(groupDept){
			conditionQuery.append(" and model.problemHistoryId in ( select model2.problemHistory.problemHistoryId from ");
		    conditionQuery.append(" AssignedProblemProgress model2 order by model2.problemSourceScopeConcernedDepartment.problemSourceScopeConcernedDepartmentId )");
		}
		
		// To Group Problems By Department
		if(groupCadre){
			conditionQuery.append(" and model.problemHistoryId in ( select model2.problemHistory.problemHistoryId from ");
			conditionQuery.append(" CadreProblemDetails model2 order by model2.cadre.cadreId )");
		}
		
		Query queryObject = getSession().createQuery(conditionQuery.toString());
		queryObject.setLong(0,userId);
		int i = 1;
		if(locationId != 0l)
			queryObject.setLong(i++,locationId);
				
		if(status!=null && status != 0l && !status.equals(""))
		queryObject.setLong(i++,status);	
		
		if(deptId!=0l)
			queryObject.setLong(i,deptId);
		return queryObject.list();
	}	
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByConstituencyId(Long userId, Long status) {
		Object[] params = {userId,status};
		String query = buildCommonQueryForProblems();
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.problemAndProblemSource.user.registrationId = ? and model.problemStatus.problemStatusId = ? and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List findCompleteProblems(Long problemLocationId) {
		return getHibernateTemplate().find(" Select model.problemHistoryId,model.comments,model.dateUpdated,model.isDelete," +
				" model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId,model.problemStatus.status" +
				" from ProblemHistory model where model.problemLocation.problemLocationId =? order by model.problemHistoryId",problemLocationId);
	}

	@SuppressWarnings("unchecked")
	public List getProblemsCountInAllStatusByLocationForAUser(String locationIds,
			Long registrationId) {
		Object[] params = {registrationId};
		return getHibernateTemplate().find("select model.problemStatus.problemStatusId, model.problemStatus.status," +
				"count(model.problemHistoryId) " +
				"from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in " +
				"(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where " +
				"model1.delimitationConstituency.delimitationConstituencyID = (" +
				"select model2.delimitationConstituencyID from DelimitationConstituency model2 where " +
				"model2.constituency.constituencyId in ("+locationIds+") group by model2.constituency.constituencyId " +
				"order by model2.year desc)) and " +
				"model.problemLocation.problemAndProblemSource.user.registrationId = ? "+
				"group by model.problemStatus.problemStatusId",params );
	}
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountInAllStatusByLocation(String locationIds) {
		return getHibernateTemplate().find("select model.problemStatus.problemStatusId, model.problemStatus.status," +
				"count(model.problemHistoryId) " +
				"from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in " +
				"(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where " +
				"model1.delimitationConstituency.delimitationConstituencyID in (" +
				"select model2.delimitationConstituencyID from DelimitationConstituency model2 where " +
				"model2.constituency.constituencyId in ("+locationIds+") and model.isDelete is null group by " +
				"model2.constituency.constituencyId order by model2.year desc)) group " +
				"by model.problemStatus.problemStatusId order by model.problemStatus.problemStatusId");
	}
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountInAllStatusByLocation(Long userId) {
		return getHibernateTemplate().find("select model.problemStatus.problemStatusId, model.problemStatus.status," +
				"count(model.problemHistoryId) from ProblemHistory model where " +
				"model.problemLocation.problemAndProblemSource.user.registrationId = ? and model.isDelete is null " +
				"group by model.problemStatus.problemStatusId order by model.problemStatus.problemStatusId",userId);
	}
	
	public List findProblemsByStatusDateAndLocation(String tehsilIds, Long statusId, Date fromDate, Date toDate){
		Object[] params = {fromDate, toDate, statusId};
		return getHibernateTemplate().find("select model.problemLocation.problemLocationId, " +
				"model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
				"model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problemSource.informationSource," +
				"model.problemLocation.problemAndProblemSource.problemAndProblemSourceId," +
				"model.problemStatus.status," +
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn"+
				" from ProblemHistory model where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? and " +
				"model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + tehsilIds +
				") and model.problemStatus.problemStatusId = ? and model.isDelete is null",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusDateAndLocation(Long userId, Long statusId, Date fromDate, Date toDate){
		Object[] params = {fromDate, toDate,userId, statusId};
		return getHibernateTemplate().find("select model.problemLocation.problemLocationId, " +
				"model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
				"model.problemLocation.problemImpactLevel.regionScopesId," +
				"model.problemLocation.problemAndProblemSource.problemSource.informationSource," +
				"model.problemLocation.problemAndProblemSource.problemAndProblemSourceId," +
				"model.problemStatus.status," +
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn,model.problemLocation.problemImpactLevelValue "+
				"from ProblemHistory model where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? and " +
				"model.problemLocation.problemAndProblemSource.user.registrationId = ? "+
				"and model.problemStatus.problemStatusId = ? and model.isDelete is null",params);
	}
	
		
	public List findProblemsByDateAndLocation(String tehsilIds, Date fromDate, Date toDate){
		Object[] params = {fromDate, toDate};
		return getHibernateTemplate().find("select model.problemLocation.problemLocationId, " +
				"model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
				"model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problemSource.informationSource," +
				"model.problemLocation.problemAndProblemSource.problemAndProblemSourceId," +
				"model.problemStatus.status," +
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn"+
				" from ProblemHistory model where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? and " +
				"model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + tehsilIds +
				") and model.isDelete is null",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findProblemsByDateAndLocation(Long userId, Date fromDate, Date toDate){
		Object[] params = {fromDate, toDate,userId};
		return getHibernateTemplate().find("select model.problemLocation.problemLocationId, " +
				"model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
				"model.problemLocation.problemImpactLevel.regionScopesId," +
				"model.problemLocation.problemAndProblemSource.problemSource.informationSource," +
				"model.problemLocation.problemAndProblemSource.problemAndProblemSourceId," +
				"model.problemStatus.status," +
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn,model.problemLocation.problemImpactLevelValue "+
				"from ProblemHistory model where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? and " +
				"model.problemLocation.problemAndProblemSource.user.registrationId = ? "+
				"and model.isDelete is null",params);
	}
	
	public List findLatestProblemsByMandals(String tehsilIds, Long statusId){		
		return getHibernateTemplate().find("select model.problemLocation.problemLocationId, " +
				"model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
				"model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problemSource.informationSource," +
				"model.problemLocation.problemAndProblemSource.problemAndProblemSourceId," +
				"model.problemStatus.status," +
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn"+
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in " +
				"(" + tehsilIds +") and model.problemStatus.problemStatusId = ?" +
						" and model.isDelete is null order by model.dateUpdated desc", statusId);
	}
	
	@SuppressWarnings("unchecked")
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(String tehsilIds, String statusIds){
		return getHibernateTemplate().find("select count(model.problemHistoryId), model.dateUpdated, model.problemStatus.problemStatusId, " +
				"model.problemStatus.status from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in " +
				"(" + tehsilIds +") and model.problemStatus.problemStatusId in("+statusIds+")  " +
						" group by date(model.dateUpdated),model.problemStatus.problemStatusId order by model.dateUpdated desc");
	}
	
	@SuppressWarnings("unchecked")
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(Long userId, String statusIds){
		return getHibernateTemplate().find("select count(model.problemHistoryId), model.dateUpdated, model.problemStatus.problemStatusId, " +
				"model.problemStatus.status from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? " +
				"and model.problemStatus.problemStatusId in("+statusIds+")  " +
						" group by date(model.dateUpdated),model.problemStatus.problemStatusId order by model.dateUpdated desc",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> findProblemHistoryByProblemLocation(Long problemLocationId) {
		Object [] params = {problemLocationId};
		return getHibernateTemplate().find("from ProblemHistory model where model.problemLocation.problemLocationId = ?" +
				" and model.isDelete is null",params );
	}

	@SuppressWarnings("unchecked")
	public List<AssignedProblemProgress> getAssignedProblemsProgress(Long problemHistoryId) {		
		return getHibernateTemplate().find("select model.assignedProblemProgresses from ProblemHistory model where model.problemHistoryId = ?",problemHistoryId) ;
	}
	
	/**
	 * @author Ravi Kiran.Y
	 * @return
	 */
	public String buildCommonQuery(){		
		StringBuilder query = new StringBuilder();
		query.append(" select model.problemStatus.status," );
		query.append(" model.problemLocation.hamlet.hamletName,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.identifiedOn,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.problem,");
		query.append(" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId,"); 
		query.append(" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId,");
		query.append(" model.problemLocation.problemAndProblemSource.user.registrationId");
		query.append(" from ProblemHistory model ");
		return query.toString();		
	}
	
	public String buildCommonQueryForProblems(){		
		StringBuilder query = new StringBuilder();
		query.append(" select model.problemStatus.status," );
		query.append(" model.problemLocation.problemImpactLevel.regionScopesId,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.identifiedOn,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.problem,");
		query.append(" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId,"); 
		query.append(" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId,");
		query.append(" model.problemLocation.problemAndProblemSource.user.registrationId,model.problemLocation.problemImpactLevelValue");
		query.append(" from ProblemHistory model ");
		return query.toString();		
	}
	
	
	public String getCommonDataForAllProblems(){
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(" select model.problemLocation.problemAndProblemSource.problem.problem,");	
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.description,");
		conditionQuery.append(" model.problemLocation.problemImpactLevel.scope,");
		conditionQuery.append(" model.problemLocation.problemImpactLevelValue,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.identifiedOn,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.externalUser.name,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.problemId,");
		conditionQuery.append(" model.problemHistoryId,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.existingFrom,");
		conditionQuery.append(" model.problemStatus.status,");
		conditionQuery.append(" model.problemLocation.problemImpactLevel.regionScopesId");
		conditionQuery.append(" from ProblemHistory model ");
		return conditionQuery.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsPostedForCurrentDay(Date date,String status,String isApproved){
		Object[] params = {date,status,isApproved};
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(getCommonDataForAllProblems());
		
		conditionQuery.append(" where date(model.dateUpdated) = ? and model.problemStatus.status = ? ");	
		conditionQuery.append(" and model.isApproved = ? ");	
		conditionQuery.append(" and model.problemLocation.problemAndProblemSource.externalUser is not null ");
		conditionQuery.append(" order by date(model.dateUpdated)");	
		
		Query queryObject = getSession().createQuery(conditionQuery.toString());
		queryObject.setDate(0,date);
		queryObject.setString(1,status);
		queryObject.setString(2,isApproved);
		queryObject.setMaxResults(100);
		return queryObject.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsBetweenDatesWithCompleteData(Date fromDate, Date toDate, String status,String isApproved){
		Object[] params = {fromDate, toDate, status,isApproved};
		StringBuilder query = new StringBuilder();
		query.append(getCommonDataForAllProblems());		
		query.append(" where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
		query.append(" and model.problemStatus.status = ? and model.isApproved = ? ");	
		query.append(" and model.problemLocation.problemAndProblemSource.externalUser is not null ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setDate(0,fromDate);
		queryObject.setDate(1,toDate);
		queryObject.setString(2,status);
		queryObject.setString(3,isApproved);
		queryObject.setMaxResults(100);
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsBetweenDates(Date fromDate, Date toDate, String status,String isApproved){
		Object[] params = {fromDate, toDate, status,isApproved};		
		
		StringBuilder query = new StringBuilder();		
		query.append("select count(model.dateUpdated),date(model.dateUpdated) from ProblemHistory model ");
		query.append(" where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
		query.append(" and model.problemStatus.status = ? and model.isApproved = ? ");
		query.append(" and model.problemLocation.problemAndProblemSource.externalUser is not null ");
		query.append(" group by date(model.dateUpdated)");			
		return getHibernateTemplate().find(query.toString(),params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(Date date,String status,String isApproved){
		Object[] params = {date, status,isApproved};		
		
		StringBuilder query = new StringBuilder();		
		query.append( "select count(model.dateUpdated),model.problemLocation.problemImpactLevel.scope from ProblemHistory model ");
		query.append(" where date(model.dateUpdated) = ? and model.problemStatus.status = ? and model.isApproved = ? ");
		query.append(" and model.problemLocation.problemAndProblemSource.externalUser is not null ");
		query.append(" group by model.problemLocation.problemImpactLevel.regionScopesId");			
		return getHibernateTemplate().find(query.toString(),params);
	}
	//	queryObject.setMaxResults(20);
	
	@SuppressWarnings("unchecked")
	public List getAllProblemHistoryIdsForGivenLocationByTheirIds(List<Long> locationIds,String impactLevel,String problemType){			
		StringBuilder locationQuery = new StringBuilder();
		locationQuery.append(getCommonDataForAllProblems());
		locationQuery.append(" where model.problemLocation.problemImpactLevel.scope = ?");
		locationQuery.append(" and model.isApproved = ? ");
		locationQuery.append(" and model.problemLocation.problemImpactLevelValue in (:locationIds)");		
		locationQuery.append(" and model.problemLocation.problemAndProblemSource.externalUser is not null ");
		locationQuery.append(" order by date(model.dateUpdated)");	
		Query queryObject = getSession().createQuery(locationQuery.toString());
		queryObject.setString(0,impactLevel);
		queryObject.setString(1,problemType);
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setMaxResults(IConstants.MAX_PROBLEMS_DISPLAY.intValue());
		return queryObject.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.dao.IProblemHistoryDAO#getProblemHistoryByProblemStatusForAUser(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 * DAO Method that retrieves problems data in a particular status(like NEW,ASSIGNED,FIXED ...) posted by a user
	 */
	@SuppressWarnings("unchecked")
	public List getProblemHistoryByProblemStatusForAUser(Long userId,
			Long statusId, String isPushed,String isDeleted) {
		Object[] params = {userId,statusId,isPushed,isDeleted};
		return getHibernateTemplate().find("select model.problemLocation.problemImpactLevel.regionScopesId,"+
				"model.problemLocation.problemImpactLevelValue,model.problemStatus.problemStatusId,model.dateUpdated,"+
				"model.problemStatus.status,model.problemLocation.problemAndProblemSource.problem.problemId,model.isApproved,"+
				"model.problemLocation.problemAndProblemSource.problem.description,model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn,model.problemLocation.problemAndProblemSource."+
				"problem.existingFrom,model.isDelete,model.problemLocation.problemLocationId from ProblemHistory model where model.problemLocation.problemAndProblemSource."+
				"user.registrationId = ? and model.problemStatus.problemStatusId = ? and model.problemLocation.problemAndProblemSource."+
				"isPushed != ? and model.isDelete != ?",params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.dao.IProblemHistoryDAO#getProblemHistoryForAUser(java.lang.Long, java.lang.String, java.lang.String)
	 * DAO Method that retrieves all problems data posted by a user
	 */
	@SuppressWarnings("unchecked")
	public List getProblemHistoryForAUser(Long userId, String isPushed,String isDeleted) {
		Object[] params = {userId,isPushed,isDeleted};
		return getHibernateTemplate().find("select model.problemLocation.problemImpactLevel.regionScopesId,"+
				"model.problemLocation.problemImpactLevelValue,model.problemStatus.problemStatusId,model.dateUpdated,"+
				"model.problemStatus.status,model.problemLocation.problemAndProblemSource.problem.problemId,model.isApproved,"+
				"model.problemLocation.problemAndProblemSource.problem.description,model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.identifiedOn,model.problemLocation.problemAndProblemSource."+
				"problem.existingFrom,model.isDelete,model.problemLocation.problemLocationId from ProblemHistory model where model.problemLocation.problemAndProblemSource."+
				"user.registrationId = ? and model.problemLocation.problemAndProblemSource.isPushed != ? and model.isDelete != ?",params);
	}

	public List findProblemCompleteInfo(Long problemHistoryId) {
			StringBuilder locationQuery = new StringBuilder();
			locationQuery.append(getCommonDataForAllProblems());
			locationQuery.append(" where model.problemHistoryId = ?");
			Query queryObject = getSession().createQuery(locationQuery.toString());
			queryObject.setLong(0,problemHistoryId);
			return queryObject.list();
		
	}
	
	public Long getAllRecordsCountForPostedProblemsByAnanymousUserId(Long registrationId, String reasonType){

		StringBuilder query = new StringBuilder();
		query.append(" select count(*) from ProblemHistory model ");
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId is not null ");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? ");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId != ? ");
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.TRUE+"'");			
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.FALSE+"'");	
		
		Query queryObject = getSession().createQuery(query.toString());
		
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType))
			queryObject.setParameter(0, registrationId);
		
		return (Long)queryObject.uniqueResult();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.dao.IProblemHistoryDAO#getAllPostedProblemsByAnanymousUserId(java.lang.Long)
	 * DAO Method that retrieves all problems data posted by a Ananymous user
	 */
	public List getAllPostedProblemsByAnanymousUserId(Long registrationId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.problemLocation.problemAndProblemSource.problem.problemId, ");
		query.append("model.problemLocation.problemAndProblemSource.problem.description,model.problemLocation.problemAndProblemSource.problem.identifiedOn, ");
		query.append("model.problemLocation.problemAndProblemSource.problem.year,model.problemLocation.problemAndProblemSource.problem.problem, ");
		query.append("model.problemLocation.problemAndProblemSource.problem.existingFrom,model.problemLocation.problemImpactLevelValue, ");
		query.append("model.problemLocation.problemImpactLevel.scope,model.isApproved, model.problemHistoryId from ProblemHistory model ");
		
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId is not null ");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? ");			
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId != ? ");
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.TRUE+"'");			
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.REJECTED+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append("where model.problemLocation.problemAndProblemSource.externalUser.userId = ? and model.isApproved = '"+IConstants.FALSE+"'");	
		
		
		
		query.append(" order by "+columnName+" "+order);
		
		Query queryObject = getSession().createQuery(query.toString());
		
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType))
			queryObject.setParameter(0, registrationId);
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(results);
		
		return queryObject.list();
		/*return getHibernateTemplate().find("select model.problemLocation.problemAndProblemSource.problem.problemId,"+
				" model.problemLocation.problemAndProblemSource.problem.description,model.problemLocation.problemAndProblemSource.problem.identifiedOn,"+
				" model.problemLocation.problemAndProblemSource.problem.year,model.problemLocation.problemAndProblemSource.problem.problem,"+
				" model.problemLocation.problemAndProblemSource.problem.existingFrom,model.problemLocation.problemImpactLevelValue,"+				
				" model.problemLocation.problemImpactLevel.scope,model.isApproved from ProblemHistory model where model.problemLocation.problemAndProblemSource."+
				"externalUser.userId = ? ",registrationId);*/
	}
	
	public List getAllPostedProblemCount(Long registrationId)
	{		
		return getHibernateTemplate().find("select count(distinct model.problemLocation.problemAndProblemSource.problem.problemId), "+
				"model.isApproved from ProblemHistory model where model.problemLocation.problemAndProblemSource.externalUser.userId = ? "+
				"group by model.isApproved",registrationId);
	}
	
	public List getAllPostedProblemCountOtherThanLoggedInUser(Long registrationId)
	{		
		/*return getHibernateTemplate().find("select count(distinct model.problemLocation.problemAndProblemSource.problem.problemId), "+
				"from ProblemHistory model where model.problemLocation.problemAndProblemSource.externalUser.userId != ?",registrationId);*/
		
		return getHibernateTemplate().find("select count(distinct model.problemLocation.problemAndProblemSource.problem.problemId) "+
				"from ProblemHistory model where model.problemLocation.problemAndProblemSource.externalUser.userId != ? "+
				"",registrationId);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getRecentPostedProblemsForAUserByCount(Long userId,
			Long problemStatusId, Integer startIndex, Integer maxResults) {
		
				
		Criteria criteria = getSession().createCriteria(ProblemHistory.class)
		               .createAlias("probSource.user", "user")
		               .createAlias("problemLocation", "probLoc")
		               .createAlias("problemStatus", "probStatus")
		               .createAlias("probLoc.problemAndProblemSource", "probSource")
		               .add(Expression.eq("user.registrationId", userId))
		               .add(Expression.eq("probStatus.problemStatusId", problemStatusId))
		               .add(Restrictions.or(
					        Restrictions.eq( "isDelete", "false" ),
					        Restrictions.isNull("isDelete")))
		               
		               .setMaxResults(maxResults)
		               .setFirstResult(startIndex)
		               .addOrder(Order.desc("dateUpdated"));
			
	 return criteria.list();
	}

	public Long getRecentPostedProblemsCountForAUserByProblemStatus(
			Long userId, Long problemStatusId) {
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(*) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.problemStatus.problemStatusId = "+problemStatusId+ " and "+
				"model.isDelete is null or model.isDelete = 'false' order by dateUpdated desc").iterate().next() );
		
	 return resultsCount;
	}
	
	public Long getProblemsCountPostedByAUserInBetweenDates(
			Long userId, Long problemStatusId) {
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(model.problemHistoryId) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.problemStatus.problemStatusId = "+problemStatusId+ " and "+
				"model.isDelete is null or model.isDelete = 'false' group by date(model.dateUpdated) order by dateUpdated desc").iterate().next() );
		
	 return resultsCount;
	}

	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenDates(Long userId,
			Long statusId,Integer startIndex,Integer maxResults) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select count(model.problemHistoryId),date(model.dateUpdated) from ProblemHistory model where ");
		query.append("model.problemLocation.problemAndProblemSource.user.registrationId= ? ");
		query.append("and model.problemStatus.problemStatusId = ? and model.isDelete is null ");
		query.append("or model.isDelete = 'false' group by date(model.dateUpdated) order by model.dateUpdated desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, statusId);
		
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
		
	 return queryObject.list();
	}
	
	public Long getProblemsCountPostedByAUserInBetweenMonths(
			Long userId, Long problemStatusId) {
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(model.problemHistoryId) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.problemStatus.problemStatusId = "+problemStatusId+ " and "+
				"model.isDelete is null or model.isDelete = 'false' group by month(model.dateUpdated) order by dateUpdated desc").iterate().next() );
		
	 return resultsCount;
	}
	
	
	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenMonths(Long userId,
			Long statusId,Integer startIndex,Integer maxResults) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select count(model.problemHistoryId),month(model.dateUpdated),year(model.dateUpdated) from ProblemHistory model where ");
		query.append("model.problemLocation.problemAndProblemSource.user.registrationId= ? ");
		query.append("and model.problemStatus.problemStatusId = ? and model.isDelete is null ");
		query.append("or model.isDelete = 'false' group by month(model.dateUpdated) order by model.dateUpdated desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, statusId);
		
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
		
	 return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenDates(Long userId,
			Integer startIndex, Integer maxResults) {
		
        StringBuilder query = new StringBuilder();
		
		query.append("select count(model.problemHistoryId),date(model.dateUpdated),model.problemStatus.status ");
		query.append("from ProblemHistory model where model.problemLocation.problemAndProblemSource.");
		query.append("user.registrationId= ? and model.isDelete is null or model.isDelete = 'false' ");
		query.append("group by model.problemStatus.problemStatusId,date(model.dateUpdated) order by model.dateUpdated desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, userId);
				
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
		
	 return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getProblemsPostedForAUserInBetweenMonths(Long userId,
			Integer startIndex, Integer maxResults) {
		
        StringBuilder query = new StringBuilder();
		
		query.append("select count(model.problemHistoryId),month(model.dateUpdated),model.problemStatus.status,year(model.dateUpdated) ");
		query.append("from ProblemHistory model where model.problemLocation.problemAndProblemSource.");
		query.append("user.registrationId= ? and model.isDelete is null or model.isDelete = 'false' ");
		query.append("group by model.problemStatus.problemStatusId,month(model.dateUpdated) order by model.dateUpdated desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, userId);
				
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
		
	 return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByAUserInBetweenDates(
			Long userId){
		
		return getHibernateTemplate().find("select distinct date(model.dateUpdated) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.isDelete is null or model.isDelete = 'false' order by dateUpdated desc");
	}
	
	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByAUserInBetweenMonths(
			Long userId) {
		
		return getHibernateTemplate().find("select distinct month(model.dateUpdated) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.isDelete is null or model.isDelete = 'false' order by dateUpdated desc");
		
	}

	@SuppressWarnings("unchecked")
	public List getProblemsCountPostedByUserInDifferentLifeCycleStages(
			Long userId) {
		
		return getHibernateTemplate().find("select count(model.problemHistoryId),model.problemStatus.problemStatusId,"+
				"model.problemStatus.status,model.problemLocation.problemAndProblemSource.problemSource.informationSourceId,"+
				"model.problemLocation.problemAndProblemSource.problemSource.informationSource from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and model.problemStatus.status "+
				"in ("+IConstants.PROBLEMS_LIFE_CYCLE+") and model.isDelete is null or model.isDelete = 'false' group by "+
				"model.problemStatus.status,model.problemLocation.problemAndProblemSource.problemSource.informationSource "+
				"order by model.problemStatus.problemStatusId");
	}

	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getProblemsPostedByUserInDifferentLifeCycleStagesByDate(
			Long userId,Integer startIndex,Integer maxResults) {
		
		Criteria criteria = getSession().createCriteria(ProblemHistory.class)
        .createAlias("probSource.user", "user")
        .createAlias("problemLocation", "probLoc")
        .createAlias("problemStatus", "probStatus")
        .createAlias("probLoc.problemAndProblemSource", "probSource")
        .add(Expression.eq("user.registrationId", userId))
        
        .add(Restrictions.or(
		        Restrictions.eq( "isDelete", "false" ),
		        Restrictions.isNull("isDelete")))
        
        .setMaxResults(maxResults)
        .setFirstResult(startIndex)
        .addOrder(Order.desc("dateUpdated"));

     return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(
			Long userId, Integer startIndex, Integer maxResults) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select model.problemLocation.problemAndProblemSource.problem.problemId,"+
				"model.problemHistoryId,model.problemLocation.problemAndProblemSource.problem.problem,"+
				"model.problemLocation.problemAndProblemSource.problem.description,"+
	            "model.problemLocation.problemAndProblemSource.problem.identifiedOn,"+
	            "model.problemLocation.problemAndProblemSource.problem.existingFrom,"+
	            "model.problemLocation.problemAndProblemSource.problemSource.informationSource,"+
	            "model.problemStatus.status,model.problemLocation.problemImpactLevel from "+
	            "ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId= ? "+
	            "and model.isDelete is null or model.isDelete = 'false' order by date(model.dateUpdated) desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, userId);
				
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
		
	 return queryObject.list();
	}
	
	public Long getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(Long userId){
		
		Long resultsCount = ( (Long) getSession().createQuery("select count(model.problemHistoryId) from ProblemHistory model where "+
				"model.problemLocation.problemAndProblemSource.user.registrationId = "+userId+" and "+
				"model.isDelete is null or model.isDelete = 'false' order by date(dateUpdated) desc").iterate().next() );
		
	 return resultsCount;
	}

	@SuppressWarnings("unchecked")
	public List getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(Long userId,Long statusId,Date startDate,Date endDate){
		
		StringBuilder query = new StringBuilder();
				
		query.append("select count(model.problemHistoryId) from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? ");
		//If problems by problem status 
		if(statusId != null && !statusId.equals(0L))
			query.append("and model.problemStatus.problemStatusId = "+statusId+ " ");
		
		if(startDate != null && endDate != null){
			query.append("and date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
			
		}
		else{
			if(startDate != null)
				query.append("and date(model.dateUpdated) == ? ");
						
			if(endDate != null)
				query.append("and date(model.dateUpdated) == ? ");
				
		}
		
		query.append("and model.isDelete is null or model.isDelete = 'false' ");
		query.append("order by date(model.dateUpdated) desc");
		
		Query queryObject = getSession().createQuery(query.toString());
				
		queryObject.setParameter(0, userId);
		
		if(startDate != null && endDate != null){
			queryObject.setParameter(1, startDate);
			queryObject.setParameter(2, endDate);
		}else{
			if(startDate != null)
				queryObject.setParameter(1, startDate);
			if(endDate != null)
				queryObject.setParameter(2, endDate);				
		}
		
						
     return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(
			Long userId,Long statusId, Date startDate, Date endDate, Integer startIndex,
			Integer maxResults) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? ");
		//If problems by problem status 
		if(statusId != null && !statusId.equals(0L))
			query.append("and model.problemStatus.problemStatusId = "+statusId+ " ");
		
		if(startDate != null && endDate != null){
			query.append("and date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
			
		}
		else{
			if(startDate != null)
				query.append("and date(model.dateUpdated) == ? ");
						
			if(endDate != null)
				query.append("and date(model.dateUpdated) == ? ");
				
		}
		
		query.append("and model.isDelete is null or model.isDelete = 'false' ");
		query.append("order by date(model.dateUpdated) desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		
		if(startDate != null && endDate != null){
			queryObject.setParameter(1, startDate);
			queryObject.setParameter(2, endDate);
		}else{
			if(startDate != null)
				queryObject.setParameter(1, startDate);
			if(endDate != null)
				queryObject.setParameter(2, endDate);				
		}
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(maxResults);
				
     return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getProblemHistoryBasedOnId(Long historyId){
		
		return getHibernateTemplate().find("from ProblemHistory model where model.problemHistoryId = ?",historyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getTotalProblemsCountForAnUserInARegion(Long userId,String locationStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select model.problemStatus.status from ProblemHistory model where " +
				" model.problemLocation.problemAndProblemSource.user.registrationId = ? "+locationStr+" and model.isDelete is null and model.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getStatusWiseProblemsForAnUserInARegion(Long userId,String locationStr,String statusStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" from ProblemHistory model where " +
				" model.problemLocation.problemAndProblemSource.user.registrationId = ? "+locationStr+" "+statusStr+" and model.isDelete is null and model.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getTotalProblemsStatusForAnUser(Long userId)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" select model.problemStatus.status from ProblemHistory model where " +
				" model.problemLocation.problemAndProblemSource.user.registrationId = ?  and model.isDelete is null and model.isApproved = 'true' ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> getStatusWiseProblemsForAnUser(Long userId,String statusStr)
	{
		Object[] params = {userId};
		return getHibernateTemplate().find(" from ProblemHistory model where " +
				" model.problemLocation.problemAndProblemSource.user.registrationId = ? "+statusStr+" and model.isDelete is null and model.isApproved = 'true' ",params);
	}
	 @SuppressWarnings("unchecked")
	public List<Object[]> getCompleteProblemDetailsBySearchString(String query,Date fromDate,Date toDate){
		
		StringBuilder queryString = new StringBuilder();
		
		queryString.append("select model.problemLocation.problemAndProblemSource.problem.problem,"+
				 "model.problemLocation.problemAndProblemSource.problem.description,model.problemStatus.status,"+
				 "model.problemLocation.problemAndProblemSource.problem.identifiedOn,"+
				 "model.problemLocation.problemAndProblemSource.problemSource.informationSource,"+
				 "model.problemLocation.problemImpactLevel.scope ,model.problemHistoryId from ProblemHistory model where "+query+" and model.isDelete is null and model.isApproved = 'true' group by model.problemLocation.problemAndProblemSource.problem.problem.problemId");
	
		Query queryObj = getSession().createQuery(queryString.toString());
		if(fromDate !=null && toDate!=null){
			queryObj.setParameter(0, fromDate);
			queryObj.setParameter(1, toDate);
		}
		else
		{
			if(fromDate!=null){
				queryObj.setParameter(0, fromDate);
			}
			if(toDate!=null){
				queryObj.setParameter(0, toDate);
			}
		}
		return queryObj.list(); 
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemDetailsByName(String name){
		
		return getHibernateTemplate().find("select model.problemLocation.problemAndProblemSource.problem.problem,"+
				 "model.problemLocation.problemAndProblemSource.problem.description,model.problemStatus.status,"+
				 "model.problemLocation.problemAndProblemSource.problem.identifiedOn FROM ProblemHistory model where model.problemLocation.problemAndProblemSource.user.userName ="+name+" or "+
				 "model.problemLocation.problemAndProblemSource.externalUser.username="+name+" or "+
				 "model.problemLocation.problemAndProblemSource.problemExternalSource.name="+name+" "+
				 "and model.isDelete is null and model.isApproved = 'true' ");
	}
}
