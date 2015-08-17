package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.model.CadreHealthStatus;

public class CadreHealthStatusDAO extends GenericDaoHibernate<CadreHealthStatus, Long> implements ICadreHealthStatusDAO{

	public CadreHealthStatusDAO() {
		super(CadreHealthStatus.class);
		
	}

}
