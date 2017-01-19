package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.model.VerificationStatus;

public class VerificationStatusDAO extends GenericDaoHibernate<VerificationStatus, Long> implements IVerificationStatusDAO {

	public VerificationStatusDAO() {
		super(VerificationStatus.class);
		
	}

}
