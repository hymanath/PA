package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPublicationElectionDAO;
import com.itgrids.partyanalyst.model.PublicationElection;

public class PublicationElectionDAO  extends GenericDaoHibernate<PublicationElection,Long> implements IPublicationElectionDAO {
  
	public PublicationElectionDAO(){
		super(PublicationElection.class);
	}

}
