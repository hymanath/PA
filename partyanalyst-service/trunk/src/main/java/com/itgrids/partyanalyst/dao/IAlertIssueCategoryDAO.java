package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertIssueCategory;

public interface IAlertIssueCategoryDAO extends GenericDao<AlertIssueCategory, Long>{
	public List<Object[]> getIssueCategoryDetailsOfAlertType(Long alertTypeId);
	public List<Long> getIdOfName(String issueCategory);
}
