package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentative;

public class PublicRepresentativeDAO extends GenericDaoHibernate<PublicRepresentative, Long> implements IPublicRepresentativeDAO{

	public PublicRepresentativeDAO() {
		super(PublicRepresentative.class);
	}

}
