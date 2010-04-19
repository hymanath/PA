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

import com.itgrids.partyanalyst.dao.ICommentCategoryPartyDAO;
import com.itgrids.partyanalyst.model.CommentCategoryParty;

public class CommentCategoryPartyDAOHibernateTest extends BaseDaoTestCase {

	private ICommentCategoryPartyDAO commentCategoryPartyDAO;

	public void setCommentCategoryPartyDAO(
			ICommentCategoryPartyDAO commentCategoryPartyDAO) {
		this.commentCategoryPartyDAO = commentCategoryPartyDAO;
	}
	
	@Test
	public void testGetAllPartyComments(){
		List<CommentCategoryParty> partyComments = commentCategoryPartyDAO.getAll();
		if(partyComments != null){
			System.out.println(" Results Size :" + partyComments.size());
			System.out.println(" Party :" + partyComments.get(0).getParty().getShortName());
			System.out.println(" Election :" + partyComments.get(0).getElection().getElectionYear());
			System.out.println(" Comment :" + partyComments.get(0).getCommentData().getCommentDesc());
		}
	}
	@Test
	public void testGetPartyComments(){
		//List<CommentCategoryParty> partyComments = commentCategoryPartyDAO.getCommentsOnAPartyInAnElection("Assembly", "2009", new Long(1), new Long(62));
		//List<CommentCategoryParty> partyComments = commentCategoryPartyDAO.getCommentsOnAPartyInAllElections(new Long(62));
		List<CommentCategoryParty> partyComments = commentCategoryPartyDAO.getCommentsOnAPartyInAnElection(new Long(10), new Long(24));
		if(partyComments != null){
			System.out.println(" Results Size :" + partyComments.size());
			for(CommentCategoryParty results:partyComments){
				System.out.println(" Party :" + results.getParty().getShortName());
				System.out.println(" Election :" + results.getElection().getElectionYear());
				System.out.println(" Comment :" + results.getCommentData().getCommentDesc());
			}
		}
	}
}
