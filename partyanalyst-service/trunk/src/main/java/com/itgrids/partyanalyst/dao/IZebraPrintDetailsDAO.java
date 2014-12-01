package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ZebraPrintDetails;

public interface IZebraPrintDetailsDAO extends GenericDao<ZebraPrintDetails, Long>{ 

	public List<Object[]> getMemberShipCardPrintStatusByDate(List<Long> constituencyIds,Date fromDate, Date toDate,String type);
	
	public List<Object[]> getMemberShipPrintCardStatusByDate(List<Long> locationIdList,Date fromDate, Date toDate,String queryStr);
	
	public List<Object[]> getPrintedCardsDetails();
	
	public List<Object[]> getMemberShipCardPushDataStatusByDate(List<Long> locationIdList,Date fromDate, Date toDate,String queryStr);
	
	public Long getPrintedCountByLocations(List<Long> consituencyIdsList, String searchType,String dataType);
	
	public List<Object[]> getPrintedCountByLocationWise(List<Long> consituencyIdsList, String searchType,String dataType);
	
	public Long getTotalPrintStatusCount(List<Long> consituencyIdsList, String searchType,String dataType);
	public List<Object[]> getCadreDetailsByStatus(Long Id,String Status);
	
	public int updateVoterName(Long zebraPrintDetailsId,String voterName);
	
	public List<Object[]> getAllCadreDetailsByBatchCode(String batchCode);
}
