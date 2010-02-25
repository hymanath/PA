package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.ProblemHistory;

public class ProblemHistoryDAO extends GenericDaoHibernate<ProblemHistory, Long> implements IProblemHistoryDAO{

	public ProblemHistoryDAO(){
		super(ProblemHistory.class);
	}
		
	@SuppressWarnings("unchecked")
	public List<ProblemHistory> findProblemLocationsByUserId(Long registrationId, Long statusId){
		Object [] params = {registrationId, statusId};
		return getHibernateTemplate().find("from ProblemHistory model where model.problemLocation.problemAndProblemSource.user.registrationId = ? " +
				"and model.problemStatus.problemStatusId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByHamletId(Long hamletId){
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.hamletId = ?",hamletId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByTehsilId(Long tehsilId) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId = ?",tehsilId);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsForALocationsByConstituencyId(String constituencyIds) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +
				") order by model.problemLocation.hamlet.hamletName");
	}

	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByHamletId(Long hamletId,
			String status) {
		Object[] params = {hamletId,status};
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.hamletId = ? and model.problemStatus.status = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByTehsilId(Long tehsilId,
			String status) {
		Object[] params = {tehsilId,status};
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId = ? and model.problemStatus.status = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findProblemsByStatusForALocationsByConstituencyId(
			String constituencyIds, String status) {
		return getHibernateTemplate().find(" Select model.problemStatus.status," +
				" model.problemLocation.hamlet.hamletName," +
				" model.problemLocation.problemAndProblemSource.problem.identifiedOn," +
				" model.problemLocation.problemAndProblemSource.problem.problem," +
				" model.problemHistoryId,model.comments,model.problemLocation.problemAndProblemSource.problemExternalSource.problemExternalSourceId," +
				" model.problemLocation.problemAndProblemSource.problem.description,model.dateUpdated" +
				" from ProblemHistory model where model.problemLocation.hamlet.township.tehsil.tehsilId in (  " + constituencyIds +
				") and model.problemStatus.status = ?",status);
	}	
}
