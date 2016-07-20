package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.model.NominatedPost;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class NominatedPostDAO extends GenericDaoHibernate<NominatedPost, Long> implements INominatedPostDAO{

	public NominatedPostDAO() {
		super(NominatedPost.class);
	}

	public List<Object[]> getAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.nominatedPostStatus.status, model.nominatedPostStatusId, " +
				" model.nominatedPostMember.boardLevelId, count(model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) " +
				" from NominatedPost model where  " +
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' ");		
		if(new CommonMethodsUtilService().isListOrSetValid(statusList))
			queryStr.append(" and model.nominatedPostStatusId in (:statusList) ");			
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
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
	
	public List<Object[]> getTotalAvaiablePostDetails(Long boardLevelId,Date startDate,Date endDate,List<Long> statusList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select '','', model.nominatedPostMember.boardLevelId, count(distinct model.nominatedPostId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) " +
				" from NominatedPost model where  " +
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' ");		
			
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and model.nominatedPostMember.boardLevelId = :boardLevelId ");		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");		
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
	
	public List<Object[]> getNominatedPostsByBoardsAndDepts(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId){
		StringBuilder str = new StringBuilder();
		str.append("SELECT position.positionId," +
				" position.positionName " +
				"	FROM NominatedPost model left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE model.isDeleted ='N'" +
				" and nominatedPostMember.isDeleted = 'N'" +
				" and nominatedPostPosition.isDeleted = 'N'" );
		
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
		
		return query.list();
		
	}
	
	public List<Object[]> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValue,List<Long> deptId,List<Long> boardId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT position.positionId," +
				" position.positionName,model.nominatedPostStatus.nominatedPostStatusId" +
				",model.nominatedPostStatus.status,count(distinct model.nominatedPostId) " +
				"	FROM NominatedPost model left join model.nominatedPostMember nominatedPostMember " +
				" left join nominatedPostMember.nominatedPostPosition nominatedPostPosition " +
				" left join nominatedPostPosition.position position " +
				"   WHERE model.isDeleted ='N' " +
				" and nominatedPostMember.isDeleted = 'N'" +
				"  and nominatedPostPosition.isDeleted = 'N' " );
		
		if(boardLevelId !=null && boardLevelId>0l){
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
		
		str.append("GROUP BY position.positionId,model.nominatedPostStatus.nominatedPostStatusId " +
				" ORDER BY model.nominatedPostMember.nominatedPostPosition.position.positionId desc ");
		
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
		
		return query.list();		
	}
	
}
