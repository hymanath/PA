package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ITdpCadreLoginDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreLoginDetails;

public class TdpCadreLoginDetailsDAO extends GenericDaoHibernate<TdpCadreLoginDetails, Long> implements ITdpCadreLoginDetailsDAO{

	public TdpCadreLoginDetailsDAO() {
		super(TdpCadreLoginDetails.class);

	}

}
