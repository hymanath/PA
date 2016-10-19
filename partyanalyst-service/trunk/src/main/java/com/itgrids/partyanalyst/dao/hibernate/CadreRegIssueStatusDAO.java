package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueStatusDAO;
import com.itgrids.partyanalyst.model.CadreRegIssueStatus;

public class CadreRegIssueStatusDAO extends GenericDaoHibernate<CadreRegIssueStatus, Long> implements ICadreRegIssueStatusDAO {

	public CadreRegIssueStatusDAO() {
		super(CadreRegIssueStatus.class);
		
	}

	public List<Object[]> getCadreRegIssueStatusType() {
		Query query = getSession()
				.createQuery(
						"select model.cadreRegIssueStatusId,model.status from CadreRegIssueStatus model");
		return query.list();
	}

	
}
