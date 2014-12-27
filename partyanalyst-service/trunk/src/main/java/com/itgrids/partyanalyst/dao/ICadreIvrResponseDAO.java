package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreIvrResponse;

public interface ICadreIvrResponseDAO extends GenericDao<CadreIvrResponse, Long>{
	public List<Date> getDates();
	public Long getIvrStatusCount(Date date,Long Id,String searchType);
	public List<Object[]> getIvrCadreDetails(Date date,Long Id,String searchType,Integer startIndex,Integer maxIndex);
	public Long getTotalIvrCount();
	
	public List<Object[]> getIvrCountByDate(Date fromDate,Date toDate,String state);
	public List<Object[]> getConstituencyWiseIvrCount();
	public List<Object[]> getTehsilWiseIVRInfo();
	public List<Object[]> getTehsilWiseIVRTotalCountInfo();
	public List<Object[]> getLocalBodyWiseIVRInfo();
	public List<Object[]> getLocalBodyWiseIVRTotalCountInfo();
	public List<Object[]> getPanchayatWiseIVRInfo(String state);
	public List<Object[]> getPanchayatWiseIVRCountInfo(String state);
	public List<Object[]> getIvrCountForAPAndTS();
}
