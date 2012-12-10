package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteStatewiseDAO;

public class CasteStatewiseDAOHibernateTest extends BaseDaoTestCase {
	private ICasteStatewiseDAO casteStatewiseDAO;

	
	public void setCasteStatewiseDAO(ICasteStatewiseDAO casteStatewiseDAO) {
		this.casteStatewiseDAO = casteStatewiseDAO;
	}
	
	/*public void test()
	{
		casteStatewiseDAO.getAll();
	}*/


/*	public void testCasteStatewiseDAO(){
		
	
		List<Object[]> list=casteStatewiseDAO.getStatewiseCastNames(1l);
	
		System.out.println(list.size()); 
		//Object list1[]=list.toArray();
		for(Object[] params:list)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);

		}
		
	
}*/
	/*
	
	public void testgetCasteNamesByAutoPopulate(){
		
		List list=casteStatewiseDAO.getCasteNamesByAutoPopulate(1l,"ka");
		System.out.println(list);
		
	}*/
	
	/*
	public void testgetAllCasteDetails(){
		
		List<Object[]> list=casteStatewiseDAO.getAllCasteDetails();
		for(Object[] params : list)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
		
	}*/
}