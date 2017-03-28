package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICensusParameterDAO;
import com.itgrids.partyanalyst.model.CensusParameter;

public class CensusParameterDAO extends GenericDaoHibernate<CensusParameter,Long> implements ICensusParameterDAO{
	
	public CensusParameterDAO()
	{
		super(CensusParameter.class);
	}

}
