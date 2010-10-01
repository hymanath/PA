package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemHistory;

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
	public List findProblemsForALocationsByHamletId(Long hamletId){
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);		
		conditionQuery.append(" where model.problemLocation.hamlet.hamletId = ? and model.isDelete is null");
		
		return getHibernateTemplate().find(conditionQuery.toString(),hamletId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByTehsilId(Long tehsilId) {
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.isDelete is null)");		
		return getHibernateTemplate().find(conditionQuery.toString(),tehsilId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(String constituencyIds) {
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +") and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString());
	}

	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByHamletId(Long hamletId,String status) {
		Object[] params = {hamletId,status};
		
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.hamletId = ? and model.problemStatus.status = ? and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}

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
	public List findProblemsByStatusForALocationsByConstituencyId(String constituencyIds, String status) {
		String query = buildCommonQuery();
		
		StringBuilder conditionQuery = new StringBuilder();		
		conditionQuery.append(query);
		conditionQuery.append(" where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +") and model.problemStatus.status = ? and model.isDelete is null");
		return getHibernateTemplate().find(conditionQuery.toString(),status);
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
	
	public List findLatestProblemsGroupByDatePostedByMandalsAndStatus(String tehsilIds, String statusIds){
		return getHibernateTemplate().find("select count(model.problemHistoryId), model.dateUpdated, model.problemStatus.problemStatusId, " +
				"model.problemStatus.status from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in " +
				"(" + tehsilIds +") and model.problemStatus.problemStatusId in("+statusIds+")  " +
						" group by date(model.dateUpdated),model.problemStatus.problemStatusId order by model.dateUpdated desc");
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
		query.append(" Select model.problemStatus.status," );
		query.append(" model.problemLocation.hamlet.hamletName,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.identifiedOn,");
		query.append(" model.problemLocation.problemAndProblemSource.problem.problem,");
		query.append(" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId,"); 
		query.append(" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId,");
		query.append(" model.problemLocation.problemAndProblemSource.user.registrationId");
		query.append(" from ProblemHistory model ");
		return query.toString();		
	}
	
	
	public String getCommonDataForAllProblems(){
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(" Select model.problemLocation.problemAndProblemSource.problem.problem,");	
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.description,");
		conditionQuery.append(" model.problemLocation.problemImpactLevel.problemImpactLevel,");
		conditionQuery.append(" model.problemLocation.problemImpactLevelValue,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.identifiedOn,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.externalUser.name,");
		conditionQuery.append(" model.problemLocation.problemAndProblemSource.problem.problemId,");
		conditionQuery.append(" model.problemHistoryId");
		conditionQuery.append(" from ProblemHistory model ");
		return conditionQuery.toString();
	}
	
	///Uncompleted Query...........
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsPostedForCurrentDay(Date date,String status,String isApproved){
		Object[] params = {date,status,isApproved};
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(getCommonDataForAllProblems());
		
		conditionQuery.append(" where date(model.dateUpdated) = ? and model.problemStatus.status = ? ");	
		conditionQuery.append(" and model.isApproved = ? ");	
		//conditionQuery.append(" and model.problemLocation.problemAndProblemSource.user is null ");
		conditionQuery.append(" order by date(model.dateUpdated)");	
		
		return getHibernateTemplate().find(conditionQuery.toString(),params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsBetweenDatesWithCompleteData(Date fromDate, Date toDate, String status,String isApproved){
		Object[] params = {fromDate, toDate, status,isApproved};
		StringBuilder query = new StringBuilder();
		query.append(getCommonDataForAllProblems());		
		query.append(" where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
		query.append(" and model.problemStatus.status = ? and model.isApproved = ? ");		
		return getHibernateTemplate().find(query.toString(),params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllNonApprovedProblemsBetweenDates(Date fromDate, Date toDate, String status,String isApproved){
		Object[] params = {fromDate, toDate, status,isApproved};		
		
		StringBuilder query = new StringBuilder();		
		query.append("select count(model.dateUpdated),date(model.dateUpdated) from ProblemHistory model ");
		query.append(" where date(model.dateUpdated) >= ? and date(model.dateUpdated) <= ? ");
		query.append(" and model.problemStatus.status = ? and model.isApproved = ? ");
		//query.append(" and model.problemLocation.problemAndProblemSource.user is null " +
		query.append(" group by date(model.dateUpdated)");			
		return getHibernateTemplate().find(query.toString(),params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(Date date,String status,String isApproved){
		Object[] params = {date, status,isApproved};		
		
		StringBuilder query = new StringBuilder();		
		query.append( "select count(model.dateUpdated),model.problemLocation.problemImpactLevel.problemImpactLevel from ProblemHistory model ");
		query.append(" where date(model.dateUpdated) = ? and model.problemStatus.status = ? and model.isApproved = ? ");
		//query.append(" and model.problemLocation.problemAndProblemSource.user is null " +
		query.append(" group by model.problemLocation.problemImpactLevel.problemImpactLevelId");			
		return getHibernateTemplate().find(query.toString(),params);
	}
}
