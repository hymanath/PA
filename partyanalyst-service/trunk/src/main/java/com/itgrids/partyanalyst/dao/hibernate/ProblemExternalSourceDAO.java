/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;
import com.itgrids.partyanalyst.model.ProblemExternalSource;

public class ProblemExternalSourceDAO extends GenericDaoHibernate<ProblemExternalSource, Long> implements IProblemExternalSourceDAO {

	public ProblemExternalSourceDAO() {
		super(ProblemExternalSource.class);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemExternalSource> findByProblemExternalSourceName(String name) {
		return getHibernateTemplate().find("from ProblemExternalSource model where model.name = ?", name);
	}

	@SuppressWarnings("unchecked")
	public List<ProblemExternalSource> findByProblemExternalSourceId(
			Long sourceId) {		
		return getHibernateTemplate().find("from ProblemExternalSource model where model.problemExternalSourceId = ?", sourceId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemExternalSource> getExternalPersonDetails(Long politicalChangesId){
		return getHibernateTemplate().find(" from ProblemExternalSource model where model.problemExternalSourceId in " +
				" ( select model2.externalSource.problemExternalSourceId from PoliticalChanges model2 where model2.politicalChangesId = ?)", politicalChangesId);
	}
}
