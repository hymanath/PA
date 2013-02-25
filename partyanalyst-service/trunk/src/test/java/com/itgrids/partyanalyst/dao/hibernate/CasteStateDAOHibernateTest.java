package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.utils.IConstants;

public class CasteStateDAOHibernateTest extends BaseDaoTestCase {
	private ICasteStateDAO casteStateDAO;

	
	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	
	/*public void test()
	{
		casteStateDAO.getAll();
	}*/


/*	public void testCasteStateDAO(){
		
	
		List<Object[]> list=casteStateDAO.getStatewiseCastNames(1l);
	
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
		
		List list=casteStateDAO.getCasteNamesByAutoPopulate(1l,"ka");
		System.out.println(list);
		
	}*/
	
	/*
	public void testgetAllCasteDetails(){
		
		List<Object[]> list=casteStateDAO.getAllCasteDetails();
		for(Object[] params : list)
		{
			System.out.println(params[0]);
			System.out.println(params[1]);
		}
		
	}*/
	
	public void testgetAllCasteDetailsForVoters1()
	{
		List<Object[]> list = casteStateDAO.getAllCastesForVoters(1l, 1l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]+"("+params[2]+")");
		}
	}
	
	/*public void testGetCasteStateByCasteId()
	{
		CasteState casteState = casteStateDAO.getCasteStateByCasteId(1l, 1l, 1l, 2l);
		if(casteState != null)
			System.out.println(casteState.getCaste().getCasteName());
	}*/
	
}