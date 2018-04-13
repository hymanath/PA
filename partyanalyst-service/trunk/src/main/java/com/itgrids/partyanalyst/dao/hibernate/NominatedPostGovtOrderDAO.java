package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class NominatedPostGovtOrderDAO extends GenericDaoHibernate<NominatedPostGovtOrder, Long> implements INominatedPostGovtOrderDAO{

	public NominatedPostGovtOrderDAO() {
		super(NominatedPostGovtOrder.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> gettingGoPassedDates(Long nominatedPostId ){
		Query query = getSession().createQuery("select distinct model.nominatedPost.nominatedPostId,date(model.govtOrder.fromDate)," +
				                               " date(model.govtOrder.toDate),model.govtOrder.govtOrderId " +
				   							   " from NominatedPostGovtOrder model " +
				   							   " where  model.nominatedPost.nominatedPostId =:nominatedPostId and " +
				   							   " model.isDeleted = 'N' and model.govtOrder.isDeleted = 'N' ");
		query.setParameter("nominatedPostId", nominatedPostId);
		return query.list();
	}


	private static final String NOMINATED_POST_ID =" model.nominatedPostId ";
	public List<Long> getExpiredNominatedPostIdsLsit(Date currentDate){
		Query query = getSession().createQuery(" select distinct "+NOMINATED_POST_ID+" from NominatedPostGovtOrder model where date(model.govtOrder.toDate) <:currentDate " +
				" and model.isDeleted='N' and model.govtOrder.isDeleted='N' and model.isExpired='N' ");
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	public int updateApplicationExpiredByPostIds(List<Long> nominatedPostIdsLsist, Date currentDate,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("update NominatedPostGovtOrder model set model.isExpired='Y',model.updatedTime=:currentDate ");
		//if(userId != null && userId.longValue()>0L)
			queryStr.append(" ,model.updatedBy=:userId ");
		queryStr.append(" where model.isDeleted='N' and model.nominatedPostId in (:nominatedPostIdsLsist) and model.isExpired='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("currentDate", currentDate);
		query.setParameterList("nominatedPostIdsLsist", nominatedPostIdsLsist);
		//if(userId != null && userId.longValue()>0L)
			query.setParameter("userId", userId);
		
		return query.executeUpdate();
	}
	public List<Long> getNominatedPostGovtOrderByPostId(Long postId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.nominatedPostGovtOrderId from  NominatedPostGovtOrder model where  " +
				" model.isDeleted = 'N' and model.isExpired = 'N' ");
		if(postId != null && postId.longValue() > 0l){
			queryStr.append(" and  model.nominatedPost.nominatedPostId = :postId  ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		
		if(postId != null && postId.longValue() > 0l){
	    	query.setParameter("postId", postId);
	    }
		
		return query.list();
	}
	public List<Object[]> getLevelWiseGoIssuedPostions(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId
			,List<Long> statusIds,Long startIndex,Long endIndex,Long positionId){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select" +
	 	 		   " nominationPostCandidate.nominationPostCandidateId," +//0
	 	 		   " nominationPostCandidate.candidateName," +//1
	 	 		   " model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +//2
	 	 		   " model.nominatedPostMember.nominatedPostPosition.departments.deptName," +//3
	 	 		   " model.nominatedPostMember.nominatedPostPosition.board.boardId," +//4
	 	 		   " model.nominatedPostMember.nominatedPostPosition.board.boardName," +//5
	 	 		   " model.nominatedPostMember.nominatedPostPosition.position.positionId," +//6
	 	 		   " model.nominatedPostMember.nominatedPostPosition.position.positionName," +//7
	 	 		   " model.nominationPostCandidate.gender, " +//8
	 	 		   " casteCategory.categoryName," +//9
	 	 		   " tdpCadre.image, " +//10
	 	 		   " model.nominatedPostId "+//11
	 	 		   " from NominatedPost model " +
	 	 		   " left join model.nominationPostCandidate nominationPostCandidate" +
	 	 		   " left join nominationPostCandidate.tdpCadre tdpCadre  " +
	 	 		   " left join nominationPostCandidate.casteState  casteState " +
	 	 		   " left join casteState.casteCategoryGroup casteCategoryGroup " +
	 	 		   " left join casteCategoryGroup.casteCategory casteCategory " +
	 	 		   " where " +
	 			   " model.isDeleted = 'N' " +
	 			   " and model.isExpired = 'N' " +
	 			   " and model.nominatedPostMember.isDeleted = 'N'" +
	 			   " and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N'  ");
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 		 	/*if(locationTypeId == 2l){
	 	        	sb.append(" and model.nominatedPostMember.address.state.stateId in(:locationValues) ");
	 	        } else*/ if (locationTypeId == 10) {
					sb.append(" and model.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
				}else if(locationTypeId == 4l){
	 	        	sb.append(" and model.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	sb.append(" and model.nominatedPostMember.address.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	sb.append(" and model.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	sb.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");	        
	 	    }else if(locationTypeId == 8l){
	         	sb.append(" and model.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");	        
	       }
	 	}
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(model.updatedTime) = :year ");   
	 	 }
	 	 
	 	if(positionId != null && positionId.longValue() > 0L){
	 		sb.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId ");
	 	}
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
    	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
    		  sb.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
    	   else if(boardLevelId.longValue() ==5L)
    		  sb.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
    	  else if(boardLevelId.longValue() ==7L)
    		  sb.append(" and model.nominatedPostMember.boardLevelId in (7,8) ");
	      } 
	 	if(statusIds != null && statusIds.size()>0){
	 		sb.append(" and  model.nominatedPostStatus.nominatedPostStatusId in(:statusIds) ");
	 	}
	 	 Query query = getSession().createQuery(sb.toString());
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 		 	/*if(locationTypeId == 2l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else*/ if(locationTypeId == 4l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 3l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 5l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 6l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 7l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 8l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }
	 	        
	 	    }
	 	 if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	 if( startIndex != null &&  endIndex != null){
	 		query.setFirstResult(startIndex.intValue()) ;
	 		query.setMaxResults(endIndex.intValue()) ;
	 	 }
	 	if(boardLevelId.longValue() >0l && boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	if(statusIds != null && statusIds.size()>0){
	 		query.setParameterList("statusIds", statusIds);
	 	}
	 	if(positionId != null && positionId.longValue() > 0L){
	 		query.setParameter("positionId", positionId);
	 	}
	 	 return query.list();
	  }
	
	public List<Object[]> getLevelWiseGoIssuedDate(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId
			,String year,Long boardLevelId,List<Long> statusIds,Set<Long> nominatedPostIds,Long positionId){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select" +
	 	 		   "  date(model.govtOrder.toDate)," +//0
	 	 		   " model.nominatedPost.nominatedPostId " +//1
	 	 		   " from NominatedPostGovtOrder model " +
	 	 		   " left join model.nominatedPost nominatedPost  " +
	 	 		   "  where " +
	 			   " nominatedPost.isDeleted = 'N' " +
	 			   " and nominatedPost.isExpired = 'N' " +
	 			   " and nominatedPost.nominatedPostMember.isDeleted = 'N' " +
	 			   " and model.isDeleted = 'N' and model.isExpired = 'N' and model.govtOrder.isDeleted ='N'" +
	 			   " and nominatedPost.nominatedPostMember.nominatedPostPosition.isDeleted ='N'  ");
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 		 	if(locationTypeId == 2l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValues) ");
	 	        } else if (locationTypeId == 10) {
					sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
				}else if(locationTypeId == 4l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");	        
	 	    }else if(locationTypeId == 8l){
	         	sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");	        
	       }
	 	        
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(nominatedPost.updatedTime) = :year ");   
	 	 }
	 	 
	 	 if(nominatedPostIds != null && nominatedPostIds.size() > 0){
	 		sb.append(" and nominatedPost.nominatedPostId  in (:nominatedPostIds) ");
	 	 }
	 	 if(positionId != null && positionId.longValue()>0l){
	 		sb.append(" and nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId =:positionId ");
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
   	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
   		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
   	   else if(boardLevelId.longValue() ==5L)
   		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
   	  else if(boardLevelId.longValue() ==7L)
   		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId in (7,8) ");
	      } 
	 	if(statusIds != null && statusIds.size()>0){
	 		sb.append(" and  nominatedPost.nominatedPostStatus.nominatedPostStatusId in(:statusIds) ");
	 	}
	 	 Query query = getSession().createQuery(sb.toString());
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 		 	if(locationTypeId == 2l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 4l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 3l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 5l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 6l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 7l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 8l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }
	 	        
	 	    }
	 	 if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	if(nominatedPostIds != null && nominatedPostIds.size() > 0){
	 		query.setParameterList("nominatedPostIds", nominatedPostIds);
	 	}
	 	if(boardLevelId.longValue() >0l && boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	if(statusIds != null && statusIds.size()>0){
	 		query.setParameterList("statusIds", statusIds);
	 	}
	 	 if(positionId != null && positionId.longValue()>0l){
	 		query.setParameter("positionId", positionId);
	 	 }
	 	 return query.list();
	  }
	public List<Object[]> getNominatedPostExpireDetails(List<Long> locationValues, Date startDate, Date endDate,Long locationTypeId, String year, Long boardLevelId,Long deptId){
		StringBuilder sb = new StringBuilder();
	 	 sb.append(" select ");
	 	if(deptId != null && deptId.longValue() >0l){
	 		sb.append("  nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +
	 	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," );
	 	}else {
	 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		 	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," );
	 	}
	 	sb.append(" date(nominatedPostGovt.govtOrder.toDate) " + 		   
	 	//sb.append(" nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPostGovt.nominatedPost.nominatedPostStatus.status" +
	 	 		   " ,count(nominatedPostGovt.nominatedPost.nominatedPostId) " +
	 	 		   " from NominatedPostGovtOrder nominatedPostGovt  " +
	 	 		   " where nominatedPostGovt.nominatedPost.isDeleted = 'N' " +
	 			   " and nominatedPostGovt.nominatedPost.isExpired = 'N' " +
	 			   " and nominatedPostGovt.nominatedPost.nominatedPostMember.isDeleted = 'N' and nominatedPostGovt.isDeleted = 'N' " +
	 			   " and nominatedPostGovt.isExpired = 'N' and nominatedPostGovt.govtOrder.isDeleted = 'N' ");
	 	 
	 	if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
			/*if (locationTypeId == 2) {
				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValues) ");
				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			} else*/ if (locationTypeId == 3l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.district.districtId in(:locationValues) ");
			} else if (locationTypeId == 4l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 5l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 6l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
			}else if (locationTypeId == 8l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostnominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
			}else if (locationTypeId == 7l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");
			}else if (locationTypeId == 9l) {
				sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");
			}		
		}
	 	
	 	if(deptId != null && deptId.longValue() >0l){
	 		 sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId  ");
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPostGovt.nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(nominatedPostGovt.nominatedPost.updatedTime) = :year ");   
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
    	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
    		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
    	   else if(boardLevelId.longValue() ==5L)
    		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
    	  else if(boardLevelId.longValue() ==7L)
    		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId in (7,8) ");
	      }
	 	sb.append(" group by nominatedPostGovt.nominatedPost.nominatedPostId ");
	 	/*if(deptId != null && deptId.longValue() >0l){
	 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId ,");
	 	}else{
	 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId ,");
	 	}
	 	 sb.append(" nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
	 	 sb.append(" order by nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName ");*/
	 	 
	 	 Query query = getSession().createQuery(sb.toString());
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 && locationTypeId != 2){
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
	
	public List<Object[]> getNominatedPostStateWiseExpireDetails(Date startDate, Date endDate){
		StringBuilder sb = new StringBuilder();
	 	sb.append(" select ");
	 	sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.address.state.stateId," +
		 	 	  " nominatedPost.nominatedPostMember.address.state.stateName," );
	 	sb.append(" date(nominatedPostGovt.govtOrder.toDate) " + 		   
	 	 		   " ,count(nominatedPostGovt.nominatedPost.nominatedPostId) " +
	 	 		   " from NominatedPostGovtOrder nominatedPostGovt  " +
	 	 		   " where nominatedPostGovt.nominatedPost.isDeleted = 'N' " +
	 			   " and nominatedPostGovt.nominatedPost.isExpired = 'N' " +
	 			   " and nominatedPostGovt.nominatedPost.nominatedPostMember.isDeleted = 'N' and nominatedPostGovt.isDeleted = 'N' " +
	 			   " and nominatedPostGovt.isExpired = 'N' and nominatedPostGovt.govtOrder.isDeleted = 'N' ");
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPostGovt.nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 
	 	sb.append(" group by ");
	 	
	 	 sb.append(" nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPostGovt.nominatedPost.nominatedPostMember.address.state.stateId ");
	 	 sb.append(" order by nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
	 	 
	 	 Query query = getSession().createQuery(sb.toString());
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	 return query.list();
		
	}
	//delete
public int updateApplicationExpiredByPostIdsList(List<Long> nominatedPostIdsLsist,Long reasonId,String remark, Date currentDate,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("update NominatedPostGovtOrder model set model.isExpired='Y',model.cadreDeletedReasonId =:reasonId,model.deletedRemarks =:remark, model.updatedTime=:currentDate ");
		//if(userId != null && userId.longValue()>0L){
			queryStr.append(" ,model.updatedBy=:userId ");
		//}
		queryStr.append(" where ");
		//if(nominatedPostIdsLsist != null && nominatedPostIdsLsist.size() >0){
			queryStr.append("  model.nominatedPostId in (:nominatedPostIdsLsist) ");
		//}
		
		queryStr.append(" and model.isDeleted='N' and model.isExpired='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		//if(nominatedPostIdsLsist != null && nominatedPostIdsLsist.size() >0){
			query.setParameterList("nominatedPostIdsLsist", nominatedPostIdsLsist);
		//}
		//if(userId != null && userId.longValue()>0L){
			query.setParameter("userId", userId);
		//}
				 
		 //if(reasonId != null && reasonId.longValue() >0l){
			 query.setParameter("reasonId", reasonId);
		 //}
		// if(remark != null && remark.length() >0){
			 query.setParameter("remark", remark);
		// }
		//if(currentDate != null){
		 query.setDate("currentDate", currentDate);
		//}
		 
		return query.executeUpdate();
	}

public List<Object[]> getNominatedPostExpireMembersDetails(List<Long> locationValues, Date startDate, Date endDate,Long locationTypeId, String year, Long boardLevelId,Long deptId){
	StringBuilder sb = new StringBuilder();
 	 sb.append(" select ");
 	/*if(deptId != null && deptId.longValue() >0l){
 		sb.append("  nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +
 	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," );
 	}else {
 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
	 	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," );
 	}*/
 	 
 	sb.append(" nominationPostCandidate.nominationPostCandidateId," +//0
 			" nominationPostCandidate.candidateName," +//1
 			"nominationPostCandidate.mobileNo," +//2
 			"nominatedPostGovt.nominatedPost.nominatedPostId," +//3
 			"nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId," +//4
	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionName," );//5
 	sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +//6
	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," );//7
 	sb.append("  nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +//8
	 		   " nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," );//9
 	sb.append(" date(nominatedPostGovt.govtOrder.toDate), " + 	//10
 			 " nominationPostCandidate.gender, " +//11
	 		   " casteCategory.categoryName," +//12
	 		   " tdpCadre.image " +//13
 	//sb.append(" nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPostGovt.nominatedPost.nominatedPostStatus.status" +
 	 		  // " ,count(nominatedPostGovt.nominatedPost.nominatedPostId) " +
 	 		   " from NominatedPostGovtOrder nominatedPostGovt " +
 	 		   " left join nominatedPostGovt.nominationPostCandidate nominationPostCandidate " +
 	 		   " left join nominationPostCandidate.tdpCadre tdpCadre  " +
	 		   " left join nominationPostCandidate.casteState  casteState " +
	 		   " left join casteState.casteCategoryGroup casteCategoryGroup " +
	 		   " left join casteCategoryGroup.casteCategory casteCategory " +
 	 		   " where nominatedPostGovt.nominatedPost.isDeleted = 'N' " +
 			   " and nominatedPostGovt.nominatedPost.isExpired = 'N' " +
 			   " and nominatedPostGovt.nominatedPost.nominatedPostMember.isDeleted = 'N' and nominatedPostGovt.isDeleted = 'N' " +
 			   " and nominatedPostGovt.isExpired = 'N' and nominatedPostGovt.govtOrder.isDeleted = 'N'" +
 			   " and nominationPostCandidate.isDeleted = 'N' ");
 	 
 	if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
		/*if (locationTypeId == 2) {
			//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValues) ");
			//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		} else*/ if (locationTypeId == 3l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.district.districtId in(:locationValues) ");
		} else if (locationTypeId == 4l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValues) ");
		} else if (locationTypeId == 5l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
		} else if (locationTypeId == 6l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
		}else if (locationTypeId == 8l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostnominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
		}else if (locationTypeId == 7l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");
		}else if (locationTypeId == 9l) {
			sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");
		}		
	}
 	
 	if(deptId != null && deptId.longValue() >0l){
 		 sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId  ");
 	 }
 	 if(startDate != null && endDate != null){
 		 sb.append(" and (date(nominatedPostGovt.nominatedPost.updatedTime) between :startDate and :endDate) ");
 	 }
 	 if(year != null && !year.trim().isEmpty()){
 		 sb.append(" and year(nominatedPostGovt.nominatedPost.updatedTime) = :year ");   
 	 }
 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
	   else if(boardLevelId.longValue() ==5L)
		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
	  else if(boardLevelId.longValue() ==7L)
		  sb.append(" and nominatedPostGovt.nominatedPost.nominatedPostMember.boardLevelId in (7,8) ");
      }
 	/*sb.append(" group by ");
 	if(deptId != null && deptId.longValue() >0l){
 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId ,");
 	}else{
 		sb.append(" nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId ,");
 	}
 	 sb.append(" nominatedPostGovt.nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
 	 sb.append(" order by nominatedPostGovt.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName ");*/
 	 
 	 Query query = getSession().createQuery(sb.toString());
 	 
 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0 && locationTypeId != 2){
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
