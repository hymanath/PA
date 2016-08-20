package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardCommentDAO;
import com.itgrids.partyanalyst.model.DashboardComment;
import com.itgrids.partyanalyst.model.LeaderOccasion;

public class DashboardCommentDAO extends GenericDaoHibernate<DashboardComment, Long> implements IDashboardCommentDAO{

	public DashboardCommentDAO() {
		super(DashboardComment.class);
		
	}
	
}