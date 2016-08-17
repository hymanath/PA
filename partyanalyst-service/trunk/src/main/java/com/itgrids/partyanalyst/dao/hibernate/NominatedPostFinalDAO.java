package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.model.NominatedPostFinal;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominatedPostFinalDAO extends GenericDaoHibernate<NominatedPostFinal, Long> implements INominatedPostFinalDAO{

	public NominatedPostFinalDAO() {
		super(NominatedPostFinal.class);
	}

	public List getFinalShortListedApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate){
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
					" CC.categoryName," +
					" CCG.casteCategoryGroupName," +
					" caste.casteName," +
					" NPA.applicationStatus.applicationStatusId," +
					" NPA.applicationStatus.status," +
					" NPA.nominatedPostApplicationId," +
					" NPA.boardLevelId," +
					" NPA.locationValue , " +
					//" NPA.nominatedPost.nominatedPostId, ");
					" NPA.nominationPostCandidate.imageurl," +
					" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +
					" NCC.categoryName," +
					" NC.casteName," +
					" NPA.nominationPostCandidate.gender," +
					" TC.gender");
		sb.append(" from NominatedPostApplication NPA " +
					" left join NPA.nominationPostCandidate.tdpCadre TC" +
					" left join TC.casteState CS" +
					" left join CS.casteCategoryGroup CCG" +
					" left join CCG.casteCategory CC" +
					" left join CS.caste caste" +
					" left join NPA.nominationPostCandidate.casteState NCS" +
					" left join NCS.casteCategoryGroup NCCG" +
					" left join NCCG.casteCategory NCC" +
					" left join NCS.caste NC" +
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
			
			if(departmentId != null && departmentId.longValue() > 0l)
				sb.append(" and NPA.departments.departmentId = :departmentId" );
			if(boardId != null && boardId.longValue() > 0l)
				sb.append("  and NPA.board.boardId = :boardId " );
			if(positionId != null && positionId.longValue() > 0l)
				sb.append(" and NPA.position.positionId = :positionId " );
		}
		else if(type.equalsIgnoreCase("any")){
			sb.append(" and");
			if(departmentId != null && departmentId.longValue() > 0l)
				sb.append(" NPA.departments.departmentId = :departmentId");
			else
				sb.append(" NPA.departments.departmentId is null");
			sb.append(" and");
			if(boardId != null && boardId.longValue() > 0l)
				sb.append(" NPA.boardId = :boardId");
			else
				sb.append(" NPA.boardId is null");
			sb.append(" and");
			if(positionId != null && positionId.longValue() > 0l)
				sb.append(" NPA.positionId = :positionId");
			else
				sb.append(" NPA.positionId is null");
			/*sb.append(" and (NPA.departments.departmentId is null" +
						" or NPA.boardId is null" +
						" or NPA.positionId is null)");*/
		}
		sb.append(" and NPA.nominationPostCandidate.isDeleted = 'N'" +
					" and NPA.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if((searchLevelId.longValue() != 1L) && levelValue != null && levelValue.longValue() > 0l)
				query.setParameter("levelValue", levelValue);
		
		if(departmentId != null && departmentId.longValue() > 0l)
			query.setParameter("departmentId", departmentId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		/*if(type.equalsIgnoreCase("this")){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
			query.setParameter("positionId", positionId);
		}*/
		
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
	
	public List<Object[]> getAnyAppliedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.nominationPostCandidateId," +
					" count(distinct model.departments.departmentId)" +
					" from NominatedPostApplication model" +
					" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
					" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
			sb.append(" and (model.departmentId != :deptId or (model.departmentId = :deptId and model.boardId != :boardId))");
		sb.append(" group by model.nominationPostCandidate.nominationPostCandidateId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
			query.setParameter("deptId", deptId);
			query.setParameter("boardId", boardId);
		}
		
		return query.list();
	}
	
	public List<Long> getAnyShortlistedDepartmentsForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.nominationPostCandidateId" +
						" from NominatedPostApplication model" +
						" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
						" and model.applicationStatus.applicationStatusId = 3" +
						" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
			sb.append(" and (model.departmentId != :deptId or (model.departmentId = :deptId and model.boardId != :boardId))");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
			query.setParameter("deptId", deptId);
			query.setParameter("boardId", boardId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getShortlistedDepartmentsCountForCandidateList(Set<Long> nominatedPostCandidateIds,Long deptId,Long boardId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.nominationPostCandidateId," +
						" count(distinct model.departments.departmentId)" +
						" from NominatedPostApplication model" +
						" where model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)" +
						" and model.applicationStatus.applicationStatusId = 3" +
						" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
			sb.append(" and (model.departmentId != :deptId or (model.departmentId = :deptId and model.boardId != :boardId))");
		sb.append(" group by model.nominationPostCandidate.nominationPostCandidateId");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
		if(deptId != null && deptId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
			query.setParameter("deptId", deptId);
			query.setParameter("boardId", boardId);
		}
		
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
					" CC.categoryName," +
					" CCG.casteCategoryGroupName," +
					" caste.casteName," +
					" model.applicationStatus.applicationStatusId," +
					" model.applicationStatus.status," +
					" model.isPrefered," +
					" model1.nominatedPostApplicationId ," +
					" model.nominationPostCandidate.imageurl," +
					" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +
					" TC.gender," +
					" NCC.categoryName," +
					" NC.casteName"+
					" from NominatedPostFinal model,NominatedPostApplication model1" +
					" left join model.nominationPostCandidate.tdpCadre TC" +
					" left join TC.casteState CS" +
					" left join CS.casteCategoryGroup CCG" +
					" left join CCG.casteCategory CC" +
					" left join CS.caste caste" +
					" left join model.nominationPostCandidate.casteState NCS" +
					" left join NCS.casteCategoryGroup NCCG" +
					" left join NCCG.casteCategory NCC" +
					" left join NCS.caste NC" +
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
	public Long getTotalApplicationsByLocation(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.nominatedPostApplication.nominatedPostApplicationId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and ");
		if(levelId != null && levelId.longValue() > 0l){
			sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
		}
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and ");
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		}
		 
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'");
		
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
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		 if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		 }
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getGenderWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.gender," +
					" count(distinct model.nominationPostCandidate.nominationPostCandidateId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and ");
		if(levelId != null && levelId.longValue() > 0l){
			sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
		}
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and ");
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		 if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		  }
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.gender ");
		
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
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		  if(stateId != null && stateId.longValue() > 0){
 			  query.setParameter("stateId", stateId); 
 		 }
		return query.list();
	}
	
	public List<Object[]> getCasteWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
					" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
					" count(distinct model.nominatedPostApplication.nominatedPostApplicationId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		
		if(levelId != null && levelId.longValue() > 0l){
			sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
		}
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		}
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
		
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
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		 if(stateId != null && stateId.longValue() > 0){
 			  query.setParameter("stateId", stateId); 
 		 }
		return query.list();
	}
	
	public List<Object[]> getAgeGroupWiseTotalCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.nominatedPostAgeRange.ageRange," +
					" count(distinct model.nominatedPostApplication.nominatedPostApplicationId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		
		if(levelId != null && levelId.longValue() > 0l){
			sb.append("  model.nominatedPostMember.boardLevelId = :levelId and ");
		}
		
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		}
		sb.append(" model.isDeleted = 'N' and model.nominatedPostMember.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
					" group by model.nominationPostCandidate.nominatedPostAgeRangeId");
		
		Query query = getSession().createQuery(sb.toString());
		if(positionId != null && positionId.longValue() > 0l)
			query.setParameter("positionId", positionId);
		if(levelId != null && levelId.longValue() > 0l)
		//	if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(boardId != null && boardId.longValue() > 0l)
			query.setParameter("boardId", boardId);
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			query.setParameter("casteGroupId", casteGroupId);
		if(applStatusId != null && applStatusId.longValue() > 0l)
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		 if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		 }
		return query.list();
	}
	
	public List<Object[]> getCasteCategoryGroupWiseCountsForPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,String type,Long stateId){
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
					" count(distinct model.nominatedPostApplication.nominatedPostApplicationId)");
		sb.append(" from NominatedPostFinal model" +
					" where");
		if(positionId != null && positionId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.positionId = :positionId and");
		if(levelId != null && levelId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.boardLevelId = :levelId and ");
		
		/*if(levelId != null && levelId.longValue() > 0l){
			if(levelId.longValue() != 5L)
				sb.append("  model.nominatedPostMember.boardLevelId = :levelId and   ");
			else
				sb.append("   model.nominatedPostMember.boardLevelId in (5,6) and ");
		}
		*/
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.departmentId = :deptId and");
		if(boardId != null && boardId.longValue() > 0l)
			sb.append(" model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and");
		if(casteGroupId != null && casteGroupId.longValue() > 0l)
			sb.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :casteGroupId and");
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		}
		/*if(applStatusId != null && applStatusId.longValue() > 0l)
			sb.append(" model.applicationStatusId = :applStatusId and");*/
		
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
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		 if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		 }
		return query.list();
	}
	
	public List<Object[]> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nominatedPostMember.nominatedPostPosition.position.positionId," +
						" model.nominatedPostMember.nominatedPostPosition.position.positionName," +
						" model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
						" model.nominationPostCandidate.nominatedPostAgeRange.ageRange," +
						" model.nominationPostCandidate.gender," +
						" count(distinct model.nominatedPostApplication.nominatedPostApplicationId)");
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
		
		if(applStatusId != null && applStatusId.longValue()==0l){ // for all status
			sb.append(" model.applicationStatus.applicationStatusId not in (2,4) and ");
			sb.append(" model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) and ");
		}else{
			if(applStatusId != null && applStatusId.longValue()==1l){ //shortListed Application
				sb.append(" model.applicationStatus.applicationStatusId=3 and ");
			}else{
			   sb.append("  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId=:postStatusId and");
			}
		}
		if(stateId != null && stateId.longValue() > 0){
			 sb.append("  model.nominatedPostMember.address.state.stateId=:stateId and ");
		}
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
			if(applStatusId.longValue()!=1)
			query.setParameter("postStatusId", applStatusId);
		if(casteId != null && casteId.longValue() > 0l)
			query.setParameter("casteId", casteId);
		 if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		 }
		return query.list();
	}
	
	 public List<Object[]> getCandidateCasteList(Long locationLevelId,Long stateId){
		 
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
		   if(stateId != null && stateId.longValue() > 0){
			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
		   }
		   queryStr.append(" order by model.nominationPostCandidate.casteState.caste.casteName ");
		 
		 Query query = getSession().createQuery(queryStr.toString());
		 
		 if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		 }
		 if(locationLevelId != null && locationLevelId.longValue() > 0 && locationLevelId.longValue()!=5){
			  query.setParameter("locationLevelId", locationLevelId);
		 }
		 return query.list();
	 }
	 
	  public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId,Long stateId){
		  
		  StringBuilder queryStr = new StringBuilder();
		            
		   queryStr.append(" select model.nominationPostCandidate.casteState.casteStateId," +
		   		           " model.nominationPostCandidate.casteState.caste.casteName," +
		   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionName," +
		   	          	   " count(distinct model.nominationPostCandidate.nominationPostCandidateId) " +
		   	          	   " from NominatedPost model " +
		   	          	   " where " +
		   	          	   " model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N'" +
		   	          	   " and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostStatus.status in ('Confirmed','GO Issued')");
		                   ////and model.nominatedPostStatus.status in ('Confirmed','GO Issued')
		   
				      if(LocationLevelId != null && LocationLevelId > 0){
				   	    queryStr.append("");
							if(LocationLevelId.longValue() != 5L)
								queryStr.append("  and model.nominatedPostMember.boardLevelId=:LocationLevelId   ");
							else
								queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6)  ");
				      }
                      if(positionId != null && positionId.longValue() > 0){
                    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId");  
                      }
                      if(stateId != null && stateId.longValue() > 0){
           			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
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
		             if(stateId != null && stateId.longValue() > 0){
		   			  query.setParameter("stateId", stateId); 
		   		       }
		   return query.list();
	  }
  public List<Object[]> getCasteGroup(Long locationLevelId,Long stateId){
	  
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
		   if(stateId != null && stateId.longValue() > 0){
   			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
   		    }
		   
		   queryStr.append(" order by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName ");
		 
		 Query query = getSession().createQuery(queryStr.toString());
	 	 
		 if(locationLevelId != null && locationLevelId.longValue() > 0 && locationLevelId.longValue()!=5){
			  query.setParameter("locationLevelId", locationLevelId);
		 }
		 if(stateId != null && stateId.longValue() > 0){
   			  query.setParameter("stateId", stateId); 
   		 }
		 return query.list();
	 
  }
  public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId,Long stateId){
		
	  StringBuilder queryStr = new StringBuilder();
       queryStr.append(" select model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
	   		           " model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
	   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionId," +
	   	          	   " model.nominatedPostMember.nominatedPostPosition.position.positionName," +
	   	          	   " count(distinct model.nominationPostCandidate.nominationPostCandidateId) " +
	   	          	   " from NominatedPost model " +
	   	          	   " where " +
	   	          	   " model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N'" +
	   	          	   " and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostStatus.status in ('Confirmed','GO Issued')");
	               // and model.nominatedPostStatus.status in ('Confirmed','GO Issued')
			      if(LocationLevelId != null && LocationLevelId > 0){
			   	   // queryStr.append(" and model.nominatedPostMember.boardLevelId=:LocationLevelId ");
			    	  if(LocationLevelId.longValue() != 5L){
						   queryStr.append("  and model.nominatedPostMember.boardLevelId=:LocationLevelId  ");   
					   }else{
						   queryStr.append("  and model.nominatedPostMember.boardLevelId in (5,6) ");
					   }
			      }
                 if(positionId != null && positionId.longValue() > 0){
                 	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId");  
                 }
                 if(stateId != null && stateId.longValue() > 0){
         			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
         		  }
      		   
                 
	              queryStr.append(" group by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
	              		          " model.nominatedPostMember.nominatedPostPosition.position.positionId " +
	              		          " order by model.nominatedPostMember.nominatedPostPosition.position.positionId ");
        
	              Query query = getSession().createQuery(queryStr.toString());
	    
	             if(LocationLevelId != null && LocationLevelId.longValue() > 0){ //santosh
	            	 if(LocationLevelId != null && LocationLevelId != 5L)
	            		 query.setParameter("LocationLevelId", LocationLevelId);
			    }
	            if(positionId != null && positionId.longValue() > 0){
	               	query.setParameter("positionId", positionId);
				 }  
	            if(stateId != null && stateId.longValue() > 0){
	     			  query.setParameter("stateId", stateId); 
	     		 }
	   return query.list();
	 
  }
  	/*
	 * Swadhin
	 */
	public List<Object[]> getApplicationStatusList(){
		Query query = getSession().createQuery(" select distinct model.applicationStatus.applicationStatusId, model.applicationStatus.status " +
											   " from NominatedPostFinal model" +
											   " where " +
											   " model.isDeleted = 'N' ");  
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
	 public Object[] getShortListedPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId){
		  
		  StringBuilder queryStr = new StringBuilder();
		  
		    queryStr.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status,count(distinct model.nominatedPostApplication.nominatedPostApplicationId) from NominatedPostFinal model " +
		    		        " where" +
		    		        " model.isDeleted='N' and model.nominatedPost.isExpired='N' and model.nominatedPost.isDeleted='N' and model.applicationStatus.status='Shortlisted' ");
		  
		     if(positionId != null && positionId.longValue() > 0){
		    	 queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId ");
		     }
		     if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId "); 
		     }
		     if(stateId != null && stateId.longValue() > 0){
   			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
   		    }
		   
		     Query query = getSession().createQuery(queryStr.toString());
		     if(positionId != null && positionId.longValue() > 0){
		      query.setParameter("positionId", positionId);	 
		     }
		     if(boardLevelId != null && boardLevelId.longValue() > 0){
		      query.setParameter("boardLevelId", boardLevelId);	 	 
		     }
		     if(stateId != null && stateId.longValue() > 0){
    			  query.setParameter("stateId", stateId); 
    		 }
	          return (Object[]) query.uniqueResult(); 
	  }
	public List<Object[]>  getNominatedCandidatePositionDetails(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName){
		StringBuilder strQuery = new StringBuilder();
		if(locationLevelName.equalsIgnoreCase("state")){
			strQuery.append(" select model.nominatedPostMember.address.state.stateId, model.nominatedPostMember.address.state.stateName, ");
		}
		if(locationLevelName.equalsIgnoreCase("District")){
			strQuery.append(" select model.nominatedPostMember.address.district.districtId, model.nominatedPostMember.address.district.districtName, ");
		}
		if(locationLevelName.equalsIgnoreCase("Assembly")){
			strQuery.append(" select model.nominatedPostMember.address.constituency.constituencyId, model.nominatedPostMember.address.constituency.name, ");
		}
		if(locationLevelName.equalsIgnoreCase("Assembly")){
			
		}
		
		strQuery.append(" select model.nominatedPostMember.address.state.stateId, model.nominatedPostMember.address.state.stateName, count(model.nominatedPostApplicationId) " +
				        " from " +
				        " NominatedPostFinal model " +
				        " where " +
				        " model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in (2,3,4) " +
				        " and model.applicationStatus.applicationStatusId not in (2,4) and " +
				        " model.nominatedPostMember.address.state.stateId = :stateId " +
				        " group by model.nominatedPostMember.address.state.stateId");
		return null;
	}
	public List<Object[]>  getNominatedCandidateGroupByDist(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId/*, String addressId*/){
		String addressId = "";
		StringBuilder strQuery = new StringBuilder();
		strQuery.append(" ");
		if(addressId.equalsIgnoreCase("not null")){
			strQuery.append(" select model.nominationPostCandidate.address.district.districtId, model.nominationPostCandidate.address.district.districtName, " +
							" count(model.nominationPostCandidate.nominationPostCandidateId) " +
							" from " +
							" NominatedPostFinal model " +
							" where " +
							" model.nominationPostCandidate.address.addressId != 'NULL' and " );	
		}
		if(addressId.equalsIgnoreCase("null")){
			strQuery.append(" select model.nominationPostCandidate.tdpCadre.userAddress.district.districtId, model.nominationPostCandidate.tdpCadre.userAddress.district.districtName, " +
							" count(model.nominationPostCandidate.nominationPostCandidateId) " +
							" from " +
							" NominatedPostFinal model " +
							" where " +
							" model.nominationPostCandidate.address.addressId == 'NULL' and " );  	
			
		}
		
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
		if(addressId.equalsIgnoreCase("not null")){
			strQuery.append(" model.nominationPostCandidate.address.state.stateId = :stateId " +
							" group by " +
							" model.nominationPostCandidate.address.district.districtId " +  
							" order by " +
							" model.nominationPostCandidate.address.district.districtId ");
		}
		if(addressId.equalsIgnoreCase("null")){
			strQuery.append(" model.nominationPostCandidate.tdpCadre.userAddress.state.stateId = :stateId " +
							" group by " +
							" model.nominationPostCandidate.tdpCadre.userAddress.district.districtId " +  
							" order by " +
							" model.nominationPostCandidate.tdpCadre.userAddress.district.districtId ");  
		}
		
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
	
	public List<Object[]> getWishListCount(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId){
		 StringBuilder queryStr = new StringBuilder();
	       
	       queryStr.append(" select ");
	       
	       
	       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
	 		  queryStr.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName,");
	 	   }
	       queryStr.append(" count(model.nominatedPostFinalId)");
	       
	       queryStr.append(" from  NominatedPostFinal model where model.isDeleted = 'N' and model.isPrefered = 'Y' ");
	       
	       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	   if(LocationLevelId.longValue() != 5L)
	    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
	    	   else
	    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId in (5,6) ");
	       }
	       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
	    	   queryStr.append(" and model.nominatedPostMember.locationValue in (:lctnLevelValueList)");
	       }
	       if(departmentId != null && departmentId.longValue() > 0){
	    	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId=:departmentId ");
	    	   
	       }
	       if(boardId != null && boardId.longValue() > 0){
	    	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId=:boardId ");
	       }
	      
	    	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N' group by model.nominatedPostMember.nominatedPostPosition.position.positionId ");
	       
	       
	       Query query = getSession().createQuery(queryStr.toString());
	       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	   if(LocationLevelId.longValue() != 5L)
	    		   query.setParameter("LocationLevelId", LocationLevelId);
	       }
	       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
	    	   query.setParameterList("lctnLevelValueList", lctnLevelValueList);
	       }
	       if(departmentId != null && departmentId.longValue() > 0){
	    	   query.setParameter("departmentId", departmentId);
	       }
	       if(boardId != null && boardId.longValue() > 0){
	    	   query.setParameter("boardId", boardId);
	       }
	    return query.list();
	}
	
	public List<Long> getApplicationFinalModels(Long deptId,Long boardId,List<Long> positions,Long levelId,List<Long> searchLevelValues){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.nominatedPostFinalId from NominatedPostFinal model " +
				" WHERE  model.isDeleted = 'N' " +
				" AND model.nominatedPostMember.isDeleted = 'N' " +
					" AND model.applicationStatusId = :shortListId ");
				
				if(deptId !=null && deptId>0){
					queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.departmentId = :departmentId ");
				}
				if(boardId !=null && boardId>0){
					queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.boardId = :boardId ");
				}
				if(positions !=null && positions.size()>0){
					queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.positionId in (:positionIds) ");
				}
				if(levelId !=null && levelId>0){
					queryStr.append(" AND model.nominatedPostMember.boardLevelId = :boardLevelId ");
				}
				if(searchLevelValues !=null && searchLevelValues.size()>0){
					queryStr.append(" AND model.nominatedPostMember.locationValue in (:locationValue) ");
				}
				Query query = getSession().createQuery(queryStr.toString());
				
				if(deptId !=null && deptId>0){
					query.setParameter("departmentId",deptId);
				}
		
				if(boardId !=null && boardId>0){
					query.setParameter("boardId",boardId);
				}
		
				if(positions !=null && positions.size()>0){
					query.setParameterList("positionIds",positions);
				}
				if(levelId !=null && levelId>0){
					query.setParameter("boardLevelId",levelId);
				}
				if(searchLevelValues !=null && searchLevelValues.size()>0){
					query.setParameterList("locationValue",searchLevelValues);
				}
				
				query.setParameter("shortListId", 3L);
				
				return query.list();
				
	}
	
		public int updateApplicationStatusToFinalReview(Long userId,List<Long> finalIds){
			
			StringBuilder queryStr = new StringBuilder();
			DateUtilService dateUtilService = new DateUtilService();
			
			queryStr.append(" UPDATE  NominatedPostFinal model SET model.applicationStatus.applicationStatusId = :applicationStatusId," +
					" model.updatedBy =:updatedBy," +
					" model.updatedTime =:updatedTime" +
					"	WHERE model.isDeleted ='N' " );
			
			if(finalIds !=null && finalIds.size()>0){
				queryStr.append("  and model.nominatedPostFinalId in (:finalIds)  ");
			}
					
			Query query = getSession().createQuery(queryStr.toString());
			
			query.setParameter("applicationStatusId", IConstants.NOMINATED_APPLICATION_FINAL_REVIEW);		
			query.setParameter("updatedBy", userId);
			query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
			
			if(finalIds !=null && finalIds.size()>0){
				query.setParameterList("finalIds", finalIds);
			}
			
			return query.executeUpdate();
		}	
		
		public List<NominatedPostFinal> getNominatedPostApplicationDetailsByApplciationId(Long nominatedPostApplicationId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select distinct model  from NominatedPostFinal model where  model.nominatedPostApplicationId =:nominatedPostApplicationId ");
			queryStr.append(" and  model.isDeleted='N'  and model.nominatedPostApplication.isDeleted='N' ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("nominatedPostApplicationId", nominatedPostApplicationId);
			return query.list();
		}
		
	public List<Long> getNominatedPostFinalIds(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList){
		Query query = getSession().createQuery(" select model.nominatedPostFinalId from NominatedPostFinal model " +
		   		" where model.nominatedPostMember.boardLevelId=:locationLevelId " +
		   		" and model.nominatedPostMember.locationValue in (:locationLevelValueList) " +
		   		" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId " +
		   		" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId " +
		   		" and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
		   		" and model.applicationStatusId=:oldStatus " +
		   		" and model.nominatedPostMember.nominatedPostPosition.positionId in (:positionsList) ");
		   
		   	query.setParameter("locationLevelId", locationLevelId);
		   	query.setParameterList("locationLevelValueList", locationLevelValueList);
		   	query.setParameter("departmentId", departmentId);
		   	query.setParameter("boardId", boardId);
		   	query.setParameterList("positionsList", positionsList);
		   	query.setParameter("oldStatus", 5l);
		   	
		   return query.list();
	}
	
	public Integer updateGoIssuedStatusInNominatedPostFinal(List<Long> nominatedPostFinalIds,Date date){
		Query query = getSession().createQuery(" update NominatedPostFinal model set model.applicationStatusId=:nominatedPostStatusId,model.updatedTime=:date " +
		   		" where model.nominatedPostFinalId in (:nominatedPostFinalIds) ");
		   
		   	query.setParameter("nominatedPostStatusId", 7l);
		   	query.setDate("date", date);
		   	query.setParameterList("nominatedPostFinalIds",nominatedPostFinalIds);
		   	
		   	return query.executeUpdate();
	}
}
