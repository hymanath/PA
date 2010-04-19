/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommentDataDAO;
import com.itgrids.partyanalyst.model.CommentData;

public class CommentDataDAO extends GenericDaoHibernate<CommentData, Long> implements
		ICommentDataDAO {

	public CommentDataDAO() {
		super(CommentData.class);
	}

	
}
