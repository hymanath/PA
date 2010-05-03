package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAO extends GenericDaoHibernate<ElectionGoverningBody, Long>
											implements IElectionGoverningBodyDAO{

	public ElectionGoverningBodyDAO(){
		super(ElectionGoverningBody.class);
	}
	
}
