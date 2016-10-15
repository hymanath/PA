package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueStatusDAO;
import com.itgrids.partyanalyst.model.CadreRegIssueStatus;

public class CadreRegIssueStatusDAO extends GenericDaoHibernate<CadreRegIssueStatus, Long> implements ICadreRegIssueStatusDAO {

	public CadreRegIssueStatusDAO() {
		super(CadreRegIssueStatus.class);
		
	}

	
}
