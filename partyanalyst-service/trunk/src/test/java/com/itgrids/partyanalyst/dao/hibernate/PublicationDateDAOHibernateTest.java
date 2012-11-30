package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.model.PublicationDate;

public class PublicationDateDAOHibernateTest extends BaseDaoTestCase  {
	private IPublicationDateDAO publicationDateDAO;
	public void testGetAllData(){
		List<PublicationDate> result =  publicationDateDAO.getAll();
		System.out.println(result.size());
	}
}
