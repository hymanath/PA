package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostCommentDAO;
import com.itgrids.partyanalyst.model.NominatedPostComment;

public class NominatedPostCommentDAO extends GenericDaoHibernate<NominatedPostComment, Long> implements INominatedPostCommentDAO{

	public NominatedPostCommentDAO() {
		super(NominatedPostComment.class);
		// TODO Auto-generated constructor stub
	}

}
