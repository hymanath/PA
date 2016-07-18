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
				" model.nominatedPost.nominatedPostMember.boardLevelId, sum(model.nominatedPostMember.maxMembers), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.departmentId), count(model.nominatedPostMember.nominatedPostPosition.positionId), " +
				" count(distinct model.nominatedPostMember.nominatedPostPosition.boardId) " +
				" from NominatedPost model where  " +
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N'  and " +
				" model.nominatedPostMember.isDeleted ='N' and model.isDeleted='N' ");
		
		if(new CommonMethodsUtilService().isListOrSetValid(statusList))
			queryStr.append(" and model.nominatedPostStatusId in (:statusList) ");
			
		if(boardLevelId != null && boardLevelId.longValue()>0)
			queryStr.append(" and model.nominatedPost.nominatedPostMember.boardLevelId = :boardLevelId ");
		
		if(startDate != null && endDate != null)
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		
		queryStr.append(" group by model.nominatedPostStatusId,model.nominatedPost.nominatedPostMember.boardLevelId order by model.boardLevelId  ");
		
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
}
