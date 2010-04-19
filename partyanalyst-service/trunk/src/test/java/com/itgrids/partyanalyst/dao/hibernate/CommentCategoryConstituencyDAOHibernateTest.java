/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICommentCategoryConstituencyDAO;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;

public class CommentCategoryConstituencyDAOHibernateTest extends BaseDaoTestCase {

	private ICommentCategoryConstituencyDAO commentCategoryConstituencyDAO;

	public void setCommentCategoryConstituencyDAO(
			ICommentCategoryConstituencyDAO commentCategoryConstituencyDAO) {
		this.commentCategoryConstituencyDAO = commentCategoryConstituencyDAO;
	}
	
	@Test
	public void testGetAllConstituencyComments(){
		List<CommentCategoryConstituency> constiComments = commentCategoryConstituencyDAO.getAll();
		if(constiComments != null){
			System.out.println(" Results Size :" + constiComments.size());
			System.out.println(" Constituency :" + constiComments.get(0).getConstituency().getName());
			System.out.println(" Election :" + constiComments.get(0).getElection().getElectionScope().getElectionType().getElectionType());
			System.out.println(" Comment :" + constiComments.get(0).getCommentData().getCommentDesc());
		}
	}
	
	@Test
	public void testGetConstituencyComments(){
		//List<CommentCategoryConstituency> constiComments = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAElection(new Long(10), new Long(232));
		//List<CommentCategoryConstituency> constiComments = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAllElections(new Long(232));
		List<CommentCategoryConstituency> constiComments = commentCategoryConstituencyDAO.getCommentsOnAConstituencyInAElection("2004", new Long(232));
		if(constiComments != null){
			System.out.println(" Results Size :" + constiComments.size());
			for(CommentCategoryConstituency results:constiComments){
				System.out.print(" Constituency :" + results.getConstituency().getName());
				System.out.print(" Election :" + results.getElection().getElectionScope().getElectionType().getElectionType());
				System.out.print(" Comment :" + results.getCommentData().getCommentDesc());
				System.out.println("");
			}
		}
	}
}
