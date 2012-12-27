package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICategoryValuesDAO;

public class CategoryValuesDAOHibernateTest  extends BaseDaoTestCase{
	
	private ICategoryValuesDAO categoryValuesDAO;

	public void setCategoryValuesDAO(ICategoryValuesDAO categoryValuesDAO) {
		this.categoryValuesDAO = categoryValuesDAO;
	}
	
	public void testgetVoterCategoryValues(){
		
		List list=categoryValuesDAO.getVoterCategoryValues(1l);
		System.out.println(list);
	}

}
