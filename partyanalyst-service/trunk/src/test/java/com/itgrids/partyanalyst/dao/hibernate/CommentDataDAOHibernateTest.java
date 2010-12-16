/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICommentDataDAO;
import com.itgrids.partyanalyst.model.CommentData;

public class CommentDataDAOHibernateTest extends BaseDaoTestCase {

	private ICommentDataDAO commentDataDAO;

	public void setCommentDataDAO(ICommentDataDAO commentDataDAO) {
		this.commentDataDAO = commentDataDAO;
	}
	/*
	@Test
	public void testGetAllComments(){
		List<CommentData> commentsData = commentDataDAO.getAll();
		if(commentsData != null){
			System.out.println("Size :" + commentsData.size());
		}
	}*/
	
	public void testScrutinePostedComments(){
		
		List<Long>  reasonIds = new ArrayList<Long>();
		reasonIds.add(20L);		
		int status = commentDataDAO.updateSetIsApprovedStatusToPostedComments(reasonIds, "true");
		setComplete();
		System.out.println(status);
		
	}
	
}
