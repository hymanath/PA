package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
				" left join model.departments department " +
				" left join model.board board  " +
				" WHERE   model.isDeleted ='N' and model.isExpired = 'N'  and model.applicationStatus.applicationStatusId not in (2,4,8)   ");
				//" AND model.locationValue = :locationValue ");
		if(boardLevelId.longValue() !=5L)
			str.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
		else 
			str.append(" and model.boardLevel.boardLevelId  in (5,6) ");
		
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
			str.append(" AND ( ");
			 if(departmentId != null && departmentId.longValue() > 0L)
				 str.append(" department.departmentId =:departmentId ");
			 else 
				 str.append(" department.departmentId is null ");
			 
			 if(boardId != null && boardId.longValue() > 0L)
				 str.append(" OR  board.boardId =:boardId ");
			 else 
				 str.append("  OR  board.boardId is null ");
			 
			 /*if(positionId != null && positionId.longValue() > 0L)
				 str.append(" OR  (position.positionId =:positionId AND position.positionId is not null )");
			 else */
				
			 str.append(" )");
			 
			 str.append("  AND position.positionId is null ");
			 
		}else{			
			if(departmentId != null && departmentId.longValue() > 0l){
				str.append(" AND department.departmentId = :departmentId" );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
					//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
					str.append("  AND position.positionId=:positionId" );
				else
					str.append("  AND position.positionId is  null " );
			}
			else if(boardId != null && boardId.longValue() > 0l){
					if(departmentId != null && departmentId.longValue() > 0l)
						str.append(" AND department.departmentId = :departmentId" );
					else
						str.append(" AND department.departmentId is null " );
					if(boardId != null && boardId.longValue() > 0l)
						str.append("  AND board.boardId = :boardId " );
					else
						str.append("  AND board.boardId is null " );
					if(positionId != null && positionId.longValue() > 0l)
						//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
						str.append("  AND position.positionId=:positionId " );
					else
						str.append("  AND position.positionId is  null " );
					
			}
			else if(positionId != null && positionId.longValue() > 0l){
				if(departmentId != null && departmentId.longValue() > 0l)
					str.append(" AND department.departmentId = :departmentId" );
				else
					str.append(" AND department.departmentId is null " );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
					//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
					str.append("  AND position.positionId=:positionId" );
				else
					str.append("  AND position.positionId is  null " );
			}
		}
		str.append(" GROUP BY position.positionId ");  
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		 if(departmentId != null && departmentId.longValue() > 0L)
				query.setParameter("departmentId", departmentId);
		 if(boardId != null && boardId.longValue() > 0L)
			 query.setParameter("boardId", boardId);
		 if(positionId != null && positionId.longValue() > 0L)
			 query.setParameter("positionId", positionId);
		 
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
				" FROM NominatedPostFinal model1,NominatedPostApplication model left join model.position position " +
				" left join model.departments department left join model.board board  " +
				" WHERE   model.isDeleted ='N' and   model1.isDeleted ='N' and " +
				" model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" and model1.nominatedPostMember.isDeleted='N' and "+
				 " model1.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and model.isExpired = 'N' and model1.isExpired = 'N' "+
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
			/*str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");*/
			str.append(" AND ( ");
			 if(departmentId != null && departmentId.longValue() > 0L)
				 str.append(" model.departments.departmentId =:departmentId ");
			 else 
				 str.append(" model.departments.departmentId is null ");
			 
			 if(boardId != null && boardId.longValue() > 0L)
				 str.append(" OR  model.board.boardId =:boardId ");
			 else 
				 str.append("  OR  model.board.boardId is null ");
			 
			 if(positionId != null && positionId.longValue() > 0L)
				 str.append(" OR  (position.positionId =:positionId AND position.positionId is not null )");
			 else 
				 str.append("  OR position.positionId is null ");
			 
			 str.append(" )");
			 
		}else {
			if(departmentId != null && departmentId.longValue() > 0l){
				str.append(" AND department.departmentId = :departmentId" );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
					str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
				else
					str.append("  AND position.positionId is  null " );
			}
			else if(boardId != null && boardId.longValue() > 0l){
					if(departmentId != null && departmentId.longValue() > 0l)
						str.append(" AND department.departmentId = :departmentId" );
					else
						str.append(" AND department.departmentId is null " );
					if(boardId != null && boardId.longValue() > 0l)
						str.append("  AND board.boardId = :boardId " );
					else
						str.append("  AND board.boardId is null " );
					if(positionId != null && positionId.longValue() > 0l)
						str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
					else
						str.append("  AND position.positionId is  null " );
					
			}
			else if(positionId != null && positionId.longValue() > 0l){
				if(departmentId != null && departmentId.longValue() > 0l)
					str.append(" AND department.departmentId = :departmentId" );
				else
					str.append(" AND department.departmentId is null " );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
					str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
				else
					str.append("  AND position.positionId is  null " );
			}
		}
		
		str.append("GROUP BY position.positionId ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 6)

		 if(departmentId != null && departmentId.longValue() > 0L)
				query.setParameter("departmentId", departmentId);
		 if(boardId != null && boardId.longValue() > 0L)
			 query.setParameter("boardId", boardId);
		 if(positionId != null && positionId.longValue() > 0L)
			 query.setParameter("positionId", positionId);
		 
		 
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
				" count(distinct model.nominatedPostApplicationId)  ,  " +
				" model.applicationStatus.applicationStatusId , model.applicationStatus.status " +
				" FROM NominatedPostApplication model left join model.position position " +
				" left join model.departments department " +
				" left join model.board board   " +
				" WHERE   model.isDeleted ='N'  and model.isExpired = 'N' and model.applicationStatus.applicationStatusId not in (2,4,8)  " );
				//" AND model.locationValue = :locationValue" );
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() !=5L)
				str.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
			else
				str.append(" and model.boardLevel.boardLevelId in (5,6) ");
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
				/*	str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");*/
					str.append(" AND ( ");
					 if(departmentId != null && departmentId.longValue() > 0L)
						 str.append(" department.departmentId =:departmentId ");
					 else 
						 str.append(" department.departmentId is null ");
					 
					 if(boardId != null && boardId.longValue() > 0L)
						 str.append(" OR  board.boardId =:boardId ");
					 else 
						 str.append("  OR  board.boardId is null ");
					 
				/*	 if(positionId != null && positionId.longValue() > 0L)
						 str.append(" OR  (position.positionId =:positionId AND position.positionId is not null )");
					 else */
						// str.append("  OR position.positionId is null ");
					 
					 str.append(" )");
					 
					 str.append("  AND position.positionId is null ");
					 
				}else {
					if(departmentId != null && departmentId.longValue() > 0l){
						str.append(" AND department.departmentId = :departmentId" );
						if(boardId != null && boardId.longValue() > 0l)
							str.append("  AND board.boardId = :boardId " );
						else
							str.append("  AND board.boardId is null " );
						if(positionId != null && positionId.longValue() > 0l)
							// str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
							str.append("  AND position.positionId=:positionId  " );
						else
							str.append("  AND position.positionId is  null " );
					}
					else if(boardId != null && boardId.longValue() > 0l){
							if(departmentId != null && departmentId.longValue() > 0l)
								str.append(" AND department.departmentId = :departmentId" );
							else
								str.append(" AND department.departmentId is null " );
							if(boardId != null && boardId.longValue() > 0l)
								str.append("  AND board.boardId = :boardId " );
							else
								str.append("  AND board.boardId is null " );
							if(positionId != null && positionId.longValue() > 0l)
								//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
								str.append("  AND position.positionId=:positionId " );
							else
								str.append("  AND position.positionId is  null " );
							
					}
					else if(positionId != null && positionId.longValue() > 0l){
						if(departmentId != null && departmentId.longValue() > 0l)
							str.append(" AND department.departmentId = :departmentId" );
						else
							str.append(" AND department.departmentId is null " );
						if(boardId != null && boardId.longValue() > 0l)
							str.append("  AND board.boardId = :boardId " );
						else
							str.append("  AND board.boardId is null " );
						if(positionId != null && positionId.longValue() > 0l)
							//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
							str.append("  AND position.positionId=:positionId " );
						else
							str.append("  AND position.positionId is  null " );
					}
			}
				//str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
				str.append(" GROUP BY position.positionId,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
				
		//Query Calling
				Query query = getSession().createQuery(str.toString());
				
				//Parameters Setting(max 5)

				 if(departmentId != null && departmentId.longValue() > 0L)
						query.setParameter("departmentId", departmentId);
				 if(boardId != null && boardId.longValue() > 0L)
					 query.setParameter("boardId", boardId);
				 if(positionId != null && positionId.longValue() > 0L)
					 query.setParameter("positionId", positionId);
				 
				 
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
		str.append(" SELECT position.positionId,position.positionName,model.nominationPostCandidate.age, count(distinct model.nominatedPostApplicationId), " +
				"  model.applicationStatus.applicationStatusId , model.applicationStatus.status" +
				" FROM NominatedPostApplication model left join model.position position left join model.departments department left join model.board board   " +
				" WHERE   model.isDeleted ='N'  and model.isExpired = 'N' and model.applicationStatus.applicationStatusId not in (2,4,8)  " );
				if(boardLevelId != null && boardLevelId.longValue()>0L){
					if(boardLevelId.longValue() !=5L)
						str.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
					else
						str.append(" and model.boardLevel.boardLevelId in (5,6) ");
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
			/*str.append(" AND (model.departments.departmentId is null" +
				" OR model.board.boardId is null" +
				" OR position.positionId is null) ");*/
			str.append(" AND ( ");
			 if(departmentId != null && departmentId.longValue() > 0L)
				 str.append(" department.departmentId =:departmentId ");
			 else 
				 str.append(" department.departmentId is null ");
			 
			 if(boardId != null && boardId.longValue() > 0L)
				 str.append(" OR  board.boardId =:boardId ");
			 else 
				 str.append("  OR  board.boardId is null ");
			 
			/* if(positionId != null && positionId.longValue() > 0L)
				 str.append(" OR  (position.positionId =:positionId AND position.positionId is not null )");
			 else */
				 
			 
			 str.append(" )");
			 
			 str.append("  AND position.positionId is null ");
			 
		}else{
			if(departmentId != null && departmentId.longValue() > 0l){
				str.append(" AND department.departmentId = :departmentId" );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
				//	str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
					str.append("  AND position.positionId=:positionId " );
				else
					str.append("  AND position.positionId is  null " );
			}
			else if(boardId != null && boardId.longValue() > 0l){
					if(departmentId != null && departmentId.longValue() > 0l)
						str.append(" AND department.departmentId = :departmentId" );
					else
						str.append(" AND department.departmentId is null " );
					if(boardId != null && boardId.longValue() > 0l)
						str.append("  AND board.boardId = :boardId " );
					else
						str.append("  AND board.boardId is null " );
					if(positionId != null && positionId.longValue() > 0l)
						// str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
						str.append("  AND position.positionId=:positionId  " );
					else
						str.append("  AND position.positionId is  null " );
					
			}
			else if(positionId != null && positionId.longValue() > 0l){
				if(departmentId != null && departmentId.longValue() > 0l)
					str.append(" AND department.departmentId = :departmentId" );
				else
					str.append(" AND department.departmentId is null " );
				if(boardId != null && boardId.longValue() > 0l)
					str.append("  AND board.boardId = :boardId " );
				else
					str.append("  AND board.boardId is null " );
				if(positionId != null && positionId.longValue() > 0l)
					//str.append("  AND position.positionId=:positionId   AND position.positionId is not null " );
					str.append("  AND position.positionId=:positionId  " );
				else
					str.append("  AND position.positionId is  null " );
			}
		}
		//str.append(" AND model.nominationPostCandidate.tdpCadreId is not null ");
		
		str.append(" GROUP BY position.positionId,model.nominationPostCandidate.age ");
		
		//Query Calling
		Query query = getSession().createQuery(str.toString());
		
		//Parameters Setting(max 5)

		 if(departmentId != null && departmentId.longValue() > 0L)
				query.setParameter("departmentId", departmentId);
		 if(boardId != null && boardId.longValue() > 0L)
			 query.setParameter("boardId", boardId);
		 if(positionId != null && positionId.longValue() > 0L)
			 query.setParameter("positionId", positionId);
		 
		 
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
					" and NPA.departments.departmentId = :departmentId and NPA.isDeleted='N' ");
		
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
		sb.append(" and NPA.nominationPostCandidate.isDeleted = 'N' and NPA.isExpired = 'N' ");
		
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
				"    model.isDeleted ='N' and  model.nominationPostCandidate.isDeleted ='N' and model.isExpired = 'N'  ");
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
		queryStr.append(" select model.boardLevelId, count(distinct model.nominatedPostApplicationId), " +
				" count(distinct department.departmentId), " +
				" count(distinct board.boardId), " +
				"  nominatedPostMember.nominatedPostMemberId,department.departmentId,board.boardId  " +
				" from NominatedPostApplication model ");
		
		/*if(levelId != null && levelId.longValue()>1L && stateId != null){
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition ");
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		}
		else{
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition ");
			queryStr.append(" where ");
		}*/
		
		if(levelId != null && levelId.longValue()>1L && stateId != null){
			queryStr.append("  left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition" +
							"  left join nominatedPostPosition.departments department" +
							"  left join nominatedPostPosition.board  board ");
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and model.isDeleted='N'  and model.isExpired = 'N' " );
		}
		else{
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
							"  left join nominatedPostPosition.departments department " +
							"  left join nominatedPostPosition.board  board where model.isDeleted='N' and model.isExpired = 'N'  ");
		}
		
		//queryStr.append(" and  model.applicationStatusId is not null and nominatedPostMember is not null ");
		queryStr.append(" and  model.applicationStatusId is not null  "); // any board any department scenario member will not available in overview appln count
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
			queryStr.append(" and  model3.state.stateId=:stateId  ");
		
		queryStr.append("" +
				" and  model.isDeleted='N' and "+
				" nominatedPostMember.isDeleted='N' and "+
				" nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
				
				" group  by model.boardLevelId, nominatedPostMember.nominatedPostMemberId  order by model.boardLevelId ");
		
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
		
		/*StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count(model.nominatedPostMember.nominatedPostMemberId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +  
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) from NominatedPostApplication model " );
		if(levelId != null && levelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and " );
		else
			queryStr.append(" where "); */
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boardLevelId, count( distinct model.nominatedPostApplicationId), " +
				" count(distinct nominatedPostPosition.departmentId), " +
				" count(distinct nominatedPostPosition.boardId),nominatedPostMember.nominatedPostMemberId , " +
				"nominatedPostPosition.departmentId,nominatedPostPosition.boardId,model.applicationStatusId  " +
				" from NominatedPostApplication model ");
		if(levelId != null && levelId.longValue()>1L && stateId != null){
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition ");
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and model.isDeleted='N' and model.isExpired = 'N' " );
		}
		else{
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition where model.isDeleted='N' and model.isExpired = 'N' ");
		}
		queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")  ");
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
		
		queryStr.append(" " +
				" and model.isDeleted='N' and "+
				" nominatedPostMember.isDeleted='N' and "+
				" nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
				" group  by model.boardLevelId,nominatedPostMember.nominatedPostMemberId order by model.boardLevelId ");
		
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
				"    model.isDeleted ='N' and model.isExpired = 'N' and model.nominationPostCandidate.isDeleted ='N'  and model.nominationPostCandidateId not in ( " +
				" select distinct model1.nominationPostCandidateId from NominatedPostFinal model1 " +
				" where model1.isDeleted='N' and "+
				" model1.nominatedPostMember.isDeleted='N' and "+
				" model1.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model1.isExpired = 'N' "+
				" ) ");
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
	
	
	public List<Object[]> getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId,Long statusId){
		StringBuilder str = new StringBuilder();
		
		if(nominateCandId != null && nominateCandId.longValue()>0L)
			searchType="Not Cadre";
		str.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
				" model.boardLevel.boardLevelId,model.boardLevel.level,departments.departmentId," +
	        " departments.deptName,board.boardId,board.boardName,position.positionId," +
	        " position.positionName,model.postType.postTypeId,model.nominatedPostApplicationId " +
	        " from NominatedPostApplication model left join model.position position left join model.departments departments " +
	        " left join model.board  board  where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'   ");
		
			if(statusId != null && statusId.longValue() == 9l){
				str.append(" and model.isExpired = 'Y' and model.applicationStatus.applicationStatusId = :statusId " );
			}else{
				str.append(" and model.isExpired = 'N' " );
			}
			
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
	      
	        if(statusId != null){
	        	query.setParameter("statusId", statusId);
	        }
	        return query.list();
	  }
	public List<Object[]> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId,Long statusId){
		StringBuilder str = new StringBuilder(); 
		str.append(" select model.applicationStatus.applicationStatusId,model.applicationStatus.status,model.boardLevel.boardLevelId," +
	    		"model.boardLevel.level,dept.departmentId," +
	        " dept.deptName," +
	        " board.boardId,board.boardName," +
	        " position.positionId,position.positionName, " +
	        " model.locationValue, model.nominatedPostApplicationId  " +
	        " from NominatedPostApplication model" +
	        " left join model.departments dept" +
	        " left join model.board board" +
	        " left join model.position position " +
	        " where " );
		if(candidateId != null && candidateId.longValue()>0){
	        str.append(" model.nominationPostCandidateId = :candidateId " );
		 }
		str.append(" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N' " );
		if(statusId != null && statusId.longValue() == 9l){
			str.append(" and model.isExpired = 'Y' and model.applicationStatus.applicationStatusId = :statusId " );
		 }else{
			str.append(" and model.isExpired = 'N' " );
		 }
	      //  " and model.applicationStatus.status =:applied ");
		 Query query = getSession().createQuery(str.toString());
		if(candidateId != null && candidateId.longValue()>0){
	        query.setParameter("candidateId", candidateId);
		 }
		if(statusId != null && statusId.longValue() == 9l){
			query.setParameter("statusId", statusId);
		}
	      //  query.setParameter("applied",IConstants.NOMINATED_APPLIED_STATUS);
	        return query.list();
	  }
	public List<Object[]> getPositionDetaislOfEveryApplicationStatus(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType){
		
		StringBuilder str = new StringBuilder();
		
			if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" SELECT model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName," +
						" model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
						" count(distinct model.nominatedPostApplicationId) " +
						" FROM NominatedPostFinal model left join model.nominatedPostMember.nominatedPostPosition.position position" +
						" WHERE " +
						" model.isDeleted='N' and "+
						" model.nominatedPostMember.isDeleted='N' and "+
						" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and model.isExpired = 'N' "+
						"" );
				if(boardLevelId !=null && boardLevelId>0){
						str.append(" AND model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId");		
				}
				if(locationValues !=null && locationValues.size()>0){
					str.append(" AND model.nominatedPostMember.locationValue in (:locationValues)");
				}
			// Any Dept && Board && post Scenarios Consideration && non Consideration
				
				if(deptsIds !=null && deptsIds.size()>0){
					str.append(" AND model.nominatedPostMember.nominatedPostPosition.departments.departmentId in (:deptsIds) ");
				}
				if(boardIds !=null && boardIds.size()>0){
					str.append(" AND model.nominatedPostMember.nominatedPostPosition.board.boardId in (:boardIds) ");
				}
				
			if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
				
				str.append(" and   model.nominatedPostMember.nominatedPostPosition.position.positionId is not null ");
				
			}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
				str.append(" and  model.nominatedPostMember.nominatedPostPosition.position.positionId is null ");
			}
				if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
					str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
				}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
					str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
					//str.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
				}
				
				str.append(" GROUP BY  model.nominatedPostMember.nominatedPostPosition.position.positionId,model.applicationStatus.applicationStatusId " +
							" ORDER BY  model.nominatedPostMember.nominatedPostPosition.position.positionId");
		}
		else{
		str.append(" SELECT position.positionId,position.positionName,model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
				" count(distinct model.nominatedPostApplicationId) " +
				" FROM NominatedPostApplication model left join model.position position" +
				" WHERE " +
				" model.isDeleted = 'N'  and model.isExpired = 'N' " );
		
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
		
		if(deptsIds !=null && deptsIds.size()>0){
			str.append(" AND model.departments.departmentId in (:deptsIds) ");
		}
		if(boardIds !=null && boardIds.size()>0){
			str.append(" AND model.board.boardId in (:boardIds) ");
		}
		
		
	if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
		
		str.append(" and  model.positionId is not null ");
		
	}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
		str.append(" and model.positionId is null ");
	}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
			//str.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
		}
		
		str.append(" GROUP BY position.positionId,model.applicationStatus.applicationStatusId " +
					" ORDER BY position.positionId");
		
		}
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){			
				query.setParameter("boardLevelId", boardLevelId);		
		}
		if(locationValues !=null && locationValues.size()>0){
			query.setParameterList("locationValues",locationValues);
		}
		if(deptsIds !=null && deptsIds.size()>0){
			query.setParameterList("deptsIds",deptsIds);
		}
		if(boardIds !=null && boardIds.size()>0){
			query.setParameterList("boardIds",boardIds);
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
		       if(status != null && (status.equalsIgnoreCase("TOTAL") || status.equalsIgnoreCase("goPassed")))
		       {
			       queryStr.append(" select ");
			       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
			    	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId,model.nominatedPostMember.nominatedPostPosition.departments.deptName,");
			       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
			      	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName,");
			       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
			 		  queryStr.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName,");
			 	   }

			       queryStr.append(" model.nominatedPostStatusId,model.nominatedPostStatus.status,count(model.nominatedPostId), model.nominatedPostMemberId "); 
			       
			       queryStr.append(" from  NominatedPost model where " +
			    		   "  model.isDeleted='N' and "+
							" model.nominatedPostMember.isDeleted='N' and "+
							" model.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
			       		"");
			       
			       if(status != null && status.equalsIgnoreCase("finalReview"))
			    	   queryStr.append(" and model.nominatedPostStatusId = 2 ");
			       else if(status != null && status.equalsIgnoreCase("finaliZed"))
			    	   queryStr.append(" and model.nominatedPostStatusId = 3 ");
			       else if(status != null && status.equalsIgnoreCase("goPassed"))
			    	   queryStr.append(" and model.nominatedPostStatusId = 4 ");
			      // else if(status != null && status.equalsIgnoreCase("total"))
			    	  // queryStr.append(" and model.nominationPostCandidateId is null ");
			       
			        if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
		    	 	   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
		    	    }
			      /* if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
			    	   if(LocationLevelId.longValue() != 5L)
			    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
			    	   else
			    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId in (5,6) ");
			       }*/
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
			    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.position.positionId order by  model.nominatedPostMember.nominatedPostPosition.position.orderNo ");
			       }
			       
			       Query query = getSession().createQuery(queryStr.toString());
			       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
			    	  // if(LocationLevelId.longValue() != 5L)
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
		       else{
				       queryStr.append(" select ");
				       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
				    	   queryStr.append(" model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId,model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName,");
				       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
				      	   queryStr.append(" model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName,");
				       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
				 		  queryStr.append(" model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionName,");
				 	   }
				       if(status.equalsIgnoreCase("Total")  && LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
				       queryStr.append(" model.nominatedPost.nominatedPostStatusId,model.nominatedPost.nominatedPostStatus.status,count(distinct model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId)");
				       }else{
				    	   queryStr.append(" model.nominatedPost.nominatedPostStatusId,model.nominatedPost.nominatedPostStatus.status,count(model.nominatedPost.nominatedPostId)"); 
				       }
				       
				       queryStr.append(", model.nominatedPostMemberId from  NominatedPostFinal model where model.nominatedPost.isDeleted = 'N' " +
				    		    "  and model.isDeleted='N' and "+
								" model.nominatedPostMember.isDeleted='N' and "+
								" model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostApplication.isDeleted = 'N' " +
								" and model.nominatedPostApplication.isExpired = 'N' and model.isExpired = 'N' "+
				       		"");
				       if(status != null && status.equalsIgnoreCase("finalReview"))
				    	   queryStr.append(" and model.applicationStatusId = 6 ");
				       else if(status != null && status.equalsIgnoreCase("finaliZed"))
				    	   queryStr.append(" and model.applicationStatusId = 5 ");
				       else if(status != null && status.equalsIgnoreCase("goPassed"))
				    	   queryStr.append(" and model.applicationStatusId = 7 ");
				        if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
			    	 	   queryStr.append(" and model.nominatedPost.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
			    	    }
				      /* if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
				    	   if(LocationLevelId.longValue() != 5L)
				    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:LocationLevelId ");
				    	   else
				    		   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId in (5,6) ");
				       }*/
				       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
				    	   queryStr.append(" and model.nominatedPost.nominatedPostMember.locationValue in (:lctnLevelValueList)");
				       }
				       if(departmentId != null && departmentId.longValue() > 0){
				    	   queryStr.append(" and model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId=:departmentId ");
				    	   
				       }
				       if(boardId != null && boardId.longValue() > 0){
				    	   queryStr.append(" and model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId=:boardId ");
				       }
				       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
				    	   queryStr.append(" group by model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId, model.nominatedPostMemberId order by model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId ");
				       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
				     	   queryStr.append(" group by model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId, model.nominatedPostMemberId order by model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId ");
				       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
				    	   queryStr.append(" group by model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId, model.nominatedPostMemberId order by  model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId ");
				       }
				       
				       Query query = getSession().createQuery(queryStr.toString());
				       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
				    	  // if(LocationLevelId.longValue() != 5L)
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
	}
 public List<Object[]> getApplicationStatusCntByPositionId(Long positionId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.applicationStatus.applicationStatusId, model.applicationStatus.status,count(distinct model.nominatedPostFinalId)  from NominatedPostFinal model,NominatedPostApplication model1  " +
				" where model.isDeleted = 'N'  and "+
				" model.nominatedPostMember.isDeleted='N' and "+
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
				" and model1.isDeleted = 'N' and model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" and model1.isExpired = 'N' and model.isExpired = 'N' ");
		
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
	   	          	   " from NominatedPostApplication model where model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N' " +
	   	          	   " and model.isExpired = 'N' ");
	   
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
	   	          	   " from NominatedPostApplication model where model.isDeleted='N' and model.nominationPostCandidate.isDeleted='N' " +
	   	          	   " and model.isExpired = 'N' ");
	   
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
	 queryStr.append(" select distinct model.nominationPostCandidate.casteState.casteStateId,model.nominationPostCandidate.casteState.caste.casteName from NominatedPostApplication model where model.isDeleted = 'N'" +
	 		"  and model.nominationPostCandidate.isDeleted='N' and model.isExpired = 'N' ");
	 
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
 public List<Object[]> getApplicationDetailsCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId){
	 
	 StringBuilder queryStr = new StringBuilder();
	 
	   queryStr.append("select distinct  model.applicationStatus.applicationStatusId,model.applicationStatus.status,count(distinct model.nominatedPostApplicationId) from NominatedPostApplication model " +
	 		" where" +
	 		" model.isDeleted='N' and model.isExpired = 'N'  ");
	   
		   if(positionId != null && positionId.longValue() > 0){
			   queryStr.append(" and model.position.positionId = :positionId");
		   }
		   if(boardLevelId != null && boardLevelId.longValue() > 0){
			   if(boardLevelId.longValue() != 5L)
				   queryStr.append(" and model.boardLevel.boardLevelId=:boardLevelId ");
			   else
				   queryStr.append(" and model.boardLevel.boardLevelId in (5,6) ");
		   }
		   if(stateId != null && stateId.longValue() > 0){
 			   queryStr.append(" and model.address.state.stateId=:stateId");
 		  }
	       queryStr.append(" group by model.applicationStatus.applicationStatusId ");
	   
	      Query query = getSession().createQuery(queryStr.toString());
		  
		  if(positionId != null && positionId.longValue() > 0){
		   query.setParameter("positionId", positionId);
		   }  
		   if(boardLevelId != null && boardLevelId.longValue() > 0 && boardLevelId.longValue() != 5L){
			query.setParameter("boardLevelId", boardLevelId);   
		   }
		   if(stateId != null && stateId.longValue() > 0){
	  			  query.setParameter("stateId", stateId); 
	  	  }
	  return query.list();
 }
 
	public List<Object[]> getTotalApplicationCountsByBoard(Long boardLevelId,Long searchLevelId,Long searchLevelValue,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct  department.departmentId," +
					" board.boardId," +
					" count(model.nominatedPostApplicationId)" );
					if(statusId != null && statusId.longValue()==3L)
						 sb.append(" from NominatedPostFinal model "+
									" left join model.nominatedPostMember.nominatedPostPosition.board board " +
									" left join model.nominatedPostMember.nominatedPostPosition.departments department " +
								 	" left join model.nominatedPostMember.boardLevel boardLevel " +
								 	" left join model.nominatedPostMember.address address "+					
						" where boardLevel.boardLevelId = :boardLevelId " +
						" and model.isDeleted='N' and "+
						" model.nominatedPostMember.isDeleted='N' and "+
						" model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.isExpired = 'N' "+
						" ");
					else{
						 sb.append(" from NominatedPostApplication model " +
									" left join model.departments department " +
									" left join model.board board " +
									" where model.boardLevelId = :boardLevelId and model.isDeleted='N' and model.isExpired = 'N' ");
					}
		
		if(statusId != null && statusId.longValue()==3L){
			if(searchLevelId != null && searchLevelId.longValue() > 0l){
				 if(searchLevelId == 1l)
					 sb.append(" and address.country.countryId = 1");
				 else if(searchLevelId == 2l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.state.stateId = :searchLevelValue");
				 else if(searchLevelId == 3l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.district.districtId = :searchLevelValue");
				 else if(searchLevelId == 4l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.constituency.constituencyId = :searchLevelValue");
				 else if(searchLevelId == 5l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.tehsil.tehsilId = :searchLevelValue");
				 else if(searchLevelId == 6l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.localElectionBody.localElectionBodyId = :searchLevelValue");
				 else if(searchLevelId == 7l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
					 sb.append(" and address.panchayat.panchayatId = :searchLevelValue");
			 }
		}
		else{
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
		}
		 if(statusId != null && statusId.longValue() > 0l )
			 sb.append(" and model.applicationStatusId  in (:statusId)");
		 sb.append(" and model.isDeleted = 'N'" +
		 			" group by department.departmentId,board.boardId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("boardLevelId", boardLevelId);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			query.setParameter("searchLevelValue", searchLevelValue);
		
		return query.list();
	}
	
	public List<Object[]> getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue){
		  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count( model.nominatedPostApplicationId), model.nominatedPostMember.nominatedPostMemberId ");
		queryStr.append(" from NominatedPostApplication model   " );
		queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N'  and model.nominatedPostMember.isDeleted='N'  and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'" +
				" and model.isExpired = 'N'  ");	// for total 
						
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		}
		queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") ");
		
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
	
	public int updateApplicationStatusToFinal(List<Long> finalIds,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		DateUtilService dateUtilService = new DateUtilService();
		
		queryStr.append("UPDATE NominatedPostApplication model SET model.applicationStatus.applicationStatusId = :applicationStatusId," +
				" model.updatedBy =:updatedBy," +
				" model.updatedTime =:updatedTime" +
				"	WHERE  model.isDeleted = 'N' and model.isExpired = 'N' " );
		
		if(finalIds !=null && finalIds.size()>0){
			queryStr.append("  and model.nominatedPostApplicationId in (:finalIds)  ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(finalIds !=null && finalIds.size()>0){
			query.setParameterList("finalIds",finalIds);
		}
		
		query.setParameter("applicationStatusId", IConstants.NOMINATED_APPLICATION_FINAL_REVIEW);
		query.setParameter("updatedBy", userId);
		query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
		
		
		return query.executeUpdate();
	}
	public List<Object[]> getAnyDeptApplicationOverviewCountLocationWise(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
		      List<Long> locationValue,Long searchLevelId,String status,String statusType){
		    
		    StringBuilder str = new StringBuilder();
		    str.append("SELECT position.positionId ");
		    if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    str.append(" ,model.applicationStatus.applicationStatusId ");	
		    }
		    str.append(",count(distinct model.nominatedPostApplicationId),model.applicationStatus.applicationStatusId " +
		        " FROM NominatedPostApplication model  " +
		        " left join model.position position " +
		        " WHERE model.isDeleted='N'  and model.isExpired = 'N' ");
		    if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    	//str.append(" and model.nominatedPostMemberId is not null ");
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
		     
		     if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
					//str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
				}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
					str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
					//str.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
				}
		     
		    str.append(" GROUP BY position.positionId ");
		    
		   // if(status!= null && status.equalsIgnoreCase("nominatedPostMemeber")){
		    	str.append(", model.applicationStatus.applicationStatusId");	
		    //}
		    
		    
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
	
	public List<Object[]> getRecord(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList){
		Query query = getSession().createQuery(" select model.nominatedPostApplicationId,model.nominationPostCandidateId,model.departmentId,model.boardId," +
				" model.positionId,model.boardLevelId,model.locationValue,model.applicationStatusId,model.insertedBy,date(model.insertedTime),model.updatedBy," +
				" date(model.updatedTime),model.isDeleted " +
				" from NominatedPostApplication model " +
				" where model.nominatedPostMember.boardLevelId=:locationLevelId " +
				" and model.nominatedPostMember.locationValue in (:locationLevelValueList) " +
				" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId " +
				" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId " +
				" and model.nominatedPostMember.nominatedPostPosition.positionId in (:positionsList) " +
				" and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
				" and model.applicationStatusId=:oldStatus  and model.isExpired = 'N' ");
		
		query.setParameter("locationLevelId", locationLevelId);
		query.setParameterList("locationLevelValueList", locationLevelValueList);
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameterList("positionsList", positionsList);
		query.setParameter("oldStatus", 5l);
		
		return query.list();
	}
	
	public Integer updateApplicationStatusForGO(List<Long> nominatedPostApplicationIds,Date date){
		Query query = getSession().createQuery(" update NominatedPostApplication model set model.applicationStatusId=:statusId, model.updatedTime=:date" +
				" where model.nominatedPostApplicationId in (:nominatedPostApplicationIds) ");
		
		query.setDate("date", date);
		query.setParameter("statusId", 7l);
		query.setParameterList("nominatedPostApplicationIds", nominatedPostApplicationIds);
		
		return query.executeUpdate();
	}
	
	public Object[] getBoardLevel(Long applicationId){
		Query query = getSession().createQuery(" select model.boardLevel.boardLevelId,model.locationValue from NominatedPostApplication model where model.nominatedPostApplicationId = :applicationId " +
				" and model.isDeleted='N'  and model.isExpired = 'N' ");	
	
		query.setParameter("applicationId", applicationId);
		return (Object[])query.uniqueResult();
	}

	public List<Object[]> getApplicationDetailsOfCandidate(Set<Long> candidateIds){
		
		Query query = getSession().createQuery(" select model.nominationPostCandidateId,count(distinct model.nominatedPostApplicationId)" +
				" from  NominatedPostApplication model " +
				" where model.nominationPostCandidateId in (:candidateIds) " +
				" and model.isDeleted ='N' and model.isExpired = 'N' " +
				//" and model.applicationStatus.status=:applied" +
				" group by model.nominationPostCandidateId ");
		
		query.setParameterList("candidateIds", candidateIds);
		//query.setParameter("applied", IConstants.NOMINATED_APPLIED_STATUS);
		
		return query.list();
	}
	public List<Object[]> getAppliedPositionsForCandidate(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long locationLevelId,Long nominatedPostCandId){
		StringBuilder queryStr = new StringBuilder();
		   
	    queryStr.append(" select distinct position.positionId,model.nominatedPostApplicationId from NominatedPostApplication model left join model.position position " +
				" where model.boardLevel.boardLevelId=:boardLevelId " +
				" and model.locationValue =:searchLevelValue and model.applicationStatus.applicationStatusId not in (2,4,8) and model.isExpired = 'N' " );
	    if(departmentId != null && departmentId.longValue() > 0l){
	    	queryStr.append(" and model.departments.departmentId=:departmentId " );
	    }else{
	    	queryStr.append(" and model.departments.departmentId is null " );
	    }
	    
	    if(boardId != null && boardId.longValue() > 0l){
	    	queryStr.append(" and model.board.boardId=:boardId " );
	    }else{
	    	queryStr.append(" and model.board.boardId is null " );
	    }
				
	    queryStr.append(" and model.isDeleted='N' and model.nominationPostCandidate.nominationPostCandidateId =:nominatedPostCandId and model.nominationPostCandidate.isDeleted ='N' ");
	    
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("boardLevelId", boardLevelId);
		query.setParameter("searchLevelValue", searchLevelValue);
		 if(departmentId != null && departmentId.longValue() > 0l){
		query.setParameter("departmentId", departmentId);
		 }
		 if(boardId != null && boardId.longValue() > 0l){
		query.setParameter("boardId", boardId);
		 }
		query.setParameter("nominatedPostCandId", nominatedPostCandId);
	    return query.list(); 
	}
	
	public List<Long> getApplicationIds(Long deptId,Long boardId,List<Long> positions,Long levelId,List<Long> searchLevelValues,Long userId,List<Long> statusIds){

		
		StringBuilder queryStr = new StringBuilder();
		DateUtilService dateUtilService = new DateUtilService();
		
		queryStr.append("SELECT model.nominatedPostApplicationId" +
				" FROM NominatedPostApplication model " +
				"	WHERE  model.isDeleted = 'N'" +
				"	AND model.nominatedPostMember.isDeleted ='N' " +
				" AND model.nominatedPostMember.nominatedPostPosition.isDeleted ='N'  and model.isExpired = 'N' ");
		
		if(statusIds !=null && statusIds.size()>0){
			queryStr.append(" AND model.applicationStatusId in (:statusIds) ");
		}
		
		if(deptId !=null && deptId>0){
			queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.departmentId = :departmentId ");
		}
		if(boardId !=null && boardId>0){
			queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.boardId = :boardId ");
		}else{
			queryStr.append(" AND model.nominatedPostMember.nominatedPostPosition.boardId is null ");
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
		if(statusIds !=null && statusIds.size()>0){
			query.setParameterList("statusIds",statusIds);
		}
		
		//query.setParameter("applicationStatusId", IConstants.NOMINATED_APPLICATION_FINAL_REVIEW);
		//query.setParameter("shortListId", 3L);
	//	query.setParameter("updatedBy", userId);
	//	query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
		
		
		return query.list();
	
	}
	public List<Object[]> getFinalReviewCandidateCountForLocation(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId, Long positionId, String status){
		
	       StringBuilder queryStr = new StringBuilder(); 
	       
	       queryStr.append(" select distinct ");
	       if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l){
	    	   queryStr.append(" D.departmentId,D.deptName,");  
	       }
	       
	       
	       queryStr.append(" NPS.nominatedPostStatusId, " +
	       				   " NPS.status, " +
	       				   " model.nominatedPostId, " +
	       				   " NPC.candidateName, " + 
	       				   " TC.firstname, " +
	       				   " NPC.mobileNo, " +
	       				   " TC.mobileNo, " +
	       				   " TC.memberShipNo, " +
	       				   " NPC.age, " +
	       				   " TC.age, " +
	       				   " B.boardId, " +
	       				   " B.boardName, " +
	       				   " P.positionId, " +
	       				   " P.positionName, " +  
	       				   " C.casteId, " +
	       				   " C.casteName, " +
	       				   " CC.casteCategoryId, " +
	       				   " CC.categoryName, " +
	       				   " GO.govtOrderId, " +
	       				   " GO.orderName, " +
	       				   " GO.fromDate, " +
	       				   " GO.toDate,TC.image,NPC.imageurl,TC.tdpCadreId ");   
	  
	       
	       queryStr.append(" from  " +  
	       				   " NominatedPost model, " +
	       				   " NominatedPostGovtOrder NPGO " +
	       				   " left join model.nominationPostCandidate NPC " +   
	       				   " left join model.nominatedPostStatus NPS" +
	       				   " left join NPC.tdpCadre TC " +
	       				   " left join model.nominatedPostMember NPM " +  
	       				   " left join NPM.nominatedPostPosition NPP " +
	       				   " left join NPM.boardLevel BL " +
	       				   " left join NPP.board B " +
	       				   " left join NPP.position P " +
	       				   " left join NPP.departments D" +
	       				   " left join NPC.casteState CS " +
	       				   " left join CS.caste C " +
	       				   " left join CS.casteCategoryGroup CCG " +
	       				   " left join CCG.casteCategory CC " +
	       				   " left join NPGO.govtOrder GO " +
	       				   " left join NPGO.nominatedPost NPOST " +  
	       		           " where " +
	       		           " model.isDeleted = 'N' " +  
	       		           " and NPGO.isDeleted = 'N' " +
	       		           " and NPGO.govtOrder.isDeleted = 'N' " +
	       		           " and model.isDeleted='N' and "+
	       		           " model.nominatedPostMember.isDeleted='N' and "+
	       		           " model.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
	       		           " ");    
	       
	       if(status != null && status.equalsIgnoreCase("finalReview"))
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 2 ");
	       else if(status != null && status.equalsIgnoreCase("finaliZed"))
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 3 ");
	       else if(status != null && status.equalsIgnoreCase("goPassed"))      
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 4 ");
	       
	       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	   if(LocationLevelId.longValue() != 5L)
	    		   queryStr.append(" and BL.boardLevelId=:LocationLevelId ");  
	    	   else
	    		   queryStr.append(" and BL.boardLevelId in (5,6) ");
	       }
	       
	       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
	    	   queryStr.append(" and NPM.locationValue in (:lctnLevelValueList)");  
	       }
	       
	       if(departmentId != null && departmentId.longValue() > 0){
	    	   queryStr.append(" and D.departmentId=:departmentId ");        
	    	   
	       }
	       queryStr.append(" and model.nominatedPostId = NPOST.nominatedPostId " +   
	       		           " and NPC.isDeleted = 'N' " +
	       		           //" and TC.isDeleted = 'N' " +
	       		           " and NPM.isDeleted = 'N' " + 
	       		           " and NPP.isDeleted = 'N' " +  
	       		           " ");
	       if(boardId != null && boardId.longValue() > 0){
	    	   queryStr.append(" and B.boardId=:boardId ");
	       }
	       if(positionId != null && positionId.longValue() > 0){
	    	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId ");
	       }
	       /*if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() ==  0l){
	    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId order by model.nominatedPostMember.nominatedPostPosition.departments.departmentId ");
	       }*/
	       queryStr.append(" order by model.nominatedPostId, B.boardId, P.positionId");    
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
	       if(positionId != null && positionId.longValue() > 0){  
	    	   query.setParameter("positionId", positionId); 
	       }
	    return query.list();  
}
	
	public List<Object[]> getTotalAvaiableApplicationsDetails(Long boardLevelId,Long stateId,Long applicationStatusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.applicationStatusId, count(distinct model.nominatedPostApplicationId)  from NominatedPostApplication model " +
				" where model.isDeleted ='N'  and model.isExpired = 'N' ");
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and model.boardLevelId=:boardLevelId ");
			else
				queryStr.append(" and model.boardLevelId in (5,6) ");
		}
		if(stateId != null && stateId>0L)
			queryStr.append(" and model.address.state.stateId=:stateId ");
		if(applicationStatusId != null && applicationStatusId>0L)
			queryStr.append(" and model.applicationStatusId=:applicationStatusId ");
		
		queryStr.append(" group by model.applicationStatusId order by model.applicationStatusId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(boardLevelId != null && boardLevelId.longValue()>0L){
				if(boardLevelId.longValue() != 5L)
					 query.setParameter("boardLevelId", boardLevelId);
			}
		  
		  if(stateId != null && stateId>0L)
			query.setParameter("stateId", stateId);
		  if(applicationStatusId != null && applicationStatusId>0L)
			query.setParameter("applicationStatusId", applicationStatusId);
		  
		  return query.list();
	}
	public List<Object[]> getFinalReviewCandidateCountForLocationFilter(Long LocationLevelId, List<Long> lctnLevelValueList, List<Long> deptList, List<Long> boardList, List<Long> positionList, Date fromDate, Date expDate, String status){
		
	       StringBuilder queryStr = new StringBuilder();  
	       queryStr.append(" select distinct " +
	       				   " D.departmentId,D.deptName," +//0,1
	       				   " NPS.nominatedPostStatusId, " +
	       				   " NPS.status, " +
	       				   " model.nominatedPostId, " +
	       				   " NPC.candidateName, " + 
	       				   " TC.firstname, " +//6
	       				   " NPC.mobileNo, " +
	       				   " TC.mobileNo, " +
	       				   " TC.memberShipNo, " +
	       				   " NPC.age, " +
	       				   " TC.age, " +//11
	       				   " B.boardId, " +
	       				   " B.boardName, " +
	       				   " P.positionId, " +
	       				   " P.positionName, " +  
	       				   " C.casteId, " +//16
	       				   " C.casteName, " +
	       				   " CC.casteCategoryId, " +
	       				   " CC.categoryName, " +
	       				   " GO.govtOrderId, " +
	       				   " GO.orderName, " + //21 
	       				   " GO.fromDate, " +
	       				   " GO.toDate ,TC.image,NPC.imageurl,TC.tdpCadreId ");//26   
	  
	       
	       queryStr.append(" from  " +  
	       				   " NominatedPost model, " +
	       				   " NominatedPostGovtOrder NPGO " +
	       				   " left join model.nominationPostCandidate NPC " +   
	       				   " left join model.nominatedPostStatus NPS" +
	       				   " left join NPC.tdpCadre TC " +
	       				   " left join model.nominatedPostMember NPM " +  
	       				   " left join NPM.nominatedPostPosition NPP " +
	       				   " left join NPM.boardLevel BL " +
	       				   " left join NPP.board B " +
	       				   " left join NPP.position P " +
	       				   " left join NPP.departments D" +
	       				   " left join NPC.casteState CS " +
	       				   " left join CS.caste C " +
	       				   " left join CS.casteCategoryGroup CCG " +
	       				   " left join CCG.casteCategory CC " +
	       				   " left join NPGO.govtOrder GO " +
	       				   " left join NPGO.nominatedPost NPOST " +  
	       		           " where " +
	       		           " model.isDeleted = 'N' " +  
	       		           " and NPGO.isDeleted = 'N' " +
	       		           " and NPGO.govtOrder.isDeleted = 'N' " +
	       		           " and model.isDeleted='N' and "+
	       		           " model.nominatedPostMember.isDeleted='N' and "+
	       		           " model.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
	       		           " ");    
	       
	       if(status != null && status.equalsIgnoreCase("finalReview"))
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 2 ");
	       else if(status != null && status.equalsIgnoreCase("finaliZed"))
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 3 ");
	       else if(status != null && status.equalsIgnoreCase("goPassed"))      
	    	   queryStr.append(" and NPS.nominatedPostStatusId = 4 ");
	       
	       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	   if(LocationLevelId.longValue() != 5L)
	    		   queryStr.append(" and BL.boardLevelId=:LocationLevelId ");  
	    	   else
	    		   queryStr.append(" and BL.boardLevelId in (5,6) ");
	       }
	       
	       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
	    	   queryStr.append(" and NPM.locationValue in (:lctnLevelValueList)");  
	       }
	       
	       if(deptList != null && deptList.size() > 0 && deptList.get(0) != 0l){
	    	   if(deptList.size() == 1 && deptList.get(0).longValue() >0L)
	    		   queryStr.append(" and D.departmentId in (:deptList) ");  
	    	   else if(deptList.size() > 1)
	    		   queryStr.append(" and D.departmentId in (:deptList) ");  
	       }
	     
	       if(fromDate != null && expDate != null){
	    	   queryStr.append(" and (date(NPGO.govtOrder.toDate) between :lowerRange and :expDate ) ");        
	       }  
	       queryStr.append(" and model.nominatedPostId = NPOST.nominatedPostId " +   
	       		           " and NPC.isDeleted = 'N' " +
	       		           //" and TC.isDeleted = 'N' " +
	       		           " and NPM.isDeleted = 'N' " + 
	       		           " and NPP.isDeleted = 'N' " +  
	       		           " ");
	       if(boardList != null && boardList.size() > 0  && boardList.get(0) != 0l){
	    	   if(boardList.size() == 1 && boardList.get(0).longValue() >0L)
	    		   queryStr.append(" and B.boardId in (:boardList) "); 	
        	   else if(boardList.size() > 1)
        		   queryStr.append(" and B.boardId in (:boardList) "); 
	       }
	       if(positionList != null && positionList.size() > 0 && positionList.get(0) != 0l){ 
	    	   if(positionList.size() == 1 && positionList.get(0).longValue() >0L)
	    		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId in (:positionList) ");
	    	   else if(positionList.size() > 1)
	    		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId in (:positionList) ");
	       }
	       /*if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() ==  0l){
	    	   queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId order by model.nominatedPostMember.nominatedPostPosition.departments.departmentId ");
	       }*/
	       queryStr.append(" order by model.nominatedPostId, B.boardId, P.positionId");    
	       Query query = getSession().createQuery(queryStr.toString());
	       
	       if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	   if(LocationLevelId.longValue() != 5L)
	    		   query.setParameter("LocationLevelId", LocationLevelId);
	       }
	       if(lctnLevelValueList != null && lctnLevelValueList.size() > 0){
	    	   query.setParameterList("lctnLevelValueList", lctnLevelValueList);
	       }
	       if(deptList != null && deptList.size() > 0 && deptList.get(0) != 0l){
	    	   if(deptList.size() == 1 && deptList.get(0).longValue() >0L)
	    		   query.setParameterList("deptList", deptList);
	    	   else if(deptList.size() > 1)
	    		   query.setParameterList("deptList", deptList);
	       }
	       if(boardList != null && boardList.size() > 0  && boardList.get(0) != 0l){
	    	   if(boardList.size() == 1 && boardList.get(0).longValue() >0L)
	    		   query.setParameterList("boardList", boardList);  
	    	   else if(boardList.size() > 1)
	    		   query.setParameterList("boardList", boardList);  
	       }
	       
	       if(positionList != null && positionList.size() > 0 && positionList.get(0) != 0l){  
	    	   if(positionList.size() == 1 && positionList.get(0).longValue() >0L)
	    		   query.setParameterList("positionList", positionList); 
	    	   else if(positionList.size() > 1)
	    		   query.setParameterList("positionList", positionList); 
	       }
	       if(fromDate != null && expDate != null){
	    	   query.setDate("lowerRange", fromDate);
	    	   query.setDate("expDate", expDate);
	       }
	       
	    return query.list();  
	}

