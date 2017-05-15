package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeEnrollmentDAO;
import com.itgrids.partyanalyst.model.BoothInchargeEnrollment;

public class BoothInchargeEnrollmentDAO extends GenericDaoHibernate<BoothInchargeEnrollment, Long> implements IBoothInchargeEnrollmentDAO{

	public BoothInchargeEnrollmentDAO() {
		super(BoothInchargeEnrollment.class);
	}

}
