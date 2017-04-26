package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtSmsActionType;

public interface IGovtSmsActionTypeDAO extends GenericDao<GovtSmsActionType, Long> {
	public List<String> getSMSTextforActionTypeId(Long actionTypeId,Long smsTypeId,Long languageId,Long govtUserTypeId);
}