@SuppressWarnings("unchecked")
public List<Object[]> getNominatedPostsAppliedApplciationsDetalsNew(Long levelId,Date startDate,Date endDate,Long stateId,String type){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.boardLevelId,count(distinct model1.nominatedPostId),count(distinct model.nominatedPostApplicationId)," +
				" nominatedPostMember.nominatedPostMemberId " +				
				" from NominatedPostApplication model,NominatedPost model1 ");	
		
		if(levelId != null && levelId.longValue()>1L && stateId != null){
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition ");
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and model.isDeleted='N' and model.isExpired = 'N' " );
			queryStr.append(" and nominatedPostMember.nominatedPostMemberId = model1.nominatedPostMember.nominatedPostMemberId " );
		}
		else{
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition where model.isDeleted='N' and model.isExpired = 'N' ");
		}
		
		//queryStr.append(" and  model.applicationStatusId is not null and nominatedPostMember is not null ");
		queryStr.append(" and  model.nominatedPostMember.nominatedPostMemberId = model1.nominatedPostMember.nominatedPostMemberId and " +
				" model.applicationStatusId is not null " +
				" and model1.isDeleted='N' and "+
				" model1.nominatedPostMember.isDeleted='N' and "+
				" model1.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
				"  ");
		if(levelId.longValue() != 5L)
			queryStr.append("   and model.boardLevelId =:levelId ");
		else
			queryStr.append("   and model.boardLevelId in (5,6) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		
		if(type !=null && type.trim().equalsIgnoreCase("applied")){
			queryStr.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+") " );
		}else if(type !=null && type.trim().equalsIgnoreCase("running")){
			queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") " );
			queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
		}
		
			queryStr.append(" and model1.nominatedPostStatus.nominatedPostStatusId in ("+IConstants.NOMINATED_OPEN_POSTS_STATUS_IDS+") ");//not finalyzed and g.o passed
		
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId , nominatedPostMember.nominatedPostMemberId " +
				"  order by model.boardLevelId ");
		
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

