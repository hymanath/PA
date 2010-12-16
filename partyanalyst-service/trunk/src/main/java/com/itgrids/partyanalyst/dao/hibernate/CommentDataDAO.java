/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommentDataDAO;
import com.itgrids.partyanalyst.model.CommentData;

public class CommentDataDAO extends GenericDaoHibernate<CommentData, Long> implements
		ICommentDataDAO {

	public CommentDataDAO() {
		super(CommentData.class);
	}

	public Integer updateSetIsApprovedStatusToPostedComments(List<Long> reasonIds, String isApproved)
	{
		Query queryObject = getSession().createQuery("update CommentData model set model.isApproved = ? where model.commentDataId in (:reasonIds)");
		queryObject.setParameter(0, isApproved);
		queryObject.setParameterList("reasonIds", reasonIds);
		return queryObject.executeUpdate();
			
	}
}
