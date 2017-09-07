package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreHealthDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreHealthDetails;

public class TdpCadreHealthDetailsDAO extends GenericDaoHibernate<TdpCadreHealthDetails, Long> implements ITdpCadreHealthDetailsDAO{

	public TdpCadreHealthDetailsDAO() {
		super(TdpCadreHealthDetails.class);
	}

}
