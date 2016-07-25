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
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1 && stateId != null)
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
		
		if(boardLevelId != null && boardLevelId.longValue()>1 && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append(" model2.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==2L)
				queryStr.append(" model2.district.districtId between 1 and 10 ");
			else
				queryStr.append(" model2.district.districtId between 1 and 23 ");
		}
		
		queryStr.append(" group by model.nominatedPostStatusId,model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
			
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(new CommonMethodsUtilService().isListOrSetValid(statusList))
			query.setParameterList("statusList", statusList);
		
		return query.list();
	}
	
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList,Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select '','', model.nominatedPostMember.boardLevelId, count(distinct model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) ");
		queryStr.append(" from NominatedPost model   " );
		if(boardLevelId != null && boardLevelId.longValue()>1 && stateId != null)
			queryStr.append(",UserAddress model2 where model.nominatedPostMember.addressId = model2.userAddressId and " );
		else
			queryStr.append(" where ");
		queryStr.append(" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' ");		
			
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
		if(boardLevelId != null && boardLevelId.longValue()>1 && stateId != null){
			if(stateId.longValue() ==1L)
				queryStr.append(" model2.district.districtId between 11 and 23 ");
			else if(stateId.longValue() ==2L)
				queryStr.append(" model2.district.districtId between 1 and 10 ");
			else
				queryStr.append(" model2.district.districtId between 1 and 23 ");
		}
		
		queryStr.append(" group by model.nominatedPostMember.boardLevelId order by model.nominatedPostMember.boardLevelId  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
			
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
	
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType){
		StringBuilder str = new StringBuilder();
		str.append("SELECT position.positionId," +
				" position.positionName " +
				"	FROM NominatedPostApplication model1,NominatedPost model left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE model1.nominationPostCandidate.nominationPostCandidateId = model.nominationPostCandidate.nominationPostCandidateId " +
				" and model.isDeleted ='N'" +
				" and nominatedPostMember.isDeleted = 'N'" +
				" and nominatedPostPosition.isDeleted = 'N' " +
				" and model1.isDeleted = 'N'" +
				" and model1.nominationPostCandidate.isDeleted = 'N' " );
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and nominatedPostMember.locationValue in (:levelValue) ");
		}
		if(deptId !=null && deptId.size() >0){
			str.append(" and nominatedPostPosition.department.departmentId in (:deptId) ");
		}
		if(boardId !=null && boardId.size()>0){
			str.append(" and nominatedPostPosition.board.boardId in (:boardId) ");
		}
		
		str.append(" and  nominatedPostPosition.positionId is not null ");
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model1.applicationStatus.status = :notYet ");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" and model1.applicationStatus.status != :notYet ");
		}
		
		str.append(" GROUP BY position.positionId ORDER BY position.positionId ");
		
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
		
		if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") || statusType.trim().equalsIgnoreCase("running")) ){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}

		return query.list();
		
	}
	
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId,String statusType,String positionType){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT position.positionId," +
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
				" and model1.nominationPostCandidate.isDeleted = 'N' " );
		
		if(boardLevelId !=null && boardLevelId>0l){
			str.append(" and nominatedPostMember.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and nominatedPostMember.locationValue in (:levelValue) ");
		}
		
		// Any Dept && Board && post Scenarios Consideration && non Consideration
		if(positionType !=null && positionType.trim().equalsIgnoreCase("post")){
			if(deptId !=null && deptId.size() >0){
				str.append(" and nominatedPostPosition.department.departmentId in (:deptId) ");
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
		
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model1.applicationStatus.status = :notYet ");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" and model1.applicationStatus.status != :notYet ");
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
		
		if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") || statusType.trim().equalsIgnoreCase("running")) ){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}
		
		return query.list();		
	}
	
}
