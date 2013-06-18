/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
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
