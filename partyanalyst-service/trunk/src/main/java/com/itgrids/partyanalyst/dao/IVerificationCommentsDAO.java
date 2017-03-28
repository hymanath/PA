package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerificationComments;

public interface IVerificationCommentsDAO extends GenericDao<VerificationComments, Long> {
	public List<Object[]> getAlertCommentByAlertId(Long alertId);
	public List<Object[]> getAletConversationDtls(Long alertId);
}
