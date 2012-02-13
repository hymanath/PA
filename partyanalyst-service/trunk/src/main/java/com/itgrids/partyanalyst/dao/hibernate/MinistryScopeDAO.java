package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IMinistryScopeDAO;
import com.itgrids.partyanalyst.model.MinistryScope;

public class MinistryScopeDAO extends GenericDaoHibernate<MinistryScope,Long> implements IMinistryScopeDAO{

	public MinistryScopeDAO() {
		super(MinistryScope.class);
	}

}
