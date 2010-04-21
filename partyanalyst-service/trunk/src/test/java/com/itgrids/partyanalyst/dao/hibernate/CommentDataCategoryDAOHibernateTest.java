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
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	@SuppressWarnings("unchecked")
	public void testGetCommentDataCategoryByType(){
		List listData = commentDataCategoryDAO.findCommentDataCategoryByType(IConstants.CANDIDATE_COMMENTS_LOST);
		if(listData != null && listData.size() > 0){
			for(int i=0;i<listData.size();i++){
			Object[] params = (Object[])listData.get(i);
			Long id = (Long)params[0];
			String type = (String)params[1];
			String basicType = (String)params[2];
			
			System.out.print(" ID :" + id);
			System.out.print(" TYPE :" + type);
			System.out.print(" BASIC TYPE :" + basicType);	
			
			System.out.println("");
			}
		}
	}
}
