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
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.hamletId = ? and model.isDelete is null",hamletId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByTehsilId(Long tehsilId) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.isDelete is null)",tehsilId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(String constituencyIds) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId" +
				" ,model.problemLocation.problemAndProblemSource.user.registrationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +
				") and model.isDelete is null ");
	}

	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByHamletId(Long hamletId,String status) {
		Object[] params = {hamletId,status};
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.hamletId = ? and model.problemStatus.status = ? " +
				" and model.isDelete is null",params);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByTehsilId(Long tehsilId,String status) {
		Object[] params = {tehsilId,status};
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.problemStatus.status = ? " +
				" and model.isDelete is null",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByConstituencyId(String constituencyIds, String status) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated,model.problemLocation.problemLocationId,model.problemLocation.problemAndProblemSource.user.registrationId" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +
				") and model.problemStatus.status = ? and model.isDelete is null",status);
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
	
}
