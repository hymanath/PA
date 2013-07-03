package com.itgrids.electoralconnect.dao.hibernatetest;

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
	
	public void testCount()
	{
		System.out.println(commentDAO.getTotalCommentsCountByAnnouncementId(1l));
	}
}
