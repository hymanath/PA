package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISmsOtpDetailsDAO;
import com.itgrids.partyanalyst.model.SmsOtpDetails;

public class SmsOtpDetailsDAO extends GenericDaoHibernate<SmsOtpDetails, Long>
		implements ISmsOtpDetailsDAO {

	public SmsOtpDetailsDAO(){
		super(SmsOtpDetails.class);
	}
}
