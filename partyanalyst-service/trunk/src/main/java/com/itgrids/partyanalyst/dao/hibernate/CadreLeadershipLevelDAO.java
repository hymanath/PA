package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreLeadershipLevelDAO;
import com.itgrids.partyanalyst.model.CadreLeadershipLevel;

public class CadreLeadershipLevelDAO extends GenericDaoHibernate<CadreLeadershipLevel, Long> implements ICadreLeadershipLevelDAO{

	public CadreLeadershipLevelDAO() {
		super(CadreLeadershipLevel.class);
	
	}

}
