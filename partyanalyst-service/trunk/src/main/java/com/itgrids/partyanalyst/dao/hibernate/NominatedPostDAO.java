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
				" model.nominatedPostMember.boardLevelId, count(distinct model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(distinct model.nominatedPostMember.nominatedPostMemberId), " +
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
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			if(boardLevelId.longValue() !=5L)
				queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		
		if(boardLevelId != null && boardLevelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model2.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model2.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model2.state.stateId=:stateId ");
		
		queryStr.append(" group by model.nominatedPostStatusId,model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() !=5L)
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
	
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,Long stateId,String statustype){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select '','', model.nominatedPostMember.boardLevelId, count(distinct model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(distinct model.nominatedPostMember.nominatedPostMemberId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1L && stateId != null)
			queryStr.append(",UserAddress model2 where model.nominatedPostMember.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
			//	" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' and  model.nominatedPostStatusId = 1 "); // for open status	
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' and ");	// for total 
		if(statustype != null && !statustype.trim().isEmpty() && statustype.trim().equalsIgnoreCase("Open")){
			queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and  model.nominatedPostMember.isDeleted ='N' and " +
					" model.isDeleted='N' and  model.nominatedPostStatusId = 1 "); // for open status	
		}else if(statustype != null && !statustype.trim().isEmpty() && statustype.trim().equalsIgnoreCase("Total")){
				queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and  model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N'  "); // for open status	
		}
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		}
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
		if(boardLevelId != null && boardLevelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model2.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model2.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model2.state.stateId=:stateId ");
		
		queryStr.append(" group by model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() != 5L)
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
		if(statusType != null && statusType.equalsIgnoreCase("notRecieved")){
			str.append("SELECT model.nominatedPostMember.nominatedPostPosition.position.positionId," +
					" model.nominatedPostMember.nominatedPostPosition.position.positionName " +
					"	, count(model.nominatedPostId) FROM NominatedPost model" +
					"   WHERE " +
					"  model.isDeleted ='N'" );
			
			if(boardLevelId !=null && boardLevelId>0){
				//if(boardLevelId.longValue() !=5L)
					str.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
				//else
					//str.append(" and model.boardLevelId in (5,6) ");
			}
			if(levelValue !=null && levelValue.size()>0){
				str.append(" and model.nominatedPostMember.locationValue in (:levelValue) ");
			}
			if(deptId !=null && deptId.size() >0){
				str.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId in (:deptId) ");
			}
			if(boardId !=null && boardId.size()>0){
				str.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId in (:boardId) ");
			}
			
			str.append(" and  model.nominatedPostMember.nominatedPostPosition.position.positionId is not null ");
			str.append(" and model.nominatedPostStatusId = 1 ");
			str.append(" GROUP BY model.nominatedPostMember.nominatedPostPosition.position.positionId" +
					"  ORDER BY model.nominatedPostMember.nominatedPostPosition.position.positionId ");
			
		}else{
			str.append("SELECT model.position.positionId," +
					" model.position.positionName " +
					"	FROM NominatedPostApplication model" +
					"   WHERE " +
					"  model.isDeleted ='N'" );
			
			if(boardLevelId !=null && boardLevelId>0){
				//if(boardLevelId.longValue() !=5L)
					str.append(" and model.boardLevelId =:boardLevelId ");
				//else
					//str.append(" and model.boardLevelId in (5,6) ");
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
			
			/*if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status = :notYet ");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and model.applicationStatus.applicationStatusId not in (:running) ");
			}*/
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
			}
			
			str.append(" GROUP BY model.position.positionId ORDER BY model.position.positionId ");
		}
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
		
		/*if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
			query.setParameter("running",IConstants.NOMINATED_POST_NOT_RUNNING_STATUS);
		}
*/
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
			//if(boardLevelId.longValue() !=5L)
				str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
			//else 
			//	str.append(" and nominatedPostMember.boardLevelId in (5,6) ");
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
			//if(boardLevelId.longValue() !=5L)
				str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
			//else
			//	str.append(" and nominatedPostMember.boardLevelId in (5,6) ");
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
		
		// Any Dept && Board && post Scenarios Consideration && non Consideration
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){						
			str.append(" and  nominatedPostPosition.positionId is not null ");
			
		}else if(positionType !=null && positionType.trim().equalsIgnoreCase("anyPost")){
			str.append(" and  nominatedPostPosition.positionId is null) ");
		}
		
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				str.append(" and model.nominatedPostStatus.status = :open ");
		}
		
		
		str.append("GROUP BY position.positionId,model.nominatedPostStatus.nominatedPostStatusId " +
				" ORDER BY model.nominatedPostMember.nominatedPostPosition.position.positionId desc ");
		
		Query query = getSession().createQuery(str.toString());
		
		//if(boardLevelId !=null && boardLevelId>0 && boardLevelId.longValue() !=5L){
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
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
			query.setParameter("open",statusType);
		}
		
		return query.list();		
	}
	
	public List<NominatedPost> getNominatedPostDetailsBySearchCriteria(Long departmentId,Long boardId,List<Long> positionIds,Long boardLevelId,List<Long> searchLevelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model from NominatedPost model where  " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId and  " +
				"  model.nominatedPostMember.nominatedPostPosition.boardId = :boardId and  " +
				"  model.nominatedPostMember.nominatedPostPosition.positionId in  (:positionIds) " +
				" and model.nominatedPostStatus.nominatedPostStatusId=1 and model.nominationPostCandidateId is null  ");
		if(boardLevelId != null && boardLevelId.longValue() >0L){
			//if(boardLevelId.longValue() !=5L)
				queryStr.append(" and model.nominatedPostMember.boardLevelId=:boardLevelId ");
			//else
			//	queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		}
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
		if(boardLevelId != null && boardLevelId.longValue() >0L)
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


	   public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId,Long searchLevelValue,Long searchlevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.departments.deptName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
		    		       " and model.nominatedPostStatus.nominatedPostStatusId=1 and model.nominationPostCandidateId is null ");
		   
		    if(postType != null && postType.longValue() > 0)
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.postType.postTypeId=:postTypeId ");
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	
		    	if(boardLevelId.longValue() !=5L)
		    	 queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
		    	else
		    		 queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
		    	//if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
				//    queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
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
		    else
		    	 queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
		    
		    Query query = getSession().createQuery(queryStr.toString() +" order by model.nominatedPostMember.nominatedPostPosition.departments.deptName asc ");
		    
		    if(postType != null && postType.longValue() > 0L){
		    	query.setParameter("postTypeId", postType);
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0L && boardLevelId.longValue() !=5L){
		    	query.setParameter("boardLevelId", boardLevelId);
		    }
		    if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
		    	query.setParameter("searchLevelValue", searchLevelValue);
		    
		    return query.list();
	   }
	 public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId,Long searchLevelValue,Long searchlevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.board.boardId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.board.boardName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
		    		       "  and model.nominatedPostStatus.nominatedPostStatusId=1 and model.nominationPostCandidateId is null  ");
		    
		    if(departmentId != null && departmentId.longValue() > 0){
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId=:departmentId");
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	if(boardLevelId.longValue() !=5L)
		    		queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId");
		    	else
		    		 queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,7)");
		    	//if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
				//    queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
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
		    else
		    	 queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
		    
		    
		    
		    
		    Query query = getSession().createQuery(queryStr.toString()+" order by model.nominatedPostMember.nominatedPostPosition.board.boardName asc ");
		    if(departmentId != null && departmentId.longValue() > 0L){
		    	query.setParameter("departmentId", departmentId);
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0L && boardLevelId.longValue() !=5L){
		    	query.setParameter("boardLevelId", boardLevelId);
		    }
		    if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
		    	query.setParameter("searchLevelValue", searchLevelValue);
		    
		    return query.list();
	   }
	 public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		    		        " model.nominatedPostMember.nominatedPostPosition.position.positionName from NominatedPost " +
		    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  " +
		    		        "  and model.nominatedPostStatus.nominatedPostStatusId=1 and model.nominationPostCandidateId is null  "); 
		    
		       if(departmentId != null && departmentId.longValue()> 0L){
			      queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId =:deapartmentId ");	
			    }
	           if(boardId != null && boardId.longValue()> 0){
			      queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId =:boardId ");	
			    }
	           if(boardLevelId != null && boardLevelId.longValue() > 0L){
	        	   if(boardLevelId.longValue() !=5L)
	        		   queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
	        	   else
	        		   queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
	        	   //if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
					//   queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
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
			    else
			    	 queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
	           
	           
	           
			    Query query = getSession().createQuery(queryStr.toString()+" order by model.nominatedPostMember.nominatedPostPosition.position.positionName asc ");
			    
				 if(departmentId != null && departmentId.longValue()> 0L){
						query.setParameter("deapartmentId", departmentId);
				 }
				 if(boardId != null && boardId.longValue()> 0L){
						query.setParameter("boardId", boardId); 
				 }
				 if(boardLevelId != null && boardLevelId.longValue() > 0L && boardLevelId.longValue() !=5L){
				    	query.setParameter("boardLevelId", boardLevelId);
				  }
				 if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
				    	query.setParameter("searchLevelValue", searchLevelValue);
		    return query.list();
	 }
	 
	 public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType,Long searchlevelId,Long searchLevelValue){
		 
		 StringBuilder str = new StringBuilder();
		 
		 str.append(" SELECT distinct model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		 		"	model.nominatedPostMember.nominatedPostPosition.departments.deptName," +
					" model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName " +
					" FROM NominatedPost model " +
					" WHERE model.isDeleted = 'N'" +
					" and model.nominatedPostMember.isDeleted = 'N' " +
					" and model.nominatedPostMember.nominatedPostPosition.isDeleted ='N'"  );
			if(boardLevelId !=null && boardLevelId>0){
				if(levelValue == null){
					if(boardLevelId.longValue() !=5L)
						str.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
					else
						str.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
				}else{
					str.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
				}
				
			}
			if(levelValue !=null && levelValue.size()>0){
				str.append(" and model.nominatedPostMember.locationValue in (:levelValue) ");
			}
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				str.append(" and model.nominatedPostStatus.status = :Open ");
			}
			if(searchlevelId != null && searchlevelId.longValue()>0L){
				if(searchlevelId.longValue() == 1L)
					str.append(" and model.nominatedPostMember.locationValue  = :searchLevelValue ");
				else if(searchlevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.state.stateId =:searchLevelValue ");
				
				else if(searchlevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.district.districtId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.constituency.constituencyId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.tehsil.tehsilId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.panchayatId =:searchLevelValue ");
			}
			//str.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
			//		" model.nominatedPostMember.nominatedPostPosition.board.boardId  ");
			str.append(" order by model.nominatedPostMember.nominatedPostPosition.departments.deptName ");
			Query query = getSession().createQuery(str.toString());
			
			if(boardLevelId !=null && boardLevelId>0){
				if(levelValue == null){
					if(boardLevelId.longValue() !=5L){
						query.setParameter("boardLevelId", boardLevelId);
					}						
				}else{
						query.setParameter("boardLevelId", boardLevelId);
				}
			}
			if(levelValue !=null && levelValue.size()>0){
				query.setParameterList("levelValue", levelValue);
			}
			if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				query.setParameter("Open",statusType);
			}
			
			if(searchlevelId !=null && searchLevelValue != null && searchLevelValue.longValue()>0L){
				query.setParameter("searchLevelValue",searchLevelValue);
			}
			
			return query.list();
		 
	 }
	 public List<NominatedPost> getNominatedPostDetailsByNominatedPostMember(Long nominatedPostMemberId){
		 Query query = getSession().createQuery("select distinct model " +
		 									" from NominatedPost model" +
		 									" where model.nominatedPostMemberId = :nominatedPostMemberId" +
		 									" and model.nominationPostCandidateId is null" +
		 									" and model.isDeleted = 'N' and model.isExpired = 'N'");
		 query.setParameter("nominatedPostMemberId", nominatedPostMemberId);
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getOpenedPositionsCountByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId,");
		 if(status != null && (status.equalsIgnoreCase("Total") ))
		 			sb.append(" count(distinct model.nominatedPostId)" +
		 			" from NominatedPost model ");
		 else
			 sb.append(" count(distinct model.nominatedPostMemberId)" +
			 			" from NominatedPostApplication model ");
		/* if(status != null && !status.equalsIgnoreCase("Total"))
		 	sb.append(" ,NominatedPostApplication model1 " +
		 			" where model.nominatedPostMemberId = model1.nominatedPostMemberId and " );
		 else*/
			 sb.append(" where ");
		 sb.append("  model.nominatedPostMember.boardLevel.boardLevelId = :boardLevelId" +
		 		//	" and model.nominationPostCandidateId is null" +
		 			" ");
		 
		 sb.append("");
		/* if(status != null && status.equalsIgnoreCase("Total"))
				sb.append(" ");
		 else if(status != null && status.equalsIgnoreCase("Open"))
			sb.append(" and model.nominatedPostStatusId = 1 and model1.applicationStatusId = 1 and model.nominationPostCandidateId is null ");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.nominatedPostStatusId = 1 and model1.applicationStatusId not in (5) and model.nominationPostCandidateId is null");*/
		 
		 if(status != null && status.equalsIgnoreCase("Total"))
				sb.append(" ");
		 else if(status != null && (status.equalsIgnoreCase("Open")))
			sb.append(" and model.nominatedPostStatusId = 1");
		 else if(status != null && (status.equalsIgnoreCase("notYet")))
				sb.append(" and model.applicationStatusId = 1");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")");
		 
		 if(searchLevelId != null && searchLevelId.longValue() > 0l){
			 if(searchLevelId == 1l)
				 sb.append(" and model.nominatedPostMember.address.country.countryId = 1");
			 else if(searchLevelId == 2l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.state.stateId = :searchLevelValue");
			 else if(searchLevelId == 3l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.district.districtId = :searchLevelValue");
			 else if(searchLevelId == 4l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.constituency.constituencyId = :searchLevelValue");
			 else if(searchLevelId == 5l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId = :searchLevelValue");
			 else if(searchLevelId == 6l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId = :searchLevelValue");
			 else if(searchLevelId == 7l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.panchayat.panchayatId = :searchLevelValue");
		 }
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open")))
			 sb.append(" and model.isExpired = 'N'");
		 
		 sb.append(" and model.isDeleted = 'N'" +
		 			" and model.nominatedPostMember.isDeleted = 'N' and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N'" +
		 			" group by model.nominatedPostMember.nominatedPostPosition.departmentId");
		 
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("boardLevelId", boardLevelId);
		 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			 query.setParameter("searchLevelValue", searchLevelValue);
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getOpenedPositionsCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId," +
		 			" model.nominatedPostMember.nominatedPostPosition.boardId," +
		 			" count(model.nominatedPostMemberId)");
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open")))
		 			sb.append(" from NominatedPost model ");
		 else
			 sb.append(" from NominatedPostApplication model");
		/* if(status != null && !status.equalsIgnoreCase("Total"))
			 sb.append(" ,NominatedPostApplication model1" +
		 			" where model.nominatedPostMemberId = model1.nominatedPostMemberId and" );
		 else*/
			 sb.append(" where ");
		 sb.append("  model.nominatedPostMember.boardLevel.boardLevelId = :boardLevelId" );
		 		
		 /*if(status != null && status.equalsIgnoreCase("Total"))
				sb.append(" ");
		 else if(status != null && status.equalsIgnoreCase("Open"))
			sb.append(" and model.nominatedPostStatusId = 1 and model1.applicationStatusId = 1 and model.nominationPostCandidateId is null ");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.nominatedPostStatusId = 1 and model1.applicationStatusId not in (5) and model.nominationPostCandidateId is null ");*/
		 if(status != null && status.equalsIgnoreCase("Total"))
				sb.append(" ");
		 else if(status != null && (status.equalsIgnoreCase("Open")))
				 sb.append(" and model.nominatedPostStatusId = 1");
		 else if(status != null && status.equalsIgnoreCase("notYet"))
			 	 sb.append(" and model.applicationStatusId = 1 ");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")");
		 
		 if(searchLevelId != null && searchLevelId.longValue() > 0l){
			 if(searchLevelId == 1l)
				 sb.append(" and model.nominatedPostMember.address.country.countryId = 1");
			 else if(searchLevelId == 2l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.state.stateId = :searchLevelValue");
			 else if(searchLevelId == 3l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.district.districtId = :searchLevelValue");
			 else if(searchLevelId == 4l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.constituency.constituencyId = :searchLevelValue");
			 else if(searchLevelId == 5l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId = :searchLevelValue");
			 else if(searchLevelId == 6l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId = :searchLevelValue");
			 else if(searchLevelId == 7l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and model.nominatedPostMember.address.panchayat.panchayatId = :searchLevelValue");
		 }
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open")))
			 sb.append(" and model.isExpired = 'N'");
		 
		 sb.append(" and model.isDeleted = 'N'" +
		 			" and model.nominatedPostMember.isDeleted = 'N' and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N'" +
		 			" group by model.nominatedPostMember.nominatedPostPosition.departmentId," +
		 			" model.nominatedPostMember.nominatedPostPosition.boardId");
		 
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("boardLevelId", boardLevelId);
		 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			 query.setParameter("searchLevelValue", searchLevelValue);
		 
		 return query.list();
	 }
	 
	public List<Object[]> getTotalPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId){
		 
		   StringBuilder queryStr = new StringBuilder();
		          
		    queryStr.append("select model.nominatedPostStatus.nominatedPostStatusId,model.nominatedPostStatus.status,count(model.nominatedPostId) from NominatedPost model " +
		    		       " where " +
		    		       " model.isExpired='N' and model.isDeleted='N' ");
		    
           if(positionId != null && positionId.longValue() > 0){
        	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId ");
           }
           if(boardLevelId != null && boardLevelId.longValue() > 0){
        	   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId ");
           }
		  queryStr.append(" group by model.nominatedPostStatus.nominatedPostStatusId ");
		  
		  Query query = getSession().createQuery(queryStr.toString());
		  
		  if(positionId != null && positionId.longValue() > 0){
		   query.setParameter("positionId", positionId);
		   }  
		   if(boardLevelId != null && boardLevelId.longValue() > 0){
			query.setParameter("boardLevelId", boardLevelId);   
		   }
            return query.list();
	 }
  public Long getNoCandiateCntPositionAndLocationWise(Long positionId,Long boardLevelId){
	  
	     StringBuilder queryStr = new StringBuilder();
	    
	      queryStr.append(" select count(distinct model.nominatedPostId) from NominatedPost model " +
	  		             " where " +
	  		             " model.isDeleted='N' and model.isExpired='N' and model.nominationPostCandidateId is null   ");
	  
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
		   
	  return (Long) query.uniqueResult();
  }
	 
  public List<Object[]> getTotalCorpAndBoardsAndPositions(Long boardLevelId, Date startDate,Date endDate,Long stateId){
	  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) , count(model.nominatedPostMember.nominatedPostMemberId) ");
		queryStr.append(" from NominatedPostApplication model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1L && stateId != null)
			queryStr.append(",UserAddress model2 where model.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N'   ");	// for total 
						
		if(boardLevelId != null && boardLevelId.longValue()>0L){
			if(boardLevelId.longValue() != 5L)
				queryStr.append(" and model.boardLevelId = :boardLevelId ");
			else
				queryStr.append(" and model.boardLevelId in (5,6) ");
		}
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
		if(boardLevelId != null && boardLevelId.longValue()>2L && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append("  and (model2.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ) ");
			else if(stateId.longValue() ==36L)
				queryStr.append(" and  ( model2.district.districtId  in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )");
		}
		else if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			queryStr.append(" and  model2.state.stateId=:stateId ");
		
		queryStr.append(" order by model.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() != 5L)
			query.setParameter("boardLevelId", boardLevelId);
			
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(boardLevelId != null && boardLevelId.longValue() == 2L && stateId != null)
			query.setParameter("stateId", stateId.longValue() == 1L ? stateId.longValue():36L);
		
		return query.list();
	
		
	  	/*StringBuilder queryStr = new StringBuilder();      
	    queryStr.append(" select count(distinct model.departmentId) ,count(distinct model.boardId),count(distinct model.positionId) from NominatedPostApplication model where ");
	    if(boardLevelId != null && boardLevelId.longValue() > 0)
	    	if(boardLevelId.longValue()>5L)
	    		queryStr.append(" model.boardLevelId =:boardLevelId and ");
	    	else 
	    		queryStr.append(" model.boardLevelId  in (5,6) and ");
	    queryStr.append(" model.isDeleted='N' and  model.isDeleted='N' ");
	    Query query = getSession().createQuery(queryStr.toString());	 
	    if(boardLevelId != null && boardLevelId.longValue() > 0)
			query.setParameter("boardLevelId", boardLevelId);   
		   */
  }
  

	public List<Object[]> getTotalCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue){
		  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count( model.nominatedPostId), model.nominatedPostMember.nominatedPostMemberId ");
		queryStr.append(" from NominatedPost model   " );
		queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N' and  model.nominatedPostMember.isDeleted ='N' ");	// for total 
						
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
		queryStr.append(" group by  model.nominatedPostMember.nominatedPostMemberId  ");
		queryStr.append(" order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() != 5L)
			query.setParameter("boardLevelId", boardLevelId);
		if(searchLevelValue != null && searchLevelValue.longValue()>0L)
			query.setParameter("searchLevelValue", searchLevelValue);
	
		return query.list();
}
	public List<Object[]> getBoardLevelsForOpenedPositions(){
		Query query = getSession().createQuery("select distinct model.nominatedPostMember.boardLevel.boardLevelId," +
										" model.nominatedPostMember.boardLevel.level" +
										" from NominatedPost model" +
										" where model.nominationPostCandidateId is null" +
										" and model.nominatedPostStatus.nominatedPostStatusId = 1" +  //Only Open Position Levels
										" and model.isDeleted = 'N' and model.isExpired = 'N'" +
										" order by model.nominatedPostMember.boardLevel.boardLevelId");
		return query.list();
	}
	
	public List<Object[]> getStatesForOpenedPositions(Long boardLevelId ){
		Query query = getSession().createQuery("select distinct model.nominatedPostMember.address.state.stateId," +
											" model.nominatedPostMember.address.state.stateName" +
											" from NominatedPost model" +
											" where model.nominationPostCandidateId is null" +
											" and model.nominatedPostStatus.nominatedPostStatusId = 1 and model.nominatedPostMember.boardLevelId=:boardLevelId " +    //Only Open Position Locations.
											" and model.isDeleted = 'N' and model.isExpired = 'N'");
		query.setParameter("boardLevelId", boardLevelId);
		return query.list();
	}
	
	public List<Object[]> getOpenPositionDistrictsForState(Long stateId,Long boardLevelId){
		Query query = getSession().createQuery("select distinct model.nominatedPostMember.address.district.districtId," +
											" model.nominatedPostMember.address.district.districtName" +
											" from NominatedPost model" +
											" where model.nominationPostCandidateId is null" +
											" and model.nominatedPostStatus.nominatedPostStatusId = 1 and model.nominatedPostMember.boardLevelId = :boardLevelId "  +     	//Only Open Position Locations.
											" and model.nominatedPostMember.address.state.stateId = :stateId" +   
											" and model.isDeleted = 'N' and model.isExpired = 'N'" +
											" order by model.nominatedPostMember.address.district.districtName asc ");
		query.setParameter("stateId", stateId);
		query.setParameter("boardLevelId", boardLevelId);
		return query.list();
	}
	
	public List<Object[]> getOpenPositionConstituenciesForDistrict(Long districtId,Long boardLevelId){
		Query query = getSession().createQuery("select distinct model.nominatedPostMember.address.constituency.constituencyId," +
											" model.nominatedPostMember.address.constituency.name" +
											" from NominatedPost model" +
											" where model.nominationPostCandidateId is null and model.nominatedPostMember.boardLevelId = :boardLevelId  " +
											" and model.nominatedPostStatus.nominatedPostStatusId = 1" +     	//Only Open Position Locations.
											" and model.nominatedPostMember.address.district.districtId = :districtId" +   
											" and model.isDeleted = 'N' and model.isExpired = 'N'" +
											" order by model.nominatedPostMember.address.constituency.name");
		query.setParameter("districtId", districtId);
		query.setParameter("boardLevelId", boardLevelId);
		return query.list();
	}
	
	public List<Object[]> getMandalMuncilIdsForConstituency(Long constituencyId,Long boardLevelId){
		Query query = getSession().createQuery("select tehsil.tehsilId," +
											" leb.localElectionBodyId" +
											" from NominatedPost model" +
											" left join model.nominatedPostMember.address.tehsil tehsil" +
											" left join model.nominatedPostMember.address.localElectionBody leb" +
											" where model.nominationPostCandidateId is null and model.nominatedPostMember.boardLevelId = :boardLevelId " +
											" and model.nominatedPostStatus.nominatedPostStatusId = 1" +     	//Only Open Position Locations.
											" and model.nominatedPostMember.address.constituency.constituencyId = :constituencyId" +   
											" and model.isDeleted = 'N' and model.isExpired = 'N'");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("boardLevelId", boardLevelId);
		return query.list();
	}
	
	public List<Object[]> getPanchayWardIdsForMandal(Long id,String type,Long constituencyId,Long boardLevelId){
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		if(type != null && type.equalsIgnoreCase("mandal"))
			sb.append(" model.nominatedPostMember.address.panchayat.panchayatId," +
						" model.nominatedPostMember.address.panchayat.panchayatName");
		else if(type != null && type.equalsIgnoreCase("muncipality"))
			sb.append(" model.nominatedPostMember.address.ward.constituencyId," +
					" model.nominatedPostMember.address.ward.name");
		sb.append(" from NominatedPost model" +
					" where model.nominationPostCandidateId is null" +
					" and model.nominatedPostStatus.nominatedPostStatusId = 1" +
					" and model.nominatedPostMember.address.constituency.constituencyId = :constituencyId" +
					" and model.isDeleted = 'N' and model.isExpired = 'N' and model.nominatedPostMember.boardLevelId = :boardLevelId  ");
		if(type != null && type.equalsIgnoreCase("mandal"))
			sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId = :id");
		else if(type != null && type.equalsIgnoreCase("muncipality"))
			sb.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId = :id");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("boardLevelId", boardLevelId);
		if(type != null)
			query.setParameter("id", id);
		return query.list();
	}
	
	/*public List<Object[]> getDepartmentsForOpenPositions(){
		Query query = getSession().createQuery("select *" +
											" from NominatedPost model" +
											" where model.nominationPostCandidateId is null" +
											" and model.nominatedPostStatus.nominatedPostStatusId = 1" +		//Only Open Position Departments.
											" and model.isDeleted = 'N' and model.isExpired = 'N'");
		return query.list();
	}*/
	public List<Long> getTotalDeptsCount(Long levelId){
		Query query= getSession().createQuery("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId   from NominatedPost model " +
				" where model.isDeleted = 'N' and model.isExpired = 'N' and model.nominatedPostMember.boardLevelId =:levelId   order by   " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId  ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Long> getTotalApplicationsDeptsCount(Long levelId){
		Query query= getSession().createQuery("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId   from NominatedPostApplication model " +
				" where  model.isDeleted = 'N' and model.nominatedPostMember.boardLevelId =:levelId  order by  " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getTotalCorpsIdsCount(Long levelId){
		Query query= getSession().createQuery("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId, " +
				" model.nominatedPostMember.nominatedPostPosition.boardId " +
				"    from NominatedPost model " +
				" where  model.isDeleted = 'N' and model.isExpired = 'N' and model.nominatedPostMember.boardLevelId =:levelId  order by   " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId , model.nominatedPostMember.nominatedPostPosition.boardId ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getTotalApplicationsCorpsIdsCount(Long levelId){
		Query query= getSession().createQuery("select distinct model.nominatedPostMember.nominatedPostPosition.departmentId , " +
				" model.nominatedPostMember.nominatedPostPosition.boardId  from NominatedPostApplication model " +
				" where model.isDeleted = 'N' and model.nominatedPostMember.boardLevelId =:levelId  order by   " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId, model.nominatedPostMember.nominatedPostPosition.boardId ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
   public List<Object[]> getPositionByLevelId(Long boardLevelId){
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append("select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		    		        " model.nominatedPostMember.nominatedPostPosition.position.positionName from NominatedPost " +
		    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' ");
	    if(boardLevelId != null && boardLevelId.longValue()>0){
	    	queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId ");
	    }
	   Query query = getSession().createQuery(queryStr.toString());
	   if(boardLevelId != null && boardLevelId.longValue()>0){
		   query.setParameter("boardLevelId", boardLevelId);
	   }
	return query.list();   
   }
   public Long getAllPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId){
		 
	   StringBuilder queryStr = new StringBuilder();
	          
	    queryStr.append("select count(model.nominatedPostId) from NominatedPost model " +
	    		       " where " +
	    		       " model.isExpired='N' and model.isDeleted='N' ");
	    
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
        return (Long) query.uniqueResult();
 }
}
