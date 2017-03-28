package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrRespondentLocationDAO;
import com.itgrids.partyanalyst.model.IvrRespondentLocation;
import com.itgrids.partyanalyst.model.TdpCadre;

public class IvrRespondentLocationDAO extends
		GenericDaoHibernate<IvrRespondentLocation, Long> implements
		IIvrRespondentLocationDAO {
	public IvrRespondentLocationDAO() {
		super(IvrRespondentLocation.class);
	}

	
}
