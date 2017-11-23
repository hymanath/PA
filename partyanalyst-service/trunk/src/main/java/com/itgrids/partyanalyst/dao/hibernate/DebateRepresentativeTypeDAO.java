package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.DebateRepresentativeType;

public class DebateRepresentativeTypeDAO extends GenericDaoHibernate<DebateRepresentativeType,Long> implements IDebateRepresentativeTypeDAO{

	public DebateRepresentativeTypeDAO()
	{
		super(DebateRepresentativeType.class);
	}
}
