package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreIvrResponse;

public interface ICadreIvrResponseDAO extends GenericDao<CadreIvrResponse, Long>{
	public List<Date> getDates();
	public Long getIvrStatusCount(Date date,Long Id,String searchType);
	public List<Object[]> getIvrCadreDetails(Date date,Long Id,String searchType,Integer startIndex,Integer maxIndex);
	public  Long  getTotalIvrCount(String state,List<Long> accessLocationIds);
	
	public List<Object[]> getIvrCountByDate(Date fromDate,Date toDate,String state,List<Long> accessLocationIds,Long campaignId);
	public List<Object[]> getConstituencyWiseIvrCount();
	public List<Object[]> getTehsilWiseIVRInfo();
	public List<Object[]> getTehsilWiseIVRTotalCountInfo();
	public List<Object[]> getLocalBodyWiseIVRInfo();
	public List<Object[]> getLocalBodyWiseIVRTotalCountInfo();
	public List<Object[]> getPanchayatWiseIVRInfo(String state);
	public List<Object[]> getPanchayatWiseIVRCountInfo(String state);
	public List<Object[]> getIvrCountForAPAndTS();
	public List<Object[]> getLocationWiseIVRInfo(Set<Long> locationIds,String locationType,Date startDate,Date endDate,List<Long> accessLocationIds);
	public List<Object[]> getLocationWiseIVRCountsInfo(Set<Long> locationIds,String locationType,Date startDate,Date endDate,Long constituencyId,Long campaignId);
	public List<Object[]> getIvrCountByLocationIdsAndType(List<Long> locationIds,String type);
	public List<Object[]> getCadreCommitteesIvRDetails(Long reportType,Long campainId);
	public List<Object[]> getStateWiseCommitterIvrDetails(List<Long> distIds,Long campainId);
	public List<Long> getPanchayatsCountIvrStarted(List<Long> distIds,Long campainId);
	public List<Long> getTotalIvrCalls(List<Long> distIds,Long campainId);
	public List<Long> getTotalAnsweredIvrCalls(List<Long> distIds,Long campainId);
}
