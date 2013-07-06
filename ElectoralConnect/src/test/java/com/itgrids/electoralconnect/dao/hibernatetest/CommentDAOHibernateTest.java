package com.itgrids.electoralconnect.dao.hibernatetest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.ICommentDAO;


public class CommentDAOHibernateTest extends BaseDaoTestCase{
	
	private ICommentDAO commentDAO;

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}


	/*public void test(){
		commentDAO.getAll();
	}*/

	/*public void testgetTop5Comments()
	{
		List<Object[]> values = commentDAO.getTop5Comments(1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1]);
		}
	}*/
	
	/*public void testgetTop5Comments()
	{
		List<Object[]> values = commentDAO.getAllComments(1l,0,3);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1]);
		}
	}*/
	
	/*public void testCount()
	{
		System.out.println(commentDAO.getTotalCommentsCountByAnnouncementId(1l));
	}*/
	
	public void testCount()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date startDate = null;
		 Date endDate = null   ;
		try {
			startDate = df.parse("2013-06-04");
			endDate   = df.parse("2013-07-05");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Object[]> values = commentDAO.getCommentsBetwnnSelectedDates(startDate , endDate, 0, 20);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1]);
		}
		
		//System.out.println(commentDAO.getCommentsCountBetweenSelectedDates(startDate , endDate));
	}
	
	/*public void testCount()
	{
		List<Object[]> values = commentDAO.getAllComments(0, 20);
		for (Object[] parms : values) {
		System.out.println(parms[0]  +":"+ parms[1]);
		}
	    System.out.println(commentDAO.getTotalComments());
	}*/
	
	/*public void testCount()
	{
		System.out.println(commentDAO.abuseTheComment(1l));
	}*/
	
	/*public void testCount()
	{
		List<Object[]> values = commentDAO.getTop5Notifications(0, 5);
		for (Object[] parms : values) {
		System.out.println(parms[0]  +":"+ parms[1]);
		}
	}*/
	
	/*public void testCount()
	{
		System.out.println(commentDAO.getTotalCommentsCountByAnnouncementId(1l));
	}*/
}
