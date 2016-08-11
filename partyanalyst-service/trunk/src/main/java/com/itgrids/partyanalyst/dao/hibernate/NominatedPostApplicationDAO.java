package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
				" WHERE ");
				//" AND model.locationValue = :locationValue ");
		if(boardLevelId.longValue() !=5L)
			str.append("  model.boardLevel.boardLevelId=:boardLevelId ");
		else 
			str.append(" model.boardLevel.boardLevelId  in (5,6) ");
		
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if((searchLevelId.longValue() == 1L))
					str.append(" and model.address.country.countryId  = 1 ");
				else if((searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
					str.append(" and model.address.state.stateId  = :locationValue ");
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
		if(boardLevelId.longValue() !=5L)
			query.setParameter("boardLevelId", boardLevelId);
		
		if((searchLevelId.longValue() != 1L) && locationValue != null && locationValue.longValue() > 0l)
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
				" " +
				//" AND model.locationValue = :locationValue" +
				" AND model1.applicationStatus.status = :status  ");
		if(boardLevelId.longValue() !=5L)
			str.append(" AND model.boardLevel.boardLevelId=:boardLevelId ");
		else 
			str.append(" AND model.boardLevel.boardLevelId in (5,6) ");
		
		if(searchLevelId != null && searchLevelId.longValue()>0L){
			if((searchLevelId.longValue() == 1L))
				str.append(" and model.address.country.countryId  = 1 ");
			else if((searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
				str.append(" and model.address.state.stateId  = :locationValue ");
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
		if(boardLevelId.longValue() !=5L)
			query.setParameter("boardLevelId", boardLevelId);
		
		if((searchLevelId.longValue() != 1L) && locationValue != null && locationValue.longValue() > 0l)
			query.setParameter("locationValue", locationValue);		
		query.setParameter("status",IConstants.SHORTLISTED_STATUS);
		
		return query.list();
	}
	
	public List<Object[]> getCasteWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
				" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE " );
				//" AND model.locationValue = :locationValue" );
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() !=5L)
				str.append(" model.boardLevel.boardLevelId=:boardLevelId ");
			else
				str.append(" model.boardLevel.boardLevelId in (5,6) ");
		}
		
				if(searchLevelId != null && searchLevelId.longValue()>0L){
					if((searchLevelId.longValue() == 1L))
						str.append(" and model.address.country.countryId  = 1 ");
					else if((searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
						str.append(" and model.address.state.stateId  = :locationValue ");
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
				" AND position.positionId is not null " );
				}
				//str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
				str.append(" GROUP BY position.positionId,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
				
		//Query Calling
				Query query = getSession().createQuery(str.toString());
				
				//Parameters Setting(max 5)
				if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
					query.setParameter("departmentId", departmentId);
					query.setParameter("boardId", boardId);
					query.setParameter("positionId", positionId);
				}
				if(boardLevelId.longValue() !=5L)
					query.setParameter("boardLevelId", boardLevelId);
				if((searchLevelId.longValue() != 1L) && locationValue != null && locationValue.longValue() > 0l)
					query.setParameter("locationValue", locationValue);						
		
		return query.list();
	}
	
	
	public List<Object[]> getAgeRangeWiseApplications(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
			Long locationValue,String type,Long searchLevelId){
		
		StringBuilder str = new StringBuilder();
		
		//Query
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.age," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position " +
				" WHERE " );
				if(boardLevelId != null && boardLevelId.longValue()>0L){
					if(boardLevelId.longValue() !=5L)
						str.append(" model.boardLevel.boardLevelId=:boardLevelId ");
					else
						str.append(" model.boardLevel.boardLevelId in (5,6) ");
				}
		
				//" AND model.locationValue = :locationValue" );
					if(searchLevelId != null && searchLevelId.longValue()>0L){
						if((searchLevelId.longValue() == 1L))
							str.append(" and model.address.country.countryId  = 1 ");
						else if((searchLevelId.longValue() == 2L) && locationValue != null && locationValue.longValue()>0L)
							str.append(" and model.address.state.stateId  = :locationValue ");
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
				" AND position.positionId is not null " );
		}
		//str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
		str.append(" GROUP BY position.positionId,model.nominationPostCandidate.age ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 5)
		if(type ==null && departmentId != null && departmentId > 0 && boardId != null && boardId > 0 && positionId !=null && positionId>0){
			query.setParameter("departmentId", departmentId);
			query.setParameter("boardId", boardId);
			query.setParameter("positionId", positionId);
		}
		if(boardLevelId.longValue() !=5L)
			query.setParameter("boardLevelId", boardLevelId);
		if((searchLevelId.longValue() != 1L) && locationValue != null && locationValue.longValue() > 0l)
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
					" where " +
					"  NPA.locationValue = :levelValue" +
					" and NPA.departments.departmentId = :departmentId");
		
		if(levelId != null && levelId.longValue()>0L){
			if(levelId.longValue() !=5L)
				sb.append(" and NPA.boardLevel.boardLevelId = :levelId ");
			else
				sb.append(" and NPA.boardLevel.boardLevelId in (5,6) ");
		}
		
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
		
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		
		query.setParameter("levelValue", levelValue);
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		
		return query.list();
	}
	
