package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAreaInchargeEnrollmentDAO;
import com.itgrids.partyanalyst.model.AreaInchargeEnrollment;

public class AreaInchargeEnrollmentDAO extends GenericDaoHibernate<AreaInchargeEnrollment, Long> implements IAreaInchargeEnrollmentDAO {

	public AreaInchargeEnrollmentDAO(){
		super(AreaInchargeEnrollment.class);
	}
	
}
