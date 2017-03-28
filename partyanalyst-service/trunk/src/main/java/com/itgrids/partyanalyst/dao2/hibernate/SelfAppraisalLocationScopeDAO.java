package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalLocationScopeDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalLocationScope;

public class SelfAppraisalLocationScopeDAO extends GenericDaoHibernate<SelfAppraisalLocationScope, Long> implements
		ISelfAppraisalLocationScopeDAO {
	public SelfAppraisalLocationScopeDAO() {
		super(SelfAppraisalLocationScope.class);
	}
}
