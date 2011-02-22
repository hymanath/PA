/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InformationSource;

public interface IInformationSourceDAO extends GenericDao<InformationSource, Long> {

	public List<InformationSource> getInformationSourceByType(String sourceType);
	
	@SuppressWarnings("unchecked")
	public List getAllInformationSourceDetails();
}
