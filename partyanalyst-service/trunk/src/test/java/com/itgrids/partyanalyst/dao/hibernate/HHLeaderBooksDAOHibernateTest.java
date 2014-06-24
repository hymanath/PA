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

	public void test(){
		List<Long> boo = new ArrayList<Long>();
		boo.add(531l);
		
		Integer update=hhLeaderBooksDAO.deleteLeaderWithBooks(boo,445l);
		System.out.println(update);
	}

}
