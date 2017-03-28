package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DashboardComment;
import com.itgrids.partyanalyst.model.LeaderOccasion;

public interface IDashboardCommentDAO  extends GenericDao<DashboardComment, Long>{
	public List<DashboardComment> getDisplayDashboardComments(Long userId,Long dashBoardComponentId);
	public int updateDashboardComment(String comment,Long dashboardCommentId);
	public DashboardComment deleteDashBoardcomments(Long dashboardCommentId);	
}
