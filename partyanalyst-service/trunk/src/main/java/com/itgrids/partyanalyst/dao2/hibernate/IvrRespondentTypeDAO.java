package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrRespondentTypeDAO;
import com.itgrids.partyanalyst.model.IvrRespondentType;

public class IvrRespondentTypeDAO extends GenericDaoHibernate<IvrRespondentType, Long> implements IIvrRespondentTypeDAO{

	public IvrRespondentTypeDAO() {
		super(IvrRespondentType.class);
		// TODO Auto-generated constructor stub
	}

}
