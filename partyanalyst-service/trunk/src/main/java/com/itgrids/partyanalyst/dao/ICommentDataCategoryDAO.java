/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 20,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommentDataCategory;

public interface ICommentDataCategoryDAO extends GenericDao<CommentDataCategory, Long> {

	@SuppressWarnings("unchecked")
	public List findCommentDataCategoryByType(String type);
	
}
