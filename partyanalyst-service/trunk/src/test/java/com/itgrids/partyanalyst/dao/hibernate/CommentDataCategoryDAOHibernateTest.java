/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 20,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICommentDataCategoryDAO;
import com.itgrids.partyanalyst.model.CommentDataCategory;

public class CommentDataCategoryDAOHibernateTest extends BaseDaoTestCase {

	private ICommentDataCategoryDAO commentDataCategoryDAO;

	public void setCommentDataCategoryDAO(
			ICommentDataCategoryDAO commentDataCategoryDAO) {
		this.commentDataCategoryDAO = commentDataCategoryDAO;
	}
	
	@Test
	public void testGetAllCategories(){
		List<CommentDataCategory> list = commentDataCategoryDAO.getAll();
		System.out.println("Size :" + list.size());
	}
}
