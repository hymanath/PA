package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrRegionScopesDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrRegionScopes;


public class IvrRegionScopesDAO extends GenericDaoHibernate<IvrRegionScopes, Long> implements IIvrRegionScopesDAO{

	public IvrRegionScopesDAO() {
		super(IvrRegionScopes.class);
	}

}
