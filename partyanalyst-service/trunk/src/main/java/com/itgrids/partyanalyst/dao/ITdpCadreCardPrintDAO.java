package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCardPrint;

public interface ITdpCadreCardPrintDAO extends GenericDao<TdpCadreCardPrint,Long>{
	
	public List<Object[]> getCardPrintDetailsByMemberShipId(String memberShipId);
	public List<Object[]> getStatusWisePrintingCardsCounts(Long stateId,Long vendorId,Date fromDate,Date toDate);
}
