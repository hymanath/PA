package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssuePerson;

public interface ICadreRegIssuePersonDAO extends GenericDao<CadreRegIssuePerson, Long>{
	public List<Object[]> getCadreRegIssuePersonDetails(Date fromDate,Date toDate);

}
