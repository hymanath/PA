package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentativeType;

public class PublicRepresentativeTypeDAO extends GenericDaoHibernate<PublicRepresentativeType, Long> implements IPublicRepresentativeTypeDAO{

	public PublicRepresentativeTypeDAO() {
		super(PublicRepresentativeType.class);
	}

}
