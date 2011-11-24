package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;


public class PartyGalleryDAO extends GenericDaoHibernate<PartyGallery,Long> implements IPartyGalleryDAO{
	
	public PartyGalleryDAO()
	{
		super(PartyGallery.class);
	}

}
