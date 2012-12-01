package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.model.PublicationDate;

public class PublicationDateDAO extends
		GenericDaoHibernate<PublicationDate, Long> implements
		IPublicationDateDAO {	
	
	public PublicationDateDAO(){
		super(PublicationDate.class);
		
	}
	public List<PublicationDate> getPublicationDates(){
		return getHibernateTemplate().find("from PublicationDate model order by model.date desc " );
	}
}
