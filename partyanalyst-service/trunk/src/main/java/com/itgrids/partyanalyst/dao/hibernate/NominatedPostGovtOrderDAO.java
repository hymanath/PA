package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

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
		if(userId != null && userId.longValue()>0L)
			queryStr.append(" ,model.updatedBy=:userId ");
		queryStr.append(" where model.isDeleted='N' and model.nominatedPostId in (:nominatedPostIdsLsist) and model.isExpired='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("currentDate", currentDate);
		query.setParameterList("nominatedPostIdsLsist", nominatedPostIdsLsist);
		if(userId != null && userId.longValue()>0L)
			query.setParameter("userId", userId);
		
		return query.executeUpdate();
	}
	public List<Long> getNominatedPostGovtOrderByPostId(Long postId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.nominatedPostGovtOrderId from  NominatedPostGovtOrder model where model.nominatedPost.nominatedPostId = :postId and " +
				" model.isDeleted = 'N' and model.isExpired = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(postId != null && postId.longValue() > 0l){
	    	query.setParameter("postId", postId);
	    }
		
		return query.list();
	}
	public List<Object[]> getLevelWiseGoIssuedPostions(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,List<Long> statusIds){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select" +
	 	 		   " nominationPostCandidate.nominationPostCandidateId," +//0
	 	 		   " nominationPostCandidate.candidateName," +//1
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +//2
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," +//3
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +//4
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," +//5
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId," +//6
	 	 		   " model.nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionName," +//7
	 	 		   " model.nominatedPost.nominationPostCandidate.gender, " +//8
	 	 		   " casteCategory.categoryName," +//9
	 	 		   " date(model.govtOrder.toDate)," +//10
	 	 		   " tdpCadre.image " +//11
	 	 		   " from NominatedPostGovtOrder model " +
	 	 		   " left join model.nominatedPost.nominationPostCandidate nominationPostCandidate" +
	 	 		   " left join nominationPostCandidate.tdpCadre tdpCadre  " +
	 	 		   " left join nominationPostCandidate.casteState  casteState " +
	 	 		   " left join casteState.casteCategoryGroup casteCategoryGroup " +
	 	 		   " left join casteCategoryGroup.casteCategory casteCategory " +
	 	 		   " where " +
	 			   " model.nominatedPost.isDeleted = 'N' " +
	 			   " and model.nominatedPost.isExpired = 'N' " +
	 			   " and model.nominatedPost.nominatedPostMember.isDeleted = 'N' " +
	 			   " and model.isDeleted = 'N' and model.isExpired = 'N' and model.govtOrder.isDeleted ='N' ");
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 		 	if(locationTypeId == 2l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.state.stateId in(:locationValues) ");
	 	        } else if (locationTypeId == 10) {
					sb.append(" and model.nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
				}else if(locationTypeId == 4l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	sb.append(" and model.nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");	        
	 	    }else if(locationTypeId == 8l){
	         	sb.append(" and model.nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");	        
	       }
	 	        
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(model.nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(model.nominatedPost.updatedTime) = :year ");   
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
    	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
    		  sb.append(" and model.nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
    	   else if(boardLevelId.longValue() ==5L)
    		  sb.append(" and model.nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
    	  else if(boardLevelId.longValue() ==7L)
    		  sb.append(" and model.nominatedPost.nominatedPostMember.boardLevelId in (7,8) ");
	      } 
	 	if(statusIds != null && statusIds.size()>0){
	 		sb.append(" and  model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in(:statusIds) ");
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
	 	if(boardLevelId.longValue() >0l && boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	if(statusIds != null && statusIds.size()>0){
	 		query.setParameterList("statusIds", statusIds);
	 	}
	 	 return query.list();
	  }
	
}
