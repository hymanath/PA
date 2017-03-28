package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsHistoryMobileNoDAO;
import com.itgrids.partyanalyst.model.SmsHistoryMobileNo;

public class SmsHistoryMobileNoDAO extends GenericDaoHibernate<SmsHistoryMobileNo, Long> implements
             ISmsHistoryMobileNoDAO {

	public SmsHistoryMobileNoDAO() {
		super(SmsHistoryMobileNo.class);
	}

	
	
}
