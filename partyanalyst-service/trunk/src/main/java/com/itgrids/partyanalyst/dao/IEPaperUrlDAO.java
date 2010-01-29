package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EPaperUrl;

public interface IEPaperUrlDAO extends GenericDao<EPaperUrl, Long> {
	
	public List<EPaperUrl> findByEpaperUrlId(Long epaperUrlId);
	
	public List<EPaperUrl> findByDistrictUrl(String districtUrl);
	
	public List<EPaperUrl> findEPapersForDistrictByDistrictId(Long districtId);

}