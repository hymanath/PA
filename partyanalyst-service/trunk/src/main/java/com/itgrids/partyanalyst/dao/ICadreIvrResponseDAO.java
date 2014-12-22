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
	public List<Object[]> getIvrCountByDate(Date date);
}
