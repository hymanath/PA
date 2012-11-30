package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;

public class PublicationDateDAO extends
		GenericDaoHibernate<PublicationDate, Long> implements
		IPublicationDateDAO {	
	
	public PublicationDateDAO(){
		super(PublicationDate.class);
		
	}
}