@SuppressWarnings("unchecked")
public List<Object[]> getNominatedPostsAppliedApplcitionsDetalsNew(Long levelId,Date startDate,Date endDate,Long stateId,String type){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.boardLevelId,count(distinct model1.nominatedPostId),count(distinct model.nominatedPostApplicationId)," +
				" nominatedPostMember.nominatedPostMemberId " +				
				" from NominatedPostApplication model,NominatedPost model1 ");	
		
		if(levelId != null && levelId.longValue()>1L && stateId != null){
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition ");
			queryStr.append(" ,UserAddress model3 where model.addressId = model3.userAddressId and model.isDeleted='N' and model.isExpired = 'N' " );
			queryStr.append(" and nominatedPostMember.nominatedPostMemberId = model1.nominatedPostMember.nominatedPostMemberId " );
		}
		else{
			queryStr.append(" left join model.nominatedPostMember nominatedPostMember ");
			queryStr.append("  left join nominatedPostMember.nominatedPostPosition nominatedPostPosition where model.isDeleted='N' and model.isExpired = 'N' ");
		}
		
		queryStr.append(" and  model.applicationStatusId is not null and nominatedPostMember is not null " +
				" and model1.isDeleted='N' and "+
				" model1.nominatedPostMember.isDeleted='N' and "+
				" model1.nominatedPostMember.nominatedPostPosition.isDeleted='N' "+
				" ");
		if(levelId.longValue() != 5L)
			queryStr.append("   and model.boardLevelId =:levelId ");
		else
			queryStr.append("   and model.boardLevelId in (5,6) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		
		if(type !=null && type.trim().equalsIgnoreCase("applied")){
			queryStr.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+") " );
		}else if(type !=null && type.trim().equalsIgnoreCase("running")){
			queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") " );
			queryStr.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
		}
		
			queryStr.append(" and model1.nominatedPostStatus.nominatedPostStatusId in ("+IConstants.NOMINATED_OPEN_POSTS_STATUS_IDS+") ");//not finalyzed and g.o passed
		
		if(levelId != null && levelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model3.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model3.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(levelId != null && levelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model3.state.stateId=:stateId ");
		
		queryStr.append(" group  by model.boardLevelId , nominatedPostMember.nominatedPostMemberId " +
				"  order by model.boardLevelId ");
		
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

public int updateApllicationStatusToReject(Long nominatedPostApplicationId,Long statusId,Long userId){
	
	StringBuilder queryStr = new StringBuilder();
	DateUtilService dateUtilService = new DateUtilService();
	
	queryStr.append("UPDATE NominatedPostApplication model SET model.applicationStatus.applicationStatusId = :applicationStatusId," +
			" model.updatedBy =:updatedBy," +
			" model.updatedTime =:updatedTime" +
			"	WHERE  model.isDeleted = 'N' and model.nominatedPostApplicationId =:nominatedPostApplicationId and model.applicationStatusId in (1,3,6) " +
			" and model.isExpired = 'N' " );
	
	Query query = getSession().createQuery(queryStr.toString());
	
	query.setParameter("nominatedPostApplicationId", nominatedPostApplicationId);
	query.setParameter("applicationStatusId", statusId);
	query.setParameter("updatedBy", userId);
	query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
	
	return query.executeUpdate();
}
public List<Object[]> getPositionWiseTotalApplicationsReceived(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType){
	
	StringBuilder str = new StringBuilder();
	
		/*if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" SELECT model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName," +
					" model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
					" count(distinct model.nominatedPostApplicationId) " +
					" FROM NominatedPostFinal model left join model.nominatedPostMember.nominatedPostPosition.position position" +
					" WHERE " +
					" model.isDeleted = 'N' " );
			if(boardLevelId !=null && boardLevelId>0){
					str.append(" AND model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId");		
			}
			if(locationValues !=null && locationValues.size()>0){
				str.append(" AND model.nominatedPostMember.locationValue in (:locationValues)");
			}
		// Any Dept && Board && post Scenarios Consideration && non Consideration
			
			if(deptsIds !=null && deptsIds.size()>0){
				str.append(" AND model.nominatedPostMember.nominatedPostPosition.departments.departmentId in (:deptsIds) ");
			}
			if(boardIds !=null && boardIds.size()>0){
				str.append(" AND model.nominatedPostMember.nominatedPostPosition.board.boardId in (:boardIds) ");
			}
			
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
			
			str.append(" and   model.nominatedPostMember.nominatedPostPosition.position.positionId is not null ");
			
		}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
			str.append(" and  model.nominatedPostMember.nominatedPostPosition.position.positionId is null ");
		}
			if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
			}
			
			str.append(" GROUP BY  model.nominatedPostMember.nominatedPostPosition.position.positionId,model.applicationStatus.applicationStatusId " +
						" ORDER BY  model.nominatedPostMember.nominatedPostPosition.position.positionId");
	}
	else{*/
	str.append(" SELECT position.positionId,position.positionName,model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
			" count(distinct model.nominatedPostApplicationId) " +
			" FROM NominatedPostApplication model " +
			"  left join model.position position " +
			"  left join model.departments department " +
			"  left join model.board board" +
			" WHERE " +
			" model.isDeleted = 'N' and model.isExpired = 'N' " );
	
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
	
	if(deptsIds !=null && deptsIds.size()>0){
		str.append(" AND department.departmentId in (:deptsIds) ");
	}
	if(boardIds !=null && boardIds.size()>0){
		str.append(" AND board.boardId in (:boardIds) ");
	}
	
	
if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
	
	str.append(" and  model.positionId is not null ");
	
}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
	str.append(" and model.positionId is null ");
}
	
	if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet") || statusType.trim().equalsIgnoreCase("running")){
		str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
	}
	else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
		str.append(" AND model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
		//str.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") " );
	}
	
	str.append(" GROUP BY position.positionId,model.applicationStatus.applicationStatusId " +
				" ORDER BY position.positionId");
	
	//}
	Query query = getSession().createQuery(str.toString());
	
	if(boardLevelId !=null && boardLevelId>0){			
			query.setParameter("boardLevelId", boardLevelId);		
	}
	if(locationValues !=null && locationValues.size()>0){
		query.setParameterList("locationValues",locationValues);
	}
	if(deptsIds !=null && deptsIds.size()>0){
		query.setParameterList("deptsIds",deptsIds);
	}
	if(boardIds !=null && boardIds.size()>0){
		query.setParameterList("boardIds",boardIds);
	}
	
	/*if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
		query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
	}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
		query.setParameter("running",Long.valueOf(IConstants.NOMINATED_POST_NOT_RUNNING_STATUS));
	}*/
	
	return query.list();
}

