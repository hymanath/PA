/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.model.CommentCategoryCandidate;
import com.itgrids.partyanalyst.service.impl.DateService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CommentCategoryCandidateDAOHibernateTest extends BaseDaoTestCase {
	
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;

	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}
	
	/*
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
	*/
	/*@SuppressWarnings("unchecked")
	public void testGetCommentsCountForACandidate(){
		List commentsCount = commentCategoryCandidateDAO.getCommentsCountInAnElectionForAPartyForCommentCategory(new Long(9),new Long(24),"WON");
		if(commentsCount != null && commentsCount.size() > 0){
			Object params = (Object)commentsCount.get(0);
			System.out.println("Comments Count :" + (Long)params);
		}
	}*/
	/*
	@SuppressWarnings("unchecked")
	public void testGetCommentCountGroupedByCategory(){
		List commentsCount = commentCategoryCandidateDAO.getCommentsCountGroupedByCommentCategory(new Long(9),new Long(24),"WON");
		if(commentsCount != null && commentsCount.size() > 0){
			for(int i=0;i<commentsCount.size();i++){
				Object[] params = (Object[])commentsCount.get(i);
				System.out.println(" Comment Category Id   :" + (Long)params[1]);
				System.out.println(" Comment Category Type :" + (String)params[2]);
				System.out.println(" Comments Count        :" + (Long)params[0]);
				System.out.println("...........................................");
			}
		}
	}
	*/
	/*@SuppressWarnings("unchecked")
	public void testGetCommentCategoryGroupedByConstituency(){
		List commentsCount = commentCategoryCandidateDAO.getCommentsCommentCategoryCountGroupedByConstituencyForAParty(new Long(9),new Long(62),"WON");
		if(commentsCount != null && commentsCount.size() > 0){
			for(int i=0;i<commentsCount.size();i++){
				Object[] params = (Object[])commentsCount.get(i);
				System.out.println(" Constituency Id   :" + (Long)params[1]);
				System.out.println(" Constituency      :" + (String)params[2]);
				System.out.println(" Comments Count        :" + (Long)params[0]);
				System.out.println("...........................................");
			}
		}
	}*/
	
	/*public void testGetAllCommentsByUserAndCategoryForANomination(){
		List list = commentCategoryCandidateDAO.getAllCommentsByUserAndCategoryForANomination(13606l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAllCommentsByPaidUserAndCategoryForANomination(){
		List list = commentCategoryCandidateDAO.getAllCommentsByPaidUserAndCategoryForANomination(16262l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetTotalPostedUsersGroupedByCommentCategory(){
		List list = commentCategoryCandidateDAO.getTotalPostedPaidUsersGroupedByCommentCategory(4L, 361L, IConstants.CANDIDATE_COMMENTS_LOST);
		List freeUserList = commentCategoryCandidateDAO.getTotalPostedFreeUsersGroupedByCommentCategory(4L, 361L, IConstants.CANDIDATE_COMMENTS_LOST);
		System.out.println(list.size());
		System.out.println(freeUserList.size());
		
		Long paidUsers = 0L;
		Long freeUsers = 0L;
		
		if(list.size() > 0 )
		{
			paidUsers = (Long)list.get(0);					
			System.out.println(" Paid User Count"+ paidUsers);
		}
		
		if(freeUserList.size() > 0)
		{
			freeUsers = (Long)freeUserList.get(0);					
			System.out.println(" Free User Count"+ freeUsers);
		}
		
		System.out.println(" Total Users = "+(paidUsers+freeUsers));
		
		
	}*/
	
	/*public void testGetAllOpenedComments()
	{
		System.out.println();
		List list = commentCategoryCandidateDAO.getAllOpenedComments(DateService.convertStringToDate("2010-12-13", "yyyy-MM-dd"),
				DateService.convertStringToDate("2010-12-13", "yyyy-MM-dd"));
		System.out.println(list.size());
	}
	 */	
	
	/*public void testGetPostedReasonsByFreeUserId()
	{
		List<Long> connectedUserIds = new ArrayList<Long>(0);
		connectedUserIds.add(1l);
		List list = commentCategoryCandidateDAO.getPostedReasonsByFreeUserId(2L, 0, 3, "asc", "model.commentData.commentDesc","ConnectedUserPoliticalReasons",connectedUserIds);
		
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0 ; i<list.size();i++)
			{
				Object[] params = (Object[])list.get(i);
				for(Object result : params)
					System.out.println("\t"+result);
			}
		}
	}*/
	
	/*public void testGetPostedReasonsCountByFreeUserId()
	{
		List list = commentCategoryCandidateDAO.getPostedReasonsCountByFreeUserId(2L);
		
		System.out.println(list.size());
	}*/
	
	/*public void testgetPostedReasonsCountOtherThanLoginUserId()
	{
		List list = commentCategoryCandidateDAO.getPostedReasonsCountOtherThanLoginUserId(2L);
		
		System.out.println(list.size());
	}
	*/
	/*public void testGetTotalPostedReasonsCountByFreeUserId()
	{
		Long count = commentCategoryCandidateDAO.getTotalPostedReasonsCountByFreeUserId(2L);
		System.out.println(count);
	}
	*/
	/*public void testgetCommonDataForAllProblems()
	{
		
		Date startDate = null;
		Date endDate = null;
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		try {
			startDate = (Date)format.parse("2012-04-13 10:30:09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			endDate = (Date)format.parse("2012-04-18 14:59:46");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List comments = commentCategoryCandidateDAO.getAllOpenedComments(startDate,endDate);
		if(comments  != null || comments.size() > 0)
		{
			for (int i = 0; i < comments.size(); i++)
			{
				Object[] params = (Object[])comments.get(i);
				System.out.println((Long)params[0]);
				System.out.println(params[1].toString());
				System.out.println(params[2].toString());
				System.out.println(params[3].toString());
				System.out.println((Long)params[4]);
				System.out.println(params[5].toString());
				System.out.println(params[6].toString());
				System.out.println(params[7].toString());
				System.out.println((Long)params[8]);
				System.out.println(params[9].toString());
				System.out.println(params[10].toString());
			System.out.println((Long)params[11]);
				
				
				
			}
			
		
			
			
		}			
	}*/
	
	/*public void testgetUsersBasedOnReasonIds()
	{
		List<Long> commentId = new ArrayList<Long>();
		commentId.add(150l);
		List<Object[]> list =commentCategoryCandidateDAO.getUsersBasedOnReasonIds(commentId);
		System.out.println(list.size());
		
	}*/
	
	public void testGetAllCommentsByUserAndCategoryForANomination()
	{
		List result = commentCategoryCandidateDAO.getAllCommentsAndCategoryForANomination(81145l);
		System.out.println(result.size());
	}
	
	/*public void test()
	{
		commentCategoryCandidateDAO.getAll();
	}*/
	
	/*public void testgetConnectedUsersPostedReasonsCount()
	{
		List<Long> connectedUserIds = new ArrayList<Long>(0);
		connectedUserIds.add(1l);
		List list = commentCategoryCandidateDAO.getConnectedUsersPostedReasonsCount(connectedUserIds);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size();i++)
			{
				Object[] params = (Object[])list.get(i);
				for(Object result : params)
					System.out.println("\t" +result);
			}
				
		}
	}*/
	
	/*public void testGetTotalPostedReasonsCount()
	{
		List<Long> connectedUserIds = new ArrayList<Long>(0);
		Long count = commentCategoryCandidateDAO.getTotalPostedReasonsCount(IConstants.TOTAL, 1l, connectedUserIds);
		System.out.println(count);
	}*/
	
	/*public void testGetPostedPoliticalReasonsByUserId()
	{
		List results = commentCategoryCandidateDAO.getPostedPoliticalReasonsByUserId(1971l, 0, 10);
		if(results != null && results.size() > 0)
		{
			for(int i=0;i<results.size();i++)
			{
				Object[] params = (Object[])results.get(i);
				for(Object list : params)
					System.out.println("\t" +list);
			}
		}
	}*/
	
	
}
