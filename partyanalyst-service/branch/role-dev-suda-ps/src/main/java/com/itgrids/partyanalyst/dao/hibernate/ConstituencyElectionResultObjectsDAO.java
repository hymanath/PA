/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;

public class ConstituencyElectionResultObjectsDAO extends GenericDaoHibernate<ConstituencyElectionResult, Long> 
                                                 implements IConstituencyElectionResultObjectsDAO {

	/*
	 *  Returns constituency Election Result Objects
	 */
	public ConstituencyElectionResultObjectsDAO() {
		super(ConstituencyElectionResult.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findConstituencyElectionResultObjects(
			Long constituencyId) {
		
		Query queryObject = getSession().createQuery("from ConstituencyElectionResult as model where model.constituencyElection.constiElecId in ( select constiElecId from ConstituencyElection as newmodel where newmodel.constituency.constituencyId = ?)");
		 queryObject.setParameter(0, constituencyId);
		return queryObject.list();
	}

	

}
