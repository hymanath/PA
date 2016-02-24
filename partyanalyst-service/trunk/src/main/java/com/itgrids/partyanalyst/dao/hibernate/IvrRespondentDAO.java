package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrRespondentDAO;
import com.itgrids.partyanalyst.model.IvrRespondent;

public class IvrRespondentDAO extends GenericDaoHibernate<IvrRespondent, Long> implements IIvrRespondentDAO{

	public IvrRespondentDAO()
	{
		super(IvrRespondent.class);
	}
	
}
