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

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;

public class CommentCategoryCandidateDAOHibernateTest extends BaseDaoTestCase {
	
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}
	
	
	@Test
	public void testGetAllCandidateComments(){
		List<CommentCategoryCandidate> candComments = commentCategoryCandidateDAO.getAll();
		if(candComments != null){
			System.out.println("Results Size :" + candComments.size());
			System.out.println("Candidate :" + candComments.get(0).getNomination().getCandidate().getLastname());
			System.out.println("Comment   :" + candComments.get(0).getCommentData().getCommentDesc());
		}
	}
	
	public void testGetCandidateComments(){
		//List<CommentCategoryCandidate> candComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAllElections(new Long(16610));
		//List<CommentCategoryCandidate> candComments = commentCategoryCandidateDAO.getAllCommentsOnACandidate("Assembly", "1999", new Long(16610));
		//List<CommentCategoryCandidate> candComments = commentCategoryCandidateDAO.getCommentsOnACandidateInAConstituency("Assembly", "2009", new Long(16610), new Long(8));
		List<CommentCategoryCandidate> candComments = commentCategoryCandidateDAO.getAllCommentsOnACandidateInAnElection(new Long(9), new Long(16610));
		if(candComments != null){
			System.out.println("Results Size :" + candComments.size());
			for(CommentCategoryCandidate results:candComments){
			System.out.print(" Candidate :" + results.getNomination().getCandidate().getLastname());
			System.out.print(" Comment   :" + results.getCommentData().getCommentDesc());
			System.out.println("");
			}
		}
	}

}
