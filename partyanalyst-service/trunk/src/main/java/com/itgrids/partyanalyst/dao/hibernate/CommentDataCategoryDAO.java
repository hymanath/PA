/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 20,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommentDataCategoryDAO;
import com.itgrids.partyanalyst.model.CommentDataCategory;

public class CommentDataCategoryDAO extends GenericDaoHibernate<CommentDataCategory, Long>
		implements ICommentDataCategoryDAO {

	public CommentDataCategoryDAO() {
		super(CommentDataCategory.class);
	}

}
