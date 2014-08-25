package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INewsTypeDAO;

public class NewsTypeDAOHibernateTest  extends BaseDaoTestCase{
	private INewsTypeDAO newsTypeDAO;
	
	
	
	public INewsTypeDAO getNewsTypeDAO() {
		return newsTypeDAO;
	}



	public void setNewsTypeDAO(INewsTypeDAO newsTypeDAO) {
		this.newsTypeDAO = newsTypeDAO;
	}



	public void testGetAll(){
		List<Object[]> list = newsTypeDAO.getAllNewsTypes();
		System.out.println(list.size());
	}
}
