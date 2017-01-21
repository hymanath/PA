package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerificationConversation;

public interface IVerificationConversationDAO extends GenericDao<VerificationConversation, Long> {

	public Long getLastestVerificationConversionIdbyAlertId(Long alertId);
}