public List<Object[]> getPositionWiseTotalApplicationnsReceived(Long boardLevelId,List<Long> locationValues,List<Long> deptsIds,List<Long> boardIds,String statusType,String positionType){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" SELECT position.positionId,position.positionName,model.applicationStatus.applicationStatusId,model.applicationStatus.status," +
			" count(distinct model.nominatedPostApplicationId) " +
			" FROM NominatedPostApplication model " +
			"  left join model.position position " +
			"  left join model.departments department " +
			"  left join model.board board" +
			" WHERE " +
			" model.isDeleted = 'N' and model.isExpired = 'N' " );
	
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
	
	if(deptsIds !=null && deptsIds.size()>0){
		str.append(" AND department.departmentId in (:deptsIds) ");
	}
	if(boardIds !=null && boardIds.size()>0){
		str.append(" AND board.boardId in (:boardIds) ");
	}
	
	
if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
	
	str.append(" and  model.positionId is not null ");
	
}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
	str.append(" and model.positionId is null ");
}
	
	if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet") ){
		str.append(" AND model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
	}
	
	str.append(" GROUP BY position.positionId,model.applicationStatus.applicationStatusId " +
				" ORDER BY position.positionId");
	
	//}
	Query query = getSession().createQuery(str.toString());
	
	if(boardLevelId !=null && boardLevelId>0){			
			query.setParameter("boardLevelId", boardLevelId);		
	}
	if(locationValues !=null && locationValues.size()>0){
		query.setParameterList("locationValues",locationValues);
	}
	if(deptsIds !=null && deptsIds.size()>0){
		query.setParameterList("deptsIds",deptsIds);
	}
	if(boardIds !=null && boardIds.size()>0){
		query.setParameterList("boardIds",boardIds);
	}
	
	/*if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
		query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
	}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
		query.setParameter("running",Long.valueOf(IConstants.NOMINATED_POST_NOT_RUNNING_STATUS));
	}*/
	
	return query.list();
}
public int updateApllicationStatusToReject(Long memberId,final Long userId){
	
	StringBuilder queryStr = new StringBuilder();
	DateUtilService dateUtilService = new DateUtilService();
	
	queryStr.append("UPDATE NominatedPostApplication model SET model.applicationStatus.applicationStatusId = :applicationStatusId," +
			" model.updatedBy =:updatedBy," +
			" model.updatedTime =:updatedTime " +
			"	WHERE  model.isDeleted = 'N' and model.nominatedPostMember.nominatedPostMemberId =:memberId and " +
			" model.applicationStatus.applicationStatusId not in (5,7)  and model.isExpired = 'N' " );//5--confirmed, 7 -- g.o passed
	
	Query query = getSession().createQuery(queryStr.toString());
	
	query.setParameter("memberId", memberId);
	query.setParameter("applicationStatusId", 4L);// rejected in final review status id
	query.setParameter("updatedBy", userId);
	query.setParameter("updatedTime", dateUtilService.getCurrentDateAndTime());
	
	return query.executeUpdate();
}
public List<NominatedPostApplication> getApplicationIdsByMemberId(Long memberId){
	
	 Query query = getSession().createQuery(" select model from NominatedPostApplication model " +
		 		" where  model.isDeleted='N' " +
		 		" and model.nominatedPostMember.nominatedPostMemberId =:memberId  and model.applicationStatus.applicationStatusId not in (5,7) " +
		 		" and model.isExpired = 'N' ");
		 query.setParameter("memberId", memberId);
		 
		return query.list();
}
public List<Object[]> getApplicationIdsByCAndidateId(Long candidateId){
	
	 Query query = getSession().createQuery(" select model.nominatedPostApplicationId,model.positionId from NominatedPostApplication model " +
		 		" where  model.isDeleted='N' " +
		 		" and model.nominationPostCandidate.nominationPostCandidateId =:candidateId  and model.applicationStatus.applicationStatusId not in (2,4,8) " +
		 		" and model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'  and model.isExpired = 'N' ");
		 query.setParameter("candidateId", candidateId);
		 
		return query.list();
}
public List<Object[]> getAnyPositionDetailsByLevelId(Long boardLevelId){
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append(" select model.board.boardId,model.departments.departmentId,count(model.nominatedPostApplicationId) from " +
	" NominatedPostApplication model where model.boardLevel.boardLevelId = :boardLevelId and model.applicationStatus.applicationStatusId = 1 and " +
	"  model.position.positionId is null and  model.isDeleted = 'N' and model.board.boardId is not null and model.departments.departmentId is not null  and model.isExpired = 'N' group by model.board.boardId,model.departments.departmentId  ");
	
	Query query = getSession().createQuery(queryStr.toString());
	
	 query.setParameter("boardLevelId", boardLevelId);
	 
	return query.list();
	
}
	
	public int updateApplicationExpiredByApplns(List<Long> nominatedPostApplciationIdsLsit, Long userId,Date currentDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("  update NominatedPostApplication model set  model.applicationStatusId = 9, model.isExpired='Y' ,model.updatedTime =:currentDate");
		if(userId != null && userId.longValue()>0L)
			queryStr.append(" , model.updatedBy=:userId ");
		
		queryStr.append("  where model.nominatedPostApplicationId in (:nominatedPostApplciationIdsLsit) and model.isDeleted='N' and model.isExpired='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		if(userId != null && userId.longValue()>0L)
			 query.setParameter("userId", userId);
		 query.setParameterList("nominatedPostApplciationIdsLsit", nominatedPostApplciationIdsLsit);
		 query.setParameter("currentDate", currentDate);
		 int count = query.executeUpdate();
		 return count;
	}
	public Long nominatedPostAppPostionDetails(Long boardLevelId,Long locationValue,Long deptId,Long boardId,Long positionId,Long postTypeId,Long nominationCandidateId,Long statusId ){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.nominatedPostApplicationId from NominatedPostApplication model ");
		queryStr.append(" where " );
			if(boardLevelId != null && boardLevelId.longValue()>0){  
				queryStr.append(" model.boardLevel.boardLevelId =:boardLevelId ");
			}
			if(locationValue != null && locationValue.longValue()>0){
				queryStr.append("  and model.locationValue =:locationValue ");
			}
			if(deptId != null && deptId.longValue()>0){
				queryStr.append(" and model.departments.departmentId =:deptId ");
			}
			if(boardId != null && boardId.longValue()>0){
				queryStr.append(" and model.board.boardId =:boardId ");
			}
			if(positionId != null && positionId.longValue()>0){
				queryStr.append(" and model.position.positionId =:positionId ");
			}
			if(postTypeId != null && postTypeId.longValue()>0){
				queryStr.append(" and model.postType.postTypeId =:postTypeId ");
			}
			if(nominationCandidateId != null && nominationCandidateId.longValue()>0){
				queryStr.append(" and model.nominationPostCandidate.nominationPostCandidateId =:nominationCandidateId ");
			}
			queryStr.append(" and model.applicationStatus.applicationStatusId not in(2,4,8) ");
			queryStr.append(" and model.isDeleted ='N' and  model.isExpired ='N' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(boardLevelId != null && boardLevelId.longValue()>0){  
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(locationValue != null && locationValue.longValue()>0){
			query.setParameter("locationValue", locationValue);
		}
		if(deptId != null && deptId.longValue()>0){
			query.setParameter("deptId", deptId);
		}
		if(boardId != null && boardId.longValue()>0){
			query.setParameter("boardId", boardId);
		}
		if(positionId != null && positionId.longValue()>0){
			query.setParameter("positionId", positionId);
		}
		if(postTypeId != null && postTypeId.longValue()>0){
			query.setParameter("postTypeId", postTypeId);
		}
		if(nominationCandidateId != null && nominationCandidateId.longValue()>0){
			query.setParameter("nominationCandidateId", nominationCandidateId);
		}
		 return (Long) query.uniqueResult();
		
	}
	public List<Object[]> getNominatedPostApplicationStatusWiseCount(Long locationType, List<Long> locationValue, Date startDate,
			Date endDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select " +
		 		   " nominatedPostApplication.applicationStatus.applicationStatusId, " +
		 		   " nominatedPostApplication.applicationStatus.status, " +
		 		   " count(distinct nominatedPostApplication.nominatedPostApplicationId) " +
		 		   " from NominatedPostApplication nominatedPostApplication where " +
				   " nominatedPostApplication.isDeleted = 'N' " +
				   " and nominatedPostApplication.isExpired = 'N' ");
		if (locationType != null && locationValue != null && locationValue.size()>0) {
				if (locationType == 2) {
				//sb.append(" and nominatedPostApplication.address.state.stateId in(:locationValue) ");
				} else if (locationType == 3) {
					sb.append(" and nominatedPostApplication.address.district.districtId in(:locationValue) ");
				} else if (locationType == 10) {
					sb.append(" and nominatedPostApplication.address.parliamentConstituency.constituencyId in(:locationValue) ");
				} else if (locationType == 4) {
					sb.append(" and nominatedPostApplication.address.constituency.constituencyId in(:locationValue) ");
				} else if (locationType == 5) {
					sb.append(" and nominatedPostApplication.address.tehsil.tehsilId in(:locationValue) ");
				}else if (locationType == 6) {
					sb.append(" and nominatedPostApplication.address.panchayat.panchayatId in(:locationValue) ");
				}else if (locationType == 7) {
					sb.append(" and nominatedPostApplication.address.localElectionBody.localElectionBodyId in(:locationValue) ");
				}else if(locationType == 8){
		        	sb.append(" and nominatedPostApplication.address.ward.constituencyId in(:locationValue) ");	        
			      }
		}
		if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPostApplication.updatedTime) between :startDate and :endDate) ");
		}
		 sb.append(" group by nominatedPostApplication.applicationStatus.applicationStatusId ");
		 sb.append(" order by nominatedPostApplication.applicationStatus.applicationStatusId ");
		 Query query = getSession().createQuery(sb.toString());
		
		 if (locationType != null && locationValue != null && locationValue.size() > 0 && locationType != 2) {
			 query.setParameterList("locationValue",locationValue);
		 }
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 return query.list();
	 }
	public List<Object[]> getTotalReceivedApplicationsForLocation(List<Long> boardLevelId,Long levelId,List<Long> levelValues,Date startDate, Date endDate, String year){
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select count(model.nominatedPostApplicationId),model.boardLevelId from NominatedPostApplication model where  model.isDeleted ='N' and  model.isExpired ='N' and " +
	        " model.applicationStatus.applicationStatusId not in(2,4,8) ");
	    
	    if (levelId != null && levelValues != null && levelValues.size()>0) {
	        if (levelId.longValue() == 2L) {
	         // sb.append(" and model.address.state.stateId in(:levelValues) ");
	          //sb.append(" and model.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
	        } else if (levelId.longValue() == 3L) {
	         sb.append(" and model.address.district.districtId in(:levelValues) ");
	       } else if (levelId.longValue() == 10L) {
	         sb.append(" and model.address.constituency.constituencyId in(:levelValues) ");
	       } else if (levelId.longValue() == 4L) {
	         sb.append(" and model.address.constituency.constituencyId in(:levelValues) ");
	       } else if (levelId.longValue() == 5L) {
	         sb.append(" and model.address.tehsil.tehsilId in(:levelValues) ");
	       }else if (levelId.longValue() == 6L) {
	         sb.append(" and model.address.panchayat.panchayatId in(:levelValues) ");
	       }else if (levelId.longValue() == 7L) {
	         sb.append(" and model.address.localElectionBody.localElectionBodyId in(:levelValues) ");
	       }else if (levelId.longValue() == 8L) {
	         sb.append(" and model.address.ward.constituencyId in(:levelValues) ");
	       }    
	       }
	    if(startDate != null && endDate != null){
	 		 sb.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(model.updatedTime) = :year ");   
	 	 }
	    
	        
	       if(boardLevelId != null && boardLevelId.size() > 0L){
	           
	             sb.append(" and model.boardLevelId in (:boardLevelId) ");
	           
	       }
	       sb.append("group by model.boardLevelId");
	       Query query = getSession().createQuery(sb.toString());
	       
	       if(startDate != null && endDate != null){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
			
			if(year !=null && !year.trim().isEmpty()){
		 		query.setParameter("year", Integer.parseInt(year));
		 	 }
	       
	       if(boardLevelId != null && boardLevelId.size() > 0L){
	         query.setParameterList("boardLevelId",boardLevelId );
	       }
	       if(levelId != null && levelId.longValue() > 0l && levelValues != null && levelValues.size() > 0 && levelId.longValue() != 2L){
	         query.setParameterList("levelValues",levelValues );
	       }
	       
	       return query.list();
	  }
	public List<Object[]> getLocationWiseApplicationCount(List<Long> locationValues,Date startDate,Date endDate,Long locationTypeId,Long boardLevelId){
		
		 StringBuilder sb = new StringBuilder();
		    sb.append(" select count(model.nominatedPostApplicationId),model.boardLevelId,model.boardLevel.level " +
		    		" from NominatedPostApplication model " +
		    		" where  model.isDeleted ='N' and  model.isExpired ='N' and  model.nominatedPostMember.isDeleted ='N' ");
		    
		    if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
	 			if (locationTypeId == 2l) {
	 				sb.append(" and model.nominatedPostMember.address.state.stateId in(:locationValue) ");
	 				//sb.append(" and model.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
	 			} else if (locationTypeId == 3l) {
					sb.append(" and model.address.district.districtId in(:locationValue) ");
				} else if (locationTypeId == 10l) {
					sb.append(" and model.address.parliamentConstituency.constituencyId in(:locationValue) ");
				} else if (locationTypeId == 4l) {
					sb.append(" and model.address.constituency.constituencyId in(:locationValue) ");
				} else if (locationTypeId == 5l) {
					sb.append(" and model.address.tehsil.tehsilId in(:locationValue) ");
				}else if (locationTypeId == 6l) {
					sb.append(" and model.address.panchayat.panchayatId in(:locationValue) ");
				}else if (locationTypeId == 7l) {
					sb.append(" and model.address.localElectionBody.localElectionBodyId in(:locationValue) ");
				}else if (locationTypeId == 8l) {
					sb.append(" and model.address.ward.constituencyId in(:locationValue) ");
				}		
				
	        }
		     if(startDate != null && endDate != null){
		    	sb.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
		     }
		     if(boardLevelId != null && boardLevelId.longValue() > 0L){
		             sb.append(" and model.boardLevelId >=:boardLevelId "); 
		      }
		     sb.append(" and model.applicationStatus.applicationStatusId not in(8,2,4) ");
		       sb.append(" group by model.nominatedPostMember.boardLevelId");
		       Query query = getSession().createQuery(sb.toString());
		       if (locationTypeId != null && locationValues != null && locationValues.size()>0 ) {
		          query.setParameterList("locationValue",locationValues);
		       }
		      // query.setParameterList("locationValue",locationValues);
		       if(boardLevelId != null && boardLevelId.longValue() > 0L){
		         query.setParameter("boardLevelId",boardLevelId );
		       }   
		       if(startDate != null && endDate != null){
					 query.setDate("startDate",startDate);
					 query.setDate("endDate",endDate);
				 }
		       return query.list();
	}
	
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getDepartmentWiseApplicationDetails(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,Long deptId){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select ");
	 	 
	 	if(deptId != null && deptId.longValue() >0l){
	 		sb.append("   nominatedPostApplication.nominatedPostMember.nominatedPostPosition.board.boardId," +
	 	 		   " nominatedPostApplication.nominatedPostMember.nominatedPostPosition.board.boardName," );
	 	}else {
	 		sb.append("  nominatedPostApplication.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
	 	 		   " nominatedPostApplication.nominatedPostMember.nominatedPostPosition.departments.deptName," );
	 	}
	 	 		   
	 	sb.append("  nominatedPostApplication.applicationStatus.applicationStatusId,nominatedPostApplication.applicationStatus.status ," +
	 	 		   " count(nominatedPostApplication.nominatedPostApplicationId) " +
	 	 		   " from NominatedPostApplication nominatedPostApplication " +
	 	 		   " where  nominatedPostApplication.isDeleted = 'N' " +
	 			   " and nominatedPostApplication.isExpired = 'N' " +
	 			   " and nominatedPostApplication.nominatedPostMember.isDeleted = 'N' ");
	 	 
	 	if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
 			if (locationTypeId == 2) {
 				sb.append(" and nominatedPostApplication.nominatedPostMember.address.state.stateId in(:locationValues) ");
 				//sb.append(" and nominatedPostApplication.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
 			} else if (locationTypeId == 3) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.district.districtId in(:locationValues) ");
			} else if (locationTypeId == 10) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 4) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 5) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
			}else if (locationTypeId == 6) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
			}else if (locationTypeId == 7) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");
			}else if (locationTypeId == 8) {
				sb.append(" and nominatedPostApplication.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");
			}		
		}
	 	 
	 	 sb.append(" and nominatedPostApplication.applicationStatus.applicationStatusId not in  (2,4,8,9) ");
	 	if(deptId != null && deptId.longValue() >0l){
	 		 sb.append(" and nominatedPostApplication.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId  ");
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPostApplication.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(nominatedPostApplication.updatedTime) = :year ");   
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
     	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
     		  sb.append(" and nominatedPostApplication.nominatedPostMember.boardLevelId =:boardLevelId ");
     	   else if(boardLevelId.longValue() ==5L)
     		  sb.append(" and nominatedPostApplication.nominatedPostMember.boardLevelId in (5,6) ");
     	  else if(boardLevelId.longValue() ==7L)
     		  sb.append(" and nominatedPostApplication.nominatedPostMember.boardLevelId in (7,8) ");
	      }
	 	sb.append(" group by ");
	 	if(deptId != null && deptId.longValue() >0l){
	 		sb.append(" nominatedPostApplication.nominatedPostMember.nominatedPostPosition.board.boardId ,");
	 	}else{
	 		sb.append(" nominatedPostApplication.nominatedPostMember.nominatedPostPosition.departments.departmentId ,");
	 	}
	 	 sb.append(" nominatedPostApplication.applicationStatus.applicationStatusId ");
	 	 sb.append(" order by nominatedPostApplication.nominatedPostMember.nominatedPostPosition.departments.deptName ");
	 	 
	 	 Query query = getSession().createQuery(sb.toString());
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 ){
	 		  query.setParameterList("locationValues", locationValues);
	 	  }
	 	
	 	 if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L && boardLevelId.longValue() > 0l)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	if(deptId != null && deptId.longValue() >0l)
	 		query.setParameter("deptId", deptId);
	 	 return query.list();
	  }


	}
