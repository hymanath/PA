package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.model.HamletBoothPublication;

public class HamletBoothPublicationDAO extends
		GenericDaoHibernate<HamletBoothPublication, Long> implements
		IHamletBoothPublicationDAO {
	public HamletBoothPublicationDAO(){		
		super(HamletBoothPublication.class);		
	}	
}
