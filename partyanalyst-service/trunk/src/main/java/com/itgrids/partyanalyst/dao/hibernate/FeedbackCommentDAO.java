package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFeedbackCommentDAO;
import com.itgrids.partyanalyst.model.FeedBackComment;
import com.itgrids.partyanalyst.model.FeedBackTask;

public class FeedbackCommentDAO extends	GenericDaoHibernate<FeedBackComment, Long> implements IFeedbackCommentDAO {

	public FeedbackCommentDAO() {
		super(FeedBackComment.class);
	}
	
}
