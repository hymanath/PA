package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.model.NominatedPost;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominatedPostDAO extends GenericDaoHibernate<NominatedPost, Long> implements INominatedPostDAO{

	public NominatedPostDAO() {
		super(NominatedPost.class);
	}

	public List<Object[]> getAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.nominatedPostStatus.status, model.nominatedPostStatusId, " +
				" model.nominatedPostMember.boardLevelId, count(model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(distinct model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model2 where model.nominatedPostMember.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		
		queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' ");		
		if(new CommonMethodsUtilService().isListOrSetValid(statusList))
			queryStr.append(" and model.nominatedPostStatusId in (:statusList) ");			
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		
		if(boardLevelId != null && boardLevelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append(" and  model2.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==36L)
				queryStr.append("  and model2.district.districtId between 1 and 10 ");
		}
		else if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model2.state.stateId=:stateId ");
		
		queryStr.append(" group by model.nominatedPostStatusId,model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
			
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(new CommonMethodsUtilService().isListOrSetValid(statusList))
			query.setParameterList("statusList", statusList);
		if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		return query.list();
	}
	
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select '','', model.nominatedPostMember.boardLevelId, count(distinct model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1L && stateId != null)
			queryStr.append(",UserAddress model2 where model.nominatedPostMember.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' and  model.nominatedPostStatusId = 1 ");		
			
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
		if(boardLevelId != null && boardLevelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and model2.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  model2.district.districtId between 1 and 10 ");
		}
		else if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model2.state.stateId=:stateId ");
		
		queryStr.append(" group by model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
			
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType){
		StringBuilder str = new StringBuilder();
		/*str.append("SELECT position.positionId," +
				" position.positionName " +
				"	FROM NominatedPostApplication model1,NominatedPost model" +
				"  left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" and model.isDeleted ='N'" +
				" and nominatedPostMember.isDeleted = 'N'" +
				" and nominatedPostPosition.isDeleted = 'N' " +
				" and model1.isDeleted = 'N'" +
				" and model1.nominationPostCandidate.isDeleted = 'N' " );*/
		
		str.append("SELECT model.position.positionId," +
				" model.position.positionName " +
				"	FROM NominatedPostApplication model" +
				"   WHERE " +
				"  model.isDeleted ='N'" );
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and model.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and model.locationValue in (:levelValue) ");
		}
		if(deptId !=null && deptId.size() >0){
			str.append(" and model.departments.departmentId in (:deptId) ");
		}
		if(boardId !=null && boardId.size()>0){
			str.append(" and model.board.boardId in (:boardId) ");
		}
		
		str.append(" and  model.positionId is not null ");
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model.applicationStatus.status = :notYet ");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" and model.applicationStatus.status not in (:running) ");
		}
		
		str.append(" GROUP BY model.position.positionId ORDER BY model.position.positionId ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}
		if(deptId !=null && deptId.size() >0){
			query.setParameterList("deptId", deptId);
		}
		if(boardId !=null && boardId.size()>0){
			query.setParameterList("boardId", boardId);
		}
		
		if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
			query.setParameter("running",IConstants.NOMINATED_POST_NOT_RUNNING_STATUS);
		}

		return query.list();
		
	}
	
	public List<Object[]> getNominatedPostsByBoardsAndDeptsForOpen(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT position.positionId," +
				" position.positionName " +
				"	FROM NominatedPost model" +
				"  left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE " +
				"  model.isDeleted ='N'" +
				" and nominatedPostMember.isDeleted = 'N'" +
				" and nominatedPostPosition.isDeleted = 'N' ");
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and nominatedPostMember.locationValue in (:levelValue) ");
		}
		if(deptId !=null && deptId.size() >0){
			str.append(" and nominatedPostPosition.departments.departmentId in (:deptId) ");
		}
		if(boardId !=null && boardId.size()>0){
			str.append(" and nominatedPostPosition.board.boardId in (:boardId) ");
		}
		
		str.append(" and  nominatedPostPosition.positionId is not null ");
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
			str.append("and model.nominatedPostStatus.status = :Open");
		}
		
		Query query = getSession().createQuery(str.toString());
		
		str.append(" GROUP BY nominatedPostPosition.positionId ORDER BY nominatedPostPosition.positionId ");
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}
		if(deptId !=null && deptId.size() >0){
			query.setParameterList("deptId", deptId);
		}
		if(boardId !=null && boardId.size()>0){
			query.setParameterList("boardId", boardId);
		}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open"))
		{
			query.setParameter("Open", statusType);
		}
		
		return query.list();
		
	}
	
	
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType,String positionType){
		
		StringBuilder str = new StringBuilder();
		
		/*str.append("SELECT position.positionId," +
				" position.positionName,model.nominatedPostStatus.nominatedPostStatusId" +
				",model.nominatedPostStatus.status,count(distinct model.nominatedPostId) " +
				"	FROM NominatedPostApplication model1, NominatedPost model left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" and model.isDeleted ='N' " +
				" and nominatedPostMember.isDeleted = 'N'" +
				"  and nominatedPostPosition.isDeleted = 'N' " +
				" and model1.isDeleted = 'N'" +
				" and model1.nominationPostCandidate.isDeleted = 'N' " );*/
		
		str.append("SELECT position.positionId," +
				" position.positionName,model.nominatedPostStatus.nominatedPostStatusId" +
				",model.nominatedPostStatus.status,count(distinct model.nominatedPostId) " +
				"	FROM NominatedPost model left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE  " +
				"  model.isDeleted ='N' " +
				" and nominatedPostMember.isDeleted = 'N'" +
				"  and nominatedPostPosition.isDeleted = 'N' "  );
		
		if(boardLevelId !=null && boardLevelId>0l){
			str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and nominatedPostMember.locationValue in (:levelValue) ");
		}
		
		// Any Dept && Board && post Scenarios Consideration && non Consideration
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
			if(deptId !=null && deptId.size() >0){
				str.append(" and nominatedPostPosition.departments.departmentId in (:deptId) ");
			}
			if(boardId !=null && boardId.size()>0){
				str.append(" and nominatedPostPosition.board.boardId in (:boardId) ");
			}
			
			str.append(" and  nominatedPostPosition.positionId is not null ");
			
		}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
			str.append(" and (nominatedPostPosition.departmentId is null " +
					" or nominatedPostPosition.boardId is null " +
					" or  nominatedPostPosition.positionId is null) ");
		}
		
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				str.append(" and model.nominatedPostStatus.status = :open ");
		}
		
		
		str.append("GROUP BY position.positionId,model.nominatedPostStatus.nominatedPostStatusId " +
				" ORDER BY model.nominatedPostMember.nominatedPostPosition.position.positionId desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
			if(deptId !=null && deptId.size() >0){
				query.setParameterList("deptId", deptId);
			}
			if(boardId !=null && boardId.size()>0){
				query.setParameterList("boardId", boardId);
			}
		}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
			query.setParameter("open",statusType);
		}
		
		return query.list();		
	}
	
	public List<NominatedPost> getNominatedPostDetailsBySearchCriteria(Long departmentId,Long boardId,List<Long> positionIds,Long boardLevelId,List<Long> searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model from NominatedPost model where model.nominatedPostMember.boardLevelId=:boardLevelId and " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId and  " +
				"  model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and  " +
				"  model.nominatedPostMember.nominatedPostPosition.positionId in  (:positionIds) ");
		
		/*if(searchLevelId != null && searchLevelId.longValue()>0L){
			if(searchLevelId.longValue() == 1L || searchLevelId.longValue() == 2L)
				queryStr.append(" and model.nominatedPostMember.locationValue  = :searchLevelValue ");
			else if(searchLevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.district.districtId =:searchLevelValue ");
			else if(searchLevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.constituency.constituencyId =:searchLevelValue ");
			else if(searchLevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.tehsil.tehsilId =:searchLevelValue ");
			else if(searchLevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId =:searchLevelValue ");
			else if(searchLevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
				queryStr.append(" and model.nominatedPostMember.address.panchayatId =:searchLevelValue ");
		}*/
				
		if(searchLevelValue !=null && searchLevelValue.size()>0){
			queryStr.append(" and model.nominatedPostMember.locationValue in (:searchLevelValue) ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameterList("positionIds", positionIds);
		query.setParameter("boardLevelId", boardLevelId);
		if(searchLevelValue !=null && searchLevelValue.size()>0){
			query.setParameterList("searchLevelValue", searchLevelValue);
		}

		return query.list();
	}
	
	public List<Object[]> getNominatdPostStatusCntByPosition(Long positionId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.nominatedPostStatus.nominatedPostStatusId, model.nominatedPostStatus.status,count(distinct model.nominatedPostId)  from NominatedPost model,NominatedPostApplication model1  " +
				"where model.isDeleted = 'N' and model1.isDeleted = 'N' and model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId ");
		
		
		 if(positionId != null && positionId.longValue() > 0l){
			queryStr.append(" and model1.position.positionId = :positionId ");
		}
		 queryStr.append(" group by model.nominatedPostStatus.nominatedPostStatusId "); 
		Query query = getSession().createQuery(queryStr.toString());
		if(positionId != null && positionId.longValue() > 0l){
			query.setParameter("positionId", positionId);
		}
		
		return query.list();
	}


	   public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.departments.deptName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
		    		       " and model.nominatedPostStatus.nominatedPostStatusId=1 ");
		    
		    if(postType != null && postType.longValue() > 0){
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.postType.postTypeId=:postTypeId ");
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	 queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId");
		    }
		    Query query = getSession().createQuery(queryStr.toString());
		    
		    if(postType != null && postType.longValue() > 0){
		    	query.setParameter("postTypeId", postType);
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	query.setParameter("boardLevelId", boardLevelId);
		    }
		    return query.list();
	   }
	 public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.board.boardId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.board.boardName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N'");
		    
		    if(departmentId != null && departmentId.longValue() > 0){
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId=:departmentId");
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	 queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId");
		    }
		    Query query = getSession().createQuery(queryStr.toString());
		    if(departmentId != null && departmentId.longValue() > 0){
		    	query.setParameter("departmentId", departmentId);
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	query.setParameter("boardLevelId", boardLevelId);
		    }
		    return query.list();
	   }
	 public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		    		        " model.nominatedPostMember.nominatedPostPosition.position.positionName from NominatedPost " +
		    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' ");
		    
		       if(departmentId != null && departmentId.longValue()> 0){
			      queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId =:deapartmentId ");	
			    }
	           if(boardId != null && boardId.longValue()> 0){
			      queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId =:boardId ");	
			    }
	           if(boardLevelId != null && boardLevelId.longValue() > 0){
	  	    	 queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
	  	      }
			    Query query = getSession().createQuery(queryStr.toString());
			    
				 if(departmentId != null && departmentId.longValue()> 0){
						query.setParameter("deapartmentId", departmentId);
				 }
				 if(boardId != null && boardId.longValue()> 0){
						query.setParameter("boardId", boardId); 
				 }
				 if(boardLevelId != null && boardLevelId.longValue() > 0){
				    	query.setParameter("boardLevelId", boardLevelId);
				  }
		    return query.list();
	 }
	 
	 public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType){
		 
		 StringBuilder str = new StringBuilder();
		 
		 str.append(" SELECT model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		 		"	model.nominatedPostMember.nominatedPostPosition.departments.deptName," +
					" model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName " +
					" FROM NominatedPost model " +
					" WHERE model.isDeleted = 'N'" +
					" and model.nominatedPostMember.isDeleted = 'N' " +
					" and model.nominatedPostMember.nominatedPostPosition.isDeleted ='N'"  );
			if(boardLevelId !=null && boardLevelId>0){
				str.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
			}
			if(levelValue !=null && levelValue.size()>0){
				str.append(" and model.nominatedPostMember.locationValue in (:levelValue) ");
			}
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("open")){
				str.append(" and model.nominatedPostStatus.status = :open ");
			}
			
			str.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
					" model.nominatedPostMember.nominatedPostPosition.board.boardId  ");
			str.append(" order by model.nominatedPostMember.nominatedPostPosition.departments.deptName ");
			Query query = getSession().createQuery(str.toString());
			
			if(boardLevelId !=null && boardLevelId>0){
				query.setParameter("boardLevelId", boardLevelId);
			}
			if(levelValue !=null && levelValue.size()>0){
				query.setParameterList("levelValue", levelValue);
			}
			if(statusType !=null && statusType.trim().equalsIgnoreCase("open")){
				query.setParameter("open",statusType);
			}
			
			return query.list();
		 
	 }
	 
}
