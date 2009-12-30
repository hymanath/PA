package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFundSourceDAO;
import com.itgrids.partyanalyst.model.FundSource;

public class FundSourceDAO extends GenericDaoHibernate<FundSource, Long> implements IFundSourceDAO{

	public FundSourceDAO(){
		super(FundSource.class);
	}
}
