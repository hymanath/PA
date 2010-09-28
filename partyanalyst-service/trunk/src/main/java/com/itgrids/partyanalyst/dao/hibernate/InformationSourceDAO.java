/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.model.InformationSource;

public class InformationSourceDAO extends GenericDaoHibernate<InformationSource, Long> implements IInformationSourceDAO {

	public InformationSourceDAO() {
		super(InformationSource.class);
		
	}

	
}
