/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;
import com.itgrids.partyanalyst.model.ProblemExternalSource;

public interface IProblemExternalSourceDAO extends GenericDao<ProblemExternalSource, Long> {

	public List<ProblemExternalSource> findByProblemExternalSourceName(String name);
	
	public List<ProblemExternalSource> findByProblemExternalSourceId(Long sourceId);
	
	public List<ProblemExternalSource> getExternalPersonDetails(Long politicalChangesId);
}
