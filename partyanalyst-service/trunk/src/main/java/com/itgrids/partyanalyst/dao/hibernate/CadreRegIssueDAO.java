package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueDAO;
import com.itgrids.partyanalyst.model.CadreRegIssue;

public class CadreRegIssueDAO extends GenericDaoHibernate<CadreRegIssue, Long> implements ICadreRegIssueDAO {

	public CadreRegIssueDAO() {
		super(CadreRegIssue.class);
		
	}

}
