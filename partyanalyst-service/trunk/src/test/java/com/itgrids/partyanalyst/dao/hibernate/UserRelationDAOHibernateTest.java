package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.model.UserRelation;

public class UserRelationDAOHibernateTest extends BaseDaoTestCase {
	
	IUserRelationDAO userRelationDAO;

	public void setUserRelationDAO(IUserRelationDAO userRelationDAO) {
		this.userRelationDAO = userRelationDAO;
	}
	
	/*public void test(){
	List<UserRelation> result = userRelationDAO.getAll();	
	for(UserRelation UserRelation: result)
	{
		System.out.println(UserRelation.getUserRelationId()+"------"+UserRelation.getRelationship());
		
	}
   }*/
   public void testGetRelationId(){
		List<Long> result = userRelationDAO.getRelationId("Husband");	
		System.out.println(result);
	   }

}
