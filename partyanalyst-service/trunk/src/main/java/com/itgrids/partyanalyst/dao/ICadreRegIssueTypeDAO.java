package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssueType;

public interface ICadreRegIssueTypeDAO extends GenericDao<CadreRegIssueType, Long> {
	public List<Object[]> getCadreRegIssueType();

}
