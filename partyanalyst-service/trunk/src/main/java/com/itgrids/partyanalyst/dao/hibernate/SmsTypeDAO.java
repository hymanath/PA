package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsTypeDAO;
import com.itgrids.partyanalyst.model.SmsType;

public class SmsTypeDAO extends GenericDaoHibernate<SmsType,Long> implements ISmsTypeDAO  {
	
	public SmsTypeDAO() {
		super(SmsType.class);
	}

}
