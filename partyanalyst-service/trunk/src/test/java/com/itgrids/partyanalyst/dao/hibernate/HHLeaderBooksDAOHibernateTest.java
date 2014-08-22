package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHLeaderBooksDAO;
import com.itgrids.partyanalyst.model.HHLeaderBooks;

public class HHLeaderBooksDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHLeaderBooksDAO hhLeaderBooksDAO;
	
	public void setHhLeaderBooksDAO(IHHLeaderBooksDAO hhLeaderBooksDAO) {
		this.hhLeaderBooksDAO = hhLeaderBooksDAO;
	}

	/*public void test(){
		List<Long> boo = new ArrayList<Long>();
		boo.add(531l);
		
		Integer update=hhLeaderBooksDAO.deleteLeaderWithBooks(boo,445l);
		System.out.println(update);
	}*/
	
	public void test(){
		List<Object[]> list = hhLeaderBooksDAO.getAllBooksInConstituency(282l);
		
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"\t"+obj[1].toString());
		}
		
		//System.out.println(list.size());
		
	}

}
