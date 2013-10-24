package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.partyanalyst.dao.IUserNewsCategoryDAO;

public class CategoryDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserNewsCategoryDAO userNewsCategoryDAO;

	
		

	public void setUserNewsCategoryDAO(IUserNewsCategoryDAO userNewsCategoryDAO) {
		this.userNewsCategoryDAO = userNewsCategoryDAO;
	}

	
	/*public void test()
	{
		userNewsCategoryDAO.getAll();
	}*/
	
	/*public void testUpdateCategory(){
		int res=userNewsCategoryDAO.updateCategory(1l, 10l, "sasi", "true");
		System.out.println(res);
	}*/
	/*public void testUpdateCategory(){
		int res=userNewsCategoryDAO.updateCategoryName(1l, 10l, "sasi");
		System.out.println(res);
	}*/
	/*public void testUpdateCategory(){
		int res=userNewsCategoryDAO.updateCategoryStatus(1l, 10l, "true");
		System.out.println("deleted:"+res);
	}*/
	
	/*public void testgetAllCategories()
	{
		List<Object[]> list = categoryDAO.getAllCategories();
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
}
