package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMahanaduPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.MahanaduPublicRepresentative;

public class MahanaduPublicRepresentativeDAO extends GenericDaoHibernate<MahanaduPublicRepresentative, Long> implements IMahanaduPublicRepresentativeDAO{

	public MahanaduPublicRepresentativeDAO()
	{
		super(MahanaduPublicRepresentative.class);
	}
}
