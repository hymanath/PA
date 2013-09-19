package com.itgrids.electoralconnect.dao.hibernatetest;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.ICommentReplyDAO;
import com.itgrids.electoralconnect.model.CommentReply;



public class CommentReplyDAOHibernateTest extends BaseDaoTestCase{
	
	private ICommentReplyDAO commentReplyDAO;
	
	public void setCommentReplyDAO(ICommentReplyDAO commentReplyDAO) {
		this.commentReplyDAO = commentReplyDAO;
	}
	
	/*public void test(){
		commentReplyDAO.getAll();
	}*/
	
	/*public void testcomments(){
		//List<CommentReply> cmntReply=new ArrayList<CommentReply>();
		Long count=commentReplyDAO.getRepliesForCommentByIdCount(19l);
			System.out.println(count);
		
	}*/
	/*public void testgen(){
		List<Long> arrList=new ArrayList<Long>();
		arrList.add(20l);
		arrList.add(30l);
		List<CommentReply> cmntReply=commentReplyDAO.getRepliesForCommentByIds(arrList);
		for(CommentReply param:cmntReply){
			System.out.println(param.getCommentedOn().longValue());
		}
	}*/
	public void testgen(){
		List<Long> arrList=new ArrayList<Long>();
		arrList.add(20l);
		arrList.add(30l);
		List<Object[]> cmntReply=commentReplyDAO.getReplyCountsByIdsList(arrList);
		for(Object[] obj:cmntReply){
			System.out.println(obj[0]+":"+obj[1]);
		}
	}


}
