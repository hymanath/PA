package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCommentDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalComment;

public class SelfAppraisalCommentDAO extends GenericDaoHibernate<SelfAppraisalComment, Long> implements ISelfAppraisalCommentDAO{

	public SelfAppraisalCommentDAO(){
		super(SelfAppraisalComment.class);
	}
}
