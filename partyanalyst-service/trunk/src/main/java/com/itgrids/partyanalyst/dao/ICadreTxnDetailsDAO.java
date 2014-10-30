package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTxnDetails;


public interface ICadreTxnDetailsDAO extends GenericDao<CadreTxnDetails, Long>{
	public Integer updateCompleteStatus(String uniqueKey,Long constituencyId);
	public Long getPendingAmountForUser(String uniqueKey,Long constituencyId,Long userId);
	public Integer updatePendingAmount(Long pendingAmount,String uniqueKey,Long constituencyId,Long userId);
}
