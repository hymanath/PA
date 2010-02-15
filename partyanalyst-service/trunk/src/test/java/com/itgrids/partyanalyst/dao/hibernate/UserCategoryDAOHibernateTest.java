package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCategoryDAO;
import com.itgrids.partyanalyst.model.UserCategory;

public class UserCategoryDAOHibernateTest extends BaseDaoTestCase{

	private IUserCategoryDAO userCategoryDAO;

	public IUserCategoryDAO getUserCategoryDAO() {
		return userCategoryDAO;
	}

	public void setUserCategoryDAO(IUserCategoryDAO userCategoryDAO) {
		this.userCategoryDAO = userCategoryDAO;
	}
	
/*	public void testSave(){
		UserCategory userCategory = new UserCategory("MUNCIPALITY", null);
 		userCategoryDAO.save(userCategory);
 		setComplete();
	}*/
	
	public void testGetAll(){
		List<UserCategory> list = userCategoryDAO.getAll();
		assertEquals(1, list.size());
	}
	
	/*public void testDelete(){
		userCategoryDAO.remove(new Long(2));
		setComplete();
	}*/
}
