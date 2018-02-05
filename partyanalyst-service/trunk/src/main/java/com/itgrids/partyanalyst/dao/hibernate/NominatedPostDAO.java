package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId)" +
				"  ,model.nominatedPostMemberId,model.nominatedPostMember.nominatedPostPosition.departmentId,model.nominatedPostMember.nominatedPostPosition.boardId ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1L && stateId != null)
			queryStr.append(" ,UserAddress model2 where model.nominatedPostMember.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		
		queryStr.append(" model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  ");		
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
		
		queryStr.append(" group by model.nominatedPostMemberId, model.nominatedPostStatusId,model.nominatedPostMember.boardLevelId order by model.nominatedPostMemberId  ");
		
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
		queryStr.append(" model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and  " +
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
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
					"  model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'   " );
			
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
					"  ORDER BY model.nominatedPostMember.nominatedPostPosition.position.orderNo ");
			
		}
		else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){

			str.append("SELECT model.nominatedPostMember.nominatedPostPosition.position.positionId," +
					" model.nominatedPostMember.nominatedPostPosition.position.positionName " +
					"	FROM NominatedPostFinal model" +
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
			
			str.append(" and  model.nominatedPostMember.nominatedPostPosition.positionId is not null ");
			
			/*if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status = :notYet ");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and model.applicationStatus.applicationStatusId not in (:running) ");
			}*/
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and model.applicationStatus.status not in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
				//str.append(" and model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
			}
			
			str.append(" GROUP BY model.nominatedPostMember.nominatedPostPosition.position.positionId ORDER BY model.nominatedPostMember.nominatedPostPosition.position.orderNo ");
		
		}
			else{
		
			str.append("SELECT position.positionId," +
					"  position.positionName " +
					"	FROM NominatedPostApplication model left join  model.position position " +
					" left join model.departments department " +
					" left join model.board board " +
					"   WHERE " +
					"  model.isDeleted ='N' " );
			
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
				str.append(" and department.departmentId in (:deptId) ");
			}
			if(boardId !=null && boardId.size()>0){
				str.append(" and board.boardId in (:boardId) ");
			}
			
			//str.append(" and  model.positionId is not null ");
			
			/*if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status = :notYet ");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and model.applicationStatus.applicationStatusId not in (:running) ");
			}*/
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
				str.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
			}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
				str.append(" and  model.positionId is not null ");
				str.append(" and model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
			}
			
			str.append(" GROUP BY position.positionId ORDER BY position.orderNo  ");
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
		
		str.append(" GROUP BY nominatedPostPosition.positionId ORDER BY nominatedPostPosition.orderNo ");
		
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
			str.append(" and  nominatedPostPosition.positionId is null ");
		}
		
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				str.append(" and model.nominatedPostStatus.status = :open ");
		}
		
		str.append(" and model.nominatedPostStatus.nominatedPostStatusId is not null ");
		
		
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
		queryStr.append("  and model.isDeleted ='N' " +
		"  and model.nominatedPostMember.isDeleted = 'N' " +
		"  and model.nominatedPostMember.nominatedPostPosition.isDeleted = 'N' "  );
		
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


	   public List<Object[]> getBoardLevelWiseDepartments(Long postType,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId,Long positionId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.departments.deptName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " +
		    		       "  and model.isDeleted='N' and model.nominationPostCandidateId is null ");
		   
		    if(postType != null && postType.longValue() > 0)
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.postType.postTypeId=:postTypeId ");
		    
		    if(positionId != null && positionId.longValue() > 0)
		    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId ");
		    
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
		    
		    if(positionId != null && positionId.longValue() > 0)
		    	query.setParameter("positionId", positionId);
		    
		    return query.list();
	   }
	 public List<Object[]> getLevelWiseDepartmentsBoard(Long departmentId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.board.boardId," +
		    		       " model.nominatedPostMember.nominatedPostPosition.board.boardName from NominatedPost " +
		    		       " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " +
		    			   " and model.isDeleted='N' and model.nominationPostCandidateId is null ");
		    
		    if(departmentId != null && departmentId.longValue() > 0){
		          queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId=:departmentId");
		    }
		    if(boardLevelId != null && boardLevelId.longValue() > 0){
		    	if(boardLevelId.longValue() !=5L)
		    		queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId");
		    	else
		    		 queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6)");
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
	 public List<Object[]> getLevelWiseDepartmentsBoardPosition(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
		    		        " model.nominatedPostMember.nominatedPostPosition.position.positionName" +
		    		        " from NominatedPost " +
		    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " +
	    		       		"  and model.isDeleted='N' and model.nominationPostCandidateId is null ");
		    		        
		    
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
		 
		 str.append(" SELECT distinct department.departmentId,department.deptName,board.boardId,board.boardName " +
					" FROM NominatedPost model " +
					" left join model.nominatedPostMember nominatedPostMember " +
					" left join model.nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
					" left join model.nominatedPostMember.nominatedPostPosition.departments department " +
					" left join model.nominatedPostMember.nominatedPostPosition.board board " +
					" left model.nominatedPostMember.address address " +
					" WHERE model.isDeleted = 'N'" +
					" and nominatedPostMember.isDeleted = 'N' " +
					" and nominatedPostPosition.isDeleted ='N'"  );
			if(boardLevelId !=null && boardLevelId>0){
				if(levelValue == null){
					if(boardLevelId.longValue() !=5L)
						str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
					else
						str.append(" and nominatedPostMember.boardLevelId in (5,6) ");
				}else{
					str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
				}
				
			}
			if(levelValue !=null && levelValue.size()>0){
				str.append(" and nominatedPostMember.locationValue in (:levelValue) ");
			}
			
			if(statusType !=null && statusType.trim().equalsIgnoreCase("Open")){
				str.append(" and model.nominatedPostStatus.status = :Open ");
			}
			if(searchlevelId != null && searchlevelId.longValue()>0L){
				if(searchlevelId.longValue() == 1L)
					str.append(" and model.nominatedPostMember.locationValue  = :searchLevelValue ");
				else if(searchlevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.state.stateId =:searchLevelValue ");
				
				else if(searchlevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.district.districtId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.constituency.constituencyId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.tehsil.tehsilId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.localElectionBody.localElectionBodyId =:searchLevelValue ");
				else if(searchlevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and address.panchayatId =:searchLevelValue ");
			}
			//str.append(" group by model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
			//		" model.nominatedPostMember.nominatedPostPosition.board.boardId  ");
			str.append(" order by department.deptName ");
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
	 
	 public List<Object[]> getOpenedPositionsCountByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status)
	 {
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("select distinct departments.departmentId ");
		 			
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open"))){
			 		sb.append("  ,count(nominatedPostMember.nominatedPostMemberId),0,0");
		 			sb.append(" from NominatedPost model "+
				 			" left join model.nominatedPostMember  nominatedPostMember "+
						 	" left join model.nominatedPostMember.nominatedPostPosition  nominatedPostPosition " + 
						 	" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
						 	" left join model.nominatedPostMember.nominatedPostPosition.board board " +
						 	" left join model.nominatedPostMember.boardLevel boardLevel " +
						 	" left join model.nominatedPostMember.address address ");
		 }else  if(status != null && status.equalsIgnoreCase("notYet") ){
			 sb.append(" , count( position.positionId), position.positionId,board.boardId ");
			 sb.append(" from NominatedPostApplication model"+
					 	" left join model.departments departments " +
					 	" left join model.board board " +
					 	" left join model.position position " +
					 	" left join model.boardLevel boardLevel " +
					 	" left join model.address address ");
		 }
		 else{
				sb.append(" , count(model.nominatedPostApplicationId) ");
				sb.append(" from NominatedPostFinal model "+
						" left join model.nominatedPostMember.nominatedPostPosition.board board " +
						" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
					 	" left join model.nominatedPostMember.boardLevel boardLevel " +
					 	" left join model.nominatedPostMember.address address ");
		 }


	 sb.append(" where ");
	 sb.append(" boardLevel.boardLevelId = :boardLevelId" );

	 
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
	 
	 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open")))
		 sb.append(" and model.isExpired = 'N'");
	 
	 sb.append(" and model.isDeleted = 'N'  group by departments.departmentId ");
	 if(status != null && status.equalsIgnoreCase("notYet") )
		 sb.append(" , board.boardId ,position.positionId");
		 
	 Query query = getSession().createQuery(sb.toString());
	 query.setParameter("boardLevelId", boardLevelId);
	 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
		 query.setParameter("searchLevelValue", searchLevelValue);
	 
	 return query.list();
	 }
	 
	 public List<Object[]> getOpenedPositionsCountByDepartmennt111(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status){
		 StringBuilder sb = new StringBuilder(); 
		 sb.append("select distinct nominatedPostPosition.departmentId,");
		 if(status != null && (status.equalsIgnoreCase("Total") ))
		 			sb.append(" count(distinct model.nominatedPostId)" +
		 			" from NominatedPost model "+
		 			" left join model.nominatedPostMember  nominatedPostMember "+
					" left join nominatedPostMember.nominatedPostPosition  nominatedPostPosition ");
		 else
			 sb.append(" count(distinct model.nominatedPostMemberId)" +
			 			" from NominatedPostApplication model " +
			 			" left join model.nominatedPostMember  nominatedPostMember "+
					 	" left join nominatedPostMember.nominatedPostPosition  nominatedPostPosition ");
		/* if(status != null && !status.equalsIgnoreCase("Total"))
		 	sb.append(" ,NominatedPostApplication model1 " +
		 			" where model.nominatedPostMemberId = model1.nominatedPostMemberId and " );
		 else*/
			 sb.append(" where ");
		 sb.append("  nominatedPostMember.boardLevel.boardLevelId = :boardLevelId" +
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
				 sb.append(" and nominatedPostMember.address.country.countryId = 1");
			 else if(searchLevelId == 2l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.state.stateId = :searchLevelValue");
			 else if(searchLevelId == 3l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.district.districtId = :searchLevelValue");
			 else if(searchLevelId == 4l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.constituency.constituencyId = :searchLevelValue");
			 else if(searchLevelId == 5l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.tehsil.tehsilId = :searchLevelValue");
			 else if(searchLevelId == 6l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.localElectionBody.localElectionBodyId = :searchLevelValue");
			 else if(searchLevelId == 7l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
				 sb.append(" and nominatedPostMember.address.panchayat.panchayatId = :searchLevelValue");
		 }
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open")))
			 sb.append(" and model.isExpired = 'N'");
		 
		 sb.append(" and model.isDeleted = 'N'" +
		 			" and nominatedPostMember.isDeleted = 'N' and nominatedPostPosition.isDeleted = 'N'" +
		 			" group by nominatedPostPosition.departmentId");
		 
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("boardLevelId", boardLevelId);
		 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			 query.setParameter("searchLevelValue", searchLevelValue);
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getOpenedPositionsCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select distinct departments.departmentId," +
		 			" board.boardId");
		 			
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open"))){
			 		sb.append(" , count(nominatedPostMember.nominatedPostMemberId),board.boardName");
		 			sb.append(" from NominatedPost model "+
				 			" left join model.nominatedPostMember  nominatedPostMember "+
						 	" left join model.nominatedPostMember.nominatedPostPosition  nominatedPostPosition " +
						 	" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
						 	" left join model.nominatedPostMember.nominatedPostPosition.board board " +
						 	" left join model.nominatedPostMember.boardLevel boardLevel " +
						 	" left join model.nominatedPostMember.address address ");
	 } else  if(status != null && status.equalsIgnoreCase("notYet") ){
		 sb.append(" , count(model.nominatedPostId) ");
		 sb.append(" from NominatedPost model"+
				 	" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
				 	" left join model.nominatedPostMember.nominatedPostPosition.board board " +
				 	" left join model.nominatedPostMember.boardLevel boardLevel " +
				 	" left join model.nominatedPostMember.address address ");
	 }else{
				sb.append(" , count(model.nominatedPostApplicationId),board.boardName");
				 sb.append(" from NominatedPostFinal model "+
							" left join model.nominatedPostMember.nominatedPostPosition.board board " +
							" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
						 	" left join model.nominatedPostMember.boardLevel boardLevel " +
						 	" left join model.nominatedPostMember.address address ");
		 }
		/* if(status != null && !status.equalsIgnoreCase("Total"))
			 sb.append(" ,NominatedPostApplication model1" +
		 			" where model.nominatedPostMemberId = model1.nominatedPostMemberId and" );
		 else*/
			 sb.append(" where ");
		 sb.append(" boardLevel.boardLevelId = :boardLevelId" );
		 		
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
			 	 sb.append(" and model.nominatedPostStatusId in (1,2) ");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")");
		 
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
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open") || status.equalsIgnoreCase("notYet") ))
			 sb.append(" and model.isExpired = 'N'");
		 
		 sb.append(" and model.isDeleted = 'N' " +
		 			" group by departments.departmentId, board.boardId");
		 
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("boardLevelId", boardLevelId);
		 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			 query.setParameter("searchLevelValue", searchLevelValue);
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getOpenedPositionWiseCountForBoardsByDepartment(Long boardLevelId,Long searchLevelId,Long searchLevelValue,String status){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select distinct departments.departmentId," +
		 			" board.boardId");
		 			
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open"))){
			 		sb.append(" , count(nominatedPostMember.nominatedPostMemberId),board.boardName");
		 			sb.append(" from NominatedPost model "+
				 			" left join model.nominatedPostMember  nominatedPostMember "+
						 	" left join model.nominatedPostMember.nominatedPostPosition  nominatedPostPosition " +
						 	" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
						 	" left join model.nominatedPostMember.nominatedPostPosition.board board " +
						 	" left join model.nominatedPostMember.boardLevel boardLevel " +
						 	" left join model.nominatedPostMember.address address ");
	 } else  if(status != null && status.equalsIgnoreCase("notYet") ){
		 sb.append(" , count(model.nominatedPostId),model.nominatedPostMember.nominatedPostPosition.positionId ");
		 sb.append(" from NominatedPost model"+
				 	" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
				 	" left join model.nominatedPostMember.nominatedPostPosition.board board " +
				 	" left join model.nominatedPostMember.boardLevel boardLevel " +
				 	" left join model.nominatedPostMember.address address ");
	 }else{
				sb.append(" , count(model.nominatedPostApplicationId),board.boardName");
				 sb.append(" from NominatedPostFinal model "+
							" left join model.nominatedPostMember.nominatedPostPosition.board board " +
							" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
						 	" left join model.nominatedPostMember.boardLevel boardLevel " +
						 	" left join model.nominatedPostMember.address address ");
		 }
		/* if(status != null && !status.equalsIgnoreCase("Total"))
			 sb.append(" ,NominatedPostApplication model1" +
		 			" where model.nominatedPostMemberId = model1.nominatedPostMemberId and" );
		 else*/
			 sb.append(" where ");
		 sb.append(" boardLevel.boardLevelId = :boardLevelId" );
		 		
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
			 	 sb.append(" and model.nominatedPostStatusId in (1) ");
		 else if(status != null && status.equalsIgnoreCase("running"))
			sb.append(" and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+")");
		 
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
		 if(status != null && (status.equalsIgnoreCase("Total") || status.equalsIgnoreCase("Open") || status.equalsIgnoreCase("notYet") ))
			 sb.append(" and model.isExpired = 'N'");
		 
		 sb.append(" and model.isDeleted = 'N' " +
		 			" group by departments.departmentId, board.boardId,model.nominatedPostMember.nominatedPostPosition.positionId ");
		 
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("boardLevelId", boardLevelId);
		 if(searchLevelId != 1l && searchLevelValue != null && searchLevelValue.longValue() > 0l)
			 query.setParameter("searchLevelValue", searchLevelValue);
		 
		 return query.list();
	 }
	 
	public List<Object[]> getTotalPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId){
		 
		   StringBuilder queryStr = new StringBuilder();
		          
		    queryStr.append("select model.nominatedPostStatus.nominatedPostStatusId,model.nominatedPostStatus.status,count(distinct model.nominatedPostId) from NominatedPost model " +
		    		       " where " +
		    		       " model.isExpired='N' and model.isDeleted='N' ");
		    
           if(positionId != null && positionId.longValue() > 0){
        	   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId=:positionId ");
           }
           if(boardLevelId != null && boardLevelId.longValue() > 0){
        	   queryStr.append(" and model.nominatedPostMember.boardLevel.boardLevelId=:boardLevelId ");
           }
           if(stateId != null && stateId.longValue() > 0){
 			   queryStr.append(" and model.nominatedPostMember.address.state.stateId=:stateId");
 		  }
		   
		  queryStr.append(" group by model.nominatedPostStatus.nominatedPostStatusId ");
		  
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
  

	public List<Object[]> getTotalCorpIdsAndBoardsIdsAndPositionsIds(Long boardLevelId,Long searchlevelId,Long searchLevelValue,Long nominatedPostStatusId){
		  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count( model.nominatedPostId), model.nominatedPostMember.nominatedPostMemberId ");
		queryStr.append(" from NominatedPost model   " );
		queryStr.append(" where ");
		queryStr.append(" model.isDeleted='N' and  model.nominatedPostMember.isDeleted ='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' ");	// for total 
						
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
		
		if(nominatedPostStatusId != null && nominatedPostStatusId.longValue()>0L)
			queryStr.append(" and model.nominatedPostStatusId =:nominatedPostStatusId ");
		
		queryStr.append(" group by  model.nominatedPostMember.nominatedPostMemberId  ");
		queryStr.append(" order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L && boardLevelId.longValue() != 5L)
			query.setParameter("boardLevelId", boardLevelId);
		if(searchLevelValue != null && searchLevelValue.longValue()>0L)
			query.setParameter("searchLevelValue", searchLevelValue);
	
		if(nominatedPostStatusId != null && nominatedPostStatusId.longValue()>0L)
			query.setParameter("nominatedPostStatusId", nominatedPostStatusId);
		
		return query.list();
}
	public List<Object[]> getBoardLevelsForOpenedPositions(){
		Query query = getSession().createQuery("select distinct model.nominatedPostMember.boardLevel.boardLevelId," +
										" model.nominatedPostMember.boardLevel.level" +
										" from NominatedPost model" +
										" where model.nominationPostCandidateId is null" +
										" and model.nominatedPostStatus.nominatedPostStatusId in ("+IConstants.NOMINATED_OPEN_POSTS_STATUS_IDS+" ) " +  //Only Open Position Levels
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
				" where  model.isDeleted = 'N' and model.nominatedPostMember.boardLevelId =:levelId and model.nominatedPostMember.isDeleted='N' and " +
				" model.isDeleted='N' and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+")  order by  " +
				" model.nominatedPostMember.nominatedPostPosition.departmentId ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Long> getTotalApplicationsDeptsCountforAnyBoards(Long levelId){
		Query query= getSession().createQuery("select distinct model.departmentId   from NominatedPostApplication model " +
				" where  model.isDeleted = 'N' and model.boardLevelId =:levelId  and model.isDeleted='N'   and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+")  " +
				" order by  model.departmentId ");
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
				" where model.isDeleted = 'N' and model.nominatedPostMember.boardLevelId =:levelId  " +
				"  and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+") order by   " + 
				" model.nominatedPostMember.nominatedPostPosition.departmentId, model.nominatedPostMember.nominatedPostPosition.boardId ");
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getTotalApplicationsCorpsIdsForAnyPostsCount(Long levelId){
		Query query= getSession().createQuery("select distinct model.departmentId , " +
				" model.boardId  from NominatedPostApplication model " +
				" where model.isDeleted = 'N' and model.boardLevelId =:levelId  and model.applicationStatusId not in ("+IConstants.NOMINATED_POST_REJECTED_STATUS_IDS+")  ");
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
	    queryStr.append(" order by model.nominatedPostMember.nominatedPostPosition.position.positionName ");
	   Query query = getSession().createQuery(queryStr.toString());
	   if(boardLevelId != null && boardLevelId.longValue()>0){
		   query.setParameter("boardLevelId", boardLevelId);
	   }
	return query.list();   
   }
   
   public Long getAllPositionCntPositionAndLocationWise(Long positionId,Long boardLevelId,Long stateId){
		 
	   StringBuilder queryStr = new StringBuilder();
	          
	    queryStr.append("select count(distinct model.nominatedPost.nominatedPostId) from NominatedPostFinal model " +
	    		       " where " +
	    		       " model.nominatedPost.isExpired='N' and model.nominatedPost.isDeleted='N' and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' ");
	    
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
        return (Long) query.uniqueResult();
 }
   
   public List<Object[]> getPositionsForABoard(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId){
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName " +
	   		" from NominatedPost model " +
	   		" where model.nominatedPostMember.boardLevelId=:locationLevelId " +
	   		" and model.nominatedPostMember.locationValue in (:locationLevelValueList) " +
	   		" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId " +
	   		" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId " +
	   		" and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
	   		" and model.nominatedPostStatusId=3 ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   query.setParameter("locationLevelId", locationLevelId);
	   query.setParameterList("locationLevelValueList", locationLevelValueList);
	   query.setParameter("departmentId", departmentId);
	   query.setParameter("boardId", boardId);
	   
	   return query.list();
   }
   
   public List<Long> getNominatedPostIds(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId,List<Long> positionsList){
	   Query query = getSession().createQuery(" select model.nominatedPostId from NominatedPost model " +
		   		" where model.nominatedPostMember.boardLevelId=:locationLevelId " +
		   		" and model.nominatedPostMember.locationValue in (:locationLevelValueList) " +
		   		" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId " +
		   		" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId " +
		   		" and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " +
		   		" and model.nominatedPostStatusId=:oldStatus " +
		   		" and model.nominatedPostMember.nominatedPostPosition.positionId in (:positionsList) ");
		   
		   	query.setParameter("locationLevelId", locationLevelId);
		   	query.setParameterList("locationLevelValueList", locationLevelValueList);
		   	query.setParameter("departmentId", departmentId);
		   	query.setParameter("boardId", boardId);
		   	query.setParameterList("positionsList", positionsList);
		   	query.setParameter("oldStatus", 3l);
		   	
		   	return query.list();
   }
   
   public Integer updateGoIssuedStatusInNominatedPost(List<Long> nominatedPostIds,Date date){
	   Query query = getSession().createQuery(" update NominatedPost model set model.nominatedPostStatusId=:nominatedPostStatusId,model.updatedTime=:date " +
	   		" where model.nominatedPostId in (:nominatedPostIds) ");
	   
	   	query.setParameter("nominatedPostStatusId", 4l);
	   	query.setParameterList("nominatedPostIds", nominatedPostIds);
	   	query.setDate("date", date);
	   	
	   	return query.executeUpdate();
   }
   
   public List<Long> checkPositionAvailableOrNot(Long departmentId,Long boardId,Long positionId,Long boardLevlId,Long searchLevelValue,Long searchLevelId){
	   StringBuilder queryStr = new StringBuilder();
       
	    queryStr.append(" select distinct model.nominatedPostId from NominatedPost model " +
	    		"where model.nominatedPostStatus.nominatedPostStatusId != 4 " +
	    		"and model.nominationPostCandidateId is null "+
	    		" and model.isDeleted = 'N' and model.isExpired = 'N' " +
	    		"and model.nominatedPostMember.isDeleted = 'N' " +
	    		" and " +
	    		" model.nominatedPostMember.nominatedPostPosition.departments.departmentId =:departmentId " +
	    		"and model.nominatedPostMember.nominatedPostPosition.board.boardId =:boardId " +
	    		"and " +
	    		" model.nominatedPostMember.nominatedPostPosition.position.positionId =:positionId " +
	    		"and model.nominatedPostMember.boardLevel.boardLevelId =:boardLevlId " +
	    		"and model.nominatedPostMember.nominatedPostPosition.isDeleted='N' ");
	    
	    if(searchLevelId != null && searchLevelId.longValue()>0L){
	        if((searchLevelId.longValue() == 1L))
	        	queryStr.append(" and model.nominatedPostMember.address.country.countryId  = 1 ");
	        else if((searchLevelId.longValue() == 2L) && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.state.stateId =:searchLevelValue ");
	        else if(searchLevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.district.districtId =:searchLevelValue  ");
	        else if(searchLevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.constituency.constituencyId =:searchLevelValue  ");
	        else if(searchLevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.tehsil.tehsilId =:searchLevelValue  ");
	        else if(searchLevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId =:searchLevelValue  ");
	        else if(searchLevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0l)
	        	queryStr.append(" and model.nominatedPostMember.address.panchayatId =:searchLevelValue ");
	      }
	   	
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("departmentId", departmentId);
	    query.setParameter("boardId", boardId);
	    query.setParameter("positionId", positionId);
	    query.setParameter("boardLevlId", boardLevlId);
	    query.setParameter("searchLevelValue", searchLevelValue);
	    
	    
	    return query.list();
   
   }
   
   public List<Object[]> getApllicationDepmtBoards(Long departmentId,Long boardLevelId,Long levelValue,Long positionId){
	   
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append("  select distinct model.nominatedPostMember.nominatedPostPosition.board.boardId, " +
	   		" model.nominatedPostMember.nominatedPostPosition.board.boardName from NominatedPost model where " +
	   		" model.nominatedPostMember.boardLevel.boardLevelId =:boardLevelId and model.nominatedPostMember.nominatedPostPosition.departmentId =:departmentId" +
	   		" and model.nominatedPostMember.locationValue = :levelValue and model.nominatedPostMember.isDeleted = 'N' and model.isExpired='N' and  model.nominationPostCandidateId is null ");
	   
	   if(positionId != null && positionId.longValue() > 0l)
		   queryStr.append(" and  model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId " ); 
	   Query query = getSession().createQuery(queryStr.toString());
	   query.setParameter("boardLevelId", boardLevelId);
	   query.setParameter("levelValue", levelValue);
	   query.setParameter("departmentId", departmentId);
	   
	   if(positionId != null && positionId.longValue() > 0l)
		   query.setParameter("positionId", positionId);
	   
	   return query.list();
   }
   
   public Integer updateNominatedPost(Long nominatedPostId,Long nominatedPostCandidateId,Date date,Long userId){
	   Query query = getSession().createQuery(" update NominatedPost model set model.nominationPostCandidateId=:nominatedPostCandidateId, " +
	   		" model.nominatedPostStatusId=:statusId, model.updatedTime=:date, model.updatedBy=:userId where model.nominatedPostId=:nominatedPostId ");
	   
	   query.setParameter("nominatedPostCandidateId", nominatedPostCandidateId);
	   query.setParameter("statusId", 4l);
	   query.setParameter("date", date);
	   query.setParameter("userId", userId);
	   query.setParameter("nominatedPostId", nominatedPostId);
	   
	   return query.executeUpdate();
	   
   }
   
   public List<NominatedPost> getNominatedPostByMemberOfFinalReview(Long memberId){
	   Query query = getSession().createQuery(" select model from NominatedPost model" +
	   		" where model.isDeleted ='N' " +
	   		" and model.nominatedPostStatus.nominatedPostStatusId =:finaReview " +
	   		" and model.nominatedPostMember.nominatedPostMemberId = :memberId ");
	   
	   
	   query.setParameter("finaReview", IConstants.NOMINATED_POST_FINAL_REVIEW);
	   query.setParameter("memberId", memberId);
	   
	   return query.list();
	   
   }
   public Integer updateNominatedPostsForOpenState(Set<Long> postIds,Long userId,Date date){
	   
	   Query query = getSession().createQuery(" update NominatedPost model set model.nominatedPostStatus.nominatedPostStatusId = 1 " +
	   		" ,model.updatedTime=:date,model.updatedBy=:userId " +
	   		" where model.nominatedPostId in (:postIds) ");
	   
	   query.setParameter("userId", userId);
	   query.setDate("date",date);
	   query.setParameterList("postIds", postIds);
	   
	   return query.executeUpdate();
	   
   }
   public Long getOpenedPositions(Long memberId){
		
	   Query query = getSession().createQuery(" select count(model.nominatedPostId) from NominatedPost model" +
		   		" where model.isDeleted ='N' and model.nominatedPostMember.nominatedPostMemberId = :memberId and model.nominationPostCandidate.nominationPostCandidateId is null ");
		   		
		    query.setParameter("memberId", memberId);
	   
		   
		   return (Long)query.uniqueResult();	
		
   }
    
   public List<Object[]> getStatusWiseNominatedProfileDetils(Long stateId,Long casteStateId,Long positionId,Long boardLevelId,Long casteCategryId,
                                                             Long ageRangeTypeId,Long deptmentId,Long corptionId,
                                                             String genderType,List<Long> postStatusIds,Long locationId,String type){
	         StringBuilder sb = new StringBuilder();
	         sb.append(" select distinct model2.nominationPostCandidateId," +
	                 " model2.candidateName," +
			         " model2.mobileNo,"+
	                 " tc.relativename," +
			         " tc.memberShipNo," +
	                 " model2.imageurl," );
			         //" model2.address.district.districtId,"+
	                 //" model2.address.district.districtName," );
	        /* if(type.toString().equalsIgnoreCase("candidate")){
	        	 sb.append(" const.constituencyId," +
	                 " const.name," );
	         }else if(type.toString().equalsIgnoreCase("application")){
	        	 sb.append(" tc.userAddress.constituency.constituencyId,tc.userAddress.constituency.name, ");
	         }*/
	         sb.append(" tc.tdpCadreId,model.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
	                 " model.nominatedPostMember.nominatedPostPosition.departments.deptName,model.nominatedPostMember.nominatedPostPosition.board.boardId," +
	                 "model.nominatedPostMember.nominatedPostPosition.board.boardName,model.nominatedPostMember.nominatedPostPosition.position.positionId," +
	                 "model.nominatedPostMember.nominatedPostPosition.position.positionName  ");
	         
	         if(postStatusIds != null && !postStatusIds.isEmpty()){
	        	  
	        	  if(type.toString().equalsIgnoreCase("candidate")){
	        		  //sb.append(" , model.nominatedPost.nominatedPostStatus.nominatedPostStatusId, model.nominatedPost.nominatedPostStatus.status ");
	        		  sb.append(" , NP.nominatedPostStatus.nominatedPostStatusId, NP.nominatedPostStatus.status ");
	        	  }else if(type.toString().equalsIgnoreCase("application")){
	        		  sb.append(" , model.applicationStatus.applicationStatusId, model.applicationStatus.status ");
	        	  }
	        	  
	          }
	        // if(casteCategryId != null && casteCategryId.longValue()>0l){
	        	// sb.append(", model2.nominatedPostAgeRange.nominatedPostAgeRangeId is not null ");
	         //}
	         
	         //if(casteStateId != null && casteStateId.longValue()>0l ){
	        	// sb.append(", model2.casteState.casteStateId is not null,model2.nominatedPostAgeRange.nominatedPostAgeRangeId is not null  ");
	         //}
	         
	         
	                 sb.append(" from NominatedPost NP,NominatedPostFinal model , NominationPostCandidate model2 left join model2.tdpCadre tc");
	                 /*if(type.toString().equalsIgnoreCase("candidate")){
	                	 sb.append(" left join model2.tdpCadre tc " +
			         " left join tc.userAddress addr left join addr.constituency const ");
	                 }else if(type.toString().equalsIgnoreCase("application")){
	                	 sb.append(" left join model2.tdpCadre tc " );
	                 }*/
			        
	   
	          if(stateId != null && stateId.longValue() > 0){
	       		sb.append(" where model.nominatedPostMember.address.state.stateId=:stateId");
   		      }
	          
	          if( locationId.longValue() == 0l && type.toString().equalsIgnoreCase("application") && (casteCategryId.longValue()>0l || casteStateId.longValue()>0l)){
	        	  sb.append(" and  model2.nominatedPostAgeRange.nominatedPostAgeRangeId is not null  ");
	          }
	          sb.append(" and  model.isDeleted='N' and model2.isDeleted='N'" +
	   	          	   " and model.nominatedPostMember.isDeleted='N' and model.nominatedPostMember.nominatedPostPosition.isDeleted='N'   " +
	        		  " and NP.nominatedPostId = model.nominatedPost.nominatedPostId " +
	   	          	  " and NP.nominationPostCandidate.nominationPostCandidateId = model2.nominationPostCandidateId ");
	         // sb.append(" and tc.enrollmentYear = 2014 and tc.isDeleted = 'N' " );  
	          if(postStatusIds != null && !postStatusIds.isEmpty()){
	        	  //sb.append(" and model.applicationStatus.applicationStatusId in(:postStatusIds)");
	        	  if(type.toString().equalsIgnoreCase("candidate")){
	        		  sb.append(" and NP.nominatedPostStatus.nominatedPostStatusId in(:postStatusIds)");
	        	  }else if(type.toString().equalsIgnoreCase("application")){
	        		  sb.append(" and model.applicationStatus.applicationStatusId in(:postStatusIds)");
	        	  }
	        	  //sb.append(" and model.nominatedPost.nominatedPostStatus.nominatedPostStatusId in ("+IConstants.NOMINATED_POST_FINALIZED_GOISSUED_STATUS+") ");
	          }
	          if(casteStateId != null && casteStateId.longValue()>0l){
	        	  if(type.toString().equalsIgnoreCase("candidate"))
		   		     sb.append(" and model2.casteState.casteStateId =:casteStateId"); 
	        	  else if(type.toString().equalsIgnoreCase("application"))
	        		  sb.append(" and model2.casteState.caste.casteId =:casteStateId"); 
	        	 
		   		  }
		   		  if(ageRangeTypeId != null && ageRangeTypeId.longValue()>0l){
		   		     sb.append(" and model2.nominatedPostAgeRange.nominatedPostAgeRangeId =:ageRangeTypeId"); 
		   		  }
		   		  if(deptmentId != null && deptmentId.longValue()>0l){
		   		     sb.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId =:deptmentId"); 
		   		  }
		   		  if(corptionId != null && corptionId.longValue()>0l){
		   		     sb.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId =:corptionId"); 
		   		  }
		   		  if(casteCategryId != null && casteCategryId.longValue()>0l){
		   		     sb.append(" and model2.casteState.casteCategoryGroup.casteCategory.casteCategoryId  =:casteCategryId"); 
		   		  }
		   		  if(positionId !=null && positionId.longValue()>0l){
		   			  sb.append(" and  model.nominatedPostMember.nominatedPostPosition.position.positionId =:positionId ");
		   		  }
		   		  if(genderType != null && !genderType.equalsIgnoreCase("")){
		   			  sb.append(" and  model2.gender =:genderType ");
		   		  }
		   		  if(boardLevelId >0L){
		   		    if(boardLevelId != 5l){
		   			  sb.append(" and model.nominatedPostMember.boardLevel.boardLevelId =:boardLevelId ");
		   		    }else {
		   			 sb.append(" and model.nominatedPostMember.boardLevel.boardLevelId in (5,6) ");
		   		    }
		   		 }  
		   		  
		   		if(locationId !=null && locationId.longValue()>0l){
		   			
		   			if(boardLevelId.longValue() == 2l){
		   				sb.append(" and  model.nominatedPostMember.address.state.stateId = :locationId ");
		   			}
		   			else if(boardLevelId.longValue() == 3l){
		   				sb.append(" and  model.nominatedPostMember.address.district.districtId = :locationId  ");
		   			}
		   			else if(boardLevelId.longValue() == 4l){
		   				sb.append(" and model.nominatedPostMember.address.constituency.constituencyId = :locationId ");
		   			}
		   			else if(boardLevelId.longValue() == 5l){
		   				sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId = :locationId ");
		   			}
		   			else if(boardLevelId.longValue() == 7l){
		   				sb.append(" and  model.nominatedPostMember.address.panchayat.localElectionBodyId = :locationId ");
		   			}
		   			else if(boardLevelId.longValue() == 6l){
		   				sb.append(" and model.nominatedPostMember.address.panchayat.panchayatId = :locationId ");
		   			}
		   		}
	   		  //sb.append("and model.nominationPostCandidate.tdpCadreId = tc.tdpCadreId");
	   		 Query qry = getSession().createQuery(sb.toString());
	   		 
	   		if(stateId !=null && stateId.longValue()>0l){
	   			qry.setParameter("stateId", stateId);
	   		}
	   	   if(postStatusIds != null && !postStatusIds.isEmpty()){
	   		 qry.setParameterList("postStatusIds", postStatusIds);
	   	   }
	   	  if(casteStateId != null && casteStateId.longValue()>0l){
	   		qry.setParameter("casteStateId", casteStateId);
	   	  }
	   	  if(casteCategryId != null && casteCategryId.longValue()>0l){
	   		qry.setParameter("casteCategryId", casteCategryId);
	   	  }
	   	  if(positionId !=null && positionId.longValue()>0l){
	   		qry.setParameter("positionId", positionId);
	     }
	   	 if(boardLevelId !=null && boardLevelId.longValue()>0l && boardLevelId != 5l){
		   		qry.setParameter("boardLevelId", boardLevelId);
		     }
	   	if(ageRangeTypeId !=null && ageRangeTypeId.longValue()>0l){
	   		qry.setParameter("ageRangeTypeId", ageRangeTypeId);
	     }
	   	if(deptmentId !=null && deptmentId.longValue()>0l){
	   		qry.setParameter("deptmentId", deptmentId);
	     }
	   	if(corptionId !=null && corptionId.longValue()>0l){
	   		qry.setParameter("corptionId", corptionId);
	     }
	   	if(genderType !=null && !genderType.equalsIgnoreCase("")){
	   		qry.setParameter("genderType", genderType);
	     }
	   	if(locationId !=null && locationId.longValue()>0l){
	   		qry.setParameter("locationId", locationId);
	     }
	   
	   return qry.list();
   }
   public List<Object[]> getNominatedPostDetails(Long locationLevelId,List<Long> locationValues,Long departmentId,Long boardId,Long positionId){
	   
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select model.nominatedPostStatusId,count(distinct model.nominatedPostId)  " +
	    				" from NominatedPost model where model.isDeleted='N'  ");
	    if(locationLevelId != null && locationLevelId.longValue() == 1l){//1-central
	    	queryStr.append(" and model.nominatedPostMember.address.country.countryId in (:locationValues) ");	
	    }else if(locationLevelId != null && locationLevelId.longValue() == 2l){// 2-state
	    	queryStr.append(" and model.nominatedPostMember.address.state.stateId in (:locationValues) ");	
	    }else if(locationLevelId.longValue() == 3l){// 3-district
	    	queryStr.append(" and model.nominatedPostMember.address.district.districtId in (:locationValues) ");	
	    }else if(locationLevelId.longValue() == 4l){// 4-constituency
	    	queryStr.append(" and model.nominatedPostMember.address.constituency.constituencyId in (:locationValues) ");	
	    }
	    if(locationLevelId != null && locationLevelId.longValue() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.boardLevelId=:locationLevelId ");
	    }
	    if(departmentId != null && departmentId.longValue() > 0){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId ");
	    }
	    if(boardId != null && boardId.longValue() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId ");
	    }
	    if(positionId != null && positionId.longValue() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.positionId=:positionId ");
	    }
	    queryStr.append(" group by model.nominatedPostStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	   if(locationValues != null && locationValues.size() > 0l){
		   query.setParameterList("locationValues", locationValues);
	   }
	   if(departmentId != null && departmentId.longValue() > 0l){
		  query.setParameter("departmentId", departmentId);   
	   }
	   if(boardId != null && boardId.longValue() > 0l){
		   query.setParameter("boardId", boardId);   
	   }
	   if(positionId != null && positionId.longValue() > 0l){
		   query.setParameter("positionId", positionId); 
	   }
	   if(locationLevelId != null && locationLevelId.longValue() > 0l){
		  query.setParameter("locationLevelId", locationLevelId); 
	   }
	   return query.list();
   }
   
   public List<Object[]> getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(Long LocationLevelId,List<Long> locationValues,Long departmentId,Long boardId){
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select ");
	   if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() == 0l && boardId != null && boardId.longValue() ==  0l){
    	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.departments.departmentId,model.nominatedPostMember.nominatedPostPosition.departments.deptName,");
       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() == 0l){
      	   queryStr.append(" model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName,");
       }else if(LocationLevelId != null && LocationLevelId.longValue() >= 1l && departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l){
 		  queryStr.append(" model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominatedPostMember.nominatedPostPosition.position.positionName,");
 	   }
       queryStr.append(" count(distinct model.nominatedPostId)"); 
       queryStr.append(" from  NominatedPost model where model.isDeleted = 'N' and model.nominatedPostStatusId in ("+IConstants.NOMINATED_OPEN_POSTS_STATUS_IDS+") ");
        if(locationValues !=null && locationValues.size()>0){
        	queryStr.append(" and model.nominatedPostMember.locationValue in (:locationValues)");
		}
	    if(LocationLevelId != null && LocationLevelId.longValue() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.boardLevelId=:locationLevelId ");
	    }
	    if(departmentId != null && departmentId.longValue() > 0){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departmentId=:departmentId ");
	    }
	    if(boardId != null && boardId.longValue() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.boardId=:boardId ");
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
    	 query.setParameter("locationLevelId", LocationLevelId);
       }
       if(locationValues != null && locationValues.size() > 0){
    	   query.setParameterList("locationValues", locationValues);
       }
       if(departmentId != null && departmentId.longValue() > 0){
    	   query.setParameter("departmentId", departmentId);
       }
       if(boardId != null && boardId.longValue() > 0){
    	   query.setParameter("boardId", boardId);
      }
       return query.list();
  }
   public List<Object[]> getLevelWiseDepartmentsBoardPosition1(List<Long> departmentId,List<Long> boardId,Long boardLevelId,List<Long> searchLevelValue){
	   
	   StringBuilder queryStr = new StringBuilder();
	   
	    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
	    		        " model.nominatedPostMember.nominatedPostPosition.position.positionName from NominatedPost " +
	    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " );
	    		        
	    
	       if(departmentId != null && departmentId.size()> 0L){
	    	   if(departmentId.size() == 1 && departmentId.get(0).longValue() >0L)
	    		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId in (:deapartmentId) ");
	    	   else if(departmentId.size() > 1)
	    		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId in (:deapartmentId) ");
		   }
           if(boardId != null && boardId.size()> 0){
        	   if(boardId.size() == 1 && boardId.get(0).longValue() >0L)
        		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId in (:boardId) ");	
        	   else if(boardId.size() > 1)
        		   queryStr.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId in (:boardId) ");
		   }
           if(boardLevelId != null && boardLevelId.longValue() > 0L){
        	   if(boardLevelId.longValue() !=5L)
        		   queryStr.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
        	   else
        		   queryStr.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
        	   //if(searchLevelValue != null && searchLevelValue.longValue() > 0L)
				//   queryStr.append(" and model.nominatedPostMember.locationValue =:searchLevelValue ");
  	      }
           
           /*if(searchlevelId != null && searchlevelId.longValue()>0L){
				if(searchlevelId.longValue() == 1L)
					queryStr.append(" and model.nominatedPostMember.locationValue  in(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.state.stateIdin(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.district.districtId in(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.constituency.constituencyId in(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.tehsil.tehsilId in(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:searchLevelValue) ");
				else if(searchlevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					queryStr.append(" and model.nominatedPostMember.address.panchayatId in(:searchLevelValue) ");
			}
		    else*/
           if(searchLevelValue != null && searchLevelValue.size() > 0L)
		    	 queryStr.append(" and model.nominatedPostMember.locationValue in(:searchLevelValue) ");
           
           
           
		    Query query = getSession().createQuery(queryStr.toString()+" order by model.nominatedPostMember.nominatedPostPosition.position.positionName asc ");
		    
			/* if(departmentId != null && departmentId.size()> 0L){
					query.setParameterList("deapartmentId", departmentId);
			 }
			 if(boardId != null && boardId.size()> 0L){
					query.setParameterList("boardId", boardId); 
			 }*/
		    
		    if(departmentId != null && departmentId.size()> 0L){
		    	   if(departmentId.size() == 1 && departmentId.get(0).longValue() >0L)
		    			query.setParameterList("deapartmentId", departmentId);
		    	   else if(departmentId.size() > 1)
		    			query.setParameterList("deapartmentId", departmentId);
			   }
	           if(boardId != null && boardId.size()> 0){
	        	   if(boardId.size() == 1 && boardId.get(0).longValue() >0L)
	        		   query.setParameterList("boardId", boardId); 
	        	   else if(boardId.size() > 1)
	        		   query.setParameterList("boardId", boardId); 
			    }
	           
			 if(boardLevelId != null && boardLevelId.longValue() > 0L && boardLevelId.longValue() !=5L){
			    	query.setParameter("boardLevelId", boardLevelId);
			  }
			 if(searchLevelValue != null && searchLevelValue.size() > 0L)
			    	query.setParameterList("searchLevelValue", searchLevelValue);
	    return query.list();
 }
   
   public int updatePoststoOpenByPostIds(List<Long> nominatedPostIdsLsist, Date currentDate,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("update NominatedPost model set model.nominationPostCandidateId = null ,model.updatedTime=:currentDate,model.nominatedPostStatusId = 1  ");
		if(userId != null && userId.longValue()>0L)
			queryStr.append(" ,model.updatedBy=:updatedBy ");
		queryStr.append(" where model.isDeleted='N' and model.nominatedPostId in (:nominatedPostIdsLsist)  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("currentDate", currentDate);
		query.setParameterList("nominatedPostIdsLsist", nominatedPostIdsLsist);
		if(userId != null && userId.longValue()>0L)
			query.setParameter("updatedBy", userId);
		
		return query.executeUpdate();
	}
   
   public List<Long> getNominatedPostIdsForBoardLevelId(Long boardLevelId,Long levelValue,Long departmentId,Long boardId,Long positionId){
	   StringBuilder str = new StringBuilder(); 
	   str.append(" select model.nominatedPostId from NominatedPost model" +
		   		" where model.isDeleted ='N'  and model.nominationPostCandidate.nominationPostCandidateId is null " +
		   		" and model.nominatedPostStatusId in (1,2) ");
	   
		   		if(boardLevelId != null && boardLevelId.longValue() >0l){
		   			str.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId " );
		   		}
		   		if(levelValue != null && levelValue.longValue() >0l){
		   			str.append(" and model.nominatedPostMember.locationValue = :levelValue " );
		   		}
		   		if(departmentId != null && departmentId.longValue() >0l){
		   			str.append(" and model.nominatedPostMember.nominatedPostPosition.departments.departmentId = :departmentId " );
		   		}
		   		if(boardId != null && boardId.longValue() >0l){
		   			str.append(" and model.nominatedPostMember.nominatedPostPosition.board.boardId = :boardId " );
		   		}
		   		if(positionId != null && positionId.longValue() >0l){
		   			str.append(" and model.nominatedPostMember.nominatedPostPosition.position.positionId = :positionId " );
		   		}
		   	 Query query = getSession().createQuery(str.toString());
		   		
		   	if(boardLevelId != null && boardLevelId.longValue() >0l){
		   		query.setParameter("boardLevelId", boardLevelId);
	   		}
	   		if(levelValue != null && levelValue.longValue() >0l){
	   			query.setParameter("levelValue", levelValue);
	   		}
	   		if(departmentId != null && departmentId.longValue() >0l){
	   			query.setParameter("departmentId", departmentId);
	   		}
	   		if(boardId != null && boardId.longValue() >0l){
	   			query.setParameter("boardId", boardId);
	   		}
	   		if(positionId != null && positionId.longValue() >0l){
	   			query.setParameter("positionId", positionId);
	   		}
		    
	   return query.list();	
		
   }

 public List<Long> getNominatedPostIds(Long nominateCandId){
		Query query = getSession().createQuery("select distinct model.nominatedPostId " +
				   							   " from NominatedPost model " +
				   							   " where  model.nominationPostCandidate.nominationPostCandidateId =:nominateCandId and " +
				   							   " model.nominatedPostStatus.nominatedPostStatusId = 4 and " +
				   							   " model.isDeleted = 'N' ");
		query.setParameter("nominateCandId", nominateCandId);
		return query.list();
	}
 public Long getOfNominatedPostCondidates(Long nominateCandId,Long nominatedPostMemberId){
	   Query query = getSession().createQuery("select distinct model.nominatedPostId " +
				   " from NominatedPost model " +
				   " where  model.nominationPostCandidate.nominationPostCandidateId =:nominateCandId and model.nominatedPostMember.nominatedPostMemberId =:nominatedPostMemberId " +
				   " and model.nominatedPostStatus.nominatedPostStatusId = 4 and " +
				   " model.isDeleted = 'N' ");
query.setParameter("nominateCandId", nominateCandId);
query.setParameter("nominatedPostMemberId", nominatedPostMemberId);
return (Long) query.uniqueResult();
	   
   }
 public List<Object[]> getTotalPostCandidates(Long departmentId,Long boardId,Long positionId){
		Query query = getSession().createQuery("select model.nominatedPostMember.nominatedPostPosition.positionId,count(model.nominatedPostId) " +
		   " from NominatedPost model " +
		   " where  model.nominatedPostMember.nominatedPostPosition.departmentId =:departmentId and " +
		   " model.nominatedPostMember.nominatedPostPosition.boardId =:boardId and " +
		   " model.nominatedPostMember.nominatedPostPosition.positionId =:positionId  and " +
		   "  model.isDeleted = 'N'  and model.nominatedPostMember.isDeleted = 'N' " +
		   " and model.nominatedPostMember.nominatedPostPosition.isDeleted ='N' " +
		   " GROUP BY model.nominatedPostMember.nominatedPostPosition.positionId ");
		
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		return  query.list();
	}
 public List<Object[]> getOpenPostCandidates(Long departmentId,Long boardId,Long positionId){
		Query query = getSession().createQuery("select model.nominatedPostMember.nominatedPostPosition.positionId,count(model.nominatedPostId) " +
				   							   " from NominatedPost model " +
				   							   " where  model.nominatedPostMember.nominatedPostPosition.departmentId =:departmentId and " +
				   							   " model.nominatedPostMember.nominatedPostPosition.boardId =:boardId and " +
				   							   " model.nominatedPostMember.nominatedPostPosition.positionId =:positionId and  " +
				   							   " model.isDeleted = 'N'  and model.nominatedPostMember.isDeleted = 'N'  " +
				   							   " and model.nominatedPostMember.nominatedPostPosition.isDeleted ='N' and model.nominatedPostStatusId = 1  and " +
				   							   " model.nominationPostCandidateId is null GROUP BY model.nominatedPostMember.nominatedPostPosition.positionId ");
		query.setParameter("departmentId", departmentId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		return  query.list();
	}
 public List<Object[]> getNominatedPostStatusWiseCount(Long locationType,List<Long> locationValuesList, Date startDate, Date endDate,
			String year){
	 StringBuilder sb = new StringBuilder();
	 sb.append(" select " +
	 		   " nominatedPost.nominatedPostStatus.nominatedPostStatusId, " +
	 		   " nominatedPost.nominatedPostStatus.status, " +
	 		   " count(distinct nominatedPost.nominatedPostId) " +
	 		   " from NominatedPost nominatedPost where " +
			   " nominatedPost.isDeleted = 'N' " +
			   " and nominatedPost.isExpired = 'N' " +
			   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
	 
	 	if (locationType != null && locationValuesList != null && locationValuesList.size()>0) {
	 			if (locationType == 2) {
	 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValue) ");
	 			} else if (locationType == 3) {
					sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValue) ");
				} else if (locationType == 10) {
					sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
				} else if (locationType == 4) {
					sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValue) ");
				} else if (locationType == 5) {
					sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValue) ");
				}else if (locationType == 6) {
					sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValue) ");
				}else if (locationType == 7) {
					sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValue) ");
				}else if (locationType == 8) {
					sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValue) ");
				}		
				
	   }
	 if(startDate != null && endDate != null){
		 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
	 }
	 sb.append(" group by nominatedPost.nominatedPostStatus.nominatedPostStatusId "); 
	 sb.append(" order by nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
	 Query query = getSession().createQuery(sb.toString());
	 if(locationType != 2){
		 query.setParameterList("locationValue",locationValuesList);
	 }
	 if(startDate != null && endDate != null){
		 query.setDate("startDate",startDate);
		 query.setDate("endDate",endDate);
	 }
	 return query.list();
 }
 @SuppressWarnings("unchecked")
public List<Object[]> getPositionWiseMemberCount(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year){
	 StringBuilder sb = new StringBuilder();
	 sb.append(" select " +
	 		   " nominatedPost.nominatedPostMember.boardLevel.boardLevelId, " +
	 		   " nominatedPost.nominatedPostMember.boardLevel.level, " +
	 		   " count(nominatedPost.nominatedPostId) " +
	 		   " from NominatedPost nominatedPost where " +
			  // " nominatedPost.nominatedPostMember.address.constituency.constituencyId = :constituencyId " +
			   " nominatedPost.isDeleted = 'N' " +
			   " and nominatedPost.isExpired = 'N' " +
			   " and nominatedPost.nominatedPostMember.isDeleted = 'N' " +
			   " and nominatedPost.nominationPostCandidate.isDeleted='N' ");
	 
	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
		 	if(locationTypeId == 2l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.state.stateId in(:locationValues) ");
	        }else if(locationTypeId == 4l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.constituency.constituencyId in(:locationValues) ");
	        }else if(locationTypeId == 3l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.district.districtId in(:locationValues) ");
	        }else if(locationTypeId == 10l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.parliamentConstituency.constituencyId in(:locationValues) ");
	        }else if(locationTypeId == 5l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.tehsil.tehsilId in(:locationValues) ");
	        }else if(locationTypeId == 6l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.panchayat.panchayatId in(:locationValues) ");
	        }else if(locationTypeId == 7l){
	        	sb.append(" and nominatedPost.nominationPostCandidate.address.localElectionBody.localElectionBodyId in(:locationValues) ");	        
	    }else if(locationTypeId == 8l){
        	sb.append(" and nominatedPost.nominationPostCandidate.address.ward.constituencyId in(:locationValues) ");	        
      }
	        
	 }
	 sb.append(" and nominatedPost.nominatedPostStatus.nominatedPostStatusId in (3,4) ");
	 if(startDate != null && endDate != null){
		 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
	 }
	 if(year != null && !year.trim().isEmpty()){
		 sb.append(" and year(nominatedPost.updatedTime) = :year ");   
	 }
	 sb.append(" group by nominatedPost.nominatedPostMember.boardLevel.boardLevelId ");
	 sb.append(" order by nominatedPost.nominatedPostMember.boardLevel.boardLevelId ");
	 
	 Query query = getSession().createQuery(sb.toString());
	 
	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
		 	if(locationTypeId == 2l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 4l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 3l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 10l){
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
	 return query.list();
 }
   public List<Long> getNominatedPostIdByMemberId(Long nominatedPostMemberId) {
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append("select model.nominatedPostId from NominatedPost model " +
	   		           " where model.nominationPostCandidateId is null " +
	   		           " and model.nominatedPostStatusId=1 " +
	   		           " and model.isDeleted='N' and model.isExpired='N' " +
	   		           " and model.nominatedPostMemberId=:nominatedPostMemberId  ");
	    Query query = getSession().createQuery(queryStr.toString());
	    query.setParameter("nominatedPostMemberId", nominatedPostMemberId);
	    return query.list();
	   
  }
   
   public List<Object[]> getNominatedPostStatusByPositionId(List<Long> memberIds){
	   
	   StringBuilder queryStr = new StringBuilder();
	   
	    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostPosition.position.positionId," +
	    		        " count(model.nominatedPostMember.nominatedPostPosition.position.positionId)," +
	    		        " model.nominatedPostMember.nominatedPostMemberId " +
	    		        " from NominatedPost " +
	    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " +
    		       		"  and model.isDeleted='N' and model.nominationPostCandidateId is null and model.nominatedPostStatus.nominatedPostStatusId = 1 ");
	    		        
	    if(memberIds != null && memberIds.size() > 0l){
	    	queryStr.append(" and model.nominatedPostMember.nominatedPostMemberId in (:memberIds)");
	    }
	   
	    queryStr.append(" group by model.nominatedPostMember.nominatedPostPosition.position.positionId");
	    
	    Query query = getSession().createQuery(queryStr.toString());
	    if(memberIds != null && memberIds.size() > 0l)
	    	query.setParameterList("memberIds", memberIds);
		 	
	    return query.list();
 }
   public List<Long> getMemberIds(Long departmentId,Long boardId,Long boardLevelId,Long searchLevelValue,Long searchlevelId,Long applicationId){
	   
	   StringBuilder queryStr = new StringBuilder();
	   
	    queryStr.append(" select distinct model.nominatedPostMember.nominatedPostMemberId from NominatedPost " +
	    		        " model where model.nominatedPostMember.nominatedPostPosition.isDeleted='N' and model.nominatedPostMember.isDeleted='N'  " +
    		       		"  and model.isDeleted='N' and model.nominationPostCandidateId is null ");
	    		        
	    
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
   public List<Object[]> getMemberStatusDetails(List<Long> nominatedPostCandidateIds){
	   StringBuilder sb = new StringBuilder();
	   sb.append("select distinct model.nominatedPostId,model.nominationPostCandidate.nominationPostCandidateId,model.nominatedPostStatus.nominatedPostStatusId," +
		   		" model.nominatedPostStatus.status," +//3
		   		" model.nominatedPostMember.nominatedPostPosition.position.positionId," +//4
		   		" model.nominatedPostMember.nominatedPostPosition.position.positionName," +//5
		   		" model.nominatedPostMember.nominatedPostPosition.departments.deptName," +//6
		   		" model.nominatedPostMember.nominatedPostPosition.board.boardName," +//7
		   		" model.nominatedPostMember.boardLevel.boardLevelId," +//8
		   		" model.nominatedPostMember.boardLevel.level," +//9
		   		" state.stateName," +//10
		   		" district.districtName," +//11
		   		" constituency.name," +//12
		   		" tehsil.tehsilName," +//13
		   		" localElectionBody.name," +//14
		   		" panchayat.panchayatName," +//15
		   		" ward.name" +//16
		   		" from NominatedPost model" +
		   		" left join model.nominatedPostMember.address.state state " +
		   		" left join model.nominatedPostMember.address.district district" +
		   		" left join model.nominatedPostMember.address.constituency constituency" +
		   		" left join model.nominatedPostMember.address.tehsil tehsil" +
		   		" left join model.nominatedPostMember.address.localElectionBody localElectionBody" +
		   		" left join model.nominatedPostMember.address.panchayat panchayat" +
		   		" left join model.nominatedPostMember.address.ward ward" +
		   		" where model.isDeleted = 'N' and model.isExpired = 'N'");
	   
	   if(nominatedPostCandidateIds != null && nominatedPostCandidateIds.size() > 0l){
		   sb.append(" and model.nominationPostCandidate.nominationPostCandidateId in (:nominatedPostCandidateIds)");
	   }
	   
	   Query query = getSession().createQuery(sb.toString());
	   if(nominatedPostCandidateIds != null && nominatedPostCandidateIds.size() > 0l)
		   query.setParameterList("nominatedPostCandidateIds", nominatedPostCandidateIds);
	   
	   return query.list();
   }
   
	public List<Object[]> getAllNominatedStatusList(){
		Query query = getSession().createQuery("select model.nominatedPostStatusId,model.status from NominatedPostStatus model ");
		return query.list();
	}

	public List<Object[]> getLocationWiseNominatedPostAnalysisDetails(List<Long> locationValues, Long boardLevelId,Long searchLevelId,String type,List<Long> statusIds,Date startDate,Date endDate,String year){
		
			StringBuilder builder =new StringBuilder();
			builder.append(" select count(model.nominatedPostId),model.nominatedPostMember.nominatedPostPosition.position.positionId," +
					"model.nominatedPostMember.nominatedPostPosition.position.positionName," );
		
		if(type != null && type.equalsIgnoreCase("casteGroup")){
			builder.append(" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
					"model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName ,");
		}else if(type != null && type.equalsIgnoreCase("subCaste")){
			builder.append(" model.nominationPostCandidate.casteState.caste.casteId," +
						" model.nominationPostCandidate.casteState.caste.casteName, ");
		}else if(type != null && type.equalsIgnoreCase("ageGroup")){
			builder.append("model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
					" model.nominationPostCandidate.nominatedPostAgeRange.ageRange, ");
		}else if(type != null && type.equalsIgnoreCase("mandal")){
			if((searchLevelId.longValue() == 1L))
				builder.append("  state.stateId, state.stateName,");
			else if((searchLevelId.longValue() == 2L) )
				builder.append(" district.districtId ,district.districtName, ");
			else if(searchLevelId.longValue() ==3L || searchLevelId.longValue() == 10l)
				builder.append("  constituency.constituencyId,constituency.name, ");
			else if(searchLevelId.longValue() ==4L )
				builder.append(" tehsil.tehsilId,tehsil.tehsilName, ");
			else if(searchLevelId.longValue() ==5L || searchLevelId.longValue() ==6L)
				builder.append(" panchayat.panchayatId,panchayat.panchayatName, ");
			else if(searchLevelId.longValue() ==7L || searchLevelId.longValue() ==8L)
				builder.append("  ward.constituencyId,ward.name, ");
		}else if(type != null && type.equalsIgnoreCase("muncipality")){
			 if(searchLevelId.longValue() ==4L )
				builder.append(" localElectionBody.localElectionBodyId,localElectionBody.name, ");
		}
		builder.append(" model.nominationPostCandidate.gender ");
				builder.append(" from NominatedPost model " +
						" left join  model.nominatedPostMember.address.state state " +
						" left join  model.nominatedPostMember.address.district district" +
						"  left join  model.nominatedPostMember.address.constituency constituency" +
						"  left join  model.nominatedPostMember.address.tehsil tehsil" +
						" left join model.nominatedPostMember.address.localElectionBody localElectionBody " +
						" left join model.nominatedPostMember.address.panchayat panchayat" +
						"  left join  model.nominatedPostMember.address.ward ward" +
						" left join model.nominatedPostMember.address.parliamentConstituency parliamentConstituency ");
				
				/*if(type != null && type.equalsIgnoreCase("mandal")){
					builder.append("  left join model.nominatedPostMember.address.tehsil tehsil " );
				}else if(type != null && type.equalsIgnoreCase("townDiv")){
					builder.append(" left join model.nominatedPostMember.address.localElectionBody localElectionBody ");
				}*/
				builder.append(" where model.isDeleted = 'N' and model.isExpired = 'N' and model.nominatedPostMember.isDeleted = 'N' " );
				if(searchLevelId != null && searchLevelId.longValue()>0L){
					if((searchLevelId.longValue() == 1L))
						builder.append(" and model.nominatedPostMember.address.country.countryId  = 1 ");
					/*else if((searchLevelId.longValue() == 2L) && locationValues != null && locationValues.size()>0)
						builder.append(" and state.stateId  in (:locationValue) ");*/
					else if(searchLevelId.longValue() ==3L && locationValues != null && locationValues.size()>0)
						builder.append(" and district.districtId in (:locationValue) ");
					else if(searchLevelId.longValue() ==4L  && locationValues != null && locationValues.size()>0)
						builder.append(" and constituency.constituencyId in (:locationValue) ");
					else if(searchLevelId.longValue() ==10L  && locationValues != null && locationValues.size()>0)
						builder.append(" and parliamentConstituency.constituencyId in (:locationValue) ");
					else if(searchLevelId.longValue() ==5L  && locationValues != null && locationValues.size()>0)
						builder.append(" and tehsil.tehsilId in (:locationValue) ");
					else if(searchLevelId.longValue() ==6L  && locationValues != null && locationValues.size()>0)
						builder.append(" and panchayat.panchayatId in (:locationValue) ");
					else if(searchLevelId.longValue() ==7L  && locationValues != null && locationValues.size()>0)
						builder.append(" and localElectionBody.localElectionBodyId in (:locationValue) ");
					else if(searchLevelId.longValue() ==8L  && locationValues != null && locationValues.size()>0)
						builder.append(" and ward.constituencyId in (:locationValue) ");
				}

               if(startDate != null && endDate != null){
            	   builder.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
		 	  }
		 	  if(year != null && !year.trim().isEmpty()){
		 		  builder.append(" and year(model.updatedTime) = :year ");   
		 	   } 	 
		 	  
				if(boardLevelId != null && boardLevelId.longValue() > 0L){
			     	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
			     		  builder.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
			     	   else if(boardLevelId.longValue() ==5L)
			     		  builder.append(" and model.nominatedPostMember.boardLevelId in (5,6) ");
			     	  else if(boardLevelId.longValue() ==7L)
			     		 builder.append(" and model.nominatedPostMember.boardLevelId in (7,8) ");
				      }
				if(statusIds != null && statusIds.size() >0){
					builder.append(" and model.nominatedPostStatus.nominatedPostStatusId in (:statusIds) ");
				}
				builder.append(" group by model.nominatedPostMember.nominatedPostPosition.position.positionId,model.nominationPostCandidate.gender ");
				if(type != null && type.equalsIgnoreCase("casteGroup")){
					builder.append(" ,model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
				}else if(type != null && type.equalsIgnoreCase("subCaste")){
					builder.append(" ,model.nominationPostCandidate.casteState.caste.casteId ");
				}else if(type != null && type.equalsIgnoreCase("ageGroup")){
					builder.append(" ,model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId ");
				}else if(type != null && type.equalsIgnoreCase("mandal")){
					/*if((searchLevelId.longValue() == 1L))
						builder.append("  ,state.stateId ");
					else*/ if((searchLevelId.longValue() == 2L) )
						builder.append(", district.districtId ");
					else if(searchLevelId.longValue() ==3L || searchLevelId.longValue() == 10l)
						builder.append("  ,constituency.constituencyId ");
					else if(searchLevelId.longValue() ==4L )
						builder.append(" ,tehsil.tehsilId ");
					else if(searchLevelId.longValue() ==5L || searchLevelId.longValue() ==6L)
						builder.append(", panchayat.panchayatId ");
					else if(searchLevelId.longValue() ==7L || searchLevelId.longValue() ==8L)
						builder.append(" ,ward.constituencyId  ");
				}else if(type != null && type.equalsIgnoreCase("muncipality")){
					 if(searchLevelId.longValue() ==4L )
							builder.append(" ,localElectionBody.localElectionBodyId ");
					}
				Query query = getSession().createQuery(builder.toString());
				if(year !=null && !year.trim().isEmpty()){
			 		query.setParameter("year", Integer.parseInt(year));
			 	 }	 
				
				 if(startDate != null && endDate != null){
					query.setDate("startDate", startDate);
					query.setDate("endDate", endDate);
				}
				
				if(searchLevelId != null && searchLevelId.longValue()>0L && locationValues != null && locationValues.size()>0 && searchLevelId.longValue() != 2L){
					query.setParameterList("locationValue", locationValues);
				}
				if(statusIds != null && statusIds.size() >0){
					query.setParameterList("statusIds", statusIds);
				}
				if(boardLevelId != null && boardLevelId.longValue() > 0L){
			     	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
			     		  query.setParameter("boardLevelId", boardLevelId);
				}
		
		return query.list();
		
	}
	@Override
	public List<Object[]> getAllNominatedStatusListLevelWise(List<Long> boardLevelIds,	List<Long> levelValues,Long levelId,Date startDate,Date endDate,String year) {
		
		 
		   StringBuilder sb = new StringBuilder();
		   
		    sb.append("select count(model.nominatedPostId),model.nominatedPostStatusId,model.nominatedPostMember.boardLevelId from  NominatedPost model  where model.isDeleted ='N' and model.isExpired = 'N' and model.nominatedPostMember.isDeleted='N'"); 		        

		    if (levelId != null && levelValues != null && levelValues.size()>0) {
		        if (levelId.longValue() == 2L) {
		          //sb.append(" and model.nominatedPostMember.address.state.stateId in(:levelValues) ");
		          //sb.append(" and model.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		        } else if (levelId.longValue() == 3L) {
		         sb.append(" and model.nominatedPostMember.address.district.districtId in(:levelValues) ");
		       } else if (levelId.longValue() == 10L) {
		         sb.append(" and model.nominatedPostMember.address.constituency.constituencyId in(:levelValues) ");
		       } else if (levelId.longValue() == 4L) {
		         sb.append(" and model.nominatedPostMember.address.constituency.constituencyId in(:levelValues) ");
		       } else if (levelId.longValue() == 5L) {
		         sb.append(" and model.nominatedPostMember.address.tehsil.tehsilId in(:levelValues) ");
		       }else if (levelId.longValue() == 6L) {
		         sb.append(" and model.nominatedPostMember.address.panchayat.panchayatId in(:levelValues) ");
		       }else if (levelId.longValue() == 7L) {
		         sb.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:levelValues) ");
		       }else if (levelId.longValue() == 8L) {
		         sb.append(" and model.nominatedPostMember.address.ward.constituencyId in(:levelValues) ");
		     }    
		     }
		    if(startDate != null && endDate != null){
		 		 sb.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
		 	 }
		 	 if(year != null && !year.trim().isEmpty()){
		 		 sb.append(" and year(model.updatedTime) = :year ");   
		 	 }
		    
			 if(boardLevelIds !=  null && boardLevelIds.size() > 0){
        		   sb.append(" and model.nominatedPostMember.boardLevelId  in (:boardLevelIds) ");
			 }
        	 	     
		     sb.append(" group by model.nominatedPostStatusId, model.nominatedPostMember.boardLevelId");
		     
		     Query query = getSession().createQuery(sb.toString());

				if(startDate != null && endDate != null){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			   }
			
			 if(year !=null && !year.trim().isEmpty()){
		 		query.setParameter("year", Integer.parseInt(year));
		 	  }
		     
		     if(levelId != null && levelId.longValue() > 0l && levelValues != null && levelValues.size() > 0 && levelId.longValue() != 2L){
			           	query.setParameterList("levelValues", levelValues);
			       
			    } 
		     if(boardLevelIds !=  null && boardLevelIds.size() > 0){
		    	 query.setParameterList("boardLevelIds", boardLevelIds);
		     }
		     
		       return query.list();
	       }

	public List<Object[]> getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(List<Long> locationValues,Long searchLevelId,List<Long> statusIdsList,String type,Date startDate,Date endDate,String year){
		
		StringBuilder builder =new StringBuilder();
		if(type != null && type.equalsIgnoreCase("ageRange")){
		 builder.append(" select count( model.nominationPostCandidateId),model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId," +
		 		" model.nominationPostCandidate.nominatedPostAgeRange.ageRange " );
		}else if(type != null && type.equalsIgnoreCase("casteCategory")){
		 builder.append(" select count(  model.nominationPostCandidateId),model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
		 		" model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.categoryName " );
		}
		   builder.append(" from NominatedPost model  ");
			builder.append(" where model.isDeleted = 'N' and model.isExpired = 'N' and model.nominationPostCandidate.isDeleted = 'N' " );
			if (searchLevelId != null && locationValues != null && locationValues.size()>0) {
		        if (searchLevelId == 2) {
		        	//builder.append(" and model.nominatedPostMember.address.state.stateId in(:locationValues) ");
		        	//builder.append(" and model.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		        } else if (searchLevelId == 3) {
		        	builder.append(" and model.nominatedPostMember.address.district.districtId in(:locationValues) ");
		       } else if (searchLevelId == 10) {
		    	   builder.append(" and model.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValues) ");
		       } else if (searchLevelId == 4) {
		    	   builder.append(" and model.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
		       } else if (searchLevelId == 5) {
		    	   builder.append(" and model.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
		       }else if (searchLevelId == 6) {
		    	   builder.append(" and model.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
		       }else if (searchLevelId == 7) {
		    	   builder.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");
		       }else if (searchLevelId == 8) {
		    	   builder.append(" and model.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");
		     }    
		     }

             if(startDate != null && endDate != null){
            	 builder.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
		 	 }
		 	 if(year != null && !year.trim().isEmpty()){
		 		builder.append(" and year(model.updatedTime) = :year ");   
		 	 }
			 
			
			if(statusIdsList != null && statusIdsList.size()>0){
				builder.append(" and model.nominatedPostStatusId in(:statusIdsList)");
			}
			if(type != null && type.equalsIgnoreCase("ageRange")){
			  builder.append(" group by model.nominationPostCandidate.nominatedPostAgeRange.nominatedPostAgeRangeId ");
			}else if(type != null && type.equalsIgnoreCase("casteCategory")){
			 builder.append(" group by model.nominationPostCandidate.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
			}
			Query query = getSession().createQuery(builder.toString());

			if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		    }
		
		    if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	     }
			
			if(searchLevelId != null && searchLevelId.longValue()>0L && locationValues != null && locationValues.size()>0 && searchLevelId != 2){
				query.setParameterList("locationValues", locationValues);
			}
			if(statusIdsList != null && statusIdsList.size()>0){
				query.setParameterList("statusIdsList", statusIdsList);
			}
	return query.list();

	}
	public List<Object[]> getAreaWiseDashboardCandidatesCountView(List<Long> locationValues,Long searchLevelId,List<Long> statusIds,String type,Date startDate,Date endDate,String year){
		  
		  StringBuilder builder =new StringBuilder();
		  builder.append(" select count(distinct model.nominationPostCandidate.nominationPostCandidateId),model.nominatedPostMember.boardLevel.boardLevelId," +
		  		"model.nominatedPostMember.boardLevel.level,");
		  
		  if(type != null && type.equalsIgnoreCase("muncipality")){
				 if(searchLevelId.longValue() ==4L )
					builder.append(" localElectionBody.localElectionBodyId,localElectionBody.name, ");
			}else{
				if((searchLevelId.longValue() == 1L))
					builder.append("  state.stateId, state.stateName,");
				else if((searchLevelId.longValue() == 2L) )
					builder.append(" district.districtId ,district.districtName, ");
				else if(searchLevelId.longValue() ==3L || searchLevelId.longValue() == 10l)
					builder.append("  constituency.constituencyId,constituency.name, ");
				else if(searchLevelId.longValue() ==4L )
					builder.append(" tehsil.tehsilId,tehsil.tehsilName, ");
				else if(searchLevelId.longValue() ==5L || searchLevelId.longValue() ==6L)
					builder.append(" panchayat.panchayatId,panchayat.panchayatName, ");
				else if(searchLevelId.longValue() ==7L || searchLevelId.longValue() ==8L)
					builder.append("  ward.constituencyId,ward.name, ");
			}
		   builder.append(" model.nominationPostCandidate.gender ");
		    builder.append(" from NominatedPost model " +
		    		"	left join  model.nominatedPostMember.address.state state " +
						" left join  model.nominatedPostMember.address.district district" +
						"  left join  model.nominatedPostMember.address.constituency constituency" +
						"  left join  model.nominatedPostMember.address.tehsil tehsil" +
						" left join model.nominatedPostMember.address.localElectionBody localElectionBody " +
						" left join model.nominatedPostMember.address.panchayat panchayat" +
						"  left join  model.nominatedPostMember.address.ward ward" +
						" left join model.nominatedPostMember.address.parliamentConstituency parliamentConstituency ");
		    
		    builder.append(" where model.isDeleted = 'N' and model.isExpired = 'N' and model.nominatedPostMember.isDeleted = 'N' " );
		    if (searchLevelId != null && locationValues != null && locationValues.size()>0) {
		        if (searchLevelId == 2) {
		        	//builder.append(" and state.stateId in(:locationValues) ");
		        	//builder.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		        } else if (searchLevelId == 3) {
		        	builder.append(" and district.districtId in(:locationValues) ");
		       } else if (searchLevelId == 10) {
		    	   builder.append(" and parliamentConstituency.constituencyId in(:locationValues) ");
		       } else if (searchLevelId == 4) {
		    	   builder.append(" and constituency.constituencyId in(:locationValues) ");
		       } else if (searchLevelId == 5) {
		    	   builder.append(" and tehsil.tehsilId in(:locationValues) ");
		       }else if (searchLevelId == 6) {
		    	   builder.append(" and panchayat.panchayatId in(:locationValues) ");
		       }else if (searchLevelId == 7) {
		    	   builder.append(" and localElectionBody.localElectionBodyId in(:locationValues) ");
		       }else if (searchLevelId == 8) {
		    	   builder.append(" and ward.constituencyId in(:locationValues) ");
		     }    
		     }
		    if(startDate != null && endDate != null){
		    	builder.append(" and (date(model.updatedTime) between :startDate and :endDate) ");
		 	 }
		 	 if(year != null && !year.trim().isEmpty()){
		 		builder.append(" and year(model.updatedTime) = :year ");   
		 	 }
		    if(statusIds != null && statusIds.size() >0){
				builder.append(" and model.nominatedPostStatus.nominatedPostStatusId in (:statusIds) ");
			}
		    
		    builder.append(" group by " );
		    if(type != null && type.equalsIgnoreCase("muncipality")){
				 if(searchLevelId.longValue() ==4L )
					builder.append(" localElectionBody.localElectionBodyId, ");
			}else{
			    if((searchLevelId.longValue() == 1L))
					builder.append("  state.stateId, ");
				else if((searchLevelId.longValue() == 2L) )
					builder.append(" district.districtId ,");
				else if(searchLevelId.longValue() ==3L || searchLevelId.longValue() == 10l)
					builder.append("  constituency.constituencyId ,");
				else if(searchLevelId.longValue() ==4L )
					builder.append(" tehsil.tehsilId, ");
				else if(searchLevelId.longValue() ==5L || searchLevelId.longValue() ==6L)
					builder.append(" panchayat.panchayatId ,");
				else if(searchLevelId.longValue() ==7L || searchLevelId.longValue() ==8L)
					builder.append(" ward.constituencyId,  ");
			}
		    builder.append(" model.nominatedPostMember.boardLevelId,model.nominationPostCandidate.gender ");
		    Query query = getSession().createQuery(builder.toString());
		    if(startDate != null && endDate != null){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
			
			if(year !=null && !year.trim().isEmpty()){
		 		query.setParameter("year", Integer.parseInt(year));
		 	 }
		    if(searchLevelId != null && searchLevelId.longValue()>0L && locationValues != null && locationValues.size()>0 && searchLevelId.longValue() != 2l){
		      query.setParameterList("locationValues", locationValues);
		    }
		    if(statusIds != null && statusIds.size() >0){
				query.setParameterList("statusIds", statusIds);
			}
		return query.list();

		}
	
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getNominatedPositionWiseCandidates(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,Long startIndex,Long endIndex){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select" +
	 	 		   " nominationPostCandidate.nominationPostCandidateId,nominationPostCandidate.candidateName," +
	 	 		   " nominationPostCandidate.imageurl," +
	 	 		   "  tdpCadre.tdpCadreId," +
	 	 		   " tdpCadre.memberShipNo, " +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionName," +
	 	 		   " nominatedPost.nominatedPostStatus.nominatedPostStatusId," +
	 	 		   " nominatedPost.nominatedPostStatus.status," +
	 	 		   "  district.districtId," +
	 	 		   "  district.districtName " +
	 	 		   " from NominatedPost nominatedPost " +
	 	 		   " left join nominatedPost.nominationPostCandidate nominationPostCandidate" +
	 	 		   " left join nominationPostCandidate.tdpCadre tdpCadre  " +
	 	 		   " left join nominatedPost.nominatedPostMember nominatedPostMember " +
	 	 		   " left join nominatedPostMember.address address " +
	 	 		   " left join address.district  district where " +
	 			  // " nominatedPost.nominatedPostMember.address.constituency.constituencyId = :constituencyId " +
	 			   " nominatedPost.isDeleted = 'N' " +
	 			   " and nominatedPost.isExpired = 'N' " +
	 			   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 		 	if(locationTypeId == 2l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.state.stateId in(:locationValues) ");
	 	        }else if(locationTypeId == 4l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	sb.append(" and nominatedPost.nominationPostCandidate.address.localElectionBody.localElectionBodyId in(:locationValues) ");	        
	 	    }else if(locationTypeId == 8l){
	         	sb.append(" and nominatedPost.nominationPostCandidate.address.ward.constituencyId in(:locationValues) ");	        
	       }
	 	        
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(nominatedPost.updatedTime) = :year ");   
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
     	   if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
     		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
     	   else if(boardLevelId.longValue() ==5L)
     		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId in (5,6) ");
     	  else if(boardLevelId.longValue() ==7L)
     		  sb.append(" and nominatedPost.nominatedPostMember.boardLevelId in (7,8) ");
	      }
	 	// sb.append(" group by nominatedPost.nominatedPostMember.boardLevel.boardLevelId ");
	 	 //sb.append(" order by nominatedPost.nominatedPostMember.boardLevel.boardLevelId ");
	 	 
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
	 	 if( startIndex != null && startIndex.longValue()>0l){
	 		query.setFirstResult(startIndex.intValue());
	 	 }
	 	if( endIndex != null && endIndex.longValue()>0l){
	 		query.setMaxResults(endIndex.intValue());
	 	}
	 	 if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	if(boardLevelId.longValue() !=5L && boardLevelId.longValue() !=7L)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	 return query.list();
	  }
	//locationValues,startDate,endDate,locationTypeId,boardLevelId
		 public List<Object[]> getNominatedPostLocationStatusWiseCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId){
			 StringBuilder sb = new StringBuilder();
			 sb.append(" select " +
			 		   " nominatedPost.nominatedPostStatus.nominatedPostStatusId, " +
			 		   " nominatedPost.nominatedPostStatus.status, " +
			 		   " count(distinct nominatedPost.nominatedPostId),nominatedPost.nominatedPostMember.boardLevel.boardLevelId,nominatedPost.nominatedPostMember.boardLevel.level " +
			 		   " from NominatedPost nominatedPost where " +
					   " nominatedPost.isDeleted = 'N' " +
					   " and nominatedPost.isExpired = 'N' " +
					   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
			 
			 	if (locationType != null && locationValuesList != null && locationValuesList.size()>0) {
			 			/*if (locationType == 2) {
			 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValue) ");
			 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			 			} else*/ if (locationType == 3) {
							sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValue) ");
						} else if (locationType == 10) {
							sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
						} else if (locationType == 4) {
							sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValue) ");
						} else if (locationType == 5) {
							sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValue) ");
						}else if (locationType == 6) {
							sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValue) ");
						}else if (locationType == 7) {
							sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValue) ");
						}else if (locationType == 8) {
							sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValue) ");
						}		
						
			   }
			 if(startDate != null && endDate != null){
				 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
			 }
			 if(boardLevelId != null && boardLevelId.longValue()>0l){
				 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId >=:boardLevelId ");
			 }
			 sb.append(" group by nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPost.nominatedPostMember.boardLevel.boardLevelId "); 
			 sb.append(" order by nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
			 Query query = getSession().createQuery(sb.toString());
			 if (locationType != null && locationValuesList != null && locationValuesList.size()>0 && locationType !=2l) {
			   query.setParameterList("locationValue",locationValuesList);
			 }
			// query.setParameterList("locationValue",locationValuesList);
			 if(startDate != null && endDate != null){
				 query.setDate("startDate",startDate);
				 query.setDate("endDate",endDate);
			 }
			 if(boardLevelId != null && boardLevelId.longValue()>0l){
				 query.setParameter("boardLevelId", boardLevelId);
			 }
			 return query.list();
		 }
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getDepartmentWisePostDetails(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,Long deptId){
	 	 StringBuilder sb = new StringBuilder();
	 	 sb.append(" select ");
	 	if(deptId != null && deptId.longValue() >0l){
	 		sb.append("  nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +
	 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," );
	 	}else {
	 		sb.append(" nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		 	 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName," );
	 	}
	 	 		   
	 	sb.append(" nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPost.nominatedPostStatus.status" +
	 	 		   " ,count(nominatedPost.nominatedPostId) " +
	 	 		   " from NominatedPost nominatedPost  " +
	 	 		   " where nominatedPost.isDeleted = 'N' " +
	 			   " and nominatedPost.isExpired = 'N' " +
	 			   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
	 	 
	 	if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
 			/*if (locationTypeId == 2) {
 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValues) ");
 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
 			} else*/ if (locationTypeId == 3l) {
				sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValues) ");
			} else if (locationTypeId == 10) {
				sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 4) {
				sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValues) ");
			} else if (locationTypeId == 5) {
				sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValues) ");
			}else if (locationTypeId == 6) {
				sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValues) ");
			}else if (locationTypeId == 7) {
				sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValues) ");
			}else if (locationTypeId == 8) {
				sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValues) ");
			}		
		}
	 	
	 	if(deptId != null && deptId.longValue() >0l){
	 		 sb.append(" and nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId = :deptId  ");
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
	 	 }
	 	 if(year != null && !year.trim().isEmpty()){
	 		 sb.append(" and year(nominatedPost.updatedTime) = :year ");   
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L){
	 		 sb.append(" and nominatedPost.nominatedPostMember.boardLevelId =:boardLevelId ");
	      }
	 	sb.append(" group by ");
	 	if(deptId != null && deptId.longValue() >0l){
	 		sb.append(" nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId ,");
	 	}else{
	 		sb.append(" nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId ,");
	 	}
	 	 sb.append(" nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
	 	 sb.append(" order by nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName ");
	 	 
	 	 Query query = getSession().createQuery(sb.toString());
	 	 
	 	 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 		  query.setParameterList("locationValues", locationValues);
	 	  }
	 	
	 	 if(year !=null && !year.trim().isEmpty()){
	 		query.setParameter("year", Integer.parseInt(year));
	 	 }
	 	 if(startDate != null && endDate != null){
	 		 query.setDate("startDate",startDate);
	 		 query.setDate("endDate",endDate);
	 	 }
	 	if(boardLevelId != null && boardLevelId.longValue() > 0L)
	 		query.setParameter("boardLevelId", boardLevelId);
	 	if(deptId != null && deptId.longValue() >0l)
	 		query.setParameter("deptId", deptId);
	 	 return query.list();
	  }
	 public List<Object[]> getNominatedPostLocationStatusBasedWiseCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select " +
		 		   " nominatedPost.nominatedPostStatus.nominatedPostStatusId, " +
		 		   " nominatedPost.nominatedPostStatus.status, " +
		 		   " count(distinct nominatedPost.nominatedPostId),nominatedPost.nominatedPostMember.boardLevel.boardLevelId,nominatedPost.nominatedPostMember.boardLevel.level " +
		 		   " from NominatedPost nominatedPost where " +
				   " nominatedPost.isDeleted = 'N' " +
				   " and nominatedPost.isExpired = 'N' " +
				   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
		 
		 	if (locationType != null && locationValuesList != null && locationValuesList.size()>0) {
		 			/*if (locationType == 2) {
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValue) ");
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		 			} else*/ if (locationType == 3) {
						sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValue) ");
					} else if (locationType == 10) {
						sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
					} else if (locationType == 4) {
						sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValue) ");
					} else if (locationType == 5) {
						sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValue) ");
					}else if (locationType == 6) {
						sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValue) ");
					}else if (locationType == 7) {
						sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValue) ");
					}else if (locationType == 8) {
						sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValue) ");
					}		
					
		   }
		 if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
		 }
		 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId  not in(1,7) ");
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId >=:boardLevelId ");
		 }
		 sb.append(" group by nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPost.nominatedPostMember.boardLevel.boardLevelId "); 
		 sb.append(" order by nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
		 Query query = getSession().createQuery(sb.toString());
		 if (locationType != null && locationValuesList != null && locationValuesList.size()>0 && locationType !=2l) {
		   query.setParameterList("locationValue",locationValuesList);
		 }
		// query.setParameterList("locationValue",locationValuesList);
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 query.setParameter("boardLevelId", boardLevelId);
		 }
		 return query.list();
	 }
	 public List<Object[]> getNominatedPostLocationWiseBoardLevelCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select  " );
		 if(locationType == 3){
		   sb.append(" nominatedPost.nominatedPostMember.address.district.districtId,nominatedPost.nominatedPostMember.address.district.districtName," );
		 }else if(locationType == 4){
			 sb.append(" nominatedPost.nominatedPostMember.address.constituency.constituencyId,nominatedPost.nominatedPostMember.address.constituency.name ");
		 }else if(locationType == 5){
			 sb.append(" nominatedPost.nominatedPostMember.address.tehsil.tehsilId,nominatedPost.nominatedPostMember.address.tehsil.tehsilName ");
		 }else if(locationType == 10){
			 sb.append(" nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId,nominatedPost.nominatedPostMember.address.parliamentConstituency.name, ");
		 }
		 sb.append(" nominatedPost.nominatedPostStatus.nominatedPostStatusId, " +
		 		   " nominatedPost.nominatedPostStatus.status, " +
		 		   " count(distinct nominatedPost.nominatedPostId),nominatedPost.nominatedPostMember.boardLevel.boardLevelId,nominatedPost.nominatedPostMember.boardLevel.level " +
		 		   " from NominatedPost nominatedPost where " +
				   " nominatedPost.isDeleted = 'N' " +
				   " and nominatedPost.isExpired = 'N' " +
				   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
		 
		 	if (locationType != null && locationValuesList != null && locationValuesList.size()>0) {
		 			/*if (locationType == 2) {
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValue) ");
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		 			} else*/ if (locationType == 3) {
						sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValue) ");
					} else if (locationType == 10) {
						sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
					} else if (locationType == 4) {
						sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValue) ");
					} else if (locationType == 5) {
						sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValue) ");
					}else if (locationType == 6) {
						sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValue) ");
					}else if (locationType == 7) {
						sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValue) ");
					}else if (locationType == 8) {
						sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValue) ");
					}		
					
		   }
		 if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
		 }
		 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId  not in(1,2,6,8) ");
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId >=:boardLevelId ");
		 }
		 sb.append(" group by nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPost.nominatedPostMember.boardLevel.boardLevelId "); 
		 if(locationType == 3){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.district.districtId ");
		 }else if(locationType == 4){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.constituency.constituencyId ");
		 }else if(locationType == 5){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.tehsil.tehsilId ");
		 }else if(locationType == 10){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId ");
		 }
		 sb.append(" order by nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
		 Query query = getSession().createQuery(sb.toString());
		 if (locationType != null && locationValuesList != null && locationValuesList.size()>0 && locationType !=2l) {
		   query.setParameterList("locationValue",locationValuesList);
		 }
		// query.setParameterList("locationValue",locationValuesList);
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 query.setParameter("boardLevelId", boardLevelId);
		 }
		 return query.list();
	 }
	 public List<Object[]> getNominatedPostStateWiseCount(Date startDate,Date endDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select nominatedPost.nominatedPostMember.address.state.stateId,nominatedPost.nominatedPostMember.address.state.stateName, " );
		 sb.append(" nominatedPost.nominatedPostStatus.nominatedPostStatusId, " +
		 		   " nominatedPost.nominatedPostStatus.status, " +
		 		   " count(distinct nominatedPost.nominatedPostId) " +
		 		   " from NominatedPost nominatedPost where " +
				   " nominatedPost.isDeleted = 'N' " +
				   " and nominatedPost.isExpired = 'N' " +
				   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
		 if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
		 }
		 sb.append(" and nominatedPost.nominatedPostStatus.nominatedPostStatusId not in(2) ");
		 sb.append(" group by nominatedPost.nominatedPostStatus.nominatedPostStatusId,nominatedPost.nominatedPostMember.address.state.stateId "); 
		 sb.append(" order by nominatedPost.nominatedPostStatus.nominatedPostStatusId ");
		 Query query = getSession().createQuery(sb.toString());
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 return query.list();
	 }
	
	 public List<Object[]> getLocationWiseNominatedPostCount(String levelStr,Long levelId,List<Long> levelValues,Date startDate,Date endDate){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select  " );
		 if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("state")){
			 sb.append(" nominatedPost.nominatedPostMember.address.state.stateId,nominatedPost.nominatedPostMember.address.state.stateName, ");
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("district")){
		   sb.append(" nominatedPost.nominatedPostMember.address.district.districtId,nominatedPost.nominatedPostMember.address.district.districtName," );
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("constituency")){
			 sb.append(" nominatedPost.nominatedPostMember.address.constituency.constituencyId,nominatedPost.nominatedPostMember.address.constituency.name, ");
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("parliament")){
			 sb.append(" nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId,nominatedPost.nominatedPostMember.address.parliamentConstituency.name, ");
		 }
		 sb.append(" count(distinct nominatedPost.nominatedPostId),nominatedPost.nominatedPostMember.boardLevel.boardLevelId,nominatedPost.nominatedPostMember.boardLevel.level " +
		 		   " from NominatedPost nominatedPost where " +
				   " nominatedPost.isDeleted = 'N' " +
				   " and nominatedPost.isExpired = 'N' " +
				   " and nominatedPost.nominatedPostMember.isDeleted = 'N' ");
		 
		 if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty()){
		 			if (levelId.longValue() == 2) {
		 				sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:levelValues) ");
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		 			} else if(levelId.longValue() == 3) {
						sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:levelValues) ");
					} else if (levelId == 10) {
						sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:levelValues) ");
					} else if(levelId.longValue() == 4) {
						sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:levelValues) ");
					} else if (levelId == 5) {
						sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:levelValues) ");
					}else if (levelId == 6) {
						sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:levelValues) ");
					}else if (levelId == 7) {
						sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:levelValues) ");
					}else if (levelId == 8) {
						sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:levelValues) ");
					}		
					
		   }
		 if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
		 }
		 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId  not in(6,8) ");
		 /*if(boardLevelId != null && boardLevelId.longValue()>0l){
			 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId >=:boardLevelId ");
		 }*/
		 sb.append(" group by nominatedPost.nominatedPostMember.boardLevel.boardLevelId "); 
		 if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("state")){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.state.stateId ");
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("district")){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.district.districtId ");
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("constituency")){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.constituency.constituencyId ");
		 }else if(levelStr != null && levelStr.trim().toString().equalsIgnoreCase("parliament")){
			 sb.append(" ,nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId ");
		 }
		 sb.append(" order by nominatedPost.nominatedPostMember.boardLevel.boardLevelId ");
		 Query query = getSession().createQuery(sb.toString());
		 if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty())
				query.setParameterList("levelValues", levelValues);
		// query.setParameterList("locationValue",locationValuesList);
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 return query.list();
	 }
	 //0-departId,1-deptName,2-count,3-boardId,4-boardName,5-positionId,6-positionName
	 public List<Object[]> getDepartMentAndBoardWisePositinsStatusCount(List<Long> locationValuesList,Date startDate,Date endDate,Long locationType,Long boardLevelId,Long statusId){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select  " );
		 sb.append(" nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId , " +
		 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.departments.deptName, " +
		 		   " count(distinct nominatedPost.nominatedPostId),nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId," +
		 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardName," +
		 		   " nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId,nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionName " +
		 		   " from NominatedPost nominatedPost where " +
				   " nominatedPost.isDeleted = 'N' " +
				   " and nominatedPost.isExpired = 'N' " +
				   " and nominatedPost.nominatedPostMember.isDeleted = 'N' " +
				   " and nominatedPost.nominatedPostMember.nominatedPostPosition.isDeleted ='N' ");
		 
		 	if (locationType != null && locationValuesList != null && locationValuesList.size()>0) {
		 			/*if (locationType == 2) {
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.state.stateId in(:locationValue) ");
		 				//sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		 			} else*/ if (locationType.longValue() == 3l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.district.districtId in(:locationValue) ");
					} else if (locationType.longValue() == 10l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.parliamentConstituency.constituencyId in(:locationValue) ");
					} else if (locationType.longValue() == 4l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.constituency.constituencyId in(:locationValue) ");
					} else if (locationType.longValue() == 5l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.tehsil.tehsilId in(:locationValue) ");
					}else if (locationType.longValue() == 6l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.panchayat.panchayatId in(:locationValue) ");
					}else if (locationType.longValue() == 7l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.localElectionBody.localElectionBodyId in(:locationValue) ");
					}else if (locationType.longValue() == 8l) {
						sb.append(" and nominatedPost.nominatedPostMember.address.ward.constituencyId in(:locationValue) ");
					}		
					
		   }
		 if(startDate != null && endDate != null){
			 sb.append(" and (date(nominatedPost.updatedTime) between :startDate and :endDate) ");
		 }
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 sb.append(" and nominatedPost.nominatedPostMember.boardLevel.boardLevelId =:boardLevelId ");
		 }
		 if(statusId != null && statusId.longValue()>0l){
			 sb.append(" and nominatedPost.nominatedPostStatus.nominatedPostStatusId =:statusId ");
		 }
		 sb.append(" group by  nominatedPost.nominatedPostMember.nominatedPostPosition.departments.departmentId," +
		 		" nominatedPost.nominatedPostMember.nominatedPostPosition.board.boardId,nominatedPost.nominatedPostMember.nominatedPostPosition.position.positionId ");
		 Query query = getSession().createQuery(sb.toString());
		 if (locationType != null && locationValuesList != null && locationValuesList.size()>0 ) {
		   query.setParameterList("locationValue",locationValuesList);
		 }
		 if(startDate != null && endDate != null){
			 query.setDate("startDate",startDate);
			 query.setDate("endDate",endDate);
		 }
		 if(boardLevelId != null && boardLevelId.longValue()>0l){
			 query.setParameter("boardLevelId", boardLevelId);
		 }
		 if(statusId != null && statusId.longValue()>0l){
			 query.setParameter("statusId", statusId);
		 }
		 return query.list();
	 }
	 
}
