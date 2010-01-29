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
import com.itgrids.partyanalyst.model.EPaperUrl;

public interface IEPaperDAO extends GenericDao<EPaper,Long> {
	
	public List<EPaper> findByEpaperId(Long epaperId);
	
	public List<EPaper> findByName(String name);
	
	public List<EPaper> findByDescription(String description);
	
	public List<EPaper> findByClassification(String classification);
	
	public List<EPaper> findByStateId(Long stateId);
	
	public List<EPaper> findByCountryId(Long countryId);
	
	public List<EPaper> findByStateUrl(String stateUrl);
	
	public List<EPaper> findBycountryUrl(String countryUrl);
	
	public List<EPaper> findByLanguage(String language);
	
	public List<EPaper> findByImage(String image);

}
