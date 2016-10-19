package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssueStatus;

public interface ICadreRegIssueStatusDAO extends GenericDao<CadreRegIssueStatus, Long> {
	public List<Object[]> getCadreRegIssueStatusType();

}
