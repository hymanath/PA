package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.AlertIssueSubType;

public interface IAlertIssueSubTypeDAO extends GenericDao<AlertIssueSubType,Long>{

	public List<Object[]> getSubTypesByAlertIssueType(Long alertIssueTypeId);
}
