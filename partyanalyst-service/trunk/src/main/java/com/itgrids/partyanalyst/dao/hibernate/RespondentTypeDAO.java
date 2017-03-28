package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRespondentTypeDAO;
import com.itgrids.partyanalyst.model.RespondentType;

public class RespondentTypeDAO extends GenericDaoHibernate<RespondentType, Long> implements IRespondentTypeDAO{

	public RespondentTypeDAO() {
		super(RespondentType.class);
	}

}
