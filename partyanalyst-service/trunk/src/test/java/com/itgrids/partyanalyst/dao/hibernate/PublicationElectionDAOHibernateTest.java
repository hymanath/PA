package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPublicationElectionDAO;

public class PublicationElectionDAOHibernateTest extends BaseDaoTestCase {
   private IPublicationElectionDAO publicationElectionDAO;


	public void setPublicationElectionDAO(
			IPublicationElectionDAO publicationElectionDAO) {
		this.publicationElectionDAO = publicationElectionDAO;
	}
   
	public void testGetAll(){
		publicationElectionDAO.getAll();
	}
   
}
