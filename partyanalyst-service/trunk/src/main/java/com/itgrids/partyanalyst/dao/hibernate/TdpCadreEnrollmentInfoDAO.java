package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreEnrollmentInfo;

public class TdpCadreEnrollmentInfoDAO extends GenericDaoHibernate<TdpCadreEnrollmentInfo, Long> implements ITdpCadreEnrollmentInfoDAO {

	
	public TdpCadreEnrollmentInfoDAO() {
		super(TdpCadreEnrollmentInfo.class);
	}
}
