package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemLikesDAO;
import com.itgrids.partyanalyst.model.ProblemLikes;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemLikesDAOHibernateTest extends BaseDaoTestCase{

	private IProblemLikesDAO problemLikesDAO;
	
	public void setProblemLikesDAO(IProblemLikesDAO problemLikesDAO) {
		this.problemLikesDAO = problemLikesDAO;
	}
	/*public void test()
	{
		problemLikesDAO.getAll();
	}*/
	
/*	public void testgetAllLikes()
	{
		List<Long> list = problemLikesDAO.getAllLikes(1l);
		System.out.println(list.size());
	}
	
	public void testCheckIfUserAlreadyLikedOrDisliked(){
		List<ProblemLikes> probLikesList=problemLikesDAO.checkIfUserAlreadyLikedOrDisliked(1L, 25L);
		if(probLikesList!=null && probLikesList.size()>0)
			System.out.println(probLikesList.get(0).getIsLiked());
		else
			System.out.println("not available"); 
	}*/
	
	public void testUpdateUserLikeOrDislike(){
		Integer rs=problemLikesDAO.updateUserLikeOrDislike(25L, 1L, "true");
		System.out.println(rs); 
		
	}
}