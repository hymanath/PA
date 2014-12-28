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
	
	public List<Object[]> getParliamentWiseResults(List<Long> consituencyIdsList, String dataType);
	
	public List<ZebraPrintDetails> getCadreDetailsByMembershipId(String membershipId);
	
	
	public List<Object[]> getPrintedCountByLocationWise(List<Long> consituencyIdsList, String searchType,String dataType);
	
	public Long getTotalPrintStatusCount(List<Long> consituencyIdsList, String searchType,String dataType);
	public List<Object[]> getCadreDetailsByStatus(Long Id,String Status);
	
	public int updateVoterName(Long zebraPrintDetailsId,String voterName);
	
	public List<Object[]> getAllCadreDetailsByBatchCode(String batchCode);
	public List<Object[]> getPrintedCountByLocationWise(Long locationId, String searchType,String dataType);
	public List<Object[]> getPrintedCountByParliamentise(Long parliamentId, String dataType);
	public List<Object[]> getPrintedCountByInsertedTime(Long locationId, String searchType,String dataType);
	public List<Object[]> getPrintedCountByParlmentInsertedTime(Long parliamentId, String dataType);
	public List<Object[]> getJobCodesByLocationWise(Long locationId, String searchType);
	public List<Object[]> getJobCodesByParliament(Long parliamentId, String dataType);
	public List<Object[]> getAllCadreDetailsByBatchCodeandLocation(Date batchCode,String searchType,Long Id);
	public List<Object[]> getAllCadreDetailsByParliament(Date batchCode,String searchType,List<Long> Ids);
	public List<Object[]> getPrintedCountDetailsByStatusTypeSeacrh(List<Long> locationIdsList, String searchType,String dataType);
	public List<Object[]> getParliamentWiseResultsByStatusType(List<Long> parliamentIdsList, String dataType);
	public Long getPrintingCompletedCount(String state) ;
	public Long getIvrReadyCount(Date date,String state) ;
}
