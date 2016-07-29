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
			Long locationValue,String type,Long searchLevelId){
		
		StringBuilder str = new StringBuilder();
		//Query
		str.append(" SELECT position.positionId,position.positionName,count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model  left join model.position position " +
				" WHERE model.boardLevel.boardLevelId=:boardLevelId");
				//" AND model.locationValue = :locationValue ");
		
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if((searchLevelId.longValue() == 1L || searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.locationValue = :locationValue ");
				else if(searchLevelId.longValue() ==3L && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.district.districtId =:locationValue ");
				else if(searchLevelId.longValue() ==4L  && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.constituency.constituencyId =:locationValue ");
				else if(searchLevelId.longValue() ==5L  && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.tehsil.tehsilId =:locationValue ");
				else if(searchLevelId.longValue() ==6L  && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.localElectionBody.localElectionBodyId =:locationValue ");
				else if(searchLevelId.longValue() ==7L  && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.panchayatId =:locationValue ");
			}
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");
		}else if(departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			str.append(" AND model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId" +
				" AND position.positionId=:positionId " +
				" AND position.positionId is not null" );
		}
		
		str.append(" GROUP BY position.positionId ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 6)
		if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
			query.setParameter("positionId", positionId);
		}
		query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	
	
	public List<Object[]> getShortlistedCandidatesStatus(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId){
	
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostFinal model1,NominatedPostApplication model left join model.position position  " +
				" WHERE " +
				" model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" AND model.boardLevel.boardLevelId=:boardLevelId" +
				//" AND model.locationValue = :locationValue" +
				" AND model1.applicationStatus.status = :status  ");
		
		if(searchLevelId != null && searchLevelId.longValue()>0L){
			if((searchLevelId.longValue() == 1L || searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.locationValue = :locationValue ");
			else if(searchLevelId.longValue() ==3L && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.district.districtId =:locationValue ");
			else if(searchLevelId.longValue() ==4L  && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.constituency.constituencyId =:locationValue ");
			else if(searchLevelId.longValue() ==5L  && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.tehsil.tehsilId =:locationValue ");
			else if(searchLevelId.longValue() ==6L  && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.localElectionBody.localElectionBodyId =:locationValue ");
			else if(searchLevelId.longValue() ==7L  && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.panchayatId =:locationValue ");
		}
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");
		}else if(departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			str.append(" AND model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId" +
				" AND position.positionId=:positionId" +
				" AND position.positionId is not null " );
		}
		
		str.append("GROUP BY position.positionId ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 6)
		if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
			query.setParameter("positionId", positionId);
		}
		query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("locationValue", locationValue);		
		query.setParameter("status",IConstants.SHORTLISTED_STATUS);
		
		return query.list();
	}
	
	public List<Object[]> getCasteWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
				" model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE model.boardLevel.boardLevelId=:boardLevelId" );
				//" AND model.locationValue = :locationValue" );
		
				if(searchLevelId != null && searchLevelId.longValue()>0L){
					if((searchLevelId.longValue() == 1L || searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.locationValue = :locationValue ");
					else if(searchLevelId.longValue() ==3L && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.district.districtId =:locationValue ");
					else if(searchLevelId.longValue() ==4L  && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.constituency.constituencyId =:locationValue ");
					else if(searchLevelId.longValue() ==5L  && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.tehsil.tehsilId =:locationValue ");
					else if(searchLevelId.longValue() ==6L  && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.localElectionBody.localElectionBodyId =:locationValue ");
					else if(searchLevelId.longValue() ==7L  && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.panchayatId =:locationValue ");
				}
		
				if(type !=null && type.equalsIgnoreCase("Any")){
					str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");
				}else if(departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
					str.append(" AND model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId" +
				" AND position.positionId=:positionId " +
				" AND position.positionId is not null" );
				}
				str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
				str.append("GROUP BY position.positionId,model.nominationPostCandidate.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
				
		//Query Calling
				Query query = getSession().createQuery(str.toString());
				
				//Parameters Setting(max 5)
				if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
					query.setParameter("departmentId", departmentId);
					query.setParameter("boardId", boardId);
					query.setParameter("positionId", positionId);
				}
				query.setParameter("boardLevelId", boardLevelId);
				query.setParameter("locationValue", locationValue);						
		
		return query.list();
	}
	
	
	public List<Object[]> getAgeRangeWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.tdpCadre.age," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE model.boardLevel.boardLevelId=:boardLevelId" );
				//" AND model.locationValue = :locationValue" );
					if(searchLevelId != null && searchLevelId.longValue()>0L){
						if((searchLevelId.longValue() == 1L || searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.locationValue = :locationValue ");
						else if(searchLevelId.longValue() ==3L && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.district.districtId =:locationValue ");
						else if(searchLevelId.longValue() ==4L  && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.constituency.constituencyId =:locationValue ");
						else if(searchLevelId.longValue() ==5L  && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.tehsil.tehsilId =:locationValue ");
						else if(searchLevelId.longValue() ==6L  && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.localElectionBody.localElectionBodyId =:locationValue ");
						else if(searchLevelId.longValue() ==7L  && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.panchayatId =:locationValue ");
					}
					
		
		if(type !=null && type.equalsIgnoreCase("Any")){
			str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");
		}else if(departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			str.append(" AND model.departments.departmentId = :departmentId" +
				" AND model.board.boardId = :boardId" +
				" AND position.positionId=:positionId " +
				" AND position.positionId is not null" );
		}
		str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
		str.append("GROUP BY position.positionId,model.nominationPostCandidate.tdpCadre.age ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 5)
		if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
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
	
	public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count(model.nominatedPostApplicationId), " +
				" count(distinct model.departmentId), " +
				" count(distinct model.boardId)  " +
				" from NominatedPostApplication model ");
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where ");
		//queryStr.append("   model.isDeleted='N'  and  model.applicationStatusId =1   and model.boardLevelId =:levelId ");
		queryStr.append("   model.isDeleted='N'  and  model.applicationStatusId is not null   and model.boardLevelId =:levelId ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId = 1");//applied
		
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append(" and model3.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==2L)
				queryStr.append(" and model3.district.districtId between 1 and 10 ");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		
		return query.list();
	}

	public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count(model.nominatedPostApplicationId), " +
				" count(distinct model.departmentId), " +
				" count(distinct model.boardId) from NominatedPostApplication model " );
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N' and model.applicationStatusId not in (1,5) and model.boardLevelId =:levelId  ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId <>1  ");
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append(" and model3.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==2L)
				queryStr.append(" and model3.district.districtId between 1 and 10 ");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
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
	
	
	public List<Object[]> getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId){
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
				" model.boardLevel.boardLevelId,model.boardLevel.level,model.departments.departmentId," +
	        " model.departments.deptName,model.board.boardId,model.board.boardName,model.position.positionId," +
	        " model.position.positionName,model.departments.postTypeId " +
	        " from NominatedPostApplication model " +
	        " where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' ");
	        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
	        	str.append(" and model.nominationPostCandidate.tdpCadre.tdpCadreId = :tdpCadreId ");
	        }
	        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
	        	str.append(" and model.nominationPostCandidate.nominationPostCandidateId = :nominateCandId ");
	        }
	        
	        str.append( " order by model.departments.postTypeId ");
	        
	        Query query = getSession().createQuery(str.toString());
	        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
	        	  query.setParameter("tdpCadreId", tdpCadreId);
	        }
	        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
	        	  query.setParameter("nominateCandId", nominateCandId);
	        }
	      
	        return query.list();
	  }
	public List<Object[]> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId){
	    
	    Query query = getSession().createQuery( " select model.applicationStatus.applicationStatusId,model.applicationStatus.status,model.boardLevel.boardLevelId," +
	    		"model.boardLevel.level,dept.departmentId," +
	        " dept.deptName," +
	        " board.boardId,board.boardName," +
	        " position.positionId,position.positionName, " +
	        " model.locationValue " +
	        " from NominatedPostApplication model" +
	        " left join model.departments dept" +
	        " left join model.board board" +
	        " left join model.position position " +
	        " where model.nominationPostCandidateId = :candidateId " +
	        " and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' ");
	        
	        query.setParameter("candidateId", candidateId);
	        return query.list();
	  }
	public List<Object[]> getPositionDetaislOfEveryApplicationStatus(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType){
		
		StringBuilder str = new StringBuilder();
		
		
		str.append(" SELECT position.positionId,position.positionName,model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position" +
				" WHERE " +
				" model.isDeleted = 'N' " );
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" AND model.boardLevel.boardLevelId=:boardLevelId");			
		}
		if(locationValues !=null && locationValues.size()>0){
			str.append(" AND model.locationValue in (:locationValues)");
		}
	// Any Dept && Board && post Scenarios Consideration && non Consideration
	if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
		if(deptsIds !=null && deptsIds.size()>0){
			str.append(" AND model.departments.departmentId in (:deptsIds) ");
		}
		if(boardIds !=null && boardIds.size()>0){
			str.append(" AND model.board.boardId in (:boardIds) ");
		}
		
		str.append(" and  model.positionId is not null ");
		
	}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
		str.append(" AND (model.departmentId is null " +
				" or model.boardId is null " +
				" or model.positionId is null ) ");
	}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" AND model.applicationStatus.status = :notYet ");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" AND model.applicationStatus.status not in (:running) ");
		}
		
		str.append(" GROUP BY position.positionId,model.applicationStatus.applicationStatusId " +
					" ORDER BY position.positionId");
		
		
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);		
		}
		if(locationValues !=null && locationValues.size()>0){
			query.setParameterList("locationValues",locationValues);
		}
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
			if(deptsIds !=null && deptsIds.size()>0){
				query.setParameterList("deptsIds",deptsIds);
			}
			if(boardIds !=null && boardIds.size()>0){
				query.setParameterList("boardIds",boardIds);
			}
		}
		
		if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
			query.setParameter("running",IConstants.NOMINATED_POST_NOT_RUNNING_STATUS);
		}
		
		return query.list();
	}
 public List<Object[]> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId){
		
		       StringBuilder queryStr = new StringBuilder();
		       
		       queryStr.append(" select ");
		          
		      /* if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
		    	   queryStr.append(" model.departments.departmentId,model.departments.deptName,");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
		      	   queryStr.append(" model.board.boardId,model.board.boardName,");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
		 		  queryStr.append(" model.position.positionId,model.position.positionName,");
		 	   }
		       queryStr.append(" model.applicationStatus.applicationStatusId,model.applicationStatus.status,count(model.nominatedPostApplicationId)");
		       queryStr.append(" from  NominatedPostApplication model where model.isDeleted = 'N' and model.applicationStatus.applicationStatusId = 3 ");
		       
		       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
		    	    queryStr.append(" and model.boardLevel.boardLevelId=:LocationLevelId ");
		       }
		       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
		    	   queryStr.append(" and model.locationValue in (:lctnLevelValueList)");
		       }
		       if(departmentId != null && departmentId.longValue() > 0){
		    	   queryStr.append(" and model.departments.departmentId=:departmentId ");
		    	   
		       }
		       if(boardId != null && boardId.longValue() > 0){
		    	   queryStr.append(" and model.board.boardId=:boardId ");
		       }
		       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
		    	   queryStr.append(" group by model.departments.departmentId order by model.departments.departmentId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
		     	   queryStr.append(" group by model.board.boardId order by model.board.boardId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
		    	   queryStr.append(" group by model.position.positionId order by  model.position.positionId ");
		       }*/
	 
		       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
		    	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId,model.nominatedPostMember.nominatedPostPosition.departments.deptName,");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
		      	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName,");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
		 		  queryStr.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName,");
		 	   }
		       queryStr.append(" model.nominatedPostStatusId,model.nominatedPostStatus.status,count(model.nominatedPostId)");
		       
		       queryStr.append(" from  NominatedPost model where model.isDeleted = 'N' and model.nominatedPostStatusId = 2 ");
		       
		       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
		    	    queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
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
		       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
		    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId order by model.nominatedPostMember.nominatedPostPosition.departments.departmentId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
		     	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.board.boardId order by model.nominatedPostMember.nominatedPostPosition.board.boardId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
		    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.position.positionId order by  model.nominatedPostMember.nominatedPostPosition.position.positionId ");
		       }
		       
		       Query query = getSession().createQuery(queryStr.toString());
		       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
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
 public List<Object[]> getApplicationStatusCntByPositionId(Long positionId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.applicationStatus.applicationStatusId, model.applicationStatus.status,count(distinct model.nominatedPostFinalId)  from NominatedPostFinal model,NominatedPostApplication model1  " +
				"where model.isDeleted = 'N'  and model1.isDeleted = 'N' and model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId ");
		
		
		 if(positionId != null && positionId.longValue() > 0l){
			queryStr.append(" and model1.position.positionId = :positionId ");
		}
		 queryStr.append(" group by model.applicationStatus.applicationStatusId "); 
		Query query = getSession().createQuery(queryStr.toString());
		if(positionId != null && positionId.longValue() > 0l){
			query.setParameter("positionId", positionId);
		}
		
		return query.list();
	}
  public List<Object[]> getLocationWiseCastePositionCount(Long LocationLevelId){
	  
	  StringBuilder queryStr = new StringBuilder();
	            
	   queryStr.append(" select model.nominationPostCandidate.casteState.casteStateId,model.nominationPostCandidate.casteState.caste.casteName," +
	   	          	   " model.position.positionId,model.position.positionName,count(model.position.positionId) " +
	   	          	   " from NominatedPostApplication model where model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N' ");
	   
			      if(LocationLevelId != null && LocationLevelId > 0){
			   	    queryStr.append(" and model.boardLevel.boardLevelId=:LocationLevelId ");
			      }

	             queryStr.append(" group by model.nominationPostCandidate.casteState.caste.casteId,model.position.positionId order by model.position.positionId ");
         
	             Query query = getSession().createQuery(queryStr.toString());
	    
	             if(LocationLevelId != null && LocationLevelId > 0){
			    	query.setParameter("LocationLevelId", LocationLevelId);
			    }
	   return query.list();
  }
 public List<Object[]> getLocationWiseCasteGroupPositionCount(Long LocationLevelId){
	
	    StringBuilder queryStr = new StringBuilder();
     
	    queryStr.append(" select model.nominationPostCandidate.casteState.casteStateId,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
	   	          	   " model.position.positionId,model.position.positionName,count(model.position.positionId) " +
	   	          	   " from NominatedPostApplication model where model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N' ");
	   
	             if(LocationLevelId != null && LocationLevelId > 0){
			        queryStr.append(" and model.boardLevel.boardLevelId=:LocationLevelId ");
			      }

	             queryStr.append("   group by model.nominationPostCandidate.casteState.caste.casteId,model.position.positionId order by model.position.positionId ");
       
	             Query query = getSession().createQuery(queryStr.toString());
	    
	             if(LocationLevelId != null && LocationLevelId > 0){
			    	query.setParameter("LocationLevelId", LocationLevelId);
			    }
	   return query.list();
	 
 }
 public List<Object[]> getCandidateCasteList(Long locationLevelId){
	 
	 StringBuilder queryStr = new StringBuilder();
	 queryStr.append(" select distinct model.nominationPostCandidate.casteState.casteStateId,model.nominationPostCandidate.casteState.caste.casteName from NominatedPostApplication model where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted='N'");
	 
	   if(locationLevelId != null && locationLevelId.longValue() > 0){
		   if(locationLevelId != 5){
			   queryStr.append(" and model.boardLevelId=:locationLevelId ");   
		   }else{
			   queryStr.append(" and model.boardLevelId in (5,6,7) ");
		   }
		 
	   }
	   queryStr.append(" order by model.nominationPostCandidate.casteState.caste.casteName ");
	 
	 Query query = getSession().createQuery(queryStr.toString());
 	 
	 if(locationLevelId != null && locationLevelId.longValue() > 0 && locationLevelId.longValue()!=5){
		  query.setParameter("locationLevelId", locationLevelId);
	 }
	 return query.list();
 }
}
