package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.model.TdpCadreCardPrint;

public interface ITdpCadreCardPrintDAO extends GenericDao<TdpCadreCardPrint,Long>{
	
	public List<Object[]> getCardPrintDetailsByMemberShipId(String memberShipId);
	public List<Object[]> getStatusWisePrintingCardsCounts(Long stateId,Long vendorId,Date fromDate,Date toDate);
	public List<Object[]> getBoxWisePrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId);
	
	public Integer updateTdpCadreCardPrintDataById(PrintVO printVO);
	public List<Object[]> postVerificationCadreData(Long constituencyId);
	public List<Object[]> getPrintPushedConstituencies();
	
	public void flushAndclearSession();
	public Long getTotalCadreInConstituency(Long vendorId , Long constituencyId);
}
