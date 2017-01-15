package com.itgrids.cardprint.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.TdpCadreCardPrint;

public interface ITdpCadreCardPrintDAO extends GenericDao<TdpCadreCardPrint, Long> {
	
	public Long getCardPrintVendorIdByConstituencyId(Long constituencyId);
	public void flushAndclearSession();
}
