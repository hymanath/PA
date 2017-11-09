package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.model.JbCommitteeEnrollment;

public class JbCommitteeEnrollmentDAO extends GenericDaoHibernate<JbCommitteeEnrollment, Long> implements IJbCommitteeEnrollmentDAO {

	public JbCommitteeEnrollmentDAO() {
		super(JbCommitteeEnrollment.class);
		
	}

	

}
