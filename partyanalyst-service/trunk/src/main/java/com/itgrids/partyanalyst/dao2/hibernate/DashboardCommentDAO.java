package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDashboardCommentDAO;
import com.itgrids.partyanalyst.model.DashboardComment;
import com.itgrids.partyanalyst.model.LeaderOccasion;

public class DashboardCommentDAO extends GenericDaoHibernate<DashboardComment, Long> implements IDashboardCommentDAO{

	public DashboardCommentDAO() {
		super(DashboardComment.class);
		
	}
	public List<DashboardComment> getDisplayDashboardComments(Long userId,Long dashBoardComponentId)
	{
		Query query = getSession().createQuery("select model from DashboardComment model" +
				" where model.dashboradComponentId = :dashBoardComponentId" +
				" and model.isDeleted ='N' order by model.insertedTime desc ");
		query.setParameter("dashBoardComponentId", dashBoardComponentId);
		return query.list();
	}
	public int updateDashboardComment(String comment,Long dashboardCommentId){
		Query query = getSession().createQuery(" update DashboardComment model set model.comment = :comment" +
				" where model.dashboardCommentId =:dashBoardComponentId");
		query.setParameter("comment", comment);
		query.setParameter("dashBoardComponentId", dashboardCommentId);
		return query.executeUpdate();
	}
	public DashboardComment deleteDashBoardcomments(Long dashboardCommentId)
	{
		Query query = getSession().createQuery("select  model from DashboardComment model" +
				" where model.isDeleted ='N' and  model.dashboardCommentId = :dashboardCommentId");
		query.setParameter("dashboardCommentId", dashboardCommentId);
		return  (DashboardComment) query.uniqueResult();
	}
	
}