/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EPaper;

public interface IEPaperDAO extends GenericDao<EPaper,Long> {
	
	public List<EPaper> findByEpaperId(Long epaperId);
	
	public List<EPaper> findByMainUrl(String mainUrl);
	
	public List<EPaper> findByEpaperUrl(String epaperUrl);
	
	public List findEPapersForDistrictByDistrictId(Long districtId);
	
	public List findMainEPapersForStateByStateId(Long districtId);

}
