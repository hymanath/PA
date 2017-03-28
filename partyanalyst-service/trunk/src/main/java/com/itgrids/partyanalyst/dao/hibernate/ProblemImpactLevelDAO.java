/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 29, 2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemImpactLevelDAO;
import com.itgrids.partyanalyst.model.ProblemImpactLevel;

public class ProblemImpactLevelDAO extends GenericDaoHibernate<ProblemImpactLevel, Long> implements IProblemImpactLevelDAO{

	public ProblemImpactLevelDAO() {
		super(ProblemImpactLevel.class);
	}

	@SuppressWarnings("unchecked")
	public List getProblemImpactLevelByName(String name) {
		return getHibernateTemplate().find("select model.problemImpactLevelId,model.problemImpactLevel "+
				"from ProblemImpactLevel model where model.problemImpactLevel = ?",name);
	}

}
