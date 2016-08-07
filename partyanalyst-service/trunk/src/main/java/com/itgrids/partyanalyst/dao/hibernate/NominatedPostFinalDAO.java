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
		
		if(boardLevelId != null && boardLevelId.longValue()>0){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and  model.nominatedPost.nominatedPostMember.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and  model.nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
		}
		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominatedPost.insertedTime) between :startDate and :endDate ");
		
		queryStr.append(" group by  model.nominatedPost.nominatedPostMember.boardLevelId order by model.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			if(boardLevelId.longValue() != 5L)
				query.setParameter("boardLevelId", boardLevelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type,Long searchLevelId){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select NPA.nominationPostCandidate.nominationPostCandidateId," +
					" NPA.nominationPostCandidate.tdpCadreId," +
					" NPA.nominationPostCandidate.voterId," +
					" NPA.nominationPostCandidate.candidateName," +
					" NPA.nominationPostCandidate.mobileNo," +
					" NPA.nominationPostCandidate.age," +
					/*" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +*/
					" CC.categoryName," +
					" CCG.casteCategoryGroupName," +
					" caste.casteName," +
					" NPA.applicationStatus.applicationStatusId," +
					" NPA.applicationStatus.status," +
					" NPA.nominatedPostApplicationId," +
					" NPA.boardLevelId," +
					" NPA.locationValue , " +
					//" NPA.nominatedPost.nominatedPostId, ");
					" NPA.nominationPostCandidate.imageurl ");
		sb.append(" from NominatedPostApplication NPA " +
					//" left join NPA.nominationPostCandidate.tdpCadre TC" +
					" left join NPA.nominationPostCandidate.casteState CS" +
					" left join CS.casteCategoryGroup CCG" +
					" left join CCG.casteCategory CC" +
					" left join CS.caste caste" +
					" where ");
		if(levelId != null && levelId.longValue()>0){
			if(levelId.longValue() != 5L)
				sb.append(" NPA.boardLevel.boardLevelId = :levelId ");
			else
				sb.append(" NPA.boardLevel.boardLevelId in (5,6) ");
		}
		
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if((searchLevelId.longValue() == 1L))
					sb.append(" and NPA.address.country.countryId  = 1 ");
				else if((searchLevelId.longValue() == 2L) && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.state.stateId  = :levelValue ");
				else if(searchLevelId.longValue() ==3L && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.district.districtId =:levelValue ");
				else if(searchLevelId.longValue() ==4L  && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.constituency.constituencyId =:levelValue ");
				else if(searchLevelId.longValue() ==5L  && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.tehsil.tehsilId =:levelValue ");
				else if(searchLevelId.longValue() ==6L  && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.localElectionBody.localElectionBodyId =:levelValue ");
				else if(searchLevelId.longValue() ==7L  && levelValue != null && levelValue.longValue()>0L)
					sb.append(" and NPA.address.panchayatId =:levelValue ");
			}
		
		if(type.equalsIgnoreCase("this")){
			sb.append(" and NPA.departments.departmentId = :departmentId" +
						" and NPA.board.boardId = :boardId" +
						" and NPA.position.positionId = :positionId");
		}
		else if(type.equalsIgnoreCase("any")){
			sb.append(" and (NPA.departments.departmentId is null" +
						" or NPA.boardId is null" +
						" or NPA.positionId is null)");
		}
		sb.append(" and NPA.nominationPostCandidate.isDeleted = 'N'" +
					" and NPA.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if((searchLevelId.longValue() != 1L) && levelValue != null && levelValue.longValue() > 0l)
				query.setParameter("levelValue", levelValue);
		
		if(type.equalsIgnoreCase("this")){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
			query.setParameter("positionId", positionId);
		}
		
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
	
	public List<Object[]> getShortlistedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds){
		Query query = getSession().createQuery("select model.nominationPostCandidate.nominationPostCandidateId," +
												" count(distinct model.departments.departmentId)" +
												" from NominatedPostApplication model" +
												" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
												" and model.applicationStatus.applicationStatusId = 3" +
												" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
												" group by model.nominationPostCandidate.nominationPostCandidateId");
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		
		return query.list();
	}
	
	public List<Object[]> getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominatedPostFinalId," +
					" model.nominationPostCandidate.nominationPostCandidateId," +
					" model.nominationPostCandidate.tdpCadreId," +
					" model.nominationPostCandidate.voterId," +
					" model.nominationPostCandidate.candidateName," +
					" model.nominationPostCandidate.mobileNo," +
					" model.nominationPostCandidate.gender," +
					" model.nominationPostCandidate.age," +
					/*" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +
					" TC.gender," +*/
					" CC.categoryName," +
					" CCG.casteCategoryGroupName," +
					" caste.casteName," +
					" model.applicationStatus.applicationStatusId," +
					" model.applicationStatus.status," +
					" model.isPrefered," +
					" model1.nominatedPostApplicationId ," +
					" model.nominationPostCandidate.imageurl "+
					" from NominatedPostFinal model,NominatedPostApplication model1" +
					//" left join model.nominationPostCandidate.tdpCadre TC" +
					" left join model.nominationPostCandidate.casteState CS" +
					" left join CS.casteCategoryGroup CCG" +
					" left join CCG.casteCategory CC" +
					" left join CS.caste caste" +
					" where model.nominationPostCandidateId = model1.nominationPostCandidateId" +
					//" and model1.boardLevelId = :levelId" +
					" and model1.departmentId = :departmentId" +
					" and model1.boardId = :boardId" +
					" and model1.positionId = :positionId" +
					/*" and model.nominatedPostMember.boardLevelId = :levelId" +
					" and model.nominatedPostMember.nominatedPostPosition.departmentId = :departmentId" +
					" and model.nominatedPostMember.nominatedPostPosition.boardId = :boardId" +
					" and model.nominatedPostMember.nominatedPostPosition.positionId = :positionId" +*/
					" and model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N'" +
					" and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N'" +
					" and model.nominationPostCandidate.isDeleted = 'N' ");
		
		if(levelId != null && levelId.longValue()>0){
			if(levelId.longValue() != 5L)
				sb.append("  and model.nominatedPostMember.boardLevelId = :levelId ");
			else
				sb.append("  and model.nominatedPostMember.boardLevelId in (5,6) ");
		}
		
		if(levelValue != null && levelValue.longValue() > 0l)
			sb.append(" and model1.locationValue = :levelValue");
		
		Query query = getSession().createQuery(sb.toString());
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(levelValue != null && levelValue.longValue() > 0l)
			query.setParameter("levelValue", levelValue);
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		
		return query.list();
	}
	
	public List<Object[]> getGenderWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.gender," +
					" count(model.nominatedPostFinalId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and ");
		if(levelId != null && levelId.longValue() > 0l){
			if(levelId.longValue() != 5L)
				sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
			else
				sb.append("  model.nominatedPostMember.boardLevelId  in (5,6) and ");
		}
		
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");
		
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.gender");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
			if(levelId.longValue() != 5L)
				query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			query.setParameter("applStatusId", applStatusId);
		
		return query.list();
	}
	
	public List<Object[]> getCasteWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
					" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
					" count(model.nominatedPostFinalId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		
		if(levelId != null && levelId.longValue() > 0l){
			if(levelId.longValue() != 5L)
				sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
			else
				sb.append("  model.nominatedPostMember.boardLevelId   in (5,6)  and");
		}
		
		
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");
		
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
			if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			query.setParameter("applStatusId", applStatusId);
		
		return query.list();
	}
	
	public List<Object[]> getAgeGroupWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.nominatedPostAgeRange.ageRange," +
					" count(model.nominatedPostFinalId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		//if(levelId != null && levelId.longValue() > 0l)
			//sb.append(" model.nominatedPostMember.boardLevelId = :levelId and");
		
		if(levelId != null && levelId.longValue() > 0l){
			if(levelId.longValue() != 5L)
				sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
			else
				sb.append("   model.nominatedPostMember.boardLevelId in (5,6) and ");
		}
		
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");
		
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.nominatedPostAgeRangeId");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
			if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			query.setParameter("applStatusId", applStatusId);
		
		return query.list();
	}
	
	public List<Object[]> getCasteCategoryGroupWiseCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		if(type != null && type.equalsIgnoreCase("casteCategory"))
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
						" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName,");
		else if(type != null && type.equalsIgnoreCase("casteName"))
			sb.append(" model.nominationPostCandidate.casteState.caste.casteId," +
						" model.nominationPostCandidate.casteState.caste.casteName,");
		
		sb.append(" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.nominatedPostAgeRange.ageRange," +
					" model.nominationPostCandidate.gender," +
					" count(model.nominatedPostFinalId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		if(levelId != null && levelId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.boardLevelId = :levelId and ");
		
		if(levelId != null && levelId.longValue() > 0l){
			if(levelId.longValue() != 5L)
				sb.append("  model.nominatedPostMember.boardLevelId = :levelId and   ");
			else
				sb.append("   model.nominatedPostMember.boardLevelId in (5,6) and ");
		}
		
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");
		
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by");
		if(type != null && type.equalsIgnoreCase("casteCategory"))
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId,");
		else if(type != null && type.equalsIgnoreCase("casteName"))
			sb.append(" model.nominationPostCandidate.casteState.caste.casteId,");
					
		sb.append(" model.nominationPostCandidate.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.gender" +
					" order by");
		if(type != null && type.equalsIgnoreCase("casteCategory"))
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
		else if(type != null && type.equalsIgnoreCase("casteName"))
			sb.append(" model.nominationPostCandidate.casteState.caste.casteName");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
			query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			query.setParameter("applStatusId", applStatusId);
		
		return query.list();
	}
	
	public List<Object[]> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominatedPostMember.nominatedPostPosition.position.positionId," +
						" model.nominatedPostMember.nominatedPostPosition.position.positionName," +
						" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
						" model.nominationPostCandidate.nominatedPostAgeRange.ageRange," +
						" model.nominationPostCandidate.gender," +
						" count(model.nominatedPostFinalId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		if(levelId != null && levelId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.boardLevelId = :levelId and");
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");
		if(casteId != null && casteId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.caste.casteId = :casteId and");
		
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by");
		sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId," +
					" model.nominationPostCandidate.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.gender");
		//sb.append(" model.nominationPostCandidate.casteState.caste.casteName");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
			query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			query.setParameter("applStatusId", applStatusId);
		if(casteId != null && casteId.longValue() > 0l)
			query.setParameter("casteId", casteId);
		
		return query.list();
	}
	
	 public List<Object[]> getCandidateCasteList(Long locationLevelId){
		 
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.nominationPostCandidate.casteState.casteStateId,model.nominationPostCandidate.casteState.caste.casteName" +
		 		"  from NominatedPost model where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted='N'");
		 
		   if(locationLevelId != null && locationLevelId.longValue() > 0){
			   if(locationLevelId != 5){
				   queryStr.append(" and model.nominatedPostMember.boardLevelId=:locationLevelId ");   
			   }else{
				   queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
			   }
		   }
		   queryStr.append(" order by model.nominationPostCandidate.casteState.caste.casteName ");
		 
		 Query query = getSession().createQuery(queryStr.toString());
	 	 
		 if(locationLevelId != null && locationLevelId.longValue() > 0 && locationLevelId.longValue()!=5){
			  query.setParameter("locationLevelId", locationLevelId);
		 }
		 return query.list();
	 }
	 
	  public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId){
		  
		  StringBuilder queryStr = new StringBuilder();
		            
		   queryStr.append(" select model.nominationPostCandidate.casteState.casteStateId," +
		   		           " model.nominationPostCandidate.casteState.caste.casteName," +
		   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionName," +
		   	          	   " count(distinct model.nominationPostCandidate.nominationPostCandidateId) " +
		   	          	   " from NominatedPost model " +
		   	          	   " where " +
		   	          	   " model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N'" +
		   	          	   " and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'" +
		   	          	   " and model.nominatedPostStatus.status in ('Confirmed','GO Issued') ");
		   
				      if(LocationLevelId != null && LocationLevelId > 0){
				   	    queryStr.append("");
							if(LocationLevelId.longValue() != 5L)
								queryStr.append("  and model.nominatedPostMember.boardLevelId=:LocationLevelId   ");
							else
								queryStr.append("   and model.nominatedPostMember.boardLevelId in (5,6)  ");
				      }
                      if(positionId != null && positionId.longValue() > 0){
                    	queryStr.append("model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId");  
                      }
                      
		              queryStr.append(" group by model.nominationPostCandidate.casteState.caste.casteId," +
		              		          " model.nominatedPostMember.nominatedPostPosition.position.positionId " +
		              		          " order by model.nominatedPostMember.nominatedPostPosition.position.positionId ");
	         
		              Query query = getSession().createQuery(queryStr.toString());
		    
		             if(LocationLevelId != null && LocationLevelId.longValue() > 0){
		            	 if(LocationLevelId.longValue() != 5L)
				    	query.setParameter("LocationLevelId", LocationLevelId);
				     }
		             if(positionId != null && positionId.longValue() > 0){
					    	query.setParameter("positionId", positionId);
					  }
		   return query.list();
	  }
  public List<Object[]> getCasteGroup(Long locationLevelId){
	  
	  StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName" +
		 		"  from NominatedPost model where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted='N'");
		 
		   if(locationLevelId != null && locationLevelId.longValue() > 0){
			   if(locationLevelId != 5L){
				   queryStr.append(" and model.nominatedPostMember.boardLevelId=:locationLevelId ");   
			   }else{
				   queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
			   }
		   }
		   queryStr.append(" order by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName ");
		 
		 Query query = getSession().createQuery(queryStr.toString());
	 	 
		 if(locationLevelId != null && locationLevelId.longValue() > 0 && locationLevelId.longValue()!=5){
			  query.setParameter("locationLevelId", locationLevelId);
		 }
		 return query.list();
	 
  }
  public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId){
		
	  StringBuilder queryStr = new StringBuilder();
       queryStr.append(" select model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
	   		           " model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
	   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionId," +
	   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionName," +
	   	          	   " count(distinct model.nominationPostCandidate.nominationPostCandidateId) " +
	   	          	   " from NominatedPost model " +
	   	          	   " where " +
	   	          	   " model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N'" +
	   	          	   " and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'" +
	   	          	   " and model.nominatedPostStatus.status in ('Confirmed','GO Issued') ");
	   
			      if(LocationLevelId != null && LocationLevelId > 0){
			   	   // queryStr.append(" and model.nominatedPostMember.boardLevelId=:LocationLevelId ");
			    	  if(LocationLevelId.longValue() != 5L){
						   queryStr.append("  and model.nominatedPostMember.boardLevelId=:LocationLevelId  ");   
					   }else{
						   queryStr.append("  and model.nominatedPostMember.boardLevelId in (5,6) ");
					   }
			      }
                 if(positionId != null && positionId.longValue() > 0){
               	queryStr.append("model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId");  
                 }
                 
	              queryStr.append(" group by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
	              		          " model.nominatedPostMember.nominatedPostPosition.position.positionId " +
	              		          " order by model.nominatedPostMember.nominatedPostPosition.position.positionId ");
        
	              Query query = getSession().createQuery(queryStr.toString());
	    
	             if(LocationLevelId != null && LocationLevelId.longValue() > 0){
	            	 if(LocationLevelId != null && LocationLevelId != 5L)
	            		 query.setParameter("LocationLevelId", LocationLevelId);
			    }
	            if(positionId != null && positionId.longValue() > 0){
	            	
				    	query.setParameter("positionId", positionId);
				 }
	   return query.list();
	 
}
	public List<Object[]> getApplicationStatusList(){
		Query query = getSession().createQuery("select distinct model.applicationStatus.applicationStatusId, model.applicationStatus.status from NominatedPostFinal model");  
		return query.list();
	}
	public List<Object[]> getPositionCountForGender(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId,String type){
		 StringBuilder queryStr = new StringBuilder();
	    
		 	queryStr.append(" select  model.nominatedPostMember.nominatedPostPosition.position.positionId ");
		   
		   if(type.toString().equalsIgnoreCase("Gender"))
		   {
			   queryStr.append(" ,model.nominationPostCandidate.gender,count(model.nominationPostCandidate.nominationPostCandidateId) " );
		   }else if(type.toString().equalsIgnoreCase("Age"))
		   {
			   queryStr.append(" ,model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId,model.nominationPostCandidate.nominatedPostAgeRange.ageRange,count(model.nominationPostCandidate.nominationPostCandidateId) " );   
		   }
		   
		   queryStr.append(" from  NominatedPostFinal model " +
				   " where model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId and model.nominatedPostMember.boardLevel.boardLevelId = :boardLevelId and " +
				   " model.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId and model.nominatedPostMember.nominatedPostPosition.board.boardId = :boardId and " +
				   " model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :castegroupId and  model.applicationStatusId = :positionStatusId  and " +
				   " model.nominationPostCandidate.address.district.districtId = :districtId and model.isDeleted = 'N' ");
		   
		   if(type.toString().equalsIgnoreCase("Gender")){
			   queryStr.append(" group by model.nominationPostCandidate.gender ");
		   }else if(type.toString().equalsIgnoreCase("Age")){
			   queryStr.append(" group by model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId "); 
		   }
				   Query query = getSession().createQuery(queryStr.toString());	
				   
		   query.setParameter("positionId", positionId);
		   query.setParameter("boardLevelId", boardLevelId);
		   query.setParameter("deptId", deptId);
		   query.setParameter("boardId", boardId);
		   query.setParameter("castegroupId", castegroupId);
		   query.setParameter("positionStatusId", positionStatusId);
		
		   query.setParameter("districtId", districtId);
		   
				   return query.list();
	}
	 public Object[] getShortListedPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId){
		  
		  StringBuilder queryStr = new StringBuilder();
		  
		    queryStr.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status,count(distinct model.nominatedPost.nominatedPostId) from NominatedPostFinal model " +
		    		        " where" +
		    		        " model.isDeleted='N' and model.nominatedPost.isExpired='N' and model.nominatedPost.isDeleted='N' and model.applicationStatus.status='Shortlisted' ");
		  
		     if(positionId != null && positionId.longValue() > 0){
		    	 queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId ");
		     }
		     if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId "); 
		     }
		    
		     Query query = getSession().createQuery(queryStr.toString());
		     if(positionId != null && positionId.longValue() > 0){
		      query.setParameter("positionId", positionId);	 
		     }
		     if(boardLevelId != null && boardLevelId.longValue() > 0){
		      query.setParameter("boardLevelId", boardLevelId);	 	 
		     }
	          return (Object[]) query.uniqueResult(); 
	  }
	public List<Object[]>  getNominatedCandidateGroupByDist(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId){
		StringBuilder strQuery = new StringBuilder();
		strQuery.append(" select model.nominationPostCandidate.address.district.districtId, model.nominationPostCandidate.address.district.districtName, " +
						" count(model.nominationPostCandidate.nominationPostCandidateId) " +
						" from " +
						" NominatedPostFinal model " +
						" where " );
						if(!(positionId.equals(0l)) && positionId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId and ");
						}
						if(!(locationLevelId.equals(0l)) && locationLevelId != null){
							strQuery.append(" model.nominatedPostMember.boardLevel.boardLevelId = :locationLevelId and ");
						}
						if(!(deptId.equals(0l)) && deptId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId and ");
						}
						if(!(corporationId.equals(0l)) && corporationId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :corporationId and ");
						}
						if(!(castGroupId.equals(0l)) && castGroupId != null){
							strQuery.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :castGroupId and ");
						}
						if(!(positionStatusId.equals(0l)) && positionStatusId != null){
							strQuery.append(" model.applicationStatus.applicationStatusId = :positionStatusId and ");
						}
						strQuery.append(" model.nominationPostCandidate.address.state.stateId = :stateId " +
						" group by " +
						" model.nominationPostCandidate.address.district.districtId " +  
						" order by " +
						" model.nominationPostCandidate.address.district.districtId ");
		Query query = getSession().createQuery(strQuery.toString());			
		if(!(positionId.equals(0l)) && positionId != null){
			query.setParameter("positionId", positionId);
		}
		if(!(locationLevelId.equals(0l)) && locationLevelId != null){
			query.setParameter("locationLevelId", locationLevelId);
		}
		if(!(deptId.equals(0l)) && deptId != null){
			query.setParameter("deptId", deptId);
		}
		if(!(corporationId.equals(0l)) && corporationId != null){
			query.setParameter("corporationId", corporationId);
		}
		if(!(castGroupId.equals(0l)) && castGroupId != null){
			query.setParameter("castGroupId", castGroupId);
		}
		if(!(positionStatusId.equals(0l)) && positionStatusId != null){
			query.setParameter("positionStatusId", positionStatusId);
		}
		query.setParameter("stateId", stateId);
		return query.list(); 
	}
	public List<Object[]>  getNominatedCandidateGroupByDistAndGender(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId){
		StringBuilder strQuery = new StringBuilder();
		strQuery.append(" select model.nominationPostCandidate.address.district.districtId, model.nominationPostCandidate.address.district.districtName, model.nominationPostCandidate.gender, " +
						" count(model.nominationPostCandidate.nominationPostCandidateId) " +
						" from " +
						" NominatedPostFinal model " +
						" where " );
						if(!(positionId.equals(0l)) && positionId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId and ");
						}
						if(!(locationLevelId.equals(0l)) && locationLevelId != null){
							strQuery.append(" model.nominatedPostMember.boardLevel.boardLevelId = :locationLevelId and ");
						}
						if(!(deptId.equals(0l)) && deptId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId and ");
						}
						if(!(corporationId.equals(0l)) && corporationId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :corporationId and ");
						}
						if(!(castGroupId.equals(0l)) && castGroupId != null){
							strQuery.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :castGroupId and ");
						}
						if(!(positionStatusId.equals(0l)) && positionStatusId != null){
							strQuery.append(" model.applicationStatus.applicationStatusId = :positionStatusId and ");
						}
						strQuery.append(" model.nominationPostCandidate.address.state.stateId = :stateId " +
						" group by " +
						" model.nominationPostCandidate.address.district.districtId, " +
						" model.nominationPostCandidate.gender " +  
						" order by " +
						" model.nominationPostCandidate.address.district.districtId," +
						" model.nominationPostCandidate.gender " ); 
						
		Query query = getSession().createQuery(strQuery.toString());			
		if(!(positionId.equals(0l)) && positionId != null){
			query.setParameter("positionId", positionId);
		}
		if(!(locationLevelId.equals(0l)) && locationLevelId != null){
			query.setParameter("locationLevelId", locationLevelId);
		}
		if(!(deptId.equals(0l)) && deptId != null){
			query.setParameter("deptId", deptId);
		}
		if(!(corporationId.equals(0l)) && corporationId != null){
			query.setParameter("corporationId", corporationId);
		}
		if(!(castGroupId.equals(0l)) && castGroupId != null){
			query.setParameter("castGroupId", castGroupId);
		}
		if(!(positionStatusId.equals(0l)) && positionStatusId != null){
			query.setParameter("positionStatusId", positionStatusId);
		}
		query.setParameter("stateId", stateId);
		return query.list(); 
	}
	public List<Object[]>  getNominatedCandidateGroupByDistAndAgeGroup(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId){
		StringBuilder strQuery = new StringBuilder();
		strQuery.append(" select model.nominationPostCandidate.address.district.districtId, model.nominationPostCandidate.address.district.districtName, " +
						" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId, count(model.nominationPostCandidate.nominationPostCandidateId) " +
						" from " +
						" NominatedPostFinal model " +
						" where " );
						if(!(positionId.equals(0l)) && positionId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId and ");
						}
						if(!(locationLevelId.equals(0l)) && locationLevelId != null){
							strQuery.append(" model.nominatedPostMember.boardLevel.boardLevelId = :locationLevelId and ");
						}
						if(!(deptId.equals(0l)) && deptId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId and ");
						}
						if(!(corporationId.equals(0l)) && corporationId != null){
							strQuery.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId = :corporationId and ");
						}
						if(!(castGroupId.equals(0l)) && castGroupId != null){
							strQuery.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :castGroupId and ");
						}
						if(!(positionStatusId.equals(0l)) && positionStatusId != null){
							strQuery.append(" model.applicationStatus.applicationStatusId = :positionStatusId and ");
						}
						strQuery.append(" model.nominationPostCandidate.address.state.stateId = :stateId " +
						" group by " +
						" model.nominationPostCandidate.address.district.districtId, " +
						" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId " +  
						" order by " +
						" model.nominationPostCandidate.address.district.districtId," +
						" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId ");
		
		Query query = getSession().createQuery(strQuery.toString());			
		if(!(positionId.equals(0l)) && positionId != null){
			query.setParameter("positionId", positionId);
		}
		if(!(locationLevelId.equals(0l)) && locationLevelId != null){
			query.setParameter("locationLevelId", locationLevelId);
		}
		if(!(deptId.equals(0l)) && deptId != null){
			query.setParameter("deptId", deptId);
		}
		if(!(corporationId.equals(0l)) && corporationId != null){  
			query.setParameter("corporationId", corporationId);
		}
		if(!(castGroupId.equals(0l)) && castGroupId != null){
			query.setParameter("castGroupId", castGroupId);
		}
		if(!(positionStatusId.equals(0l)) && positionStatusId != null){
			query.setParameter("positionStatusId", positionStatusId);
		}
		query.setParameter("stateId", stateId);
		return query.list(); 
	}
}
