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
    
	
	@SuppressWarnings("unchecked")
	public void testFindCommentsCountInAnElectionForAParty(){
		List commentCount = commentCategoryCandidateDAO.getCommentsCountForAPartyInAnElection(new Long(9), new Long(62));
		if(commentCount != null && commentCount.size() > 0){
			Object params = (Object)commentCount.get(0);
			Long countVal = (Long)params;
			System.out.println("Count Val :" + countVal);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetCommentsDetailsForAParty(){
		List commentsData = commentCategoryCandidateDAO.getCommentsResultsForAPartyInAnElection(new Long(9),new Long(62));
		if(commentsData != null && commentsData.size() > 0){
			for(int i=0;i<commentsData.size();i++){
				Object[] params = (Object[])commentsData.get(i);
				System.out.print(" Constituency  :" + params[1]);
				System.out.print(" Candidate     :" + params[3]);
				System.out.print(" Comment Desc  :" + params[4]);
				System.out.print(" Comment by    :" + params[5]);
				System.out.print(" Comment Date  :" + params[6]);
				System.out.print(" Comment Catg  :" + params[7]);
				
				System.out.println("..........................");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testGetCommentsCountForACandidate(){
		List commentsCount = commentCategoryCandidateDAO.getCommentsCountForACandidateInAConstituencyInAnELection(new Long(9), new Long(19368), new Long(232));
		if(commentsCount != null && commentsCount.size() > 0){
			Object params = (Object)commentsCount.get(0);
			System.out.println("Comments Count :" + (Long)params);
		}
	}
}
