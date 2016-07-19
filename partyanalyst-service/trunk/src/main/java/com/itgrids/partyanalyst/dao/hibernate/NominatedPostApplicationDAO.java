package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominatedPostApplicationDAO extends GenericDaoHibernate<NominatedPostApplication, Long> implements INominatedPostApplicationDAO{

	public NominatedPostApplicationDAO() {
		super(NominatedPostApplication.class);
		
	}
	
	public List<Object[]> getAppliationsReceievedStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type){
		
		StringBuilder str = new StringBuilder();
		//Query
		str.append(" SELECT position.positionId,position.positionName,count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model  left join model.position position " +
				" WHERE " +
				"  model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId " +				
				" AND model.boardLevel.boardLevelId=:boardLevelId" +
				" AND model.locationValue = :locationValue ");
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND position.positionId is null ");
		}else if(positionId !=null && positionId>0){
			str.append(" AND position.positionId=:positionId " );
		}
		
		str.append("GROUP BY position.positionId ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 6)
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		if(type ==null && positionId !=null && positionId>0 ){
			query.setParameter("positionId", positionId);
		}
		query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	
	
	public List<Object[]> getShortlistedCandidatesStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type){
	
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostFinal model1,NominatedPostApplication model left join model.position position  " +
				" WHERE " +
				" model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" AND model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId " +				
				" AND model.boardLevel.boardLevelId=:boardLevelId" +
				" AND model.locationValue = :locationValue" +
				" AND model1.applicationStatus.status = :status  ");
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND position.positionId is null ");
		}else if(positionId !=null && positionId>0){
			str.append(" AND position.positionId=:positionId " );
		}
		
		str.append("GROUP BY position.positionId ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 6)
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		if(type ==null && positionId !=null && positionId>0 ){
			query.setParameter("positionId", positionId);
		}
		query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("locationValue", locationValue);		
		query.setParameter("status",IConstants.SHORTLISTED_STATUS);
		
		return query.list();
	}
	
	public List<Object[]> getCasteWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
				" model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE " +
				"  model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId " +				
				" AND model.boardLevel.boardLevelId=:boardLevelId" +
				" AND model.locationValue = :locationValue" );
		
				if(type !=null && type.equalsIgnoreCase("Any")){
					str.append(" AND position.positionId is null ");
				}else if(positionId !=null && positionId>0){
					str.append(" AND position.positionId=:positionId " );
				}
				str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
				str.append("GROUP BY position.positionId,model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
				
		//Query Calling
				Query query = getSession().createQuery(str.toString());
				
				//Parameters Setting(max 5)
				query.setParameter("departmentId", departmentId);
				query.setParameter("boardId", boardId);
				if(type ==null && positionId !=null && positionId>0 ){
					query.setParameter("positionId", positionId);
				}
				query.setParameter("boardLevelId", boardLevelId);
				query.setParameter("locationValue", locationValue);						
		
		return query.list();
	}
	
	
	public List<Object[]> getAgeRangeWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.tdpCadre.age," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE " +
				"  model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId " +				
				" AND model.boardLevel.boardLevelId=:boardLevelId" +
				" AND model.locationValue = :locationValue" );
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND position.positionId is null ");
		}else if(positionId !=null && positionId>0){
			str.append(" AND position.positionId=:positionId " );
		}
		str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
		str.append("GROUP BY position.positionId,model.nominationPostCandidate.tdpCadre.age ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 5)
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		if(type ==null && positionId !=null && positionId>0 ){
			query.setParameter("positionId", positionId);
		}
		query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("locationValue", locationValue);						

		return query.list();
		
	}
	
	
	
	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select NPA.nominationPostCandidate.nominationPostCandidateId," +
					" NPA.nominationPostCandidate.tdpCadreId," +
					" NPA.nominationPostCandidate.voterId," +
					" NPA.nominationPostCandidate.candidateName," +
					" NPA.nominationPostCandidate.mobileNo," +
					" TC.firstname," +
					" TC.mobileNo," +
					" TC.age," +
					" TC.casteState.casteCategoryGroup.casteCategory.categoryName," +
					" TC.casteState.casteCategoryGroup.casteCategoryGroupName," +
					" TC.casteState.caste.casteName");
		sb.append(" from NominatedPostApplication NPA " +
					" left join NPA.nominationPostCandidate.tdpCadre TC" +
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
		sb.append(" and NPA.nominationPostCandidate.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		
		return query.list();
	}
	
public List<Object[]> getNominatedPostsAppliedAppliciationsDtals(Long levelId,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select district model.boardLevelId, count(model.nominationPostCandidateId), from NominatedPostApplication model where " +
				"  model.nominationPostCandidate.isDeleted ='N'  ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominationPostCandidate.insertedTime) between :startDate and :endDate ");
		queryStr.append(" and model.applicationStatusId = 1 ");//applied
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.nominatedPostMember.boardLevelId, count(model2.nominatedPostApplicationId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) from NominatedPost model,NominatedPostApplication model2 " +
				"   where  model.nominationPostCandidateId = model2.nominationPostCandidateId and " +
				"   model.nominationPostCandidate.isDeleted ='N'  and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and " +
				"   model2.isDeleted='N' and model.nominatedPostMember.boardLevelId = model2.boardLevelId and model2.applicationStatusId = 1  " +
				"  and model.nominatedPostMember.boardLevelId =:levelId ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominationPostCandidate.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId = 1");//applied
		queryStr.append(" group  by model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}

	public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.nominatedPostMember.boardLevelId, count(model2.nominatedPostApplicationId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) from NominatedPost model,NominatedPostApplication model2 " +
				"   where  model.nominationPostCandidateId = model2.nominationPostCandidateId and " +
				"   model.nominationPostCandidate.isDeleted ='N'  and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and " +
				"   model2.isDeleted='N' and model.nominatedPostMember.boardLevelId = model2.boardLevelId and model2.applicationStatusId in (2,3)  " +
				" and model.nominatedPostMember.boardLevelId =:levelId  ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominationPostCandidate.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId <>1  ");
		queryStr.append(" group  by model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}

	public List<Object[]> getPendingApplciationStatusDtls(Long boardLevelId,Date startDate,Date endDate){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select district model.boardLevelId, count(model.nominationPostCandidateId) from NominatedPostApplication model where " +
				"  model.nominationPostCandidate.isDeleted ='N'  and model.nominationPostCandidateId not in ( " +
				" select distinct model1.nominationPostCandidateId from NominatedPostFinal model1 ) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominationPostCandidate.insertedTime) between :startDate and :endDate ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getCandidateAppliedPostsByCadre(Long tdpCadreId){
	    
	    Query query = getSession().createQuery( " select model.applicationStatus.applicationStatusId,model.applicationStatus.status,model.boardLevel.boardLevelId,model.boardLevel.level,model.departments.departmentId," +
	        " model.departments.deptName,model.board.boardId,model.board.boardName,model.position.positionId,model.position.positionName,model.departments.postTypeId " +
	        " from NominatedPostApplication model " +
	        " where model.nominationPostCandidate.tdpCadre.tdpCadreId = :tdpCadreId " +
	        " and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' " +
	        " order by model.departments.postTypeId ");
	        
	        query.setParameter("tdpCadreId", tdpCadreId);
	        return query.list();
	  }
	
public List<Object[]> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId){
	    
	    Query query = getSession().createQuery( " select model.applicationStatus.applicationStatusId,model.applicationStatus.status,model.boardLevel.boardLevelId," +
	    		"model.boardLevel.level,model.departments.departmentId," +
	        " model.departments.deptName," +
	        " model.board.boardId,model.board.boardName," +
	        " model.position.positionId,model.position.positionName, " +
	        " model.locationValue " +
	        " from NominatedPostApplication model " +
	        " where model.nominationPostCandidateId = : candidateId and " +
	        " and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' ");
	        
	        query.setParameter("candidateId", candidateId);
	        return query.list();
	  }
}
