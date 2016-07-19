package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.model.NominatedPostFinal;

public class NominatedPostFinalDAO extends GenericDaoHibernate<NominatedPostFinal, Long> implements INominatedPostFinalDAO{

	public NominatedPostFinalDAO() {
		super(NominatedPostFinal.class);
	}

	public List<Object[]> getFinalShortListedApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.nominatedPost.nominatedPostMember.boardLevelId, count(model.nominatedPostFinalId)  from NominatedPostFinal model where  " +
				" model.applicationStatus.status ='Shortlisted'  ");
		
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and  model.nominatedPost.nominatedPostMember.boardLevelId = :boardLevelId ");
		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominatedPost.insertedTime) between :startDate and :endDate ");
		
		queryStr.append(" group by  model.nominatedPost.nominatedPostMember.boardLevelId order by model.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select NPA.nominationPostCandidate.nominationPostCandidateId," +
					" TC.tdpCadreId," +
					" NPA.nominationPostCandidate.voterId," +
					" NPA.nominationPostCandidate.candidateName," +
					" NPA.nominationPostCandidate.mobileNo," +
					" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +
					" CC.categoryName," +
					" CCG.casteCategoryGroupName," +
					" caste.casteName," +
					" NPA.applicationStatus.applicationStatusId," +
					" NPA.applicationStatus.status," +
					" NPA.nominatedPostApplicationId");
					//" NPA.nominatedPost.nominatedPostId");
		sb.append(" from NominatedPostApplication NPA " +
					" left join NPA.nominationPostCandidate.tdpCadre TC" +
					" left join TC.casteState CS" +
					" left join CS.casteCategoryGroup CCG" +
					" left join CCG.casteCategory CC" +
					" left join CS.caste caste" +
					" where NPA.boardLevel.boardLevelId = :levelId" +
					" and NPA.locationValue = :levelValue" +
					" and NPA.departments.departmentId = :departmentId");
		
		if(type.equalsIgnoreCase("this") && boardId != null && positionId != null){
			sb.append(" and NPA.board.boardId = :boardId" +
						" and NPA.position.positionId = :positionId");
		}
		else if(type.equalsIgnoreCase("any")){
			sb.append(" and NPA.boardId != :boardId" +
						" and NPA.positionId != :positionId");
		}
		sb.append(" and NPA.nominationPostCandidate.isDeleted = 'N'" +
					" and NPA.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		
		return query.list();
	}
	
	public Long getNominatedPostFinalDetails(Long nominatedPostId,Long nominationPostCandidateId){
		Query query = getSession().createQuery("select model.nominatedPostFinalId" +
										" from NominatedPostFinal model" +
										" where model.nominatedPostId = :nominatedPostId" +
										" and model.nominationPostCandidateId = :nominationPostCandidateId" +
										" and model.isDeleted = 'N'");
		
		query.setParameter("nominatedPostId", nominatedPostId);
		query.setParameter("nominationPostCandidateId", nominationPostCandidateId);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getAnyAppliedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds){
		Query query = getSession().createQuery("select model.nominationPostCandidate.nominationPostCandidateId," +
												" count(distinct model.departments.departmentId)" +
												" from NominatedPostApplication model" +
												" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
												" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
												" group by model.nominationPostCandidate.nominationPostCandidateId");
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		
		return query.list();
	}
	
	public List<Long> getAnyShortlistedDepartmentsForCandidateList(Set<Long> nominatedPostCandidateIds){
		Query query = getSession().createQuery("select model.nominationPostCandidate.nominationPostCandidateId" +
												" from NominatedPostApplication model" +
												" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
												" and model.applicationStatus.applicationStatusId = 3" +
												" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		
		return query.list();
	}
}
