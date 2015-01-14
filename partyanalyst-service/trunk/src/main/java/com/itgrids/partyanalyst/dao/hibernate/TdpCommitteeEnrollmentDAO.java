package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;

public class TdpCommitteeEnrollmentDAO extends GenericDaoHibernate<TdpCommitteeEnrollment, Long>  implements ITdpCommitteeEnrollmentDAO {
	public TdpCommitteeEnrollmentDAO() {
		super(TdpCommitteeEnrollment.class);
	}
}