public List<Object[]> getNominatedPostsAppliedAppliciationsDtals(Long levelId,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select district model.boardLevelId, count(model.nominationPostCandidateId) from NominatedPostApplication model where " +
				"  model.nominationPostCandidate.isDeleted ='N'  ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.nominationPostCandidate.insertedTime) between :startDate and :endDate ");
		queryStr.append(" and model.applicationStatusId = 1 ");//applied
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		//query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count( model.nominatedPostMember.nominatedPostMemberId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId)  " +
				" from NominatedPostApplication model ");
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where ");
		//queryStr.append("   model.isDeleted='N'  and  model.applicationStatusId =1   and model.boardLevelId =:levelId ");
		queryStr.append("   model.isDeleted='N'  and  model.applicationStatusId is not null   ");
		if(levelId.longValue() != 5L)
			queryStr.append("   and model.boardLevelId =:levelId ");
		else
			queryStr.append("   and model.boardLevelId in (5,6) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		queryStr.append(" and model.applicationStatusId = 1");//applied
		
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		
		return query.list();
	}

	/*public List<Object[]> getNominatedPostsAppliedApplciationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
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
		queryStr.append("   model.isDeleted='N'  and  model.applicationStatusId is not null   ");
		if(levelId.longValue() != 5L)
			queryStr.append("   and model.boardLevelId =:levelId ");
		else
			queryStr.append("   and model.boardLevelId in (5,6) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		queryStr.append(" and model.applicationStatusId = 1");//applied
		
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(levelId.longValue() != 5L)
			query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		
		return query.list();
	}*/
	public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count(model.nominatedPostMember.nominatedPostMemberId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) from NominatedPostApplication model " );
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N' and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")  ");
		if(levelId != null && levelId.longValue()>0L){
			if(levelId.longValue() != 5L)
				queryStr.append("   and model.boardLevelId =:levelId ");
			else
				queryStr.append("   and model.boardLevelId in (5,6) ");
		}
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId <>1  ");
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(levelId != null && levelId.longValue()>0L)
			if(levelId.longValue() != 5L)
				query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		return query.list();
	}

	/*public List<Object[]> getNominatedPostsRunningAppliedApplicationsDtals(Long levelId,Date startDate,Date endDate,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count(model.nominatedPostApplicationId), " +
				" count(distinct model.departmentId), " +
				" count(distinct model.boardId) from NominatedPostApplication model " );
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N' and model.applicationStatusId not in ("+IConstants.RUNNING_NOMINATED_POSTS_STATUS_IDS+")  ");
		if(levelId != null && levelId.longValue()>0L){
			if(levelId.longValue() != 5L)
				queryStr.append("   and model.boardLevelId =:levelId ");
			else
				queryStr.append("   and model.boardLevelId in (5,6) ");
		}
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		//queryStr.append(" and model.applicationStatusId <>1  ");
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId order by model.boardLevelId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(levelId != null && levelId.longValue()>0L)
			if(levelId.longValue() != 5L)
				query.setParameter("levelId", levelId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(levelId != null && levelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		return query.list();
	}*/
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
		
		if(nominateCandId != null && nominateCandId.longValue()>0L)
			searchType="Not Cadre";
		str.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
				" model.boardLevel.boardLevelId,model.boardLevel.level,departments.departmentId," +
	        " departments.deptName,board.boardId,board.boardName,position.positionId," +
	        " position.positionName,model.postType.postTypeId " +
	        " from NominatedPostApplication model left join model.position position left join model.departments departments " +
	        " left join model.board  board  where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' ");
	        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
	        	str.append(" and model.nominationPostCandidate.tdpCadre.tdpCadreId = :tdpCadreId ");
	        }
	        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
	        	str.append(" and model.nominationPostCandidate.nominationPostCandidateId = :nominateCandId ");
	        	
	        	//if(tdpCadreId != null && tdpCadreId.longValue()>0L)
	        		//str.append(" and model.nominationPostCandidate.tdpCadre.tdpCadreId is not null ");
	        	//else
	        		//str.append(" and model.nominationPostCandidate.tdpCadre.tdpCadreId is null ");
	        }
	        
	        str.append( " order by model.postType.postTypeId ");
	        
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
			//if(boardLevelId.longValue() != 5L)
				str.append(" AND model.boardLevel.boardLevelId=:boardLevelId");		
			//else
			//	str.append(" AND model.boardLevel.boardLevelId in (5,6) ");		
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
			str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
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
		
		/*if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
			query.setParameter("running",Long.valueOf(IConstants.NOMINATED_POST_NOT_RUNNING_STATUS));
		}*/
		
		return query.list();
	}
 public List<Object[]> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId,String status){
		
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
		       
		       queryStr.append(" from  NominatedPost model where model.isDeleted = 'N' ");
		       if(status != null && status.equalsIgnoreCase("finalReview"))
		    	   queryStr.append(" and model.nominatedPostStatusId = 2 ");
		       else if(status != null && status.equalsIgnoreCase("finaliZed"))
		    	   queryStr.append(" and model.nominatedPostStatusId = 3 ");
		       else if(status != null && status.equalsIgnoreCase("goPassed"))
		    	   queryStr.append(" and model.nominatedPostStatusId = 4 ");
		       
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
		       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
		    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId order by model.nominatedPostMember.nominatedPostPosition.departments.departmentId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
		     	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.board.boardId order by model.nominatedPostMember.nominatedPostPosition.board.boardId ");
		       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
		    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.position.positionId order by  model.nominatedPostMember.nominatedPostPosition.position.positionId ");
		       }
		       
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
			    	  if(LocationLevelId.longValue() != 5L)
			    		 queryStr.append(" and model.boardLevel.boardLevelId=:LocationLevelId ");
			    	  else
				    	queryStr.append(" and model.boardLevel.boardLevelId in  (5,6) ");
			      }

	             queryStr.append(" group by model.nominationPostCandidate.casteState.caste.casteId,model.position.positionId order by model.position.positionId ");
         
	             Query query = getSession().createQuery(queryStr.toString());
	    
	             if(LocationLevelId != null && LocationLevelId > 0){
	            	 if(LocationLevelId.longValue() != 5L)
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
	            	 if(LocationLevelId.longValue() != 5L)
	            		 queryStr.append(" and model.boardLevel.boardLevelId=:LocationLevelId ");
	            	 else
	            		 queryStr.append(" and model.boardLevel.boardLevelId in (5,6) ");
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
 public List<Object[]> getApplicationDetailsCntPositionAndLocationWise(Long positionId,Long boardLevelId){
	 
	 StringBuilder queryStr = new StringBuilder();
	 
	   queryStr.append("select model.applicationStatus.applicationStatusId,model.applicationStatus.status,count(model.nominatedPostApplicationId) from NominatedPostApplication model " +
	 		" where" +
	 		" model.isDeleted='N' ");
	   
		   if(positionId != null && positionId.longValue() > 0){
			   queryStr.append(" and model.position.positionId = :positionId");
		   }
		   if(boardLevelId != null && boardLevelId.longValue() > 0){
			   if(boardLevelId.longValue() != 5L)
				   queryStr.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
			   else
				   queryStr.append(" and model.boardLevel.boardLevelId in (5,6) ");
		   }
	   
	       queryStr.append(" group by model.applicationStatus.applicationStatusId ");
	   
	      Query query = getSession().createQuery(queryStr.toString());
		  
		  if(positionId != null && positionId.longValue() > 0){
		   query.setParameter("positionId", positionId);
		   }  
		   if(boardLevelId != null && boardLevelId.longValue() > 0 && boardLevelId.longValue() != 5L){
			query.setParameter("boardLevelId", boardLevelId);   
		   }
	  return query.list();
 }
 
	public List<Object[]> getTotalApplicationCountsByBoard(Long boardLevelId,Long searchLevelId,Long searchLevelValue,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct  model.departmentId," +
					" model.boardId," +
					" count(model.nominatedPostApplicationId)" +
					" from NominatedPostApplication model" +
					" where model.boardLevelId = :boardLevelId");
		 if(searchLevelId != null && searchLevelId.longValue() > 0l){
			 if(searchLevelId == 1l)
				 sb.append(" and model.address.country.countryId = 1");
			 else if(searchLevelId == 2l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.state.stateId = :searchLevelValue");
			 else if(searchLevelId == 3l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.district.districtId = :searchLevelValue");
			 else if(searchLevelId == 4l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.constituency.constituencyId = :searchLevelValue");
			 else if(searchLevelId == 5l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.tehsil.tehsilId = :searchLevelValue");
			 else if(searchLevelId == 6l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.localElectionBody.localElectionBodyId = :searchLevelValue");
			 else if(searchLevelId == 7l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.address.panchayat.panchayatId = :searchLevelValue");
		 }
		 if(statusId != null && statusId.longValue() > 0l )
			 sb.append(" and model.applicationStatusId  in (3)");
		 sb.append(" and model.isDeleted = 'N'" +
		 			" group by model.departmentId,model.boardId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("boardLevelId", boardLevelId);
	//	if(statusId != null && statusId.longValue() > 0l)
		//	query.setParameter("statusId", statusId);
		if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			query.setParameter("searchLevelValue", searchLevelValue);
		
		return query.list();
	}
	
	public List<Object[]> getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue){
		  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count( model.nominatedPostApplicationId), model.nominatedPostMember.nominatedPostMemberId ");
		queryStr.append(" from NominatedPostApplication model   " );
		queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N'  and model.nominatedPostMember.isDeleted='N'  ");	// for total 
						
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		}
		
		if(searchlevelId != null && searchlevelId.longValue()>0L){
			if(searchlevelId.longValue() == 1L)
				queryStr.append(" and model.nominatedPostMember.locationValue  = :searchLevelValue ");
			else if(searchlevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.state.stateId =:searchLevelValue ");
			else if(searchlevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.district.districtId =:searchLevelValue ");
			else if(searchlevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.constituency.constituencyId =:searchLevelValue ");
			else if(searchlevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.tehsil.tehsilId =:searchLevelValue ");
			else if(searchlevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId =:searchLevelValue ");
			else if(searchlevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.panchayatId =:searchLevelValue ");
		}
		queryStr.append(" group by model.nominatedPostMember.nominatedPostMemberId  ");
		queryStr.append(" order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() != 5L)
			query.setParameter("boardLevelId", boardLevelId);
		if(searchLevelValue != null && searchLevelValue.longValue()>0L)
				query.setParameter("searchLevelValue", searchLevelValue);	
		
		return query.list();
  }
	
	public int updateApplicationStatusToFinal(Long deptId,Long boardId,List<Long> positions,Long levelId,List<Long> searchLevelValues,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		DateUtilService dateUtilService = new DateUtilService();
		
		queryStr.append("UPDATE NominatedPostApplication model SET model.applicationStatus.applicationStatusId = :applicationStatusId," +
				" model.updatedBy =:updatedBy," +
				" model.updatedTime =:updatedTime" +
				"	WHERE  model.isDeleted = 'N' " +
				" AND model.applicationStatusId = :shortListId ");
		
		if(deptId !=null && deptId>0){
			queryStr.append(" AND model.departmentId = :departmentId ");
		}
		if(boardId !=null && boardId>0){
			queryStr.append(" AND model.boardId = :boardId ");
		}
		if(positions !=null && positions.size()>0){
			queryStr.append(" AND model.positionId in (:positionIds) ");
		}
		if(levelId !=null && levelId>0){
			queryStr.append(" AND model.boardLevelId = :boardLevelId ");
		}
		if(searchLevelValues !=null && searchLevelValues.size()>0){
			queryStr.append(" AND model.locationValue in (:locationValue) ");
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
		
		query.setParameter("applicationStatusId", IConstants.NOMINATED_APPLICATION_FINAL_REVIEW);
		query.setParameter("shortListId", 3L);
		query.setParameter("updatedBy", userId);
		query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
		
		
		return query.executeUpdate();
	}
	public List<Object[]> getAnyDeptApplicationOverviewCountLocationWise(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
		      List<Long> locationValue,Long searchLevelId,String status){
		    
		    StringBuilder str = new StringBuilder();
		    str.append("SELECT position.positionId ");
		    if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    str.append(" ,model.applicationStatus.applicationStatusId ");	
		    }
		    str.append(",count(distinct model.nominatedPostApplicationId) " +
		        " FROM NominatedPostApplication model  " +
		        " left join model.position position " +
		        " WHERE model.isDeleted='N' ");
		    if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    	str.append(" and model.nominatedPostMemberId is not null ");
		    }
		    
		    
		    if(searchLevelId ==null || searchLevelId==0l){
		    	
		    	if(boardLevelId.longValue() >0)
				      str.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
		    	
		    	if(locationValue !=null && locationValue.size()>0){
		    		str.append(" and model.locationValue in (:locationValue) ");
		    	}		    	
		    	
		    }else{
		    	
		    	if(boardLevelId.longValue() !=5L)
				      str.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
				    else 
				      str.append(" and model.boardLevel.boardLevelId  in (5,6) ");
		    	
		    	if(searchLevelId != null && searchLevelId.longValue()>0L){
			        if((searchLevelId.longValue() == 1L))
			          str.append(" and model.address.country.countryId  = 1 ");
			        else if((searchLevelId.longValue() == 2L) && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.state.stateId  in (:locationValue) ");
			        else if(searchLevelId.longValue() ==3L && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.district.districtId in (:locationValue) ");
			        else if(searchLevelId.longValue() ==4L  && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.constituency.constituencyId in (:locationValue) ");
			        else if(searchLevelId.longValue() ==5L  && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.tehsil.tehsilId in (:locationValue) ");
			        else if(searchLevelId.longValue() ==6L  && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.localElectionBody.localElectionBodyId in (:locationValue) ");
			        else if(searchLevelId.longValue() ==7L  && locationValue != null && locationValue.size()>0)
			          str.append(" and model.address.panchayatId =:locationValue ");
			      }
		    }
		    
		     if(departmentId != null && departmentId.longValue() > 0L){
		    	 str.append(" and model.departments.departmentId=:departmentId");
		     }else{
		    	 str.append(" and model.departmentId is null ");
		     }
		     if(boardId != null && boardId.longValue() > 0L){
		    	 str.append(" and model.board.boardId=:boardId");
		     }else{
		    	 str.append(" and model.boardId is null "); 
		     }
		     
		    str.append(" GROUP BY position.positionId ");
		    
		    if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    	str.append(", model.applicationStatus.applicationStatusId");	
		    }
		    Query query = getSession().createQuery(str.toString());
		    
		    
		    if(departmentId != null && departmentId.longValue() > 0){
		    	   query.setParameter("departmentId", departmentId);
		    }
          if(boardId != null && boardId.longValue() > 0){
          	 query.setParameter("boardId", boardId);
		    }
          
          if(searchLevelId ==null || searchLevelId==0l){
        	  
        	  if(boardLevelId !=null && boardLevelId>0l){
        		  query.setParameter("boardLevelId", boardLevelId);
        	  }
        	  if(locationValue != null && locationValue.size() > 0){
        		  query.setParameterList("locationValue", locationValue);
        	  }
        	  
          }else{
        	  if(boardLevelId.longValue() !=5L)
    		      query.setParameter("boardLevelId", boardLevelId);
        	  if((searchLevelId.longValue() != 1L) && locationValue != null && locationValue.size() > 0)
    		      query.setParameterList("locationValue", locationValue);
          }
		   
		    return query.list();
		  }
}
