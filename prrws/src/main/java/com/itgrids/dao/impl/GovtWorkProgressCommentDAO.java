package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressCommentDAO;
import com.itgrids.model.GovtWorkProgressComment;

@Repository
public class GovtWorkProgressCommentDAO extends GenericDaoHibernate<GovtWorkProgressComment, Long> implements IGovtWorkProgressCommentDAO{

	public GovtWorkProgressCommentDAO() {
		super(GovtWorkProgressComment.class);
	}


}
