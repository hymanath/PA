package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssuePersonDAO;
import com.itgrids.partyanalyst.model.CadreRegIssuePerson;

public class CadreRegIssuePersonDAO extends GenericDaoHibernate<CadreRegIssuePerson, Long> implements ICadreRegIssuePersonDAO{

	public CadreRegIssuePersonDAO() {
		super(CadreRegIssuePerson.class);
		
	}
	
	public List<Object[]> getCadreRegIssuePersonDetails(Date fromDate,Date toDate){
		Query query = getSession().createQuery("select model.cadreRegIssuePersonId," +
				" model.cadreRegIssue.cadreRegIssueId," +
				" model.mandal," +
				" model.name," +
				" model.mobileNo" +
				" from CadreRegIssuePerson model" +
				" where model.isDeleted = 'N' and date(model.insetedTime) between :fromDate and :toDate");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
		
		
	}

}
