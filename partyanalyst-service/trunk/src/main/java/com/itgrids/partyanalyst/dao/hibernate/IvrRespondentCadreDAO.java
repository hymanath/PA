package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrRespondentCadreDAO;
import com.itgrids.partyanalyst.model.IvrRespondentCadre;

public class IvrRespondentCadreDAO extends GenericDaoHibernate<IvrRespondentCadre, Long> implements IIvrRespondentCadreDAO{

	public IvrRespondentCadreDAO() {
		super(IvrRespondentCadre.class);
	}
}
