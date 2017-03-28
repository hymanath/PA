package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationDAO;
import com.itgrids.partyanalyst.model.TdpCadreLocation;

public class TdpCadreLocationDAO extends GenericDaoHibernate<TdpCadreLocation, Long> implements ITdpCadreLocationDAO{

	public TdpCadreLocationDAO() {
		super(TdpCadreLocation.class);
		
	}

}
