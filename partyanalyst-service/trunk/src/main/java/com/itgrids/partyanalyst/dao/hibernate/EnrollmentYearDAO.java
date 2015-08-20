package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEnrollmentYearDAO;
import com.itgrids.partyanalyst.model.EnrollmentYear;

public class EnrollmentYearDAO extends GenericDaoHibernate<EnrollmentYear, Long> implements IEnrollmentYearDAO{

	public EnrollmentYearDAO() {
		super(EnrollmentYear.class);
	}

}
