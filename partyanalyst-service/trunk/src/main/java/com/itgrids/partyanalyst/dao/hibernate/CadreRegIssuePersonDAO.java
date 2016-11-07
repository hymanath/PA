package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.CadreRegIssuePerson;

public class CadreRegIssuePersonDAO extends GenericDaoHibernate<CadreRegIssuePerson, Long>{

	public CadreRegIssuePersonDAO() {
		super(CadreRegIssuePerson.class);
		
	}

}
