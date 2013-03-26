/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

public class CommentCategoryCandidateDAO extends GenericDaoHibernate<CommentCategoryCandidate, Long>
		implements ICommentCategoryCandidateDAO {

	public CommentCategoryCandidateDAO() {
		super(CommentCategoryCandidate.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getCommentsOnACandidateInAConstituency(
			String electionType, String electionYear, Long candidateId,
			Long constituencyId, Long userId, String hqlQuery) {
		Object[] params = {electionType,electionYear,candidateId,constituencyId, userId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.nomination.constituencyElection.election.electionYear = ?" +
				" and model.nomination.candidate.candidateId = ? and model.nomination.constituencyElection.constituency.constituencyId = ? " +
				hqlQuery, params);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAnElection(Long electionId,Long candidateId, Long userId, String hqlQuery){
		Object[] params = {electionId, candidateId, userId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? " +
				"and model.nomination.candidate.candidateId = ? "+hqlQuery,params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidate(
			String electionType, String electionYear, Long candidateId, Long userId, String hqlQuery) {
		Object[] params = {electionType,electionYear,candidateId, userId};
		return getHibernateTemplate().find("from CommentCategoryCandidate model where " +
				"model.nomination.constituencyElection.election.electionScope.electionType.electionType = ?"+
				" and model.nomination.constituencyElection.election.electionYear = ?" +
				" and model.nomination.candidate.candidateId = ? "+hqlQuery, params);

	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryCandidate> getAllCommentsOnACandidateInAllElections(Long candidateId) {
		return getHibernateTemplate().find("from CommentCategoryCandidate model where model.nomination.candidate.candidateId = ?", candidateId);
	}
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidate(Long candidateId,
			Long constituencyId, String electionType, String electionYear) {
		Object[] params = {candidateId,constituencyId,electionType,electionYear};
		return getHibernateTemplate().find("select count(model.commentCategoryCandidateId) from CommentCategoryCandidate model"+
				" where model.nomination.candidate.candidateId = ? and model.nomination.constituencyElection.constituency.constituencyId = ? and"+
				" model.nomination.constituencyElection.election.electionScope.electionType.electionType = ? and model.nomination.constituencyElection.election.electionYear = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountForAPartyInAnElection(Long electionId,Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,
			Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType,model.nomination.candidateResult.rank,model.nomination.nominationId "+
				"from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? "+
				"and model.nomination.party.partyId = ? order by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidateInAConstituencyInAnELection(
			Long electionId, Long candidateId, Long constituencyId) {
		Object[] params = {electionId,candidateId,constituencyId};
		return getHibernateTemplate().find("select count(model.commentData.commentDataId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.candidate.candidateId = ?"+
				" and model.nomination.constituencyElection.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountInAnElectionForAPartyForCommentCategory(Long electionId,Long partyId,String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountGroupedByCommentCategory(Long electionId,
			Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId),model.commentData.commentDataCategory.commentDataCategoryId,model.commentData.commentDataCategory.commentDataCategoryType from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.commentData.commentDataCategory.commentDataCategoryType",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getCommentsCountAndScoreGroupedByCommentCategory(Long electionId,Long partyId,String category) {
		Object[] params = {electionId,partyId,category};
		
		return getHibernateTemplate().find("select count(distinct model.nomination.constituencyElection.constituency.constituencyId),model.commentData.commentDataCategory.commentDataCategoryId,model.commentData.commentDataCategory.commentDataCategoryType, sum(model.severity) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.commentData.commentDataCategory.commentDataCategoryId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalPostedPaidUsersGroupedByCommentCategory(Long electionId,Long partyId,String category) {
		Object[] params = {electionId,partyId,category};
		
		return getHibernateTemplate().find("select count(distinct model.user.userId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.nomination.party.partyId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getTotalPostedFreeUsersGroupedByCommentCategory(Long electionId,Long partyId,String category) {
		Object[] params = {electionId,partyId,category};
		
		return getHibernateTemplate().find("select count(distinct model.user.userId) from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.nomination.party.partyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCommentCategoryCountGroupedByConstituencyForAParty(
			Long electionId, Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select count(distinct model.commentData.commentDataCategory.commentDataCategoryType),model.nomination.constituencyElection.constituency.constituencyId,"+
				"model.nomination.constituencyElection.constituency.name from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?"+
				" and model.commentData.commentDataCategory.commentClassification = ?"+
				" group by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getNominationsForCandidateHavingComments(Long electionId,
			Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("select distinct model.nomination.nominationId from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,
			Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType,model.nomination.candidateResult.rank, model.nomination.nominationId "+
				"from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? "+
				"and model.nomination.party.partyId = ? and model.commentData.commentDataCategory.commentClassification = ? order by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getNominationsForCandidateHavingComments(Long electionId,
			Long partyId, String category) {
		Object[] params = {electionId,partyId,category};
		return getHibernateTemplate().find("select distinct model.nomination.nominationId from CommentCategoryCandidate model"+
				" where model.nomination.constituencyElection.election.electionId = ?"+
				" and model.nomination.party.partyId = ? and model.commentData.commentDataCategory.commentClassification = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentsResultsForAPartyInAnElection(Long electionId,
			Long partyId, String category, Long categoryTypeId) {
		Object[] params = {electionId,partyId,category,categoryTypeId};
		return getHibernateTemplate().find("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType,model.nomination.candidateResult.rank, model.nomination.nominationId "+
				"from CommentCategoryCandidate model where model.nomination.constituencyElection.election.electionId = ? "+
				"and model.nomination.party.partyId = ? and model.commentData.commentDataCategory.commentClassification = ? "+
				"and model.commentData.commentDataCategory.commentDataCategoryId = ? order by model.nomination.constituencyElection.constituency.constituencyId",params);
	}

	@SuppressWarnings("unchecked")
	public List getCommentResultsForCandidateNominations(
			List<Long> nominationIds) {
		Query queryObject = getSession().createQuery("select model.commentData.commentDataCategory.commentDataCategoryId,model.commentData.commentDataCategory.commentDataCategoryType,"+
				"count(distinct model.nomination.constituencyElection.constituency.constituencyId), sum(model.severity) from CommentCategoryCandidate model where "+
				"model.nomination.nominationId in (:nominationIds) group by model.commentData.commentDataCategory.commentDataCategoryId");
		queryObject.setParameterList("nominationIds", nominationIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCommentDetailsForSetOfNominations(List<Long> nominationIds) {
		Query queryObject = getSession().createQuery("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType,model.nomination.candidateResult.rank,model.nomination.nominationId from CommentCategoryCandidate model where "+
				"model.nomination.nominationId in (:nominationIds) order by model.nomination.constituencyElection.constituency.constituencyId");
		queryObject.setParameterList("nominationIds", nominationIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCommentDetailsForSetOfNominations(List<Long> nominationIds,
			Long categoryTypeId) {
		Query queryObject = getSession().createQuery("select model.nomination.constituencyElection.constituency.constituencyId,model.nomination.constituencyElection.constituency.name,"+
				"model.nomination.candidate.candidateId,model.nomination.candidate.lastname,"+
				"model.commentData.commentDesc,model.commentData.commentBy,"+
				"model.commentData.commentDate,model.commentData.commentDataCategory.commentDataCategoryType,model.nomination.candidateResult.rank,model.nomination.nominationId from CommentCategoryCandidate model where "+
				"model.commentData.commentDataCategory.commentDataCategoryId = ? and "+
				"model.nomination.nominationId in (:nominationIds) order by model.nomination.constituencyElection.constituency.constituencyId");
		queryObject.setParameter(0, categoryTypeId);
		queryObject.setParameterList("nominationIds", nominationIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getAnalyzedConstituenciesCountFromNominationIds(
			List<Long> nominationIds) {
		Query queryObject = getSession().createQuery("select count(distinct model.nomination.constituencyElection.constituency.constituencyId) "+
				"from CommentCategoryCandidate model where model.nomination.nominationId in (:nominationIds)");
		queryObject.setParameterList("nominationIds", nominationIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCommentsCountForACandidateFromNominationId(Long nominationId) {
		return getHibernateTemplate().find("select count(distinct model.commentData.commentDataCategory.commentDataCategoryId) "+
				"from CommentCategoryCandidate model where model.nomination.nominationId = ? group by model.commentData.commentDataCategory.commentDataCategoryId",nominationId);
	}
	
	/*public List getAllCommentsByFreeUserAndCategoryForANomination(Long nominationId){
		Object[] params = {nominationId};
		return getHibernateTemplate().find("select model.freeUser.userId, model.freeUser.name, model.commentData.commentDesc, " +
				"model.commentData.commentDataCategory.commentDataCategoryType, model.commentData.commentDataCategory.commentDataCategoryId, " +
				"model.commentData.commentDataCategory.commentClassification, model.severity from CommentCategoryCandidate model where " +
				"model.nomination.nominationId = ? group by model.freeUser.userId, model.commentData.commentDataCategory.commentDataCategoryId", params);
	}*/
	
		public List getAllCommentsAndCategoryForANomination(Long nominationId){
		Object[] params = {nominationId};
		return getHibernateTemplate().find("select model.user.userId, model.user.firstName, model.commentData.commentDesc, " +
				"model.commentData.commentDataCategory.commentDataCategoryType, model.commentData.commentDataCategory.commentDataCategoryId, " +
				"model.commentData.commentDataCategory.commentClassification, model.severity, model.user.lastName from CommentCategoryCandidate model where " +
				"model.nomination.nominationId = ? group by model.user.userId, model.commentData.commentDataCategory.commentDataCategoryId", params);
	}
		
	/*public List getAllCommentsByPaidUserAndCategoryForANomination(Long nominationId){
		Object[] params = {nominationId};
		return getHibernateTemplate().find("select model.user.userId, model.user.firstName, model.commentData.commentDesc, " +
				"model.commentData.commentDataCategory.commentDataCategoryType, model.commentData.commentDataCategory.commentDataCategoryId, " +
				"model.commentData.commentDataCategory.commentClassification, model.severity from CommentCategoryCandidate model where " +
				"model.nomination.nominationId = ? group by model.user.userId, model.commentData.commentDataCategory.commentDataCategoryId", params);
	}*/

	public List getAllCommentsOfUserForANomination(Long electionId,
			Long constituencyId, Long candidateId, Long userId, String hqlQuery) {
		Object[] params = {electionId, constituencyId, candidateId, userId};
		return getHibernateTemplate().find("select model.commentCategoryCandidateId, model.severity from CommentCategoryCandidate model where " +
				"model.nomination.constituencyElection.election.electionId = ? and model.nomination.constituencyElection.constituency.constituencyId = ? " +
				"and model.nomination.candidate.candidateId = ? "+hqlQuery, params);
	}
	
	public String getCommonDataForAllProblems(){
		StringBuilder conditionQuery = new StringBuilder();
		conditionQuery.append(" select model.commentData.commentDataId,");	
		conditionQuery.append(" model.commentData.commentDesc,");
		conditionQuery.append(" model.commentData.commentDate,");
		conditionQuery.append(" model.commentData.commentBy,");		
		conditionQuery.append(" model.nomination.candidate.candidateId,");
		conditionQuery.append(" model.nomination.candidate.lastname,");
		conditionQuery.append(" model.nomination.party.shortName,");
		conditionQuery.append(" model.nomination.constituencyElection.constituency.name, ");
		conditionQuery.append(" model.nomination.candidateResult.rank, ");
		conditionQuery.append(" model.nomination.constituencyElection.election.electionScope.electionType.electionType, ");
		conditionQuery.append(" model.nomination.constituencyElection.election.electionYear, ");
		conditionQuery.append("model.commentData.isApproved ");
		
		conditionQuery.append(" from CommentCategoryCandidate model ");
		return conditionQuery.toString();
	}
	
	public List getAllCommentsBetweenDates(Date fromDate, Date toDate, String isApproved)
	{
		Object[] params = {fromDate, toDate, isApproved};
		StringBuilder query = new StringBuilder();
		query.append(getCommonDataForAllProblems());		
		query.append(" where date(model.commentData.commentDate) >= ? and date(model.commentData.commentDate) <= ? ");
		query.append(" and model.commentData.isApproved = ? ");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setDate(0,fromDate);
		queryObject.setDate(1,toDate);
		queryObject.setString(2,isApproved);
		queryObject.setMaxResults(100);
		return queryObject.list();
	}
	public List getAllOpenedComments(Date fromDate, Date toDate)
	{
		StringBuilder query = new StringBuilder();
		query.append(getCommonDataForAllProblems());		
		query.append(" where date(model.commentData.commentDate) >= ? and date(model.commentData.commentDate) <= ? ");
		//query.append(" and (model.commentData.isApproved ='true' and model.commentData.isApproved = 'false')");	
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setDate(0,fromDate);
		queryObject.setDate(1,toDate);
		queryObject.setMaxResults(100);
		return queryObject.list();
	}
	public List getAllOpenedApprovedComments(Date fromDate, Date toDate,String status)
	{
		StringBuilder query = new StringBuilder();
		query.append(getCommonDataForAllProblems());		
		query.append(" where date(model.commentData.commentDate) >= ? and date(model.commentData.commentDate) <= ? ");
		
		if(status.equals("Approved"))
		{
			query.append(" and model.commentData.isApproved ='true' ");
		}
		else 
		{
			query.append(" and model.commentData.isApproved ='false' ");	
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setDate(0,fromDate);
		queryObject.setDate(1,toDate);
		queryObject.setMaxResults(100);
		return queryObject.list();
	}
	
	/*
	public List getPostedReasonsByFreeUserId(Long userId)
	{		
		StringBuilder query = new StringBuilder();
		query.append(" select model.commentData.commentDataId,");	
		query.append(" model.commentData.commentDesc,");
		query.append(" model.commentData.commentDate,");
		query.append(" model.commentData.commentBy,");			
		query.append(" model.nomination.candidate.candidateId,");
		query.append(" model.nomination.candidate.lastname,");
		query.append(" model.nomination.party.shortName,");
		query.append(" model.nomination.constituencyElection.constituency.name, ");
		query.append(" model.nomination.candidateResult.rank, ");
		query.append(" model.nomination.constituencyElection.election.electionScope.electionType.electionType, ");
		query.append(" model.nomination.constituencyElection.election.electionYear, ");
		query.append(" model.commentData.isApproved ");
		
		query.append(" from CommentCategoryCandidate model ");
		query.append(" where model.freeUser.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		queryObject.setMaxResults(100);
		
		return queryObject.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List getPostedReasonsByFreeUserId(Long userId, Integer startIndex, Integer results, 
			String order, String columnName, String reasonType,List<Long> connectedUserIds)
	{	
		StringBuilder query = new StringBuilder();
		query.append(" select model.commentData, ");			
		query.append(" model.nomination.candidate.candidateId,");
		query.append(" model.nomination.candidate.lastname,");
		query.append(" model.nomination.party.shortName,");
		query.append(" model.nomination.constituencyElection.constituency.name, ");
		query.append(" model.nomination.candidateResult.rank, ");
		query.append(" model.nomination.constituencyElection.election.electionScope.electionType.electionType, ");
		query.append(" model.nomination.constituencyElection.election.electionYear, ");
		query.append(" model.user.userId,model.user.profileImg");
		query.append(" from CommentCategoryCandidate model ");
		
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append(" where model.user.userId is not null and model.commentData.isApproved = '"+IConstants.TRUE+"'");
		if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append(" where model.user.userId = ? ");
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
		{
			query.append(" where model.user.userId != ? and model.commentData.isApproved = '"+IConstants.TRUE+"'");
			if(connectedUserIds != null && connectedUserIds.size() > 0)
				query.append(" and model.user.userId not in (:connectedUserIds)");
		}
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append(" where model.user.userId = ? and model.commentData.isApproved = '"+IConstants.TRUE+"' ");
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append(" where model.user.userId = ? and model.commentData.isApproved = '"+IConstants.FALSE+"' ");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append(" where model.user.userId = ? and model.commentData.isApproved is null");		
		else if(reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons") && connectedUserIds != null && connectedUserIds.size() > 0)
			query.append(" where model.user.userId in (:connectedUserIds) and model.commentData.isApproved = '"+IConstants.TRUE+"'");
			
			
		query.append(" order by "+columnName+" "+order);
		
		Query queryObject = getSession().createQuery(query.toString());
		
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType) && !reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons"))
			queryObject.setParameter(0, userId);
		
		if(connectedUserIds != null && connectedUserIds.size() > 0 && (reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons") || reasonType.equalsIgnoreCase(IConstants.OTHERUSERS)))
			queryObject.setParameterList("connectedUserIds", connectedUserIds);
		
		queryObject.setFirstResult(startIndex);		
		queryObject.setMaxResults(results);
		
		return queryObject.list();
	}
	
	public Long getTotalPostedReasonsCountByFreeUserId(Long userId)
	{	
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.commentCategoryCandidateId) ");		
		query.append(" from CommentCategoryCandidate model ");
		query.append(" where model.user.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
				
		return (Long)queryObject.uniqueResult();
	}
	
	public Long getTotalPostedReasonsCount(String reasonType, Long userId,List<Long> connectedUserIds)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.commentCategoryCandidateId) ");		
		query.append(" from CommentCategoryCandidate model where ");
		if(reasonType.equalsIgnoreCase(IConstants.TOTAL))
			query.append(" model.user.userId is not null and model.commentData.isApproved = '"+IConstants.TRUE+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.LOGGED_USER))
			query.append(" model.user.userId = ? ");
		else if(reasonType.equalsIgnoreCase(IConstants.OTHERUSERS))
		{
			query.append(" model.user.userId != ? and model.commentData.isApproved = '"+IConstants.TRUE+"'");
			if(connectedUserIds != null && connectedUserIds.size() > 0)
				query.append(" and model.user.userId not in (:connectedUserIds) ");
		}
		else if(reasonType.equalsIgnoreCase(IConstants.APPROVED))
			query.append(" model.user.userId = ? and model.commentData.isApproved = '"+IConstants.TRUE+"'");
		else if(reasonType.equalsIgnoreCase(IConstants.REJECTED))
			query.append(" model.user.userId = ? and model.commentData.isApproved = '"+IConstants.FALSE+"' ");
		else if(reasonType.equalsIgnoreCase(IConstants.NOTCONSIDERED))
			query.append(" model.user.userId = ? and model.commentData.isApproved is null");
		else if(reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons") && connectedUserIds != null && connectedUserIds.size() > 0)
			query.append(" model.user.userId in (:connectedUserIds) and model.commentData.isApproved = '"+IConstants.TRUE+"'");
		
		Query queryObject = getSession().createQuery(query.toString());
		if(!IConstants.TOTAL.equalsIgnoreCase(reasonType) && !reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons"))
			queryObject.setParameter(0, userId);
		if(connectedUserIds != null && connectedUserIds.size() > 0 && (reasonType.equalsIgnoreCase("ConnectedUserPoliticalReasons") || reasonType.equalsIgnoreCase(IConstants.OTHERUSERS)))
			queryObject.setParameterList("connectedUserIds", connectedUserIds);
				
		return (Long)queryObject.uniqueResult();
		
	}
	
	public Long getTotalPostedReasonsCountByFreeUserId()
	{	
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.commentCategoryCandidateId) ");		
		query.append(" from CommentCategoryCandidate model ");
		query.append(" where model.user.userId is not null");
		
		Query queryObject = getSession().createQuery(query.toString());
						
		return (Long)queryObject.uniqueResult();
	}
	
	public List getPostedReasonsCountByFreeUserId(Long userId)
	{
		return getHibernateTemplate().find("select count(model.commentCategoryCandidateId), model.commentData.isApproved "+
				"from CommentCategoryCandidate model where model.user.userId = ? group by model.commentData.isApproved",userId);
	}
	
	public List getPostedReasonsCountOtherThanLoginUserId(Long userId)
	{
		return getHibernateTemplate().find("select count(*) "+
				"from CommentCategoryCandidate model where model.user.userId != ? and model.user.userId != null and model.commentData.isApproved = '"+IConstants.TRUE+"'",userId);
	}
	@SuppressWarnings("unchecked")
	/*public List<Object[]> getUsersBasedOnReasonIds(List<Long> reasonIds)
	{
		Query queryObject = getSession().createQuery("select model.freeUser.userId,model.freeUser.email,model.commentData.commentBy,model.nomination.candidate.lastname,model.nomination.candidateResult.rank, model.nomination.constituencyElection.constituency.name,model.nomination.constituencyElection.election.electionScope.electionType.electionType,model.freeUser.lastName from CommentCategoryCandidate model where model.commentData.commentDataId in (:reasonIds)");
		queryObject.setParameterList("reasonIds", reasonIds);
		return queryObject.list();
			
	}*/
	
	public List<Object[]> getUsersBasedOnReasonIds(List<Long> reasonIds)
	{
		Query queryObject = getSession().createQuery("select model.user.userId,model.user.email,model.commentData.commentBy,model.nomination.candidate.lastname,model.nomination.candidateResult.rank, model.nomination.constituencyElection.constituency.name,model.nomination.constituencyElection.election.electionScope.electionType.electionType,model.user.lastName,model.user.firstName from CommentCategoryCandidate model where model.commentData.commentDataId in (:reasonIds)");
		queryObject.setParameterList("reasonIds", reasonIds);
		return queryObject.list();
			
	}
	
	public List getConnectedUsersPostedReasonsCount(List<Long> connectedUserIds)
   	{
   		StringBuilder queryString = new StringBuilder();
   		queryString.append("select count(model.commentCategoryCandidateId), model.commentData.isApproved from CommentCategoryCandidate model where model.user.userId in (:connectedUserIds) ");
   		queryString.append(" group by model.commentData.isApproved");
   		Query queryObj = getSession().createQuery(queryString.toString());
   		queryObj.setParameterList("connectedUserIds", connectedUserIds);
   		return queryObj.list();
   		
   	}
	
	public List getPostedPoliticalReasonsByUserId(Long userId,int startIndex,int maxIndex)
	{
		StringBuilder queryString = new StringBuilder();
		
		queryString.append("select model.commentData, ");
		queryString.append(" model.nomination.candidate.candidateId, ");
		queryString.append(" model.nomination.candidate.lastname, ");
		queryString.append(" model.nomination.party.shortName, ");
		queryString.append(" model.nomination.constituencyElection.constituency.name, ");
		queryString.append(" model.nomination.candidateResult.rank, ");
		queryString.append(" model.nomination.constituencyElection.election.electionScope.electionType.electionType, ");
		queryString.append(" model.nomination.constituencyElection.election.electionYear, ");
		queryString.append(" model.user.userId,model.user.profileImg, model.commentData.commentDate ");
		queryString.append(" from CommentCategoryCandidate model ");
		queryString.append(" where model.user.userId = ? and model.commentData.isApproved ='"+IConstants.TRUE+"' ");
		queryString.append(" order by model.commentData.commentDate desc ");
		Query queryObj = getSession().createQuery(queryString.toString());
		queryObj.setParameter(0, userId);
		queryObj.setFirstResult(startIndex);
		queryObj.setMaxResults(maxIndex);
		return queryObj.list();
	}
	
	
	public List getAllPostedCommentsOfUserForANomination(Long electionId,
			Long constituencyId, Long candidateId,Long userId) {
		Object[] params = {electionId, constituencyId, candidateId,userId};
		return getHibernateTemplate().find("select model.commentCategoryCandidateId, model.severity from CommentCategoryCandidate model where " +
				"model.nomination.constituencyElection.election.electionId = ? and model.nomination.constituencyElection.constituency.constituencyId = ? " +
				"and model.nomination.candidate.candidateId = ? and model.user.userId = ?", params);
	}
		
}
